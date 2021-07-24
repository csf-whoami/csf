package com.csf.whoami.database.providers;

import org.apache.ibatis.jdbc.SQL;

public class MenuProvider {

	private static final String TABLE_NAME = "W_TB_MENU";

	public String fetchMenus() {
        return new SQL() {
            {
                SELECT("ID, MENU_NAME, LINK_SCREEN, PARENT_MENU");
                FROM(TABLE_NAME);
                WHERE("DELETED_AT IS NULL");
            }
        }.toString();
    }
}
