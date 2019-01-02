/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : classdesign

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 02/01/2019 11:44:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `id` int(11) NOT NULL COMMENT '床位',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病床状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES (1, '已占用');
INSERT INTO `bed` VALUES (2, '已占用');
INSERT INTO `bed` VALUES (3, '已占用');
INSERT INTO `bed` VALUES (4, '已占用');
INSERT INTO `bed` VALUES (5, '已占用');
INSERT INTO `bed` VALUES (6, '已占用');

-- ----------------------------
-- Table structure for bed_copy1
-- ----------------------------
DROP TABLE IF EXISTS `bed_copy1`;
CREATE TABLE `bed_copy1`  (
  `id` int(11) NOT NULL COMMENT '床位',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病床状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for division
-- ----------------------------
DROP TABLE IF EXISTS `division`;
CREATE TABLE `division`  (
  `divisionid` int(11) NOT NULL COMMENT '部门代号',
  `divisionname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `mainname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主任',
  `funame` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '副主任',
  PRIMARY KEY (`divisionid`) USING BTREE,
  INDEX `divisionname`(`divisionname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of division
-- ----------------------------
INSERT INTO `division` VALUES (1, '院长室', '李东', '陈铭军');
INSERT INTO `division` VALUES (2, '妇产科', '曹盼超', '李昌泽');
INSERT INTO `division` VALUES (3, '激光科', '赵青', '周明');
INSERT INTO `division` VALUES (4, '内分泌科', '小花', '小红');
INSERT INTO `division` VALUES (5, '外科', '曹盼超', '董阮灿');
INSERT INTO `division` VALUES (6, '神经科', '王柳', '王瑶');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `id` int(11) NOT NULL COMMENT '员工号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `division` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在部门',
  `workname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职务',
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学历',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `nativeplace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '籍贯',
  `nationality` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国籍',
  `nation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '民族',
  `numberid` int(11) NULL DEFAULT NULL COMMENT '身份证号',
  `marriage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `Healthy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '健康状况',
  `time` date NULL DEFAULT NULL COMMENT '参加工作时间',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工状态',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'email',
  `workid` int(11) NOT NULL COMMENT '工作岗位',
  PRIMARY KEY (`id`, `workid`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `doctor_ibfk_1`(`workid`, `workname`) USING BTREE,
  INDEX `division`(`division`) USING BTREE,
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`workid`, `workname`) REFERENCES `work` (`workid`, `workname`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`division`) REFERENCES `division` (`divisionname`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (1, '曹盼超', '外科', '麻醉师', '小学', '男', '2018-09-10', '河北', '中国', '汉族', 4100, '否', '是', '2018-09-11', '失恋', '湖南科技大学', '130', 'qq', 1);
INSERT INTO `doctor` VALUES (2, '李东', '院长室', '院长', '博士', '男', '2009-10-09', '中国', '中国', '汉族', 4100, '已婚', '健康', '2018-09-10', '良好', '地球', '11100', '1123', 5);
INSERT INTO `doctor` VALUES (3, '陈铭军', '院长室', '财务', '硕士', '1', '2010-11-10', '1', '1', '1', 1, '1', '1', '1998-10-01', '1', '1', '1', '1', 4);

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '病人姓名',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '入院时间',
  `de` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病人所属科室',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '病人状况',
  `doctor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主治医生',
  `housenumber` int(11) NULL DEFAULT NULL COMMENT '病人房间号',
  `number` int(11) NOT NULL COMMENT '病人病床号',
  PRIMARY KEY (`name`) USING BTREE,
  INDEX `number`(`number`) USING BTREE,
  INDEX `patient`(`doctor`) USING BTREE,
  CONSTRAINT `number` FOREIGN KEY (`number`) REFERENCES `bed` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `patient` FOREIGN KEY (`doctor`) REFERENCES `doctor` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('asd', '你', '1999-09-09 00:00:00', '3', '1', '曹盼超', 1, 6);
INSERT INTO `patient` VALUES ('asda', 'asd', '2018-09-10 00:00:00', '妇产科', '1', '曹盼超', 1, 2);
INSERT INTO `patient` VALUES ('asdaa', '1', '2018-12-26 15:12:11', '1', '1', '李东', 1, 3);
INSERT INTO `patient` VALUES ('asdzz', '1', '2018-12-27 15:19:21', '1', '1', '曹盼超', 1, 5);
INSERT INTO `patient` VALUES ('小明', '男', '2018-01-01 00:00:00', '外科', '重度残废', '李东', 1, 4);
INSERT INTO `patient` VALUES ('杨钰', '男', '2018-09-12 00:00:00', '外科', '受伤', '曹盼超', 1, 1);

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `id` int(11) NOT NULL COMMENT '岗位代号',
  `salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `work` (`workid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES (1, 1000.00);
INSERT INTO `salary` VALUES (2, 2000.00);
INSERT INTO `salary` VALUES (3, 3000.00);
INSERT INTO `salary` VALUES (4, 4000.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `TMS_USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TMS_PSWD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TMS_USER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('caopanchao', 'caopanchao', '曹盼超');
INSERT INTO `user` VALUES ('lidong', 'lidong', '李东');

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`  (
  `workid` int(11) NOT NULL COMMENT '岗位代号',
  `divisionid` int(11) NOT NULL COMMENT '部门代号',
  `workname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位名称',
  PRIMARY KEY (`workid`) USING BTREE,
  INDEX `divisionid`(`divisionid`) USING BTREE,
  INDEX `workid`(`workid`, `divisionid`, `workname`) USING BTREE,
  INDEX `workid_2`(`workid`, `workname`) USING BTREE,
  CONSTRAINT `divisionid` FOREIGN KEY (`divisionid`) REFERENCES `division` (`divisionid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES (1, 2, '麻醉师');
INSERT INTO `work` VALUES (2, 2, '护士');
INSERT INTO `work` VALUES (3, 2, '操刀手');
INSERT INTO `work` VALUES (4, 1, '财务');
INSERT INTO `work` VALUES (5, 1, '院长');
INSERT INTO `work` VALUES (6, 3, '手术师');
INSERT INTO `work` VALUES (7, 4, '内分泌');
INSERT INTO `work` VALUES (8, 5, '药剂师');
INSERT INTO `work` VALUES (9, 5, '魔法师');

SET FOREIGN_KEY_CHECKS = 1;
