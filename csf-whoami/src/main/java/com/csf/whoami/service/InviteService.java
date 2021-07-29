/**
 * 
 */
package com.csf.whoami.service;

import com.csf.base.domain.InviteGroupDetailDomain;
import com.csf.base.domain.InviteGroupDomain;

/**
 * @author mba0051
 *
 */
public interface InviteService {

	String inviteToGroup(InviteGroupDomain domain);
	String inviteToChannel(String groupId, InviteGroupDomain domain);
	InviteGroupDetailDomain getInviteGroupDetail(String inviteId);
}
