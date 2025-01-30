package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    @GetMapping(path = "/getSecretMessage")
//    public String getMySecretMessage(){
//        return "Your Password is : Password*123##";
//    }

    // Mandatory employeeId
    // http://localhost:8080/employees/10

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
            Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
            return employeeDTO
                    .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                    .orElseThrow(()-> new ResourceNotFoundException("Resource not found with id : "+id));
    }

    //**************************************************************************************

    // for Optional fields RequestParam
    // http://localhost:8080/employees?age=25

//    @GetMapping
//    public String getAllEmployees(@RequestParam Integer age){
//        return "Hi you age is : " + age;
//    }

    //***************************************************************************************

    // for Multiple Optional fields RequestParam
    // http://localhost:8080/employees?age=25&sortBy=name

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    //*****************************************************************************************

    // POST

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //*******************************************************************************************

    // PUT

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, employeeId));
    }

    //*********************************************************************************************

    // DELETE
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
       boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
       if(gotDeleted)return ResponseEntity.ok(true);
       return ResponseEntity.notFound().build();
    }

    //***********************************************************************************************

    //PATCH
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId, @RequestBody Map<String, Object> updates){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
