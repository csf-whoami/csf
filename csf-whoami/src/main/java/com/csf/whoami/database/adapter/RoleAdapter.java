package com.csf.whoami.database.adapter;

import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.dto.RoleInfo;
import com.csf.whoami.database.model.TbRole;

public class RoleAdapter {

    private RoleAdapter() { }

    /**
     * Convert DTO to Role model.
     * @param dto
     * @return
     */
    public static TbRole dtoToModel(RoleInfo dto) {
        if (dto == null) {
            return null;
        }
        TbRole model = new TbRole();
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
    public static RoleInfo modelToDto(TbRole model) {
        if (model == null) {
            return null;
        }
        RoleInfo dto = new RoleInfo();
        dto.setRoleId(StringUtils.fromLong(model.getId()));
        dto.setRoleName(model.getName());
        return dto;
    }
}
