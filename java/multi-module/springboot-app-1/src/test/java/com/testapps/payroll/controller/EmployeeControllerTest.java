package com.testapps.payroll.controller;

import com.testapps.payroll.dtos.EmployeeDto;
import com.testapps.payroll.exceptions.EmployeeNotFoundException;
import com.testapps.payroll.mappers.EmployeeMapper;
import com.testapps.payroll.model.Employee;
import com.testapps.payroll.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {
    @Mock
    private EmployeeRepository repository;
    @Mock
    private EmployeeMapper employeeMapper;
    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllEmployees() {
        Employee e1 = new Employee("John Doe", "Developer");
        e1.setId(1L);
        Employee e2 = new Employee("Jane Smith", "Manager");
        e2.setId(2L);
        when(repository.findAll()).thenReturn(Arrays.asList(e1, e2));
        List<Employee> result = controller.all();
        assertThat(result).containsExactly(e1, e2);
    }

    @Test
    void testNewEmployee() {
        Employee newEmp = new Employee("John Doe", "Developer");
        Employee savedEmp = new Employee("John Doe", "Developer");
        savedEmp.setId(1L);
        when(repository.save(newEmp)).thenReturn(savedEmp);
        Employee result = controller.newEmployee(newEmp);
        assertThat(result).isEqualTo(savedEmp);
    }

    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee("John Doe", "Developer");
        emp.setId(1L);
        EmployeeDto dto = new EmployeeDto(1L, "John Doe", "Developer");
        when(repository.findById(1L)).thenReturn(Optional.of(emp));
        when(employeeMapper.toEmployeeDto(emp)).thenReturn(dto);
        EmployeeDto result = controller.one(1L);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> controller.one(1L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("1");
    }

    @Test
    void testReplaceEmployee_Update() {
        Employee existing = new Employee("John Doe", "Developer");
        existing.setId(1L);
        Employee update = new Employee("John Doe", "Manager");
        update.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(update);
        Employee result = controller.replaceEmployee(update, 1L);
        assertThat(result).isEqualTo(update);
        assertThat(existing.getRole()).isEqualTo("Manager");
    }

    @Test
    void testReplaceEmployee_Create() {
        Employee newEmp = new Employee("Jane Smith", "Manager");
        newEmp.setId(2L);
        when(repository.findById(2L)).thenReturn(Optional.empty());
        when(repository.save(newEmp)).thenReturn(newEmp);
        Employee result = controller.replaceEmployee(newEmp, 2L);
        assertThat(result).isEqualTo(newEmp);
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(repository).deleteById(1L);
        controller.deleteEmployee(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}