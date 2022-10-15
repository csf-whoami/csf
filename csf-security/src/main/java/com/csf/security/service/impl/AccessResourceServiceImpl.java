/**
 * 
 */
package com.csf.security.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.csf.security.entity.AccessResourceEntity;
import com.csf.security.repository.AccessResourceRepository;
import com.csf.security.service.AccessResourceService;

/**
 * @author mba0019
 *
 */
public class AccessResourceServiceImpl implements AccessResourceService {

	@Autowired
	AccessResourceRepository accessResource;

	@Override
	public List<String> getAccessUrlWithoutPermision() {
		List<AccessResourceEntity> urlResources = accessResource.findAll();
		return urlResources.stream().map(item -> item.getAccessUrl()).collect(Collectors.toList());
	}

}
