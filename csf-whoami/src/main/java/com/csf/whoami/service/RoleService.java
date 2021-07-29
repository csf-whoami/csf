package com.csf.whoami.service;

import java.util.List;

import com.csf.base.domain.RoleInfo;

public interface RoleService {

	/**
	 * Select Role ID by name.
	 * 
	 * @param roleName
	 * @return
	 */
    Long getRoleByName(String roleName);

    /**
     * List all user roles.
     * 
     * @param id
     * @return
     */
    List<RoleInfo> getUserRole(Long id);

    RoleInfo getRoleById(Long id);

    List<RoleInfo> getSystemRoles();
}
