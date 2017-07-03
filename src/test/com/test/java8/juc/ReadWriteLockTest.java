package test.java8.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {

        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    demo.get();
                }
            }
        }, "Read").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    demo.set(i);
                }
            }
        }, "Write").start();
    }
}

class ReadWriteLockDemo {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private int number;

    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " " + number);
        } finally {
            lock.readLock().unlock();
        }

    }

    public void set(int number) {
        lock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName() + " " + number);
        } finally {
            lock.writeLock().unlock();
        }

    }
}


