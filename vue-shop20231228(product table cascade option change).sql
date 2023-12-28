-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vue-shop
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `liked_product`
--

DROP TABLE IF EXISTS `liked_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liked_product` (
  `product_id` int NOT NULL,
  `member_id` varchar(45) NOT NULL,
  `liked_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `liked_member_id_to_member_member_id_idx` (`member_id`),
  KEY `liked_product_id_to_product_product_id_idx` (`product_id`),
  CONSTRAINT `liked_member_id_to_member_member_id` FOREIGN KEY (`member_id`) REFERENCES `mydb`.`member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `liked_product_id_to_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `mydb`.`product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked_product`
--

LOCK TABLES `liked_product` WRITE;
/*!40000 ALTER TABLE `liked_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `liked_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` varchar(45) NOT NULL,
  `member_pw` varchar(45) NOT NULL,
  `member_name` varchar(45) DEFAULT NULL,
  `member_addr` varchar(100) DEFAULT NULL,
  `member_age` int DEFAULT NULL,
  `member_email` varchar(45) DEFAULT NULL,
  `member_join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memberbasket`
--

DROP TABLE IF EXISTS `memberbasket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memberbasket` (
  `product_id` int NOT NULL,
  `member_id` varchar(45) NOT NULL,
  `mb_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `mb_member_id_to_member_member_id_idx` (`member_id`),
  KEY `mb_product_id_to_product_product_id_idx` (`product_id`),
  CONSTRAINT `mb_member_id_to_member_member_id` FOREIGN KEY (`member_id`) REFERENCES `mydb`.`member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mb_product_id_to_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `mydb`.`product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memberbasket`
--

LOCK TABLES `memberbasket` WRITE;
/*!40000 ALTER TABLE `memberbasket` DISABLE KEYS */;
/*!40000 ALTER TABLE `memberbasket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `product_img` varchar(45) DEFAULT NULL,
  `product_brand` int DEFAULT NULL,
  `product_description` varchar(200) DEFAULT NULL,
  `product_cost` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `product_product_brand_to_product_brand_product_brand_id_idx` (`product_brand`),
  CONSTRAINT `product_product_brand_to_product_brand_product_brand_id` FOREIGN KEY (`product_brand`) REFERENCES `product_brand` (`product_brand_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'나이키 덩크 로우 SP',NULL,1,NULL,10000),(2,'나이키 덩크 로우 프리미엄',NULL,1,NULL,20000),(3,'나이키 코르테즈',NULL,1,NULL,30000),(4,'나이키 덩크 로우 레트로',NULL,1,NULL,40000),(5,'나이키 에어 포스 1 \'07',NULL,1,NULL,50000),(6,'나이키 에어 조던 1 로우',NULL,1,NULL,60000),(7,'나이키 루나 포스 1',NULL,1,NULL,70000),(8,'나이키 보메로 5',NULL,1,NULL,80000),(9,'나이키 에어 베이퍼맥스 2023 플라이니트',NULL,1,NULL,90000),(10,'나이키 에어 베이퍼맥스 모크 롬',NULL,1,NULL,100000),(11,'나이키 에어맥스 2013',NULL,1,NULL,111111),(12,'나이키 에어 포스 1 \'07',NULL,1,NULL,122222),(13,'나이키 에어맥스 97',NULL,1,NULL,133333),(14,'나이키 에어맥스 펄스',NULL,1,NULL,144444),(15,'나이키 조던 1 미드',NULL,1,NULL,155555),(16,'나이키 에어맥스 97 SE',NULL,1,NULL,166666),(17,'나이키 다운시프터 12',NULL,1,NULL,177777),(18,'나이키 조던 1 하이 OG \'로얄 리이매진드\'',NULL,1,NULL,188888),(19,'나이키 터미네이터 로우',NULL,1,NULL,199999),(20,'나이키 페가수스 40',NULL,1,NULL,0),(21,'나이키 에어 조던 1 하이 G NRG',NULL,1,NULL,0),(22,'아디다스 버뮤다',NULL,2,NULL,0),(23,'아디다스 가젤',NULL,2,NULL,0),(24,'아디다스 가젤 볼드',NULL,2,NULL,0),(25,'아디다스 가젤 인도어',NULL,2,NULL,0),(26,'아디다스 삼바 OG',NULL,2,NULL,0),(27,'아디다스 슈퍼스타',NULL,2,NULL,0),(28,'아디다스 슈퍼스타 XLG',NULL,2,NULL,0),(29,'아디다스 슈퍼스타 82',NULL,2,NULL,0),(30,'아디다스 리스폰스 CL',NULL,2,NULL,0),(31,'아디다스 퓨어부스트 22',NULL,2,NULL,0),(32,'아디다스 울트라부스트 1.0',NULL,2,NULL,0),(33,'update test1','',1,'update test description1',0),(37,'NIKE_TEST_1',NULL,1,'description',10000),(38,'NIKE_TEST_2',NULL,1,'description',20000),(39,'NIKE_TEST_3',NULL,1,'description',30000),(40,'NIKE_TEST_4',NULL,1,'description',40000),(41,'NIKE_TEST_5',NULL,1,'description',50000),(42,'NIKE_TEST_6',NULL,1,'description',60000),(43,'NIKE_TEST_7',NULL,1,'description',70000),(44,'NIKE_TEST_8',NULL,1,'description',80000),(45,'NIKE_TEST_9',NULL,1,'description',90000),(46,'NIKE_TEST_10',NULL,1,'description',100000),(47,'NIKE_TEST_11',NULL,1,'description',110000),(48,'NIKE_TEST_12',NULL,1,'description',120000),(49,'NIKE_TEST_13',NULL,1,'description',130000),(50,'NIKE_TEST_14',NULL,1,'description',140000),(51,'NIKE_TEST_15',NULL,1,'description',150000),(52,'NIKE_TEST_16',NULL,1,'description',160000),(53,'NIKE_TEST_17',NULL,1,'description',170000),(54,'NIKE_TEST_18',NULL,1,'description',180000),(55,'NIKE_TEST_19',NULL,1,'description',190000),(56,'NIKE_TEST_20',NULL,1,'description',200000),(57,'ADIDAS_TEST_1',NULL,2,'description',10000),(58,'ADIDAS_TEST_2',NULL,2,'description',20000),(59,'ADIDAS_TEST_3',NULL,2,'description',30000),(60,'ADIDAS_TEST_4',NULL,2,'description',40000),(61,'ADIDAS_TEST_5',NULL,2,'description',50000),(62,'ADIDAS_TEST_6',NULL,2,'description',60000),(63,'ADIDAS_TEST_7',NULL,2,'description',70000),(64,'ADIDAS_TEST_8',NULL,2,'description',80000),(65,'ADIDAS_TEST_9',NULL,2,'description',90000),(66,'ADIDAS_TEST_10',NULL,2,'description',100000),(67,'ADIDAS_TEST_11',NULL,2,'description',110000),(68,'ADIDAS_TEST_12',NULL,2,'description',120000),(69,'ADIDAS_TEST_13',NULL,2,'description',130000),(70,'ADIDAS_TEST_14',NULL,2,'description',140000),(71,'ADIDAS_TEST_15',NULL,2,'description',150000),(72,'ADIDAS_TEST_16',NULL,2,'description',160000),(73,'ADIDAS_TEST_17',NULL,2,'description',170000),(74,'ADIDAS_TEST_18',NULL,2,'description',180000),(75,'ADIDAS_TEST_19',NULL,2,'description',190000),(76,'ADIDAS_TEST_20',NULL,2,'description',200000),(77,'test shoes','',1,'test description',0),(84,'TEST PROD WITH TEST BRAND','',NULL,'',0),(85,'TEST PROD WITH TEST BRAND','',NULL,'',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_brand`
--

DROP TABLE IF EXISTS `product_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_brand` (
  `product_brand_id` int NOT NULL AUTO_INCREMENT,
  `product_brand_name` varchar(45) NOT NULL,
  PRIMARY KEY (`product_brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_brand`
--

LOCK TABLES `product_brand` WRITE;
/*!40000 ALTER TABLE `product_brand` DISABLE KEYS */;
INSERT INTO `product_brand` VALUES (1,'NIKE'),(2,'ADIDAS'),(3,'ASICS'),(5,'UPDATE TEST');
/*!40000 ALTER TABLE `product_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_warehouse`
--

DROP TABLE IF EXISTS `product_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_warehouse` (
  `product_code` varchar(100) NOT NULL,
  `product_id` int NOT NULL,
  `product_size` int NOT NULL,
  `product_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_code`),
  KEY `product_detail_product_id_to_product_product_id_idx` (`product_id`),
  CONSTRAINT `product_detail_product_id_to_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_warehouse`
--

LOCK TABLES `product_warehouse` WRITE;
/*!40000 ALTER TABLE `product_warehouse` DISABLE KEYS */;
INSERT INTO `product_warehouse` VALUES ('DEFEXDF12',1,270,'2023-12-06 11:46:12'),('DEFEXDF13',1,270,'2023-12-06 11:48:54'),('DEFEXDF14',1,270,'2023-12-06 11:51:53'),('DEFEXDF15',1,270,'2023-12-06 11:54:31'),('DEFEXDF16',1,270,'2023-12-06 11:58:50'),('DEFEXDF17',1,270,'2023-12-06 12:00:00'),('DEFEXDF18',1,270,'2023-12-06 12:00:25'),('DEFEXDF19',1,270,'2023-12-06 12:06:59'),('DF212412F',1,260,'2023-12-06 10:57:50'),('TEST01',33,270,'2023-12-09 13:57:51'),('WILLDELETECODE',33,260,'2023-12-10 09:49:03');
/*!40000 ALTER TABLE `product_warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-28 18:10:28
