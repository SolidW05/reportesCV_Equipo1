-- ============================================================
-- Script de inicialización: autoridades_db
-- Se ejecuta automáticamente la primera vez que arranca
-- el contenedor MySQL (solo si el volumen está vacío)
-- ============================================================

CREATE DATABASE IF NOT EXISTS autoridades_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
USE autoridades_db;

-- ============================================================
-- TABLA: municipios (debe crearse PRIMERO porque
-- autoridades tiene una FK que apunta a esta tabla)
-- ============================================================
CREATE TABLE IF NOT EXISTS municipios (
  idMunicipio INT          NOT NULL AUTO_INCREMENT,
  municipio   VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (idMunicipio)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO municipios (idMunicipio, municipio) VALUES
(1,'Acatic'),(2,'Acatlán de Juárez'),(3,'Ahualulco de Mercado'),
(4,'Amacueca'),(5,'Amatitán'),(6,'Ameca'),(7,'Arandas'),
(8,'Atemajac de Brizuela'),(9,'Atengo'),(10,'Atenguillo'),
(11,'Atotonilco el Alto'),(12,'Atoyac'),(13,'Autlán de Navarro'),
(14,'Ayotlán'),(15,'Ayutla'),(16,'Bolaños'),(17,'Cabo Corrientes'),
(18,'Casimiro Castillo'),(19,'Cañadas de Obregón'),(20,'Chapala'),
(21,'Chimaltitán'),(22,'Chiquilistlán'),(23,'Cihuatlán'),(24,'Cocula'),
(25,'Colotlán'),(26,'Encarnación de Díaz'),(27,'Etzatlán'),
(28,'Guachinango'),(29,'Guadalajara'),(30,'Hostotipaquillo'),
(31,'Huejuquilla el Alto'),(32,'Huejúcar'),
(33,'Ixtlahuacán de los Membrillos'),(34,'Ixtlahuacán del Río'),
(35,'Juanacatlán'),(36,'Juchitlán'),(37,'La Barca'),(38,'La Huerta'),
(39,'La Manzanilla de la Paz'),(40,'Lagos de Moreno'),(41,'Magdalena'),
(42,'Mascota'),(43,'Mazamitla'),(44,'Mexticacán'),(45,'Mezquitic'),
(46,'Mixtlán'),(47,'Ocotlán'),(48,'Ojuelos de Jalisco'),(49,'Pihuamo'),
(50,'Poncitlán'),(51,'Puerto Vallarta'),(52,'Quitupan'),
(53,'San Cristóbal de la Barranca'),(54,'San Diego de Alejandría'),
(55,'San Gabriel'),(56,'San Ignacio Cerro Gordo'),
(57,'San Juan de los Lagos'),(58,'San Juanito de Escobedo'),
(59,'San Julián'),(60,'Tamazula de Gordiano'),(61,'Tapalpa'),
(62,'Tecalitlán'),(63,'Techaluta de Montenegro'),(64,'Tecolotlán'),
(65,'Tenamaxtlán'),(66,'Teocaltiche'),(67,'Teocuitatlán de Corona'),
(68,'Tepatitlán de Morelos'),(69,'Tequila'),(70,'Teuchitlán'),
(71,'Tizapán el Alto'),(72,'Tlajomulco de Zúñiga'),(73,'Tolimán'),
(74,'Tomatlán'),(75,'Tonalá'),(76,'Tonaya'),(77,'Tonila'),
(78,'Totatiche'),(79,'Tototlán'),(80,'Tuxcacuesco'),(81,'Tuxcueca'),
(82,'Tuxpan'),(83,'Unión de San Antonio'),(84,'Unión de Tula'),
(85,'Valle de Guadalupe'),(86,'Concepción de Buenos Aires'),
(87,'Cuautitlán de García Barragán'),(88,'Cuautla'),(89,'Cuquío'),
(90,'Degollado'),(91,'Ejutla'),(92,'El Arenal'),(93,'El Grullo'),
(94,'El Limón'),(95,'El Salto'),(96,'Jalostotitlán'),(97,'Jamay'),
(98,'Jesús María'),(99,'Jilotlán de los Dolores'),(100,'Jocotepec'),
(101,'San Marcos'),(102,'San Martín Hidalgo'),
(103,'San Martín de Bolaños'),(104,'San Miguel el Alto'),
(105,'San Sebastián del Oeste'),(106,'Santa María de los Ángeles'),
(107,'Santa María del Oro'),(108,'Sayula'),(109,'Tala'),
(110,'Talpa de Allende'),(111,'Zapotlán el Grande'),
(112,'Zapotlán del Rey'),(113,'Zapotlanejo'),(114,'Valle de Juárez'),
(115,'Zapotitlán de Vadillo'),(116,'Zapotiltic'),(117,'Villa Corona'),
(118,'Zapopan'),(119,'Villa Guerrero'),(120,'Villa Hidalgo'),
(121,'Villa Purificación'),(122,'Yahualica de González Gallo'),
(123,'Zacoalco de Torres'),(124,'Gómez Farías'),
(125,'San Pedro Tlaquepaque');

-- ============================================================
-- TABLA: autoridades
-- Coincide exactamente con tu entidad Autoridad.java:
--   idAutoridad  → idAutoridades
--   idUsuario    → idUsuarios
--   telefono     → Telefono
--   municipioId  → municipio (FK a municipios)
-- ============================================================
CREATE TABLE IF NOT EXISTS autoridades (
  idAutoridades INT         NOT NULL AUTO_INCREMENT,
  idUsuarios    INT         NOT NULL,
  Telefono      VARCHAR(10) NOT NULL,
  municipio     INT,
  PRIMARY KEY (idAutoridades),
  FOREIGN KEY (municipio) REFERENCES municipios(idMunicipio)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;