package com.taocaicai;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T06_TransferQueue {


    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "123456".toCharArray();
        char[] ac = "ABCDEF".toCharArray();

        TransferQueue queue = new LinkedTransferQueue();
        new Thread(() -> {
            for (char c : aI) {
                try {
                    queue.transfer(c);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {
            for (char c : ac) {
                try {
                    System.out.print(queue.take());
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();


    }
}
