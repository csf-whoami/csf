package com.csf.whoami.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csf.whoami.database.adapter.RoleAdapter;
import com.csf.whoami.database.dto.RoleInfo;
import com.csf.whoami.database.model.TbRole;
import com.csf.whoami.database.repository.RoleRepository;
import com.csf.whoami.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Long getRoleByName(String roleName) {
        TbRole role = roleRepository.findByCode(roleName);
        return role.getId();
    }

    @Override
    public List<RoleInfo> getUserRole(Long id) {
        List<TbRole> userRoles = roleRepository.findAllByUserId(id);
        return userRoles.stream().map(item -> RoleAdapter.modelToDto(item)).collect(Collectors.toList());
    }

    @Override
    public RoleInfo getRoleById(Long id) {
        if (id == null) {
            return null;
        }
        TbRole role = roleRepository.findById(id).orElse(null);
        return RoleAdapter.modelToDto(role);
    }

	@Override
	public List<RoleInfo> getSystemRoles() {
		List<TbRole> roles = roleRepository.findAll();
		return roles.stream().map(item -> RoleAdapter.modelToDto(item)).collect(Collectors.toList());
	}
}
