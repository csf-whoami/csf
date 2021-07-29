package com.csf.whoami.database.adapter;

import com.csf.base.domain.YesNo;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.models.TbChannel;

public class ChannelAdapter {

    private ChannelAdapter() {}

    public static ChannelInfo modelToDomain(TbChannel entity) {
        if (entity == null) {
            return null;
        }
        ChannelInfo domain = new ChannelInfo();
        domain.setId(String.valueOf(entity.getId()));
        domain.setChannelName(entity.getChannelName());
        domain.setChannelUrl(entity.getChannelUrl());
        domain.setChannelDescription(entity.getChannelDescription());
        domain.setLockStatus(entity.getIsLock().toString());
        domain.setCreateDate(DateTimeUtils.toString(entity.getCreatedAt(), DateTimeUtils.YYYYMMDD));
        return domain;
    }
    
    public static TbChannel domainToModel(ChannelInfo domain) {
        if (domain == null) {
            return null;
        }
        TbChannel entity = initialEntity();
        entity.setId(StringUtils.toLongOrNull(domain.getId()));
        entity.setChannelName(domain.getChannelName());
        entity.setChannelUrl(domain.getChannelUrl());
        entity.setChannelDescription(domain.getChannelDescription());
        entity.setIsLock("Y".equalsIgnoreCase(domain.getLockStatus()) ? YesNo.Y : YesNo.N);
        entity.setIsClosed("Y".equalsIgnoreCase(domain.getClosedStatus()) ? YesNo.Y : YesNo.N);
        entity.setIsPrivate("Y".equalsIgnoreCase(domain.getPrivateStatus()) ? YesNo.Y : YesNo.N);
        entity.setGroupId(StringUtils.toLongOrNull(domain.getGroupId()));
        return entity;
    }

    private static TbChannel initialEntity() {
        TbChannel entity = new TbChannel();
        return entity;
    }
}
