package test.java8.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程循环打印ABC
 */
public class TestLoop {
    public static void main(String[] args) {
        TestLoopDemo demo = new TestLoopDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        demo.LoopA();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        demo.LoopB();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        demo.LoopC();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}

class TestLoopDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int number = 1;

    public void LoopA() throws InterruptedException {
        lock.lock();
        try {
            //判断是否为1
            if (number != 1) {
                condition1.await();//1线程等哈
            }
            //打印
            System.out.println(Thread.currentThread().getName() + ": " + number);

            //唤醒
            number++;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void LoopB() throws InterruptedException {
        lock.lock();
        try {
            //判断是否为2
            if (number != 2) {
                condition2.await();//2线程等哈
            }
            //打印
            System.out.println(Thread.currentThread().getName() + ": " + number);

            //唤醒
            number++;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void LoopC() throws InterruptedException {
        lock.lock();
        try {
            //判断是否为1
            if (number != 3) {
                condition3.await();//2线程等哈
            }
            //打印
            System.out.println(Thread.currentThread().getName() + ": " + number);
            System.out.println("=====================================");
            //唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

