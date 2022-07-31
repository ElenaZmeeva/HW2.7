package Service;

import com.example.HW27.Employee;
import com.example.HW27.Exceptions.EmployeeAlreadyAddedException;
import com.example.HW27.Exceptions.EmployeeNotFoundException;
import com.example.HW27.Exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int LIMIT = 10;
    private final List<Employee> employees = new ArrayList<>();

    private final CheckService checkService;

    public EmployeeServiceImpl(CheckService checkService) {
        this.checkService = checkService;
    }



    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {

        Employee employee = new Employee(checkService.checkFirstName(firstName),
                checkService.checkLastName(lastName),
                department,
                salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    @Override
    public Employee remove(String firstName, String lastName, int department, int salary) {

        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName, int department, int salary) {
        return employees.stream()
                .filter(employee -> employee.getFirstName().equals(firstName)&& employee.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException:: new);
        }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
