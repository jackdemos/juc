package com.taocaicai;

/**
 * @project JUC
 * @author Oakley
 * @created 2021-10-19 13:06:13:06
 * @package com.taocaicai
 * @description TODO
 * @since: 0.0.0.1
 */
public class JConsoleThreadLock {

  static class SyncAddRunnable implements Runnable {

    int a, b;

    public SyncAddRunnable(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public void run() {
      synchronized (Integer.valueOf(a)) {
        synchronized (Integer.valueOf(b)) {
          System.out.println("print a+b = :" + (a + b));
        }
      }
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 1000; i++) {
      new Thread(new SyncAddRunnable(1, 2)).start();
      new Thread(new SyncAddRunnable(2, 1)).start();
    }
  }
}
