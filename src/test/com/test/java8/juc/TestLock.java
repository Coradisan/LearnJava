package test.java8.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Aaron on 2017/6/28 0028.
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "T1").start();
        new Thread(ticket, "T2").start();
        new Thread(ticket, "T3").start();
    }
}

class Ticket implements Runnable {
    private int num = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (num > 0) {
                    num--;
                    System.out.println(Thread.currentThread().getName() + " " + num);
                }
            } finally {

                lock.unlock();
            }
        }

    }
}