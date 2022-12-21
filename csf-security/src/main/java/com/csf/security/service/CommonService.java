/**
 * 
 */
package com.csf.security.service;

import com.csf.common.domain.LoginRequestDomain;
import com.csf.common.domain.ResponseDataAPI;

/**
 * @author tuan
 *
 */
public interface CommonService {

	ResponseDataAPI checkLogin(LoginRequestDomain domain) throws Exception;

}
