package com.aurionpro.app.service;

import com.aurionpro.app.dto.EmployeeRequestDto;
import com.aurionpro.app.dto.EmployeeResponseDto;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	EmployeeResponseDto addEmployee(EmployeeRequestDto requestDto);

	List<EmployeeResponseDto> getAllEmployees();

	Optional<EmployeeResponseDto> getEmployeeById(Long id);

	EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto);

	void deleteEmployee(Long id);

	List<EmployeeResponseDto> getDeactivatedEmployees();

	EmployeeResponseDto activateEmployee(Long id);

	void downloadEmployeesAsCsv(HttpServletResponse response) throws IOException;
}