/*
Navicat MySQL Data Transfer

Source Server         : 10.15.123.81
Source Server Version : 50731
Source Host           : 10.15.123.81:3306
Source Database       : yrs_base

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-12-26 19:45:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `shard_database`
-- ----------------------------
DROP TABLE IF EXISTS `shard_database`;
CREATE TABLE `shard_database` (
  `id` int(11) NOT NULL COMMENT '主键',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `database_name` varchar(64) DEFAULT NULL COMMENT '库名',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shard_database
-- ----------------------------
INSERT INTO `shard_database` VALUES ('1', '10.15.123.81', '3306', 'yrs_base', 'root', '123456', '1');
INSERT INTO `shard_database` VALUES ('2', '10.15.123.81', '3306', 'yrs_base_2020', 'root', '123456', '1');
INSERT INTO `shard_database` VALUES ('3', '10.15.123.81', '3306', 'yrs_base_2021', 'root', '123456', '1');
