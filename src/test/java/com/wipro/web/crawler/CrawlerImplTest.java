package com.wipro.web.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

public class CrawlerImplTest
{

  private URL indexURL;
  private String baseURL;
  private CrawlerImpl crawler;

  @Before
  public void setup() throws URISyntaxException
  {
    indexURL = CrawlerImplTest.class.getClassLoader().getResource("./index.html");
    String urlString = indexURL.toString();
    baseURL = urlString.substring(0, urlString.lastIndexOf('/')) + "/";

    PageServiceImpl pageService = new PageServiceImpl();
    crawler = new CrawlerImpl(baseURL, pageService);
  }

  @Test
  public void crawlTest()
  {

    crawler.crawl("index.html");
    TreeNode<Page> tree = crawler.getSiteMapRootTree();
    assertNotNull("Tree should be non null", tree);

    assertEquals("number of Children should match the href in the index.html.", 3,
        tree.getChildrenCount());

    Set<TreeNode<Page>> children = tree.getChildren();
    Optional<TreeNode<Page>> optional = children.stream()
        .filter(t -> t.getModel().getUrl().equals(baseURL + "b/b.html")).findAny();
    assertTrue("Reads the child page.", optional.isPresent());

    TreeNode<Page> b = optional.get();
    assertEquals("Duplicates should not be allowed.", 1, b.getChildrenCount());

    optional = children.stream().filter(t -> t.getModel().getUrl().equals(baseURL + "c/c.html"))
        .findAny();
    assertTrue("Reads the child page.", optional.isPresent());
    TreeNode<Page> c = optional.get();

    TreeNode<Page> google = c.getChildren().stream().findFirst().get();
    assertTrue("External urls should not be crawled.", google.isLeaf());
  }

}
