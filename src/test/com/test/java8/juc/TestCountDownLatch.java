package test.java8.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁 CountDownLatch
 * 线程计数器
 */

public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);//5个线程计数
        CountNum thread = new CountNum(countDownLatch);

        Long begin = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(thread).start();
        }

        countDownLatch.await();//当减为0，唤醒

        Long end = System.currentTimeMillis();

        System.out.println("time" + (end - begin));
    }
}

class CountNum implements Runnable {
    private CountDownLatch countDownLatch;//注入进来

    public CountNum(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("hh");
        for (int i = 0; i < 5000; i++) {
            if (i % 2 == 0) {
//                System.out.println(i);
            }
        }
        countDownLatch.countDown();//每个线程run一次就减少一次
    }


}