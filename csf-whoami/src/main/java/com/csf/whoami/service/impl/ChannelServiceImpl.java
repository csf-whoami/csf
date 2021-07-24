package com.csf.whoami.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.exception.CustomException;
import com.csf.base.exception.ErrorException;
import com.csf.base.exception.HttpStatus;
import com.csf.whoami.database.adapter.ChannelAdapter;
import com.csf.whoami.database.dto.response.ChannelInfo;
import com.csf.whoami.database.models.TbChannel;
import com.csf.whoami.database.repository.ChannelRepository;
import com.csf.whoami.service.ChannelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    public Page<ChannelInfo> channelList(Long groupId, Pageable pageable) {
        if (groupId == null) {
            return Page.empty();
        }
        return channelRepository.findAllByGroupId(groupId, pageable);
    }

    @Override
    public ChannelInfo getChannelDetail(Long id) {
        TbChannel channel = channelRepository.findById(id).orElse(null);
        return ChannelAdapter.modelToDomain(channel);
    }

    @Transactional
    @Override
    public ChannelInfo registerOrUpdate(ChannelInfo domain) {
        TbChannel entity = ChannelAdapter.domainToModel(domain);
        entity = channelRepository.save(entity);
        if (entity == null) {
            throw new CustomException(ErrorException.CANT_CREATE_GROUP.getMessage(),
            		ErrorException.CANT_CREATE_GROUP.getCode(),
            		HttpStatus.BAD_REQUEST);
        }
        return ChannelAdapter.modelToDomain(entity);
    }
}
