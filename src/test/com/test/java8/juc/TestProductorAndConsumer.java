package test.java8.juc;

/**
 * 生产者消费者模型
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor).start();
        new Thread(consumer).start();
    }
}

class Clerk {
    private int produce = 0;

    //进货
    public synchronized void get() {
        if (produce < 10) {
            System.out.println("开始进货：" + Thread.currentThread().getName() + " " + ++produce);
            this.notifyAll();
        } else {
            System.out.println("进货满了！");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    //卖货
    public synchronized void sale() {
        if (produce > 0) {
            System.out.println("开始出售：" + Thread.currentThread().getName() + " " + --produce);
            this.notifyAll();
        } else {
            System.out.println("出售完了！");
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }
    }

}

//生产者
class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}

//消费者
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}