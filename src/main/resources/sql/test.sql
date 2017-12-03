USE test;

/* 测试产品表 */
DROP TABLE IF EXISTS product;
CREATE TABLE product (
  id          BIGINT         NOT NULL  AUTO_INCREMENT
  COMMENT '产品id',
  name        VARCHAR(255)   NOT NULL
  COMMENT '产品名称',
  price       DECIMAL(10, 2) NOT NULL  DEFAULT 0
  COMMENT '产品价格',
  stock       BIGINT            NOT NULL  DEFAULT 0
  COMMENT '产品库存',
  create_time TIMESTAMP      NOT NULL  DEFAULT '0000-00-00 00:00:00'
  COMMENT '创建时间',
  update_time TIMESTAMP      NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (id),
  KEY idx_create_time(create_time),
  KEY idx_update_time(update_time)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '产品表';

/* product */
INSERT INTO test.product (id, name, price, stock, create_time, update_time)
VALUES (NULL, 'iPhone X', 8999.00, 100, NULL , NULL);
INSERT INTO test.product (id, name, price, stock, create_time, update_time)
VALUES (NULL, 'Mac Pro', 13999.00, 100, NULL, NULL);


DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT
  COMMENT '用户编号',
  name     VARCHAR(255) NOT NULL
  COMMENT '用户名称',
  username VARCHAR(255) NOT NULL
  COMMENT '账号',
  password VARCHAR(255) NOT NULL
  COMMENT '密码',
  salt     VARCHAR(255) NOT NULL
  COMMENT '盐',
  status   TINYINT      NOT NULL DEFAULT 1
  COMMENT '用户状态 0-无效，1-有效',
  PRIMARY KEY (id),
  UNIQUE KEY (username)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '用户';

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id        INT          NOT NULL AUTO_INCREMENT
  COMMENT '角色编号',
  role_name VARCHAR(255) NOT NULL
  COMMENT '角色名称',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '角色';

DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
  id       INT          NOT NULL AUTO_INCREMENT
  COMMENT '权限编号',
  url      VARCHAR(255) NOT NULL
  COMMENT 'url地址',
  url_name VARCHAR(255) NOT NULL
  COMMENT 'url描述',
  perm     VARCHAR(255) NOT NULL
  COMMENT '权限标识符',
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '权限';

DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  user_id INT NOT NULL
  COMMENT '用户编号',
  role_id INT NOT NULL
  COMMENT '角色编号',
  PRIMARY KEY (user_id, role_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '用户-角色';

DROP TABLE IF EXISTS role_permissions;
CREATE TABLE role_permissions (
  role_id       INT NOT NULL
  COMMENT '角色编号',
  permission_id INT NOT NULL
  COMMENT '权限编号',
  PRIMARY KEY (role_id, permission_id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '角色-权限';

/* user */
INSERT INTO test.users (id, name, username, password, salt, status)
VALUES (1, 'root', 'wch853', '6991b327c9a016bbfc7fbe905d08a82e', '!@#', 1);
INSERT INTO test.users (id, name, username, password, salt, status)
VALUES (2, 'read-only', 'ro', '6991b327c9a016bbfc7fbe905d08a82e', '!@#', 1);
INSERT INTO test.users (id, name, username, password, salt, status)
VALUES (3, 'read-add-update', 'rau', '6991b327c9a016bbfc7fbe905d08a82e', '!@#', 1);

/* role */
INSERT INTO test.roles (id, role_name) VALUES (1, '超级管理员');
INSERT INTO test.roles (id, role_name) VALUES (2, 'read-only');
INSERT INTO test.roles (id, role_name) VALUES (3, 'read-add-update');

/* permission */
INSERT INTO test.permission (id, url, url_name, perm) VALUES (1, '/query', '查询', 'user:query');
INSERT INTO test.permission (id, url, url_name, perm) VALUES (2, '/add', '新增', 'user:add');
INSERT INTO test.permission (id, url, url_name, perm) VALUES (3, '/update', '更新', 'user:update');
INSERT INTO test.permission (id, url, url_name, perm) VALUES (4, '/delete', '删除', 'user:delete');
INSERT INTO test.permission (id, url, url_name, perm) VALUES (5, '/**', '超级管理员', '*');

/* user_roles */
INSERT INTO test.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO test.user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO test.user_roles (user_id, role_id) VALUES (3, 3);

/* role_permissions */
INSERT INTO test.role_permissions (role_id, permission_id) VALUES (1, 5);
INSERT INTO test.role_permissions (role_id, permission_id) VALUES (2, 1);
INSERT INTO test.role_permissions (role_id, permission_id) VALUES (3, 1);
INSERT INTO test.role_permissions (role_id, permission_id) VALUES (3, 2);
INSERT INTO test.role_permissions (role_id, permission_id) VALUES (3, 3);