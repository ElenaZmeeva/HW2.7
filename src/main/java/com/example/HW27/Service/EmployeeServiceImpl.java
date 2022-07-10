package com.example.HW27.Service;
import com.example.HW27.Employee;
import com.example.HW27.Exceptions.CheckException;
import com.example.HW27.Exceptions.EmployeeAlreadyAddedException;
import com.example.HW27.Exceptions.EmployeeNotFoundException;
import com.example.HW27.Exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

import java.util.*;
@Service

public class EmployeeServiceImpl implements EmployeeService {
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


@Override
    public Employee add(String firstName, String lastName, int department, int salary) {
check(firstName,lastName);
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
    @Override
    public Employee remove (String firstName, String lastName,  int department, int salary){
        check(firstName,lastName);
        Employee employee = new Employee(firstName, lastName,department, salary );
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(getKey(employee));
        return employee;
    }
@Override
    public Employee find (String firstName, String lastName, int department, int salary){
    check(firstName,lastName);
        Employee employee = new Employee(firstName, lastName,department, salary );
        if (!employees.containsKey(getKey(employee))) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    @Override
    public List<Employee> getAll () {
        return new ArrayList<>(employees.values());
    }


    private void check (String firstName, String lastName) {
       if(!(isAlpha(firstName) && isAlpha(lastName)));{
           throw new CheckException();
        }
    }


}
