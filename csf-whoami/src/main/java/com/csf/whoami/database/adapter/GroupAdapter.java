/**
 * 
 */
package com.csf.whoami.database.adapter;

import com.csf.base.domain.response.GroupInfo;
import com.csf.whoami.database.models.TbGroup;

public class GroupAdapter {

    public static GroupInfo modelToDomain(TbGroup entity) {
        if (entity == null) {
            return null;
        }
        GroupInfo domain = new GroupInfo();
        domain.setId(String.valueOf(entity.getId()));
        domain.setGroupName(entity.getGroupName());
        domain.setGroupType(entity.getGroupType());
        return domain;
    }
    
    public static TbGroup domainToModel(GroupInfo domain) {
        if (domain == null) {
            return null;
        }
        TbGroup entity = initialEntity();
        entity.setGroupName(domain.getGroupName());
        entity.setGroupType(domain.getGroupType());
        return entity;
    }

    private static TbGroup initialEntity() {
        TbGroup entity = new TbGroup();
        return entity;
    }
}
