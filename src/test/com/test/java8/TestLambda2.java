package test.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Lambda表达式：
 * 语法：
 * <p>
 * 1. 无参数
 * ()-> System.out.println("hello");
 * <p>
 * 2. 有一个参数 无返回值
 * (x)->System.out.println(x);
 * <p>
 * 3. 只有一个参数 小括号可以不写
 * x -> System.out.println(x);
 * <p>
 * 4.有两个以上的参数 有返回值，Lambda 中多条语句
 * Comparator<Integer> comparator1 = (x, y) -> {
 * System.out.println(x);
 * return Integer.compare(x, y);
 * };
 * <p>
 * 5.只有一条语句，return和大括号都可以不写
 * Comparator<Integer> comparator2 =(x, y) -> Integer.compare(x, y);
 * <p>
 * 6.jvm会有类型推断，左边
 * String[] str = {"aa","bb","cc","dd"}; //分开会报错
 * <p>
 * 上联：左右遇一括号省
 * 下联：左侧瑞端类型省
 */
public class TestLambda2 {

    @Test
    public void test1() {
        int num = 1;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        r.run();
        System.out.println("-----------------------------");
        Runnable r1 = () -> System.out.println("hellow world!" + num);
        r1.run();
    }

    @Test
    public void test2() {
        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o.toString());
            }
        };
        consumer.accept("haha");

        Consumer consumer1 = (o) -> System.out.println(o);
        consumer1.accept("hahaha");
    }

    @Test
    public void test3() {
        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        Comparator<Integer> comparator1 = (x, y) -> {
            System.out.println(x);
            return Integer.compare(x, y);
        };

        Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);


        show(new HashMap<>());//通过目标参数反推
    }

    public void show(HashMap<Integer, String> hashMap) {

    }

    @Test
    public void test4() {
        //接口实现类myFunc1
        Integer num = operation(12, new MyFunc<Integer>() {
            @Override
            public Integer getValue(Integer o) {
                return o * o;
            }
        });
        System.out.println(num);

        //接口实现类myFunc2
        Integer num1 = operation(12, (x) -> x * x);
        System.out.println(num1);
    }

    public Integer operation(Integer num, MyFunc<Integer> func) {
        return func.getValue(num);
    }
}
