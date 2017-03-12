package com.wipro.web.crawler;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.wipro.web.crawler.model.Page;

public class PageServiceImpl implements PageService
{

  private static Pattern PATTERN_LINK = Pattern.compile("<a\\s.*?href=[\"|'](.*?)[\"|']");

  @Override
  public Page readPage(URL url) throws IOException
  {

    if (url == null)
    {
      return null;
    }

    Page page = new Page(url.toExternalForm());

    URLConnection connection = url.openConnection();
    String contentType = connection.getContentType();
    if (contentType != null && contentType.contains("text/html")
        || contentType.contains("text/plain"))
    {
      // read the content to extract any references this page has.
      int capacity = connection.getContentLength() == -1 ? 1024 : connection.getContentLength();

      StringWriter writer = new StringWriter(capacity);
      IOUtils.copy(connection.getInputStream(), writer, "utf-8");
      String content = writer.toString();

      Matcher match = PATTERN_LINK.matcher(content);

      while (match.find())
      {
        String link = match.group(1);
        if (link.startsWith(".") || link.startsWith("/"))
        {
          // this is a relative path. get the absolute url;
          URL linkUrl = new URL(url, link);
          link = linkUrl.toExternalForm();
        }

        page.addHref(link);
      }
    }

    return page;
  }

}
