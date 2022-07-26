/**
 * 
 */
package com.csf.database.adapter;

import com.csf.base.domain.response.GroupInfo;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.GroupEntity;
import com.csf.database.view.GroupView;

public class GroupAdapter {

    public static GroupInfo viewToDomain(GroupView view) {
        if (view == null) {
            return null;
        }
        GroupInfo domain = new GroupInfo();
        domain.setId(String.valueOf(view.getId()));
        domain.setName(view.getName());
        domain.setTypeCode(view.getTypeCode());
        return domain;
    }

    public static GroupInfo modelToDomain(GroupEntity entity) {
        if (entity == null) {
            return null;
        }
        GroupInfo domain = new GroupInfo();
        domain.setId(String.valueOf(entity.getId()));
//        domain.setGroupName(entity.getName());
        domain.setTypeCode(entity.getTypeCode());
        return domain;
    }

    public static GroupEntity domainToModel(GroupInfo domain) {
        if (domain == null) {
            return null;
        }
        GroupEntity entity = initialEntity();
//        entity.setName(domain.getGroupName());
        entity.setTypeCode(domain.getTypeCode());
        return entity;
    }

    private static GroupEntity initialEntity() {
        GroupEntity entity = new GroupEntity();
        return entity;
    }
}
