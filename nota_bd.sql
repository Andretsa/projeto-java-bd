-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (x86_64)
--
-- Host: localhost    Database: nota_bd
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pessoa` (
  `idPessoa` int NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `nomeCompleto` varchar(50) NOT NULL,
  `whatsapp` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPessoa`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES (2,'7111111110','manel','8399966696','eu@gmail.com');
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Segurado`
--

DROP TABLE IF EXISTS `Segurado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Segurado` (
  `idSegurado` int NOT NULL,
  `numeroApolice` int DEFAULT NULL,
  `idPessoa` int DEFAULT NULL,
  PRIMARY KEY (`idSegurado`),
  KEY `idPessoa` (`idPessoa`),
  CONSTRAINT `segurado_ibfk_1` FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Segurado`
--

LOCK TABLES `Segurado` WRITE;
/*!40000 ALTER TABLE `Segurado` DISABLE KEYS */;
/*!40000 ALTER TABLE `Segurado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seguro`
--

DROP TABLE IF EXISTS `Seguro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Seguro` (
  `idSeguro` int NOT NULL,
  `vigencia` date NOT NULL,
  `situacao` varchar(20) DEFAULT NULL,
  `apolice` varchar(20) DEFAULT NULL,
  `idSegurado` int DEFAULT NULL,
  `idVeiculo` int DEFAULT NULL,
  PRIMARY KEY (`idSeguro`),
  KEY `idSegurado` (`idSegurado`),
  KEY `idVeiculo` (`idVeiculo`),
  CONSTRAINT `seguro_ibfk_1` FOREIGN KEY (`idSegurado`) REFERENCES `Segurado` (`idSegurado`),
  CONSTRAINT `seguro_ibfk_2` FOREIGN KEY (`idVeiculo`) REFERENCES `Veiculo` (`idVeiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seguro`
--

LOCK TABLES `Seguro` WRITE;
/*!40000 ALTER TABLE `Seguro` DISABLE KEYS */;
/*!40000 ALTER TABLE `Seguro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Veiculo`
--

DROP TABLE IF EXISTS `Veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Veiculo` (
  `idVeiculo` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(20) NOT NULL,
  `ano` year DEFAULT NULL,
  `modelo` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `seguradora` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idVeiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Veiculo`
--

LOCK TABLES `Veiculo` WRITE;
/*!40000 ALTER TABLE `Veiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'nota_bd'
--

--
-- Dumping routines for database 'nota_bd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24 13:37:45
