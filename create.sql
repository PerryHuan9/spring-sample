DROP TABLE IF EXISTS article;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `publish_date` bigint(20) DEFAULT NULL,
  `article_type` varchar(100) DEFAULT NULL COMMENT '文章类型',
  `article_id` varchar(100) DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章信息2';

INSERT INTO article(title, author, publish_date, article_type, article_id)
    VALUES ('标题1','作者1', 23424234234, '原创', 21312312);
INSERT INTO article(title, author, publish_date, article_type, article_id)
    VALUES ('标题2','作者2', 23424234234, '原创', 21312312);
INSERT INTO article(title, author, publish_date, article_type, article_id)
    VALUES ('标题3','作者3', 23424234234, '原创', 21312312);
INSERT INTO article(title, author, publish_date, article_type, article_id)
    VALUES ('标题4','作者4', 23424234234, '原创', 21312312);
INSERT INTO article(title, author, publish_date, article_type, article_id)
    VALUES ('标题5','作者5', 23424234234, '原创', 21312312);


DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '用户电话号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

INSERT INTO user(name, email, phone_number) VALUES ('perry1', '11011232@qq.com', '12313123123');
INSERT INTO user(name, email, phone_number) VALUES ('perry2', '11011232@qq.com', '12313123123');
INSERT INTO user(name, email, phone_number) VALUES ('perry3', '11011232@qq.com', '12313123123');
INSERT INTO user(name, email, phone_number) VALUES ('perry4', '11011232@qq.com', '12313123123');
INSERT INTO user(name, email, phone_number) VALUES ('perry5', '11011232@qq.com', '12313123123');




