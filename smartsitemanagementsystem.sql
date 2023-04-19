SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm
-- ----------------------------
DROP TABLE IF EXISTS `alarm`;
CREATE TABLE `alarm`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `moment` datetime(0) NULL DEFAULT NULL,
  `classes` enum('1','0') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  `job_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `handling` enum('1','0') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_alarm_staff_on_jobno`(`job_no`) USING BTREE,
  CONSTRAINT `fk_alarm_staff_on_jobno` FOREIGN KEY (`job_no`) REFERENCES `staff` (`job_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `classes` enum('1','0') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `moment` date NULL DEFAULT NULL,
  `job_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `enter_moment` time(0) NULL DEFAULT NULL,
  `leave_moment` time(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_attendance_staff_on_jobno`(`job_no`) USING BTREE,
  CONSTRAINT `fk_attendance_staff_on_jobno` FOREIGN KEY (`job_no`) REFERENCES `staff` (`job_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, '2022-12-11', 'qwe001', '09:57:12', '18:09:23');
INSERT INTO `attendance` VALUES (2, '2022-12-11', 'qwe002', '09:53:58', '18:00:50');
INSERT INTO `attendance` VALUES (3, '2022-12-11', 'qwe003', '09:57:24', '18:06:43');
INSERT INTO `attendance` VALUES (4, '2022-12-11', 'qwe004', '09:51:10', '18:04:45');
INSERT INTO `attendance` VALUES (5, '2022-12-11', 'qwe005', '10:05:01', '18:01:58');
INSERT INTO `attendance` VALUES (6, '2022-12-11', 'qwe006', '09:50:37', '17:56:06');
INSERT INTO `attendance` VALUES (7, '2022-12-11', 'qwe007', '10:01:42', '17:50:31');
INSERT INTO `attendance` VALUES (8, '2022-12-11', 'qwe008', '09:51:26', '17:53:26');
INSERT INTO `attendance` VALUES (9, '2022-12-11', 'qwe009', '10:02:41', '18:05:01');
INSERT INTO `attendance` VALUES (10, '2022-12-11', 'qwe010', '09:56:39', '18:00:02');
INSERT INTO `attendance` VALUES (11, '2022-12-11', 'qwe011', '10:06:40', '17:52:26');
INSERT INTO `attendance` VALUES (12, '2022-12-11', 'qwe012', '10:00:05', '17:56:37');
INSERT INTO `attendance` VALUES (13, '2022-12-11', 'qwe013', '09:55:00', '18:05:16');
INSERT INTO `attendance` VALUES (14, '2022-12-11', 'qwe014', '09:53:38', '18:03:35');
INSERT INTO `attendance` VALUES (15, '2022-12-11', 'qwe015', '10:02:53', '17:58:06');
INSERT INTO `attendance` VALUES (16, '2022-12-11', 'qwe016', '10:07:18', '18:08:02');
INSERT INTO `attendance` VALUES (17, '2022-12-11', 'qwe017', '10:09:59', '17:56:20');
INSERT INTO `attendance` VALUES (18, '2022-12-11', 'qwe018', '10:08:41', '18:06:21');
INSERT INTO `attendance` VALUES (19, '2022-12-11', 'qwe019', '09:53:33', '17:57:03');
INSERT INTO `attendance` VALUES (20, '2022-12-11', 'qwe020', '09:58:52', '17:56:24');
INSERT INTO `attendance` VALUES (21, '2022-12-11', 'qwe021', '10:01:27', '18:05:45');
INSERT INTO `attendance` VALUES (22, '2022-12-11', 'qwe022', '10:01:46', '18:07:52');
INSERT INTO `attendance` VALUES (23, '2022-12-11', 'qwe023', '10:09:38', '18:00:13');
INSERT INTO `attendance` VALUES (24, '2022-12-11', 'qwe024', '09:50:50', '17:56:13');
INSERT INTO `attendance` VALUES (25, '2022-12-11', 'qwe025', '10:09:07', '17:52:45');
INSERT INTO `attendance` VALUES (26, '2022-12-11', 'qwe026', '10:09:02', '18:04:00');
INSERT INTO `attendance` VALUES (27, '2022-12-11', 'qwe027', '10:05:54', '18:02:03');
INSERT INTO `attendance` VALUES (28, '2022-12-11', 'qwe028', '09:54:30', '18:06:39');
INSERT INTO `attendance` VALUES (29, '2022-12-11', 'qwe029', '09:54:09', '18:08:19');
INSERT INTO `attendance` VALUES (30, '2022-12-11', 'qwe030', '10:09:00', '18:01:22');
INSERT INTO `attendance` VALUES (31, '2022-12-11', 'qwe031', '10:06:43', '17:51:31');
INSERT INTO `attendance` VALUES (32, '2022-12-11', 'qwe032', '09:52:22', '18:01:46');
INSERT INTO `attendance` VALUES (33, '2022-12-11', 'qwe033', '10:09:52', '17:56:05');
INSERT INTO `attendance` VALUES (34, '2022-12-11', 'qwe034', '09:52:54', '17:53:22');
INSERT INTO `attendance` VALUES (35, '2022-12-11', 'qwe035', '10:08:27', '18:03:21');
INSERT INTO `attendance` VALUES (36, '2022-12-11', 'qwe036', '10:03:01', '17:59:29');
INSERT INTO `attendance` VALUES (37, '2022-12-11', 'qwe037', '09:57:12', '17:58:45');
INSERT INTO `attendance` VALUES (38, '2022-12-11', 'qwe038', '10:03:55', '18:07:02');
INSERT INTO `attendance` VALUES (39, '2022-12-11', 'qwe039', '10:04:49', '17:50:16');
INSERT INTO `attendance` VALUES (40, '2022-12-11', 'qwe040', '09:57:48', '17:52:49');
INSERT INTO `attendance` VALUES (41, '2022-12-11', 'qwe041', '10:04:04', '17:50:59');
INSERT INTO `attendance` VALUES (42, '2022-12-11', 'qwe042', '10:08:25', '18:06:07');
INSERT INTO `attendance` VALUES (43, '2022-12-11', 'qwe043', '10:05:49', '18:06:13');
INSERT INTO `attendance` VALUES (44, '2022-12-12', 'qwe001', '09:58:54', '18:07:48');
INSERT INTO `attendance` VALUES (45, '2022-12-12', 'qwe002', '10:04:46', '17:55:13');
INSERT INTO `attendance` VALUES (46, '2022-12-12', 'qwe003', '09:53:02', '17:54:46');
INSERT INTO `attendance` VALUES (47, '2022-12-12', 'qwe004', '09:51:17', '17:55:18');
INSERT INTO `attendance` VALUES (48, '2022-12-12', 'qwe005', '09:59:16', '18:03:53');
INSERT INTO `attendance` VALUES (49, '2022-12-12', 'qwe006', '09:52:57', '18:02:13');
INSERT INTO `attendance` VALUES (50, '2022-12-12', 'qwe007', '09:50:59', '18:00:15');
INSERT INTO `attendance` VALUES (51, '2022-12-12', 'qwe008', '09:55:48', '17:50:53');
INSERT INTO `attendance` VALUES (52, '2022-12-12', 'qwe009', '10:02:45', '18:00:43');
INSERT INTO `attendance` VALUES (53, '2022-12-12', 'qwe010', '09:54:12', '17:54:53');
INSERT INTO `attendance` VALUES (54, '2022-12-12', 'qwe011', '10:00:05', '18:01:42');
INSERT INTO `attendance` VALUES (55, '2022-12-12', 'qwe012', '09:50:48', '17:50:22');
INSERT INTO `attendance` VALUES (56, '2022-12-12', 'qwe013', '09:56:56', '18:01:38');
INSERT INTO `attendance` VALUES (57, '2022-12-12', 'qwe014', '09:59:35', '18:06:39');
INSERT INTO `attendance` VALUES (58, '2022-12-12', 'qwe015', '09:58:54', '17:54:57');
INSERT INTO `attendance` VALUES (59, '2022-12-12', 'qwe016', '09:51:57', '17:59:21');
INSERT INTO `attendance` VALUES (60, '2022-12-12', 'qwe017', '10:02:16', '18:01:04');
INSERT INTO `attendance` VALUES (61, '2022-12-12', 'qwe018', '09:52:33', '17:54:00');
INSERT INTO `attendance` VALUES (62, '2022-12-12', 'qwe019', '09:51:56', '18:04:19');
INSERT INTO `attendance` VALUES (63, '2022-12-12', 'qwe020', '09:56:21', '18:05:01');
INSERT INTO `attendance` VALUES (64, '2022-12-12', 'qwe021', '09:54:33', '18:02:17');
INSERT INTO `attendance` VALUES (65, '2022-12-12', 'qwe022', '10:04:45', '18:06:51');
INSERT INTO `attendance` VALUES (66, '2022-12-12', 'qwe023', '10:03:17', '18:08:08');
INSERT INTO `attendance` VALUES (67, '2022-12-12', 'qwe024', '09:57:02', '17:57:01');
INSERT INTO `attendance` VALUES (68, '2022-12-12', 'qwe025', '10:04:16', '18:01:26');
INSERT INTO `attendance` VALUES (69, '2022-12-12', 'qwe026', '09:51:14', '18:00:36');
INSERT INTO `attendance` VALUES (70, '2022-12-12', 'qwe027', '09:57:09', '17:50:19');
INSERT INTO `attendance` VALUES (71, '2022-12-12', 'qwe028', '10:03:35', '17:53:09');
INSERT INTO `attendance` VALUES (72, '2022-12-12', 'qwe029', '10:04:50', '18:03:28');
INSERT INTO `attendance` VALUES (73, '2022-12-12', 'qwe030', '09:52:31', '18:01:38');
INSERT INTO `attendance` VALUES (74, '2022-12-12', 'qwe031', '10:04:18', '17:52:13');
INSERT INTO `attendance` VALUES (75, '2022-12-12', 'qwe032', '09:52:10', '18:02:29');
INSERT INTO `attendance` VALUES (76, '2022-12-12', 'qwe033', '09:55:00', '17:55:18');
INSERT INTO `attendance` VALUES (77, '2022-12-12', 'qwe034', '09:51:52', '18:09:11');
INSERT INTO `attendance` VALUES (78, '2022-12-12', 'qwe035', '09:53:42', '18:02:03');
INSERT INTO `attendance` VALUES (79, '2022-12-12', 'qwe036', '09:55:55', '18:06:04');
INSERT INTO `attendance` VALUES (80, '2022-12-12', 'qwe037', '09:59:39', '17:57:56');
INSERT INTO `attendance` VALUES (81, '2022-12-12', 'qwe038', '09:57:29', '18:05:23');
INSERT INTO `attendance` VALUES (82, '2022-12-12', 'qwe039', '09:52:02', '18:01:16');
INSERT INTO `attendance` VALUES (83, '2022-12-12', 'qwe040', '10:00:16', '17:58:05');
INSERT INTO `attendance` VALUES (84, '2022-12-12', 'qwe041', '10:01:33', '18:02:41');
INSERT INTO `attendance` VALUES (85, '2022-12-12', 'qwe042', '09:50:50', '18:01:44');
INSERT INTO `attendance` VALUES (86, '2022-12-12', 'qwe043', '10:00:38', '17:56:30');
INSERT INTO `attendance` VALUES (87, '2022-12-13', 'qwe001', '10:00:57', '17:55:41');
INSERT INTO `attendance` VALUES (88, '2022-12-13', 'qwe002', '09:54:02', '18:08:40');
INSERT INTO `attendance` VALUES (89, '2022-12-13', 'qwe003', '10:01:05', '17:50:07');
INSERT INTO `attendance` VALUES (90, '2022-12-13', 'qwe004', '09:58:45', '17:56:07');
INSERT INTO `attendance` VALUES (91, '2022-12-13', 'qwe005', '09:54:47', '17:51:23');
INSERT INTO `attendance` VALUES (92, '2022-12-13', 'qwe006', '09:51:38', '18:07:55');
INSERT INTO `attendance` VALUES (93, '2022-12-13', 'qwe007', '10:02:40', '18:05:16');
INSERT INTO `attendance` VALUES (94, '2022-12-13', 'qwe008', '09:57:06', '17:58:32');
INSERT INTO `attendance` VALUES (95, '2022-12-13', 'qwe009', '09:50:54', '17:55:59');
INSERT INTO `attendance` VALUES (96, '2022-12-13', 'qwe010', '09:50:49', '17:59:19');
INSERT INTO `attendance` VALUES (97, '2022-12-13', 'qwe011', '09:57:30', '17:56:03');
INSERT INTO `attendance` VALUES (98, '2022-12-13', 'qwe012', '10:03:06', '17:50:37');
INSERT INTO `attendance` VALUES (99, '2022-12-13', 'qwe013', '10:04:29', '18:04:11');
INSERT INTO `attendance` VALUES (100, '2022-12-13', 'qwe014', '09:58:56', '17:53:20');
INSERT INTO `attendance` VALUES (101, '2022-12-13', 'qwe015', '09:59:19', '18:05:25');
INSERT INTO `attendance` VALUES (102, '2022-12-13', 'qwe016', '09:57:09', '17:57:00');
INSERT INTO `attendance` VALUES (103, '2022-12-13', 'qwe017', '09:56:16', '18:09:59');
INSERT INTO `attendance` VALUES (104, '2022-12-13', 'qwe018', '10:01:21', '17:56:46');
INSERT INTO `attendance` VALUES (105, '2022-12-13', 'qwe019', '09:55:54', '18:01:03');
INSERT INTO `attendance` VALUES (106, '2022-12-13', 'qwe020', '10:00:32', '18:09:13');
INSERT INTO `attendance` VALUES (107, '2022-12-13', 'qwe021', '10:03:45', '17:56:16');
INSERT INTO `attendance` VALUES (108, '2022-12-13', 'qwe022', '09:50:34', '18:08:59');
INSERT INTO `attendance` VALUES (109, '2022-12-13', 'qwe023', '09:57:13', '17:52:03');
INSERT INTO `attendance` VALUES (110, '2022-12-13', 'qwe024', '09:55:23', '18:07:05');
INSERT INTO `attendance` VALUES (111, '2022-12-13', 'qwe025', '09:59:42', '18:05:50');
INSERT INTO `attendance` VALUES (112, '2022-12-13', 'qwe026', '09:52:00', '18:02:38');
INSERT INTO `attendance` VALUES (113, '2022-12-13', 'qwe027', '10:00:26', '17:50:26');
INSERT INTO `attendance` VALUES (114, '2022-12-13', 'qwe028', '09:56:11', '17:57:18');
INSERT INTO `attendance` VALUES (115, '2022-12-13', 'qwe029', '09:57:47', '17:55:09');
INSERT INTO `attendance` VALUES (116, '2022-12-13', 'qwe030', '09:59:22', '18:03:30');
INSERT INTO `attendance` VALUES (117, '2022-12-13', 'qwe031', '09:53:37', '18:06:19');
INSERT INTO `attendance` VALUES (118, '2022-12-13', 'qwe032', '09:53:23', '18:05:09');
INSERT INTO `attendance` VALUES (119, '2022-12-13', 'qwe033', '09:52:10', '17:52:03');
INSERT INTO `attendance` VALUES (120, '2022-12-13', 'qwe034', '09:52:31', '18:03:49');
INSERT INTO `attendance` VALUES (121, '2022-12-13', 'qwe035', '09:55:31', '17:51:58');
INSERT INTO `attendance` VALUES (122, '2022-12-13', 'qwe036', '09:51:07', '17:56:17');
INSERT INTO `attendance` VALUES (123, '2022-12-13', 'qwe037', '10:00:17', '18:03:55');
INSERT INTO `attendance` VALUES (124, '2022-12-13', 'qwe038', '09:52:47', '17:51:30');
INSERT INTO `attendance` VALUES (125, '2022-12-13', 'qwe039', '09:55:01', '17:51:37');
INSERT INTO `attendance` VALUES (126, '2022-12-13', 'qwe040', '09:56:56', '17:50:12');
INSERT INTO `attendance` VALUES (127, '2022-12-13', 'qwe041', '09:58:13', '17:50:36');
INSERT INTO `attendance` VALUES (128, '2022-12-13', 'qwe042', '10:02:56', '18:01:42');
INSERT INTO `attendance` VALUES (129, '2022-12-13', 'qwe043', '10:04:29', '17:53:45');
INSERT INTO `attendance` VALUES (130, '2023-04-14', 'qwe001', '11:42:42', '11:59:39');
INSERT INTO `attendance` VALUES (131, '2023-04-15', NULL, '15:24:14', NULL);
INSERT INTO `attendance` VALUES (132, '2023-04-15', 'qwe001', '15:25:20', NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `symbol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'test', '测试部');
INSERT INTO `department` VALUES (2, 'project', '项目部');
INSERT INTO `department` VALUES (3, 'personnel', '人事部');
INSERT INTO `department` VALUES (4, 'security', '安全部');
INSERT INTO `department` VALUES (5, 'quality', '质量部');
INSERT INTO `department` VALUES (6, 'Logistics', '后勤部');

-- ----------------------------
-- Table structure for func
-- ----------------------------
DROP TABLE IF EXISTS `func`;
CREATE TABLE `func`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `menu_id` int(0) UNSIGNED NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_func_menu_on_menuid`(`menu_id`) USING BTREE,
  CONSTRAINT `fk_func_menu_on_menuid` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func
-- ----------------------------
INSERT INTO `func` VALUES (3, 1, '项目人员管理', 'PerManagement');
INSERT INTO `func` VALUES (4, 1, '人员报表查询', 'PerReport');
INSERT INTO `func` VALUES (5, 2, '人员实时分布监测', 'PerDistribution');
INSERT INTO `func` VALUES (6, 2, '人员信息监测', 'PerInfoMonitoring');
INSERT INTO `func` VALUES (7, 3, '人员实时轨迹', 'PerRealTimeTrack');
INSERT INTO `func` VALUES (8, 3, '人员统计信息', 'PerInfoStatistics');
INSERT INTO `func` VALUES (9, 4, '考勤管理', 'AttManagement');
INSERT INTO `func` VALUES (10, 5, '区域管理', 'RegionalManagement');
INSERT INTO `func` VALUES (11, 6, '告警管理', 'AlarmManagement');
INSERT INTO `func` VALUES (12, 7, '组织结构管理', 'null');
INSERT INTO `func` VALUES (13, 7, '管理人员设置', 'null');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `job_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `permissions` enum('1','0','2') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_login_staff_on_jobno`(`job_no`) USING BTREE,
  CONSTRAINT `fk_login_staff_on_jobno` FOREIGN KEY (`job_no`) REFERENCES `staff` (`job_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES (1, 'qwe001', '123', '1');
INSERT INTO `login` VALUES (2, 'qwe002', '123', '0');
INSERT INTO `login` VALUES (3, 'qwe003', '123', '0');
INSERT INTO `login` VALUES (4, 'qwe004', '123', '0');
INSERT INTO `login` VALUES (5, 'qwe005', '123', '0');
INSERT INTO `login` VALUES (6, 'qwe006', '123', '1');
INSERT INTO `login` VALUES (7, 'qwe007', '123', '0');
INSERT INTO `login` VALUES (8, 'qwe008', '123', '0');
INSERT INTO `login` VALUES (9, 'qwe009', '123', '0');
INSERT INTO `login` VALUES (10, 'qwe010', '123', '0');
INSERT INTO `login` VALUES (11, 'qwe011', '123', '0');
INSERT INTO `login` VALUES (12, 'qwe012', '123', '0');
INSERT INTO `login` VALUES (13, 'qwe013', '123', '0');
INSERT INTO `login` VALUES (14, 'qwe014', '123', '0');
INSERT INTO `login` VALUES (15, 'qwe015', '123', '1');
INSERT INTO `login` VALUES (16, 'qwe016', '123', '0');
INSERT INTO `login` VALUES (17, 'qwe017', '123', '0');
INSERT INTO `login` VALUES (18, 'qwe018', '123', '0');
INSERT INTO `login` VALUES (19, 'qwe019', '123', '0');
INSERT INTO `login` VALUES (20, 'qwe020', '123', '0');
INSERT INTO `login` VALUES (21, 'qwe021', '123', '0');
INSERT INTO `login` VALUES (22, 'qwe022', '123', '0');
INSERT INTO `login` VALUES (23, 'qwe023', '123', '0');
INSERT INTO `login` VALUES (24, 'qwe024', '123', '0');
INSERT INTO `login` VALUES (25, 'qwe025', '123', '0');
INSERT INTO `login` VALUES (26, 'qwe026', '123', '1');
INSERT INTO `login` VALUES (27, 'qwe027', '123', '0');
INSERT INTO `login` VALUES (28, 'qwe028', '123', '0');
INSERT INTO `login` VALUES (29, 'qwe029', '123', '0');
INSERT INTO `login` VALUES (30, 'qwe030', '123', '1');
INSERT INTO `login` VALUES (31, 'qwe031', '123', '0');
INSERT INTO `login` VALUES (32, 'qwe032', '123', '0');
INSERT INTO `login` VALUES (33, 'qwe033', '123', '0');
INSERT INTO `login` VALUES (34, 'qwe034', '123', '0');
INSERT INTO `login` VALUES (35, 'qwe035', '123', '0');
INSERT INTO `login` VALUES (36, 'qwe036', '123', '0');
INSERT INTO `login` VALUES (37, 'qwe037', '123', '0');
INSERT INTO `login` VALUES (38, 'qwe038', '123', '0');
INSERT INTO `login` VALUES (39, 'qwe039', '123', '1');
INSERT INTO `login` VALUES (40, 'qwe040', '123', '0');
INSERT INTO `login` VALUES (41, 'qwe041', '123', '0');
INSERT INTO `login` VALUES (42, 'qwe042', '123', '0');
INSERT INTO `login` VALUES (43, 'qwe043', '123', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '人员信息管理', 'el-icon-user');
INSERT INTO `menu` VALUES (2, '实时监测', 'el-icon-monitor');
INSERT INTO `menu` VALUES (3, '轨迹管理', 'el-icon-location');
INSERT INTO `menu` VALUES (4, '考勤管理', 'el-icon-coffee-cup');
INSERT INTO `menu` VALUES (5, '区域管理', 'el-icon-coordinate');
INSERT INTO `menu` VALUES (6, '告警管理', 'el-icon-view');
INSERT INTO `menu` VALUES (7, '超级管理员', 'el-icon-s-custom');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `work_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `limited_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `start_time` time(0) NULL DEFAULT NULL,
  `closing_time` time(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, 'A区建设项目组', ' ', ' ', '09:00:00', '17:00:00');
INSERT INTO `project` VALUES (2, 'B区建设项目组', '', '', '10:00:00', '18:00:00');
INSERT INTO `project` VALUES (3, 'C区建设项目组', '', '', '10:00:00', '18:00:00');
INSERT INTO `project` VALUES (4, 'A区检验组', '', '', '10:00:00', '18:00:00');
INSERT INTO `project` VALUES (5, 'B区检验组', '', '', '11:00:00', '19:00:00');
INSERT INTO `project` VALUES (6, 'C区检验组', '', '', '11:00:00', '19:00:00');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `job_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `department_id` int(0) UNSIGNED NULL DEFAULT NULL,
  `profession` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `is_online` enum('在线','离线') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '离线',
  `project_id` int(0) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `job_no`(`job_no`) USING BTREE,
  INDEX `fk_staff_department_on_departmentid`(`department_id`) USING BTREE,
  INDEX `fk_staff_project_on_projectid`(`project_id`) USING BTREE,
  CONSTRAINT `fk_staff_department_on_departmentid` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_project_on_projectid` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'qwe001', '张三', 1, '技术员', '离线', 1);
INSERT INTO `staff` VALUES (2, 'qwe002', '李四', 1, '技术员', '离线', 1);
INSERT INTO `staff` VALUES (3, 'qwe003', '王二', 1, '工程师', '离线', 1);
INSERT INTO `staff` VALUES (4, 'qwe004', '赵钱', 2, '项目主管', '离线', 1);
INSERT INTO `staff` VALUES (5, 'qwe005', '孙李', 2, '施工员', '离线', 2);
INSERT INTO `staff` VALUES (6, 'qwe006', '周吴', 3, '人事', '离线', 3);
INSERT INTO `staff` VALUES (7, 'qwe007', '郑王', 4, '验收员', '离线', 4);
INSERT INTO `staff` VALUES (8, 'qwe008', '冯陈', 4, '验收员', '离线', 5);
INSERT INTO `staff` VALUES (9, 'qwe009', '禇卫', 6, '大厨', '离线', 6);
INSERT INTO `staff` VALUES (10, 'qwe010', '蒋沈', 6, '副厨', '离线', 4);
INSERT INTO `staff` VALUES (11, 'qwe011', '韩杨', 5, '质量审核员', '离线', 2);
INSERT INTO `staff` VALUES (12, 'qwe012', '朱秦', 1, '工程师', '离线', 1);
INSERT INTO `staff` VALUES (13, 'qwe013', '尤许', 2, '项目主管', '离线', 1);
INSERT INTO `staff` VALUES (14, 'qwe014', '何吕', 2, '施工员', '离线', 2);
INSERT INTO `staff` VALUES (15, 'qwe015', '施张', 3, '人事', '离线', 1);
INSERT INTO `staff` VALUES (16, 'qwe016', '孔曹', 4, '验收员', '离线', 2);
INSERT INTO `staff` VALUES (17, 'qwe017', '严华', 4, '验收员', '离线', 4);
INSERT INTO `staff` VALUES (18, 'qwe018', '金魏', 6, '大厨', '离线', 3);
INSERT INTO `staff` VALUES (19, 'qwe019', '陶姜', 6, '副厨', '离线', 4);
INSERT INTO `staff` VALUES (20, 'qwe020', '戚谢', 5, '质量审核员', '离线', 5);
INSERT INTO `staff` VALUES (21, 'qwe021', '邹喻', 1, '技术员', '离线', 6);
INSERT INTO `staff` VALUES (22, 'qwe022', '柏水', 1, '技术员', '离线', 5);
INSERT INTO `staff` VALUES (23, 'qwe023', '窦章', 1, '工程师', '离线', 6);
INSERT INTO `staff` VALUES (24, 'qwe024', '云苏', 2, '项目主管', '离线', 4);
INSERT INTO `staff` VALUES (25, 'qwe025', '潘葛', 2, '施工员', '离线', 2);
INSERT INTO `staff` VALUES (26, 'qwe026', '奚范', 3, '人事', '离线', 1);
INSERT INTO `staff` VALUES (27, 'qwe027', '彭郎', 1, '工程师', '离线', 1);
INSERT INTO `staff` VALUES (28, 'qwe028', '鲁韦', 2, '项目主管', '离线', 1);
INSERT INTO `staff` VALUES (29, 'qwe029', '昌马', 2, '施工员', '离线', 2);
INSERT INTO `staff` VALUES (30, 'qwe030', '苗凤', 3, '人事', '离线', 3);
INSERT INTO `staff` VALUES (31, 'qwe031', '花方', 4, '验收员', '离线', 4);
INSERT INTO `staff` VALUES (32, 'qwe032', '俞任', 4, '验收员', '离线', 5);
INSERT INTO `staff` VALUES (33, 'qwe033', '袁柳', 6, '大厨', '离线', 6);
INSERT INTO `staff` VALUES (34, 'qwe034', '酆鲍', 6, '副厨', '离线', 4);
INSERT INTO `staff` VALUES (35, 'qwe035', '史唐', 5, '质量审核员', '离线', 2);
INSERT INTO `staff` VALUES (36, 'qwe036', '费廉', 1, '工程师', '离线', 1);
INSERT INTO `staff` VALUES (37, 'qwe037', '岑薛', 2, '项目主管', '离线', 1);
INSERT INTO `staff` VALUES (38, 'qwe038', '雷贺', 2, '施工员', '离线', 2);
INSERT INTO `staff` VALUES (39, 'qwe039', '倪汤', 3, '人事', '离线', 1);
INSERT INTO `staff` VALUES (40, 'qwe040', '滕殷', 4, '验收员', '离线', 2);
INSERT INTO `staff` VALUES (41, 'qwe041', '罗毕', 4, '验收员', '离线', 4);
INSERT INTO `staff` VALUES (42, 'qwe042', '郝邬', 6, '大厨', '离线', 3);
INSERT INTO `staff` VALUES (43, 'qwe043', '安常', 1, '技术员', '离线', 1);

-- ----------------------------
-- Table structure for trajectory
-- ----------------------------
DROP TABLE IF EXISTS `trajectory`;
CREATE TABLE `trajectory`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `job_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  `moment` datetime(0) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT ' ',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_trajectory_staff_on_jobno`(`job_no`) USING BTREE,
  CONSTRAINT `fk_trajectory_staff_on_jobno` FOREIGN KEY (`job_no`) REFERENCES `staff` (`job_no`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trajectory
-- ----------------------------
INSERT INTO `trajectory` VALUES (1, 'qwe001', '2022-12-12 22:08:34', '118.056953, 24.380213');
INSERT INTO `trajectory` VALUES (2, 'qwe002', '2022-12-12 22:08:34', '118.056953, 24.380213');
INSERT INTO `trajectory` VALUES (3, 'qwe003', '2022-12-12 22:08:34', '118.056953, 24.380214');
INSERT INTO `trajectory` VALUES (4, 'qwe004', '2022-12-12 22:08:34', '118.056953, 24.380215');
INSERT INTO `trajectory` VALUES (5, 'qwe005', '2022-12-12 22:08:34', '118.056953, 24.380216');
INSERT INTO `trajectory` VALUES (6, 'qwe006', '2022-12-12 22:08:34', '118.056953, 24.380217');
INSERT INTO `trajectory` VALUES (7, 'qwe007', '2022-12-12 22:08:34', '118.056953, 24.380218');
INSERT INTO `trajectory` VALUES (8, 'qwe008', '2022-12-12 22:08:34', '118.056953, 24.380219');
INSERT INTO `trajectory` VALUES (9, 'qwe009', '2022-12-12 22:08:34', '118.056953, 24.380220');
INSERT INTO `trajectory` VALUES (10, 'qwe010', '2022-12-12 22:08:34', '118.056953, 24.380221');
INSERT INTO `trajectory` VALUES (11, 'qwe011', '2022-12-12 22:08:34', '118.056953, 24.380222');
INSERT INTO `trajectory` VALUES (12, 'qwe012', '2022-12-12 22:08:34', '118.056953, 24.380223');
INSERT INTO `trajectory` VALUES (13, 'qwe013', '2022-12-12 22:08:34', '118.056953, 24.380224');
INSERT INTO `trajectory` VALUES (14, 'qwe014', '2022-12-12 22:08:34', '118.056953, 24.380225');
INSERT INTO `trajectory` VALUES (15, 'qwe015', '2022-12-12 22:08:34', '118.056953, 24.380226');
INSERT INTO `trajectory` VALUES (16, 'qwe016', '2022-12-12 22:08:34', '118.056953, 24.380227');
INSERT INTO `trajectory` VALUES (17, 'qwe017', '2022-12-12 22:08:34', '118.056953, 24.380228');
INSERT INTO `trajectory` VALUES (18, 'qwe018', '2022-12-12 22:08:34', '118.056953, 24.380229');
INSERT INTO `trajectory` VALUES (19, 'qwe019', '2022-12-12 22:08:34', '118.056953, 24.380230');
INSERT INTO `trajectory` VALUES (20, 'qwe020', '2022-12-12 22:08:34', '118.056953, 24.380231');
INSERT INTO `trajectory` VALUES (21, 'qwe021', '2022-12-12 22:08:34', '118.056953, 24.380232');
INSERT INTO `trajectory` VALUES (22, 'qwe022', '2022-12-12 22:08:34', '118.056953, 24.380233');
INSERT INTO `trajectory` VALUES (23, 'qwe023', '2022-12-12 22:08:34', '118.056953, 24.380234');
INSERT INTO `trajectory` VALUES (24, 'qwe024', '2022-12-12 22:08:34', '118.056953, 24.380235');
INSERT INTO `trajectory` VALUES (25, 'qwe025', '2022-12-12 22:08:34', '118.056953, 24.380236');
INSERT INTO `trajectory` VALUES (26, 'qwe026', '2022-12-12 22:08:34', '118.056953, 24.380237');
INSERT INTO `trajectory` VALUES (27, 'qwe027', '2022-12-12 22:08:34', '118.056953, 24.380238');
INSERT INTO `trajectory` VALUES (28, 'qwe028', '2022-12-12 22:08:34', '118.056953, 24.380239');
INSERT INTO `trajectory` VALUES (29, 'qwe029', '2022-12-12 22:08:34', '118.056953, 24.380240');
INSERT INTO `trajectory` VALUES (30, 'qwe030', '2022-12-12 22:08:34', '118.056953, 24.380241');
INSERT INTO `trajectory` VALUES (31, 'qwe031', '2022-12-12 22:08:34', '118.056953, 24.380242');
INSERT INTO `trajectory` VALUES (32, 'qwe032', '2022-12-12 22:08:34', '118.056953, 24.380243');
INSERT INTO `trajectory` VALUES (33, 'qwe033', '2022-12-12 22:08:34', '118.056953, 24.380244');
INSERT INTO `trajectory` VALUES (34, 'qwe034', '2022-12-12 22:08:34', '118.056953, 24.380245');
INSERT INTO `trajectory` VALUES (35, 'qwe035', '2022-12-12 22:08:34', '118.056953, 24.380246');
INSERT INTO `trajectory` VALUES (36, 'qwe036', '2022-12-12 22:08:34', '118.056953, 24.380247');
INSERT INTO `trajectory` VALUES (37, 'qwe037', '2022-12-12 22:08:34', '118.056953, 24.380248');
INSERT INTO `trajectory` VALUES (38, 'qwe038', '2022-12-12 22:08:34', '118.056953, 24.380249');
INSERT INTO `trajectory` VALUES (39, 'qwe039', '2022-12-12 22:08:34', '118.056953, 24.380250');
INSERT INTO `trajectory` VALUES (40, 'qwe040', '2022-12-12 22:08:34', '118.056953, 24.380251');
INSERT INTO `trajectory` VALUES (41, 'qwe041', '2022-12-12 22:08:34', '118.056953, 24.380252');
INSERT INTO `trajectory` VALUES (42, 'qwe042', '2022-12-12 22:08:34', '118.056953, 24.380253');
INSERT INTO `trajectory` VALUES (43, 'qwe043', '2022-12-12 22:08:34', '118.056953, 24.380254');
INSERT INTO `trajectory` VALUES (44, 'qwe001', '2022-12-12 22:36:32', '118.057074, 24.380073 ');
INSERT INTO `trajectory` VALUES (45, 'qwe002', '2022-12-12 22:36:32', '118.057074, 24.380073 ');
INSERT INTO `trajectory` VALUES (46, 'qwe003', '2022-12-12 22:36:32', '118.057074, 24.380073 ');
INSERT INTO `trajectory` VALUES (47, 'qwe004', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (48, 'qwe005', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (49, 'qwe006', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (50, 'qwe007', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (51, 'qwe008', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (52, 'qwe009', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (53, 'qwe010', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (54, 'qwe011', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (55, 'qwe012', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (56, 'qwe013', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (57, 'qwe014', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (58, 'qwe015', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (59, 'qwe016', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (60, 'qwe017', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (61, 'qwe018', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (62, 'qwe019', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (63, 'qwe020', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (64, 'qwe021', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (65, 'qwe022', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (66, 'qwe023', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (67, 'qwe024', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (68, 'qwe025', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (69, 'qwe026', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (70, 'qwe027', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (71, 'qwe028', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (72, 'qwe029', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (73, 'qwe030', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (74, 'qwe031', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (75, 'qwe032', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (76, 'qwe033', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (77, 'qwe034', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (78, 'qwe035', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (79, 'qwe036', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (80, 'qwe037', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (81, 'qwe038', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (82, 'qwe039', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (83, 'qwe040', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (84, 'qwe041', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (85, 'qwe042', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (86, 'qwe043', '2022-12-12 22:36:32', '118.057074, 24.380073');
INSERT INTO `trajectory` VALUES (87, 'qwe001', '2023-03-16 11:52:49', '118.176733,24.497066');
INSERT INTO `trajectory` VALUES (93, 'qwe001', '2023-03-16 13:58:21', 'location');
INSERT INTO `trajectory` VALUES (94, 'qwe001', '2023-04-17 14:22:18', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (95, 'qwe001', '2023-04-17 14:23:05', '122.405303,39.901539');
INSERT INTO `trajectory` VALUES (96, 'qwe001', '2023-04-17 14:27:54', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (97, 'qwe001', '2023-04-17 14:28:48', '139.7376,35.6948');
INSERT INTO `trajectory` VALUES (98, 'qwe001', '2023-04-17 14:28:55', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (99, 'qwe001', '2023-04-17 14:30:20', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (100, 'qwe001', '2023-04-17 14:30:52', '139.7376,35.6948');
INSERT INTO `trajectory` VALUES (101, 'qwe001', '2023-04-17 14:51:48', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (102, NULL, '2023-04-17 14:53:22', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (103, NULL, '2023-04-17 15:02:23', 'undefined,undefined');
INSERT INTO `trajectory` VALUES (104, NULL, '2023-04-17 16:13:37', 'undefined,undefined');

SET FOREIGN_KEY_CHECKS = 1;
