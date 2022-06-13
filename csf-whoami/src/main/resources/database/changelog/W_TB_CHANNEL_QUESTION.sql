CREATE TABLE W_TB_CHANNEL_QUESTION (
  ID                      bigint                          AUTO_INCREMENT PRIMARY KEY,
  CHANNEL_ID              bigint                          NOT NULL,
  QUESTION_ID             bigint                          NOT NULL,
  CREATED_AT              timestamp                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CREATED_BY              bigint                          DEFAULT NULL,
  UPDATED_AT              timestamp                       NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UPDATED_BY              bigint                          DEFAULT NULL,
  DELETED_AT              timestamp                       NULL DEFAULT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
