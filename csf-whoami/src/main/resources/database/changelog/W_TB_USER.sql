CREATE TABLE W_TB_USER (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  FULL_NAME               varchar(64)                     NOT NULL,
  PHONE                   varchar(64)                     DEFAULT NULL,
  ADDRESS                 varchar(320)                    DEFAULT NULL,
  EMAIL                   varchar(120)                    DEFAULT NULL,
  NOTE                    varchar(320)                    DEFAULT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY              bigint                          DEFAULT NULL,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          DEFAULT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
