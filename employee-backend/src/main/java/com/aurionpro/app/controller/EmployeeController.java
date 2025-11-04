package com.aurionpro.app.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.app.dto.EmployeeRequestDto;
import com.aurionpro.app.dto.EmployeeResponseDto;
import com.aurionpro.app.exception.ResourceNotFoundException;
import com.aurionpro.app.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Employee API", description = "API for managing employees")
@RestController
@RequestMapping("/api/employees")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Download all active employees as a CSV file")
    @GetMapping("/download")
    public void downloadEmployees(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");
        employeeService.downloadEmployeesAsCsv(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "products created sucessfully"),
            @ApiResponse(responseCode = "400",description = "INVALID format,only digits allowed")
    })
    @Operation(summary = "Add a new employee")
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployee(@Valid @RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto createdEmployee = employeeService.addEmployee(requestDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get an employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDto employeeDto = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        return ResponseEntity.ok(employeeDto);
    }

    @Operation(summary = "Update an existing employee")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, requestDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Operation(summary = "Soft delete an employee")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Get all deactivated employees")
    @GetMapping("/deactivated")
    public ResponseEntity<List<EmployeeResponseDto>> getDeactivatedEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getDeactivatedEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Activate a deactivated employee")
    @PutMapping("/{id}/activate")
    public ResponseEntity<EmployeeResponseDto> activateEmployee(@PathVariable Long id) {
        EmployeeResponseDto activatedEmployee = employeeService.activateEmployee(id);
        return ResponseEntity.ok(activatedEmployee);
    }
}