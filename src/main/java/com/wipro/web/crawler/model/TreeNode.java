package com.wipro.web.crawler.model;

import java.util.List;

public interface TreeNode<T>
{

  TreeNode<T> getParent();

  List<TreeNode<T>> getChildren();

  int getChildrenCount();

  boolean isLeaf();

  T getModel();

}
