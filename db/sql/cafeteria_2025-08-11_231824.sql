-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: cafeteria
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Sopas','Alimentos caldoso, puede incluir una gran variedad de ingredientes, desde verduras y legumbres hasta carnes, aves, pescado, mariscos, y pastas. ','2025-08-10 21:12:59'),(2,'Comida rapida','Alimentos alientes que se preparan rapido','2025-08-10 21:15:04'),(3,'Platillos','Alimentos que son mas cargados o con mayor contenido de comida','2025-08-10 21:15:54'),(4,'Mariscos','Alimentos que contienen comida marina como pescado','2025-08-10 21:16:45'),(5,'Carne','Alimentos que contienen carne como puerco, pollo, muu, dinosauro grrr','2025-08-10 21:17:36'),(6,'Pastas','Alimentos que contienen pasta','2025-08-10 21:18:22');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallepedido` (
  `id_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` decimal(5,2) NOT NULL,
  `subtotal` decimal(8,2) NOT NULL,
  `observaciones` text DEFAULT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `id_pedido` (`id_pedido`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detallepedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detallepedido_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido`
--

/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
INSERT INTO `detallepedido` VALUES (1,1,3,2,30.00,60.00,''),(2,1,4,1,20.00,20.00,''),(3,2,4,2,20.00,40.00,''),(4,2,5,1,25.00,25.00,''),(5,3,4,2,20.00,40.00,''),(6,4,3,2,30.00,60.00,'Sin cebolla'),(7,4,4,1,20.00,20.00,'Con cebolla'),(8,5,4,2,20.00,40.00,''),(9,5,5,1,25.00,25.00,''),(10,5,1,2,30.00,60.00,''),(11,6,4,2,20.00,40.00,'Sin cebolla'),(12,6,5,2,25.00,50.00,''),(13,6,6,1,100.00,100.00,'Muy buenos');
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;

--
-- Table structure for table `notificaciones`
--

DROP TABLE IF EXISTS `notificaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificaciones` (
  `id_notificacion` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `mensaje` text NOT NULL,
  `leido` tinyint(1) NOT NULL DEFAULT 0,
  `fecha_creacion` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_notificacion`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_pedido` (`id_pedido`),
  CONSTRAINT `notificaciones_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notificaciones_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificaciones`
--

/*!40000 ALTER TABLE `notificaciones` DISABLE KEYS */;
INSERT INTO `notificaciones` VALUES (1,3,1,'El pedido PED-715329F5E977 esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:39:10'),(2,3,2,'El pedido PED-BE58563E796D esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:40:34'),(3,3,3,'El pedido PED-DEA4E0CFB58D esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:42:20'),(4,4,4,'El pedido PED-6E37766DD557 esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:46:33'),(5,4,5,'El pedido PED-E9949BCE9FE3 esta pendiente. Tiempo estimado: 00:02:00',0,'2025-08-11 22:40:18'),(6,4,6,'El pedido PED-31B6CF11E9AE esta pendiente. Tiempo estimado: 20:02:20',0,'2025-08-11 22:43:36'),(7,4,6,'El pedido PED-31B6CF11E9AE esta preparando. Tiempo estimado: 20:02:20',0,'2025-08-11 23:05:12'),(8,4,5,'El pedido PED-E9949BCE9FE3 esta preparando. Tiempo estimado: 00:02:00',0,'2025-08-11 23:05:19'),(9,4,5,'El pedido PED-E9949BCE9FE3 esta listo. Tiempo estimado: 00:02:00',0,'2025-08-11 23:05:21');
/*!40000 ALTER TABLE `notificaciones` ENABLE KEYS */;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `folio` varchar(20) NOT NULL,
  `tiempo_estimado` varchar(8) NOT NULL,
  `estado` enum('pendiente','preparando','listo','entregado','cancelado') NOT NULL DEFAULT 'pendiente',
  `tiempo_entrega` varchar(8) NOT NULL,
  `comentario` text DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha_pedido` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_pedido`),
  UNIQUE KEY `folio` (`folio`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,3,'PED-715329F5E977','18:00:00','pendiente','18:00:00','Sin chile y sin cebolla porfavor',80.00,'2025-08-10 23:39:10'),(2,3,'PED-BE58563E796D','18:00:00','pendiente','18:00:00','Sin comentarios',65.00,'2025-08-10 23:40:34'),(3,3,'PED-DEA4E0CFB58D','18:00:00','pendiente','18:00:00','Sin comentarios',40.00,'2025-08-10 23:42:20'),(4,4,'PED-6E37766DD557','18:00:00','pendiente','18:00:00','Con chile abanero',80.00,'2025-08-10 23:46:33'),(5,4,'PED-E9949BCE9FE3','00:02:00','listo','00:02:00','Sin comentarios',125.00,'2025-08-11 22:40:18'),(6,4,'PED-31B6CF11E9AE','20:02:20','preparando','20:02:20','Sin comentarios',190.00,'2025-08-11 22:43:35');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `tiempo_preparacion` varchar(8) NOT NULL,
  `disponible` tinyint(1) NOT NULL DEFAULT 1,
  `fecha_registro` datetime NOT NULL DEFAULT current_timestamp(),
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `nombre_producto` (`nombre_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Albondigas','Platillo de albondigas con pasta (tenedor no incluido)',30.00,'00:00:20',1,'2025-08-10 22:01:11','1754889901153_albondigas.jpg'),(2,'Camarones','Son mariscos, muchos maricos',40.00,'00:00:45',1,'2025-08-10 22:13:31','1754889992220_camarones-macuil.jpg'),(3,'Hamburgesa','Hamburgesa con pan integral, carne de muu, queso amarillo, lechuga, jitomate',30.00,'00:00:28',1,'2025-08-10 22:15:08','1754890053644_hamburgesa.png'),(4,'Tacos de carbon','Son tacos con carne, no contiene carbon',20.00,'00:00:10',1,'2025-08-10 22:18:07','1754890157622_tacos.jpg'),(5,'Morisqueta','Arroz, frijoles, queso y salsa',25.00,'00:01:00',1,'2025-08-10 23:31:17','1754890239372_Morisqueta_michoacana.jpg'),(6,'Platillos','Son dos platillos',100.00,'20:00:00',1,'2025-08-10 23:32:36','1754890336606_platillos.jpg'),(7,'Sopa de pollo','Es una sopa caldosa con pollo verduras y condimento',30.00,'00:30:00',1,'2025-08-11 23:04:56','1754975017801_Sopa-de-pollo.jpg');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

--
-- Table structure for table `productos_categorias`
--

DROP TABLE IF EXISTS `productos_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos_categorias` (
  `id_producto` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  PRIMARY KEY (`id_producto`,`id_categoria`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `productos_categorias_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productos_categorias_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_categorias`
--

/*!40000 ALTER TABLE `productos_categorias` DISABLE KEYS */;
INSERT INTO `productos_categorias` VALUES (1,1),(1,3),(1,5),(1,6),(2,3),(2,4),(3,2),(3,5),(4,5),(5,3),(6,3),(7,1),(7,3),(7,5);
/*!40000 ALTER TABLE `productos_categorias` ENABLE KEYS */;

--
-- Table structure for table `reseniapedido`
--

DROP TABLE IF EXISTS `reseniapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reseniapedido` (
  `id_resenia` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `calificacion` tinyint(4) NOT NULL,
  `comentario` text DEFAULT NULL,
  `fecha_resenia` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_resenia`),
  UNIQUE KEY `id_usuario` (`id_usuario`,`id_pedido`),
  KEY `id_pedido` (`id_pedido`),
  CONSTRAINT `reseniapedido_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reseniapedido_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reseniapedido`
--

/*!40000 ALTER TABLE `reseniapedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `reseniapedido` ENABLE KEYS */;

--
-- Table structure for table `reseniaproductos`
--

DROP TABLE IF EXISTS `reseniaproductos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reseniaproductos` (
  `id_resenia` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `calificacion` tinyint(4) NOT NULL,
  `comentario` text DEFAULT NULL,
  `fecha_resenia` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_resenia`),
  UNIQUE KEY `id_usuario` (`id_usuario`,`id_producto`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `reseniaproductos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reseniaproductos_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reseniaproductos`
--

/*!40000 ALTER TABLE `reseniaproductos` DISABLE KEYS */;
/*!40000 ALTER TABLE `reseniaproductos` ENABLE KEYS */;

--
-- Table structure for table `sugerencias`
--

DROP TABLE IF EXISTS `sugerencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sugerencias` (
  `id_sugerencia` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `asunto` varchar(100) DEFAULT NULL,
  `comentario` text NOT NULL,
  `estado` enum('pendiente','revisada') NOT NULL DEFAULT 'pendiente',
  `fecha_registro` datetime NOT NULL DEFAULT current_timestamp(),
  `fecha_revision` datetime DEFAULT NULL,
  PRIMARY KEY (`id_sugerencia`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `sugerencias_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerencias`
--

/*!40000 ALTER TABLE `sugerencias` DISABLE KEYS */;
INSERT INTO `sugerencias` VALUES (1,3,'No hay comida','No hay comida No hay comida No hay comida No hay comida No hay comida No hay comida No hay comida No hay comida ','pendiente','2025-08-10 23:43:15',NULL),(2,3,'Agregen las mojarras','Porfavor y muchas gracias','pendiente','2025-08-10 23:43:51',NULL),(3,4,'Agregen los tacos dorados','Por favor y muchas gracias','pendiente','2025-08-10 23:47:10',NULL);
/*!40000 ALTER TABLE `sugerencias` ENABLE KEYS */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `contrasenia` varchar(100) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1,
  `rol` enum('estudiante','empleado','administrador') NOT NULL,
  `matricula` varchar(10) DEFAULT NULL,
  `fecha_registro` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `matricula` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Angel','Alonso','Gomez','angel.alonso@gmail.com','qweasdfgh*','7331533600',1,'administrador',NULL,'2025-08-10 21:03:40'),(2,'Ariana','Orive','Cardiel','ariana.orive@gmail.com','qweasdfgh*','7771236544',1,'administrador',NULL,'2025-08-10 21:06:04'),(3,'Angel Manuel','Alonso','Gomez','agao241496@gmail.com','qweasdfgh*','7331533600',1,'estudiante','AGAO241496','2025-08-10 23:37:45'),(4,'Ariana Paola','Orive','Cardiel','ocao241496@gmail.com','qweasdfgh*','7778956322',1,'estudiante','OCAO241944','2025-08-10 23:45:52');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

--
-- Dumping routines for database 'cafeteria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-11 23:18:28
