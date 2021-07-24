package com.csf.whoami.database.adapter;

import com.csf.base.utilities.StringUtils;
import com.csf.whoami.database.dto.MenuDomain;
import com.csf.whoami.database.models.TbMenu;
import com.csf.whoami.database.view.MenuView;

public class ConvertMenuDTO {

	private ConvertMenuDTO() {}

	public static MenuDomain dbToDomain(TbMenu entity) {

		if (entity == null) {
			return null;
		}

		MenuDomain domain = new MenuDomain();
		domain.setId(StringUtils.fromLong(entity.getId()));
		domain.setMenuName(entity.getMenuName());
		domain.setLinkScreen(entity.getLinkScreen());
		domain.setLocale(entity.getLocale());
		return domain;
	}

	public static MenuDomain viewToVo(MenuView view) {
        if (view == null) {
            return null;
        }
        MenuDomain vo = new MenuDomain();
        vo.setId(StringUtils.fromLong(view.getId()));
        vo.setMenuName(view.getName());
        vo.setLinkScreen(view.getLinkScreen());
        vo.setLocale(view.getLocale());
        return vo;
    }
}
