package com.wipro.web.crawler;

import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

/**
 * The entry-point of the crawler application.
 *
 */
public class App
{
  public static void main(String[] args)
  {
    if (args.length == 0)
    {
      printUsage();
      System.exit(0);
    }

    String url = args[0].startsWith("http://") ? args[0] : "http://" + args[0];

    TreeNode<Page> siteMap = crawlWeb(url);
  }

  public static TreeNode<Page> crawlWeb(String url)
  {
    // TODO implement this
    return null;
  }

  public static void printUsage()
  {
    System.out.println("Usage: java App <domain> \nExample java App example.com");
  }
}
