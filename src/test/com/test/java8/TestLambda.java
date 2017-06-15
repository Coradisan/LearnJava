package test.java8;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.*;


/**
 * Created by Aaron on 2017/6/15 0015.
 */
public class TestLambda {

    @Test
    public void test() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);

        Comparator<Integer> comparator = (m1, m2) -> Integer.compare(m1, m2);
    }

    @Test
    public void test1() {
        Comparable comparable = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
    }

    //需求:获取当前公司员工中年龄大于15岁的人
    @Test
    public void test2() {
        System.out.println("----------原始数据-----------");
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }
        System.out.println("----------基本做法-----------");
        List<Employee> emps = filterEmployee(employees);
        for (Employee emp : emps) {
            System.out.println(emp.toString());
        }
        System.out.println("----------优化 策略-----------");
        List<Employee> emps2 = filterEmp(employees, new MyPredicateImpl());
        for (Employee emp : emps2) {
            System.out.println(emp.toString());
        }
        System.out.println("----------优化 内部类-----------");
        List<Employee> emps3 = filterEmp(employees, new MyPredicate<Employee>() {
            @Override
            public boolean compResult(Employee employee) {
                if (employee.getAge() > 15) {
                    return true;
                }
                return false;
            }
        });
        for (Employee emp : emps2) {
            System.out.println(emp.toString());
        }

        System.out.println("----------优化 Lambda-----------");
        List<Employee> emps4 = filterEmp(employees, (e) -> e.getAge() > 15);
        emps4.forEach(System.out::println);

        System.out.println("----------优化 Stream-----------");
        employees.stream()
                .filter((e) -> e.getAge() > 15) //必须return  boolean
                .limit(2) //限制两行
                .forEach(System.out::println);
        System.out.println("----------优化 Stream-其他操作----------");
        employees.stream()
//                .map(e -> e.getName())
                .map(Employee::getName)
                .forEach(System.out::println);

    }

    List<Employee> employees = Arrays.asList(
            new Employee("liu", 15, 12000),
            new Employee("jia", 18, 22000),
            new Employee("chen", 28, 42000),
            new Employee("oc", 16, 20000)
    );

    //获取当前公司员工中年龄大于15岁的人 原始
    public List<Employee> filterEmployee(List<Employee> employees) {
        List<Employee> emp = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() > 15) {
                emp.add(employee);
            }
        }
        return emp;
    }

    //优化方式一:通过策略模式 大于15
    public List<Employee> filterEmp(List<Employee> employees, MyPredicate<Employee> myPredicate) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (myPredicate.compResult(employee)) {//ture 大
                list.add(employee);
            }
        }
        return list;
    }

}
