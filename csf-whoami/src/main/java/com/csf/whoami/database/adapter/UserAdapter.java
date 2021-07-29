package com.csf.whoami.database.adapter;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.csf.base.domain.RoleInfo;
import com.csf.base.domain.response.UserInfo;
import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.models.TbAccount;
import com.csf.whoami.database.models.TbUser;

public class UserAdapter {

    private UserAdapter() { }

    public static UserInfo modelToDto(TbUser model) {
        if (model == null) {
            return null;
        }
        UserInfo info = new UserInfo();
        info.setUserId(StringUtils.fromLong(model.getId()));
        info.setFullName(model.getFullName());
        info.setPhone(model.getPhone());
        info.setAddress(model.getAddress());
        info.setEmail(model.getEmail());
        info.setDescription(model.getNote());

        return info;
    }
    public static TbUser userInfoToModel(UserInfo info) {
        if (info == null) {
            return null;
        }
        TbUser user = new TbUser();
        user.setId(StringUtils.toLongOrNull(info.getUserId()));
        user.setFullName(info.getFullName());
        user.setPhone(info.getPhone());
        user.setAddress(info.getAddress());
        user.setEmail(info.getEmail());
        user.setNote(info.getDescription());

        return user;
    }

    public static UserInfo toDto(TbAccount account, TbUser user, List<RoleInfo> roleInfo) {
        if (account == null && user == null && (roleInfo == null || CollectionUtils.isEmpty(roleInfo))) {
            return null;
        }
        UserInfo info = new UserInfo();
        if (account != null) {
            info.setId(StringUtils.fromLong(account.getId()));
            info.setUsername(account.getUsername());
            info.setStartedAt(DateTimeUtils.toString(account.getStartedAt(), DateTimeUtils.YYYYMMDD));
            info.setFinishedAt(DateTimeUtils.toString(account.getFinishedAt(), DateTimeUtils.YYYYMMDD));
            info.setActivedAt(DateTimeUtils.toString(account.getActivedAt(), DateTimeUtils.YYYYMMDD));
            info.setUsable(account.getActivedAt() == null ? "N" : "Y");
            info.setUserId(StringUtils.fromLong(account.getUserId()));
        }

        if (user != null) {
            info.setUserId(StringUtils.fromLong(user.getId()));
            info.setFullName(user.getFullName());
            info.setPhone(user.getPhone());
            info.setAddress(user.getAddress());
            info.setEmail(user.getEmail());
            info.setDescription(user.getNote());
        }

        if (roleInfo != null && !CollectionUtils.isEmpty(roleInfo)) {
            info.setRoles(roleInfo);
        }
        return info;
    }
}
