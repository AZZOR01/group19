-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: dz_coach
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coach_id` int DEFAULT NULL COMMENT '教练id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `txt` text COMMENT '内容',
  `state` int DEFAULT '0' COMMENT '状态,0未确认，1已确认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,1,1,'User1','Feedback 1',0),(2,1,2,'User2','Feedback 2',1),(3,2,3,'User3','Feedback 3',0),(4,2,4,'User4','Feedback 4',1),(5,3,5,'User5','Feedback 5',0),(6,3,6,'User6','Feedback 6',1),(7,4,7,'User7','Feedback 7',1),(8,4,8,'User8','Feedback 8',0),(9,5,9,'User9','Feedback 9',1),(10,5,10,'User10','Feedback 10',1),(11,6,11,'User11','Feedback 11',0),(12,6,12,'User12','Feedback 12',0),(13,7,13,'User13','Feedback 13',1),(14,7,14,'User14','Feedback 14',1),(15,8,15,'User15','Feedback 15',0),(16,8,16,'User16','Feedback 16',1),(17,9,17,'User17','Feedback 17',0),(18,9,18,'User18','Feedback 18',0),(19,10,19,'User19','Feedback 19',1),(20,10,20,'User20','Feedback 20',1),(21,1,13,'1111','good',0),(22,1,18,'XiaoTongYu','good !',0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `form` varchar(100) DEFAULT NULL COMMENT '来自',
  `toUser` int DEFAULT '-1' COMMENT '发送给，-1为所有人',
  `txt` text COMMENT '内容',
  `state` int DEFAULT '0' COMMENT '0未读1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'法律声明',-1,'本软件将收集您的^%&*$*&^&^$&%^*#$\nasdasd',0),(41,'Membership expired',2,'Your membership has expired and has been reset to zero. Please reorder',1),(42,'Membership expired',2,'Your membership has expired and has been reset to zero. Please reorder',1),(43,'Appointment failed',1,'Your appointment from Tue Jan 20 02:03:20 HKT 1970 to Tue Jan 20 02:05:00 HKT 1970 was cancelled by the coach. Sorry, please make a new appointment',1),(44,'Appointment failed',2,'Your appointment from Wed Jan 21 04:11:36 HKT 1970 to Wed Jan 21 04:11:51 HKT 1970 was cancelled by the coach. Sorry, please make a new appointment',1),(46,'Appointment Notification',1,'You have a new course appointment at Wed Jan 21 04:13:34 HKT 1970 to Wed Jan 21 04:13:38 HKT 1970 please confirm immediately.',1),(47,'Appointment successful',1,'Your scheduled classes from Wed Jan 21 04:13:34 HKT 1970 to Wed Jan 21 04:13:38 HKT 1970 have been successfully booked. Please go to the designated location for classes immediately',1),(48,'Appointment successful',13,'Your scheduled classes from Wed Jan 21 04:13:34 HKT 1970 to Wed Jan 21 04:13:38 HKT 1970 have been successfully booked. Please go to the designated location for classes immediately',1),(49,'Appointment Notification',6,'You have a new course appointment at Wed Jan 21 04:15:44 HKT 1970 to Wed Jan 21 04:15:48 HKT 1970 please confirm immediately.',0),(50,'Appointment Notification',1,'You have a new course appointment at Wed Jan 21 04:17:22 HKT 1970 to Wed Jan 21 04:17:25 HKT 1970 please confirm immediately.',1),(51,'Appointment successful',1,'Your scheduled classes from Wed Jan 21 04:17:22 HKT 1970 to Wed Jan 21 04:17:25 HKT 1970 have been successfully booked. Please go to the designated location for classes immediately',1);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '时间',
  `user_id` int DEFAULT NULL COMMENT 'userId',
  `name` varchar(100) DEFAULT NULL COMMENT 'user姓名',
  `frequency` int DEFAULT '0' COMMENT '购买次数',
  `money` float DEFAULT '0' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1714455007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1714311415,2,'xty',100,90),(1714314717,2,'xty',100,90),(1714314744,2,'xty',30,30),(1714318599,2,'xty',100,90),(1714403907,13,'1111',100,90),(1714454652,18,'XiaoTongYu',60,50),(1714455006,18,'XiaoTongYu',100,90);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_time` mediumtext COMMENT '开始时间',
  `end_time` mediumtext COMMENT '结束时间',
  `coach_id` int DEFAULT NULL COMMENT '教练id',
  `coach_name` varchar(100) DEFAULT NULL COMMENT '教练名称',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `field` varchar(500) DEFAULT 'First training ground' COMMENT '场地',
  `state` int DEFAULT '0' COMMENT '状态,0未确认，1已确认，-1以取消',
  `num` int DEFAULT '0' COMMENT '修改次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'1620000000','1620100000',1,'教练1',1,'用户1','场地A',0,0),(2,'1620100000','1620200000',1,'教练2',1,'用户2','场地B',-1,0),(3,'1620200000','1620300000',1,'教练3',1,'用户3','场地C',-1,0),(6,'1620500000','1620600000',6,'教练6',1,'用户6','场地F',1,0),(7,'1620600000','1620700000',7,'教练7',1,'用户7','场地G',1,0),(8,'1620700000','1620800000',8,'教练8',1,'用户8','场地H',1,0),(9,'1620800000','1620900000',9,'教练9',1,'用户9','场地I',1,0),(10,'1620900000','1621000000',10,'教练10',1,'用户10','场地J',1,0),(11,'1621000000','1621100000',11,'教练11',1,'用户11','场地K',1,0),(12,'1621100000','1621200000',12,'教练12',1,'用户12','场地L',-1,0),(13,'1621200000','1621300000',13,'教练13',1,'用户13','场地M',1,0),(14,'1621300000','1621400000',14,'教练14',1,'用户14','场地N',1,0),(15,'1621400000','1621500000',15,'教练15',1,'用户15','场地O',1,0),(16,'1621500000','1621600000',16,'教练16',1,'用户16','场地P',1,0),(17,'1621600000','1621700000',17,'教练17',1,'用户17','场地Q',1,0),(18,'1621700000','1621800000',18,'教练18',1,'用户18','场地R',1,0),(35,'1714296720','1714311060',4,'Jane Smith',2,'xty','',2,0),(36,'1714414920','1714418280',1,'?',13,'1111','阿松大',2,0),(37,'1714544820','1714548480',6,'Emily Davis',18,'XiaoTongYu','仔细擦十大',0,0),(38,'1714642320','1714645920',1,'?',18,'XiaoTongYu','啊啊撒旦',2,0);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `no` varchar(100) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `sex` varchar(20) DEFAULT 'unknown' COMMENT 'male,emale',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `star` int DEFAULT '0' COMMENT '星级',
  `role_id` int DEFAULT '2' COMMENT '角色 0管理, 1教练, 2用户',
  `num` int DEFAULT '0' COMMENT '预约剩余次数',
  `time` float DEFAULT '0' COMMENT '执教时长',
  `score` float DEFAULT '0' COMMENT '评分',
  `awards` varchar(500) DEFAULT NULL COMMENT '所获奖项',
  `good` varchar(500) DEFAULT NULL COMMENT '擅长项目',
  `last_time` varchar(500) DEFAULT NULL COMMENT '最后登陆',
  `expiration` bigint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'?','asd','asd','未知ss','http://localhost:8090/static/img/1.png',1,1,1,4.73333,114,'阿松大42','啊实打实大苏打是打算','2024/04/30 13:37',NULL),(2,'xty','123123','123123','asdasd','http://localhost:8090/static/img/2.png',5,0,200,0,0,NULL,NULL,'2024/04/30 13:44',1730159424),(13,'1111','1111','1111','asd','http://localhost:8090/static/img/13.png',5,2,100,0,0,NULL,NULL,'2024/04/30 10:35',1730243907),(18,'XiaoTongYu','12345','12345','998','http://localhost:8090/static/img/18.png',5,2,158,0,0,NULL,NULL,'2024/04/30 13:28',1730295006),(19,'asd','555','555','男',NULL,0,1,0,0,0,NULL,NULL,'2024/04/30 13:32',0),(21,'John Smith','asd1','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(22,'Emily Johnson','asd2','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(23,'Michael Williams','asd3','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(24,'Emma Jones','asd4','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(25,'Christopher Brown','asd5','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(26,'Olivia Davis','asd6','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(27,'William Miller','asd7','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(28,'Sophia Wilson','asd8','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(29,'James Taylor','asd9','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL),(30,'Charlotte Brown','asd10','asd','未知',NULL,1,1,1,0,0,'阿松大42',' ','2024-04-30 13:37',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-30 13:53:50
