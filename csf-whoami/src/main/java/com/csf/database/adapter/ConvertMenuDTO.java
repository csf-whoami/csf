package com.csf.database.adapter;

import com.csf.base.domain.MenuVO;
import com.csf.base.utilities.StringUtils;
import com.csf.database.models.MenuEntity;
import com.csf.database.view.MenuView;

public class ConvertMenuDTO {

	private ConvertMenuDTO() {}

	public static MenuVO dbToDomain(MenuEntity entity) {

		if (entity == null) {
			return null;
		}

		MenuVO domain = new MenuVO();
		domain.setId(StringUtils.fromLong(entity.getId()));
		domain.setMenuName(entity.getMenuName());
		domain.setLinkScreen(entity.getLinkScreen());
		domain.setLocale(entity.getLocale());
		return domain;
	}

	public static MenuVO viewToVo(MenuView view) {
        if (view == null) {
            return null;
        }
        MenuVO vo = new MenuVO();
        vo.setId(StringUtils.fromLong(view.getId()));
        vo.setMenuName(view.getName());
        vo.setLinkScreen(view.getLinkScreen());
        vo.setLocale(view.getLocale());
        return vo;
    }
}
