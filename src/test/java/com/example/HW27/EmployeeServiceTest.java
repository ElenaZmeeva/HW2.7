package com.example.HW27;

import Service.CheckService;
import Service.EmployeeServiceImpl;
import com.example.HW27.Exceptions.EmployeeAlreadyAddedException;
import com.example.HW27.Exceptions.EmployeeNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl(new CheckService());

    @ParameterizedTest
    @MethodSource ("params")
    public void addNegativeTest(String firstName, String lastName, int department, int salary){
        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(out.add(firstName, lastName, department, salary)).isEqualTo(expected);

        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(()->out.add(firstName, lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource ("params")
    public void removeNegativeTest(String firstName, String lastName, int department, int salary) {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.remove(firstName,
                        lastName,
                        department,
                        salary));
    }


    @ParameterizedTest
    @MethodSource ("params")
    public void removePositiveTest(String firstName, String lastName, int department, int salary){

        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(out.add(firstName, lastName, department, salary)).isEqualTo(expected);

        assertThat(out.remove(firstName, lastName, department, salary)).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource ("params")
    public void findNegativeTest(String firstName, String lastName, int department, int salary) {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.find(firstName,
                        lastName,
                        department,
                        salary));

        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(out.add(firstName, lastName, department, salary)).isEqualTo(expected);
    }
    @ParameterizedTest
    @MethodSource ("params")
    public void findPositiveTest(String firstName, String lastName, int department, int salary){

        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(out.add(firstName, lastName, department, salary)).isEqualTo(expected);

        assertThat(out.find(firstName, lastName, department, salary)).isEqualTo(expected);
    }

    public static Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("Ivan","Ivanov", 1, 89000),
                Arguments.of("Petr", "Petrov",2, 56000),
                Arguments.of("Semen","Semenov", 3, 97000)
        );
}


    }

