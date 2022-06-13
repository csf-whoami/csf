CREATE TABLE W_TB_ACCOUNT (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  LOGIN_ID                varchar(32)                     NOT NULL,
  USER_PASSWORD           tinyblob                        NOT NULL,
  USER_ID                 bigint                          NOT NULL,
  ACTIVED_AT              timestamp                       NULL DEFAULT NULL,
  STARTED_AT              date                            DEFAULT NULL,
  FINISHED_AT             date                            DEFAULT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          DEFAULT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL,
  CREATED_BY              bigint                          DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;