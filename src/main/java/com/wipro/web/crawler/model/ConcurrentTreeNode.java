package com.wipro.web.crawler.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentTreeNode<T> implements TreeNode<T>
{
  private final TreeNode<T> parent;
  private final Set<TreeNode<T>> children;
  private T model;

  ReentrantLock lock = new ReentrantLock();

  public ConcurrentTreeNode(TreeNode<T> parent)
  {
    this.parent = parent;

    children = new HashSet<TreeNode<T>>();
  }

  @Override
  public TreeNode<T> getParent()
  {
    return parent;
  }

  @Override
  public void addChild(TreeNode<T> child)
  {
    lock.tryLock();
    try
    {
      children.add(child);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    finally
    {
      lock.unlock();
    }
  }

  @Override
  public Set<TreeNode<T>> getChildren()
  {
    lock.tryLock();
    try
    {
      return Collections.unmodifiableSet(children);
    }
    finally
    {
      lock.unlock();
    }
  }

  @Override
  public int getChildrenCount()
  {
    lock.tryLock();
    try
    {
      return children.size();
    }
    finally
    {
      lock.unlock();
    }
  }

  @Override
  public boolean isLeaf()
  {
    return getChildrenCount() == 0;
  }

  @Override
  public T getModel()
  {
    return model;
  }

  @Override
  public void setModel(T model)
  {
    this.model = model;
  }

  @Override
  public String toString()
  {
    return model != null ? model.toString() : super.toString();
  }

}
