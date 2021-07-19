/**
 * 
 */
package com.csf.security.service;

import com.csf.base.domain.ResponseDataAPI;
import com.csf.security.domain.LoginRequestDomain;

/**
 * @author tuan
 *
 */
public interface CommonService {

	ResponseDataAPI checkLogin(LoginRequestDomain domain) throws Exception;
}
