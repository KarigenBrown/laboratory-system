-- MySQL dump 10.13  Distrib 8.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: web
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `web_achievement`
--

DROP TABLE IF EXISTS `web_achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_achievement` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT '' COMMENT '新闻标题',
  `journal` varchar(100) DEFAULT '' COMMENT '期刊',
  `author` varchar(100) DEFAULT '' COMMENT '第一作者',
  `authors` varchar(100) DEFAULT '' COMMENT '其他作者',
  `date` varchar(100) DEFAULT '' COMMENT '时间',
  `link` varchar(100) DEFAULT '详情页链接',
  `papercode` varchar(100) DEFAULT '代码',
  `theyear` varchar(10) DEFAULT '' COMMENT '论文年份',
  `abstract` text COMMENT '摘要',
  `category` varchar(100) NOT NULL COMMENT '类别',
  `initials` varchar(255) DEFAULT '论文首字母',
  `internal` varchar(1) DEFAULT '1' COMMENT '是否为实验室内部论文',
  `article_status` int DEFAULT '0' COMMENT '论文状态',
  `hidden` varchar(1) DEFAULT '0' COMMENT '是否隐藏',
  `technique_status` int DEFAULT '0' COMMENT '技术状态',
  `achievement_category` varchar(100) DEFAULT '' COMMENT '类别',
  `address` varchar(100) DEFAULT '' COMMENT '地址',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除标志(0代表未删除,1代表已删除)',
  `create_by` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `log` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb3 COMMENT='论文管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_achievement`
--

LOCK TABLES `web_achievement` WRITE;
/*!40000 ALTER TABLE `web_achievement` DISABLE KEYS */;
INSERT INTO `web_achievement` VALUES (98,'无人机小目标综述','计算机仿真','张立丽','佟强，刘秀磊','','1','1','2022',NULL,'论文','jsjfz','1',3,'0',1,'','',0,4,'2024-05-16 13:12:14',4,'2024-05-16 13:16:23','\n2024-05-16 01:16:20:  发布'),(99,'基于生成对抗网络的三维空间民航轨迹预测模型','计算机应用与软件','曹建制','佟强，陈玉立，刘秀磊','2022-11-05','1','1','2022',NULL,'论文','jsjyyyrj','0',3,'0',1,'','',0,4,'2024-05-16 13:27:18',4,'2024-05-16 13:41:12','\n2024-05-16 01:27:15:  发布'),(100,'基于DCNN和GLU的武器领域实体关系抽取方法研究','计算机科学','李晗','侯守璐，佟强，谌彤童，杨启民，刘秀磊','2022-11-05','1','1','2022',NULL,'论文','jsjkx','1',3,'0',1,'','',0,4,'2024-05-16 14:10:58',4,'2024-05-17 15:03:03',NULL),(102,'A Prediction Approach for Video Hits in Mobile Edge Computing Environment','Security and Communication Networks','Xiulei Liu','Shoulu Hou，Qiang Tong，Zhihui Qin，Junyang Yu','2020-12-08','1','1','2020',NULL,'论文','sacn','1',3,'0',1,'','',0,4,'2024-05-16 14:17:54',4,'2024-05-17 15:03:06',NULL),(103,'专利1','期刊1','作者1','作者们','2024-05-16','1','1','2024',NULL,'专利','论文首字母','1',1,'0',1,'','',0,4,'2024-05-16 14:27:54',4,'2024-05-16 14:27:54',NULL),(104,'著作1','著作1','作者1','作者','2024-05-16','1','1','2024',NULL,'著作','论文首字母','1',1,'0',1,'','',0,4,'2024-05-16 17:04:47',4,'2024-05-16 17:04:47',NULL),(105,'软著1','软著1','作者1','作者','2024-05-16','1','1','2024',NULL,'软著','论文首字母','1',1,'0',1,'','',0,4,'2024-05-16 17:05:29',4,'2024-05-16 17:05:29',NULL),(106,'技术标准1','技术标准1','作者1','作者','2024-05-16','1','1','2024',NULL,'技术标准','论文首字母','1',1,'0',2,'','',0,4,'2024-05-16 17:06:16',4,'2024-05-16 17:06:16','\n2024-05-16 05:06:14:  申请中'),(107,'获奖竞赛1','竞赛1','','','2024-05-16','1','1','2024',NULL,'竞赛获奖','论文首字母','1',1,'0',1,'','',0,4,'2024-05-16 17:07:06',4,'2024-05-16 17:07:06',NULL);
/*!40000 ALTER TABLE `web_achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_activity`
--

DROP TABLE IF EXISTS `web_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_activity` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `urls` text COMMENT '换行分割',
  `introduction` text NOT NULL,
  `content` text NOT NULL,
  `date` date NOT NULL,
  `deleted` int DEFAULT '0' COMMENT '逻辑删除标志(0代表未删除,1代表已删除)',
  `create_by` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_activity`
--

LOCK TABLES `web_activity` WRITE;
/*!40000 ALTER TABLE `web_activity` DISABLE KEYS */;
INSERT INTO `web_activity` VALUES (8,'活动1','http://localhost:9000/web/activityPhoto/%E6%B4%BB%E5%8A%A81/2024-05-15_%E7%9B%B8%E5%85%B3%E6%B4%BB%E5%8A%A8.jpg','1','1','2024-05-21',0,4,'2024-05-16 19:53:54',4,'2024-05-16 19:53:54');
/*!40000 ALTER TABLE `web_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_demo`
--

DROP TABLE IF EXISTS `web_demo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_demo` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `time` date NOT NULL,
  `group` varchar(255) NOT NULL,
  `introduction` text NOT NULL,
  `video_urls` text COMMENT '换行分割',
  `photo_urls` text COMMENT '换行分割',
  `location` varchar(255) NOT NULL DEFAULT '',
  `content` text,
  `keywords` varchar(255) NOT NULL DEFAULT '' COMMENT ',分割',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除标志(0代表未删除,1代表已删除)',
  `create_by` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_demo`
--

LOCK TABLES `web_demo` WRITE;
/*!40000 ALTER TABLE `web_demo` DISABLE KEYS */;
INSERT INTO `web_demo` VALUES (4,'遥感光学图像目标检测Demo','2022-09-01','工程2组','遥感光学图像目标检测是一种遥感技术，旨在从卫星、航空或无人机等平台获取的光学图像中自动识别和定位地表上的目标或对象。这些目标可以包括建筑物、车辆、植被、水体、道路、城市区域等等。目标检测是遥感图像处理和分析的关键任务之一，具有广泛的应用领域，包括军事情报、城市规划、环境监测、农业管理、自然灾害管理等。\n\n遥感光学图像目标检测在军事情报领域用于敌军目标识别，也在民用领域用于城市规划、资源管理、环境保护等众多应用中发挥着重要作用。随着深度学习技术的发展，目标检测的准确性和效率得到了显著提高，为更广泛的遥感应用提供了更多可能性。','http://localhost:9000/web/demo/%E9%81%A5%E6%84%9F%E5%85%89%E5%AD%A6%E5%9B%BE%E5%83%8F%E7%9B%AE%E6%A0%87%E6%A3%80%E6%B5%8BDemo/video/%E6%95%B0%E6%8D%AE%E7%A7%91%E5%AD%A6%E4%B8%8E%E6%83%85%E6%8A%A5%E5%88%86%E6%9E%90%E5%AE%9E%E9%AA%8C%E5%AE%A4%20_%20%E9%A1%B9%E7%9B%AEDemo.mp4','http://localhost:9000/web/demo/%E9%81%A5%E6%84%9F%E5%85%89%E5%AD%A6%E5%9B%BE%E5%83%8F%E7%9B%AE%E6%A0%87%E6%A3%80%E6%B5%8BDemo/photo/2024-05-07_%E7%9B%B8%E5%85%B3%E6%8F%8F%E8%BF%B0.jpg','北京信息科技大学数据科学与情报分析实验室','111','遥感航空图像,光学图像',0,4,'2024-05-16 19:50:21',4,'2024-05-17 15:03:34');
/*!40000 ALTER TABLE `web_demo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_log`
--

DROP TABLE IF EXISTS `web_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `userid` int NOT NULL,
  `log` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_log`
--

LOCK TABLES `web_log` WRITE;
/*!40000 ALTER TABLE `web_log` DISABLE KEYS */;
INSERT INTO `web_log` VALUES (1,'2024-04-19 11:21:08',-1,'URL          : http://localhost:8081/webManager/login\nBusiness Name: 登录\nHTTP Method  : POST\nClass Method : edu.bistu.controller.WebManagerController.login\nIP           : 0:0:0:0:0:0:0:1\nArgs         : args\nResult       : r\n');
/*!40000 ALTER TABLE `web_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_manager`
--

DROP TABLE IF EXISTS `web_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_manager` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(100) NOT NULL COMMENT '学工号',
  `username` varchar(50) DEFAULT '' COMMENT '账号',
  `password` text COMMENT '密码',
  `permits` varchar(100) DEFAULT '个人' COMMENT '权限',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除标志(0代表未删除,1代表已删除)',
  `create_by` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_manager`
--

LOCK TABLES `web_manager` WRITE;
/*!40000 ALTER TABLE `web_manager` DISABLE KEYS */;
INSERT INTO `web_manager` VALUES (4,'111111','karigen','$2a$10$L0MaA6bSRmAQ5l1GuU.Uq.vGiOekZAQN0yxKOPYhtOMD2OswvJ/1W','个人信息管理',0,NULL,NULL,4,'2024-04-19 13:04:16'),(5,'333333','k','$2a$10$6l6qcCMxaxXks3xnGi2E.OTJoaLBnjkL9jJLENz55V9DqLrs6b7fC','个人信息管理,成员管理,Demo管理',0,-1,'2024-04-19 13:20:52',4,'2024-05-16 22:13:40'),(6,'222222','test2','$2a$10$p5JlbDLjmVRBEEwwOqGSzeYezILiX6nj6gE8h2ORMr7MbCMWat3f6','个人',0,-1,'2024-05-16 22:49:28',-1,'2024-05-16 22:49:28');
/*!40000 ALTER TABLE `web_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_member`
--

DROP TABLE IF EXISTS `web_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_member` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `identity` varchar(100) DEFAULT '' COMMENT '身份',
  `name` varchar(100) DEFAULT '' COMMENT '姓名',
  `contact` varchar(100) DEFAULT '' COMMENT '联系方式',
  `research` varchar(100) DEFAULT '' COMMENT '研究方向',
  `achievement` text COMMENT '科研成果',
  `introduction` text COMMENT '个人经历',
  `number` varchar(100) NOT NULL COMMENT '学工号',
  `grade` varchar(50) DEFAULT NULL COMMENT '在校生年级',
  `hidden_fields` varchar(255) DEFAULT '' COMMENT '隐藏字段,分割',
  `photo_url` varchar(255) NOT NULL DEFAULT '' COMMENT '照片链接',
  `graduation_destination` varchar(255) DEFAULT '' COMMENT '毕业去向',
  `graduation_time` date DEFAULT NULL COMMENT '毕业时间',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除标志(0代表未删除,1代表已删除)',
  `create_by` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb3 COMMENT='成员管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_member`
--

LOCK TABLES `web_member` WRITE;
/*!40000 ALTER TABLE `web_member` DISABLE KEYS */;
INSERT INTO `web_member` VALUES (47,'教授','karigen','222','222','111333','111','111111',NULL,'contact','http://localhost:9000/web/memberPhoto/111111.jpg','',NULL,0,NULL,NULL,4,'2024-05-16 22:32:33'),(48,'在校生','k','','',NULL,NULL,'333333','研一','','http://localhost:9000/web/memberPhoto/333333.jpg','',NULL,0,-1,'2024-04-19 13:20:52',4,'2024-05-16 14:24:37'),(49,'副教授','test2','','',NULL,NULL,'222222',NULL,'','http://localhost:9000/web/memberPhoto/222222.jpg','',NULL,0,-1,'2024-05-16 22:49:28',6,'2024-05-16 22:49:53');
/*!40000 ALTER TABLE `web_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_project`
--

DROP TABLE IF EXISTS `web_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_project` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '' COMMENT '项目名称',
  `link` varchar(100) DEFAULT '' COMMENT '项目链接',
  `theyear` varchar(10) DEFAULT '' COMMENT '项目年份',
  `content` text,
  `status` int DEFAULT '0' COMMENT '项目状态',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除标志(0代表未删除,1代表已删除)',
  `create_by` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `log` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_project`
--

LOCK TABLES `web_project` WRITE;
/*!40000 ALTER TABLE `web_project` DISABLE KEYS */;
INSERT INTO `web_project` VALUES (2,'项目1','1','2020','1',1,0,4,'2024-05-16 14:24:02',4,'2024-05-16 14:24:02',NULL);
/*!40000 ALTER TABLE `web_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_raw_member`
--

DROP TABLE IF EXISTS `web_raw_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_raw_member` (
  `number` varchar(100) NOT NULL COMMENT '学工号',
  `identity` varchar(100) NOT NULL COMMENT '身份',
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_raw_member`
--

LOCK TABLES `web_raw_member` WRITE;
/*!40000 ALTER TABLE `web_raw_member` DISABLE KEYS */;
INSERT INTO `web_raw_member` VALUES ('1','1'),('10','1'),('111111','教授'),('123456','教授'),('2','1'),('222222','副教授'),('3','1'),('333333','在校生'),('4','1'),('444444','毕业生'),('5','1'),('6','1'),('7','1'),('8','1'),('9','1');
/*!40000 ALTER TABLE `web_raw_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_visitors`
--

DROP TABLE IF EXISTS `web_visitors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_visitors` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `visitornum` int DEFAULT '0' COMMENT '访问量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='访问管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_visitors`
--

LOCK TABLES `web_visitors` WRITE;
/*!40000 ALTER TABLE `web_visitors` DISABLE KEYS */;
/*!40000 ALTER TABLE `web_visitors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17  8:40:55
