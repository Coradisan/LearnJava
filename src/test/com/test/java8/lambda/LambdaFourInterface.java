package test.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Java8 内置四大接口
 * <p>
 * 1.Consumer<T>消费型接口
 * void accept(T t);
 * <p>
 * 2.Supplier<T>供给型接口
 * T get();
 * <p>
 * 3.Function<T R>:函数型接口
 * R apply(T t);
 * <p>
 * 4.Predicate<T>:断言型接口
 * boolean test(T t);
 */
public class LambdaFourInterface {
    //Consumer<T> 消费型接口
    @Test
    public void test() {
        happy(1000D, (x) -> System.out.println(x));
    }

    public void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }


}
