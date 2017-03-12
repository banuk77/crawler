package com.wipro.web.crawler;

import com.wipro.web.crawler.view.ConsoleRenderer;
import com.wipro.web.crawler.view.Renderer;

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

    String url = args[0].startsWith("http") ? args[0] : "http://" + args[0];

    if (!url.endsWith("/"))
    {
      url = url + "/";
    }

    Crawler crawler = new CrawlerImpl(url, new PageServiceImpl());

    crawler.crawl(null);

    Renderer renderer = new ConsoleRenderer();
    renderer.render(crawler.getSiteMapRootTree());
  }

  public static void printUsage()
  {
    System.out.println("Usage: java App <domain>" + "\nExample" + "\n\tjava App example.com"
        + "\n\tjava App http://example.com");
  }
}
