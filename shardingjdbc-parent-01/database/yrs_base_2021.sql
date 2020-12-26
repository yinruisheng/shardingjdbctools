/*
Navicat MySQL Data Transfer

Source Server         : 10.15.123.81
Source Server Version : 50731
Source Host           : 10.15.123.81:3306
Source Database       : yrs_base_2021

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-12-25 20:35:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `alarm_1`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_1`;
CREATE TABLE `alarm_1` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_1
-- ----------------------------
INSERT INTO `alarm_1` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_1` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_1` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_1` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_1` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_1` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_10`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_10`;
CREATE TABLE `alarm_10` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_10
-- ----------------------------
INSERT INTO `alarm_10` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_10` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_10` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_10` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_10` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_10` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_11`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_11`;
CREATE TABLE `alarm_11` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_11
-- ----------------------------
INSERT INTO `alarm_11` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_11` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_11` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_11` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_11` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_11` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_12`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_12`;
CREATE TABLE `alarm_12` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_12
-- ----------------------------
INSERT INTO `alarm_12` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_12` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_12` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_12` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_12` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('6', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('16', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('17', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('20', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('21', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('22', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('23', '越界', '1640423619000');
INSERT INTO `alarm_12` VALUES ('24', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_2`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_2`;
CREATE TABLE `alarm_2` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_2
-- ----------------------------
INSERT INTO `alarm_2` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_2` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_2` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_2` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_2` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_2` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_3`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_3`;
CREATE TABLE `alarm_3` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_3
-- ----------------------------
INSERT INTO `alarm_3` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_3` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_3` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_3` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_3` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_3` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_4`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_4`;
CREATE TABLE `alarm_4` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_4
-- ----------------------------
INSERT INTO `alarm_4` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_4` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_4` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_4` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_4` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_4` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_5`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_5`;
CREATE TABLE `alarm_5` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_5
-- ----------------------------
INSERT INTO `alarm_5` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_5` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_5` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_5` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_5` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_5` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_6`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_6`;
CREATE TABLE `alarm_6` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_6
-- ----------------------------
INSERT INTO `alarm_6` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_6` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_6` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_6` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_6` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_6` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_7`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_7`;
CREATE TABLE `alarm_7` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_7
-- ----------------------------
INSERT INTO `alarm_7` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_7` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_7` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_7` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_7` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_7` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_8`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_8`;
CREATE TABLE `alarm_8` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_8
-- ----------------------------
INSERT INTO `alarm_8` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_8` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_8` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_8` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_8` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_8` VALUES ('6', '越界', '1640423619000');

-- ----------------------------
-- Table structure for `alarm_9`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_9`;
CREATE TABLE `alarm_9` (
  `id` bigint(20) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_9
-- ----------------------------
INSERT INTO `alarm_9` VALUES ('1', '打架', '1608886042919');
INSERT INTO `alarm_9` VALUES ('2', '打架', '1608886507606');
INSERT INTO `alarm_9` VALUES ('3', '摔倒', '1608887619000');
INSERT INTO `alarm_9` VALUES ('4', '摔倒', '1608887619000');
INSERT INTO `alarm_9` VALUES ('5', '越界', '1640423619000');
INSERT INTO `alarm_9` VALUES ('6', '越界', '1640423619000');
