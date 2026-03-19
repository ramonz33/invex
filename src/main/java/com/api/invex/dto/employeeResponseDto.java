package com.api.invex.dto;

import com.api.invex.utils.constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "employeeResponseDto", description = "Response payload for employee operations")
public class employeeResponseDto {
    @Schema(description = "employee ID", example = "1")
    private Long id;
    @Schema(description = "employee first name", example = "Rodolfo")
    private String firstName;
    @Schema(description = "employee middle name", example = "Gabriel")
    private String middleName;
    @Schema(description = "employee last name", example = "Juarez")
    private String lastName;
    @Schema(description = "employee second last name", example = "Perez")
    private String secondLastName;
    @Schema(description = "employee age", example = "29")
    private Integer age;
    @Schema(description = "employee gender", example = "M")
    private String gender;
    @Schema(description = "Birth date", example = "15-01-1996")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @Schema(description = "employee position", example = "Backend Java Developer")
    private String position;
    @Schema(description = "employee active flag", example = "true")
    private Boolean active;
    @Schema(description = "Record creation timestamp", example = "2026-02-23 13:56:43")
    @JsonFormat(pattern = constants.DATETIME_PATTERN)
    private LocalDateTime createdAt;
}
