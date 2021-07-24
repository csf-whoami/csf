/**
 * 
 */
package com.csf.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.whoami.database.dto.ChannelQuizDomain;
import com.csf.whoami.database.models.TbUser;
import com.csf.whoami.service.GroupService;
import com.csf.whoami.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private GroupService groupService;

	@Override
	public List<ChannelQuizDomain> getQuizByChannel(String channelId, TbUser userdto) throws Exception {
//		if (!groupService.checkUserInChannel(channelId, userdto.getUserId())) {
//			throw new Exception("User not in channel.");
//		}
		return null;
	}

}
