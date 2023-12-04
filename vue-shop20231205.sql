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
  `product_brand` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'나이키 덩크 로우 SP',NULL,'NIKE'),(2,'나이키 덩크 로우 프리미엄',NULL,'NIKE'),(3,'나이키 코르테즈',NULL,'NIKE'),(4,'나이키 덩크 로우 레트로',NULL,'NIKE'),(5,'나이키 에어 포스 1 \'07',NULL,'NIKE'),(6,'나이키 에어 조던 1 로우',NULL,'NIKE'),(7,'나이키 루나 포스 1',NULL,'NIKE'),(8,'나이키 보메로 5',NULL,'NIKE'),(9,'나이키 에어 베이퍼맥스 2023 플라이니트',NULL,'NIKE'),(10,'나이키 에어 베이퍼맥스 모크 롬',NULL,'NIKE'),(11,'나이키 에어맥스 2013',NULL,'NIKE'),(12,'나이키 에어 포스 1 \'07',NULL,'NIKE'),(13,'나이키 에어맥스 97',NULL,'NIKE'),(14,'나이키 에어맥스 펄스',NULL,'NIKE'),(15,'나이키 조던 1 미드',NULL,'NIKE'),(16,'나이키 에어맥스 97 SE',NULL,'NIKE'),(17,'나이키 다운시프터 12',NULL,'NIKE'),(18,'나이키 조던 1 하이 OG \'로얄 리이매진드\'',NULL,'NIKE'),(19,'나이키 터미네이터 로우',NULL,'NIKE'),(20,'나이키 페가수스 40',NULL,'NIKE'),(21,'나이키 에어 조던 1 하이 G NRG',NULL,'NIKE'),(22,'아디다스 버뮤다',NULL,'ADIDAS'),(23,'아디다스 가젤',NULL,'ADIDAS'),(24,'아디다스 가젤 볼드',NULL,'ADIDAS'),(25,'아디다스 가젤 인도어',NULL,'ADIDAS'),(26,'아디다스 삼바 OG',NULL,'ADIDAS'),(27,'아디다스 슈퍼스타',NULL,'ADIDAS'),(28,'아디다스 슈퍼스타 XLG',NULL,'ADIDAS'),(29,'아디다스 슈퍼스타 82',NULL,'ADIDAS'),(30,'아디다스 리스폰스 CL',NULL,'ADIDAS'),(31,'아디다스 퓨어부스트 22',NULL,'ADIDAS'),(32,'아디다스 울트라부스트 1.0',NULL,'ADIDAS');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `product_code` varchar(100) NOT NULL,
  `product_id` int NOT NULL,
  `product_description` varchar(200) DEFAULT NULL,
  `product_size` int NOT NULL,
  `product_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_code`),
  KEY `product_detail_product_id_to_product_product_id_idx` (`product_id`),
  CONSTRAINT `product_detail_product_id_to_product_product_id` FOREIGN KEY (`product_id`) REFERENCES `mydb`.`product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-05  3:17:17
