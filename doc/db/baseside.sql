/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : baseside

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2016-11-21 23:19:06
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
  `menuStatus` int(10) DEFAULT '0' COMMENT '状态：1，正常；2，暂停使用；3，删除',
  `pid` bigint(11) DEFAULT NULL,
  `menuRemark` varchar(500) DEFAULT '' COMMENT ' 说明',
  `menuType` int(10) DEFAULT NULL COMMENT ' 类型 1:目录;2:菜单;3:按钮',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `parentPath` varchar(100) DEFAULT NULL COMMENT '父菜单的路径',
  `sourceKey` varchar(255) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统基础管理', '', '1', null, '系统基础管理', '2', 'icon-fa fa-list', null, 'system');
INSERT INTO `sys_menu` VALUES ('2', '资源管理', '/sys/menu/toMenuList.html', '1', '1', '资源管理', '2', '', null, 'system:menu');
INSERT INTO `sys_menu` VALUES ('3', '测试目录', '', '1', null, '测试目录', '2', 'fa  fa-beer', null, null);
INSERT INTO `sys_menu` VALUES ('4', '二级目录', '', '1', '3', '二级目录', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('5', '三级目录', '', '1', '4', '三级目录', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('6', '四级目录', '', '1', '5', '四级目录', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('8', '角色管理', '/sys/role/roleListUI.html', '1', '1', '角色管理', '2', 'fa  fa-user', null, 'system:role');
INSERT INTO `sys_menu` VALUES ('9', '用户管理', '/sys/user/userListUI.html', '1', '1', '管理用户', '2', 'fa  fa-child', null, 'system:user');
INSERT INTO `sys_menu` VALUES ('10', '平台管理', '', '1', null, '', '2', 'fa  fa-adjust', null, 'plat');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `sid` varchar(32) NOT NULL,
  `roleName` varchar(16) DEFAULT NULL COMMENT '角色名',
  `roleKey` varchar(32) DEFAULT NULL COMMENT '角色key',
  `status` int(1) DEFAULT NULL COMMENT '角色状态 1：正常；2：锁定；3：删除',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述信息',
  `createTime` datetime DEFAULT NULL COMMENT '角色创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '角色更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'administrator', '1', '超级管理员', '2016-09-08 23:19:57', '2016-09-08 23:20:01');
INSERT INTO `sys_role` VALUES ('31238108032c4f08a71748718f302356', '平台管理员', 'lp', '1', '', '2016-11-20 22:18:34', null);
INSERT INTO `sys_role` VALUES ('4890b4ab58324557adf965b10b4eaeed', '管理员', 'admin', '1', '管理员', '2016-10-19 22:36:33', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `m_id` varchar(32) DEFAULT NULL COMMENT '菜单或按钮id',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('63', '4890b4ab58324557adf965b10b4eaeed', '1');
INSERT INTO `sys_role_menu` VALUES ('64', '4890b4ab58324557adf965b10b4eaeed', '2');
INSERT INTO `sys_role_menu` VALUES ('65', '4890b4ab58324557adf965b10b4eaeed', '8');
INSERT INTO `sys_role_menu` VALUES ('66', '4890b4ab58324557adf965b10b4eaeed', '9');
INSERT INTO `sys_role_menu` VALUES ('67', '31238108032c4f08a71748718f302356', '10');
INSERT INTO `sys_role_menu` VALUES ('68', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('69', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('70', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('71', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('72', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('73', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('74', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('75', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('76', '1', '10');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `r_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `u_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '4890b4ab58324557adf965b10b4eaeed', '30d3656de3aa4fbbac4df908a36e1fc3');
INSERT INTO `sys_role_user` VALUES ('3', '1', '4870adc7173d4fc588a67d2bb2671e6a');
INSERT INTO `sys_role_user` VALUES ('4', '4890b4ab58324557adf965b10b4eaeed', '71641f42104249979309134cecaf3bcb');
INSERT INTO `sys_role_user` VALUES ('5', '31238108032c4f08a71748718f302356', '799c8d9eb53246a0a02a60d42ffdebff');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `sid` varchar(32) NOT NULL COMMENT 'id',
  `userName` varchar(100) DEFAULT NULL COMMENT '用户真实姓名',
  `accountName` varchar(100) DEFAULT NULL COMMENT '里账户名称统一使用邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `userStatus` int(11) DEFAULT NULL COMMENT '状态：1：正常；2：锁定；3：删除',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `creatorName` varchar(100) DEFAULT NULL COMMENT '创建者accountName',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `salt` varchar(500) DEFAULT NULL COMMENT '密码加密盐',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '577341666@qq.com', 'to9Fsi8ObK4WmQ9PSs8g0g==', '1', '超级管理员', '577341666@qq.com', '2016-09-08 23:22:15', '2016-09-08 23:22:20', 'f2307545392defd4dd7226f7c9a90a5f');
INSERT INTO `sys_user` VALUES ('799c8d9eb53246a0a02a60d42ffdebff', 'ff', 'lmr123456@163.com', 'YzfiZeH4q5xX4qCqrxlykg==', '1', 'f', '577341666@qq.com', '2016-11-20 22:19:22', null, 'e2ed440c9003bb3394f5f49319e3fe97');

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
