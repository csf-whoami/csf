package com.csf.database.adapter;

import com.csf.base.domain.enumtype.YesNoEnum;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.ChannelEntity;

public class ChannelAdapter {

    private ChannelAdapter() {}

    public static ChannelInfo modelToDomain(ChannelEntity entity) {
        if (entity == null) {
            return null;
        }
        ChannelInfo domain = new ChannelInfo();
        domain.setId(String.valueOf(entity.getId()));
        domain.setName(entity.getName());
        domain.setUrl(entity.getUrl());
        domain.setDescription(entity.getDescription());
        domain.setLockStatus(entity.getIsLock().toString());
        domain.setCreateDate(DateTimeUtils.convertDateToString(entity.getCreatedAt(), DateTimeUtils.YYYYMMDD));
        return domain;
    }
    
    public static ChannelEntity domainToModel(ChannelInfo domain) {
        if (domain == null) {
            return null;
        }
        ChannelEntity entity = initialEntity();
        entity.setId(StringUtils.toLongOrNull(domain.getId()));
        entity.setName(domain.getName());
        entity.setUrl(domain.getUrl());
        entity.setDescription(domain.getDescription());
        entity.setIsLock("Y".equalsIgnoreCase(domain.getLockStatus()) ? YesNoEnum.Y : YesNoEnum.N);
        entity.setIsClosed("Y".equalsIgnoreCase(domain.getClosedStatus()) ? YesNoEnum.Y : YesNoEnum.N);
        entity.setIsPrivate("Y".equalsIgnoreCase(domain.getPrivateStatus()) ? YesNoEnum.Y : YesNoEnum.N);
        entity.setGroupId(StringUtils.toLongOrNull(domain.getGroupId()));
        return entity;
    }

    private static ChannelEntity initialEntity() {
        ChannelEntity entity = new ChannelEntity();
        return entity;
    }
}
