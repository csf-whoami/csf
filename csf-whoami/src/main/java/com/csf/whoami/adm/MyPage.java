package com.csf.whoami.adm;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsParam;
import com.csf.base.constant.ConstantsURL;
import com.csf.base.domain.MenuVO;
import com.csf.whoami.service.MenuService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = ConstantsURL.DASHBOARD_HOME)
public class MyPage {

    private final MenuService menuService;

    @GetMapping
    public ModelAndView index(ModelAndView model) {
        model.setViewName(ConstantsURL.DASHBOARD_PAGE);
        return model;
    }

    @GetMapping(value = ConstantsURL.URI_DASHBOARD_MENU)
    public ModelAndView menus(ModelAndView model) {
        List<MenuVO> listMenu = menuService.getMenus();
        model.addObject(ConstantsParam.DATAS, listMenu);
        model.setViewName(ConstantsURL.PAGE_MENU);
        return model;
    }

    @GetMapping(value = ConstantsURL.URI_DASHBOARD_START)
    public ModelAndView homePage(ModelAndView model) {
        model.setViewName(ConstantsURL.PAGE_HOME);
        return model;
    }
}
