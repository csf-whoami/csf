/**
 * 
 */
package com.csf.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author mba0019
 *
 */

@Service
public interface AccessResourceService {

	List<String> getAccessUrlWithoutPermision();

}
