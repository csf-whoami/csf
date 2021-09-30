package com.csf.whoami.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.base.core.ZValue;
import com.csf.base.domain.MenuDomain;
import com.csf.database.adapter.MenuAdapter;
import com.csf.database.mappers.MenuMapper;
import com.csf.database.models.TbMenu;
import com.csf.database.repository.MenuRepository;
import com.csf.whoami.service.MenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    @Override
    public List<MenuDomain> getMenus() {
        List<ZValue> menus = menuMapper.fetchMenuByCondition();
//        return menus.stream().map(mnu -> ConvertMenuDTO.viewToVo(mnu)).collect(Collectors.toList());
        for(ZValue item : menus) {
        	System.out.println("data: " + item);
        	System.out.println("ID: " + item.getLong("id"));
        	System.out.println("Menu name: " + item.getString("menuName"));
        	System.out.println("Link screen: " + item.getString("linkScreen"));
        }
        return new ArrayList<>();
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
