package com.aurionpro.app.mapper;

import com.aurionpro.app.dto.RoleDto;
import com.aurionpro.app.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    RoleDto toDto(Role role);

    @Mapping(source = "departmentId", target = "department.id")
    Role toEntity(RoleDto roleDto);
}