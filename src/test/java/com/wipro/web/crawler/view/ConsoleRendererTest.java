package com.wipro.web.crawler.view;

import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.wipro.web.crawler.CrawlerImpl;
import com.wipro.web.crawler.PageServiceImpl;
import com.wipro.web.crawler.view.ConsoleRenderer;

public class ConsoleRendererTest
{

  private URL indexURL;
  private String baseURL;
  private CrawlerImpl crawler;
  ConsoleRenderer renderer;

  @Before
  public void setup() throws URISyntaxException
  {
    indexURL = ConsoleRendererTest.class.getClassLoader().getResource("./index.html");
    String urlString = indexURL.toString();
    baseURL = urlString.substring(0, urlString.lastIndexOf('/')) + "/";

    PageServiceImpl pageService = new PageServiceImpl();
    crawler = new CrawlerImpl(baseURL, pageService);
    renderer = new ConsoleRenderer();
  }

  @Test
  public void renderTest()
  {

    crawler.crawl("index.html");
    renderer.render(crawler.getSiteMapRootTree());

  }

}
