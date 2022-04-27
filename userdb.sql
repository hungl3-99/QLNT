-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: localhost    Database: doan
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_day` datetime DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `is_request` bit(1) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('0e4163b6-3505-408a-b148-1a7915c65c94','2022-04-16 11:57:14','kien2005','2022-04-16 11:57:14',NULL,'Thanh Hoa','123456','2022-04-11 22:22:10','Trung Kien','123342',_binary '',_binary '\0','KBrCxYTOiPwZo2QKybHxXw==','ROLE_USER','0961330950','kien2005'),('10b9b87e-49c7-4b32-9936-b87bd460bc97','2022-04-16 11:55:38','vinhle12','2022-04-16 11:55:38',NULL,'Thanh Hoa','123456','2022-04-11 22:22:10','Vinh Thanh Le','123342',_binary '',_binary '\0','KBrCxYTOiPwZo2QKybHxXw==','ROLE_STORE','0961330950','vinhle12'),('197d9e14-d278-44c6-82f3-cff1322b6913','2022-04-16 11:56:04','hungle99','2022-04-16 11:56:04',NULL,'Thanh Hoa','123456','2022-04-11 22:22:10','Le Tuan Hung','123342',_binary '',_binary '\0','KBrCxYTOiPwZo2QKybHxXw==','ROLE_ADMIN','0961330950','hungle99'),('743925e6-2c35-4e6e-808a-e70d2c7cf2b1','2022-04-22 22:05:38','hungle99123','2022-04-22 22:05:38',NULL,'Thanh Hoa','123456','2022-04-11 22:22:10','Le Tuan Hung','123342',_binary '',_binary '\0','KBrCxYTOiPwZo2QKybHxXw==','ROLE_USER','0961330950','hungle99123'),('83063265-14c4-4ed1-a764-6a7b9c5062d4','2022-04-22 21:33:53','hungle9912','2022-04-22 21:33:53',NULL,'Thanh Hoa','123456','2022-04-11 22:22:10','Le Tuan Hung','123342',_binary '',_binary '\0','KBrCxYTOiPwZo2QKybHxXw==','ROLE_USER','0961330950','hungle9912');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `chat_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recipient_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recipient_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sender_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sender_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `chat_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `recipient_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sender_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `electric_price` bigint NOT NULL,
  `is_booking` bit(1) NOT NULL,
  `is_valid` bit(1) NOT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `max_number_price` bigint NOT NULL,
  `network_price` bigint NOT NULL,
  `number_of_room` int NOT NULL,
  `price` bigint NOT NULL,
  `room_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `water_price` bigint NOT NULL,
  `store_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9fatorh0gkwowj66ml12omb8t` (`store_id`),
  CONSTRAINT `FK9fatorh0gkwowj66ml12omb8t` FOREIGN KEY (`store_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('dbcba0fa-258c-44ff-83fc-00ac544effc7','2022-04-16 12:18:27','vinhle12','2022-04-16 14:07:55',NULL,'123',10000,_binary '',_binary '\0','Hanoi',5,10000,0,190000,'thien thai','Hotel',10000,'10b9b87e-49c7-4b32-9936-b87bd460bc97');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_order`
--

DROP TABLE IF EXISTS `system_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_order` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `room_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKljjhdrl391666ej384yxapd5q` (`account_id`),
  KEY `FKbe5btsdptbdas4a0n802jaokf` (`room_id`),
  CONSTRAINT `FKbe5btsdptbdas4a0n802jaokf` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FKljjhdrl391666ej384yxapd5q` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_order`
--

LOCK TABLES `system_order` WRITE;
/*!40000 ALTER TABLE `system_order` DISABLE KEYS */;
INSERT INTO `system_order` VALUES ('058e1948-08a6-4ba5-8da1-3d8e4cd81f58',NULL,NULL,'2022-04-11 00:00:00','REJECT','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,'2022-04-16 14:05:04','vinhle12'),('19bc092e-0256-440d-a92e-df9a054456b8',NULL,NULL,'2022-04-11 00:00:00','REJECT','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,NULL,NULL),('6f10d9b1-5fcc-4f38-9200-933f468921f9',NULL,NULL,'2022-04-11 00:00:00','REJECT','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,NULL,NULL),('753de05a-a5a3-40fe-a406-ff76e74918d2',NULL,NULL,'2022-04-11 00:00:00','REJECT','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,'2022-04-16 14:05:04','vinhle12'),('7ee66371-33b5-413a-8414-0ea0361cf3d3',NULL,NULL,'2022-04-11 00:00:00','REJECT','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,'2022-04-16 14:05:04','vinhle12'),('969b2254-c771-45f7-ab70-1d14f054afeb',NULL,NULL,'2022-04-11 00:00:00','REJECT','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,'2022-04-16 14:05:04','vinhle12'),('cab5a294-ae2c-480b-a080-575c00c95768',NULL,NULL,'2022-04-11 00:00:00','APPROVED','0e4163b6-3505-408a-b148-1a7915c65c94','dbcba0fa-258c-44ff-83fc-00ac544effc7',NULL,NULL,'2022-04-16 14:07:55','vinhle12');
/*!40000 ALTER TABLE `system_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-25 21:02:04
