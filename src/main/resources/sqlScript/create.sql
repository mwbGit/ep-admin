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
-- Table structure for `t_service_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_service_item`;
CREATE TABLE `t_service_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `ratio` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_service_item
-- ----------------------------
-- ----------------------------
-- Table structure for `t_dimension`
-- ----------------------------
DROP TABLE IF EXISTS `t_dimension`;
CREATE TABLE `t_dimension` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) NOT NULL,
  `ratio` int(10) NOT NULL,
  `type_id` int(10) NOT NULL,
  `project_type_id` int(10) DEFAULT NULL,
  `item_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `t_dimension_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `t_service_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for `t_complain`
-- ----------------------------
DROP TABLE IF EXISTS `t_complain`;
CREATE TABLE `t_complain` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `grade` int(2) NOT NULL,
  `img` varchar(128) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `dimension_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dimension_id` (`dimension_id`),
  CONSTRAINT `t_complain_ibfk_1` FOREIGN KEY (`dimension_id`) REFERENCES `t_dimension` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS t_user ;
CREATE TABLE `t_user` (
	`id` INT (10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` CHAR (32) NOT NULL,
	`img` CHAR (128) DEFAULT NULL,
	`sex` CHAR (4) NOT NULL,
	`mobile` CHAR (16) NOT NULL,
	`password` CHAR (16) NOT NULL,
	`remark` CHAR (16) DEFAULT NULL,
	`create_date` date NOT NULL,
	`update_date` date NOT NULL,
	`updated_by_id` INT (10) NOT NULL,
	`updated_by_name` CHAR (32) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;


INSERT INTO `t_user` VALUES ('1', 'admin', 'http://img04.sogoucdn.com/app/a/100520024/7cd4acbb91ec56ab77bc2d12583106b2', '男', '1302972767', 'admin', '131', '2017-08-24', '2017-08-24', '1', '孟卫波');


