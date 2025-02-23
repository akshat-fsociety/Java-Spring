package com.codingshuttle.prod_ready_features.prod_ready_features.clients.impl;

import com.codingshuttle.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.codingshuttle.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.codingshuttle.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    // LOGGING WITH SLF4J (SIMPLE LOGGING FACADE FOR JAVA)
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.info("Trying to retrieve employees form getAllEmployees");

        try{
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not get the employee");
                    })
                    .body(new ParameterizedTypeReference<>(){
                    });

            // LOGGING
            log.debug("Successfully retrieved the employees from the getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {}", employeeDTOList.getData());

            return employeeDTOList.getData();
        } catch (Exception e) {

            // LOGGING
            log.error("Exception occurred in the getAllEmployees", e);

            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {

        log.info("Trying to retrieve employee by id {}", employeeId);

        try{
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient
                                                                .get()
                                                                .uri("employees/{employeeId}", employeeId)
                                                                .retrieve()
                                                                .onStatus(HttpStatusCode::is4xxClientError,(req, res)->{
                                                                    log.error(new String(res.getBody().readAllBytes()));
                                                                    throw new ResourceNotFoundException("could not get the employee");
                                                                })
                                                                .body(new ParameterizedTypeReference<>() {
                                                                });
            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in the getEmployeeById", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.info("Trying to create employee {}", employeeDTO);
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            log.error("Exception occurred in the createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
