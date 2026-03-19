package com.api.invex.controller;


import com.api.invex.dto.employeeRequestDto;
import com.api.invex.utils.constants;
import com.api.invex.dto.employeeResponseDto;
import com.api.invex.exception.apiErrorResponse;
import com.api.invex.service.employeeServiceImpl;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(constants.BASE_employeeS_PATH)
@RequiredArgsConstructor
@Tag(name = "employees", description = "Operations for employee management")
public class employeeController {

    private final employeeServiceImpl employeeService;

    @Operation(summary = "Get all employees", description = "Returns all registered employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = constants.CODE_STATUS_200, description = "employees retrieved successfully")
    })
    @GetMapping
    public List<employeeResponseDto> getAll() {
        long start = System.currentTimeMillis();
        log.info("START Controller.getAll - Request received for GET /api/employees");
        List<employeeResponseDto> employees = employeeService.findAll();
        long duration = System.currentTimeMillis() - start;
        log.info("END Controller.getAll - {} employees returned in {} ms", employees.size(), duration);

        return employees;

    }


    @Operation(summary = "Get employee by ID", description = "Returns one employee by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_200,
                    description = "employee retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = employeeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_404,
                    description = "employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = apiErrorResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public employeeResponseDto getById(
            @Parameter(description = "employee ID", example = "1")
            @PathVariable Long id) {
        long start = System.currentTimeMillis();
        log.info("START Controller.getById - Request received for GET /api/employees/{id}");

        employeeResponseDto employee = employeeService.findById(id);
        long duration = System.currentTimeMillis() - start;
        log.info("END Controller.getById - {} employee returned in {} ms", employee.toString(), duration);

        return employee;
    }

    @Operation(summary = "Create employee", description = "Creates a new employee")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_201,
                    description = "employee created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = employeeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_400,
                    description = "Validation error / malformed request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = apiErrorResponse.class)
                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public employeeResponseDto create(@Valid @RequestBody employeeRequestDto request) {
        long start = System.currentTimeMillis();
        log.info("START Controller.create - Request received for POST /api/employees");

        employeeResponseDto employee = employeeService.create(request);
        long duration = System.currentTimeMillis() - start;
        log.info("END Controller.create - {} employee returned in {} ms", employee.toString(), duration);

        return employee;
    }

    @Operation(summary = "Create employees in batch", description = "Creates multiple employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = constants.CODE_STATUS_201, description = "employees created successfully"),
            @ApiResponse(responseCode = constants.CODE_STATUS_400, description = "Validation error / malformed request")
    })
    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public List<employeeResponseDto> createBatch(@Valid @RequestBody List<employeeRequestDto> requests) {
        return employeeService.createAll(requests);
    }

    @Operation(summary = "Update employee", description = "Updates an existing employee by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_200,
                    description = "employee updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = employeeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_400,
                    description = "Validation error / malformed request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = apiErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_404,
                    description = "employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = apiErrorResponse.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public employeeResponseDto update(@PathVariable Long id,
                                      @Valid @RequestBody employeeRequestDto request) {

        long start = System.currentTimeMillis();
        log.info("START Controller.update - Request received for PUT /api/employees/{id}");

        employeeResponseDto employee = employeeService.update(id, request);
        long duration = System.currentTimeMillis() - start;
        log.info("END Controller.update - {} employee returned in {} ms", employee.toString(), duration);

        return employee;

    }

    @Operation(summary = "Search employees by name", description = "Searches employees by first name (contains, ignore case)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = constants.CODE_STATUS_200, description = "Search completed successfully")
    })
    @GetMapping("/search")
    public List<employeeResponseDto> searchByName(@RequestParam String name) {

        long start = System.currentTimeMillis();
        log.info("START Controller.searchByName - Request received for PUT /api/employees/{id}");

        List<employeeResponseDto> employees = employeeService.searchByName(name);
        long duration = System.currentTimeMillis() - start;
        log.info("END Controller.searchByName - {} employees returned in {} ms", employees.size(), duration);

        return employees;
    }

    @Operation(summary = "Delete employee", description = "Deletes an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_204,
                    description = "employee deleted successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = constants.CODE_STATUS_404,
                    description = "employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = apiErrorResponse.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        log.info("START Controller.delete - Request received for DELETE /api/employees/{id}");

        employeeService.deleteById(id);
        long duration = System.currentTimeMillis() - start;
        log.info("END Controller.delete - {} employee returned in {} ms", id, duration);


    }
}
