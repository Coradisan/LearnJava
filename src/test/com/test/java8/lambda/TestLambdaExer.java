package test.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aaron on 2017/6/17 0017.
 */
public class TestLambdaExer {

    List<Employee> employees = Arrays.asList(
            new Employee("liu", 15, 12000),
            new Employee("jia", 18, 12000),
            new Employee("chen", 28, 42000),
            new Employee("oc", 16, 20000)
    );

    //1.先按salary 在按照age 排序
    @Test
    public void test() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getSalary().equals(e2.getSalary())) {
                return e1.getAge() - e2.getAge();
            }
            return -Integer.compare(e1.getSalary(), e2.getSalary());
        });
        employees.forEach(System.out::println);
    }

    //字符串转大写
    @Test
    public void test2() {
        String str = doIt("username", (x) -> x.toUpperCase().substring(2, 4));
        System.out.println(str);
    }

    public String doIt(String str, MyFuc2 myFuc2) {
        return myFuc2.upCaseStr(str);
    }


    //自己指定运算
    @Test
    public void test3() {
        Long result = doOP(100L, 20L, (a, b) -> a + b);
        System.out.println(result);

        Long result2 = doOP(100L, 20L, (a, b) -> a - b);
        System.out.println(result2);
    }

    public Long doOP(Long a, Long b, MyFuction<Long, Long> my) {
        return my.getValue(a, b);
    }
}

