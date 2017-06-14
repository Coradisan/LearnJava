package test.com.test.thread;

/**
 * Created by Aaron on 2017/6/12 0012.
 */
public class TestWindow implements Runnable {
    int count = 100;
    Object o = new Object();

    @Override
    public void run() {
        while (count > 0) {
            synchronized (o) {
                if (count == 50) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName()
                        + " 卖出：" + count + "张票");
                count--;
            }
        }
        System.out.println("买完了");
    }

    public static void main(String[] args) {
        TestWindow testWindow = new TestWindow();
        Thread window1 = new Thread(testWindow);
        window1.setName("窗口1");
        window1.start();

        Thread window2 = new Thread(testWindow);
        window2.setName("窗口2");
        window2.start();
    }
}
