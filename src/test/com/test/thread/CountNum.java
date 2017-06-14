package test.com.test.thread;

/**
 * Created by Aaron on 2017/6/12 0012.
 */
public class CountNum extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }
}
