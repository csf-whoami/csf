/**
 * Function list:
 * - Invite join to Group
 * - Invite join to Group(friend)
 * - Invite try to quiz test.
 * - View user's invite.
 * - View invite detail.
 * - Accept / Reject invite.
 */
package com.csf.whoami.business;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.database.dto.InviteGroupDetailDomain;
import com.csf.whoami.database.dto.InviteGroupDomain;
import com.csf.whoami.service.InviteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/invite")
@Api
public class InviteController {

	@Autowired
	private InviteService inviteService;

	/**
	 * Invite user to Group.
	 * 
	 * @author mba0051
	 * @param domain
	 * @return
	 */
	@ApiOperation(value = "Phương thức mời bạn bè vào trong Group.")
	@PostMapping("/groups")
	public String inviteToGroup(@RequestBody InviteGroupDomain domain) {
		String inviteId = inviteService.inviteToGroup(domain);
		return inviteId;
	}

	/**
	 * View invite group detail.
	 * 
	 * @author mba0051
	 * @param inviteId
	 * @return
	 */
	@ApiOperation(value = "Phương thức xem chi tiết lời mời vào Group.")
	@GetMapping("/groups/{inviteId}")
	public InviteGroupDetailDomain getInviteToGroupDetail(@PathParam("inviteId") String inviteId) {
		InviteGroupDetailDomain detailDomain = inviteService.getInviteGroupDetail(inviteId);
		return detailDomain;
	}

	/**
	 * Invite user to channel.
	 * 
	 * @author mba0051
	 * @param groupId
	 * @param domain
	 * @return
	 */
	@ApiOperation(value = "Phương thức mời bạn bè vào trong kênh.")
	@PostMapping("/groups/{groupId}/channels")
	public String inviteToChannel(@PathParam("groupId") String groupId,
			@RequestBody InviteGroupDomain domain) {
		String inviteId = inviteService.inviteToChannel(groupId, domain);
		return inviteId;
	}

	@ApiOperation(value = "Phương thức xem chi tiết lời mời vào trong kênh.")
	@PostMapping("/channels/{inviteId}")
	public InviteGroupDetailDomain getInviteToChannelDetail(@PathParam("inviteId") String inviteId) {
		InviteGroupDetailDomain detailDomain = inviteService.getInviteGroupDetail(inviteId);
		return detailDomain;
	}
}
