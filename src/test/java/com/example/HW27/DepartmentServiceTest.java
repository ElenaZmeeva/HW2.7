package com.example.HW27;

import Service.DepartmentService;
import Service.EmployeeServiceImpl;
import com.example.HW27.Exceptions.EmployeeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Ivan", "Ivanov", 1, 89000),
                new Employee("Petr", "Petrov",2, 56000),
                new Employee("Semen", "Semenov", 1, 97000),
                new Employee("Stepan", "Barsukov", 2, 64000),
                new Employee("Olga", "Kosatkina", 2, 83000)

        );
        when(employeeService.getAll()).thenReturn(employees);
    }
    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int department,Employee expected){
        Assertions.assertThat(departmentService.maxSalaryDepartment(department)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest(){
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.maxSalaryDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParams")
    public void employeeWithMinSalaryPositiveTest(int department,Employee expected){
        Assertions.assertThat(departmentService.minSalaryDepartment(department)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNegativeTest(){
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departmentService.minSalaryDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("employeesFromDepartmentParams")
    public void findEmployeesFromDepartmentPositiveTest(int department,List<Employee> expected){
        Assertions.assertThat(departmentService.findEmployeesFromDepartment(department)).containsExactlyElementsOf(expected);
    }

    @Test
    public void findEmployeesFromDepartmentNegativeTest(){
        Assertions.assertThat(departmentService.findEmployeesFromDepartment(3)).isEmpty();
    }

    public static Stream<Arguments> employeeWithMaxSalaryParams(){
        return Stream.of(
                Arguments.of(1, new Employee("Semen","Semenov", 1, 97000)),
                Arguments.of(2, new Employee("Olga","Kosatkina", 2, 83000))
                );
    }
    public static Stream<Arguments> employeeWithMinSalaryParams(){
        return Stream.of(
                Arguments.of(1,new Employee("Ivan","Ivanov", 1, 89000)),
                Arguments.of(2, new Employee("Petr", "Petrov",2, 56000))
        );
    }
    public static Stream<Arguments> employeesFromDepartmentParams(){
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Ivan", "Ivanov", 1, 89000),new Employee("Semen", "Semenov", 1, 97000))),
                Arguments.of(2,List.of (new Employee("Petr", "Petrov",2, 56000),new Employee("Stepan", "Barsukov", 2, 64000),
                        new Employee("Olga", "Kosatkina", 2, 83000))),
                Arguments.of( 3, Collections.emptyList())
                        );

    }
}
