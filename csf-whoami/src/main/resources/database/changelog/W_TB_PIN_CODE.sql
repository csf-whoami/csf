CREATE TABLE W_TB_PIN_CODE (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  GROUP_TYPE              varchar(20)                     NOT NULL,
  CONTENT_ID              bigint                          NOT NULL,
  PIN_CODE                varchar(10)                     DEFAULT NULL,
  START_DATE              date                            DEFAULT NULL,
  END_DATE                date                            DEFAULT NULL,
  IS_PRIVATE              enum ('Y', 'N')                 NOT NULL DEFAULT 'N',
  ACTIVED_AT              timestamp                       NULL DEFAULT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY              bigint                          DEFAULT NULL,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          DEFAULT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
