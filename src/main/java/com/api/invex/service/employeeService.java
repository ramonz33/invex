package com.api.invex.service;

import com.api.invex.dto.employeeResponseDto;
import com.api.invex.dto.employeeRequestDto;

import java.util.List;

public interface employeeService {
    List<employeeResponseDto> findAll();
    employeeResponseDto findById(Long id);
    employeeResponseDto create(employeeRequestDto request);
    List<employeeResponseDto> createAll(List<employeeRequestDto> requests);
    employeeResponseDto update(Long id, employeeRequestDto request);
    void deleteById(Long id);
    List<employeeResponseDto> searchByName(String name);
}
