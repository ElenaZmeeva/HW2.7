package Service;

import com.example.HW27.Employee;
import com.example.HW27.Exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeServiceImpl employeeServiceImpl;

    public DepartmentService(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    public Employee maxSalaryDepartment(int department) {
        return employeeServiceImpl.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                 .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee minSalaryDepartment(int department) {
        return employeeServiceImpl.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findEmployeesFromDepartment(int department) {
        return employeeServiceImpl.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List <Employee>> findEmployees() {
        return employeeServiceImpl.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
