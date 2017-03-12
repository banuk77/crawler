package com.wipro.web.crawler;

import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

public interface Crawler
{
  /**
   * The start page can be any welcome page. (index/default) if this is null or
   * empty, the crawl will begin from whatever page returned by the web server
   * at root level
   * 
   * @param startPage
   */
  void crawl(String startPage);

  TreeNode<Page> getSiteMapRootTree();

}
