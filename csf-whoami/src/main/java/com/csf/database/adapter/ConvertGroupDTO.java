package com.csf.database.adapter;

import com.csf.base.domain.RequestSearchGroup;
import com.csf.base.domain.response.GroupInfo;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.TbGroup;

public class ConvertGroupDTO {

    public static GroupInfo dbToDomain(TbGroup entity) {
        if (entity == null) {
            return null;
        }

        GroupInfo domain = new GroupInfo();
        domain.setId(String.valueOf(entity.getId()));
        domain.setGroupName(entity.getGroupName());
        domain.setGroupUrl(entity.getGroupUrl());
        return domain;
    }

    public static TbGroup domainToDb(GroupInfo domain) {
        if (domain == null) {
            return null;
        }
        TbGroup entity = new TbGroup();
        entity.setId(StringUtils.toLongOrNull(domain.getId()));

        entity.setGroupName(domain.getGroupName());
        entity.setGroupUrl(domain.getGroupUrl());
        entity.setGroupType(domain.getGroupType());
        entity.setIsPrivate(StringUtils.isNullOrEmpty(domain.getIsPrivate()) ? "Y" : domain.getIsPrivate());
        entity.setIsPublish(StringUtils.isNullOrEmpty(domain.getIsPublish()) ? "Y" : domain.getIsPublish());
        entity.setIsLock(StringUtils.isNullOrEmpty(domain.getIsLock()) ? "N" : domain.getIsLock());
        entity.setIsClosed(StringUtils.isNullOrEmpty(domain.getIsClosed()) ? "N" : domain.getIsClosed());
        entity.setGroupDescription(domain.getGroupDescription());
        return entity;
    }

    /**
     * User register temp group.
     * 
     * @param domain
     * @return
     */
    public static TbGroup tempDomainToDb(RequestSearchGroup domain) {
        if (domain == null) {
            return null;
        }
        TbGroup entity = new TbGroup();
        entity.setGroupUrl(domain.getGroupURL());
        return entity;
    }
}
