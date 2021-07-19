/**
 * 
 */
package com.csf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.security.entity.AccessResourceEntity;

/**
 * @author mba0019
 *
 */
public interface AccessResourceRepository extends JpaRepository<AccessResourceEntity, String>{

}
