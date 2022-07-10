package com.example.HW27.Controller;
import com.example.HW27.Employee;
import com.example.HW27.Service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping ( "/add")
    public Employee add (@RequestParam ("firstName") String firstName,
                         @RequestParam ("lastName") String lastName,
                         @RequestParam("department") int department,
                         @RequestParam ("salary")int salary){
        return employeeServiceImpl.add (firstName, lastName, department,salary);
    }

    @GetMapping(  "/remove")
    public Employee remove (@RequestParam ("firstName") String firstName,
                            @RequestParam ("lastName") String lastName,
                            @RequestParam("department") int department,
                            @RequestParam ("salary")int salary){
        return employeeServiceImpl.remove(firstName, lastName, department, salary);
    }
    @GetMapping ( "/find")
    public Employee find ( @RequestParam ("firstName") String firstName,
                           @RequestParam ("lastName") String lastName,
                           @RequestParam("department") int department,
                           @RequestParam ("salary")int salary){
        return employeeServiceImpl.find(firstName, lastName, department, salary);
    }
    @GetMapping
    public List<Employee> getAll(){
        return employeeServiceImpl.getAll();
    }

}

