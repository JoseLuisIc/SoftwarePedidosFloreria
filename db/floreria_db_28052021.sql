-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: floreriadb
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `tabla_arreglos`
--

DROP TABLE IF EXISTS `tabla_arreglos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_arreglos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `costo` double NOT NULL,
  `tiempo_elaboracion` int NOT NULL,
  `comision` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_arreglos`
--

LOCK TABLES `tabla_arreglos` WRITE;
/*!40000 ALTER TABLE `tabla_arreglos` DISABLE KEYS */;
INSERT INTO `tabla_arreglos` VALUES (1,'Arreglo Flor Muertos',200,1,5),(2,'Arreglo Flor Primavera',150,10,5),(3,'Arreglo Flor Rosas',200,15,5),(4,'Arreglo Flor Clavel',150,15,5),(5,'Arreglo Flor Domingo',300,15,5),(6,'Arreglo Flor Margaritas',500,15,5),(7,'Arreglo Flor Azucenas',200,20,5),(8,'Arreglo Flor Tulipanes',300,5,5),(9,'Arreglo Flor Diciembre',1000,50,5),(10,'Arreglo Flores de Yucatán',5000,50,5),(11,'Arreglo Paquete Misa',3000,50,5),(12,'Arreglo Paquete Boda basico',1000,50,5),(13,'Arreglo Paquete Baby Shower',1500,50,5),(14,'Arreglo Paquete Bautizo',1500,60,5),(15,'Arreglo Paquete Comunión',2000,60,5),(16,'Arreglo Paquete Boda',5000,60,5),(17,'Arreglo Paquete XV años',3000,60,5),(18,'Arreglo Margarita 1',10,10,5),(19,'Arreglo Margarita 2',20,10,5),(20,'Arreglo Margarita 6',50,10,5),(21,'Arreglo Margarita 12',100,50,5),(22,'Arreglo Tulipan 1',30,5,5),(23,'Arreglo Tulipan 6',90,10,5),(24,'Arreglo Tulipan 12',300,20,5),(25,'Arreglo Tulipan 24',1000,30,5),(26,'Arreglo Tulipan 36',1500,50,5),(27,'Arreglo Clavel 1',5,5,5),(28,'Arreglo Clavel 6',50,5,5),(29,'Arreglo Rosas 1',10,5,5),(30,'Arreglo Rosas 6',50,10,5),(31,'Arreglo Rosas 12',100,15,5),(32,'Arreglo Flor Guayaba',60,10,5),(33,'Arreglo Flores Café',70,5,5),(34,'Arreglo Flor Basico',80,5,5),(35,'Arreglo Florar Nuevo Amanecer',100,5,5),(36,'Arreglo Flores de Abril',500,50,5),(37,'Arreglo Florar Premium',1000,50,5),(38,'Arreglo Flor Pera',10,5,5),(39,'Arreglo Flor de Mar',20,100,5),(40,'Arreglo Flores Chocolate',50,5,5),(41,'Arreglo Flor Pasadia',100,5,5),(42,'Arreglo Flores Nuevas',200,18,5),(43,'Arreglo Flor Amarillos',140,10,5),(44,'Arreglo Flor Verdes',150,15,5),(45,'Arreglo Flor Mascotas',100,20,5);
/*!40000 ALTER TABLE `tabla_arreglos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla_bono`
--

DROP TABLE IF EXISTS `tabla_bono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_bono` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero_empleado` int DEFAULT NULL,
  `comision_eficiencia` double DEFAULT NULL,
  `diferencia_min` int DEFAULT NULL,
  `fecha` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `id_pedido` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `autorizado` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_bono`
--

LOCK TABLES `tabla_bono` WRITE;
/*!40000 ALTER TABLE `tabla_bono` DISABLE KEYS */;
INSERT INTO `tabla_bono` VALUES (4,300,750,-20,'2021/05/28','12',1),(5,300,1000,-8,'2021/05/28','6',1),(6,600,75,-1,'2021/05/28','14',1),(7,600,225,5,'2021/05/28','13',1),(8,700,100,-15,'2021/05/28','15',1),(9,700,200,-6,'2021/05/28','16',1),(10,700,950,3,'2021/05/28','17',1),(11,300,2517.5,-50,'2021/05/28','18',1);
/*!40000 ALTER TABLE `tabla_bono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla_empleados`
--

DROP TABLE IF EXISTS `tabla_empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_empleados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `num_empleado` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usuario` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `sucursal` int NOT NULL,
  `puesto` int NOT NULL,
  `comision` double NOT NULL,
  `tiempo_trabajo` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `num_empleado_UNIQUE` (`num_empleado`),
  KEY `plantadata_ibfk_1_idx` (`sucursal`),
  KEY `plantadata_ibfk_2_idx` (`puesto`),
  CONSTRAINT `tabla_empleados_ibfk_1` FOREIGN KEY (`sucursal`) REFERENCES `tabla_sucursal` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tabla_empleados_ibfk_2` FOREIGN KEY (`puesto`) REFERENCES `table_puesto` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_empleados`
--

LOCK TABLES `tabla_empleados` WRITE;
/*!40000 ALTER TABLE `tabla_empleados` DISABLE KEYS */;
INSERT INTO `tabla_empleados` VALUES (5,100,'Usuario Supervisor','supervisor','12345',2,2,0,0),(6,200,'Usuario Administrador','admin','12345',2,3,0,0),(8,300,'Usuario Diseñador','diseno','12345',2,1,2577.67,47),(10,400,'Manuel Vargas','manu','123',2,2,0,0),(11,500,'pedro','pedro','12345',1,2,0,0),(12,600,'luis','luis','12345',2,1,225,34),(13,700,'jose','jose','12345',2,1,950,70);
/*!40000 ALTER TABLE `tabla_empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla_estatus`
--

DROP TABLE IF EXISTS `tabla_estatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_estatus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_estatus`
--

LOCK TABLES `tabla_estatus` WRITE;
/*!40000 ALTER TABLE `tabla_estatus` DISABLE KEYS */;
INSERT INTO `tabla_estatus` VALUES (1,'asignado'),(2,'en elaboración'),(3,'terminado'),(4,'autorizado');
/*!40000 ALTER TABLE `tabla_estatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla_pedidos`
--

DROP TABLE IF EXISTS `tabla_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_pedidos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `num_empleado` int NOT NULL,
  `id_arreglo` int NOT NULL,
  `estatus` int NOT NULL,
  `tiempo_estimado` int NOT NULL,
  `hora_inicio` datetime NOT NULL,
  `hora_final` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `plantadata_ibfk_1_idx` (`num_empleado`),
  KEY `tabla_pedidos_ibfk_2_idx` (`id_arreglo`),
  KEY `tabla_pedidos_idx` (`estatus`),
  CONSTRAINT `tabla_pedidos_ibfk_1` FOREIGN KEY (`num_empleado`) REFERENCES `tabla_empleados` (`num_empleado`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tabla_pedidos_ibfk_2` FOREIGN KEY (`id_arreglo`) REFERENCES `tabla_arreglos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tabla_pedidos_ibfk_3` FOREIGN KEY (`estatus`) REFERENCES `tabla_estatus` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_pedidos`
--

LOCK TABLES `tabla_pedidos` WRITE;
/*!40000 ALTER TABLE `tabla_pedidos` DISABLE KEYS */;
INSERT INTO `tabla_pedidos` VALUES (6,300,6,4,15,'2021-05-28 03:10:18','2021-05-28 03:10:29'),(12,300,26,4,50,'2021-05-28 03:08:40','2021-05-28 03:09:19'),(13,600,5,4,15,'2021-05-28 03:11:29','2021-05-28 03:12:08'),(14,600,4,4,15,'2021-05-28 03:10:58','2021-05-28 03:11:19'),(15,700,7,4,20,'2021-05-28 04:06:22','2021-05-28 04:06:30'),(16,700,42,4,18,'2021-05-28 04:06:35','2021-05-28 04:06:51'),(17,700,26,4,50,'2021-05-28 05:00:02','2021-05-28 05:01:10'),(18,300,17,4,60,'2021-05-28 05:02:06','2021-05-28 05:02:20');
/*!40000 ALTER TABLE `tabla_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabla_sucursal`
--

DROP TABLE IF EXISTS `tabla_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabla_sucursal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabla_sucursal`
--

LOCK TABLES `tabla_sucursal` WRITE;
/*!40000 ALTER TABLE `tabla_sucursal` DISABLE KEYS */;
INSERT INTO `tabla_sucursal` VALUES (1,'Mérida'),(2,'Monterrey'),(3,'Guadalajara');
/*!40000 ALTER TABLE `tabla_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_puesto`
--

DROP TABLE IF EXISTS `table_puesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_puesto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_puesto`
--

LOCK TABLES `table_puesto` WRITE;
/*!40000 ALTER TABLE `table_puesto` DISABLE KEYS */;
INSERT INTO `table_puesto` VALUES (1,'diseñador'),(2,'supervisor'),(3,'administrador');
/*!40000 ALTER TABLE `table_puesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'floreriadb'
--

--
-- Dumping routines for database 'floreriadb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-28  5:12:38
