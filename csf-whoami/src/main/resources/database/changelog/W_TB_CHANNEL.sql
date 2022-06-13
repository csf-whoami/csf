CREATE TABLE W_TB_CHANNEL (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  CHANNEL_NAME            varchar(64)                     CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  CHANNEL_URL             varchar(200)                    NOT NULL,
  GROUP_ID                bigint                          NOT NULL,
  IS_CLOSED               enum ('Y', 'N')                 NOT NULL DEFAULT 'N',
  IS_PRIVATE              enum ('Y', 'N')                 NOT NULL DEFAULT 'N',
  IS_LOCK                 enum ('Y', 'N')                 NOT NULL DEFAULT 'N',
  CHANNEL_DESCRIPTION     varchar(320)                    DEFAULT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY              bigint                          DEFAULT NULL,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          DEFAULT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
