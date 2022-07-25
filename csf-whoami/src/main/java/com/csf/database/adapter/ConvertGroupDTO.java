package com.csf.database.adapter;

import com.csf.base.domain.request.ConfirmGroupInfo;
import com.csf.base.domain.response.GroupInfo;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.GroupEntity;

public class ConvertGroupDTO {

    public static GroupInfo dbToDomain(GroupEntity entity) {
        if (entity == null) {
            return null;
        }

        GroupInfo domain = new GroupInfo();
        domain.setId(String.valueOf(entity.getId()));
//        domain.setGroupName(entity.getName());
        domain.setGroupUrl(entity.getUrl());
        return domain;
    }

    public static GroupEntity domainToDb(GroupInfo domain) {
        if (domain == null) {
            return null;
        }
        GroupEntity entity = new GroupEntity();
        entity.setId(StringUtils.toLongOrNull(domain.getId()));

//        entity.setName(domain.getGroupName());
        entity.setUrl(domain.getGroupUrl());
        entity.setTypeId(StringUtils.toLongOrNull(domain.getTypeId()));
        entity.setIsPrivate(StringUtils.isNullOrEmpty(domain.getIsPrivate()) ? "Y" : domain.getIsPrivate());
        entity.setIsPublish(StringUtils.isNullOrEmpty(domain.getIsPublish()) ? "Y" : domain.getIsPublish());
        entity.setIsLock(StringUtils.isNullOrEmpty(domain.getIsLock()) ? "N" : domain.getIsLock());
        entity.setIsClosed(StringUtils.isNullOrEmpty(domain.getIsClosed()) ? "N" : domain.getIsClosed());
        entity.setDescription(domain.getDescription());
        return entity;
    }

    /**
     * User register temp group.
     * 
     * @param domain
     * @return
     */
    public static GroupEntity tempDomainToDb(ConfirmGroupInfo domain) {
        if (domain == null) {
            return null;
        }
        GroupEntity entity = new GroupEntity();
        entity.setUrl(domain.getGroupURL());
        return entity;
    }
}
