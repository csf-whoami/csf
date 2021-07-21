package com.csf.whoami.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csf.whoami.database.dto.MenuDomain;
import com.csf.whoami.service.MenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

//	private final MenuMapper menuMapper;
//	private final MenuRepository menuRepository;

	@Override
	public List<MenuDomain> getMenus() {
//		List<TbMenu> menus = menuRepository.findAll();
//		return menus.stream().map(mnu -> ConvertMenuDTO.dbToDomain(mnu)).collect(Collectors.toList());
//		List<MenuView> menus = menuMapper.fetchMenuByCondition();
//		return menus.stream().map(mnu -> ConvertMenuDTO.viewToVo(mnu)).collect(Collectors.toList());
		return new ArrayList<>();
	}
}
