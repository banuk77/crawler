package com.wipro.web.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wipro.web.crawler.model.Page;
import com.wipro.web.crawler.model.TreeNode;

public class AppTest
{

  private URL indexURL;

  @Before
  public void setup()
  {
    indexURL = AppTest.class.getClassLoader().getResource("./index.html");

  }

  @Test
  public void crawlWebTest()
  {

    TreeNode<Page> tree = App.crawlWeb(indexURL.toExternalForm());
    assertNotNull("Tree should be non null", tree);

    assertEquals("number of Children should match the href in the document.", 3,
        tree.getChildrenCount());

    List<TreeNode<Page>> children = tree.getChildren();
    TreeNode<Page> b = children.get(0);
    assertEquals("Duplicates should not be allowed.", 1, b.getChildrenCount());
  }

}
