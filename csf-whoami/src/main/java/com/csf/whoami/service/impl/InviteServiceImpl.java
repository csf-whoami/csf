package com.csf.whoami.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.constant.ConstantsDatabase;
import com.csf.base.constant.ConstantsDateFormat;
import com.csf.base.domain.InviteGroupDetailDomain;
import com.csf.base.domain.InviteGroupDomain;
import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.base.utilities.DateTimeUtils;
import com.csf.whoami.database.models.TbAccount;
import com.csf.whoami.database.models.TbGroup;
import com.csf.whoami.database.models.TbInvites;
import com.csf.whoami.database.models.TbNotifications;
import com.csf.whoami.database.repository.AccountRepository;
import com.csf.whoami.database.repository.GroupRepository;
import com.csf.whoami.database.repository.InvitesRepository;
import com.csf.whoami.database.repository.NotificationsRepository;
import com.csf.whoami.service.InviteService;

@Service
public class InviteServiceImpl implements InviteService {

	@Autowired
	private AccountRepository oauth2UserRepository;
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private NotificationsRepository notificationsRepository;
	@Autowired
	private InvitesRepository invitesRepository;

	/**
	 * Invite user to Group.
	 */
	@Transactional
	@Override
	public String inviteToGroup(InviteGroupDomain domain) {

		validateInviteGroup(domain);

		String inviteId = inviteUserToGroup(domain.getUserId(),
				domain.getGroupId(),
				domain.getJoinType(),
				"");
//				StringUtils.convertDateToString(new Date()));

		notificationsForUser(inviteId);
		return inviteId;
	}

	/**
	 * Notifications invite to all user.
	 * 
	 * @author mba0051
	 * @param inviteId
	 * @return
	 */
	private void notificationsForUser(String inviteId) {

		StringBuilder inviteLink = new StringBuilder();
		inviteLink.append("invite/groups/");
		inviteLink.append(inviteId);

		TbNotifications notify = new TbNotifications();
		notify.setNotifyLink(inviteLink.toString());
		notify.setNotifyStatus(ConstantsDatabase.NotifyStatusConstant.NEW.getValue());
		notify.setNotifyType(ConstantsDatabase.NotificationsTypeConstant.GROUP.getValue());
//		notify = notificationsRepository.save(notify);
		if (notify == null) {
			throw new CustomException(ErrorException.CANT_NOTIFICATION.getMessage(),
					ErrorException.CANT_NOTIFICATION.getCode(),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Invite user to group.
	 * 
	 * @author mba0051
	 * @param beInvitedId
	 * @param joinType
	 * @param expireDate
	 * @return
	 */
	private String inviteUserToGroup(String beInvitedId, String detailId, String joinType, String expireDate) {

		TbInvites invite = new TbInvites();
//		invite.setOwnerId(AuthenticationUtils.getCurrentUserId());
		invite.setInvitedId(beInvitedId);
		invite.setDetailId(detailId);
		invite.setExpireDate(DateTimeUtils.toDateOrNull(expireDate, ConstantsDateFormat.yyyyMMdd));
		invite.setMessage("Welcome to Group");
		invite.setCondition(joinType); // Add direct to group.
//		invite = invitesRepository.save(invite);
		if (invite == null) {
			throw new CustomException(ErrorException.CANT_INVITE.getMessage(),
					ErrorException.CANT_INVITE.getCode(),
					HttpStatus.BAD_REQUEST);
		}
		return String.valueOf(invite.getId());
	}

	/**
	 * Validate data and format.
	 * 
	 * @param domain
	 */
	private void validateInviteGroup(InviteGroupDomain domain) {

		commonValidate(domain);
		TbGroup group = groupRepository.findById(Long.parseLong(domain.getGroupId())).orElse(null);
		if (group == null) {
			throw new CustomException(ErrorException.INVALID_GROUP.getMessage(),
					ErrorException.INVALID_GROUP.getCode(),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Common validate domain.
	 * 
	 * @author mba0051
	 * @param domain
	 */
	private void commonValidate(InviteGroupDomain domain) {
//		String currentUserId = AuthenticationUtils.getCurrentUserId();
//		if (!StringUtils.isValidString(currentUserId)) {
//			throw new CustomException(ErrorException.INVALID_USER.getMessage(), ErrorException.INVALID_USER.getCode(),
//					HttpStatus.BAD_REQUEST);
//		}

		// Validate format.
		if (!ConstantsDatabase.GroupRequireTypeConstant.Direct.getValue().equalsIgnoreCase(domain.getJoinType())
				|| !ConstantsDatabase.GroupRequireTypeConstant.RequireAuthen.getValue().equalsIgnoreCase(domain.getJoinType())
				|| !ConstantsDatabase.GroupRequireTypeConstant.RequireExp.getValue().equalsIgnoreCase(domain.getJoinType())
				|| !ConstantsDatabase.GroupRequireTypeConstant.WaitAccept.getValue().equalsIgnoreCase(domain.getJoinType())) {
			throw new CustomException(ErrorException.INVALID_FORMAT.getMessage(),
					ErrorException.INVALID_FORMAT.getCode(),
					HttpStatus.BAD_REQUEST);
		}

		// Check exist user
		TbAccount user = oauth2UserRepository.findById(Long.parseLong(domain.getUserId())).orElse(null);
		if (user == null) {
			throw new CustomException(ErrorException.INVALID_USER.getMessage(),
					ErrorException.INVALID_USER.getCode(),
					HttpStatus.BAD_REQUEST);
		}
	}


	/**
	 * Get invite detail.
	 * @author mba0051
	 */
	@Override
	public InviteGroupDetailDomain getInviteGroupDetail(String inviteId) {

		InviteGroupDetailDomain inviteDomain = new InviteGroupDetailDomain();
		TbInvites invite = invitesRepository.findById(inviteId).orElse(null);
		if (invite == null) {
			throw new CustomException(ErrorException.NOT_EXIST_INVITE.getMessage(),
					ErrorException.NOT_EXIST_INVITE.getCode(),
					HttpStatus.BAD_REQUEST);
		}
		if (DateTimeUtils.compareDateTime(invite.getExpireDate(), new Date()) == -1) {
			throw new CustomException(ErrorException.INVITE_EXPIRED.getMessage(),
					ErrorException.INVITE_EXPIRED.getCode(),
					HttpStatus.BAD_REQUEST);
		}

		TbGroup group = groupRepository.findById(Long.parseLong(invite.getDetailId())).orElse(null);
		if (group == null) {
			throw new CustomException(ErrorException.INVALID_GROUP.getMessage(),
					ErrorException.INVALID_GROUP.getCode(),
					HttpStatus.BAD_REQUEST);
		}

		inviteDomain.setInviteId(String.valueOf(invite.getId()));
		inviteDomain.setGroupId(String.valueOf(group.getId()));
		inviteDomain.setGroupName(group.getGroupName());
//		inviteDomain.setExprireDate(StringUtils.convertDateToString(invite.getExpireDate()));

		return inviteDomain;
	}

	@Override
	public String inviteToChannel(String groupId, InviteGroupDomain domain) {
		validateInviteChannel(groupId, domain);

		String inviteId = inviteUserToGroup(domain.getUserId(),
				domain.getGroupId(),
				domain.getJoinType(),
				"");
//				StringUtils.convertDateToString(new Date()));

		notificationsForUser(inviteId);
		return inviteId;
		
	}

	private void validateInviteChannel(String groupId, InviteGroupDomain domain) {

//		TbGroup groupChannel = groupRepository.findByIdAndParentGroup(domain.getGroupId(), groupId);
//		if (groupChannel == null) {
//			throw new CustomException(ErrorException.INVALID_GROUP.getMessage(), ErrorException.INVALID_GROUP.getCode(),
//					HttpStatus.BAD_REQUEST);
//		}
	}
}
