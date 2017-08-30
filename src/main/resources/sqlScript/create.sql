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


