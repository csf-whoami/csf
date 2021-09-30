package com.csf.whoami.adm;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsURL;
import com.csf.base.domain.RoleInfo;
import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.UserInfo;
import com.csf.whoami.service.RoleService;
import com.csf.whoami.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = ConstantsURL.URI_USER)
public class UserPage extends DashboardPage<UserInfo> {

    private final UserService userService;
    private final RoleService roleService;

    /**
     * Go to management page.
     * 
     * @param search
     * @param pageable
     * @param model
     * @return
     */
    @Override
    protected Page<UserInfo> searchData(SearchVO search, Pageable pageable, ModelAndView model) {
        super.searchData(search, pageable, model);
        return userService.fetchUsers(search, pageable);
    }

    /**
     * Go to register page.
     * 
     * @param model
     * @return
     */
    @Override
    protected void initialData(UserInfo data, ModelAndView model) {
        super.initialData(data, model);
        List<RoleInfo> roles = roleService.getSystemRoles();
        data.setRoles(roles);
    }

    /**
     * Register or Update datas information.
     * 
     * @param userinfo
     * @param model
     * @return
     */
    @Override
    protected boolean registerOrUpdate(UserInfo data, ModelAndView model) {
        Long id = userService.registerOrUpdate(data);
        return id > 0L;
    }

    /**
     * Go to detail page.
     * 
     * @param id
     * @param model
     * @return
     */
    @Override
    protected UserInfo detailInfo(Long id, ModelAndView model) {
        return userService.getUserInfo(id);
    }

    /**
     * Deletes list IDs.
     * 
     * @param ids
     * @return
     */
    @Override
    protected List<Long> deleteIds(List<String> ids) {
        return userService.deleteUsers(ids);
    }

    @Override
    protected String functionPath() {
        return ConstantsURL.URI_USER;
    }

    @Override
    protected UserInfo initialModel() {
        return new UserInfo();
    }
}
