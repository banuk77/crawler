package com.wipro.web.crawler.model;

import java.util.Set;

public interface TreeNode<T>
{

  TreeNode<T> getParent();

  void addChild(TreeNode<T> child);

  Set<TreeNode<T>> getChildren();

  int getChildrenCount();

  boolean isLeaf();

  T getModel();

  void setModel(T model);

}
