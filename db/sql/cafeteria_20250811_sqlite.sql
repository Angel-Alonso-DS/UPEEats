-- SQLite conversion of cafeteria database
-- Converted from MariaDB dump

PRAGMA foreign_keys = ON;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `categorias` (
  `id_categoria` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nombre` VARCHAR(50) NOT NULL UNIQUE,
  `descripcion` TEXT NOT NULL,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` VALUES 
(1,'Sopas','Alimentos caldoso, puede incluir una gran variedad de ingredientes, desde verduras y legumbres hasta carnes, aves, pescado, mariscos, y pastas. ','2025-08-10 21:12:59'),
(2,'Comida rapida','Alimentos alientes que se preparan rapido','2025-08-10 21:15:04'),
(3,'Platillos','Alimentos que son mas cargados o con mayor contenido de comida','2025-08-10 21:15:54'),
(4,'Mariscos','Alimentos que contienen comida marina como pescado','2025-08-10 21:16:45'),
(5,'Carne','Alimentos que contienen carne como puerco, pollo, muu, dinosauro grrr','2025-08-10 21:17:36'),
(6,'Pastas','Alimentos que contienen pasta','2025-08-10 21:18:22');

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido_paterno` VARCHAR(50) NOT NULL,
  `apellido_materno` VARCHAR(50) NOT NULL,
  `correo` VARCHAR(100) NOT NULL UNIQUE,
  `contrasenia` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(10) NOT NULL,
  `activo` BOOLEAN NOT NULL DEFAULT 1,
  `rol` TEXT CHECK( rol IN ('estudiante','empleado','administrador') ) NOT NULL,
  `matricula` VARCHAR(10) UNIQUE,
  `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` VALUES 
(1,'Angel','Alonso','Gomez','angel.alonso@gmail.com','qweasdfgh*','7331533600',1,'administrador',NULL,'2025-08-10 21:03:40'),
(2,'Ariana','Orive','Cardiel','ariana.orive@gmail.com','qweasdfgh*','7771236544',1,'administrador',NULL,'2025-08-10 21:06:04'),
(3,'Angel Manuel','Alonso','Gomez','agao241496@gmail.com','qweasdfgh*','7331533600',1,'estudiante','AGAO241496','2025-08-10 23:37:45'),
(4,'Ariana Paola','Orive','Cardiel','ocao241496@gmail.com','qweasdfgh*','7778956322',1,'estudiante','OCAO241944','2025-08-10 23:45:52');

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id_producto` INTEGER PRIMARY KEY AUTOINCREMENT,
  `nombre_producto` VARCHAR(100) NOT NULL UNIQUE,
  `descripcion` TEXT NOT NULL,
  `precio` DECIMAL(5,2) NOT NULL,
  `tiempo_preparacion` VARCHAR(8) NOT NULL,
  `disponible` BOOLEAN NOT NULL DEFAULT 1,
  `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `imagen` VARCHAR(255)
);

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` VALUES 
(1,'Albondigas','Platillo de albondigas con pasta (tenedor no incluido)',30.00,'00:00:20',1,'2025-08-10 22:01:11','1754889901153_albondigas.jpg'),
(2,'Camarones','Son mariscos, muchos maricos',40.00,'00:00:45',1,'2025-08-10 22:13:31','1754889992220_camarones-macuil.jpg'),
(3,'Hamburgesa','Hamburgesa con pan integral, carne de muu, queso amarillo, lechuga, jitomate',30.00,'00:00:28',1,'2025-08-10 22:15:08','1754890053644_hamburgesa.png'),
(4,'Tacos de carbon','Son tacos con carne, no contiene carbon',20.00,'00:00:10',1,'2025-08-10 22:18:07','1754890157622_tacos.jpg'),
(5,'Morisqueta','Arroz, frijoles, queso y salsa',25.00,'00:01:00',1,'2025-08-10 23:31:17','1754890239372_Morisqueta_michoacana.jpg'),
(6,'Platillos','Son dos platillos',100.00,'20:00:00',1,'2025-08-10 23:32:36','1754890336606_platillos.jpg'),
(7,'Sopa de pollo','Es una sopa caldosa con pollo verduras y condimento',30.00,'00:30:00',1,'2025-08-11 23:04:56','1754975017801_Sopa-de-pollo.jpg');

--
-- Table structure for table `productos_categorias`
--

DROP TABLE IF EXISTS `productos_categorias`;
CREATE TABLE `productos_categorias` (
  `id_producto` INTEGER NOT NULL,
  `id_categoria` INTEGER NOT NULL,
  PRIMARY KEY (`id_producto`,`id_categoria`),
  FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `productos_categorias`
--

INSERT INTO `productos_categorias` VALUES 
(1,1),(1,3),(1,5),(1,6),
(2,3),(2,4),
(3,2),(3,5),
(4,5),
(5,3),
(6,3),
(7,1),(7,3),(7,5);

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE `pedidos` (
  `id_pedido` INTEGER PRIMARY KEY AUTOINCREMENT,
  `id_usuario` INTEGER NOT NULL,
  `folio` VARCHAR(20) NOT NULL UNIQUE,
  `tiempo_estimado` VARCHAR(8) NOT NULL,
  `estado` TEXT CHECK( estado IN ('pendiente','preparando','listo','entregado','cancelado') ) NOT NULL DEFAULT 'pendiente',
  `tiempo_entrega` VARCHAR(8) NOT NULL,
  `comentario` TEXT,
  `total` DECIMAL(10,2) NOT NULL,
  `fecha_pedido` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `pedidos`
--

INSERT INTO `pedidos` VALUES 
(1,3,'PED-715329F5E977','18:00:00','pendiente','18:00:00','Sin chile y sin cebolla porfavor',80.00,'2025-08-10 23:39:10'),
(2,3,'PED-BE58563E796D','18:00:00','pendiente','18:00:00','Sin comentarios',65.00,'2025-08-10 23:40:34'),
(3,3,'PED-DEA4E0CFB58D','18:00:00','pendiente','18:00:00','Sin comentarios',40.00,'2025-08-10 23:42:20'),
(4,4,'PED-6E37766DD557','18:00:00','pendiente','18:00:00','Con chile abanero',80.00,'2025-08-10 23:46:33'),
(5,4,'PED-E9949BCE9FE3','00:02:00','listo','00:02:00','Sin comentarios',125.00,'2025-08-11 22:40:18'),
(6,4,'PED-31B6CF11E9AE','20:02:20','preparando','20:02:20','Sin comentarios',190.00,'2025-08-11 22:43:35');

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
CREATE TABLE `detallepedido` (
  `id_detalle` INTEGER PRIMARY KEY AUTOINCREMENT,
  `id_pedido` INTEGER NOT NULL,
  `id_producto` INTEGER NOT NULL,
  `cantidad` INTEGER NOT NULL,
  `precio_unitario` DECIMAL(5,2) NOT NULL,
  `subtotal` DECIMAL(8,2) NOT NULL,
  `observaciones` TEXT,
  FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `detallepedido`
--

INSERT INTO `detallepedido` VALUES 
(1,1,3,2,30.00,60.00,''),
(2,1,4,1,20.00,20.00,''),
(3,2,4,2,20.00,40.00,''),
(4,2,5,1,25.00,25.00,''),
(5,3,4,2,20.00,40.00,''),
(6,4,3,2,30.00,60.00,'Sin cebolla'),
(7,4,4,1,20.00,20.00,'Con cebolla'),
(8,5,4,2,20.00,40.00,''),
(9,5,5,1,25.00,25.00,''),
(10,5,1,2,30.00,60.00,''),
(11,6,4,2,20.00,40.00,'Sin cebolla'),
(12,6,5,2,25.00,50.00,''),
(13,6,6,1,100.00,100.00,'Muy buenos');

--
-- Table structure for table `notificaciones`
--

DROP TABLE IF EXISTS `notificaciones`;
CREATE TABLE `notificaciones` (
  `id_notificacion` INTEGER PRIMARY KEY AUTOINCREMENT,
  `id_usuario` INTEGER NOT NULL,
  `id_pedido` INTEGER NOT NULL,
  `mensaje` TEXT NOT NULL,
  `leido` BOOLEAN NOT NULL DEFAULT 0,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `notificaciones`
--

INSERT INTO `notificaciones` VALUES 
(1,3,1,'El pedido PED-715329F5E977 esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:39:10'),
(2,3,2,'El pedido PED-BE58563E796D esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:40:34'),
(3,3,3,'El pedido PED-DEA4E0CFB58D esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:42:20'),
(4,4,4,'El pedido PED-6E37766DD557 esta pendiente. Tiempo estimado: 18:00:00',0,'2025-08-10 23:46:33'),
(5,4,5,'El pedido PED-E9949BCE9FE3 esta pendiente. Tiempo estimado: 00:02:00',0,'2025-08-11 22:40:18'),
(6,4,6,'El pedido PED-31B6CF11E9AE esta pendiente. Tiempo estimado: 20:02:20',0,'2025-08-11 22:43:36'),
(7,4,6,'El pedido PED-31B6CF11E9AE esta preparando. Tiempo estimado: 20:02:20',0,'2025-08-11 23:05:12'),
(8,4,5,'El pedido PED-E9949BCE9FE3 esta preparando. Tiempo estimado: 00:02:00',0,'2025-08-11 23:05:19'),
(9,4,5,'El pedido PED-E9949BCE9FE3 esta listo. Tiempo estimado: 00:02:00',0,'2025-08-11 23:05:21');

--
-- Table structure for table `reseniapedido`
--

DROP TABLE IF EXISTS `reseniapedido`;
CREATE TABLE `reseniapedido` (
  `id_resenia` INTEGER PRIMARY KEY AUTOINCREMENT,
  `id_usuario` INTEGER NOT NULL,
  `id_pedido` INTEGER NOT NULL,
  `calificacion` INTEGER NOT NULL CHECK(calificacion >= 0 AND calificacion <= 127),
  `comentario` TEXT,
  `fecha_resenia` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(`id_usuario`, `id_pedido`),
  FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Table structure for table `reseniaproductos`
--

DROP TABLE IF EXISTS `reseniaproductos`;
CREATE TABLE `reseniaproductos` (
  `id_resenia` INTEGER PRIMARY KEY AUTOINCREMENT,
  `id_usuario` INTEGER NOT NULL,
  `id_producto` INTEGER NOT NULL,
  `calificacion` INTEGER NOT NULL CHECK(calificacion >= 0 AND calificacion <= 127),
  `comentario` TEXT,
  `fecha_resenia` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(`id_usuario`, `id_producto`),
  FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Table structure for table `sugerencias`
--

DROP TABLE IF EXISTS `sugerencias`;
CREATE TABLE `sugerencias` (
  `id_sugerencia` INTEGER PRIMARY KEY AUTOINCREMENT,
  `id_usuario` INTEGER NOT NULL,
  `asunto` VARCHAR(100),
  `comentario` TEXT NOT NULL,
  `estado` TEXT CHECK( estado IN ('pendiente','revisada') ) NOT NULL DEFAULT 'pendiente',
  `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_revision` DATETIME,
  FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table `sugerencias`
--

INSERT INTO `sugerencias` VALUES 
(1,3,'No hay comida','No hay comida No hay comida No hay comida No hay comida No hay comida No hay comida No hay comida No hay comida ','pendiente','2025-08-10 23:43:15',NULL),
(2,3,'Agregen las mojarras','Porfavor y muchas gracias','pendiente','2025-08-10 23:43:51',NULL),
(3,4,'Agregen los tacos dorados','Por favor y muchas gracias','pendiente','2025-08-10 23:47:10',NULL);