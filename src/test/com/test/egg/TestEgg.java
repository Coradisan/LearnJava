package test.com.test.egg;

/**
 * Created by Aaron on 2017/6/13 0013.
 */
public class TestEgg {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Customer customer = new Customer(clerk);

        Thread p1 = new Thread(producer);
        Thread p2 = new Thread(customer);

        p1.setName("T1");
        p2.setName("T2");

        p1.start();
        p2.start();
    }
}

//生产者
class Producer implements Runnable {
    Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }


    @Override
    public void run() {
        System.out.println("生产开始");
        while (true) {
            clerk.addProduct();
        }
    }
}

//店员
class Clerk {
    int product = 0;

    public synchronized void addProduct() {//生产产品
        if (product >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            product++;
            notify();
            System.out.println(Thread.currentThread().getName() + " 生产了" + product);
        }
    }


    public synchronized void reduceProduct() {//消费产品
        if (product <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            product--;
            notify();
            System.out.println(Thread.currentThread().getName() + " 消费了" + product);
        }
    }
}

//消费者
class Customer implements Runnable {
    Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费开始");
        while (true) {
            clerk.reduceProduct();
        }
    }
}