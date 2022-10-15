/**
 * 
 */
package com.csf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.security.entity.RoleEntity;

/**
 * @author tuan
 *
 */
public interface RolesRepository extends JpaRepository<RoleEntity, String> {

	RoleEntity findByName(String roleName);
}
