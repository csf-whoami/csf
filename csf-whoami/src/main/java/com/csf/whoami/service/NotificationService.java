/**
 * 
 */
package com.csf.whoami.service;

import java.util.List;

import com.csf.base.domain.ChannelQuizDomain;
import com.csf.database.models.UserEntity;

public interface NotificationService {
    List<ChannelQuizDomain> getQuizByChannel(String channelId, UserEntity userdto) throws Exception;
}
