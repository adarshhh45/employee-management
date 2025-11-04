package com.aurionpro.app.mapper;

import com.aurionpro.app.dto.DepartmentDto;
import com.aurionpro.app.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);
}