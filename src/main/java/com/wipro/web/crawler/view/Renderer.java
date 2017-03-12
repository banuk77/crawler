package com.wipro.web.crawler.view;

import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

public interface Renderer
{
  void render(TreeNode<Page> siteMap);
}
