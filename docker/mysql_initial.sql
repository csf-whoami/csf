CREATE TABLE `oauth_access_token` (
  authentication_id varchar(256) PRIMARY KEY,
  token_id varchar(256) DEFAULT NULL,
  token blob DEFAULT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  authentication blob DEFAULT NULL,
  refresh_token varchar(256) DEFAULT NULL
);

CREATE TABLE `oauth_refresh_token` (
  token_id varchar(256) DEFAULT NULL,
  token blob DEFAULT NULL,
  authentication blob DEFAULT NULL
);

CREATE TABLE `tbl_users` (
  id varchar(255) PRIMARY KEY,
  user_name varchar(255) DEFAULT NULL,
  user_password varchar(255) DEFAULT NULL,
  mac_address varchar(255) DEFAULT NULL,
  login_type int(1) DEFAULT NULL,
  social_token varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  delflg int(1) DEFAULT NULL
);

CREATE TABLE `tbl_roles` (
  id varchar(255) PRIMARY KEY,
  role_code varchar(20) NOT NULL,
  role_name varchar(100) NOT NULL,
  create_date datetime DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  delflg int(11) DEFAULT NULL
);

CREATE TABLE `tbl_user_role` (
  id varchar(255) PRIMARY KEY,
  create_date datetime DEFAULT NULL,
  delflg int(11) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  role_id varchar(255) NOT NULL,
  user_id varchar(255) NOT NULL,
  KEY FK_USER_ROLE_ROLES (role_id),
  KEY FK_USER_ROLE_USERS (user_id),
  CONSTRAINT FK_USER_ROLE_USERS FOREIGN KEY (user_id) REFERENCES `tbl_users`(id),
  CONSTRAINT FK_USER_ROLE_ROLES FOREIGN KEY (role_id) REFERENCES `tbl_roles`(id)
);

CREATE TABLE `tbl_access_management` (
  url_access varchar(255) PRIMARY KEY,
  role_code varchar(20) NOT NULL,
  role_name varchar(100) NOT NULL,
  create_date datetime DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  delflg int(1) DEFAULT NULL
);

-- Insert data ROLE to table. --
INSERT INTO `tbl_roles`(id, role_code, role_name, create_date, update_date, delflg) values(UUID(), 'USER', 'USER', NOW(), null, 0);
INSERT INTO `tbl_roles`(id, role_code, role_name, create_date, update_date, delflg) values(UUID(), 'ADMIN', 'ADMIN', NOW(), null, 0);
INSERT INTO `tbl_roles`(id, role_code, role_name, create_date, update_date, delflg) values(UUID(), 'SERVER', 'SYSTEM ADMINISTRATOR', NOW(), null, 0);

-- Insert data URL to table. --
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/auth/login', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/auth/register', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/security/key', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/auth/health-life/login', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/basic/common/**', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/swagger-ui.html', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/v2/api-docs', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/swagger-resources/**', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/webjars/springfox-swagger-ui/**', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/auth/guest', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/resources/templates', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/auth/forgot-password', NOW(), null, 0);
INSERT INTO `tbl_access_management`(role_code, role_name, url_access, create_date, update_date, delflg) values('USER', 'USER', '/common/app/version', NOW(), null, 0);