package com.taocaicai;

/**
 * @project JUC
 * @author Oakley
 * @created 2021-10-19 19:14:19:14
 * @package com.taocaicai
 * @description TODO
 * @since: 0.0.0.1
 */
public class ThreadLocalTest {

  public static void main(String[] args) {
    Bank bank = new Bank();
    Transform transform = new Transform(bank);
    new Thread(transform).start();
    new Thread(transform).start();
    System.out.println(bank.get());
  }
}

class Bank {
  private ThreadLocal<Integer> money = ThreadLocal.withInitial(() -> 1000);

  public int get() {
    return money.get();
  }

  public void set() {
    this.money.set((int) (this.money.get()+(this.money.get() * 0.5)));
  }
}

class Transform implements Runnable {
  Bank bank;

  public Transform(Bank bank) {
    this.bank = bank;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      bank.set();
      System.out.println(Thread.currentThread() + "  " + bank.get());
    }
  }
}
