package com.aurionpro.app.controller;

import com.aurionpro.app.dto.DepartmentDto;
import com.aurionpro.app.dto.RoleDto;
import com.aurionpro.app.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Department API", description = "API for managing departments")
@RestController
@RequestMapping("/api/departments")
@CrossOrigin("http://localhost:4200")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(summary = "Add a new department")
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartment = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all available departments")
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @Operation(summary = "Get all roles for a specific department")
    @GetMapping("/{departmentId}/roles")
    public ResponseEntity<List<RoleDto>> getRolesByDepartment(@PathVariable Long departmentId) {
        List<RoleDto> roles = departmentService.getRolesByDepartment(departmentId);
        return ResponseEntity.ok(roles);
    }
}