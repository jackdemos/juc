package com.taocaicai;

public class T04_Ph {


    public static void main(String[] args) {
        ChopStick cs0 = new ChopStick();
        ChopStick cs1 = new ChopStick();
        ChopStick cs2 = new ChopStick();
        ChopStick cs3 = new ChopStick();
        ChopStick cs4 = new ChopStick();

        Philosopher p1 = new Philosopher("A", 1, cs0, cs1);
        Philosopher p2 = new Philosopher("B", 2, cs1, cs2);
        Philosopher p3 = new Philosopher("C", 3, cs2, cs3);
        Philosopher p4 = new Philosopher("D", 4, cs3, cs4);
        Philosopher p5 = new Philosopher("E", 5, cs4, cs0);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

    }


    static class Philosopher extends Thread {

        ChopStick left, right;
        int index;

        Philosopher(String name, int index, ChopStick left, ChopStick right) {
            setName(name);
            this.left = left;
            this.right = right;
            this.index = index;

        }

        @Override
        public void run() {
            if (index % 2 == 0) {
                synchronized (left) {
                    synchronized (right) {
                        System.out.println(index + "吃完了");
                    }
                }
            } else {
                synchronized (right) {
                    synchronized (left) {
                        System.out.println(index + "吃完了");
                    }
                }
            }
        }
    }
}
