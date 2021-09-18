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

 Date: 18/09/2021 00:04:05
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统-组别表';

-- ----------------------------
-- Records of kali_group
-- ----------------------------
BEGIN;
INSERT INTO `kali_group` VALUES ('1430106533911797760', '9999', 'superAdmin', NULL, '2021', NULL, '2021-08-24 17:55:42', NULL, NULL, '0');
INSERT INTO `kali_group` VALUES ('1430109065098715136', '9998', 'admin', NULL, '2021', NULL, '2021-08-24 18:05:46', NULL, NULL, '0');
INSERT INTO `kali_group` VALUES ('1430109104843939840', '1000', 'user', NULL, '2021', NULL, '2021-08-24 18:05:55', NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for kali_group_role
-- ----------------------------
DROP TABLE IF EXISTS `kali_group_role`;
CREATE TABLE `kali_group_role` (
                                   `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '唯一标识',
                                   `group_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '组别id',
                                   `role_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '角色id',
                                   `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
                                   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                                   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态(0正常1删除)',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   KEY `idx_0` (`group_id`,`role_id`) USING BTREE,
                                   KEY `idx_1` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统-组别角色表';

-- ----------------------------
-- Records of kali_group_role
-- ----------------------------
BEGIN;
INSERT INTO `kali_group_role` VALUES ('1431876295320940544', '1430106533911797760', '1431876295237054464', NULL, '2021-08-29 15:08:06', NULL, NULL, '0');
INSERT INTO `kali_group_role` VALUES ('1437679670769352704', '1430109065098715136', '1437679670266036224', '1430109634181881856', '2021-09-14 15:28:39', NULL, NULL, '0');
INSERT INTO `kali_group_role` VALUES ('1437680386774794240', '1430109104843939840', '1437680385961099264', '1430109634181881856', '2021-09-14 15:31:30', NULL, NULL, '0');
INSERT INTO `kali_group_role` VALUES ('1437680665037504512', '1430109104843939840', '1437680664660017152', '1430109634181881856', '2021-09-14 15:32:36', NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for kali_menu
-- ----------------------------
DROP TABLE IF EXISTS `kali_menu`;
CREATE TABLE `kali_menu` (
                             `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '唯一标识',
                             `sys_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '系统id',
                             `parent_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '父级id',
                             `parent_code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '父级code',
                             `parent_ids` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '上级ids',
                             `name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '菜单名称',
                             `href` varchar(200) CHARACTER SET utf8 DEFAULT '' COMMENT '菜单访问路径',
                             `target` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单打开方式',
                             `icon` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标',
                             `sort` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '排序',
                             `code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单编码',
                             `status` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单状态(0锁定1正常)',
                             `is_show` char(1) CHARACTER SET utf8 NOT NULL COMMENT '是否显示(0否1是)',
                             `type` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单类型',
                             `create_by` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '创建人',
                             `create_date` datetime NOT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                             `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                             `del_flag` char(1) CHARACTER SET utf8 NOT NULL COMMENT '删除标志',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统-菜单表';

-- ----------------------------
-- Records of kali_menu
-- ----------------------------
BEGIN;
INSERT INTO `kali_menu` VALUES ('1438882770994839552', '', '', '', NULL, '总菜单', '/', '', '', '0', '000000', '1', '1', '', '1430109634181881856', '2021-09-17 23:09:20', NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for kali_role
-- ----------------------------
DROP TABLE IF EXISTS `kali_role`;
CREATE TABLE `kali_role` (
                             `id` varchar(32) NOT NULL COMMENT '标识符',
                             `sys_id` varchar(32) DEFAULT NULL COMMENT '系统id',
                             `code` varchar(20) DEFAULT NULL COMMENT '角色代码',
                             `name` varchar(50) NOT NULL COMMENT '角色名称',
                             `en_name` varchar(50) NOT NULL COMMENT '角色英文名称',
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
INSERT INTO `kali_role` VALUES ('1431876295237054464', NULL, '10000', '超级管理员', 'superAdmin', '1', '0', NULL, '1430109634181881856', '2021-08-29 15:08:06', NULL, NULL, '', '0');
INSERT INTO `kali_role` VALUES ('1437679670266036224', NULL, '10001', '管理员', 'admin', '1', '0', NULL, '1430109634181881856', '2021-09-14 15:28:39', NULL, NULL, '', '0');
INSERT INTO `kali_role` VALUES ('1437680385961099264', NULL, '11000', '游客', 'user', '1', '0', NULL, '1430109634181881856', '2021-09-14 15:31:29', NULL, NULL, '', '0');
INSERT INTO `kali_role` VALUES ('1437680664660017152', NULL, '11001', '注册用户', 'registerUser', '1', '0', NULL, '1430109634181881856', '2021-09-14 15:32:36', NULL, NULL, '', '0');
COMMIT;

-- ----------------------------
-- Table structure for kali_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `kali_role_menu`;
CREATE TABLE `kali_role_menu` (
                                  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '唯一标识',
                                  `role_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '角色id',
                                  `menu_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '菜单id',
                                  `element_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '元素id',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  KEY `idx_0` (`role_id`,`menu_id`) USING BTREE,
                                  KEY `idx_1` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统-角色菜单表';

-- ----------------------------
-- Records of kali_role_menu
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
                             `status` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户状态(0正常1锁定)',
                             `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
                             `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                             `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                             `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态(0正常1删除)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统-用户表';

-- ----------------------------
-- Records of kali_user
-- ----------------------------
BEGIN;
INSERT INTO `kali_user` VALUES ('1430066935624323072', '张泱森', '@6y!@2&8@6fyÒ!2c@2af5@°°fa$$0&&2', '2271998412@qq.com', '18234125116', '阿森', '9998', NULL, '2021', '0', '1430109634181881856', '2021-08-24 15:18:21', NULL, NULL, '0');
INSERT INTO `kali_user` VALUES ('1430109634181881856', '超级管理员', '°c2◊20a620&@582a@yÒ6!y2ya!ay$fÒ$', 'admin@mail.com', '18888888888', 'SuperAdmin', '9999', '1431876295237054464', '2021', '0', '', '2021-08-24 18:08:02', NULL, NULL, '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
