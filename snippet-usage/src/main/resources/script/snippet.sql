USE snippet;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
  id          BIGINT         NOT NULL  AUTO_INCREMENT
  COMMENT '产品id',
  name        VARCHAR(255)   NOT NULL
  COMMENT '产品名称',
  price       DECIMAL(10, 2) NOT NULL  DEFAULT 0
  COMMENT '产品价格',
  stock       INT            NOT NULL  DEFAULT 0
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

INSERT INTO snippet.product (id, name, price, stock, create_time, update_time)
VALUES (NULL, 'iPhone X', 8999.00, 100, NULL , NULL);
INSERT INTO snippet.product (id, name, price, stock, create_time, update_time)
VALUES (NULL, 'Mac Pro', 13999.00, 100, NULL, NULL);

