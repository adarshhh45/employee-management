package com.aurionpro.app.service;

import com.aurionpro.app.dto.DepartmentDto;
import com.aurionpro.app.dto.RoleDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
    List<RoleDto> getRolesByDepartment(Long departmentId);
}