package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "departments")
public class DepartmentController {

    // DEPENDENCY INJECTION
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    //************************************************************************************************************

    // *************************************** GET API's *********************************************************

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(()->new ResourceNotFoundException("Department Not Found" + id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    //************************************************************************************************************

    // *************************************** POST API *********************************************************

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment){
        DepartmentDTO savedDepartment = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    //************************************************************************************************************

    // *************************************** PUT API *********************************************************


    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentDTO, departmentId));
    }

    //************************************************************************************************************

    // *************************************** DELETE API *********************************************************

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        boolean gotDeleted = departmentService.deleteDepartmentById(departmentId);
        if(gotDeleted)return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

    //************************************************************************************************************

    // *************************************** PATCH API *********************************************************

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updatePartialDepartmentById(@PathVariable Long departmentId, @RequestBody Map<String, Object>updates){
        DepartmentDTO updateDepartment = departmentService.updatePartialDepartmentById(departmentId, updates);
        if(updateDepartment == null)return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(updateDepartment);
    }

    //************************************************************************************************************

}
