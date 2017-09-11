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
	`id` INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `ratio` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 维度
-- ----------------------------
DROP TABLE IF EXISTS `t_dimension`;
CREATE TABLE `t_dimension` (
	`id` INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(36) NOT NULL,
  `ratio` int(10) NOT NULL,
  `type_id` int(10) NOT NULL,
  `project_type_id` int(10) DEFAULT NULL,
  `item_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `t_dimension_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `t_service_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- 吐槽
-- ----------------------------
DROP TABLE IF EXISTS `t_complain`;
CREATE TABLE `t_complain` (
	`id` INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `grade` int(2) NOT NULL,
  `img` varchar(128) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `dimension_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_complain_dimension` (`dimension_id`),
  CONSTRAINT `fk_complain_dimension` FOREIGN KEY (`dimension_id`) REFERENCES `t_dimension` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 用户
-- ----------------------------
DROP TABLE IF EXISTS t_user ;
CREATE TABLE `t_user` (
	`id` INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` CHAR (32) NOT NULL,
	`sex` CHAR (4) NOT NULL,
	`mobile` CHAR (16) NOT NULL,
	`password` CHAR (64) NOT NULL,
	`company` CHAR (128) DEFAULT NULL,
	`openId` varchar(32) NOT NULL ,
	`remark` CHAR (16) DEFAULT NULL,
	`is_deleted` CHAR (1) NOT NULL,
	`create_date` date NOT NULL,
	`update_date` date NOT NULL,
	`updated_by_id` INT (10) NOT NULL,
	`updated_by_name` CHAR (32) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- 空间
-- ----------------------------
DROP TABLE IF EXISTS `t_space` ;
CREATE TABLE `t_space` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `grade` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 入住空间
-- ----------------------------
DROP TABLE IF EXISTS t_user_space ;
CREATE TABLE `t_user_space` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL ,
  `space_id` int(10) unsigned NOT NULL ,
  PRIMARY KEY (`id`),
  KEY `idx_user_space_user` (`user_id`),
  KEY `idx_user_space_space` (`space_id`),
  CONSTRAINT `fk_user_space_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `fk_user_space_space` FOREIGN KEY (`space_id`) REFERENCES `t_space` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `t_user` VALUES ('1', 'admin', 'http://img04.sogoucdn.com/app/a/100520024/7cd4acbb91ec56ab77bc2d12583106b2', '男', '1302972767', '21232f297a57a5a743894a0e4a801fc3', '131', '2017-08-24', '2017-08-24', '1', '孟卫波');

CREATE TABLE `t_recharge_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `out_pay_code` varchar(64) DEFAULT NULL COMMENT '外部订单',
  `order` varchar(32) NOT NULL,
  `date` datetime NOT NULL COMMENT '充值时间',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `user_id` int(10) NOT NULL,
  `tel` varchar(16) NOT NULL,
  `recharge_amount` float(14,4) NOT NULL COMMENT '充值金额',
  `discounts_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否使用优惠券,默认不使用',
  `discounts_amount` float(14,4) NOT NULL DEFAULT '0.0000' COMMENT '优惠金额,现在都默认为0',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 待支付 1已取消 2 已支付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_RECHARGE_DETAIL_ORDER` (`order`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值流水';

-- ----------------------------
-- 资讯
-- ----------------------------
DROP TABLE IF EXISTS t_information ;
CREATE TABLE `t_information` (
	`id` INT (10) NOT NULL AUTO_INCREMENT,
	`title` varchar (128) NOT NULL,
	`introduction` varchar (128) NOT NULL,
	`author` varchar(32) NOT NULL ,
	`img` varchar (64) NOT NULL,
	`category` varchar (64) NOT NULL,
	`totle_count` int (10) NOT NULL,
	`create_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;







