package test.java8.stream;

import org.junit.Test;
import test.java8.lambda.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI1 {
    @Test
    public void test() {
        //1.通过Collection
        List<String> list = new ArrayList<>();
        Stream stream = list.stream();
        //2.通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Arrays.stream(employees);
        //3.通过Stream的of()方法
        Stream stream1 = Stream.of("aa", "bb", "vv");
        //4.无线流
        Stream.generate(() -> Math.random()).limit(4).forEach(System.out::println);

    }

    List<Employee> employees = Arrays.asList(
            new Employee("liu", 15, 12000),
            new Employee("jia", 18, 12000),
            new Employee("chen", 28, 42000),
            new Employee("oc", 16, 20000),
            new Employee("123", 16, 132222)

    );

    @Test
    public void test1() {
        employees.stream()
                .filter((x) -> x.getAge() > 18)
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
    }


    @Test
    public void test3() {
        List<String> list = Arrays.asList("aaaa", "bdd", "ccc", "ddd");

        Stream<Stream<Character>> stream1 = list.stream().map(TestStreamAPI1::getNameCharacter);
        stream1.forEach((x) -> x.forEach(System.out::println));
        System.out.println("-----------------------------------------------");
        Stream<Stream<Character>> stream2 = employees.stream().map(TestStreamAPI1::getNameCharacter1);
        stream2.forEach((x) -> x.forEach(System.out::println));
        System.out.println("-----------------------------------------------");

        employees.stream().flatMap(TestStreamAPI1::getNameCharacter1).forEach(System.out::println);


    }


    public static Stream<Character> getNameCharacter(String employees) {
        List<Character> lists = new ArrayList();
        char[] chars = employees.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lists.add(chars[i]);
        }
        return lists.stream();
    }

    public static Stream<Character> getNameCharacter1(Employee employees) {
        List<Character> lists = new ArrayList();
        char[] chars = employees.getName().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lists.add(chars[i]);
        }
        return lists.stream();
    }

}
