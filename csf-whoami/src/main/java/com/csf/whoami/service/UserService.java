package com.csf.whoami.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.csf.whoami.database.models.TbAccount;
import com.csf.base.domain.AccountDTO;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.AccountInfo;
import com.csf.base.domain.response.AuthenticationInfo;
import com.csf.base.domain.response.UserInfo;

public interface UserService {

    AccountInfo signUp(AccountDTO user);

    List<TbAccount> getAllUser();

    boolean delete(Long id);

    TbAccount getAccountById(Long id);

    AccountInfo getUserByUsername(String username);

    boolean checkExist(String username, String password);

    AuthenticationInfo getAuthenticationInfo(String username);

    Page<UserInfo> fetchUsers(SearchVO search, Pageable pageable);

    UserInfo getUserInfo(Long userId);

    Long registerOrUpdate(UserInfo userinfo);

    List<Long> deleteUsers(List<String> ids);
}
