/*
 Navicat Premium Data Transfer

 Source Server         : 抖音云_数据库
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : 14.103.42.241:4308
 Source Schema         : yiliao3

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 25/11/2024 20:27:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `action_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行为编号',
  `action_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行为名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_action_name`(`action_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行为表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES (1, 'INSERT', '添加');
INSERT INTO `action` VALUES (2, 'DELETE', '删除');
INSERT INTO `action` VALUES (3, 'UPDATE', '修改');
INSERT INTO `action` VALUES (4, 'SELECT', '查询');
INSERT INTO `action` VALUES (5, 'APPROVAL', '审批');
INSERT INTO `action` VALUES (6, 'EXPORT', '导出');
INSERT INTO `action` VALUES (7, 'BACKUP', '备份');
INSERT INTO `action` VALUES (8, 'ARCHIVE', '归档');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门邮箱',
  `desc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_dept_name`(`dept_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '总裁部', '020-12345678', '111222@163.com', '管理部负责管理公司所有事务');
INSERT INTO `dept` VALUES (2, '行政部', '020-12345678', '111222@163.com', '负责行政管理');
INSERT INTO `dept` VALUES (3, '技术部', '020-12345678', '111222@163.com', '负责产品技术开发');
INSERT INTO `dept` VALUES (4, '市场部', '020-12345678', '111222@163.com', '负责市场管理');
INSERT INTO `dept` VALUES (5, '后勤部', '020-12345678', '111222@163.com', '负责后勤管理');
INSERT INTO `dept` VALUES (6, '人事部', '020-12345678', '111222@qq.com', '负责人事相关事宜管理');
INSERT INTO `dept` VALUES (106, '测试', '020-12345678', '111@aa.com', '111222');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `pid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证ID',
  `uuid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工牌号',
  `sex` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片存储地址',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `school` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `degree` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学位',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `job` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `remark` varchar(200) CHARACTER SET utf16le COLLATE utf16le_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细介绍',
  `hiredate` date NULL DEFAULT NULL COMMENT '入职日期',
  `tag` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特长',
  `recommended` tinyint(1) NULL DEFAULT NULL COMMENT '是否是优秀医生',
  `status` bigint(1) NOT NULL COMMENT '1在职，2离职，3退休，4隐藏（逻辑删除）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (1, '李雨萌', '360201198609151112', '2F0EB81AF9094277A958A41B59139DE1', '女', '/doctor/doctor-1.jpg', '1968-08-08', '重庆医科大学', '研究生', '13593812535', '北京市西城区北三环中路14-1号', 'chengchunmei@hospital.com', '副主任医师', '首都医科大学博士生导师', '擅长诊疗：心脏血管外科，包括风心病瓣膜替换，先心病，大血管疾病外科治疗。特色为冠状动脉外科，1990年以来在院内、外主做冠状动脉搭桥手术近千例，较早引进了世界最新的搭桥技术、非体外循环下冠状动脉搭桥术，获得了良好的效果 。', '2004-02-15', '[\"领域专家\",\"温暖贴心\",\"人很好\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (2, '张佳欣', '460201197611302855', 'F1FDE764A9BB405596895722F1CCDB06', '男', '/doctor/doctor-2.jpg', '1959-05-03', '中国医科大学', '博士', '15179382777', '北京市海淀区龙翔路9号', 'qinxinyuan@hospital.com', '主任医师', '陆军军医大学研究生导师', '擅长诊疗：下肢静脉曲张的微创治疗，多种微创方法综合治疗下肢静脉曲张，包括大隐静脉激光治疗、腔内 射频治疗，Trivex透光旋切、泡沫硬化剂注射治疗，对不同曲张静脉特点选择针对性微创方法，创伤小，恢复快。', '2004-12-11', '[\"从业46年\",\"领域专家\",\"快速回复\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (3, '王文彦', '370101197707304145', '2AE43F717E444031BC0CBB5878932B07', '男', '/doctor/doctor-3.jpg', '1976-11-28', '北京协和医学院', '博士', '18658678090', '北京市朝阳区三里屯路北1楼', 'xiongjiayu@hospital.com', '主任医师', '国家远程医疗医学中心主任委员', '擅长诊疗：慢性咳嗽、喘息性/呼吸困难性疾病如支气管哮喘、慢性阻塞性肺疾病、弥漫性肺疾病、肺部肿瘤、呼吸疑难危重症及胸膜疾病的诊断与治疗。主要临床研究方向：慢性气道炎症性疾病的基础与临床以及肺部疾病的介入性诊断与治疗。', '2005-08-04', '[\"从业27年\",\"领域专家\",\"快速回复\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (4, '刘梦琪', '370101197707304145', '50595ADEF85C4B35A114A462B0FA0CDA', '男', '/doctor/doctor-4.jpg', '1977-06-14', '北京协和医学院', '博士', '14580412494', '北京市海淀区花园东路8号院', 'mengmingyuan@hospital.com', '主任医师', '北京医科大学研究生导师', '擅长诊疗：面神经修复与面部整形重建。在国内很早开展咬肌神经吻合、多面神经重建、游离股薄肌移植等面瘫治疗手术，填补面瘫治疗领域多项技术空白。在面部整形重建、显微外科组织瓣移植修复等领域积累了丰富经验。', '2005-08-04', '[\"从业27年\",\"领域专家\",\"快速回复\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (5, '赵晓雨', '520201198509071764', 'B762C0BF9F994D23B5695EA78AE3F4F7', '女', '/doctor/doctor-5.jpg', '1978-12-31', '北京协和医学院', '博士', '15597529530', '北京市西城区大乘巷1号', 'fangjiayi@hospital.com', '主任医师', '北京医科大学、北京中医药大学研究生导师', '擅长诊疗：泌尿系肿瘤，特别是肾肿瘤，肾上腺肿瘤，尿路上皮肿瘤(肾盂、输尿管、膀胱肿瘤)以及前列腺恶性肿瘤的微创治疗，膀胱及前列腺良性疾病，骶神经调控等。', '2005-08-04', '[\"从业24年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (6, '周嘉欣', '500101200212123472', '9718C444BE3646818DD264FB26EC8181', '男', '/doctor/doctor-6.jpg', '1974-01-07', '北京协和医学院', '博士', '17723959830', '北京市西城区滨河里34号', 'huangtao@hospital.com', '主任医师', '北京医科大学硕士研究生导师', '擅长诊疗：临床常见恶性肿瘤的放射治疗：1.乳腺癌 2.消化系统恶性肿瘤如食管癌、直肠癌 3.头颈部恶性肿瘤 4.肺癌 5.妇科肿瘤。临床研究方向：乳腺癌个体化放射治疗，放射治疗后正常组织损伤的预防。', '2005-08-04', '[\"从业26年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (7, '吴子萱', '620101197707093458', '126A2D95DF2E42E4BD093FB9299623FB', '女', '/doctor/doctor-7.jpg', '1977-05-03', '解放军第三军医大学', '博士', '18362319314', '北京市海淀区复兴路12号8楼', 'wumengmeng@hospital.com', '主任医师', '中国医师协会微无创专业委员会委员', '擅长诊疗：青光眼和白内障的临床诊断及治疗。对各型青光眼的诊断和疑难杂症的处理有独到见解，特别对青光眼的诊断、激光、药物和手术治疗有深入的研究。注重青光眼的早期发现和早期治疗，对青光眼患者的个体化治疗进行了深入的研究', '2005-08-04', '[\"从业26年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (8, '黄思雨', '130201200402256643', 'A1F9664A527F4DCBA48ADF312AFBC421', '女', '/doctor/doctor-8.jpg', '1972-07-28', '广州医科大学', '博士', '18576200235', '北京市海淀区太平路22号', 'tianfang@hospital.com', '主任医师', '中国医药教育协会肿瘤专家委员会委员', '擅长诊疗：头颈肿瘤的外科及综合治疗（甲状腺癌、喉癌下咽癌、涎腺肿瘤、鼻腔鼻窦良恶性肿瘤、舌癌、咽旁颅底肿瘤）。对鼻窦内窥镜外科、喉内镜手术及声显微手术、耳显微手术、鼾症手术及综合治疗有丰富的临床经验。', '2005-08-04', '[\"从业31年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (9, '许文彬', '420101199510078280', '3D3F7F2204204E30AD2F23C28A569B9A', '男', '/doctor/doctor-9.jpg', '1977-02-14', '哈尔滨医科大学', '博士', '13822560280', '北京市西城区车站西街15号院-5号楼', 'majie@hospital.com', '主任医师', '北京医师协会皮肤病专业专家委员会委员', '擅长诊疗：以皮肤病理为专长，擅长常见皮肤病如：湿疹，药疹，蕁麻疹，银屑病，扁平苔癣等;色素性皮肤病如：各种色素斑、痣，白癜风等;感染性皮肤病及性病(梅毒、淋病、尖锐湿疣、非淋菌性尿道炎)等。', '2005-08-04', '[\"从业22年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (10, '郑雅琪', '510101198806215034', 'CD2C65C455564181ADFF84BD6A2F35C7', '女', '/doctor/doctor-10.jpg', '1978-06-22', '南京医科大学', '研究生', '19738130796', '北京市丰台区望园东路2928号', 'dujiayu@hospital.com', '主治医师', '参加多项国家级、省部级多项科研课题', '擅长诊疗：应用中西医优势互补方法治疗糖尿病及其并发症(糖尿病心脑血管病、糖尿病肾病、糖尿病胃轻瘫、糖尿病周围神经病变、血糖难控因素)以及代谢综合征。', '2005-08-04', '[\"从业17年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (11, '冯佳雨', '530201199301048406', 'FFBA296720C8495785E8A78B379C9B05', '男', '/doctor/doctor-11.jpg', '1975-11-11', '天津医科大学', '博士', '13777571218', '北京市石景山区重聚路40号院-3号', 'dengguodong@hospital.com', '副主任医师', '北京医师协会风湿免疫专科分会理事', '擅长诊疗：系统性红斑狼疮、多发性肌炎、皮肌炎、类风湿关节炎、痛风、强直性脊柱炎、系统性血管炎等风湿免疫疾病，对疑难复杂危重风湿免疫疾病具备丰富诊疗经验。临床研究方向为多发性肌炎和皮肌炎，系统性红斑狼疮，痛风，风湿免疫疾病心血管系统受累等。', '2005-08-04', '[\"从业19年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (12, '蔡心怡', '120201198705219290', '0255BFF8CCC1479C898E21D1D3B0A8E7', '男', '/doctor/doctor-12.jpg', '1978-12-16', '中国医科大学', '研究生', '13069020752', '北京市海淀区玉泉路16号院', 'longzeyuan@hospital.com', '副主治医师', '参与多项国家自然科学基金课题研究', '擅长诊疗：多发性肌炎，皮肌炎，系统性红斑狼疮，类风湿关节炎等多种风湿免疫疾病诊治。对肌炎合并间质性肺疾病有深入研究。', '2005-08-04', '[\"从业15年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (13, '朱宇琪', '650201198402246623', '0370428B5452441C9F64658F2B7BC7F1', '女', '/doctor/doctor-13.jpg', '1970-12-16', '中国医科大学', '博士', '15977965686', '北京市西城区马连道南街1号院', 'songxiuying@hospital.com', '主治医师', '中华医学会风湿病分会会员', '擅长诊疗：从事风湿免疫疾病临床诊断、治疗工作20余年，有着丰富的临床经验，对风湿病重症、疑难症及长期不明原因的发热病例的诊治都具有较高的水平。', '2005-08-04', '[\"从业28年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (14, '曾子轩', '450201198007308399', '6BD7AB9AE6AD417A90042FF3536ECC6C', '男', '/doctor/doctor-14.jpg', '1971-01-07', '中国医科大学', '博士', '15589198858', '北京市石景山区八角南路19号楼', 'xuerongrun@hospital.com', '主治医师', '北京市泌尿外科分会结石感染组委员', '擅长诊疗：经皮肾镜、输尿管镜微创治疗肾结石、输尿管结石、膀胱结石;肾上腺、肾、输尿管、膀胱肿瘤及疾病等微创治疗;肾盂输尿管狭窄,输尿管狭窄及尿道狭窄的微创治疗;前列腺增生、前列腺肿瘤的诊断治疗。', '2005-08-04', '[\"从业26年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (15, '翟婷婷', '610201197909271420', '6B4A32C097BA44F1B052B6F85C2D3E7B', '男', '/doctor/doctor-15.jpg', '1968-01-07', '南京医科大学', '博士', '13923984769', '北京市丰台区久敬庄路乙1号', 'tanshang@hospital.com', '主治医师', '北京医科大学硕士研究生导师', '擅长诊疗：熟练掌握胸外科专业各类疾病的诊断、治疗，特别对肺癌的根治性手术，食管癌的根治性手术，纵隔肿瘤、胸壁恶性肿瘤的根治性手术及综合治疗。纤支镜、纵隔镜的检查及治疗，胸腔镜的各类微创手术治疗及目前开展胸腔镜下肺癌根治术。', '2005-08-04', '[\"从业36年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (16, '彭俊豪', '420201198903179411', '43E06B95BD364ACD890C73D91D9881BF', '男', '/doctor/doctor-16.jpg', '1972-03-17', '首都医科大学', '博士', '18068672244', '北京市朝阳区东三环北路辛2号', 'renzhenguo@hospital.com', '主治医师', '北京口腔临床技术研究会理事', '擅长诊疗：成人正畸、隐形正畸、牙周病正畸、多学科联合治疗、儿童错合畸形的早期正畸治疗、骨性错颌畸形的综合治疗。', '2005-08-04', '[\"从业29年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (17, '潘佳慧', '220101200306063805', 'DDAF4F5F849B4D2AB6DB8CA442794A5C', '女', '/doctor/doctor-17.jpg', '1973-05-08', '首都医科大学', '博士', '17267270501', '北京市东城区和平里北街21号', 'xujingqi@hospital.com', '主治医师', '北京口腔临床技术研究会理事', '擅长诊疗：擅长龋齿、牙髓病和根尖周病的诊断与系统治疗；牙周病及牙槽外科手术；牙体的美学修复。', '2005-08-04', '[\"从业26年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (18, '袁文斌', '630201198312155601', '6B946B8B0C4A42DA8DE05E62A6CDE8E6', '男', '/doctor/doctor-18.jpg', '1974-12-24', '北京大学口腔医学院', '博士', '13773287399', '北京市东城区北新桥三条甲58号', 'lvchenglong@hospital.com', '主治医师', '北京口腔临床技术研究会理事', '擅长诊疗：口腔科常见病、多发病的诊疗工作，包括牙体牙髓病、牙周病、牙槽外科及口腔修复的相关疾病的全面设计与治疗。', '2005-08-04', '[\"从业25年\",\"领域专家\",\"温暖贴心\"]', 1, 1, '2024-03-06 00:00:00');
INSERT INTO `doctor` VALUES (20, '韩倩倩', '421081199204014300', '70AD20F195C741568C070CF8EF579622', '男', '/doctor/doctor-20.jpg', '1992-10-17', '华中科技大学', '本科', '15002789592', '北京市海淀区', '43281991@qq.com', '主治医师', '国家远程医疗医学中心主任委员', '擅长诊疗：熟练掌握胸外科专业各类疾病的诊断、治疗，特别对肺癌的根治性手术，食管癌的根治性手术，纵隔肿瘤、胸壁恶性肿瘤的根治性手术及综合治疗。纤支镜、纵隔镜的检查及治疗，胸腔镜的各类微创手术治疗及目前开展胸腔镜下肺癌根治术。', '2024-03-12', '[\"棒棒哒\",\"领域专家\",\"温暖贴心\",\"医术高超\"]', 1, 1, '2024-03-13 14:33:50');

-- ----------------------------
-- Table structure for doctor_consultation_report
-- ----------------------------
DROP TABLE IF EXISTS `doctor_consultation_report`;
CREATE TABLE `doctor_consultation_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `patient_card_id` int(11) NULL DEFAULT NULL,
  `diagnosis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sub_dept_id` int(11) NULL DEFAULT NULL,
  `doctor_id` int(11) NULL DEFAULT NULL,
  `registration_id` int(11) NULL DEFAULT NULL,
  `rp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor_consultation_report
-- ----------------------------
INSERT INTO `doctor_consultation_report` VALUES (2, '08FF1A2286FE4C83AD637C10C72D2350', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (3, '6953906605484AA887691719A9CBA058', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (4, '602EEFF2D4ED4CB3BB74BB36B10202F0', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (5, '2E236404C6F3445AA57248BF5E217599', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (6, '9E626482D93E4A4099B9802EE8C34B62', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (7, '90F6104696B9413A98FD1A5F80287EA9', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (8, 'F4A346D552E04D4DB3E58F98DC54B038', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (9, '84F4DDABC34D4F7E876F68E121084329', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (10, '9E34D642E4F744689B1B17D5F77D2F7B', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (11, '1ACEAE9A5C39435881AE35D24267B46B', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');
INSERT INTO `doctor_consultation_report` VALUES (12, '34A4286CF41D419AB79AF96F64C7CC67', 1, '急性牙髓炎', 2, 18, 1, '[{\"method\":\"1片/次；每日三次；口服\",\"num\":1,\"spec\":\"200mg×24片\",\"name\":\"甲硝唑片\"},{\"method\":\"1片/次；每日两次；口服\",\"num\":1,\"spec\":\"250mg×24片\",\"name\":\"头孢拉定胶囊\"}]');

-- ----------------------------
-- Table structure for doctor_price
-- ----------------------------
DROP TABLE IF EXISTS `doctor_price`;
CREATE TABLE `doctor_price`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NULL DEFAULT NULL,
  `level` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price_1` decimal(10, 2) NULL DEFAULT NULL,
  `price_2` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor_price
-- ----------------------------
INSERT INTO `doctor_price` VALUES (1, 1, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (2, 2, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (3, 3, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (4, 4, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (5, 5, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (6, 6, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (7, 7, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (8, 8, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (9, 9, '主任医师', 30.00, 100.00);
INSERT INTO `doctor_price` VALUES (10, 10, '普通', 10.00, 100.00);
INSERT INTO `doctor_price` VALUES (11, 11, '副主任医师', 20.00, 100.00);
INSERT INTO `doctor_price` VALUES (12, 12, '副主任医师', 20.00, 80.00);
INSERT INTO `doctor_price` VALUES (13, 13, '普通', 10.00, 80.00);
INSERT INTO `doctor_price` VALUES (14, 14, '普通', 10.00, 80.00);
INSERT INTO `doctor_price` VALUES (15, 15, '普通', 10.00, 80.00);
INSERT INTO `doctor_price` VALUES (16, 16, '普通', 10.00, 80.00);
INSERT INTO `doctor_price` VALUES (17, 17, '普通', 10.00, 80.00);
INSERT INTO `doctor_price` VALUES (18, 18, '普通', 10.00, 80.00);
INSERT INTO `doctor_price` VALUES (19, 20, '主任医师', 30.00, 10.00);
INSERT INTO `doctor_price` VALUES (20, 36, '主任医师', 30.00, 100.00);

-- ----------------------------
-- Table structure for doctor_work_plan
-- ----------------------------
DROP TABLE IF EXISTS `doctor_work_plan`;
CREATE TABLE `doctor_work_plan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `doctor_id` int(11) NULL DEFAULT NULL COMMENT '医生ID',
  `dept_sub_id` int(11) NULL DEFAULT NULL COMMENT '诊室ID',
  `date` date NULL DEFAULT NULL COMMENT '出诊日期',
  `maximum` int(11) NULL DEFAULT NULL COMMENT '该医生当天挂号人数上线',
  `num` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor_work_plan
-- ----------------------------
INSERT INTO `doctor_work_plan` VALUES (1, 16, 2, '2024-07-02', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (2, 17, 2, '2024-03-23', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (3, 18, 2, '2024-03-23', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (4, 16, 2, '2024-03-24', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (5, 16, 2, '2023-03-25', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (6, 16, 2, '2024-03-26', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (7, 1, 18, '2024-03-23', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (8, 2, 20, '2024-03-23', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (9, 9, 20, '2024-03-23', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (10, 9, 20, '2024-03-24', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (11, 9, 20, '2024-03-25', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (12, 8, 3, '2024-03-18', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (14, 9, 4, '2024-03-19', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (15, 10, 5, '2024-03-24', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (16, 3, 6, '2024-03-24', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (17, 4, 7, '2024-03-21', 57, 0);
INSERT INTO `doctor_work_plan` VALUES (18, 5, 8, '2024-03-22', 55, 0);
INSERT INTO `doctor_work_plan` VALUES (19, 6, 9, '2024-03-19', 50, 0);
INSERT INTO `doctor_work_plan` VALUES (20, 26, 8, '2024-03-28', 15, 0);
INSERT INTO `doctor_work_plan` VALUES (22, 26, 8, '2024-03-18', 15, 0);
INSERT INTO `doctor_work_plan` VALUES (23, 26, 8, '2024-03-19', 15, 0);
INSERT INTO `doctor_work_plan` VALUES (24, 26, 8, '2024-03-22', 21, 0);
INSERT INTO `doctor_work_plan` VALUES (25, 26, 8, '2024-03-23', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (26, 26, 8, '2024-03-29', 15, 0);
INSERT INTO `doctor_work_plan` VALUES (27, 26, 8, '2024-03-24', 36, 0);
INSERT INTO `doctor_work_plan` VALUES (28, 7, 4, '2024-03-18', 24, 0);
INSERT INTO `doctor_work_plan` VALUES (29, 7, 4, '2024-03-22', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (30, 7, 4, '2024-03-29', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (31, 7, 4, '2024-03-30', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (32, 5, 4, '2024-03-29', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (33, 5, 4, '2024-03-30', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (34, 5, 4, '2024-03-31', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (35, 20, 1, '2024-04-08', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (36, 36, 1, '2024-04-08', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (37, 7, 4, '2024-04-08', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (38, 5, 4, '2024-04-08', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (39, 5, 4, '2024-04-09', 63, 0);
INSERT INTO `doctor_work_plan` VALUES (40, 7, 4, '2024-04-09', 24, 0);
INSERT INTO `doctor_work_plan` VALUES (41, 5, 4, '2024-04-10', 48, 0);
INSERT INTO `doctor_work_plan` VALUES (42, 7, 4, '2024-04-10', 42, 0);
INSERT INTO `doctor_work_plan` VALUES (43, 7, 4, '2024-04-11', 18, 0);
INSERT INTO `doctor_work_plan` VALUES (44, 5, 4, '2024-04-11', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (48, 20, 1, '2024-04-09', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (49, 36, 1, '2024-04-10', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (50, 36, 1, '2024-04-11', 15, 0);
INSERT INTO `doctor_work_plan` VALUES (52, 36, 1, '2024-04-13', 24, 0);
INSERT INTO `doctor_work_plan` VALUES (53, 18, 2, '2024-04-10', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (54, 20, 1, '2024-04-10', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (55, 3, 9, '2024-04-10', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (56, 5, 26, '2024-04-10', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (57, 3, 9, '2024-04-11', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (58, 3, 9, '2024-04-12', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (59, 43, 2, '2024-04-11', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (60, 35, 2, '2024-04-11', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (61, 7, 4, '2024-04-15', 27, 0);
INSERT INTO `doctor_work_plan` VALUES (62, 20, 1, '2024-04-11', 3, 0);
INSERT INTO `doctor_work_plan` VALUES (63, 20, 1, '2024-04-12', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (64, 20, 1, '2024-04-01', 3, 0);
INSERT INTO `doctor_work_plan` VALUES (65, 20, 1, '2024-04-13', 25, 0);
INSERT INTO `doctor_work_plan` VALUES (66, 20, 1, '2024-04-14', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (67, 20, 1, '2024-04-02', 3, 0);
INSERT INTO `doctor_work_plan` VALUES (74, 1, 2, '2024-04-12', 8, 0);
INSERT INTO `doctor_work_plan` VALUES (78, 20, 1, '2024-04-24', 14, 0);
INSERT INTO `doctor_work_plan` VALUES (79, 7, 4, '2024-04-24', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (80, 36, 1, '2024-04-25', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (81, 20, 1, '2024-04-25', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (82, 36, 1, '2024-04-24', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (83, 20, 1, '2024-04-26', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (84, 36, 1, '2024-04-26', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (85, 20, 1, '2024-04-27', 15, 0);
INSERT INTO `doctor_work_plan` VALUES (87, 20, 1, '2024-05-06', 18, 0);
INSERT INTO `doctor_work_plan` VALUES (88, 36, 1, '2024-05-06', 18, 0);
INSERT INTO `doctor_work_plan` VALUES (89, 20, 1, '2024-05-07', 18, 0);
INSERT INTO `doctor_work_plan` VALUES (90, 36, 1, '2024-05-07', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (91, 20, 1, '2024-05-08', 10, 0);
INSERT INTO `doctor_work_plan` VALUES (92, 36, 1, '2024-05-08', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (93, 20, 1, '2024-05-09', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (94, 36, 1, '2024-05-09', 6, 0);
INSERT INTO `doctor_work_plan` VALUES (95, 20, 1, '2024-05-10', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (96, 36, 1, '2024-05-10', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (97, 20, 1, '2024-05-11', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (98, 36, 1, '2024-05-11', 10, 0);
INSERT INTO `doctor_work_plan` VALUES (99, 20, 1, '2024-05-12', 12, 0);
INSERT INTO `doctor_work_plan` VALUES (100, 20, 1, '2024-06-15', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (101, 18, 2, '2024-06-15', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (102, 36, 1, '2024-06-18', 45, 0);
INSERT INTO `doctor_work_plan` VALUES (103, 44, 1, '2024-06-18', 9, 0);
INSERT INTO `doctor_work_plan` VALUES (104, 64, 3, '2024-06-18', 90, 0);
INSERT INTO `doctor_work_plan` VALUES (105, 36, 1, '2024-09-21', 16, 0);
INSERT INTO `doctor_work_plan` VALUES (106, 1, 2, '2024-09-21', 18, 0);
INSERT INTO `doctor_work_plan` VALUES (107, 36, 1, '2024-09-23', 30, 0);

-- ----------------------------
-- Table structure for doctor_work_plan_schedule
-- ----------------------------
DROP TABLE IF EXISTS `doctor_work_plan_schedule`;
CREATE TABLE `doctor_work_plan_schedule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_plan_id` int(11) NULL DEFAULT NULL,
  `slot` tinyint(4) NULL DEFAULT NULL,
  `maximum` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 501 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor_work_plan_schedule
-- ----------------------------
INSERT INTO `doctor_work_plan_schedule` VALUES (1, 1, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (2, 1, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (3, 1, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (4, 1, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (5, 1, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (6, 1, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (7, 1, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (8, 1, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (9, 1, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (10, 1, 10, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (11, 1, 11, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (12, 1, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (13, 1, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (14, 1, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (15, 1, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (16, 2, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (17, 2, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (18, 2, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (19, 2, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (20, 5, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (21, 6, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (22, 3, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (23, 3, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (24, 23, 1, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (25, 23, 2, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (26, 23, 3, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (27, 24, 1, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (28, 24, 2, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (29, 24, 3, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (30, 25, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (31, 25, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (32, 26, 1, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (33, 26, 2, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (34, 26, 3, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (35, 27, 4, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (36, 27, 5, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (37, 27, 2, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (38, 27, 1, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (39, 27, 3, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (40, 27, 6, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (41, 28, 1, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (42, 28, 2, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (43, 28, 5, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (44, 28, 4, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (45, 28, 3, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (46, 28, 6, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (47, 29, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (48, 29, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (49, 29, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (50, 30, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (51, 30, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (52, 30, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (53, 30, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (54, 31, 1, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (55, 31, 2, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (56, 31, 3, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (57, 32, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (58, 32, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (59, 32, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (60, 33, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (61, 33, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (62, 33, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (63, 34, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (64, 34, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (65, 34, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (66, 34, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (67, 34, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (68, 34, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (69, 34, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (70, 34, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (71, 34, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (72, 34, 10, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (73, 34, 11, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (74, 34, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (75, 34, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (76, 34, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (77, 34, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (78, 35, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (79, 35, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (80, 35, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (81, 36, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (82, 36, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (83, 36, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (84, 37, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (85, 37, 10, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (86, 38, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (87, 38, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (88, 38, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (89, 39, 1, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (90, 39, 2, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (91, 39, 3, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (92, 39, 4, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (93, 39, 5, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (94, 39, 6, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (95, 39, 7, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (96, 39, 8, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (97, 39, 9, 7, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (98, 40, 1, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (99, 40, 2, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (100, 40, 3, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (101, 40, 4, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (102, 40, 5, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (103, 40, 6, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (104, 41, 1, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (105, 41, 2, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (106, 41, 3, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (107, 41, 4, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (108, 41, 5, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (109, 41, 6, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (110, 41, 8, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (111, 41, 7, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (112, 42, 9, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (113, 42, 10, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (114, 42, 11, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (115, 42, 12, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (116, 42, 13, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (117, 42, 14, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (118, 42, 15, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (119, 43, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (120, 43, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (121, 43, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (122, 43, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (123, 43, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (124, 43, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (125, 44, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (126, 44, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (127, 44, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (142, 48, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (143, 48, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (144, 49, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (145, 49, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (146, 50, 8, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (147, 50, 14, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (148, 50, 11, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (152, 52, 8, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (153, 52, 12, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (154, 52, 15, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (155, 52, 14, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (156, 53, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (157, 53, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (158, 53, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (159, 54, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (162, 55, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (163, 55, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (164, 55, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (165, 56, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (166, 56, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (167, 56, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (168, 57, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (169, 57, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (170, 57, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (171, 58, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (172, 58, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (173, 58, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (174, 59, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (175, 59, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (176, 59, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (177, 60, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (178, 60, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (179, 60, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (183, 62, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (184, 63, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (185, 63, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (186, 64, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (187, 65, 2, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (188, 65, 5, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (189, 65, 8, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (190, 65, 11, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (191, 65, 1, 5, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (192, 66, 1, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (193, 66, 2, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (194, 66, 3, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (205, 67, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (209, 54, 3, 18, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (213, 54, 3, 9, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (219, 54, 2, 9, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (220, 61, 1, 27, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (221, 61, 2, 27, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (222, 61, 3, 27, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (295, 74, 1, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (296, 74, 2, 4, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (318, 79, 1, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (319, 79, 2, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (320, 79, 3, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (321, 79, 4, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (322, 79, 5, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (323, 79, 6, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (324, 80, 1, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (325, 80, 2, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (326, 80, 3, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (327, 81, 7, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (328, 81, 8, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (329, 81, 9, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (330, 82, 10, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (331, 82, 11, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (332, 82, 12, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (336, 84, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (337, 84, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (338, 84, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (344, 85, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (345, 85, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (346, 85, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (347, 85, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (348, 85, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (361, 87, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (362, 87, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (363, 87, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (364, 87, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (365, 87, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (366, 87, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (367, 88, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (368, 88, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (369, 88, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (370, 88, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (371, 88, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (372, 88, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (373, 89, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (374, 89, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (375, 89, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (376, 89, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (377, 89, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (378, 89, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (379, 90, 1, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (380, 90, 2, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (381, 90, 3, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (382, 90, 4, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (383, 90, 5, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (384, 90, 6, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (385, 91, 1, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (386, 91, 2, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (387, 91, 3, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (388, 91, 4, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (389, 91, 5, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (390, 92, 1, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (391, 92, 2, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (392, 92, 3, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (393, 93, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (394, 93, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (395, 93, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (396, 94, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (397, 94, 11, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (398, 95, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (399, 95, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (400, 95, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (401, 96, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (402, 96, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (403, 96, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (404, 97, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (405, 97, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (406, 97, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (407, 98, 11, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (408, 98, 12, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (409, 98, 13, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (410, 98, 14, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (411, 98, 15, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (412, 99, 1, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (413, 99, 2, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (414, 99, 3, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (415, 99, 4, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (416, 99, 5, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (417, 99, 6, 2, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (418, 100, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (419, 100, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (420, 100, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (421, 100, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (422, 100, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (423, 100, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (424, 100, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (425, 100, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (426, 100, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (427, 100, 10, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (428, 100, 11, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (429, 100, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (430, 100, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (431, 100, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (432, 100, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (433, 101, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (434, 101, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (435, 101, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (436, 101, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (437, 101, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (438, 101, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (439, 101, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (440, 101, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (441, 101, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (442, 101, 10, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (443, 101, 11, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (444, 101, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (445, 101, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (446, 101, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (447, 101, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (448, 102, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (449, 102, 2, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (450, 102, 3, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (451, 102, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (452, 102, 5, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (453, 102, 6, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (454, 102, 7, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (455, 102, 8, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (456, 102, 9, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (457, 102, 10, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (458, 102, 11, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (459, 102, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (460, 102, 13, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (461, 102, 14, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (462, 102, 15, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (463, 103, 1, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (466, 103, 4, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (474, 103, 12, 3, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (478, 104, 1, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (479, 104, 2, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (480, 104, 3, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (481, 104, 4, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (482, 104, 5, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (483, 104, 6, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (484, 104, 7, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (485, 104, 8, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (486, 104, 9, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (487, 104, 10, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (488, 104, 11, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (489, 104, 12, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (490, 104, 13, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (491, 104, 14, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (492, 104, 15, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (493, 105, 1, 8, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (494, 105, 3, 8, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (495, 106, 1, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (496, 106, 2, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (497, 106, 3, 6, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (498, 107, 1, 10, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (499, 107, 2, 10, 0);
INSERT INTO `doctor_work_plan_schedule` VALUES (500, 107, 3, 10, 0);

-- ----------------------------
-- Table structure for medical_dept
-- ----------------------------
DROP TABLE IF EXISTS `medical_dept`;
CREATE TABLE `medical_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室名称',
  `outpatient` tinyint(1) NULL DEFAULT NULL COMMENT '是否为门诊',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细注释',
  `recommended` tinyint(1) NULL DEFAULT NULL COMMENT '是否为优秀科室',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111222366 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_dept
-- ----------------------------
INSERT INTO `medical_dept` VALUES (1, '口腔科', 1, '目前已经成为在国内外具有一定影响力的大型医疗科室，科室现有医护人员近70人，教授6人，副教授3人，博士研究生导师3人，硕士研究生导师4人，每年培养博士、硕士研究生近20余人。', 0);
INSERT INTO `medical_dept` VALUES (2, '眼科', 1, '全科共有医护人员及技师共67人，其中教授6人，副教授9人，讲师10人，助教4人，在职的29名医生全部获得硕士、博士学位，其中博士14人。下设眼底病、青光眼、白内障、眼外伤、飞秒激光治疗近视、眼眶病、斜视等多个专业学组。开展各种外路视网膜脱离复位术，玻璃体视网膜手术治疗视网膜脱离手术、复杂及二次视网膜脱离手术、黄斑裂孔性视网膜脱离手术等。', 1);
INSERT INTO `medical_dept` VALUES (3, '耳鼻喉科', 1, '科室设有耳科、鼻科、咽喉头颈外科3个专业组，配备听力检测、前庭功能检查、喉功能检查、 内窥镜检查及多导睡眠检查5个检查室，配备手术显微镜、鼻内镜系统、耳鼻喉动力系统、CO2激光治疗机等一批先进诊疗设备，编制床位共计100张。年门急诊患者量近10万人次，年住院3500余人次，年手术3000余例，学科综合实力位于国内先进行列。', 1);
INSERT INTO `medical_dept` VALUES (4, '内科', 1, '目前已经成为在国内外具有一定影响力的大型医疗科室，科室现有医护人员近70人，教授6人，副教授3人，博士研究生导师3人，硕士研究生导师4人，每年培养博士、硕士研究生近20余人。呼吸科年门诊量5万余人次，年收治各种呼吸系统疾病住院患者2000余人次，现有床位75张，其中内科重症监护室床位23张，长期承担内科重症患者的诊治工作。长期承担七年制、本科、留学生等医学班的内科学、诊断学、临床实习等授课。', 1);
INSERT INTO `medical_dept` VALUES (5, '外科', 1, '科室现有医生52名，教授8人，副教授9人。年均开展各类手术1000余例。其中腔镜微创手术占手术量的40％以上。近年接连开展胸外科各项领先技术，如3切口食管癌手术，全腔镜食管癌手术，腔镜下胸部单操作孔、腹部单孔食管癌手术等。科室承担国家自然科学基金等国家和部省级各类科研课题10余项，发表国际核心医学期刊SCI收录英文论文10余篇，国家级核心期刊中文论文60余篇。', 1);
INSERT INTO `medical_dept` VALUES (6, '皮肤科', 1, '皮肤科成立于1977年，现共有医生11人(副主任医师3名)，护士2名，其中8人具有硕士学位、2人具有博士学位。目前门诊量120000人次/年左右，是皮肤疾病主要的诊断、治疗场所。诊治的主要疾病包括儿童湿疹、皮炎、荨麻疹、药疹等过敏变态反应性疾病;水痘、手足口病、猩红热、传染性单核细胞增多症、疣、毛囊炎、脓疱疮、念珠菌性皮炎、足癣、体癣、疥疮等感染性皮肤病。', 1);
INSERT INTO `medical_dept` VALUES (7, '妇科', 1, '妇科现有职工89人，其中医生42人（博士16人，硕士26人），包括正高职（教授、主任医师）14人，省管专家2人，享受政府特殊津贴2人，博士生导师3人，硕士生导师11人。在女性复杂生殖道畸形矫治（先天性无阴道阴道成形术、Robert子宫宫腔镜矫形术、阴道斜隔综合征矫治术、特殊类型外阴阴道畸形矫治术、子宫纵隔切除术）有丰富的治疗经验，2019年在女性生殖系统重建的基础研究与临床应用方面的突出成就获得河北省科技进步一等奖。 ', 1);
INSERT INTO `medical_dept` VALUES (8, '儿科', 1, '目前共有医护人员49 名，包括医师17人，护理人员32人。其中，主任医师2人，副主任医师2人，主治医师9人，具有硕士学位6人，在读硕士4人。儿科分成门诊及病区两个单元。儿科门诊常年24小时接诊，年门急诊量逐年提升，平均年门急诊量可达8万人次。每日输液量在80-100人次、最多时达到200-280人次。每日雾化量在50人次，最多时达到150人次。儿科始终围绕“以病人为中心，以质量为生命”这一主题，构建和谐科室，打造专业儿科团队，病人满意度不断提高，得到家长广泛的认可和尊重。', 1);
INSERT INTO `medical_dept` VALUES (9, '神经科', 1, '科室拥有一支专业的神经科诊断技术团队，常规开展神经影像学(3.0MRI、128排螺旋CT、DSA、TCD、ECT、OCT)、神经电生理（视频脑电图、动态脑电图、睡眠脑电图、肌电图、诱发电位、神经传导速度）、神经免疫、神经生化、神经病理、抗癫痫药物浓度检测、基因检测、脑脊液细胞学检查等工作，为神经系统疾病尤其是疑难杂症的诊断提供了良好的技术平台。每年开展颅内动脉瘤弹簧圈栓塞术200余例；颈内动脉支架植入术30余例；动脉取栓及支架植入20余例；微创颅内血肿清除引流术100余例。', 1);
INSERT INTO `medical_dept` VALUES (10, '肿瘤科', 1, '肿瘤内科建科于1964年，首批国家临床重点专科、卫计委癌痛规范化示范病房、国家临床药理机构肿瘤专业基地、中华医学会肿瘤学分会、中国临床肿瘤学会（CSCO）和中国研究型医院学会精准医学与肿瘤MDT专业委员会常委单位、教育部肿瘤学专业硕士及博士学位授予点。科室开展的治疗包括术前新辅助化疗、转化化疗、术后辅助化疗、姑息性化疗、根治性化疗、靶向治疗及免疫治疗等，并参与开展数十项国际、国内多中心临床试验。成立有肺癌、消化道肿瘤、乳腺癌、淋巴瘤、泌尿生殖系肿瘤、骨及皮肤软组织肉瘤、胃肠胰神经内分泌肿瘤和恶性黑色素瘤8个亚专业及相关MDT团队，重点是在MDT指导下依据组织或血液标本进行多基因检测，获得靶基因、基因多态性及通路基因的相关信息，实施规范化、个体化的综合治疗；治疗和学术水平达国内先进水平。', 1);
INSERT INTO `medical_dept` VALUES (11, '产科', 1, '产科现有专业医护人员50余名，其中高级职称11人，硕士生导师3人，硕士 10余人，开放床位3个普通病区+1个LDR病区，共124张，备有单人间、双人间的母婴同室病房，还有温馨家庭病房16间。本科室在自然分娩、阴道侧切术、新式剖宫产术等方面积累了丰富的临床经验，具备危重产科病人抢救的技术和设备，全面开展了围产期保健、产前筛查、出生缺陷监测、优生遗传咨询、孕妇学校、都哈营养指导、育儿指导、康乐待产、康乐分娩、无痛分娩、新生儿疾病筛查、新生儿抚触、新生儿游泳、产后恢复保健、院后随访等一系列整体化、人性化的治疗及护理措施，擅长各种高危妊娠的筛查、监护和治疗，妊娠合并症及妊娠并发症的诊断及治疗。', 0);
INSERT INTO `medical_dept` VALUES (12, '骨科', 1, '科室设置规范、布局合理，拥有专业医疗团队，本学科对骨科疾病的诊疗,特别是疑难、危重病人的诊治和抢救水平保持区内领先水平。科室拥有进口“C”臂机、全套进口关节镜器械、进口牵引床、进口电钻及磨钻、CPM关节康复器、骨折治疗仪等先进设备。目前开展复杂骨盆及髋臼的前后入路手术、颈胸腰椎的前后路手术、人工全髋全膝关节置换术、膝关节镜下滑膜清理、半月板成形、交叉韧带重建手术，经皮椎体后凸成形术（PKP术），闭合复位PFNA固定术，经皮插入钢板内固定治疗四肢骨折、微创腰椎融合术人工肱骨头、桡骨头置换术、断肢（指）再植术、脊柱侧弯后路矫形复位术、全髋关节翻修术等。', 0);

-- ----------------------------
-- Table structure for medical_dept_sub
-- ----------------------------
DROP TABLE IF EXISTS `medical_dept_sub`;
CREATE TABLE `medical_dept_sub`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept_id` int(11) NOT NULL,
  `location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_dept_sub
-- ----------------------------
INSERT INTO `medical_dept_sub` VALUES (1, '口腔颌面外科', 1, '1号楼2层A区');
INSERT INTO `medical_dept_sub` VALUES (2, '口腔颌面内科', 1, '1号楼2层B区');
INSERT INTO `medical_dept_sub` VALUES (3, '眼科门诊', 2, '1号楼3层A区');
INSERT INTO `medical_dept_sub` VALUES (4, '白内障诊疗中心', 2, '1号楼3层B区');
INSERT INTO `medical_dept_sub` VALUES (5, '屈光中心门诊', 2, '1号楼3层C区');
INSERT INTO `medical_dept_sub` VALUES (6, '眼激光门诊', 2, '1号楼3层D区');
INSERT INTO `medical_dept_sub` VALUES (7, '耳鼻喉门诊', 3, '1号楼3层E区');
INSERT INTO `medical_dept_sub` VALUES (8, '内分泌门诊', 4, '1号楼4层A区');
INSERT INTO `medical_dept_sub` VALUES (9, '呼吸内科门诊', 4, '1号楼4层B区');
INSERT INTO `medical_dept_sub` VALUES (10, '心血管门诊', 4, '1号楼4层C区');
INSERT INTO `medical_dept_sub` VALUES (11, '消化内科门诊', 4, '1号楼4层D区');
INSERT INTO `medical_dept_sub` VALUES (12, '糖尿病门诊', 4, '1号楼5层A区');
INSERT INTO `medical_dept_sub` VALUES (13, '肾内科门诊', 4, '1号楼5层B区');
INSERT INTO `medical_dept_sub` VALUES (14, '风湿免疫门诊', 4, '1号楼5层C区');
INSERT INTO `medical_dept_sub` VALUES (15, '普通外科门诊', 5, '1号楼5层D区');
INSERT INTO `medical_dept_sub` VALUES (16, '胸外科门诊', 5, '1号楼5层E区');
INSERT INTO `medical_dept_sub` VALUES (17, '泌尿外科门诊', 5, '1号楼6层A区');
INSERT INTO `medical_dept_sub` VALUES (18, '心脏外科门诊', 5, '1号楼5层B区');
INSERT INTO `medical_dept_sub` VALUES (19, '整形外科门诊', 5, '1号楼5层C区');
INSERT INTO `medical_dept_sub` VALUES (20, '皮肤病门诊', 6, '1号楼5层D区');
INSERT INTO `medical_dept_sub` VALUES (21, '妇科门诊', 7, '1号楼6层A区');
INSERT INTO `medical_dept_sub` VALUES (22, '不孕病门诊', 7, '1号楼6层B区');
INSERT INTO `medical_dept_sub` VALUES (23, '儿科门诊', 8, '1号楼6层C区');
INSERT INTO `medical_dept_sub` VALUES (24, '神经内科门诊', 9, '1号楼7层A区');
INSERT INTO `medical_dept_sub` VALUES (25, '神经外科门诊', 9, '1号楼7层B区');
INSERT INTO `medical_dept_sub` VALUES (26, '肿瘤科门诊', 10, '2号楼2层A区');
INSERT INTO `medical_dept_sub` VALUES (27, '产科门诊', 11, '2号楼3层A区');
INSERT INTO `medical_dept_sub` VALUES (28, '骨科门诊', 12, '2号楼4层A区');
INSERT INTO `medical_dept_sub` VALUES (29, '血液科门诊', 13, '2号楼4层B区');

-- ----------------------------
-- Table structure for medical_dept_sub_doctor
-- ----------------------------
DROP TABLE IF EXISTS `medical_dept_sub_doctor`;
CREATE TABLE `medical_dept_sub_doctor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_sub_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_dept_sub_doctor
-- ----------------------------
INSERT INTO `medical_dept_sub_doctor` VALUES (1, 2, 1);
INSERT INTO `medical_dept_sub_doctor` VALUES (2, 20, 2);
INSERT INTO `medical_dept_sub_doctor` VALUES (3, 9, 3);
INSERT INTO `medical_dept_sub_doctor` VALUES (4, 19, 4);
INSERT INTO `medical_dept_sub_doctor` VALUES (5, 26, 5);
INSERT INTO `medical_dept_sub_doctor` VALUES (6, 26, 6);
INSERT INTO `medical_dept_sub_doctor` VALUES (7, 4, 7);
INSERT INTO `medical_dept_sub_doctor` VALUES (8, 26, 8);
INSERT INTO `medical_dept_sub_doctor` VALUES (9, 20, 9);
INSERT INTO `medical_dept_sub_doctor` VALUES (10, 12, 10);
INSERT INTO `medical_dept_sub_doctor` VALUES (11, 14, 11);
INSERT INTO `medical_dept_sub_doctor` VALUES (12, 14, 12);
INSERT INTO `medical_dept_sub_doctor` VALUES (13, 14, 13);
INSERT INTO `medical_dept_sub_doctor` VALUES (14, 13, 14);
INSERT INTO `medical_dept_sub_doctor` VALUES (15, 26, 15);
INSERT INTO `medical_dept_sub_doctor` VALUES (16, 2, 16);
INSERT INTO `medical_dept_sub_doctor` VALUES (17, 2, 17);
INSERT INTO `medical_dept_sub_doctor` VALUES (18, 2, 18);
INSERT INTO `medical_dept_sub_doctor` VALUES (20, 1, 20);
INSERT INTO `medical_dept_sub_doctor` VALUES (24, 8, 26);
INSERT INTO `medical_dept_sub_doctor` VALUES (25, 9, 27);
INSERT INTO `medical_dept_sub_doctor` VALUES (26, 23, 28);
INSERT INTO `medical_dept_sub_doctor` VALUES (27, 21, 29);
INSERT INTO `medical_dept_sub_doctor` VALUES (28, 20, 30);
INSERT INTO `medical_dept_sub_doctor` VALUES (29, 4, 6);
INSERT INTO `medical_dept_sub_doctor` VALUES (30, 4, 5);
INSERT INTO `medical_dept_sub_doctor` VALUES (31, 3, 31);
INSERT INTO `medical_dept_sub_doctor` VALUES (32, 0, 32);
INSERT INTO `medical_dept_sub_doctor` VALUES (35, 2, 35);
INSERT INTO `medical_dept_sub_doctor` VALUES (36, 1, 36);
INSERT INTO `medical_dept_sub_doctor` VALUES (37, 19, 37);
INSERT INTO `medical_dept_sub_doctor` VALUES (38, 3, 38);
INSERT INTO `medical_dept_sub_doctor` VALUES (39, 1, 39);
INSERT INTO `medical_dept_sub_doctor` VALUES (40, 23, 40);
INSERT INTO `medical_dept_sub_doctor` VALUES (41, 1, 41);
INSERT INTO `medical_dept_sub_doctor` VALUES (42, 4, 42);
INSERT INTO `medical_dept_sub_doctor` VALUES (43, 2, 43);
INSERT INTO `medical_dept_sub_doctor` VALUES (44, 1, 44);
INSERT INTO `medical_dept_sub_doctor` VALUES (45, 18, 45);
INSERT INTO `medical_dept_sub_doctor` VALUES (46, 11, 46);
INSERT INTO `medical_dept_sub_doctor` VALUES (47, 4, 47);
INSERT INTO `medical_dept_sub_doctor` VALUES (48, 0, 48);
INSERT INTO `medical_dept_sub_doctor` VALUES (49, 4, 49);
INSERT INTO `medical_dept_sub_doctor` VALUES (50, 0, 50);
INSERT INTO `medical_dept_sub_doctor` VALUES (51, 7, 51);
INSERT INTO `medical_dept_sub_doctor` VALUES (52, 0, 52);
INSERT INTO `medical_dept_sub_doctor` VALUES (53, 1, 53);
INSERT INTO `medical_dept_sub_doctor` VALUES (54, 1, 54);
INSERT INTO `medical_dept_sub_doctor` VALUES (55, 0, 55);
INSERT INTO `medical_dept_sub_doctor` VALUES (56, 0, 56);
INSERT INTO `medical_dept_sub_doctor` VALUES (57, 0, 57);
INSERT INTO `medical_dept_sub_doctor` VALUES (58, 0, 58);
INSERT INTO `medical_dept_sub_doctor` VALUES (59, 0, 59);
INSERT INTO `medical_dept_sub_doctor` VALUES (60, 7, 60);
INSERT INTO `medical_dept_sub_doctor` VALUES (61, 16, 61);
INSERT INTO `medical_dept_sub_doctor` VALUES (62, 2, 62);
INSERT INTO `medical_dept_sub_doctor` VALUES (63, 7, 63);
INSERT INTO `medical_dept_sub_doctor` VALUES (64, 3, 64);
INSERT INTO `medical_dept_sub_doctor` VALUES (65, 4, 65);
INSERT INTO `medical_dept_sub_doctor` VALUES (66, 5, 66);
INSERT INTO `medical_dept_sub_doctor` VALUES (67, 1, 68);
INSERT INTO `medical_dept_sub_doctor` VALUES (68, 1, 69);
INSERT INTO `medical_dept_sub_doctor` VALUES (69, 1, 70);
INSERT INTO `medical_dept_sub_doctor` VALUES (70, 0, 72);
INSERT INTO `medical_dept_sub_doctor` VALUES (71, 7, 73);
INSERT INTO `medical_dept_sub_doctor` VALUES (72, 0, 74);
INSERT INTO `medical_dept_sub_doctor` VALUES (73, 4, 75);
INSERT INTO `medical_dept_sub_doctor` VALUES (74, 7, 76);
INSERT INTO `medical_dept_sub_doctor` VALUES (75, 11, 77);
INSERT INTO `medical_dept_sub_doctor` VALUES (76, 19, 78);
INSERT INTO `medical_dept_sub_doctor` VALUES (77, 19, 79);
INSERT INTO `medical_dept_sub_doctor` VALUES (78, 19, 80);
INSERT INTO `medical_dept_sub_doctor` VALUES (79, 11, 81);
INSERT INTO `medical_dept_sub_doctor` VALUES (80, 1, 82);
INSERT INTO `medical_dept_sub_doctor` VALUES (81, 19, 83);
INSERT INTO `medical_dept_sub_doctor` VALUES (82, 11, 84);
INSERT INTO `medical_dept_sub_doctor` VALUES (83, 1, 85);
INSERT INTO `medical_dept_sub_doctor` VALUES (84, 1, 86);
INSERT INTO `medical_dept_sub_doctor` VALUES (85, 2, 87);

-- ----------------------------
-- Table structure for medical_registration
-- ----------------------------
DROP TABLE IF EXISTS `medical_registration`;
CREATE TABLE `medical_registration`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_card_id` int(11) NULL DEFAULT NULL,
  `work_plan_id` int(11) NULL DEFAULT NULL,
  `doctor_schedule_id` int(11) NULL DEFAULT NULL,
  `doctor_id` int(11) NULL DEFAULT NULL,
  `dept_sub_id` int(11) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `slot` tinyint(4) NULL DEFAULT NULL,
  `amount` decimal(10, 2) NULL DEFAULT NULL,
  `out_trade_no` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `prepay_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `payment_status` tinyint(4) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medical_registration
-- ----------------------------

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块编号',
  `module_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_module_id`(`module_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模块资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES (1, 'USER', '用户管理');
INSERT INTO `module` VALUES (2, 'EMPLOYEE', '员工管理');
INSERT INTO `module` VALUES (3, 'DEPT', '部门管理');
INSERT INTO `module` VALUES (4, 'MEETING', '会议管理');
INSERT INTO `module` VALUES (5, 'WORKFLOW', '工作流管理');
INSERT INTO `module` VALUES (6, 'MEETING_ROOM', '会议室管理');
INSERT INTO `module` VALUES (7, 'ROLE', '角色管理');
INSERT INTO `module` VALUES (8, 'LEAVE', '请假管理');
INSERT INTO `module` VALUES (9, 'FILE', '诊室管理');
INSERT INTO `module` VALUES (10, 'AMECT', '科室管理');
INSERT INTO `module` VALUES (11, 'REIM', '医生管理');

-- ----------------------------
-- Table structure for patient_face_auth
-- ----------------------------
DROP TABLE IF EXISTS `patient_face_auth`;
CREATE TABLE `patient_face_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_card_id` int(11) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient_face_auth
-- ----------------------------

-- ----------------------------
-- Table structure for patient_user
-- ----------------------------
DROP TABLE IF EXISTS `patient_user`;
CREATE TABLE `patient_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient_user
-- ----------------------------
INSERT INTO `patient_user` VALUES (3, 'ociqy6_5hhmjVvMdCB5zq0PXTrdA', '喵喵', 'https://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEIFyVQYGJ8G1tgzm3mKZHFKSb1arL86xaoktNFEibiceoPGB3OBBIpOxzG6rB3eVjnaNLcrzJ0tc7ApLXyqv6n2Zia2Wa2lHibPYBIC5UkpV2KHtg/132', '男', 1, '2024-04-26 14:43:52');
INSERT INTO `patient_user` VALUES (4, 'opowR7fOSJ64x4d_J8Y6saPu6APg', '比屋教育_王老师', 'https://thirdwx.qlogo.cn/mmopen/vi_32/zvvtvGbucBurxNdiaIiapwrmk4cJ43uefHJvciaicMwlL3sKeDe0nv0nUZNtgTDhgI0QiaIlR47H3MRZFkG9GsjoLETFWbPYiapnJ2wym2CAVOmeM/132', '男', 1, '2024-04-28 21:40:46');
INSERT INTO `patient_user` VALUES (5, 'olYkc44eJgx-lm1NB_VGWpvYoLIU', '王老师', 'https://thirdwx.qlogo.cn/mmopen/vi_32/psbF5siazCGH0icGhSLoBJQr3ESf3xKnlZqtLGLpCqnFSW2MmBt6t5mfrjaUEqVliaw5iaba4hhYzSBJaJrHp1wfqmkB8BwiaSID8QhHzWD93f8g/132', '男', 1, '2024-04-29 10:12:13');
INSERT INTO `patient_user` VALUES (6, 'oLdPw5PtSKCPJRLbYWeHLm8PJ2fE', '微信用户', 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', '男', 1, '2024-06-24 11:28:40');
INSERT INTO `patient_user` VALUES (7, 'o56Mf7VAAGJdK7gZRIoZXrdwjrFk', '2003.秋', 'https://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEKlDFzNk8WjzU9tmYDewSnGXqn3gHiaA3mMSmRTBzrtNVmSiah1uofyOrGiaCNST6ToddNrzs0KA2sjOicEzlVY8via9KsAdlNA3nic3icR2cwtIicX5Q/132', '男', 1, '2024-07-02 16:27:00');

-- ----------------------------
-- Table structure for patient_user_info
-- ----------------------------
DROP TABLE IF EXISTS `patient_user_info`;
CREATE TABLE `patient_user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `uuid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pid` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `medical_history` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `insurance_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `exist_face_model` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient_user_info
-- ----------------------------
INSERT INTO `patient_user_info` VALUES (3, 3, '0ffede5e73e044a79c0a62ee8748cfb1', '赵六', '男', '421081199204014288', '18504852154', '1994-01-01', '[\"其他\"]', '社会基本医疗保险', 1);
INSERT INTO `patient_user_info` VALUES (4, 6, '6424ccffb77149239531726a6067b3cb', '阿狸', '男', '522424200106224854', '12233104410', '1923-01-01', '[\"无\"]', '社会基本医疗保险', 0);
INSERT INTO `patient_user_info` VALUES (5, 5, '5d001a28b1b6405183092ca0c64d69ef', '张三', '男', '210102199909191111', '13117171891', '1995-01-01', '[\"无\"]', '城镇居民医疗保险', 0);
INSERT INTO `patient_user_info` VALUES (6, 7, 'c30b2ebb6e574008bd4852127d1cc29f', '张三', '男', '360302200605063039', '17966566630', '1900-01-01', '[\"脑中风\",\"糖尿病\",\"心脏病\"]', '社会基本医疗保险', 0);
INSERT INTO `patient_user_info` VALUES (7, 4, 'e285ec6eed3a49069c5efd7e67062cd4', '王梦凡', '女', '130622199204132023', '15530260413', '1900-01-01', '[\"高血压\"]', '社会基本医疗保险', 0);

-- ----------------------------
-- Table structure for patient_video_diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `patient_video_diagnosis`;
CREATE TABLE `patient_video_diagnosis`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_card_id` int(11) NULL DEFAULT NULL,
  `doctor_id` int(11) NULL DEFAULT NULL,
  `out_trade_no` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` decimal(10, 2) NULL DEFAULT NULL,
  `payment_status` tinyint(4) NULL DEFAULT NULL,
  `prepay_id` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transaction_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expect_start` date NULL DEFAULT NULL,
  `expect_end` date NULL DEFAULT NULL,
  `real_start` date NULL DEFAULT NULL,
  `real_end` date NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient_video_diagnosis
-- ----------------------------

-- ----------------------------
-- Table structure for patient_video_diagnosis_files
-- ----------------------------
DROP TABLE IF EXISTS `patient_video_diagnosis_files`;
CREATE TABLE `patient_video_diagnosis_files`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_diagnose_id` int(11) NULL DEFAULT NULL,
  `filename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient_video_diagnosis_files
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `module_id` int(10) UNSIGNED NOT NULL COMMENT '模块ID',
  `action_id` int(10) UNSIGNED NOT NULL COMMENT '行为ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_permission`(`permission_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'USER:INSERT', 1, 1);
INSERT INTO `permission` VALUES (2, 'USER:DELETE', 1, 2);
INSERT INTO `permission` VALUES (3, 'USER:UPDATE', 1, 3);
INSERT INTO `permission` VALUES (4, 'USER:SELECT', 1, 4);
INSERT INTO `permission` VALUES (5, 'EMPLOYEE:INSERT', 2, 1);
INSERT INTO `permission` VALUES (6, 'EMPLOYEE:DELETE', 2, 2);
INSERT INTO `permission` VALUES (7, 'EMPLOYEE:UPDATE', 2, 3);
INSERT INTO `permission` VALUES (8, 'EMPLOYEE:SELECT', 2, 4);
INSERT INTO `permission` VALUES (9, 'DEPT:INSERT', 3, 1);
INSERT INTO `permission` VALUES (10, 'DEPT:DELETE', 3, 2);
INSERT INTO `permission` VALUES (11, 'DEPT:UPDATE', 3, 3);
INSERT INTO `permission` VALUES (12, 'DEPT:SELECT', 3, 4);
INSERT INTO `permission` VALUES (13, 'MEETING:INSERT', 4, 1);
INSERT INTO `permission` VALUES (14, 'MEETING:DELETE', 4, 2);
INSERT INTO `permission` VALUES (15, 'MEETING:UPDATE', 4, 3);
INSERT INTO `permission` VALUES (16, 'MEETING:SELECT', 4, 4);
INSERT INTO `permission` VALUES (17, 'WORKFLOW:APPROVAL', 5, 5);
INSERT INTO `permission` VALUES (19, 'MEETING_ROOM:INSERT', 6, 1);
INSERT INTO `permission` VALUES (20, 'MEETING_ROOM:DELETE', 6, 2);
INSERT INTO `permission` VALUES (21, 'MEETING_ROOM:UPDATE', 6, 3);
INSERT INTO `permission` VALUES (22, 'MEETING_ROOM:SELECT', 6, 4);
INSERT INTO `permission` VALUES (23, 'ROLE:INSERT', 7, 1);
INSERT INTO `permission` VALUES (24, 'ROLE:DELETE', 7, 2);
INSERT INTO `permission` VALUES (25, 'ROLE:UPDATE', 7, 3);
INSERT INTO `permission` VALUES (26, 'ROLE:SELECT', 7, 4);
INSERT INTO `permission` VALUES (27, 'LEAVE:SELECT', 8, 4);
INSERT INTO `permission` VALUES (28, 'FILE:ARCHIVE', 9, 8);
INSERT INTO `permission` VALUES (29, 'AMECT:INSERT', 10, 1);
INSERT INTO `permission` VALUES (30, 'AMECT:DELETE', 10, 2);
INSERT INTO `permission` VALUES (31, 'AMECT:UPDATE', 10, 3);
INSERT INTO `permission` VALUES (32, 'AMECT:SELECT', 10, 4);
INSERT INTO `permission` VALUES (33, 'REIM:INSERT', 11, 1);
INSERT INTO `permission` VALUES (34, 'REIM:DELETE', 11, 2);
INSERT INTO `permission` VALUES (35, 'REIM:UPDATE', 11, 3);
INSERT INTO `permission` VALUES (36, 'REIM:SELECT', 11, 4);
INSERT INTO `permission` VALUES (38, 'ROOT', 0, 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `permissions` json NOT NULL COMMENT '权限集合',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `default_permissions` json NULL COMMENT '系统角色内置权限',
  `systemic` tinyint(1) NULL DEFAULT 0 COMMENT '是否为系统内置角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '院长', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 27, 29, 30, 31, 32]', '院长职责描述信息', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 27]', 1);
INSERT INTO `role` VALUES (2, '科室主任', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 27, 29, 30, 31, 32]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 27]', 1);
INSERT INTO `role` VALUES (3, '医师', '[1, 2, 3, 4, 5, 6, 7, 8]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8]', 1);
INSERT INTO `role` VALUES (4, 'HR', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 28, 27]', 1);
INSERT INTO `role` VALUES (5, '财务', '[1, 2, 3, 4, 5, 6, 7, 8, 28, 36, 17]', NULL, '[1, 2, 3, 4, 5, 6, 7, 8, 28, 36, 17]', 1);
INSERT INTO `role` VALUES (6, '测试角色', '[36, 17]', '测试角色', '[36, 17]', 1);
INSERT INTO `role` VALUES (7, '超级管理员', '[38]', '超级管理员用户不能删除和修改', '[38]', 1);
INSERT INTO `role` VALUES (11, '测试3', '[5, 6, 16, 19]', '测试3', NULL, 0);
INSERT INTO `role` VALUES (12, '测试4', '[9, 10, 11, 12]', '测试4', NULL, 0);
INSERT INTO `role` VALUES (13, '测试111', '[1, 2, 30, 31, 32]', '测试111', NULL, 0);
INSERT INTO `role` VALUES (14, '测试5', '[1, 2, 3, 4, 5, 6, 9, 10]', '测试5', NULL, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `open_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '长期授权字符串',
  `photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像网址',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `tel` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `job` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `role` json NOT NULL COMMENT '角色',
  `root` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是超级管理员',
  `dept_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '部门编号',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `ref_id` int(11) NULL DEFAULT NULL COMMENT '关联的docker_id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unq_open_id`(`open_id`) USING BTREE,
  UNIQUE INDEX `unq_username`(`username`) USING BTREE,
  INDEX `unq_email`(`email`) USING BTREE,
  INDEX `idx_dept_id`(`dept_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '541415805af45b2f9bafad16c893f2ce', NULL, NULL, 'admin', '男', '15055555551', 'admin@163.com', '医生', '[7]', 0, 1, 1, '2024-10-29 12:52:18', 1);
INSERT INTO `users` VALUES (2, 'zhangsan', '7044670653a6ff0662d4def3e2dd4979', NULL, NULL, 'zhangsan', '男', '15055555552', 'zhangsan@163.com', '医生', '[7]', 0, 5, 1, '2024-10-29 12:52:17', 1);
INSERT INTO `users` VALUES (3, 'lisi', 'd1c3ef3d450532d24ab676e8edd05184', NULL, NULL, 'lisi', '男', '15055555553', 'lisi@163.com', '医生', '[3, 4, 5]', 0, 5, 1, '2024-09-27 16:44:15', 1);
INSERT INTO `users` VALUES (4, 'lisi1', '841A3261CDB4F1282B81A1C9C74BE834', NULL, NULL, '李四一', '男', '15055555553', 'lisi@163.com', '医生', '[5, 4]', 0, 3, 1, '2024-09-27 16:44:16', 1);
INSERT INTO `users` VALUES (8, 'lisi5', '31C8A1C1A8B31F67BB6953859BB76FB0', NULL, NULL, 'lisi5', '男', '15055555553', 'lisi@163.com', '医生', '[3]', 0, 5, 1, '2024-09-27 16:44:17', 1);
INSERT INTO `users` VALUES (14, 'ceshi1', '73CC687025F3F190CD02F3315634381D', NULL, NULL, '测试一', '男', '15055555553', '432@qq.com', '医生', '[3, 4, 5]', 0, 2, 1, '2024-09-27 16:44:18', 1);
INSERT INTO `users` VALUES (18, 'zhaoliu', '1275D389213EC634AC4299F130BF685F', NULL, NULL, '赵六', '女', '15055555553', '432@qq.com', '医生', '[2]', 0, 3, 1, '2024-09-27 16:44:19', 1);
INSERT INTO `users` VALUES (19, 'shunqi', 'AB3C7A427BE783CEDD574202C57A320D', NULL, NULL, '孙七', '女', '15055555553', '432@qq.com', '医生', '[5]', 0, 4, 1, '2024-09-27 16:44:19', 1);
INSERT INTO `users` VALUES (20, 'linan', '189DCCF8BCF7FA1ABE5C5913728AF04F', NULL, NULL, '李楠', '男', '15055555553', '432@qq.com', '医生', '[1]', 0, 5, 1, '2024-09-27 16:44:20', 1);
INSERT INTO `users` VALUES (21, 'xiaowang', 'AA244867DAF5B2B5D4BB3CB0F3C1F4EB', NULL, NULL, '小王', '男', '15055555553', '432@qq.com', '医生', '[3, 5]', 0, 6, 1, '2024-09-27 16:44:21', 1);

SET FOREIGN_KEY_CHECKS = 1;
