/**
 * 
 */
package com.csf.whoami.service;

import com.csf.whoami.database.dto.InviteGroupDetailDomain;
import com.csf.whoami.database.dto.InviteGroupDomain;

/**
 * @author mba0051
 *
 */
public interface InviteService {

	String inviteToGroup(InviteGroupDomain domain);
	String inviteToChannel(String groupId, InviteGroupDomain domain);
	InviteGroupDetailDomain getInviteGroupDetail(String inviteId);
}
