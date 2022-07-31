package Controller;

import Service.DepartmentService;
import com.example.HW27.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/max-salary")
    public Employee maxSalaryDepartment (@RequestParam("departmentId") int department){
    return departmentService.maxSalaryDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryDepartment (@RequestParam("departmentId") int department){
    return departmentService.minSalaryDepartment(department);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> findEmployeesFromDepartment (@RequestParam("departmentId") int department){
    return departmentService.findEmployeesFromDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findEmployees (){
    return departmentService.findEmployees();
    }

}
