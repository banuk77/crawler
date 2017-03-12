package com.wipro.web.crawler.model;

/**
 * A page model that represent a webpage.
 * 
 * @author Banu Kones
 *
 */
public class Page
{

  private final String url;
  private final String parentUrl;
  private String title;

  public Page(String url, String parentUrl)
  {
    this.url = url;
    this.parentUrl = parentUrl;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getUrl()
  {
    return url;
  }

  public String getParentUrl()
  {
    return parentUrl;
  }

  public String getTitle()
  {
    return title;
  }

}