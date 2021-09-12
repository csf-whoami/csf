package com.csf.whoami.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.domain.AccountDTO;
import com.csf.base.domain.RoleInfo;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.AccountInfo;
import com.csf.base.domain.response.AuthenticationInfo;
import com.csf.base.domain.response.UserInfo;
import com.csf.whoami.base.constant.ConstantsSystem;
import com.csf.whoami.base.exception.CustomException;
import com.csf.whoami.base.exception.ErrorCode;
import com.csf.whoami.base.util.StringUtils;
import com.csf.whoami.database.adapter.AccountAdapter;
import com.csf.whoami.database.adapter.UserAdapter;
import com.csf.whoami.database.models.TbAccount;
import com.csf.whoami.database.models.TbUser;
import com.csf.whoami.database.models.TbUserRole;
import com.csf.whoami.database.repository.AccountRepository;
import com.csf.whoami.database.repository.UserRepository;
import com.csf.whoami.database.repository.UserRoleRepository;
import com.csf.whoami.service.RoleService;
import com.csf.whoami.service.UserService;

import lombok.RequiredArgsConstructor;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    /**
     * @param user
     * @return User
     * @description add User
     */
    @Override
    @Transactional
    public AccountInfo signUp(AccountDTO user) {
        // Check exist account.
        TbAccount existUser = accountRepository.findByUsername(user.getUsername());
        if (existUser != null) {
            throw new CustomException(ErrorCode.EXIST_LOGIN_ID.getMessage(), HttpStatus.BAD_REQUEST);
        }

        // Check exist role.
        Long role = roleService.getRoleByName(ConstantsSystem.DEFAULT_ROLE);
        if (role == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }

        // Save User
        TbUser us = new TbUser();
        us = userRepository.save(us);

        TbUserRole userRole = new TbUserRole();
        userRole.setRoleId(role);
        userRole.setUserId(us.getId());
        userRoleRepository.save(userRole);

        // Save account.
        TbAccount objSave  = new TbAccount();
        objSave.setUsername(user.getUsername());
        objSave.setPassword(bcryptEncoder.encode(user.getPassword()));
        objSave.setUserId(us.getId());
        objSave = accountRepository.save(objSave);

        // Set role.
        return AccountAdapter.entityToDomain(objSave);
    }

    /**
     * @return List<User>
     * @description get all user
     */
    @Override
    public List<TbAccount> getAllUser() {
        return accountRepository.findAll();
    }

    /**
     * @param id
     * @return boolean
     * @description delete User
     */
    @Override
    public boolean delete(Long id) {
        Optional<TbAccount> optinal = accountRepository.findById(id);
        if(!optinal.isPresent()) {
            return false;
        }
        accountRepository.deleteById(id);
        return true;
    }

    /**
     * @param id
     * @return User
     * @description get User by Id
     */
    @Override
    public TbAccount getAccountById(Long id) {
        Optional<TbAccount> optional = accountRepository.findById(id);
        if(!optional.isPresent()) {
            return null;
        }
        return optional.get();
    }

    /**
     * @param username
     * @return User
     * @description get user by username
     */
    @Override
    public AccountInfo getUserByUsername(String username) {
        TbAccount account = accountRepository.findByUsername(username);
        return AccountAdapter.entityToDomain(account);
    }

    /**
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     * @description load user by username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbAccount user = accountRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                this.getAuthority(user)
        );
    }

    /**
     * @param user
     * @return Set<SimpleGrantedAuthority>
     * @description get authority
     */
    private Set<SimpleGrantedAuthority> getAuthority(TbAccount user) {
        Set<SimpleGrantedAuthority> list_authorities = new HashSet<>();
        List<RoleInfo> roleNames = roleService.getUserRole(user.getId());
        for (RoleInfo role: roleNames) {
            list_authorities.add(new SimpleGrantedAuthority(ConstantsSystem.ROLE + role.getRoleName()));
        }
        return list_authorities;
    }

    /**
     * @param username
     * @param password
     * @return boolean
     * @description check user exist
     */
    @Override
    public boolean checkExist(String username, String password) {
        return accountRepository.existsByUsernameAndPassword(username, password);
    }

    /**
     * Get authentication info.
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(String username) {

        AuthenticationInfo info = new AuthenticationInfo();
        TbAccount account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getCode(), ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }

        List<RoleInfo> rolesInfo = roleService.getUserRole(account.getUserId());

        info.setLoginName(account.getUsername());
        info.setToken(account.getPassword());
        info.setRoles(rolesInfo);
        return info;
    }

    @Override
    public Page<UserInfo> fetchUsers(SearchVO search, Pageable pageable) {
        return userRepository.findAllByCondition(search, pageable);
    }

    @Override
    public UserInfo getUserInfo(Long id) {

        TbAccount account = getAccount(id);
        TbUser user = getUser(account.getUserId());
        List<RoleInfo> roleInfo = roleService.getUserRole(account.getId());

        return UserAdapter.toDto(account, user, roleInfo);
    }

    @Transactional
    @Override
    public Long registerOrUpdate(UserInfo userinfo) {
        // Check role.
        RoleInfo role = roleService.getRoleById(StringUtils.toLongOrNull(userinfo.getRoleId()));
        if (role == null) {
            throw new CustomException(ErrorCode.CANT_FOUND_USER_ROLE.getMessage(), HttpStatus.BAD_REQUEST);
        }

        Long accountId = StringUtils.toLongOrNull(userinfo.getId());
        if (accountId == null) {
            return registerAccount(userinfo);
        }
        return updateAccount(userinfo, accountId);
    }

    /**
     * Update user infomation.
     * 
     * @param userinfo
     * @param accountId
     * @return
     */
    private Long updateAccount(UserInfo userinfo, Long accountId) {

        getAccount(accountId);
        getUser(StringUtils.toLongOrNull(userinfo.getUserId()));

        return registerAccount(userinfo);
    }

    /**
     * Register new user.
     * 
     * @param userinfo
     * @return
     */
    private Long registerAccount(UserInfo userinfo) {
        TbUser user = UserAdapter.userInfoToModel(userinfo);
        if (user == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        user = userRepository.save(user);

        TbAccount account = AccountAdapter.userInfoToEntity(userinfo);
        if (account == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        account.setUserId(user.getId());
        account = accountRepository.save(account);

        TbUserRole userRole = userRoleRepository.findByUserId(account.getId());
        if (userRole == null) {
            userRole = new TbUserRole();
        }
        userRole.setUserId(account.getId());
        userRole.setRoleId(StringUtils.toLongOrNull(userinfo.getRoleId()));
        userRoleRepository.save(userRole);

        return account.getId();
    }

    /**
     * Get account information.
     * 
     * @param accountId
     * @return
     */
    private TbAccount getAccount(Long accountId) {
        TbAccount account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return account;
    }

    /**
     * Get accounts information.
     * 
     * @param ids
     * @return
     */
    private List<TbAccount> getAccounts(List<Long> ids) {
        for(Long id : ids) {
            if (id == null) {
                throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        List<TbAccount> accounts = accountRepository.findAllByIds(ids);
        if (accounts.size() != ids.size()) {
            throw new CustomException(ErrorCode.DATA_INVALID.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return accounts;
    }

    /**
     * Get user information.
     * 
     * @param userId
     * @return
     */
    private TbUser getUser(Long userId) {
        if (userId == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        TbUser user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    /**
     * Get users information.
     * @param ids
     * @return
     */
    private List<TbUser> getUsers(List<Long> ids) {
        for(Long id : ids) {
            if (id == null) {
                throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        List<TbUser> users = userRepository.findAllByIds(ids);
        if (users.size() != ids.size()) {
            throw new CustomException(ErrorCode.DATA_INVALID.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return users;
    }

    private List<TbUserRole> getUserRoles(List<Long> ids) {
        for(Long id : ids) {
            if (id == null) {
                throw new CustomException(ErrorCode.DATA_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        List<TbUserRole> userRoles = userRoleRepository.findAllByIds(ids);
        if (userRoles.size() != ids.size()) {
            throw new CustomException(ErrorCode.DATA_INVALID.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return userRoles;
    }

    @Transactional
    @Override
    public List<Long> deleteUsers(List<String> ids) {
        List<Long> accountIds = ids.stream().map(item -> StringUtils.toLongOrNull(item)).collect(Collectors.toList());
        if (accountIds.size() != ids.size()) {
            throw new CustomException(ErrorCode.DATA_INVALID.getMessage(), HttpStatus.BAD_REQUEST);
        }
        List<TbAccount> accounts = getAccounts(accountIds);
        for(TbAccount acc : accounts) {
            acc.setDeletedAt(new Date());
        }

        List<Long> userIds = accounts.stream().map(TbAccount::getUserId).collect(Collectors.toList());
        List<TbUser> users = getUsers(userIds);
        for(TbUser user : users) {
            user.setDeletedAt(new Date());
        }

        List<TbUserRole> userRoles = getUserRoles(accountIds);
        for(TbUserRole userRole : userRoles) {
            userRole.setDeletedAt(new Date());
        }

        accounts = accountRepository.saveAll(accounts);
        userRepository.saveAll(users);
        userRoleRepository.saveAll(userRoles);
        return accountIds;
    }
}
