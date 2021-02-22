-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: a401_test
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `article_ingredient`
--

DROP TABLE IF EXISTS `article_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_ingredient` (
  `article_no` int unsigned NOT NULL,
  `ingredient_no` int unsigned NOT NULL,
  `main_ingredient` tinyint DEFAULT NULL,
  `ingredient_quantity` varchar(10) NOT NULL,
  `ingredient_unit` varchar(10) NOT NULL,
  `pk_article_ingredient` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pk_article_ingredient`),
  UNIQUE KEY `article_no_UNIQUE` (`article_no`),
  UNIQUE KEY `article_ingredientcol_UNIQUE` (`ingredient_no`),
  UNIQUE KEY `pk_article_ingredient_UNIQUE` (`pk_article_ingredient`),
  CONSTRAINT `fk_article_article_ingredient` FOREIGN KEY (`article_no`) REFERENCES `article` (`pk_article`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ingredient_article_ingredient` FOREIGN KEY (`ingredient_no`) REFERENCES `ingredient` (`pk_ingredient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_ingredient`
--

LOCK TABLES `article_ingredient` WRITE;
/*!40000 ALTER TABLE `article_ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_ingredient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-18 18:34:54
