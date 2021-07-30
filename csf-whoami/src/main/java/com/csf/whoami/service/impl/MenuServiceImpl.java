package com.csf.whoami.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.domain.MenuDomain;
import com.csf.whoami.database.adapter.ConvertMenuDTO;
import com.csf.whoami.database.adapter.MenuAdapter;
import com.csf.whoami.database.mappers.MenuMapper;
import com.csf.whoami.database.models.TbMenu;
import com.csf.whoami.database.repository.MenuRepository;
import com.csf.whoami.database.view.MenuView;
import com.csf.whoami.service.MenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    @Override
    public List<MenuDomain> getMenus() {
//        AuthenticationInfo user = AuthenticationUtil.getUser();
        List<MenuView> menus = menuMapper.fetchMenuByCondition();
        return menus.stream().map(mnu -> ConvertMenuDTO.viewToVo(mnu)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long registerMenu(MenuDomain menu) {
        TbMenu entity = MenuAdapter.domainToDb(menu);
        if (entity != null) {
            return menuRepository.save(entity).getId();
        }
        return null;
    }
}
