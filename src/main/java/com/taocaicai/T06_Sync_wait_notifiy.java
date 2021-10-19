package com.taocaicai;

public class T06_Sync_wait_notifiy {


    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "123456".toCharArray();
        char[] ac = "ABCDEF".toCharArray();
        new Thread(() -> {

            synchronized (o) {
                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : ac) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();


    }
}
