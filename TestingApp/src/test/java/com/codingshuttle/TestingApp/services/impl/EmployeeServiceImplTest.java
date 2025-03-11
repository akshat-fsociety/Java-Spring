package com.codingshuttle.TestingApp.services.impl;

import com.codingshuttle.TestingApp.TestContainerConfiguration;
import com.codingshuttle.TestingApp.dto.EmployeeDto;
import com.codingshuttle.TestingApp.entities.Employee;
import com.codingshuttle.TestingApp.exceptions.ResourceNotFoundException;
import com.codingshuttle.TestingApp.repositories.EmployeeRepository;
import com.codingshuttle.TestingApp.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfiguration.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy  // It indicates that we are not injecting or mocking the modelmapper instead we are using original model mapper in the code it is just spy here.
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
        mockEmployee = Employee.builder()
                .id(1L)
                .name("Akshat")
                .email("aks@gm.com")
                .salary(100L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIdIsPresent_thenReturnEmployeeDto(){
        // ASSIGN
        Long id = mockEmployee.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee)); //STUBBING

        // ACT
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        //ASSERT
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());

        verify(employeeRepository).findById(id);
    }

    @Test
    void testGetEmployeeById_whenEmployeeIdIsNotPresent_thenThrowException() {
//        Assign
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

//        act & assert
        assertThatThrownBy(()->employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
    }

        @Test
    void testCreateNewEmployee_whenValidEmployee_thenCreateNewEmployee(){
//        Assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

//        Act
        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

//        Assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }

    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException() {
//    arrange
        when(employeeRepository.findByEmail(mockEmployee.getEmail())).thenReturn(List.of(mockEmployee));

//        act & assert
        assertThatThrownBy(()->employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: "+mockEmployee.getEmail());

        verify(employeeRepository).findByEmail(mockEmployee.getEmail());
        verify(employeeRepository, never()).save(any());
    }



    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExist_thenThrowException(){
//        arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

//        act & assert
        assertThatThrownBy(()->employeeService.updateEmployee(1L, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException(){
//        arrange
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployee.setName("random");
        mockEmployee.setEmail("random@g.com");
//        act& assert
        assertThatThrownBy(()->employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");

        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository, never()).save(any());
    }


    @Test
    void testUpdateEmployee_whenValidEmployee_thenUpdateEmployee(){

//        arrange
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployee.setName("random");
        mockEmployee.setSalary(199L);
        Employee newEmployee = modelMapper.map(mockEmployeeDto, Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);
//        act & assert

        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto);
        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());
    }

    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExist_thenThrowException(){
        when(employeeRepository.existsById(1L)).thenReturn(false);

//        act & assert
        assertThatThrownBy(()->employeeService.deleteEmployee(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: "+1L);

        verify(employeeRepository, never()).deleteById(anyLong());

    }

    @Test
    void testDeleteEmployee_whenEmployeeIsValid_thenDeleteEmployee(){
        when(employeeRepository.existsById(1L)).thenReturn(true);

//        act & assert
        assertThatCode(()->employeeService.deleteEmployee(1L))
                .doesNotThrowAnyException();
        verify(employeeRepository).deleteById(anyLong());

    }

}