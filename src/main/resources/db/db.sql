use simons;

CREATE TABLE `movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id,自增',
  `name` varchar(100) NOT NULL COMMENT '电影名称',
  `link` varchar(200) NOT NULL COMMENT '电影链接',
  `passwd` varchar(100) NOT NULL COMMENT '密码',
  `original` varchar(20) NOT NULL DEFAULT '采集' COMMENT '数据来源',
  `type` varchar(10) NOT NULL DEFAULT '电影' COMMENT '类别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4179 DEFAULT CHARSET=utf8 COMMENT='电影信息表';


CREATE TABLE `movie_invalid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id,自增',
  `name` varchar(100) NOT NULL COMMENT '电影名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='失效电影信息表';


CREATE TABLE `movie_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id,自增',
  `name` varchar(100) NOT NULL COMMENT '电影名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='待补充的电影信息记录表'

