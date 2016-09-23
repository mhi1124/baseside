/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50611
Source Host           : 127.0.0.1:3306
Source Database       : xxmall

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2016-09-23 22:40:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `sid` bigint(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(200) DEFAULT '' COMMENT '名称',
  `menuUrl` varchar(200) DEFAULT '' COMMENT 'url',
  `menuStatus` int(10) DEFAULT '0' COMMENT '状态：0，正常；1，暂停使用；2，删除',
  `pid` bigint(11) DEFAULT NULL,
  `menuRemark` varchar(500) DEFAULT '' COMMENT ' 说明',
  `menuType` int(10) DEFAULT NULL COMMENT ' 类型 0:表示菜单，1：按钮',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `parentPath` varchar(100) DEFAULT NULL COMMENT '父菜单的路径',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统基础管理', '', '0', null, '系统基础管理', '0', 'icon-fa fa-list', null);
INSERT INTO `sys_menu` VALUES ('2', '资源管理', '/menu/toMenuList.html', '0', '1', '资源管理', '0', null, null);
INSERT INTO `sys_menu` VALUES ('3', '测试目录', '', '0', null, '测试目录', '0', 'icon-fa fa-list', null);
INSERT INTO `sys_menu` VALUES ('4', '二级目录', '', '0', '3', '二级目录', '0', null, null);
INSERT INTO `sys_menu` VALUES ('5', '三级目录', '', '0', '4', '三级目录', '0', null, null);
INSERT INTO `sys_menu` VALUES ('6', '四级目录', '', '0', '5', '四级目录', '0', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `sid` varchar(32) NOT NULL,
  `roleName` varchar(16) DEFAULT NULL COMMENT '角色名',
  `roleKey` varchar(32) DEFAULT NULL COMMENT '角色key',
  `status` int(1) DEFAULT NULL COMMENT '角色状态 0：正常；1：锁定；2：删除',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述信息',
  `createTime` datetime DEFAULT NULL COMMENT '角色创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '角色更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'administrator', '0', '超级管理员', '2016-09-08 23:19:57', '2016-09-08 23:20:01');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `m_id` varchar(32) DEFAULT NULL COMMENT '菜单或按钮id',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `r_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `u_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `sid` varchar(32) NOT NULL COMMENT 'id',
  `userName` varchar(100) DEFAULT NULL COMMENT '用户真实姓名',
  `accountName` varchar(100) DEFAULT NULL COMMENT '里账户名称统一使用邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `userStatus` int(11) DEFAULT NULL COMMENT '状态：0：正常；1：锁定；2：删除',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `creatorName` varchar(100) DEFAULT NULL COMMENT '创建者accountName',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '577341666@qq.com', '123456', '0', '超级管理员', '0', '2016-09-08 23:22:15', '2016-09-08 23:22:20');

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `sid` varchar(32) NOT NULL COMMENT '用户id',
  `sex` int(1) DEFAULT NULL COMMENT '性别 1:男；2 ：女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `telephone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `createTime` datetime DEFAULT NULL COMMENT '添加日期时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
