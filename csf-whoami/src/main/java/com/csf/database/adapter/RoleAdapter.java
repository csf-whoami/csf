package com.csf.database.adapter;

import com.csf.base.domain.RoleInfo;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.RoleEntity;

public class RoleAdapter {

    private RoleAdapter() { }

    /**
     * Convert DTO to Role model.
     * @param dto
     * @return
     */
    public static RoleEntity dtoToModel(RoleInfo dto) {
        if (dto == null) {
            return null;
        }
        RoleEntity model = new RoleEntity();
        model.setId(StringUtils.toLongOrNull(dto.getRoleId()));
        model.setName(dto.getRoleName());
        return model;
    }

    /**
     * Convert Role model to DTO.
     * 
     * @param model
     * @return
     */
    public static RoleInfo modelToDto(RoleEntity model) {
        if (model == null) {
            return null;
        }
        RoleInfo dto = new RoleInfo();
        dto.setRoleId(StringUtils.fromLong(model.getId()));
        dto.setRoleName(model.getName());
        return dto;
    }
}
