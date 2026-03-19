package com.api.invex.service;

import com.api.invex.dto.employeeRequestDto;
import com.api.invex.dto.employeeResponseDto;
import com.api.invex.entity.employee;
import com.api.invex.exception.resourceNotFoundException;
import com.api.invex.mapper.employeeMapper;
import com.api.invex.repository.employeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class employeeServiceImpl implements employeeService {


    private final employeeRepository employeeRepository;
    private final employeeMapper employeeMapper;


    @Override
    public List<employeeResponseDto> findAll() {


        log.debug("START Service.findAll - Fetching employees from repository");

        List<employeeResponseDto> result = employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponseDto)
                .toList();

        log.debug("END Service.findAll - Retrieved {} employees", result.size());
        return result;


    }

    @Override
    public employeeResponseDto findById(Long id) {
        employee employee = getemployeeOrThrow(id);
        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public employeeResponseDto create(employeeRequestDto request) {
        employee employee = employeeMapper.toEntity(request);
        employee saved = employeeRepository.save(employee);
        return employeeMapper.toResponseDto(saved);
    }

    @Override
    public List<employeeResponseDto> createAll(List<employeeRequestDto> requests) {
        List<employee> employees = requests.stream()
                .map(employeeMapper::toEntity)
                .collect(Collectors.toList());

        return employeeRepository.saveAll(employees).stream()
                .map(employeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public employeeResponseDto update(Long id, employeeRequestDto request) {
        employee existing = getemployeeOrThrow(id);
        employeeMapper.updateEntityFromDto(request, existing);
        employee updated = employeeRepository.save(existing);
        return employeeMapper.toResponseDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        employee employee = getemployeeOrThrow(id);
        employeeRepository.delete(employee);
    }

    @Override
    public List<employeeResponseDto> searchByName(String name) {
        return employeeRepository.findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(employeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    private employee getemployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new resourceNotFoundException("employee not found with id: " + id));
    }
}
