package com.aurionpro.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleRequestDto {

    @NotBlank(message = "Role name is mandatory")
    private String name;

    @NotNull(message = "Department is mandatory")
    private Long departmentId;
}