package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    // ******************************************** DEPENDENCY INJECTION ****************************************************************
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    // *********************************************** GET METHODS *******************************************************

    public Optional<DepartmentDTO> getDepartmentById(long id){
        return departmentRepository
                    .findById(id)
                    .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    // *********************************************** POST METHOD *******************************************************

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment){
        DepartmentEntity toSaveDepartment = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(toSaveDepartment);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    // *********************************************** PUT METHOD *******************************************************


    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, Long departmentId){
        isDepartmentExistById(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    // *********************************************** DELETE METHOD *******************************************************


    public boolean deleteDepartmentById(Long departmentId){
        isDepartmentExistById(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    // *********************************************** PATCH METHOD *******************************************************


    public DepartmentDTO updatePartialDepartmentById(Long departmentId, Map<String, Object> updates){
        isDepartmentExistById(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updates.forEach((field, value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    // UTIL FUNCTION TO CHECK IF DEPARTMENT EXISTS OR NOT
    public void isDepartmentExistById(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department not found : " + departmentId);
    }

}
