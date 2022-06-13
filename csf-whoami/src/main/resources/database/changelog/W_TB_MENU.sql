
CREATE TABLE W_TB_MENU (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  MENU_NAME               varchar(20)                     CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  LINK_SCREEN             varchar(200)                    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  LOCALE                  varchar(3)                      CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PARENT_MENU             bigint                          DEFAULT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY              bigint                          DEFAULT NULL,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          DEFAULT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
