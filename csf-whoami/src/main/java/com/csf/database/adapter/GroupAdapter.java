/**
 * 
 */
package com.csf.database.adapter;

import com.csf.base.domain.response.GroupInfo;
import com.csf.database.models.TbGroup;
import com.csf.database.view.GroupView;

public class GroupAdapter {

    public static GroupInfo viewToDomain(GroupView view) {
        if (view == null) {
            return null;
        }
        GroupInfo domain = new GroupInfo();
        domain.setId(String.valueOf(view.getId()));
        domain.setGroupName(view.getGroupName());
        domain.setGroupType(view.getGroupType());
        return domain;
    }

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
