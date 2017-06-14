package test.com.test.thread;

/**
 * Created by Aaron on 2017/6/12 0012.
 */
public class TestThread {
    public static void main(String[] args) throws Exception{
        CountNum work1 = new CountNum();
        work1.setName("work1");
        work1.start();

        /*CountNum work2 = new CountNum();
        work2.setName("work2");
        work2.start();
*/
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            //main线程偶数时候直接yield(放弃当前CPU的使用权)
//            if (i % 2 == 0) {
//                Thread.yield();
//                System.out.println(i);
//            }

            //main线程在i==20的时候，把work1加入进来
            if(i == 20){

                Thread.currentThread().join();
            }

            System.out.println(Thread.currentThread().getName() + "  " + i);
        }

    }
}
