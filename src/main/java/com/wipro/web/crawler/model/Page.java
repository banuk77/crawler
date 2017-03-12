package com.wipro.web.crawler.model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * A page model that represent a webpage.
 * 
 * @author Banu Kones
 *
 */
public class Page
{

  private final String url;
  private String title;
  private Set<String> hrefs = new TreeSet<>();;

  public Page(String url)
  {
    this.url = url;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getUrl()
  {
    return url;
  }

  public String getTitle()
  {
    return title;
  }

  public void addHref(String url)
  {
    hrefs.add(url);
  }

  public Set<String> getHrefs()
  {
    return Collections.unmodifiableSet(hrefs);
  }

  @Override
  public String toString()
  {
    return url;
  }

}