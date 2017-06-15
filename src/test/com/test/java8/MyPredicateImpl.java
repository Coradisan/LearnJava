package test.java8;

/**
 * Created by Aaron on 2017/6/16 0016.
 */
public class MyPredicateImpl implements MyPredicate<Employee> {
    @Override
    public boolean compResult(Employee employee) {
        if (employee.getAge() > 15) {
            return true;
        }
        return false;
    }
}
