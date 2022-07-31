package Service;

import com.example.HW27.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int department, int salary);

    Employee remove(String firstName, String lastName, int department, int salary);

    Employee find(String firstName, String lastName, int department, int salary);

    List<Employee> getAll();

}
