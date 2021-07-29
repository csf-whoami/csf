package com.csf.whoami.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.csf.base.domain.response.ChannelInfo;

public interface ChannelService {
	Page<ChannelInfo> channelList(Long groupId, Pageable pageable);

	ChannelInfo getChannelDetail(Long id);

	ChannelInfo registerOrUpdate(ChannelInfo channelInfo);
}
