package com.aurionpro.app.service.impl;

import com.aurionpro.app.dto.DepartmentDto;
import com.aurionpro.app.dto.RoleDto;
import com.aurionpro.app.entity.Department;
import com.aurionpro.app.exception.DuplicateResourceException;
import com.aurionpro.app.exception.ResourceNotFoundException;
import com.aurionpro.app.mapper.DepartmentMapper;
import com.aurionpro.app.mapper.RoleMapper;
import com.aurionpro.app.repository.DepartmentRepository;
import com.aurionpro.app.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final RoleMapper roleMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, RoleMapper roleMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        log.info("Attempting to add a new department with name: {}", departmentDto.getName());
        if (departmentRepository.existsByName(departmentDto.getName())) {
            log.error("Failed to add department. Name '{}' already exists.", departmentDto.getName());
            throw new DuplicateResourceException("Department with name '" + departmentDto.getName() + "' already exists.");
        }
        Department department = departmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        log.info("Successfully added new department with ID: {}", savedDepartment.getId());
        return departmentMapper.toDto(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        log.debug("Fetching all departments");
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getRolesByDepartment(Long departmentId) {
        log.debug("Fetching roles for department ID: {}", departmentId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + departmentId));
        return department.getRoles().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }
}