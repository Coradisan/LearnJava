package test.java8.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 闭锁
 * 线程Callable返回值
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PrintAndSum printAndSum = new PrintAndSum();
        //未来某一时刻接受结果
        FutureTask<Integer> futureTask = new FutureTask<Integer>(printAndSum);
        new Thread(futureTask).start();

        int result = futureTask.get();//执行完后的结果
        System.out.println(result);
    }


}

/**
 * 打印 和 求和
 */
class PrintAndSum implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i < 50; i++) {
            sum += i;
            System.out.println(i);
        }

        return sum;
    }
}
