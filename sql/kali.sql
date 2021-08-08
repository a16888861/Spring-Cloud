/*
 Navicat Premium Data Transfer

 Source Server         : MySQL-5.7.5(本地Docker)
 Source Server Type    : MySQL
 Source Server Version : 50705
 Source Host           : localhost:3306
 Source Schema         : kali

 Target Server Type    : MySQL
 Target Server Version : 50705
 File Encoding         : 65001

 Date: 08/08/2021 17:49:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kali_user
-- ----------------------------
DROP TABLE IF EXISTS `kali_user`;
CREATE TABLE `kali_user` (
  `id` varchar(32) NOT NULL COMMENT '标识符',
  `name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `mail` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `screen_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名-别名',
  `user_group` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户组别',
  `year` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '年份',
  `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of kali_user
-- ----------------------------
BEGIN;
INSERT INTO `kali_user` VALUES ('1424295941559369728', 'aaa', '@6y!@2&8@6fyÒ!2c@2af5@°°fa$$0&&2', '123', '13131', 'zhangsan', '9999', '2021', 'admin', '2021-08-08 17:06:29', NULL, NULL, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
