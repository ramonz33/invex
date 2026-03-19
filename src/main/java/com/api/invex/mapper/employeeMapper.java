package com.api.invex.mapper;

import com.api.invex.dto.employeeRequestDto;
import com.api.invex.dto.employeeResponseDto;
import com.api.invex.entity.employee;
import org.springframework.stereotype.Component;

@Component
public class employeeMapper {

    public employee toEntity(employeeRequestDto dto) {
        if (dto == null) return null;

        return employee.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .secondLastName(dto.getSecondLastName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .position(dto.getPosition())
                .active(dto.getActive())
                .build();
    }

    public employeeResponseDto toResponseDto(employee entity) {
        if (entity == null) return null;

        return employeeResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .age(entity.getAge())
                .gender(entity.getGender())
                .birthDate(entity.getBirthDate())
                .position(entity.getPosition())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public void updateEntityFromDto(employeeRequestDto dto, employee entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setSecondLastName(dto.getSecondLastName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPosition(dto.getPosition());
        entity.setActive(dto.getActive());
    }
}
