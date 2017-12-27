/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-08-18 18:22:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 服务项
-- ----------------------------
DROP TABLE IF EXISTS `t_service_item`;
CREATE TABLE `t_service_item` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `ratio` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务项';

-- ----------------------------
-- 维度
-- ----------------------------
DROP TABLE IF EXISTS `t_dimension`;
CREATE TABLE `t_dimension` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) NOT NULL,
  `ratio` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `project_type_id` int(11) DEFAULT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `t_dimension_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `t_service_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维度';


-- ----------------------------
-- 吐槽
-- ----------------------------
DROP TABLE IF EXISTS `t_complain`;
CREATE TABLE `t_complain` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `grade` int(2) NOT NULL,
  `img` varchar(128) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `dimension_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_complain_dimension` (`dimension_id`),
  CONSTRAINT `fk_complain_dimension` FOREIGN KEY (`dimension_id`) REFERENCES `t_dimension` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='吐槽';

-- ----------------------------
-- 用户
-- ----------------------------
DROP TABLE IF EXISTS t_user ;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `img` varchar(128) DEFAULT NULL,
  `company` varchar(128) DEFAULT NULL,
  `sex` char(4) DEFAULT NULL,
  `mobile` varchar(16) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `remark` varchar(16) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `updated_by_id` int(11) DEFAULT NULL,
  `updated_by_name` varchar(32) DEFAULT NULL,
  `is_managed` char(1) NOT NULL,
  `is_deleted` char(1) NOT NULL,
  `open_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_mobile_open_id` (`mobile`,`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- 空间
-- ----------------------------
DROP TABLE IF EXISTS `t_space` ;
CREATE TABLE `t_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `grade` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='空间';

-- ----------------------------
-- 入住空间
-- ----------------------------
DROP TABLE IF EXISTS t_user_space ;
CREATE TABLE `t_user_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL ,
  `space_id` int(11) NOT NULL ,
  PRIMARY KEY (`id`),
  KEY `idx_user_space_user` (`user_id`),
  KEY `idx_user_space_space` (`space_id`),
  CONSTRAINT `fk_user_space_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `fk_user_space_space` FOREIGN KEY (`space_id`) REFERENCES `t_space` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户入住空间';

CREATE TABLE `t_recharge_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `out_pay_code` varchar(64) DEFAULT NULL COMMENT '外部订单',
  `order` varchar(32) NOT NULL,
  `date` datetime NOT NULL COMMENT '充值时间',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `user_id` int(11) NOT NULL,
  `tel` varchar(16) NOT NULL,
  `recharge_amount` float(14,4) NOT NULL COMMENT '充值金额',
  `discounts_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否使用优惠券,默认不使用',
  `discounts_amount` float(14,4) NOT NULL DEFAULT '0.0000' COMMENT '优惠金额,现在都默认为0',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 待支付 1已取消 2 已支付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_RECHARGE_DETAIL_ORDER` (`order`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值流水';

-- ----------------------------
-- 小程序登录认证
-- ----------------------------
DROP TABLE IF EXISTS t_user_token ;
CREATE TABLE `t_user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(64) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_token_user` (`user_id`),
  CONSTRAINT `ifk_user_token_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小程序登录认证';

-- ----------------------------
-- 活动类别
-- ----------------------------
DROP TABLE IF EXISTS  `t_activity_type` ;
CREATE TABLE `t_activity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `is_deleted` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动类别';

-- ----------------------------
-- 1级地址
-- ----------------------------
DROP TABLE IF EXISTS  `t_address` ;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='1级地址';

-- ----------------------------
-- 2级地址
-- ----------------------------
DROP TABLE IF EXISTS  `t_address_detail` ;
CREATE TABLE `t_address_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_address_detail_address` FOREIGN KEY (`address_id`) REFERENCES `t_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='2级地址';

-- ----------------------------
-- 活动
-- ----------------------------
DROP TABLE IF EXISTS  `t_activity` ;
CREATE TABLE `t_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `price` decimal(18,2) DEFAULT NULL,
  `limit` int(11) DEFAULT NULL,
  `img` varchar(128) NOT NULL,
  `content` text NOT NULL,
  `is_online` char(1) NOT NULL,
  `type_id` int(11) NOT NULL,
  `address_detail_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_type_title` (`title`),
  CONSTRAINT `fk_activity_type` FOREIGN KEY (`type_id`) REFERENCES `t_activity_type` (`id`),
  CONSTRAINT `fk_activity_address_detail` FOREIGN KEY (`address_detail_id`) REFERENCES `t_address_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动';

-- ----------------------------
-- 报名名单
-- ----------------------------
DROP TABLE IF EXISTS  `t_activity_user` ;
CREATE TABLE `t_activity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price`decimal(18,2) DEFAULT NULL,
  `create_date` date NOT NULL,
  `user_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_user_activity_user` (`user_id`, `activity_id`),
  KEY `key_activity_user_user` (`user_id`),
  KEY `idx_activity_user_activity` (`activity_id`),
  CONSTRAINT `fk_activity_user_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `fk_activity_user_activity` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报名名单';
-- ----------------------------
-- 资讯
-- ----------------------------
CREATE TABLE `t_advice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(400) DEFAULT NULL,
  `miniText` varchar(400) DEFAULT NULL,
  `content` text,
  `img` varchar(400) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `typeId` char(32) DEFAULT NULL,
  `clicksum` int(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资讯';

DROP TABLE IF EXISTS  `t_advice_type` ;
CREATE TABLE `t_advice_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资讯类型';

-- ----------------------------
-- banner位置
-- ----------------------------
DROP TABLE IF EXISTS  `t_banner_position` ;
CREATE TABLE `t_banner_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(32) NOT NULL,
  `description` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_banner_position_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='banner位置';

-- ----------------------------
-- banner 类型
-- ----------------------------
DROP TABLE IF EXISTS  `t_banner_type` ;
CREATE TABLE `t_banner_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(32) NOT NULL,
  `description` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_banner_type_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='banner类型';

-- ----------------------------
-- banner
-- ----------------------------
DROP TABLE IF EXISTS  `t_banner` ;
CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `img` varchar(128) NOT NULL,
  `link_url` varchar(128) DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `is_online` char(1) NOT NULL,
  `update_date` date NOT NULL,
  `updated_by_id` int(11) NOT NULL,
  `updated_by_name` char(32) NOT NULL,
  `type_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `activity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_banner_type` FOREIGN KEY (`type_id`) REFERENCES `t_banner_type` (`id`),
  CONSTRAINT `fk_banner_position` FOREIGN KEY (`position_id`) REFERENCES `t_banner_position` (`id`),
  CONSTRAINT `fk_banner_activity` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图';

-- ----------------------------
-- 设施
-- ----------------------------
DROP TABLE IF EXISTS `t_device` ;
CREATE TABLE `t_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `is_deleted` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_activity_type_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设施';

-- ----------------------------
-- 社区
-- ----------------------------
DROP TABLE IF EXISTS `t_community` ;
CREATE TABLE `t_community` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '名称',
  `tag` varchar(64) DEFAULT NULL COMMENT '标签',
  `tips` text DEFAULT NULL COMMENT '提示',
  `content` text DEFAULT NULL COMMENT '介绍',
  `station_total` int(11)  DEFAULT '0' COMMENT '工位',
  `rent_num` int(11)  DEFAULT '0' COMMENT '租赁工位数量',
  `surplus_num` int(11)  DEFAULT '0' COMMENT '剩余工位数量',
  `room_num` int(11)  DEFAULT '0' COMMENT '会议室数量',
  `activity_num` int(11)  DEFAULT '0' COMMENT '活动场所数量',
  `is_online` char(1) NOT NULL,
  `created_date` date NOT NULL,
  `updated_date` date NOT NULL,
  `updated_by_id` int(11) NOT NULL,
  `updated_by_name` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社区';

-- ----------------------------
-- 社区图片
-- ----------------------------
DROP TABLE IF EXISTS `t_community_picture` ;
CREATE TABLE `t_community_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `community_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社区图片';

-- ----------------------------
-- 社区设施
-- ----------------------------
DROP TABLE IF EXISTS `t_community_device` ;
CREATE TABLE `t_community_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL,
  `community_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社区设施';

-- ----------------------------
-- 社区经理
-- ----------------------------
DROP TABLE IF EXISTS `t_community_user` ;
CREATE TABLE `t_community_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `community_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社区经理';

-- ----------------------------
-- 活动会议场所
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_meeting_space` ;
CREATE TABLE `t_activity_meeting_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `position` varchar(64) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  `capacity` int(11) NOT NULL,
  `amount` decimal(18,2) NOT NULL,
  `type` int(1) NOT NULL COMMENT '0 活动 1 会议室',
  `community_id` int(11) NOT NULL,
  `created_date` date NOT NULL,
  `updated_date` date NOT NULL,
  `updated_by_id` int(11) NOT NULL,
  `updated_by_name` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动会议场所';

-- ----------------------------
-- 活动会议场所设施
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_meeting_space_device` ;
CREATE TABLE `t_activity_meeting_space_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL,
  `space_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动会议场所设施';

INSERT INTO `t_user` VALUES ('1', 'admin', 'http://img04.sogoucdn.com/app/a/110520024/7cd4acbb91ec56ab77bc2d12583116b2', '男', '1302972767', '21232f297a57a5a743894a0e4a801fc3', '131', '2017-08-24', '2017-08-24', '1', '孟卫波','N','');
INSERT INTO `t_banner_position` VALUES ('1', 'HOME_PAGE', '首页轮播'),('2', 'E_CARD', 'E卡管理轮播');
INSERT INTO `t_banner_type` VALUES ('1', 'ACTIVITY', '活动详情'),('2', 'CUSTOM', '自定义'),('3', 'NONE', '无');
