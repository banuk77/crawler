package com.wipro.web.crawler;

import java.io.IOException;
import java.net.URL;

import com.wipro.web.crawler.model.Page;

public interface PageService
{
  Page readPage(URL baseUrl) throws IOException;
}
