package com.taocaicai;

import java.util.concurrent.locks.LockSupport;

public class T05_park_unPark {
    public static Thread t1= null;
    public static Thread t2= null;

    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "123456".toCharArray();
        char[] ac = "ABCDEF".toCharArray();


        t1 = new Thread(() -> {
                for (char c : aI) {
                    System.out.print(c);
                    LockSupport.unpark(t2);
                    LockSupport.park();
            }
            LockSupport.unpark(t2);

        }, "t1");

        t2 = new Thread(() -> {
                for (char c : ac) {
                    LockSupport.park();
                    System.out.print(c);
                    LockSupport.unpark(t1);
                }
        }, "t2");


        t1.start();
        t2.start();

    }
}
