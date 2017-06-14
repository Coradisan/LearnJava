package test.com.test.thread;

/**
 * Created by Aaron on 2017/6/13 0013.
 */
public class PrintNum implements Runnable {

    int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (num < 100) {
                    num++;
                    System.out.println(Thread.currentThread().getName() + " " + num);
                } else
                    break;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();

        Thread one = new Thread(printNum);
        Thread two = new Thread(printNum);

        one.setName("one");
        two.setName("two");

        one.start();
        two.start();
    }
}
