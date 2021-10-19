package com.taocaicai;

/**
 * @project JUC
 * @author Oakley
 * @created 2021-10-13 10:43:10:43
 * @package com.taocaicai
 * @description TODO
 * @since: 0.0.0.1
 */
public class T03_Disorder {
  private static int x = 0, y = 0;
  private static int a = 0, b = 0;

  public static void main(String[] args) throws InterruptedException {
    int i = 0;
    for (; ; ) {
      i++;
      x = 0;
      y = 0;
      a = 0;
      b = 0;

      Thread t1 =
          new Thread(
              () -> {
                a = 1;
                x = b;
              });

      Thread t2 =
          new Thread(
              () -> {
                b = 1;
                y = a;
              });

      t1.start();
      t2.start();
      t1.join();
      t2.join();
      String result = "第 " + i + "次(" + x + "," + y + ")";
      if (x == 0 && y == 0) {
        System.err.println(result);
      } else {

      }
    }
  }
}
