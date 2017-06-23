package test.java8.juc;

public class TestVolatile {
    public static void main(String[] args) {
        DemoTest dt = new DemoTest();
        new Thread(dt).start();
        while (true) {
            if (dt.isFlag()) {
                System.out.println("---------------");
                break;
            }
        }
    }

}

class DemoTest implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            System.out.println("flag:" + flag);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }
}