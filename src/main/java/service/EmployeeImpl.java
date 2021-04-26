package service;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeImpl implements IEmployee {
    private static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(1, "sdasd", "asdasda", "z2263315325275_586f04dfedf5756282a4317f27e2f4ac.jpg", true));
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public void save(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Employee employee) {

    }

    @Override
    public void remove(int id) {

    }
}
