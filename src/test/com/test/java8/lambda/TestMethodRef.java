package test.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 调用 类::方法
 */
public class TestMethodRef {


    @Test
    public void test1() {
        Consumer consumer = System.out::println;
        consumer.accept(123);

        Supplier supplier = Employee::new;
        System.out.println(supplier.get());

        Function<String, String> function = (x) -> x.toUpperCase();
        System.out.println(function.apply("Abcd"));
    }

}
