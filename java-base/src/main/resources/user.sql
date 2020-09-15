/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 15/09/2020 14:15:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(3) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_date_year` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xiaojiang', 2012);
INSERT INTO `user` VALUES (2, 'chenxi', 2018);
INSERT INTO `user` VALUES (3, '晨曦', 2020);
INSERT INTO `user` VALUES (5, '小胡', 1987);
INSERT INTO `user` VALUES (11, 'xiaojiang', 1000);
INSERT INTO `user` VALUES (12, 'name_2', 2002);
INSERT INTO `user` VALUES (13, 'name_3', 2003);
INSERT INTO `user` VALUES (14, 'name_4', 2004);
INSERT INTO `user` VALUES (15, 'name_5', 2005);
INSERT INTO `user` VALUES (16, 'name_6', 2006);
INSERT INTO `user` VALUES (17, 'name_7', 2007);
INSERT INTO `user` VALUES (18, 'name_8', 2008);
INSERT INTO `user` VALUES (19, 'name_9', 2009);

SET FOREIGN_KEY_CHECKS = 1;
