package test.com.test.thread;

/**
 * Created by Aaron on 2017/6/12 0012.
 */
public class Dual implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (Thread.currentThread().getName().equals("jiji")) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "  " + i);
                }
            } else {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "  " + i);
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Dual());
        threadOne.setName("jiji");
        threadOne.start();

        Thread threadTwo = new Thread(new Dual());
        threadTwo.setName("ouou");
        threadTwo.start();
    }
}
