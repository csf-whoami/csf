package com.csf.database.providers;

import org.apache.ibatis.jdbc.SQL;

public class GroupProvider {

    private static final String TABLE_NAME = "W_TB_GROUP";

    public String findGroupsByUser(Long userId) {
        return new SQL() {
            {
                SELECT("ID, GROUP_NAME, GROUP_TYPE, IS_PUBLISH, IS_CLOSED, IS_PRIVATE, IS_LOCK, GROUP_DESCRIPTION, GROUP_URL, ACTIVED_AT");
                FROM(TABLE_NAME);
                WHERE("DELETED_AT IS NULL");
                WHERE("GROUP_OWNER = #{userId}");
                
            }
        }.toString();
    }

    public String findGroupsByUrl(String url) {
        return new SQL() {
            {
                SELECT("ID, GROUP_NAME, GROUP_TYPE, IS_PUBLISH, IS_CLOSED, IS_PRIVATE, IS_LOCK, GROUP_DESCRIPTION, GROUP_URL, ACTIVED_AT");
                FROM(TABLE_NAME);
                WHERE("DELETED_AT IS NULL");
                WHERE("GROUP_URL = #{url}");
            }
        }.toString();
    }

    public String findGroupsByUsername(String username) {
        return new SQL() {
            {
                SELECT("W_TB_GROUP.ID, W_TB_GROUP.GROUP_NAME, W_TB_GROUP.GROUP_TYPE, W_TB_GROUP.IS_PUBLISH, W_TB_GROUP.IS_CLOSED, "
                     + " W_TB_GROUP.IS_PRIVATE, W_TB_GROUP.IS_LOCK, W_TB_GROUP.GROUP_DESCRIPTION, W_TB_GROUP.GROUP_URL, W_TB_GROUP.ACTIVED_AT");
                FROM(TABLE_NAME);
                INNER_JOIN("W_TB_ACCOUNT AS ACCOUNT ON ACCOUNT.ID = W_TB_GROUP.GROUP_OWNER");
                WHERE("W_TB_GROUP.DELETED_AT IS NULL");
                WHERE("ACCOUNT.LOGIN_ID = #{username}");
            }
        }.toString();
    }
}
