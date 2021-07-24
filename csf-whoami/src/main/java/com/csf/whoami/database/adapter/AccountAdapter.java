package com.csf.whoami.database.adapter;

import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.dto.response.AccountInfo;
import com.csf.whoami.database.dto.response.UserInfo;
import com.csf.whoami.database.models.TbAccount;

public class AccountAdapter {
    private AccountAdapter() {};

    public static AccountInfo entityToDomain(TbAccount entity) {
        if (entity == null) {
            return null;
        }
        AccountInfo domain = new AccountInfo();
        domain.setUserId(entity.getId());
        domain.setUsername(entity.getUsername());
        return domain;
    }

    public static TbAccount domainToEntity(AccountInfo domain) {
        if (domain == null) {
            return null;
        }
        TbAccount entity = new TbAccount();
        entity.setId(domain.getUserId());
        entity.setUsername(domain.getUsername());
        return entity;
    }

    public static TbAccount userInfoToEntity(UserInfo info) {
        if (info == null) {
            return null;
        }
        TbAccount account = new TbAccount();
        account.setId(StringUtils.toLongOrNull(info.getId()));
        account.setUsername(info.getUsername());
        account.setStartedAt(DateTimeUtils.toDateOrNull(info.getStartedAt(), DateTimeUtils.YYYYMMDD));
        account.setFinishedAt(DateTimeUtils.toDateOrNull(info.getFinishedAt(), DateTimeUtils.YYYYMMDD));
        account.setActivedAt(DateTimeUtils.toDateOrNull(info.getActivedAt(), DateTimeUtils.YYYYMMDD));

        return account;
    }
}
