/**
 * 
 */
package com.csf.whoami.service;

import java.util.List;

import com.csf.base.domain.ChannelQuizDomain;
import com.csf.whoami.database.models.TbUser;

public interface NotificationService {
    List<ChannelQuizDomain> getQuizByChannel(String channelId, TbUser userdto) throws Exception;
}
