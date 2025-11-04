package com.aurionpro.app.service;

import com.aurionpro.app.dto.RoleDto;
import com.aurionpro.app.dto.RoleRequestDto;

import java.util.List;

public interface RoleService {
	RoleDto addRole(RoleRequestDto roleRequestDto);
    List<RoleDto> getAllRoles();
}