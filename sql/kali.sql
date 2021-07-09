/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : kali

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 09/07/2021 21:21:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kali_user
-- ----------------------------
DROP TABLE IF EXISTS `kali_user`;
CREATE TABLE `kali_user` (
  `uid` varchar(32) NOT NULL COMMENT '标识符',
  `name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `mail` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `screenName` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名-别名',
  `group` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户组别',
  `year` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '年份',
  `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of kali_user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
