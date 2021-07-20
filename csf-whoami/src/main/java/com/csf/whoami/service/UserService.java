package com.csf.whoami.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.csf.whoami.database.dto.AccountDTO;
import com.csf.whoami.database.dto.SearchVO;
import com.csf.whoami.database.dto.response.AuthenticationInfo;
import com.csf.whoami.database.dto.response.UserInfo;
import com.csf.whoami.database.dto.response.AccountInfo;
import com.csf.whoami.database.model.TbAccount;

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
