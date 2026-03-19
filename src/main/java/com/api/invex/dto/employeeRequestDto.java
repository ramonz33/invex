package com.api.invex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.api.invex.utils.constants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "employeeRequestDto", description = "Request payload for creating/updating an employee")
public class employeeRequestDto {

    @Schema(description = "employee first name", example = "Fredi")
    @NotBlank(message = "firstName is required")
    @Size(max = 100, message = "firstName must not exceed 100 characters")
    @Pattern(
            regexp = NAME_PATTERN,
            message = "firstName must contain only letters and spaces"
    )
    private String firstName;
    @Schema(description = "employee middle name", example = "Daniel")
    @NotBlank(message = "middleName is required")
    @Size(max = 100, message = "middleName must not exceed 100 characters")
    @Pattern(
            regexp = NAME_PATTERN,
            message = "firstName must contain only letters and spaces"
    )
    private String middleName;

    @Schema(description = "employee last name", example = "Cifuentes")
    @NotBlank(message = "lastName is required")
    @Size(max = 100, message = "lastName must not exceed 100 characters")
    @Pattern(
            regexp = NAME_PATTERN,
            message = "firstName must contain only letters and spaces"
    )
    private String lastName;

    @Schema(description = "employee second last name", example = "Robledo")
    @NotBlank(message = "secondLastName is required")
    @Size(max = 100, message = "secondLastName must not exceed 100 characters")
    @Pattern(
            regexp = NAME_PATTERN,
            message = "firstName must contain only letters and spaces"
    )
    private String secondLastName;

    @Schema(description = "employee age", example = "29", minimum = "18", maximum = "99")
    @NotNull(message = "age is required")
    @Min(value = MIN_AGE, message = "age must be at least 18")
    @Max(value = MAX_AGE, message = "age must be at most 99")
    private Integer age;

    @Schema(description = "employee gender", example = "M", allowableValues = {"M", "F"})
    @NotBlank(message = "gender is required")
    @Pattern(regexp = "M|F", message = "gender must be 'M' or 'F'")
    private String gender;

    @Schema(description = "Birth date (dd-MM-yyyy)", example = "15-01-1996")
    @NotNull(message = "birthDate is required")
    @JsonFormat(pattern = DATE_PATTERN_DD_MM_YYYY)
    private LocalDate birthDate;

    @Schema(description = "employee position/job title", example = "Backend Java Developer")
    @NotBlank(message = "position is required")
    @Size(max = 150, message = "position must not exceed 150 characters")
    private String position;

    @Schema(description = "employee active flag", example = "true")
    private Boolean active;
}
