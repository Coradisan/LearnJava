package test.com.test.thread;

/**
 * Created by Aaron on 2017/6/13 0013.
 */
/*    private int money = 0;

    @Override
    public synchronized void run() {
        while (money <= 6000) {
            money += 1000;
            System.out.println(Thread.currentThread().getName() + " " + money);
        }
    }

    public static void main(String[] args) {

        Account account = new Account();

        Thread one = new Thread(account);
        one.setName("one");
        Thread two = new Thread(account);
        two.setName("two");

        one.start();
        two.start();
    }*/


public class Account {

    private int balance = 0;

    public synchronized void setBalance(int count) {
        balance += count;
        System.out.println(Thread.currentThread().getName() + " " + balance);
    }

}

class Customer implements Runnable {

    Account account;

    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.setBalance(1000);
        }
    }

}

class MainTest {
    public static void main(String[] args) {

        Account account = new Account();

        Customer one = new Customer(account);
        Customer two = new Customer(account);

        Thread thread1 = new Thread(one);
        Thread thread2 = new Thread(two);

        thread1.setName("T1");
        thread2.setName("T2");

        thread1.start();
        thread2.start();
    }
}
