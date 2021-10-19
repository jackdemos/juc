package com.taocaicai;

import java.util.ArrayList;
import java.util.List;

/**
 * @project JUC
 * @author Oakley
 * @created 2021-10-19 12:34:12:34
 * @package com.taocaicai
 * @description TODO
 * @since: 0.0.0.1
 */
public class JConsoleTool {

  static class OOMObject {

    public byte[] placeholder = new byte[64 * 1024];
  }

  public static void fillHelp(int num) throws InterruptedException {
    Thread.sleep(20000);
    List<OOMObject> list = new ArrayList<OOMObject>();
    for (int i = 0; i < num; i++) {
      Thread.sleep(50);
      list.add(new OOMObject());
    }
    System.gc();
  }

  public static void main(String[] args) throws InterruptedException {
    fillHelp(100);
    while (true){

    }
  }
}
