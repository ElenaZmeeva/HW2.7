package com.example.HW27;
import org.springframework.stereotype.Service;

import java.util.*;
@Service

public class EmployeeService {
    private static final int LIMIT = 10;

    private final Map<String, Employee> employees = new HashMap<>();
    private String getKey(Employee employee){
        return employee.getFirstName()+ employee.getLastName();
    }

    public Employee add (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(getKey(employee), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }
    public Employee remove (String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(getKey(employee));
        return employee;
    }

    public Employee find (String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    public List<Employee> getAll () {
        return new ArrayList<>(employees.values());
    }

}
