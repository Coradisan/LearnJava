package test.java8.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Aaron on 2017/6/25 0025.
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicTestDemo demo = new AtomicTestDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo).start();
        }
    }
}

class AtomicTestDemo implements Runnable {
    //    private Integer sn = 0;
    private AtomicInteger sn = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName() + "---" + sn++);
        System.out.println(Thread.currentThread().getName() + "---" + sn.getAndIncrement());
    }
}