package com.csf.database.adapter;

import com.csf.base.domain.response.AccountInfo;
import com.csf.base.domain.response.UserInfo;
import com.csf.base.utilities.DateTimeUtils;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.AccountEntity;

public class AccountAdapter {
    private AccountAdapter() {};

    public static AccountInfo entityToDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }
        AccountInfo domain = new AccountInfo();
        domain.setUserId(entity.getId());
        domain.setUsername(entity.getUsername());
        return domain;
    }

    public static AccountEntity domainToEntity(AccountInfo domain) {
        if (domain == null) {
            return null;
        }
        AccountEntity entity = new AccountEntity();
        entity.setId(domain.getUserId());
        entity.setUsername(domain.getUsername());
        return entity;
    }

    public static AccountEntity userInfoToEntity(UserInfo info) {
        if (info == null) {
            return null;
        }
        AccountEntity account = new AccountEntity();
        account.setId(StringUtils.toLongOrNull(info.getId()));
        account.setUsername(info.getUsername());
        account.setStartedAt(DateTimeUtils.convertStringToDateOrNull(info.getStartedAt(), DateTimeUtils.YYYYMMDD));
        account.setFinishedAt(DateTimeUtils.convertStringToDateOrNull(info.getFinishedAt(), DateTimeUtils.YYYYMMDD));
        account.setActivedAt(DateTimeUtils.convertStringToDateOrNull(info.getActivedAt(), DateTimeUtils.YYYYMMDD));

        return account;
    }
}
