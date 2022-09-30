/**
 * 
 */
package com.csf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.security.entity.UserRoleEntity;

/**
 * @author tuan
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

}