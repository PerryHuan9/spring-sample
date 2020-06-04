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
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL  COMMENT '密码',
  `nickname` varchar(150) COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '用户电话号码',
  `create_date` bigint COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- INSERT INTO user VALUES (1,'perry1', '11011232@qq.com', '12313123123');
-- INSERT INTO user VALUES (2,'perry2', '11011232@qq.com', '12313123123');
-- INSERT INTO user VALUES (3,'perry3', '11011232@qq.com', '12313123123');
-- INSERT INTO user VALUES (4, 'perry4', '11011232@qq.com', '12313123123');
-- INSERT INTO user VALUES (5, 'perry5', '11011232@qq.com', '12313123123');

DROP TABLE IF EXISTS role;
CREATE TABLE  `role` (
    role_id bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
    role_name varchar(100) NOT NULL COMMENT '角色名字',
    role_key varchar(100) NOT NULL COMMENT '角色key',
    status int(1) COMMENT '启用状态；0 -> 禁用; 1-> 启用',
    create_time bigint COMMENT '创建时间',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

INSERT INTO role VALUES (1, '管理员','ROLE_ADMIN',1, 1591010746912);
INSERT INTO role VALUES (2, '操作员', 'ROLE_CTRL',1, 1591010746923);
INSERT INTO role VALUES (3, '运营', 'ROLE_OPERATOR',1,1522010996912);

DROP TABLE IF EXISTS user_role_relation;
CREATE TABLE `user_role_relation` (
    relation_id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id bigint NOT NULL COMMENT '角色ID',
    user_id bigint NOT NULL COMMENT '用户ID',
    PRIMARY KEY (relation_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

INSERT INTO user_role_relation VALUES (1, 1, 1);
INSERT INTO user_role_relation VALUES (2, 2, 2);
INSERT INTO user_role_relation VALUES (3, 3, 3);
INSERT INTO user_role_relation VALUES (4, 2, 1);
INSERT INTO user_role_relation VALUES (5, 3, 1);


DROP TABLE  IF EXISTS permission;
CREATE TABLE `permission` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：1->菜单；2->按钮；',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

INSERT INTO `permission` VALUES (1,'数据概况', 'menu:data_overview', 1, '/dashboard', 1, 1591010746912);
INSERT INTO `permission` VALUES (2,'照明设备', 'menu:light', 1, '/device/light', 1, 1591770746912);
INSERT INTO `permission` VALUES (3,'智能', 'menu:smart', 1, '/device/smart', 1, 1591010766912);

DROP TABLE  IF EXISTS role_permission_relation;
CREATE TABLE `role_permission_relation` (
    relation_id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id bigint NOT NULL COMMENT '角色ID',
    permission_id bigint NOT NULL COMMENT '权限ID',
    PRIMARY KEY (relation_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

INSERT INTO role_permission_relation VALUES (1, 1, 1);
INSERT INTO role_permission_relation VALUES (2, 1, 2);
INSERT INTO role_permission_relation VALUES (3, 1, 3);
INSERT INTO role_permission_relation VALUES (4, 2, 1);
INSERT INTO role_permission_relation VALUES (5, 2, 2);





