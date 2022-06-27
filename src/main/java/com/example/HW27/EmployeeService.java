package com.example.HW27;
import org.springframework.stereotype.Service;

import java.util.*;
@Service

public class EmployeeService {
    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>(Map.of(

            "Ivan Ivanov",
            new Employee("Ivan","Ivanov ", 1, 89000),
            "Petr Petrov ",
            new Employee("Petr", "Petrov",2, 56000),
            "Semen Semenov ",
            new Employee("Semen ","Semenov ", 3, 97000),
            "Stepan Barsukov ",
            new Employee("Stepan ","Barsukov ", 4, 64000),
            "Olga Kosatkina ",
            new Employee("Olga ", "Kosatkina ",5, 83000),
            "Komarova Yana ",
            new Employee("Yana ", "Komarova ", 5, 71000),
            "Oleg Lobanov ",
            new Employee("Oleg","Lobanov" , 4, 88000),
            "Kirill Kirillov ",
            new Employee("Kirill ", "Kirillov ",3, 104000),
            "Sofia Kolos  ",
            new Employee("Sofia", "Kolos",2, 100400),
            "Ivan Sidorov",
            new Employee("Ivan ", "Sidorov ",1, 97000)
    ));
    private String getKey(Employee employee){
        return employee.getFirstName()+ employee.getLastName();
    }



    public Employee add (String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName,department, salary );
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(getKey(employee), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }
    public Employee remove (String firstName, String lastName,  int department, int salary){
        Employee employee = new Employee(firstName, lastName,department, salary );
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(getKey(employee));
        return employee;
    }

    public Employee find (String firstName, String lastName, int department, int salary){
        Employee employee = new Employee(firstName, lastName,department, salary );
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    public List<Employee> getAll () {
        return new ArrayList<>(employees.values());
    }






}
