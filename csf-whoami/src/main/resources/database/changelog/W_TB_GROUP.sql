CREATE TABLE W_TB_GROUP (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  GROUP_NAME              varchar(64)                     NOT NULL,
  GROUP_TYPE              varchar(32)                     NOT NULL,
  GROUP_OWNER             bigint                          NOT NULL,
  IS_PUBLISH              enum ('Y', 'N')                 NOT NULL DEFAULT 'Y',
  IS_CLOSED               enum ('Y', 'N')                 NOT NULL DEFAULT 'Y',
  IS_PRIVATE              enum ('Y', 'N')                 NOT NULL DEFAULT 'Y',
  IS_LOCK                 enum ('Y', 'N')                 NOT NULL DEFAULT 'N',
  GROUP_DESCRIPTION       varchar(320)                    DEFAULT NULL,
  GROUP_URL               varchar(64)                     CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  ACTIVED_AT              timestamp                       NULL DEFAULT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY              bigint                          DEFAULT NULL,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          NOT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
