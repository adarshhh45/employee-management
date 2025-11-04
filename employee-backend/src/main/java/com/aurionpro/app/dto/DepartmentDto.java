package com.aurionpro.app.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department name is required")
    private String name;
}