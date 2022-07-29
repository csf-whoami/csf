package com.csf.whoami.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csf.base.domain.RoleInfo;
import com.csf.database.adapter.RoleAdapter;
import com.csf.database.models.RoleEntity;
import com.csf.database.repository.RoleRepository;
import com.csf.whoami.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Long getRoleByName(String roleName) {
        RoleEntity role = roleRepository.findByCode(roleName);
        return role.getId();
    }

    @Override
    public List<RoleInfo> getUserRole(Long id) {
        List<RoleEntity> userRoles = roleRepository.findAllByUserId(id);
        return userRoles.stream().map(item -> RoleAdapter.modelToDto(item)).collect(Collectors.toList());
    }

    @Override
    public RoleInfo getRoleById(Long id) {
        if (id == null) {
            return null;
        }
        RoleEntity role = roleRepository.findById(id).orElse(null);
        return RoleAdapter.modelToDto(role);
    }

	@Override
	public List<RoleInfo> getSystemRoles() {
		List<RoleEntity> roles = roleRepository.findAll();
		return roles.stream().map(item -> RoleAdapter.modelToDto(item)).collect(Collectors.toList());
	}
}
