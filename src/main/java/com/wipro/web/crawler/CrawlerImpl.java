package com.wipro.web.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.wipro.web.crawler.model.ConcurrentTreeNode;
import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

public class CrawlerImpl implements Crawler
{
  private final String baseURL;

  private TreeNode<Page> rootTreeNode;

  private PageService pageService;

  Set<String> crawledPages;

  private int numberOfThreads = 3;

  public CrawlerImpl(String baseURL, PageService pageService)
  {
    this.baseURL = baseURL;
    this.pageService = pageService;
    this.crawledPages = Collections.synchronizedSet(new HashSet<>());
  }

  public void setNumberOfThreads(int numberOfThreads)
  {
    this.numberOfThreads = numberOfThreads;
  }

  @Override
  public void crawl(String startPage)
  {

    if (startPage != null && !startPage.isEmpty())
    {
      startPage = baseURL + startPage;
    }
    else
    {
      startPage = baseURL;
    }

    rootTreeNode = createTreeNode(startPage, null);

    startChildCrawlExecutor(rootTreeNode);
  }

  private void startChildCrawlExecutor(TreeNode<Page> parentNode)
  {
    Page page = parentNode.getModel();

    if (canCrawl(page.getUrl()))
    {
      crawledPages.add(page.getUrl());

      Set<String> hrefs = page.getHrefs();
      if (!hrefs.isEmpty())
      {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (String url : hrefs)
        {
          executor.submit(() -> addChild(url, parentNode));
        }

        executor.shutdown();
        try
        {
          executor.awaitTermination(5, TimeUnit.HOURS);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }

  }

  private TreeNode<Page> createTreeNode(String url, TreeNode<Page> parent)
  {
    try
    {
      TreeNode<Page> node = new ConcurrentTreeNode<Page>(parent);
      Page page = getPage(url);
      node.setModel(page);
      return node;
    }
    catch (Exception e)
    {
      // Invalid base url;
      // e.printStackTrace();
      throw new IllegalArgumentException("Could not locate the url: " + baseURL);
    }
  }

  private boolean canCrawl(String url)
  {
    // we are not crawling if url is outside of the base url and/or if it
    // already crawled;

    return url.startsWith(baseURL) && !crawledPages.contains(url);
  }

  private TreeNode<Page> addChild(String url, TreeNode<Page> parent)
  {

    // avoid duplicate roots
    if (crawledPages.contains(url))
    {
      return null;
    }

    try
    {
      TreeNode<Page> child = createTreeNode(url, parent);
      parent.addChild(child);

      startChildCrawlExecutor(child);

      return child;
    }
    catch (Exception ignore)
    {

    }

    return null;
  }

  @Override
  public TreeNode<Page> getSiteMapRootTree()
  {
    return rootTreeNode;
  }

  private Page getPage(String baseUrl) throws MalformedURLException, IOException
  {
    URL url = new URL(baseUrl);

    return pageService.readPage(url);
  }
}
