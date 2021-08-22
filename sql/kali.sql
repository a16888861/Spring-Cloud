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

 Date: 22/08/2021 22:49:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kali_group
-- ----------------------------
DROP TABLE IF EXISTS `kali_group`;
CREATE TABLE `kali_group` (
                              `id` varchar(32) NOT NULL COMMENT '标识符',
                              `user_group` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户组别',
                              `user_group_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户组别名称',
                              `permission_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限表id',
                              `year` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '年份',
                              `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
                              `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                              `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                              `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                              `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='组别表';

-- ----------------------------
-- Records of kali_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for kali_role
-- ----------------------------
DROP TABLE IF EXISTS `kali_role`;
CREATE TABLE `kali_role` (
                             `id` varchar(32) NOT NULL COMMENT '标识符',
                             `sys_id` varchar(32) NOT NULL COMMENT '系统id',
                             `code` varchar(20) DEFAULT NULL COMMENT '角色代码',
                             `name` varchar(50) NOT NULL COMMENT '角色名称',
                             `enname` varchar(50) DEFAULT NULL COMMENT '角色英文名称',
                             `type` varchar(10) NOT NULL COMMENT '角色类型',
                             `status` varchar(10) NOT NULL COMMENT '状态',
                             `data_permission_type` varchar(50) DEFAULT NULL COMMENT '数据权限',
                             `create_by` varchar(32) NOT NULL COMMENT '创建人',
                             `create_date` datetime NOT NULL COMMENT '创建时间',
                             `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
                             `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                             `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
                             `del_flag` char(1) NOT NULL COMMENT '删除标志(0正常1删除)',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `idx_0` (`status`,`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统-角色表';

-- ----------------------------
-- Records of kali_role
-- ----------------------------
BEGIN;
COMMIT;

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
                             `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
                             `year` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '年份',
                             `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
                             `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                             `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                             `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态(0正常1删除)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of kali_user
-- ----------------------------
BEGIN;
INSERT INTO `kali_user` VALUES ('1424295941559369728', 'aaa', '@6y!@2&8@6fyÒ!2c@2af5@°°fa$$0&&2', '123', '13131', 'zhangsan', '9999', NULL, '2021', 'admin', '2021-08-08 17:06:29', NULL, NULL, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
