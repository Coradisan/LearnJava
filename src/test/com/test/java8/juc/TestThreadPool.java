package test.java8.juc;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池
 */

public class TestThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> list = new ArrayList<>();
        //1.创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //2.为线程池中的线程分配任务
        for (int i = 0; i < 50; i++) {
            Future future = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i < 100; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
            list.add(future);
        }
        //3.关闭线程池
        pool.shutdown();

        list.stream().forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        });
    }
}


//
//public class TestThreadPool {
//    public static void main(String[] args) {
//        //1.创建线程池
//        ExecutorService pool = Executors.newFixedThreadPool(5);
//        TestThreadPoolDemo demo = new TestThreadPoolDemo();
//        //2.为线程池中的线程分配任务
//        for (int i = 0; i < 55; i++) {
//            pool.submit(demo);
//        }
//        //3.关闭线程池
//        pool.shutdown();
//    }
//}
//
//class TestThreadPoolDemo implements Runnable {
//    private int i = 0;
//
//    @Override
//    public void run() {
////        for (int i = 0; i < 55; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + ++i);
////        }
//    }
//}
