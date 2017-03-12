package com.wipro.web.crawler.view;

import java.util.Iterator;

import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

public class ConsoleRenderer implements Renderer
{

  @Override
  public void render(TreeNode<Page> siteMap)
  {

    System.out.println("SiteMap: " + siteMap.getModel().getUrl());

    printChildMap(siteMap, 4);
  }

  private void printChildMap(TreeNode<Page> node, int indent)
  {
    Iterator<TreeNode<Page>> iterator = node.getChildren().iterator();

    while (iterator.hasNext())
    {
      TreeNode<Page> item = iterator.next();
      String url = item.getModel().getUrl();
      String format = "%" + (url.length() + indent) + "s";

      System.out.println(String.format(format, url));
      printChildMap(item, indent + 4);
    }

  }

  public static String padLeft(String s, int indent)
  {
    return String.format("%" + indent + "s", s);
  }

}
