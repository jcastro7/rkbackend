# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.9)
# Database: runakuna_dev
# Generation Time: 2017-07-15 17:07:32 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table accion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `accion`;

CREATE TABLE `accion` (
  `IdAccion` int(11) NOT NULL AUTO_INCREMENT,
  `IdModulo` int(11) DEFAULT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Etiqueta` varchar(200) DEFAULT NULL,
  `TipoAccion` char(1) DEFAULT NULL,
  `AutorizacionDefecto` tinyint(1) NOT NULL,
  `Editable` tinyint(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdAccion`),
  KEY `fkModulo_Accion` (`IdModulo`),
  CONSTRAINT `fkModulo_Accion` FOREIGN KEY (`IdModulo`) REFERENCES `modulo` (`IdModulo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `accion` WRITE;
/*!40000 ALTER TABLE `accion` DISABLE KEYS */;

INSERT INTO `accion` (`IdAccion`, `IdModulo`, `Nombre`, `Etiqueta`, `TipoAccion`, `AutorizacionDefecto`, `Editable`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(307,46,'Acceder','Mi Dashboard','A',1,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(308,47,'Acceder','Jefe','A',1,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(309,48,'Acceder','Analista de RRHH','A',1,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(310,49,'Acceder','Acceder','A',1,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(311,49,'Importar','Importar','P',1,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(312,49,'Exportar','Exportar','P',1,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(313,49,'Agregar','Dar de Alta','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(314,49,'Ver','Ver Empleado','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(315,49,'DarDeBaja','Dar de Baja','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(316,49,'GestionarHorarios','Gestionar Horarios','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(317,49,'GestionarContratos','Gestionar Contratos','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(318,49,'GestionarHistorialDeTrabajo','Gestionar Historial de Trabajo','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(319,49,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(320,49,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(321,50,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(322,50,'InsertarSolicitudCambioMarcacion','InsertarSolicitudCambioMarcacion','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(323,51,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(324,52,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(325,53,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(326,54,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(327,55,'Acceder','Acceder','A',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(328,55,'Editar','Editar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(329,55,'Enviar','Enviar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(330,55,'Aprobar','Aprobar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(331,55,'Rechazar','Rechazar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(332,55,'Devolver','Devolver','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(333,55,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(334,56,'Acceder','Acceder','A',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(335,56,'Editar','Editar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(336,56,'Enviar','Enviar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(337,56,'Aprobar','Aprobar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(338,56,'Rechazar','Rechazar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(339,56,'Devolver','Devolver','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(340,56,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(341,57,'Acceder','Acceder','A',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(342,57,'Crear','Crear','I',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(343,57,'Editar','Editar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(344,57,'Enviar','Enviar','P',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(345,57,'Aprobar','Aprobar y Enviar a RRHH','P',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(346,57,'Validar','Validar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(347,57,'Rechazar','Rechazar','P',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(348,57,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(349,58,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(350,58,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(351,58,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(352,58,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(353,59,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(354,59,'Editar','Editar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(355,59,'Exportar','Exportar','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(356,59,'Aprobar','Aprobar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(357,59,'Rechazar','Rechazar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(358,60,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(359,60,'Aprobar','Aprobar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(360,60,'Rechazar','Rechazar','M',0,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(361,60,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(362,61,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(363,61,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(364,61,'Ver','Ver','P',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(365,62,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(366,62,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(367,63,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(368,63,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(369,64,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(370,64,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(371,64,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(372,64,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(373,65,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(374,65,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(375,66,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(376,66,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(377,66,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(378,66,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(379,67,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(380,67,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(381,67,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(382,67,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(383,68,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(384,68,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(385,69,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(386,69,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(387,70,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(388,70,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(389,71,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(390,71,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(391,71,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(392,71,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(393,72,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(394,72,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(395,72,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(396,72,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(397,73,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(398,73,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(399,73,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(400,73,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(401,74,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(402,74,'Agregar','Agregar','I',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(403,74,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(404,74,'Eliminar','Eliminar','E',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(405,75,'Acceder','Acceder','A',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(406,75,'Editar','Editar','M',0,1,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(407,76,'Acceder','Acceder','A',1,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(408,76,'Editar','Editar','M',1,0,'JOE','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(409,65,'Acceder','Ver','A',0,1,'JOE','2017-02-13 11:53:37.360000',NULL,NULL,1),
	(410,77,'Acceder','Acceder','A',1,1,'JOE','2017-02-20 16:33:11.530000',NULL,NULL,0),
	(411,54,'Editar','Editar','E',0,1,'JOE','2017-03-03 11:11:43.914000',NULL,NULL,1),
	(412,78,'Acceder','Acceder','A',0,1,'JOE','2017-03-28 13:26:57.380000',NULL,NULL,1),
	(413,78,'Agregar','Agregar','I',0,1,'JOE','2017-03-28 13:26:57.390000',NULL,NULL,1),
	(414,78,'Editar','Editar','M',0,1,'JOE','2017-03-28 13:26:57.397000',NULL,NULL,1),
	(415,78,'Eliminar','Eliminar','E',0,1,'JOE','2017-03-28 13:26:57.403000',NULL,NULL,1);

/*!40000 ALTER TABLE `accion` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table alerta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `alerta`;

CREATE TABLE `alerta` (
  `IdAlerta` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpresa` int(11) NOT NULL,
  `Codigo` varchar(20) NOT NULL,
  `TipoAlerta` varchar(2) NOT NULL,
  `TipoNotificacion` varchar(2) NOT NULL,
  `Asunto` varchar(200) NOT NULL,
  `Cuerpo` longtext NOT NULL,
  `Alerta` varchar(250) DEFAULT NULL,
  `JefeProyecto` tinyint(1) NOT NULL,
  `JefeDepartamento` tinyint(1) NOT NULL,
  `JefeUnidad` tinyint(1) NOT NULL,
  `JefeEmpresa` tinyint(1) NOT NULL,
  `Agrupamiento` varchar(250) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdAlerta`),
  KEY `fkEmpresa_Alerta` (`IdEmpresa`),
  CONSTRAINT `fkEmpresa_Alerta` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `alerta` WRITE;
/*!40000 ALTER TABLE `alerta` DISABLE KEYS */;

INSERT INTO `alerta` (`IdAlerta`, `IdEmpresa`, `Codigo`, `TipoAlerta`, `TipoNotificacion`, `Asunto`, `Cuerpo`, `Alerta`, `JefeProyecto`, `JefeDepartamento`, `JefeUnidad`, `JefeEmpresa`, `Agrupamiento`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,1,'ALERTA01','TA','I','Tardanza - [Fecha]','El dia [Fecha] ha llegado [Minutos Tardanza] minutos tarde.','El dia [Fecha] ha llegado [Minutos Tardanza] minutos tarde.',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,1,'ALERTA02','TA','G','Tardanzas - [Fecha] - [Proyecto, Departamento, Unidad de Negocio o Empresa]','Las tardanzas registradas el dia [Fecha] son las siguientes:','',1,0,0,0,'Empresa/Unidad de Negocio/Departamento Area/Proyecto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,1,'ALERTA03','IN','I','Inasistencia - [Fecha]','El dia [Fecha] no ha realizado ninguna marcación.','El dia [Fecha] no ha realizado ninguna marcación.',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,1,'ALERTA04','IN','G','Inasistencias - [Fecha] - [Proyecto, Departamento, Unidad de Negocio o Empresa]','Las inasistencias registradas el dia [Fecha] son las siguientes:','',1,0,0,0,'Empresa/Unidad de Negocio/Departamento Area/Proyecto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,1,'ALERTA05','VC','G','Vencimiento de Contrato','Los contratos de los siguientes empleados estan por vencer:','',1,0,0,0,'Empresa','A','TSS','2017-02-07 00:00:00.000000','GIANCARLO.BORJAS','2017-03-22 12:43:35.166000',5),
	(6,1,'ALERTA06','EP','I','Solicitud de Permiso - [Codigo]','El empleado [Empleado] ha solicitado un permiso para el dia [Fecha] desde las [Hora Inicio] a las [Hora Fin] a [Jefe Inmediato].','El empleado [Empleado] ha solicitado un permiso para el dia [Fecha] desde las [Hora Inicio] a las [Hora Fin] a [Jefe Inmediato].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,1,'ALERTA07','EV','I','Solicitud de Vacaciones - [Codigo]','El empleado [Empleado] ha solicitado vacaciones para el desde [Fecha Inicio] a [Fecha Fin] a [Jefe Inmediato].','El empleado [Empleado] ha solicitado vacaciones para el desde [Fecha Inicio] a [Fecha Fin] a [Jefe Inmediato].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,1,'ALERTA08','EH','I','Solicitud de Horas Extras - [Codigo]','El empleado [Empleado] ha solicitado horas extras para el dia [Fecha] desde las [Hora Inicio] a las [Hora Fin] a [Jefe Inmediato].','El empleado [Empleado] ha solicitado horas extras para el dia [Fecha] desde las [Hora Inicio] a las [Hora Fin] a [Jefe Inmediato].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,1,'ALERTA09','RP','I','Resolución de Solicitud de Permiso - [Codigo]','Se ha [Estado] el permiso solicitado el dia [Fecha] desde las [Hora Inicio] a las [Hora Fin].','Se ha [Estado] el permiso solicitado el dia [Fecha] desde las [Hora Inicio] a las [Hora Fin].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,1,'ALERTA10','RV','I','Resolución de Solicitud de Vacaciones - [Codigo]','Se ha [Estado] las vacaciones solicitadas desde [Fecha Inicio] hasta [Fecha Fin].','Se ha [Estado] las vacaciones solicitadas desde [Fecha Inicio] hasta [Fecha Fin].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,1,'ALERTA11','RH','I','Resolución de Solicitud de Horas Extras - [Codigo]','Se ha [Estado] las horas extras solicitado el dia [Fecha] de las [Hora Inicio] a las [Hora Fin].','Se ha [Estado] las horas extras solicitado el dia [Fecha] de las [Hora Inicio] a las [Hora Fin].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,1,'ALERTA12','LI','I','Excede el Máximo Número de Días de Licencia','El empleado [Empleado] ha solicitado [Dias de Licencia] dias de licencia con motivo [Tipo Licencia] en el periodo [Periodo], lo cual excede el máximo permitido.','Ha solicitado [Dias de Licencia] dias de licencia con motivo [Tipo Licencia], lo cual excede el máximo permitido.  ',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,1,'ALERTA13','CP','G','Creación de un nuevo Periodo','Se creo un nuevo periodo para los siguientes empleados:','',0,0,0,0,'Empresa','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(14,1,'ALERTA14','EL','I','Envio de Licencia  - [Codigo]','El empleado [Empleado] ha solicitado una licencia ([Tipo Licencia]) del dia [Fecha Inicio] al dia [Fecha Fin] a [Jefe Inmediato].','El empleado [Empleado] ha solicitado una licencia ([Tipo Licencia]) del dia [Fecha Inicio] al dia [Fecha Fin] a [Jefe Inmediato].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(15,1,'ALERTA15','EL','I','Resolución Parcial de la Licencia - [Codigo]','El jefe [Jefe Inmediato] actualizo la licencia solicitada por parte del empleado [Empleado] del dia [Fecha Inicio] al dia [Fecha Fin] al estado [Estado].','El jefe [Jefe Inmediato] actualizo la licencia ([Tipo Licencia])  solicitada por parte del empleado [Empleado] del dia [Fecha Inicio] al dia [Fecha Fin] al estado [Estado].',0,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(16,1,'ALERTA16','EL','I','Resolución Final de la Licencia - [Codigo]','El area de RRHH ha [Estado] la licencia solicitada por parte del empleado [Empleado] del dia [Fecha Inicio] al dia [Fecha Fin].','El area de RRHH ha [Estado] la licencia solicitada por parte del empleado [Empleado] del dia [Fecha Inicio] al dia [Fecha Fin].',1,0,0,0,'','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(17,1,'ALERTA17','DI','I','Dispositivo de Marcaciones Inactivo','El dispositivo [Codigo] no esta funcionando correctamente. Las marcaciones no se estan registrando.','El dispositivo [Codigo] no esta funcionando correctamente. Las marcaciones no se estan registrando.',0,0,0,0,'','A','TSS','2017-03-27 15:11:13.140000',NULL,NULL,1);

/*!40000 ALTER TABLE `alerta` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table alertaempleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `alertaempleado`;

CREATE TABLE `alertaempleado` (
  `IdAlertaEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdAlerta` int(11) DEFAULT NULL,
  `IdEmpleado` int(11) NOT NULL,
  `Mensaje` varchar(250) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdAlertaEmpleado`),
  KEY `fkEmpleado_AlertaEmpleado` (`IdEmpleado`),
  KEY `fkAlerta_AlertaEmpleado` (`IdAlerta`),
  CONSTRAINT `fkAlerta_AlertaEmpleado` FOREIGN KEY (`IdAlerta`) REFERENCES `alerta` (`IdAlerta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpleado_AlertaEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table alertasubscriptor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `alertasubscriptor`;

CREATE TABLE `alertasubscriptor` (
  `IdAlertaSubscriptor` int(11) NOT NULL AUTO_INCREMENT,
  `IdAlerta` int(11) DEFAULT NULL,
  `IdEmpleado` int(11) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdAlertaSubscriptor`),
  KEY `fkEmpleado_AlertaSubscriptor` (`IdEmpleado`),
  KEY `fkAlerta_AlertaSubscriptor` (`IdAlerta`),
  CONSTRAINT `fkAlerta_AlertaSubscriptor` FOREIGN KEY (`IdAlerta`) REFERENCES `alerta` (`IdAlerta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpleado_AlertaSubscriptor` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table autorizacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `autorizacion`;

CREATE TABLE `autorizacion` (
  `IdAutorizacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdRol` int(11) DEFAULT NULL,
  `IdAccion` int(11) DEFAULT NULL,
  `Autorizado` tinyint(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdAutorizacion`),
  KEY `fkRol_Autorizacion` (`IdRol`),
  KEY `fkAccion_Autorizacion` (`IdAccion`),
  CONSTRAINT `fkAccion_Autorizacion` FOREIGN KEY (`IdAccion`) REFERENCES `accion` (`IdAccion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkRol_Autorizacion` FOREIGN KEY (`IdRol`) REFERENCES `rol` (`IdRol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `autorizacion` WRITE;
/*!40000 ALTER TABLE `autorizacion` DISABLE KEYS */;

INSERT INTO `autorizacion` (`IdAutorizacion`, `IdRol`, `IdAccion`, `Autorizado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(409,1,307,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(410,1,308,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(411,1,309,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(412,1,310,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(413,1,311,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(414,1,312,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(415,1,313,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(416,1,314,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(417,1,315,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(418,1,316,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(419,1,317,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(420,1,318,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(421,1,319,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(422,1,320,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(423,1,321,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(424,1,322,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(425,1,323,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(426,1,324,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(427,1,325,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(428,1,326,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(429,1,327,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(430,1,328,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(431,1,329,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(432,1,330,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(433,1,331,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(434,1,332,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(435,1,333,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(436,1,334,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(437,1,335,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(438,1,336,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(439,1,337,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(440,1,338,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(441,1,339,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(442,1,340,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(443,1,341,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(444,1,342,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(445,1,343,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(446,1,344,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(447,1,345,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(448,1,346,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(449,1,347,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(450,1,348,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(451,1,349,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(452,1,350,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(453,1,351,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(454,1,352,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(455,1,353,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(456,1,354,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(457,1,355,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(458,1,356,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(459,1,357,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(460,1,358,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(461,1,359,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(462,1,360,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(463,1,361,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(464,1,362,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(465,1,363,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(466,1,364,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(467,1,365,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(468,1,366,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(469,1,367,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(470,1,368,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(471,1,369,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(472,1,370,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(473,1,371,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(474,1,372,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(475,1,373,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(476,1,374,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(477,1,375,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(478,1,376,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(479,1,377,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(480,1,378,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(481,1,379,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(482,1,380,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(483,1,381,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(484,1,382,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(485,1,383,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(486,1,384,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(487,1,385,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(488,1,386,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(489,1,387,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(490,1,388,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(491,1,389,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(492,1,390,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(493,1,391,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(494,1,392,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(495,1,393,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(496,1,394,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(497,1,395,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(498,1,396,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(499,1,397,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(500,1,398,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(501,1,399,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(502,1,400,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(503,1,401,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(504,1,402,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(505,1,403,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(506,1,404,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(507,1,405,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(508,1,406,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(509,1,407,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(510,1,408,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(511,3,307,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(512,3,308,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(513,3,309,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(514,3,310,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(515,3,311,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(516,3,312,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(517,3,313,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(518,3,314,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(519,3,315,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(520,3,316,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(521,3,317,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(522,3,318,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(523,3,319,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(524,3,320,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(525,3,321,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(526,3,322,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(527,3,323,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(528,3,324,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(529,3,325,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(530,3,326,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(531,3,327,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(532,3,328,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(533,3,329,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(534,3,330,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(535,3,331,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(536,3,332,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(537,3,333,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(538,3,334,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(539,3,335,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(540,3,336,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(541,3,337,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(542,3,338,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(543,3,339,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(544,3,340,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(545,3,341,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(546,3,342,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(547,3,343,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(548,3,344,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(549,3,345,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(550,3,346,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(551,3,347,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(552,3,348,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(553,3,349,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(554,3,350,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(555,3,351,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(556,3,352,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(557,3,353,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(558,3,354,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(559,3,355,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(560,3,356,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(561,3,357,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(562,3,358,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(563,3,359,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(564,3,360,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(565,3,361,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(566,3,362,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(567,3,363,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(568,3,364,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(569,3,365,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(570,3,366,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(571,3,367,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(572,3,368,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(573,3,369,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(574,3,370,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(575,3,371,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(576,3,372,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(577,3,373,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(578,3,374,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(579,3,375,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(580,3,376,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(581,3,377,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(582,3,378,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(583,3,379,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(584,3,380,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(585,3,381,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(586,3,382,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(587,3,383,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(588,3,384,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(589,3,385,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(590,3,386,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(591,3,387,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(592,3,388,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(593,3,389,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(594,3,390,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(595,3,391,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(596,3,392,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(597,3,393,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(598,3,394,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(599,3,395,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(600,3,396,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(601,3,397,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(602,3,398,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(603,3,399,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(604,3,400,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(605,3,401,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(606,3,402,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(607,3,403,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(608,3,404,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(609,3,405,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(610,3,406,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(611,3,407,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(612,3,408,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(613,4,307,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(614,4,308,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(615,4,309,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(616,4,310,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(617,4,311,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(618,4,312,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(619,4,313,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(620,4,314,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(621,4,315,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(622,4,316,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(623,4,317,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(624,4,318,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(625,4,319,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(626,4,320,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(627,4,321,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(628,4,322,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(629,4,323,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(630,4,324,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(631,4,325,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(632,4,326,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(633,4,327,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(634,4,328,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(635,4,329,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(636,4,330,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(637,4,331,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(638,4,332,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(639,4,333,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(640,4,334,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(641,4,335,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(642,4,336,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(643,4,337,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(644,4,338,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(645,4,339,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(646,4,340,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(647,4,341,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(648,4,342,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(649,4,343,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(650,4,344,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(651,4,345,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(652,4,346,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(653,4,347,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(654,4,348,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(655,4,349,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(656,4,350,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(657,4,351,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(658,4,352,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(659,4,353,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(660,4,354,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(661,4,355,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(662,4,356,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(663,4,357,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(664,4,358,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(665,4,359,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(666,4,360,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(667,4,361,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(668,4,362,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(669,4,363,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(670,4,364,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(671,4,365,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(672,4,366,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(673,4,367,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(674,4,368,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(675,4,369,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(676,4,370,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(677,4,371,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(678,4,372,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(679,4,373,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(680,4,374,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(681,4,375,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(682,4,376,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(683,4,377,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(684,4,378,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(685,4,379,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(686,4,380,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(687,4,381,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(688,4,382,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(689,4,383,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(690,4,384,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(691,4,385,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(692,4,386,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(693,4,387,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(694,4,388,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(695,4,389,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(696,4,390,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(697,4,391,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(698,4,392,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(699,4,393,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(700,4,394,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(701,4,395,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(702,4,396,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(703,4,397,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(704,4,398,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(705,4,399,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(706,4,400,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(707,4,401,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(708,4,402,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(709,4,403,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(710,4,404,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(711,4,405,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(712,4,406,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(713,4,407,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(714,4,408,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(715,2,307,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(716,2,308,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(717,2,309,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(718,2,310,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(719,2,311,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(720,2,312,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(721,2,313,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(722,2,314,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(723,2,315,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(724,2,316,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(725,2,317,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(726,2,318,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(727,2,319,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(728,2,320,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(729,2,321,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(730,2,322,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(731,2,323,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(732,2,324,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(733,2,325,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(734,2,326,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(735,2,327,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(736,2,328,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(737,2,329,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(738,2,330,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(739,2,331,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(740,2,332,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(741,2,333,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(742,2,334,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(743,2,335,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(744,2,336,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(745,2,337,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(746,2,338,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(747,2,339,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(748,2,340,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(749,2,341,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(750,2,342,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(751,2,343,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(752,2,344,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(753,2,345,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(754,2,346,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(755,2,347,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(756,2,348,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(757,2,349,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(758,2,350,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(759,2,351,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(760,2,352,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(761,2,353,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(762,2,354,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(763,2,355,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(764,2,356,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(765,2,357,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(766,2,358,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(767,2,359,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(768,2,360,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(769,2,361,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(770,2,362,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(771,2,363,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(772,2,364,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(773,2,365,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(774,2,366,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(775,2,367,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(776,2,368,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(777,2,369,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(778,2,370,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(779,2,371,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(780,2,372,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(781,2,373,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(782,2,374,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(783,2,375,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(784,2,376,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(785,2,377,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(786,2,378,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(787,2,379,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(788,2,380,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(789,2,381,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(790,2,382,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(791,2,383,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(792,2,384,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(793,2,385,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(794,2,386,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(795,2,387,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(796,2,388,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(797,2,389,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(798,2,390,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(799,2,391,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(800,2,392,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(801,2,393,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(802,2,394,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(803,2,395,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(804,2,396,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(805,2,397,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(806,2,398,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(807,2,399,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(808,2,400,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(809,2,401,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(810,2,402,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(811,2,403,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(812,2,404,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(813,2,405,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(814,2,406,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(815,2,407,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(816,2,408,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(817,1,410,0,'TSS','2017-02-20 16:35:24.847000',NULL,NULL,0),
	(818,2,410,1,'TSS','2017-02-20 16:35:51.680000',NULL,NULL,0),
	(819,3,410,0,'TSS','2017-02-20 16:35:57.030000',NULL,NULL,0),
	(820,4,410,0,'TSS','2017-02-20 16:36:01.777000',NULL,NULL,0),
	(821,3,411,1,'TSS','2017-03-03 12:52:40.493000',NULL,NULL,1),
	(822,5,307,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(823,5,308,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(824,5,309,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(825,5,310,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(826,5,311,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(827,5,312,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(828,5,313,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(829,5,314,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(830,5,315,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(831,5,316,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(832,5,317,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(833,5,318,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(834,5,319,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(835,5,320,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(836,5,321,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(837,5,322,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(838,5,323,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(839,5,324,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(840,5,325,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(841,5,326,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(842,5,327,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(843,5,328,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(844,5,329,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(845,5,330,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(846,5,331,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(847,5,332,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(848,5,333,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(849,5,334,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(850,5,335,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(851,5,336,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(852,5,337,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(853,5,338,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(854,5,339,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(855,5,340,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(856,5,341,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(857,5,342,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(858,5,343,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(859,5,344,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(860,5,345,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(861,5,346,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(862,5,347,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(863,5,348,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(864,5,349,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(865,5,350,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(866,5,351,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(867,5,352,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(868,5,353,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(869,5,354,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(870,5,355,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(871,5,356,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(872,5,357,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(873,5,358,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(874,5,359,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(875,5,360,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(876,5,361,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(877,5,362,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(878,5,363,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(879,5,364,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(880,5,365,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(881,5,366,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(882,5,367,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(883,5,368,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(884,5,369,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(885,5,370,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(886,5,371,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(887,5,372,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(888,5,373,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(889,5,374,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(890,5,375,1,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(891,5,376,1,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(892,5,377,1,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(893,5,378,1,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(894,5,379,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(895,5,380,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(896,5,381,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(897,5,382,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(898,5,383,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(899,5,384,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(900,5,385,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(901,5,386,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(902,5,387,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(903,5,388,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(904,5,389,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(905,5,390,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(906,5,391,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(907,5,392,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(908,5,393,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(909,5,394,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(910,5,395,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(911,5,396,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(912,5,397,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(913,5,398,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(914,5,399,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(915,5,400,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(916,5,401,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(917,5,402,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(918,5,403,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(919,5,404,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(920,5,405,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(921,5,406,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(922,5,407,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(923,5,408,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(924,5,409,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(925,5,410,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(926,5,411,0,'TSS','2017-03-20 13:09:05.910000',NULL,NULL,0),
	(927,4,409,0,'TSS','2017-03-20 13:34:04.693000',NULL,NULL,0),
	(928,4,411,0,'TSS','2017-03-20 13:34:04.700000',NULL,NULL,0),
	(929,3,409,0,'TSS','2017-03-20 13:34:04.707000',NULL,NULL,0),
	(930,2,409,0,'TSS','2017-03-20 13:34:04.710000',NULL,NULL,0),
	(931,2,411,0,'TSS','2017-03-20 13:34:04.717000',NULL,NULL,0),
	(932,1,409,0,'TSS','2017-03-20 13:34:04.723000',NULL,NULL,0),
	(933,1,411,0,'TSS','2017-03-20 13:34:04.730000',NULL,NULL,0),
	(934,1,412,1,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(935,1,413,1,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(936,1,414,1,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(937,1,415,1,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(938,2,412,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(939,2,413,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(940,2,414,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(941,2,415,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(942,3,412,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(943,3,413,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(944,3,414,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(945,3,415,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(946,4,412,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(947,4,413,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(948,4,414,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(949,4,415,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(950,5,412,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(951,5,413,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(952,5,414,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1),
	(953,5,415,0,'TSS','2017-03-28 13:35:11.763000',NULL,NULL,1);

/*!40000 ALTER TABLE `autorizacion` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bandasalarial
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bandasalarial`;

CREATE TABLE `bandasalarial` (
  `IdBandaSalarial` int(11) NOT NULL AUTO_INCREMENT,
  `IdCargo` int(11) NOT NULL,
  `IdMoneda` int(11) NOT NULL,
  `LimiteSuperior` decimal(10,2) DEFAULT NULL,
  `LimiteMedio` decimal(10,2) DEFAULT NULL,
  `LimiteInferior` decimal(10,2) DEFAULT NULL,
  `InicioVigencia` datetime(6) DEFAULT NULL,
  `FinVigencia` datetime(6) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdBandaSalarial`),
  KEY `fkCargo_BandaSalarial` (`IdCargo`),
  KEY `fkMoneda_BandaSalarial` (`IdMoneda`),
  CONSTRAINT `fkCargo_BandaSalarial` FOREIGN KEY (`IdCargo`) REFERENCES `cargo` (`IdCargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkMoneda_BandaSalarial` FOREIGN KEY (`IdMoneda`) REFERENCES `moneda` (`IdMoneda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table calendario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `calendario`;

CREATE TABLE `calendario` (
  `IdCalendario` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` datetime(6) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(250) DEFAULT NULL,
  `DiaCompleto` tinyint(1) NOT NULL,
  `HoraInicio` varchar(5) DEFAULT NULL,
  `HoraFin` varchar(5) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdCalendario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table cargo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cargo`;

CREATE TABLE `cargo` (
  `IdCargo` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpresa` int(11) NOT NULL,
  `IdUnidadDeNegocio` int(11) DEFAULT NULL,
  `IdDepartamentoArea` int(11) DEFAULT NULL,
  `IdSuperior` int(11) DEFAULT NULL,
  `Nombre` varchar(100) NOT NULL,
  `IdProyecto` int(11) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Descripcion` varchar(250) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdCargo`),
  KEY `fkEmpresa_Cargo` (`IdEmpresa`),
  KEY `fkUnidadDeNegocio_Cargo` (`IdUnidadDeNegocio`),
  KEY `fkDepartamentoArea_Cargo` (`IdDepartamentoArea`),
  KEY `fkCargo_Cargo` (`IdSuperior`),
  KEY `fkProyecto_Cargo` (`IdProyecto`),
  CONSTRAINT `fkCargo_Cargo` FOREIGN KEY (`IdSuperior`) REFERENCES `cargo` (`IdCargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkDepartamentoArea_Cargo` FOREIGN KEY (`IdDepartamentoArea`) REFERENCES `departamentoarea` (`IdDepartamentoArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpresa_Cargo` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkProyecto_Cargo` FOREIGN KEY (`IdProyecto`) REFERENCES `proyecto` (`IdProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUnidadDeNegocio_Cargo` FOREIGN KEY (`IdUnidadDeNegocio`) REFERENCES `unidaddenegocio` (`IdUnidadDeNegocio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;

INSERT INTO `cargo` (`IdCargo`, `IdEmpresa`, `IdUnidadDeNegocio`, `IdDepartamentoArea`, `IdSuperior`, `Nombre`, `IdProyecto`, `Estado`, `Descripcion`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(68,1,1,6,NULL,'ADM. ASSISTANT',NULL,'A','ADM. ASSISTANT','TSS','2017-02-07 00:00:00.000000','cron-usr','2017-03-14 13:47:59.506000',4),
	(69,1,1,6,NULL,'AUXILIAR DE OFICINA',NULL,'A','AUXILIAR DE OFICINA','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(70,1,1,6,NULL,'GENERAL MANAGER',NULL,'A','GENERAL MANAGER','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(71,1,1,6,NULL,'MANAGER ASSISTANT',NULL,'A','MANAGER ASSISTANT','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(72,1,1,6,NULL,'RECEPCIONIST',NULL,'A','RECEPCIONIST','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(73,1,1,9,NULL,'DBA JUNIOR',NULL,'A','DBA JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(74,1,1,9,NULL,'DBA MIDDLE',NULL,'A','DBA MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(75,1,1,9,NULL,'DBA SENIOR',NULL,'A','DBA SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(76,1,1,3,NULL,'ANALYST SENIOR',NULL,'A','ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(77,1,1,3,NULL,'ANALYST MIDDLE',NULL,'A','ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(78,1,1,3,NULL,'ANALYST JUNIOR',NULL,'A','ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(79,1,1,3,NULL,'ARCHITECT JUNIOR',NULL,'A','ARCHITECT JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(80,1,1,3,NULL,'ARCHITECT MIDDLE',NULL,'A','ARCHITECT MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(81,1,1,3,NULL,'ARCHITECT SENIOR',NULL,'A','ARCHITECT SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(82,1,1,3,NULL,'DEVELOPER JUNIOR',NULL,'A','DEVELOPER JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(83,1,1,3,NULL,'DEVELOPER MIDDLE',NULL,'A','DEVELOPER MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(84,1,1,3,NULL,'DEVELOPER SENIOR',NULL,'A','DEVELOPER SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(85,1,1,3,NULL,'INTEGRATOR JUNIOR',NULL,'A','INTEGRATOR JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(86,1,1,3,NULL,'INTEGRATOR MIDDLE',NULL,'A','INTEGRATOR MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(87,1,1,3,NULL,'INTEGRATOR SENIOR',NULL,'A','INTEGRATOR SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(88,1,1,3,NULL,'PROJECT MANAGER',NULL,'A','PROJECT MANAGER','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(89,1,1,3,NULL,'TECH. CONSULTANT',NULL,'A','TECH. CONSULTANT','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(90,1,1,3,NULL,'SENIOR TECHNICAL CONSULTANT',NULL,'A','SENIOR TECHNICAL CONSULTANT','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(91,1,1,3,NULL,'BUSINESS CONSULTANT',NULL,'A','BUSINESS CONSULTANT','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(92,1,1,3,NULL,'SENIOR BUSINESS CONSULTANT',NULL,'A','SENIOR BUSINESS CONSULTANT','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(93,1,1,3,NULL,'ANALISTA UI-UX',NULL,'A','ANALISTA UI-UX','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(94,1,1,5,NULL,'ANALYST SENIOR',NULL,'A','ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(95,1,1,5,NULL,'ANALYST MIDDLE',NULL,'A','ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(96,1,1,5,NULL,'ANALYST JUNIOR',NULL,'A','ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(97,1,1,5,NULL,'ASISTENTE COMERCIAL',NULL,'A','ASISTENTE COMERCIAL','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(98,1,1,5,NULL,'ESPECIALISTA COMERCIAL',NULL,'A','ESPECIALISTA COMERCIAL','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(99,1,1,5,NULL,'PROCESS ANALYST JUNIOR',NULL,'A','PROCESS ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(100,1,1,5,NULL,'PROCESS ANALYST MIDDLE',NULL,'A','PROCESS ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(101,1,1,5,NULL,'PROCESS ANALYST SENIOR',NULL,'A','PROCESS ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(102,1,1,5,NULL,'SALES EXECUTIVE',NULL,'A','SALES EXECUTIVE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(103,1,1,5,NULL,'SALES MANAGER',NULL,'A','SALES MANAGER','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(104,1,1,8,NULL,'AUXILIAR DE OFICINA',NULL,'A','AUXILIAR DE OFICINA','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(105,1,1,2,NULL,'ANALISTA UI-UX',NULL,'A','ANALISTA UI-UX','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(106,1,1,2,NULL,'DEVELOPER JUNIOR',NULL,'A','DEVELOPER JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(107,1,1,2,NULL,'DEVELOPER MIDDLE',NULL,'A','DEVELOPER MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(108,1,1,2,NULL,'DEVELOPER SENIOR',NULL,'A','DEVELOPER SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(109,1,1,2,NULL,'PROCESS ANALYST JUNIOR',NULL,'A','PROCESS ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(110,1,1,2,NULL,'PROCESS ANALYST MIDDLE',NULL,'A','PROCESS ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(111,1,1,2,NULL,'PROCESS ANALYST SENIOR',NULL,'A','PROCESS ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(112,1,1,7,NULL,'ANALYST JUNIOR',NULL,'A','ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(113,1,1,7,NULL,'ANALYST MIDDLE',NULL,'A','ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(114,1,1,7,NULL,'ANALYST SENIOR',NULL,'A','ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(115,1,1,7,NULL,'DEVELOPER JUNIOR',NULL,'A','DEVELOPER JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(116,1,1,7,NULL,'DEVELOPER MIDDLE',NULL,'A','DEVELOPER MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(117,1,1,7,NULL,'DEVELOPER SENIOR',NULL,'A','DEVELOPER SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(118,1,1,7,NULL,'PROJECT MANAGER',NULL,'A','','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(119,1,1,4,NULL,'QA ANALYST JUNIOR',NULL,'A','QA ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(120,1,1,4,NULL,'QA ANALYST MIDDLE',NULL,'A','QA ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(121,1,1,4,NULL,'QA ANALYST SENIOR',NULL,'A','QA ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(122,1,1,4,NULL,'QA MANAGER',NULL,'A','QA MANAGER','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(123,1,1,1,NULL,'HR MANAGER',NULL,'A','HR MANAGER','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(124,1,1,1,NULL,'IT SUPPORT JUNIOR',NULL,'A','IT SUPPORT JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(125,1,1,1,NULL,'IT SUPPORT MIDDLE',NULL,'A','IT SUPPORT MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(126,1,1,1,NULL,'IT SUPPORT SENIOR',NULL,'A','IT SUPPORT SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(127,1,1,1,NULL,'PROCESS ANALYST JUNIOR',NULL,'A','PROCESS ANALYST JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(128,1,1,1,NULL,'PROCESS ANALYST MIDDLE',NULL,'A','PROCESS ANALYST MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(129,1,1,1,NULL,'PROCESS ANALYST SENIOR',NULL,'A','PROCESS ANALYST SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(130,1,1,10,NULL,'DEVELOPER JUNIOR',NULL,'A','DEVELOPER JUNIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(131,1,1,10,NULL,'DEVELOPER MIDDLE',NULL,'A','DEVELOPER MIDDLE','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(132,1,1,10,NULL,'DEVELOPER SENIOR',NULL,'A','DEVELOPER SENIOR','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(133,1,1,10,NULL,'TECHNOLOGY MANAGER',NULL,'A','TECHNOLOGY MANAGER','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(134,1,1,3,NULL,'ASISTENTE COMERCIAL',NULL,'1','ASISTENTE COMERCIAL','TSS','2017-02-27 15:20:49.610000',NULL,NULL,0);

/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table centrocosto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `centrocosto`;

CREATE TABLE `centrocosto` (
  `IdCentroCosto` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(250) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `IdEmpresa` int(11) NOT NULL,
  `IdUnidadDeNegocio` int(11) DEFAULT NULL,
  `IdDepartamentoArea` int(11) DEFAULT NULL,
  `IdProyecto` int(11) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdCentroCosto`),
  KEY `fkDepartamentoArea_CentroCosto` (`IdDepartamentoArea`),
  KEY `fkUnidadDeNegocio_CentroCosto` (`IdUnidadDeNegocio`),
  KEY `fkEmpresa_CentroCosto` (`IdEmpresa`),
  KEY `fkProyecto_CentroCosto` (`IdProyecto`),
  CONSTRAINT `fkDepartamentoArea_CentroCosto` FOREIGN KEY (`IdDepartamentoArea`) REFERENCES `departamentoarea` (`IdDepartamentoArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpresa_CentroCosto` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkProyecto_CentroCosto` FOREIGN KEY (`IdProyecto`) REFERENCES `proyecto` (`IdProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUnidadDeNegocio_CentroCosto` FOREIGN KEY (`IdUnidadDeNegocio`) REFERENCES `unidaddenegocio` (`IdUnidadDeNegocio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `centrocosto` WRITE;
/*!40000 ALTER TABLE `centrocosto` DISABLE KEYS */;

INSERT INTO `centrocosto` (`IdCentroCosto`, `Nombre`, `Descripcion`, `Estado`, `IdEmpresa`, `IdUnidadDeNegocio`, `IdDepartamentoArea`, `IdProyecto`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(23,'ADMINISTRACION','ADMINISTRACION','A',1,1,6,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(24,'ADMINISTRACION DE BASES DE DATOS','ADMINISTRACION DE BASES DE DATOS','A',1,1,9,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(25,'CELTIC','CELTIC','A',1,1,3,2,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(26,'CFR','CFR','A',1,1,3,4,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(27,'CLARK STEEL','CLARK STEEL','A',1,1,3,6,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(28,'CONTOPSA','CONTOPSA','A',1,1,3,7,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(29,'DOLE','DOLE','A',1,1,3,3,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(30,'GERENCIA COMERCIAL','GERENCIA COMERCIAL','A',1,1,5,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(31,'GERENCIA GENERAL','GERENCIA GENERAL','A',1,1,8,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(32,'PROCESOS','PROCESOS','A',1,1,2,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(33,'PROYECTOS INTERNOS','PROYECTOS INTERNOS','A',1,1,7,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(34,'QUALITY ASSURANCE','QUALITY ASSURANCE','A',1,1,4,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(35,'RECURSOS HUMANOS','RECURSOS HUMANOS','A',1,1,1,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(36,'SITRAN','SITRAN','A',1,1,3,8,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(37,'SMOOTH COM','SMOOTH COM','A',1,1,3,9,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(38,'TRANS MOBILE','TRANS MOBILE','A',1,1,3,10,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(39,'ULOG','ULOG','A',1,1,3,11,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(40,'ULTRATUG','ULTRATUG','A',1,1,3,12,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(41,'UMAR','UMAR','A',1,1,3,13,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(42,'XPO','XPO','A',1,1,3,5,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(43,'XPO INTERNATIONAL','XPO INTERNATIONAL','A',1,1,3,14,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(44,'XPO MOBILE','XPO MOBILE','A',1,1,3,15,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(45,'TECHNOLOGY','TECHNLOGY','A',1,1,3,NULL,'TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `centrocosto` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table compensacionhorasextra
# ------------------------------------------------------------

DROP TABLE IF EXISTS `compensacionhorasextra`;

CREATE TABLE `compensacionhorasextra` (
  `IdCompensacionHoras` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `MesCompensado` varchar(2) NOT NULL,
  `FechaInicio` datetime(6) DEFAULT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `HoraInicio` varchar(5) DEFAULT NULL,
  `HoraFin` varchar(5) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdCompensacionHoras`),
  KEY `fkEmpleado_CompensacionHorasExtra` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_CompensacionHorasExtra` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table configuracionsistema
# ------------------------------------------------------------

DROP TABLE IF EXISTS `configuracionsistema`;

CREATE TABLE `configuracionsistema` (
  `IdConfiguracionSistema` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpresa` int(11) NOT NULL,
  `Codigo` varchar(100) NOT NULL,
  `Descripcion` varchar(250) NOT NULL,
  `Valor` varchar(100) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdConfiguracionSistema`),
  KEY `fkEmpresa_ConfiguracionSistema` (`IdEmpresa`),
  CONSTRAINT `fkEmpresa_ConfiguracionSistema` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `configuracionsistema` WRITE;
/*!40000 ALTER TABLE `configuracionsistema` DISABLE KEYS */;

INSERT INTO `configuracionsistema` (`IdConfiguracionSistema`, `IdEmpresa`, `Codigo`, `Descripcion`, `Valor`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,1,'GestionDeTiempo.Tardanza','Minima cantidad de minutos para considerar una asistencia como tardanza.','10','TSS','2017-02-07 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 16:15:08.860000',6),
	(2,1,'GestionDeTiempo.PermisosPermitidos','Maxima cantidad de permisos permitidos por periodo de un empleado por defecto.','10','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,1,'GestionDeTiempo.Vacaciones','Dias hábiles de vacaciones por periodo de un empleado.','22','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,1,'GestionDeTiempo.HorasSemanales','Maximo horas de trabajo a la semana por defecto (en horas).','48','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,1,'GestionDeTiempo.DuracionAlmuerzo','Maximo tiempo para almorzar por defecto (en minutos).','60','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,1,'Dashboard.Permisos','Cantidad de meses que se tomara como referencia para mostrar los permisos.','3','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,1,'Dashboard.Vacaciones','Cantidad de meses que se tomara como referencia para mostrar las vacaciones.','12','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,1,'Dashboard.HorasExtras','Cantidad de meses que se tomara como referencia para mostrar las horas extras.','3','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,1,'Dashboard.Alertas','Cantidad de alertas que se mostrara en el sistema por defecto.','20','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,1,'GestionDeTiempo.MaxDiasDeLicencia','Cantidad de alertas que se mostrara en el sistema por defecto.','30','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,1,'GestionDeTiempo.PeriodoEmpleado','Cantidad de dias para crear un nuevo periodo del empleado','30','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,1,'Login.ResetPassword','Máxima cantidad de dias de vigencia del link para generar contraseña','5','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,1,'General.EmailSoporte','Direccion del correo electronico o direcciones de correos electronicos que recibiran las notificaciones en caso ocurra algun problema en el sistema.','jamdiazdiaz@gmail.com','TSS','2017-03-27 14:56:38.363000',NULL,NULL,1),
	(14,1,'GestionDeTiempo.DispositivosMarcaciones','Codigos de los dispositivos usados para registrar las marcaciones en la empresa.','103,104,105','TSS','2017-03-27 14:56:58.530000',NULL,NULL,1);

/*!40000 ALTER TABLE `configuracionsistema` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table contrato
# ------------------------------------------------------------

DROP TABLE IF EXISTS `contrato`;

CREATE TABLE `contrato` (
  `IdContrato` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) DEFAULT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `Duracion` varchar(100) NOT NULL,
  `Cargo` varchar(100) NOT NULL,
  `IdMoneda` int(11) NOT NULL,
  `Salario` decimal(10,2) NOT NULL,
  `Direccion` varchar(250) NOT NULL,
  `TipoDocumento` varchar(50) NOT NULL,
  `NumeroDocumento` varchar(50) NOT NULL,
  `Estado` varchar(2) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `TipoContrato` varchar(2) NOT NULL DEFAULT 'DE',
  PRIMARY KEY (`IdContrato`),
  KEY `fkEmpleado_Contrato` (`IdEmpleado`),
  KEY `fkMoneda_Contrato` (`IdMoneda`),
  CONSTRAINT `fkEmpleado_Contrato` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkMoneda_Contrato` FOREIGN KEY (`IdMoneda`) REFERENCES `moneda` (`IdMoneda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table departamento
# ------------------------------------------------------------

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `IdDepartamento` int(11) NOT NULL AUTO_INCREMENT,
  `IdPais` int(11) NOT NULL,
  `Codigo` varchar(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdDepartamento`),
  KEY `fkPais_Departamento` (`IdPais`),
  CONSTRAINT `fkPais_Departamento` FOREIGN KEY (`IdPais`) REFERENCES `pais` (`IdPais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;

INSERT INTO `departamento` (`IdDepartamento`, `IdPais`, `Codigo`, `Nombre`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,1,'AM','Amazonas','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,1,'AN','Áncash','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,1,'AP','Apurímac','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,1,'AR','Arequipa','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,1,'AY','Ayacucho','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,1,'CJ','Cajamarca','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,1,'CU','Cusco','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,1,'HU','Huancavelica','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,1,'HC','Huánuco','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,1,'IC','Ica','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,1,'JU','Junín','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,1,'LL','La Libertad','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,1,'LA','Lambayeque','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(14,1,'LI','Lima','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(15,1,'LO','Loreto','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(16,1,'MA','Madre de Dios','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(17,1,'MO','Moquegua','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(18,1,'PA','Pasco','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(19,1,'PI','Piura','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(20,1,'PU','Puno','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(21,1,'SM','San Martín','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(22,1,'TA','Tacna','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(23,1,'TU','Tumbes','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(24,1,'UC','Ucayali','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table departamentoarea
# ------------------------------------------------------------

DROP TABLE IF EXISTS `departamentoarea`;

CREATE TABLE `departamentoarea` (
  `IdDepartamentoArea` int(11) NOT NULL AUTO_INCREMENT,
  `IdUnidadDeNegocio` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `IdJefe` int(11) DEFAULT NULL,
  `JefeNoDisponible` tinyint(1) DEFAULT NULL,
  `IdJefeReemplazo` int(11) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdDepartamentoArea`),
  KEY `fkUnidadDeNegocio_DepartamentoArea` (`IdUnidadDeNegocio`),
  KEY `fkEmpleado_DepartamentoArea` (`IdJefe`),
  KEY `fkJefeReem_DepartamentoArea` (`IdJefeReemplazo`),
  CONSTRAINT `fkEmpleado_DepartamentoArea` FOREIGN KEY (`IdJefe`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkJefeReem_DepartamentoArea` FOREIGN KEY (`IdJefeReemplazo`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUnidadDeNegocio_DepartamentoArea` FOREIGN KEY (`IdUnidadDeNegocio`) REFERENCES `unidaddenegocio` (`IdUnidadDeNegocio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `departamentoarea` WRITE;
/*!40000 ALTER TABLE `departamentoarea` DISABLE KEYS */;

INSERT INTO `departamentoarea` (`IdDepartamentoArea`, `IdUnidadDeNegocio`, `Nombre`, `IdJefe`, `JefeNoDisponible`, `IdJefeReemplazo`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,1,'RECURSOS HUMANOS',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,1,'PROCESOS',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,1,'PROYECTOS',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,1,'QUALITY ASSURANCE',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,1,'GERENCIA COMERCIAL',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,1,'ADMINISTRACION',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,1,'PROYECTOS INTERNOS',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,1,'GERENCIA GENERAL',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,1,'ADMINISTRACION DE BASES DE DATOS',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,1,'TECHNOLOGY',NULL,0,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,1,'SOPORTE TECNICO',NULL,0,NULL,'A','TSS','2017-03-22 12:58:51.050000',NULL,NULL,0);

/*!40000 ALTER TABLE `departamentoarea` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table dependiente
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dependiente`;

CREATE TABLE `dependiente` (
  `IdDependiente` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `ApellidoPaterno` varchar(50) NOT NULL,
  `ApellidoMaterno` varchar(50) NOT NULL,
  `Relacion` varchar(2) NOT NULL,
  `TipoDocumento` varchar(2) DEFAULT NULL,
  `NumeroDocumento` varchar(50) DEFAULT NULL,
  `FechaNacimiento` datetime(6) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdDependiente`),
  KEY `fkEmpleado_Dependiente` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_Dependiente` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table distrito
# ------------------------------------------------------------

DROP TABLE IF EXISTS `distrito`;

CREATE TABLE `distrito` (
  `IdDitrito` int(11) NOT NULL AUTO_INCREMENT,
  `IdProvincia` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Zip` varchar(50) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdDitrito`),
  KEY `fkProvincia_Distrito` (`IdProvincia`),
  CONSTRAINT `fkProvincia_Distrito` FOREIGN KEY (`IdProvincia`) REFERENCES `provincia` (`IdProvincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table documentoempleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `documentoempleado`;

CREATE TABLE `documentoempleado` (
  `IdDocumentoEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `TipoDocumento` varchar(2) NOT NULL,
  `IdLicencia` int(11) DEFAULT NULL,
  `Nombre` varchar(100) NOT NULL,
  `NombreArchivo` varchar(100) NOT NULL,
  `TipoArchivo` varchar(100) NOT NULL,
  `ContenidoArchivo` longblob NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdDocumentoEmpleado`),
  KEY `fkEmpleado_DocumentoEmpleado` (`IdEmpleado`),
  KEY `fkLicencia_DocumentoEmpleado` (`IdLicencia`),
  CONSTRAINT `fkEmpleado_DocumentoEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkLicencia_DocumentoEmpleado` FOREIGN KEY (`IdLicencia`) REFERENCES `licencia` (`IdLicencia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `documentoempleado` WRITE;
/*!40000 ALTER TABLE `documentoempleado` DISABLE KEYS */;

INSERT INTO `documentoempleado` (`IdDocumentoEmpleado`, `IdEmpleado`, `TipoDocumento`, `IdLicencia`, `Nombre`, `NombreArchivo`, `TipoArchivo`, `ContenidoArchivo`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,41,'FO',NULL,'Foto Empleado','img006.jpg','image/jpeg',X'FFD8FFE000104A46494600010100012C012C0000FFE100804578696600004D4D002A000000080005011200030000000100010000011A0005000000010000004A011B0005000000010000005201280003000000010002000087690004000000010000005A000000000000012C000000010000012C000000010002A002000400000001000001C2A0030004000000010000023400000000FFED003850686F746F73686F7020332E30003842494D04040000000000003842494D0425000000000010D41D8CD98F00B204E9800998ECF8427EFFE222144943435F50524F46494C45000101000022044150504C022000006D6E74725247422058595A2007D600020002000200140000616373704150504C000000006E6F6E65000000000000000000000000000000000000F6D6000100000000D32D4550534F00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000A646573630000216C000000477258595A000000FC000000146758595A00000110000000146258595A000001240000001477747074000001380000001463707274000021B400000050725452430000014C0000200C675452430000014C0000200C625452430000014C0000200C626B7074000021580000001458595A200000000000006FA2000038F50000039058595A2000000000000062990000B785000018DA58595A2000000000000024A000000F840000B6CF58595A20000000000000F35100010000000116CC63757276000000000000100000000001000200040005000600070009000A000B000C000E000F00100011001300140015001600180019001A001B001C001E001F00200021002300240025002600280029002A002B002D002E002F00300032003300340035003700380039003A003B003D003E003F00400042004300440045004700480049004A004C004D004E004F00510052005300540055005700580059005A005C005D005E005F00610062006300640066006700680069006B006C006D006E006F00710072007300740076007700780079007B007C007D007E008000810082008300850086008700880089008B008C008D008E00900091009200930095009600970098009A009B009C009D009F00A000A100A200A400A500A600A700A800AA00AB00AC00AD00AF00B000B100B200B400B500B600B700B900BA00BB00BC00BE00BF00C000C100C200C400C500C600C700C900CA00CB00CC00CE00CF00D000D100D300D400D500D700D800D900DA00DC00DD00DE00E000E100E200E400E500E600E800E900EA00EC00ED00EF00F000F100F300F400F600F700F800FA00FB00FD00FE00FF010101020104010501070108010A010B010D010E010F011101120114011501170118011A011B011D011F0120012201230125012601280129012B012D012E0130013101330134013601380139013B013C013E014001410143014501460148014A014B014D014F015001520154015501570159015A015C015E016001610163016501670168016A016C016E016F01710173017501760178017A017C017E017F01810183018501870189018A018C018E019001920194019601970199019B019D019F01A101A301A501A701A901AB01AC01AE01B001B201B401B601B801BA01BC01BE01C001C201C401C601C801CA01CC01CE01D001D201D401D601D801DA01DC01DE01E101E301E501E701E901EB01ED01EF01F101F301F501F801FA01FC01FE02000202020402070209020B020D020F0212021402160218021A021D021F0221022302250228022A022C022E0231023302350238023A023C023E0241024302450248024A024C024F0251025302560258025A025D025F0261026402660269026B026D02700272027502770279027C027E0281028302860288028B028D0290029202950297029A029C029F02A102A402A602A902AB02AE02B002B302B502B802BB02BD02C002C202C502C802CA02CD02CF02D202D502D702DA02DD02DF02E202E402E702EA02EC02EF02F202F502F702FA02FD02FF030203050308030A030D0310031303150318031B031E0320032303260329032C032E033103340337033A033D033F034203450348034B034E0351035403560359035C035F036203650368036B036E037103740377037A037D0380038203850388038B038E039103940398039B039E03A103A403A703AA03AD03B003B303B603B903BC03BF03C203C503C903CC03CF03D203D503D803DB03DF03E203E503E803EB03EE03F203F503F803FB03FE040204050408040B040F041204150418041C041F042204250429042C042F043304360439043D044004430447044A044D045104540457045B045E046204650468046C046F047304760479047D048004840487048B048E049204950499049C04A004A304A704AA04AE04B104B504B804BC04BF04C304C604CA04CE04D104D504D804DC04E004E304E704EA04EE04F204F504F904FD050005040508050B050F05130516051A051E052205250529052D053105340538053C054005430547054B054F05520556055A055E056205660569056D057105750579057D058105840588058C059005940598059C05A005A405A805AC05AF05B305B705BB05BF05C305C705CB05CF05D305D705DB05DF05E305E705EB05EF05F405F805FC060006040608060C061006140618061C062106250629062D063106350639063E06420646064A064E06530657065B065F06630668066C067006740679067D06810685068A068E06920697069B069F06A406A806AC06B106B506B906BE06C206C606CB06CF06D406D806DC06E106E506EA06EE06F206F706FB070007040709070D07120716071B071F07240728072D07310736073A073F07430748074D07510756075A075F07630768076D07710776077B077F07840789078D07920797079B07A007A507A907AE07B307B707BC07C107C607CA07CF07D407D907DD07E207E707EC07F107F507FA07FF08040809080D08120817081C08210826082B082F08340839083E08430848084D08520857085C08610866086B08700875087A087F08840889088E08930898089D08A208A708AC08B108B608BB08C008C508CA08CF08D408D908DF08E408E908EE08F308F808FD09030908090D09120917091D09220927092C09310937093C09410946094C09510956095B09610966096B09710976097B09810986098B09910996099B09A109A609AB09B109B609BC09C109C609CC09D109D709DC09E209E709ED09F209F809FD0A020A080A0D0A130A190A1E0A240A290A2F0A340A3A0A3F0A450A4A0A500A560A5B0A610A660A6C0A720A770A7D0A830A880A8E0A940A990A9F0AA50AAA0AB00AB60ABC0AC10AC70ACD0AD30AD80ADE0AE40AEA0AEF0AF50AFB0B010B070B0C0B120B180B1E0B240B2A0B2F0B350B3B0B410B470B4D0B530B590B5F0B640B6A0B700B760B7C0B820B880B8E0B940B9A0BA00BA60BAC0BB20BB80BBE0BC40BCA0BD00BD60BDC0BE20BE90BEF0BF50BFB0C010C070C0D0C130C190C200C260C2C0C320C380C3E0C450C4B0C510C570C5D0C640C6A0C700C760C7D0C830C890C8F0C960C9C0CA20CA80CAF0CB50CBB0CC20CC80CCE0CD50CDB0CE10CE80CEE0CF50CFB0D010D080D0E0D150D1B0D210D280D2E0D350D3B0D420D480D4F0D550D5C0D620D690D6F0D760D7C0D830D890D900D960D9D0DA40DAA0DB10DB70DBE0DC50DCB0DD20DD90DDF0DE60DEC0DF30DFA0E010E070E0E0E150E1B0E220E290E2F0E360E3D0E440E4A0E510E580E5F0E660E6C0E730E7A0E810E880E8E0E950E9C0EA30EAA0EB10EB80EBE0EC50ECC0ED30EDA0EE10EE80EEF0EF60EFD0F040F0B0F120F190F200F270F2E0F350F3C0F430F4A0F510F580F5F0F660F6D0F740F7B0F820F890F900F980F9F0FA60FAD0FB40FBB0FC20FCA0FD10FD80FDF0FE60FED0FF50FFC1003100A1012101910201027102F1036103D1044104C1053105A1062106910701078107F1086108E1095109D10A410AB10B310BA10C210C910D010D810DF10E710EE10F610FD1105110C1114111B1123112A113211391141114811501157115F1167116E1176117D1185118D1194119C11A411AB11B311BB11C211CA11D211D911E111E911F011F812001208120F1217121F1227122E1236123E1246124E1255125D1265126D1275127D1284128C1294129C12A412AC12B412BC12C412CC12D412DB12E312EB12F312FB1303130B1313131B1323132B1333133B1344134C1354135C1364136C1374137C1384138C1394139D13A513AD13B513BD13C513CD13D613DE13E613EE13F613FF1407140F14171420142814301438144114491451145A1462146A1473147B1483148C1494149C14A514AD14B614BE14C614CF14D714E014E814F114F91501150A1512151B1523152C1534153D1545154E1557155F1568157015791581158A1593159B15A415AC15B515BE15C615CF15D815E015E915F215FA1603160C1614161D1626162F1637164016491652165A1663166C1675167E1686168F169816A116AA16B316BB16C416CD16D616DF16E816F116FA1703170C1714171D1726172F17381741174A1753175C1765176E1777178017891792179C17A517AE17B717C017C917D217DB17E417ED17F7180018091812181B1824182E1837184018491852185C1865186E18771881188A1893189C18A618AF18B818C218CB18D418DE18E718F018FA1903190C1916191F19291932193B1945194E19581961196B1974197E19871991199A19A419AD19B719C019CA19D319DD19E619F019FA1A031A0D1A161A201A2A1A331A3D1A461A501A5A1A631A6D1A771A811A8A1A941A9E1AA71AB11ABB1AC51ACE1AD81AE21AEC1AF51AFF1B091B131B1D1B271B301B3A1B441B4E1B581B621B6C1B751B7F1B891B931B9D1BA71BB11BBB1BC51BCF1BD91BE31BED1BF71C011C0B1C151C1F1C291C331C3D1C471C511C5B1C651C701C7A1C841C8E1C981CA21CAC1CB61CC11CCB1CD51CDF1CE91CF41CFE1D081D121D1C1D271D311D3B1D451D501D5A1D641D6F1D791D831D8E1D981DA21DAD1DB71DC11DCC1DD61DE11DEB1DF51E001E0A1E151E1F1E2A1E341E3E1E491E531E5E1E681E731E7D1E881E931E9D1EA81EB21EBD1EC71ED21EDC1EE71EF21EFC1F071F121F1C1F271F321F3C1F471F521F5C1F671F721F7C1F871F921F9D1FA71FB21FBD1FC81FD21FDD1FE81FF31FFE20082013201E20292034203F204A2054205F206A20752080208B209620A120AC20B720C220CD20D820E320EE20F92104210F211A21252130213B21462151215C21672172217E21892194219F21AA21B521C021CC21D721E221ED21F82204220F221A22252230223C22472252225E22692274227F228B229622A122AD22B822C322CF22DA22E622F122FC23082313231F232A23352341234C23582363236F237A23862391239D23A823B423BF23CB23D623E223EE23F924052410241C24282433243F244B24562462246E247924852491249C24A824B424BF24CB24D724E324EE24FA25062512251E252925352541254D255925652570257C2588259425A025AC25B825C425D025DC25E725F325FF260B26172623262F263B26472653265F266B267726842690269C26A826B426C026CC26D826E426F026FD270927152721272D273927462752275E276A27762783278F279B27A727B427C027CC27D927E527F127FD280A28162823282F283B284828542860286D287928862892289E28AB28B728C428D028DD28E928F62902290F291B292829342941294D295A296729732980298C299929A629B229BF29CC29D829E529F129FE2A0B2A182A242A312A3E2A4A2A572A642A712A7D2A8A2A972AA42AB12ABD2ACA2AD72AE42AF12AFE2B0A2B172B242B312B3E2B4B2B582B652B722B7F2B8C2B992BA52BB22BBF2BCC2BD92BE62BF32C012C0E2C1B2C282C352C422C4F2C5C2C692C762C832C902C9E2CAB2CB82CC52CD22CDF2CED2CFA2D072D142D212D2F2D3C2D492D562D642D712D7E2D8B2D992DA62DB32DC12DCE2DDB2DE92DF62E042E112E1E2E2C2E392E472E542E612E6F2E7C2E8A2E972EA52EB22EC02ECD2EDB2EE82EF62F032F112F1E2F2C2F3A2F472F552F622F702F7E2F8B2F992FA72FB42FC22FD02FDD2FEB2FF9300630143022302F303D304B30593067307430823090309E30AC30B930C730D530E330F130FF310D311A31283136314431523160316E317C318A319831A631B431C231D031DE31EC31FA32083216322432323240324E325C326A32793287329532A332B132BF32CD32DC32EA32F83306331433233331333F334D335C336A33783386339533A333B133C033CE33DC33EB33F934073416342434333441344F345E346C347B3489349834A634B534C334D234E034EF34FD350C351A3529353735463554356335723580358F359D35AC35BB35C935D835E735F53604361336213630363F364E365C366B367A3689369736A636B536C436D336E136F036FF370E371D372C373B37493758376737763785379437A337B237C137D037DF37EE37FD380C381B382A383938483857386638753884389338A238B138C138D038DF38EE38FD390C391B392B393A39493958396739773986399539A439B439C339D239E139F13A003A0F3A1F3A2E3A3D3A4D3A5C3A6B3A7B3A8A3A9A3AA93AB83AC83AD73AE73AF63B063B153B253B343B443B533B633B723B823B913BA13BB03BC03BD03BDF3BEF3BFE3C0E3C1E3C2D3C3D3C4D3C5C3C6C3C7C3C8B3C9B3CAB3CBA3CCA3CDA3CEA3CF93D093D193D293D393D483D583D683D783D883D983DA73DB73DC73DD73DE73DF73E073E173E273E373E473E573E673E773E873E973EA73EB73EC73ED73EE73EF73F073F173F273F373F473F573F673F783F883F983FA83FB83FC83FD93FE93FF940094019402A403A404A405A406B407B408B409C40AC40BC40CD40DD40ED40FE410E411E412F413F414F416041704181419141A241B241C341D341E441F4420542154226423642474257426842784289429A42AA42BB42CB42DC42ED42FD430E431F432F43404351436143724383439443A443B543C643D743E743F84409441A442B443B444C445D446E447F449044A144B244C244D344E444F54506451745284539454A455B456C457D458E459F45B045C145D245E345F44605461746284639464A465B466C467D468F46A046B146C246D346E446F6470747184729473B474C475D476E4780479147A247B447C547D647E847F9480A481C482D483F4850486148734884489648A748B948CA48DC48ED48FF491049224933494549564968497A498B499D49AE49C049D249E349F54A064A184A2A4A3B4A4D4A5F4A714A824A944AA64AB74AC94ADB4AED4AFF4B104B224B344B464B584B694B7B4B8D4B9F4BB14BC34BD54BE74BF94C0A4C1C4C2E4C404C524C644C764C884C9A4CAC4CBE4CD04CE24CF44D064D194D2B4D3D4D4F4D614D734D854D974DA94DBC4DCE4DE04DF24E044E174E294E3B4E4D4E5F4E724E844E964EA94EBB4ECD4EDF4EF24F044F164F294F3B4F4E4F604F724F854F974FAA4FBC4FCE4FE14FF350065018502B503D5050506250755087509A50AD50BF50D250E450F75109511C512F5141515451675179518C519F51B151C451D751E951FC520F522252345247525A526D5280529252A552B852CB52DE52F1530453165329533C534F536253755388539B53AE53C153D453E753FA540D5420543354465459546C547F549254A554B854CB54DE54F255055518552B553E555155655578558B559E55B155C555D855EB55FE561256255638564B565F56725685569956AC56BF56D356E656FA570D572057345747575B576E5782579557A957BC57D057E357F7580A581E583158455858586C5880589358A758BA58CE58E258F55909591D593059445958596B597F599359A759BA59CE59E259F65A095A1D5A315A455A595A6C5A805A945AA85ABC5AD05AE45AF85B0B5B1F5B335B475B5B5B6F5B835B975BAB5BBF5BD35BE75BFB5C0F5C235C375C4B5C605C745C885C9C5CB05CC45CD85CEC5D015D155D295D3D5D515D655D7A5D8E5DA25DB65DCB5DDF5DF35E085E1C5E305E445E595E6D5E825E965EAA5EBF5ED35EE75EFC5F105F255F395F4E5F625F775F8B5FA05FB45FC95FDD5FF26006601B602F60446058606D6082609660AB60BF60D460E960FD61126127613B61506165617A618E61A361B861CD61E161F6620B622062356249625E62736288629D62B262C762DB62F06305631A632F63446359636E6383639863AD63C263D763EC64016416642B64406455646A647F649564AA64BF64D464E964FE65136529653E65536568657D659365A865BD65D265E865FD66126627663D66526667667D669266A766BD66D266E866FD67126728673D67536768677E679367A967BE67D467E967FF6814682A683F6855686A6880689668AB68C168D668EC69026917692D69436958696E6984699969AF69C569DB69F06A066A1C6A326A486A5D6A736A896A9F6AB56ACA6AE06AF66B0C6B226B386B4E6B646B7A6B906BA66BBC6BD26BE86BFE6C146C2A6C406C566C6C6C826C986CAE6CC46CDA6CF06D066D1C6D336D496D5F6D756D8B6DA16DB86DCE6DE46DFA6E116E276E3D6E536E6A6E806E966EAD6EC36ED96EF06F066F1C6F336F496F606F766F8C6FA36FB96FD06FE66FFD7013702A70407057706D7084709A70B170C770DE70F4710B71227138714F7166717C719371AA71C071D771EE7204721B72327248725F7276728D72A472BA72D172E872FF7316732C7343735A73717388739F73B673CD73E473FA74117428743F7456746D7484749B74B274C974E074F7750E7526753D7554756B7582759975B075C775DE75F6760D7624763B7652766A7681769876AF76C776DE76F5770C7724773B7752776A7781779877B077C777DE77F6780D7825783C7854786B7882789A78B178C978E078F8790F7927793E7956796E7985799D79B479CC79E379FB7A137A2A7A427A5A7A717A897AA17AB87AD07AE87B007B177B2F7B477B5F7B767B8E7BA67BBE7BD67BEE7C057C1D7C357C4D7C657C7D7C957CAD7CC57CDC7CF47D0C7D247D3C7D547D6C7D847D9C7DB47DCD7DE57DFD7E157E2D7E457E5D7E757E8D7EA57EBE7ED67EEE7F067F1E7F377F4F7F677F7F7F977FB07FC87FE07FF9801180298041805A8072808A80A380BB80D480EC8104811D8135814E8166817F819781B081C881E181F98212822A8243825B8274828C82A582BE82D682EF8307832083398351836A8383839B83B483CD83E583FE8417843084488461847A849384AC84C484DD84F6850F85288541855A8572858B85A485BD85D685EF86088621863A8653866C8685869E86B786D086E98702871B8734874D87678780879987B287CB87E487FD8817883088498862887B889588AE88C788E088FA8913892C8946895F8978899189AB89C489DE89F78A108A2A8A438A5D8A768A8F8AA98AC28ADC8AF58B0F8B288B428B5B8B758B8E8BA88BC28BDB8BF58C0E8C288C428C5B8C758C8F8CA88CC28CDC8CF58D0F8D298D428D5C8D768D908DA98DC38DDD8DF78E118E2B8E448E5E8E788E928EAC8EC68EE08EFA8F138F2D8F478F618F7B8F958FAF8FC98FE38FFD90179031904B9065907F909A90B490CE90E89102911C91369150916B9185919F91B991D391EE92089222923C92579271928B92A692C092DA92F4930F93299344935E9378939393AD93C893E293FC94179431944C94669481949B94B694D094EB95059520953B95559570958A95A595C095DA95F5960F962A9645965F967A969596B096CA96E59700971B97359750976B978697A197BB97D697F1980C98279842985D9877989298AD98C898E398FE99199934994F996A998599A099BB99D699F19A0C9A279A429A5E9A799A949AAF9ACA9AE59B009B1C9B379B529B6D9B889BA49BBF9BDA9BF59C119C2C9C479C639C7E9C999CB59CD09CEB9D079D229D3D9D599D749D909DAB9DC69DE29DFD9E199E349E509E6B9E879EA29EBE9EDA9EF59F119F2C9F489F639F7F9F9B9FB69FD29FEEA009A025A041A05CA078A094A0B0A0CBA0E7A103A11FA13AA156A172A18EA1AAA1C6A1E1A1FDA219A235A251A26DA289A2A5A2C1A2DDA2F9A315A331A34DA369A385A3A1A3BDA3D9A3F5A411A42DA449A465A481A49EA4BAA4D6A4F2A50EA52AA547A563A57FA59BA5B8A5D4A5F0A60CA629A645A661A67EA69AA6B6A6D3A6EFA70BA728A744A760A77DA799A7B6A7D2A7EFA80BA828A844A861A87DA89AA8B6A8D3A8EFA90CA929A945A962A97EA99BA9B8A9D4A9F1AA0EAA2AAA47AA64AA80AA9DAABAAAD7AAF3AB10AB2DAB4AAB67AB83ABA0ABBDABDAABF7AC14AC30AC4DAC6AAC87ACA4ACC1ACDEACFBAD18AD35AD52AD6FAD8CADA9ADC6ADE3AE00AE1DAE3AAE57AE74AE92AEAFAECCAEE9AF06AF23AF40AF5EAF7BAF98AFB5AFD3AFF0B00DB02AB048B065B082B09FB0BDB0DAB0F7B115B132B150B16DB18AB1A8B1C5B1E3B200B21EB23BB259B276B294B2B1B2CFB2ECB30AB327B345B362B380B39EB3BBB3D9B3F6B414B432B44FB46DB48BB4A8B4C6B4E4B502B51FB53DB55BB579B596B5B4B5D2B5F0B60EB62CB649B667B685B6A3B6C1B6DFB6FDB71BB739B757B775B793B7B1B7CFB7EDB80BB829B847B865B883B8A1B8BFB8DDB8FBB919B938B956B974B992B9B0B9CEB9EDBA0BBA29BA47BA66BA84BAA2BAC0BADFBAFDBB1BBB3ABB58BB76BB95BBB3BBD1BBF0BC0EBC2DBC4BBC6ABC88BCA6BCC5BCE3BD02BD20BD3FBD5DBD7CBD9BBDB9BDD8BDF6BE15BE33BE52BE71BE8FBEAEBECDBEEBBF0ABF29BF47BF66BF85BFA4BFC2BFE1C000C01FC03EC05CC07BC09AC0B9C0D8C0F7C115C134C153C172C191C1B0C1CFC1EEC20DC22CC24BC26AC289C2A8C2C7C2E6C305C324C343C362C381C3A0C3C0C3DFC3FEC41DC43CC45BC47BC49AC4B9C4D8C4F7C517C536C555C575C594C5B3C5D2C5F2C611C630C650C66FC68FC6AEC6CDC6EDC70CC72CC74BC76BC78AC7AAC7C9C7E9C808C828C847C867C886C8A6C8C5C8E5C905C924C944C964C983C9A3C9C3C9E2CA02CA22CA41CA61CA81CAA1CAC0CAE0CB00CB20CB40CB5FCB7FCB9FCBBFCBDFCBFFCC1FCC3FCC5ECC7ECC9ECCBECCDECCFECD1ECD3ECD5ECD7ECD9ECDBECDDECDFECE1FCE3FCE5FCE7FCE9FCEBFCEDFCEFFCF20CF40CF60CF80CFA0CFC1CFE1D001D021D042D062D082D0A2D0C3D0E3D103D124D144D165D185D1A5D1C6D1E6D207D227D247D268D288D2A9D2C9D2EAD30AD32BD34CD36CD38DD3ADD3CED3EED40FD430D450D471D492D4B2D4D3D4F4D514D535D556D577D597D5B8D5D9D5FAD61AD63BD65CD67DD69ED6BFD6DFD700D721D742D763D784D7A5D7C6D7E7D808D829D84AD86BD88CD8ADD8CED8EFD910D931D952D973D994D9B5D9D6D9F8DA19DA3ADA5BDA7CDA9EDABFDAE0DB01DB22DB44DB65DB86DBA8DBC9DBEADC0BDC2DDC4EDC6FDC91DCB2DCD4DCF5DD16DD38DD59DD7BDD9CDDBEDDDFDE01DE22DE44DE65DE87DEA8DECADEECDF0DDF2FDF50DF72DF94DFB5DFD7DFF9E01AE03CE05EE07FE0A1E0C3E0E5E106E128E14AE16CE18DE1AFE1D1E1F3E215E237E259E27AE29CE2BEE2E0E302E324E346E368E38AE3ACE3CEE3F0E412E434E456E478E49AE4BCE4DEE501E523E545E567E589E5ABE5CDE5F0E612E634E656E679E69BE6BDE6DFE702E724E746E769E78BE7ADE7D0E7F2E814E837E859E87BE89EE8C0E8E3E905E928E94AE96DE98FE9B2E9D4E9F7EA19EA3CEA5EEA81EAA4EAC6EAE9EB0BEB2EEB51EB73EB96EBB9EBDCEBFEEC21EC44EC66EC89ECACECCFECF2ED14ED37ED5AED7DEDA0EDC3EDE5EE08EE2BEE4EEE71EE94EEB7EEDAEEFDEF20EF43EF66EF89EFACEFCFEFF2F015F038F05BF07EF0A1F0C5F0E8F10BF12EF151F174F198F1BBF1DEF201F224F248F26BF28EF2B1F2D5F2F8F31BF33FF362F385F3A9F3CCF3F0F413F436F45AF47DF4A1F4C4F4E8F50BF52FF552F576F599F5BDF5E0F604F627F64BF66FF692F6B6F6D9F6FDF721F744F768F78CF7B0F7D3F7F7F81BF83EF862F886F8AAF8CEF8F1F915F939F95DF981F9A5F9C9F9ECFA10FA34FA58FA7CFAA0FAC4FAE8FB0CFB30FB54FB78FB9CFBC0FBE4FC08FC2CFC50FC75FC99FCBDFCE1FD05FD29FD4DFD72FD96FDBAFDDEFE02FE27FE4BFE6FFE94FEB8FEDCFF00FF25FF49FF6DFF92FFB6FFDBFFFF58595A200000000000000000000000000000000064657363000000000000000C4550534F4E20207352474200000000000000000C004500500053004F004E002000200073005200470042000000000C4550534F4E20207352474200007465787400000000436F7079726967687420286329205345494B4F204550534F4E20434F52504F524154494F4E2032303030202D20323030362E20416C6C207269676874732072657365727665642E00FFC0001108023401C203011200021101031101FFC4001F0000010501010101010100000000000000000102030405060708090A0BFFC400B5100002010303020403050504040000017D01020300041105122131410613516107227114328191A1082342B1C11552D1F02433627282090A161718191A25262728292A3435363738393A434445464748494A535455565758595A636465666768696A737475767778797A838485868788898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE1E2E3E4E5E6E7E8E9EAF1F2F3F4F5F6F7F8F9FAFFC4001F0100030101010101010101010000000000000102030405060708090A0BFFC400B51100020102040403040705040400010277000102031104052131061241510761711322328108144291A1B1C109233352F0156272D10A162434E125F11718191A262728292A35363738393A434445464748494A535455565758595A636465666768696A737475767778797A82838485868788898A92939495969798999AA2A3A4A5A6A7A8A9AAB2B3B4B5B6B7B8B9BAC2C3C4C5C6C7C8C9CAD2D3D4D5D6D7D8D9DAE2E3E4E5E6E7E8E9EAF2F3F4F5F6F7F8F9FAFFDB0043000504040404030504040406050506080D0808070708100B0C090D131014131210121214171D1914161C1612121A231A1C1E1F212121141924272420261D202120FFDB0043010506060807080F08080F201512152020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020FFDD00040039FFDA000C03010002110311003F00FAD028C0E3814FE82A4A1BE58CD3FB50030C4B9E94FE28023F2BDAA4FA50047E501DAA434088C20C715277A06442202A5C0F5A0088272454D8EB401108C63352E00145808FCB534EFC28B00CD829C0E7B50026C5E78A77A50046635C92053FA1A0088C69E9529C7E1401198C002A4C7028021F2C67A734E6C673DE801A2155EA2A4C0CD0223F2531C2E29E0E4668195FC805D8328DB8A98FA9A0772136F1E4F1537CA4E680B90FD9A3CF0318A9C9C516137720F2573C8A9B8028021F252A4CF3400D5853D29E1850034C0878A79C668018624C0E3A529C66801BE52FA5386280B89E4281C53B3C501718215049A5C8FC280B8D300241A932280B91981738CD3F3CD160B89E42E2A4041ED4582E406DD718E71F5A94E28B0EE47E571528228B05C8BC9E3EF1CD49DF34AC85719F6719C827F3A932074A2C82E4260E73B8D4BC1A2C82E446107F88D4BC5164172216E01CE4FE7528C1E828B201046DFDE352719C76A2C8088C52142048C0FAD4BC53E502210B13C39C54B9038147281198DF1C31A9874A564046237EBB8D483A628B20182263FC66A404628B2019E537F78D4B9A2C8772111310417352F14590AE3042C07DF352718CE4D1641722F29B1F78D4A3AF5A2C82E30C24E32C78A93229D877217B756EA4D4C4D2B215C83ECCB8E09FCEA7054FAD3B0EE5736C001C9A9FBD160B959ADD4B0C8AB24034AC8572B0B650781567028B20B90F90BE9535164172BFD9E3FEED4FF8D3B0EE7FFFD0FAE060AF4A39C74A92800C9A5E680170293700C01EA7A5001F852E3DE80138F4A5A0031ED45002639A5E739ED40076A077A002909DA39A0038EFC518C8C5000071460E383400846294E477A006F7A39E78A00383D05046338A004278A36922801B819E948739A005CD37B5002E71DB14D24F7A0053CFF0D372D8A0038A4CD0029A69CD0028E9CD21A005E31D2933C5017173ED4CCD0171E587A534B7B8140AE07A546D20032481F8D017250703A552FB6C59DBBC6EF4CD3B05CBB915461BE8643B564191C1AAF905CBBF4151EF041248E2A4093350ACCAC700D5582E4DDEA253F311EB4AC172C039E82A3CE280B8FCE7B53066A464829B400B9A6D500B9A6F3400A4D07D2A4078A683C5003C11E9CD341F6A0071FAD21A005C8A6F24F15403B39238A2801E0D34123EB5203C74A68E9400A3A5041CD003B3ED4DE48EB8A007039C7140ED400B9259B3D290E6801E3AD34649A0077E1483245000690EFE4718F4C500387D28A004C734BDE800C514009903F1A6FD79A00776A4EDD280178F4A4C1A00FFD1FAE31C62979A9283F1A5CF1D2800EE0F7A280139F5A5C714000E7BD1C8EB40052F7E94009DB18A5FA734009B8E7A528F4C5002679A762801BCD2F34009ED41048A00613CD3B00500274CD1CF71834008493DBAD1D80A006907271DA9C79A008CE714E23DA8019D883D68232B8ED40087EB41FA500372718A4278A0406A19255407391F5A07EA4A5F1D6B8BD7FC6434D2D0DB5AC93C98CFCBCF7AA49B225348EB64B88E304B385C7AD7CF1A978FB5EBFBD92D98CD0A633E5C630C3EA2AB90C7EB0BB1EEAFAFE9B1921AEE2E3AFEF057CBCD3C5B37CDAA5CC738C9C4CC486F41DB157C88C9E21F63EA23AE58EC561731B027190E2BE4EB6F11EA3148CA6F08C741E61C1E6ABD9A12C43EC7D672EA5093105917E7E9F30FF1AF9EB47F8833DB5CC6D71219624C02093D33C91C54BA7D8A55D753D8758D7E5D3988F29A407A631CD72BAA6BDA4EADA3B4D697B0A4E8372E5BDB9153CB634F689EC54D5BC6304F0928EF6D70A72371C67FC9AE0350BA86FEECA3ED8DDF2370381C77E9EB56A3A1CFED0EAF4DF1DCD6FAA79575270DD5810706BC9EF6592DB5478D2E43B23E090C7E6FD2AE11B98CAAC96CCFA8F4BF154139689E64CE32A58815E03A7EB33456DE4F9C1DF865C1CF34A515D0D2359B3DD20F10087579E19250413B87CC304578F0F104DF6F8E595C86DA1396E0524BB97ED8F7993C411C5344376E47191C8E95E39078989B45123FCE170391D3DA94A9D996AB9EF91DFC5246AC1B21B9CD791E9FE3223C34F334AA26DDB517783914A50B1AAAB13D892EA371956047B579CE97AEBDCD9C3B5F69C65B047E352E1629544CF4C12038E6B951E24811D61599091FEB0F606A6C573A5B9D66EE6B2AD75113C618743DFB53E51F31AD939A8D5C15C820D228973DFBFA5357A7BD218FDDC74A693C5003B34CCF1400EC9F5A406801E0F14828247668C500140EF400A0D2E2801EA69A3A71400ECF1477A005C9CF4A6B332A92177118E33480764E68C9CD20019F5A70E940081B02931400EDD4D073C5055C6C732CBBB61FBA707DA9EA8A33B463273C50171D9E68A001A968019DE9DC67A500349206683CF6A006EEF7A4DB405CFFD2FAE4E78C77A71E99152508781475FA50014639C50029E68C500273DE9DCD00379C5293839A0051C0A5ED40099146280168ED40063DA9280168A0061A762801BF5A3A74A004A2801BCE734A78ED400D39A56F61401191C50C71D48A60309E3A8ACFBCBC311DA8A58AF5C53B313696E589A658D1998ED02B8ED63C4D0DAA05972A1FFBC33DBDA9DAC67CE52F12EB13C928B543346AC7861C0FCEB82D73C7D6D25D1B78DDB628C3038EB54A3739E7320D7E3BD121BAD32F2E90C4409012C41E7D33D3DEB0AE7C6A892CA0407E618C9418AD1A6B63394915EF35B264F2EEE35772A17CD2A4051FE7EB589757BA7DCC734A5409B0731B2F0DFE1F85257EA637ECCCED4833DCB2ABF9A879F30824D6019A6B5B80D01DF13B7209271EDD6B451339BD4B522DDDA46AED6CAD1CA7897E6C818AAED7F9B69619222703E5243657F5A4EE82F645BB5837C6EFBD933C0E0FAFE955B43BE74696E6450F10FE160C475FAD56A84AEF73460BBBAB399B749C1E0A966E7E958D7F742EDD6548C823A6074F7A576C77B1A97D7F71159891A5C607BFF8544445269B147711386DBB8E558F1DA8E604CE6EE75796DEEF2640558EEC9278F6AE635E9CA5F4861531C63F8467D6A94AC68A9F31DE586BA7CE7532EC24060016E2BCCADF539F71FDE32E7009279A4C3D93E87B38D7606C172B2B360E32D5E5567AAC925D2C6A721D80CE4E71F9D34AE44A2D1EB76DABB79A04932841C06CB607E95E65AC78825120B70A63519E84F3438DC71E667A85AF8836CE638983A91C02480A6BCAEC35C7319014824F2C4F6A5CAC1C648FA4749F11C769A0BED9B7DC49C2804E00F5CE2BC674CD68A46510990B7676278A1C46A6D1EFBA15F2BE9F3EA37B29C039519EB5E656BE2698ECB770CB18DA59431DA692561FB65D4FA1748D4AE2FC4532B08ED500C01DEBC926F155C7D8F6444DBA6C0102E454C91B29D8F78B4F135BCDA9C964B22FEEC609EC4D784E9DE289209133C38F9779CF22A396E5FB63E998A7DCA0F4CF4CD790E8FE3465B8489E577978049E452503755A2CF65CE4633D6B36C2F7ED16EB21FE2FC2A5C59A29266977A686071523B8F1C9C6E1914831BF7EDE680251DA93F0A063B3499A0070EB40E9D2810ECF19A41400A3A714A2801D49400027340E9CD002EEA6F5A92AC480E453471DAA8429CD2D002720F0697BF4A901452038C71400EA33F95031734DC8A005A5A004A5A004DBEF4DDAD408FFD3FAF47340CE0678A928684E29C00C50034E775040C5002F7A4009E7B75E9400BCD1CD0026322948E28017B5260E3AD002D0066800F7A307140052E38A004FC28E4F4AA0109C1A43E94009411520277A3BD0021A4278AA246B1F97AD4333EC4662280B983E23F1045A458C929F99C7445E49AE0BC7FE25360B288E22F2A83839F6EC3AD25A9139F2D8E07C43F10F534BE0F248D1AE4F0506EAF3AF106BDAC6BCD233720E73BF90063B57445773CF9D46DB3A097C7F752C0F1CCE271CE37919EBD6BC9A6BB96D94C33796D8272390DF4EB5AB826429CBA9DFDF5CD85FC86E40576605BE618CFAF4AF2ABBD7BC856584EE18C0F9B91F8D4F25B6134D9D0DE6A260420BED6CF47507F4AF3EBBD6679D312952BDC31E6A8D153725A9BF73E2799662D12EF8C75E9D6B8F9258DA1CC636B376CD52638D247A4683AD45753C78BA4560F9F2DFB57096FBADBC9B882503CC186264E8453BAE844A91EBBAF5BC10BBBC61713270AB83CE335C7697E21FB4B086FA4420E3E6239047F8D2B364DADB9BBA0C0F71A76A2A9260A018E07AD63D96AB1E9505F4718F9E76E33E949DC6E26FD8D930779AE0948A3E4B1FD0564FF6F3AE8E2D2694798FF33E7D051A91CB72CDF6A6EFA9C32C670BB718C7046EAE50DE342C9260322A90B9ED4AC5FB3BA3475ED35E502EE33BA3707818CF5FAD4D05F1BCD3BCB71F31CE7E63D0D0106E2F539B4B333441625FE2E723B56C08A5826914600C718EF52D9D0999FA55B15BA70F8C8CED3566DE68A0D422048E721F3EB5A44C669B39FB88A79EE99E43B9F71C8FC6AE4B224770ECA782DFD6A4B4DAD8974FD2EEE528507EEF8CE7A7E1566CF589997CA815157180BE945CCE5CCCDF8234D322C46C86E1B1F7F3D3F3AC492E540F9DFCD9BA0039C8FAD69A91CAD1D8DBCB965964994E78C0278FD6B99B7F3495F341418240CF5F73410D1DD25F7CE8DE67CA09185CFF008D70DFDA575BD6DED86153F8FD6A796E369A3D456F10A0F94613E61C727F5AE334EBF96D104B2033B8EA47402A1C1A1C6563D3F49D7ACAC092C85E50DCE41E2B817BE8E570D31F2B20F0A4E73EA6924C39AC7BD697F116F192282141B72072393F8E6BC3ACB59586E10C6F22B29FBDD69B894AAC91F66E81AAB6A36A2475C1E0F6FF001AF0DF0478AD0C8229B50647E3E53D18544A9F63AA188BEE7D20A49E9593A55EFDA6DD59A45240C70739AC9C6C75C64A46D64D311B233505120A07D6801FCD25031C3A5001DB400EE9D69BD4E6801F4DE73D6801C7A7AD20CD020031D3A52E0E28B0EE28C9A0034085240207AD2E3D7B74A005148A0FAD001487228017BF5A41D6A4A1DDA9ABC11400FE9411400B9E4D263834007E069B9FF68D007FFFD4FAF78029091CFB549403A521202E5881F5A0009CD2E3A71400CF2A31299422F98782D8E6A4C63AD001DF34114001A3068001C0E6819A0001CD1D0E075A005A4C123038AA01370CF1CE280B81400BD3A51B78CD0021208A5EFC5003334B8C75A0061241E683C1A006B1005433B88D4B31C0A2F626DD4E7FC45AEC5A5D9BB392588385C75E2BC33E28F8E649EF1ED2D995628CE4B03924E7AFD28B37B18D4A89688E63C55E249EFF00599CB3A33A8C0F939C67D6BCAF53F115D8691517C956E372F27D735D11858E36DC8D6D4750BEF2CB47701549E7E6C0CD70915FDCDCDD791E6BB33E412C7FFAF5A58C9AB0CD52498CECE97AB2727EEFCA0D52D4041129883EE7072769A6D14A37DCC4B8B9946406C37A64F5A49246E115123DBD76F527D73495CE88A48A4CB2B3E5F0739C7BD5E4B5919C1FBA3A8ED8A9364D144236F00707B67BD6F59E982497214BEDE3A74A77F30E65D8CFB62ED0B5B1E564C0E73C11DEBA61A0CA2457111007200EF49497733B332ADE3689A35641F29CBFE55A9358CC320A32BE7E6F94FA55732EE438DFA0D9A52E017DB85C60D5BB6B3061659530C4160719CFB54F30727732A590CB209233C9393B463AD69DAE9ECFE6C6A39EDC7BD1CC52A6994FEC335CD946F6D22B36E2361E0FE153DBD9DDC4CC02B2329041C1CAE0F5A6DF605194762ADBA5CC0E724E40C15EE2BA7B5B32FF00F210469C49C071C723DFBD67CCD6E528DF74640B8927B0924DC37C4303A64D6D4DA4C681E38312AB0664217047E953CE983A6CE26599D41F9006FBD93CD75AFA039D09E431B075E483D68F6A90FD93671AE9290B30008639E9D2BABD2B468EE622248DB20EF18F5AA735DC4A0FB1C9885E3FE301DFA638C7A569EA561243232AA720907827E94D32B9591D9DD5A58DB02E3CD994EE0E3EEE7F2AAA6C83C40E360500E455F3A31E45D4BA9AEEF9DA698028063681EDD2B265B752A02700F5C51CC274A06AAEB05AE02C76AA507618E2AA41631C71191EEA3278C2B039A71909C1763565BBBB9FF7876DB83FC2A7834DB77860708F70064640183D2B4BDCC5C7B1A11C57401FDF6E63C60F5F7A91AE15D15629372B904943CD48AC3BCE9217C34891C9BB83EB55E68D7055DF791D18D32AC8E9349D56786E8B170771001EDF8573114EF0C9C8223CF001FBB4ACCCB94FA73C05E3C6B6786D2EAE9258A4E9C8CD781697A888A58E585FE65E7E538C7A76A2C9EE68A728EC7DF1A6DFC5776CB246FB811E95E11F0BFE24BC922699AA5C33293B55A43D2B274BB1D74F10A5B9F44AB5416F2078C107DFF4E2B0945C773A96BB1681F7A68A92C941F7A4005003A8EF9A003BD28C62800069C318A042E78A314005140828A004DC72463A53B1400DCE45382F18A00074F7A5FC39A92839F4A5078A00682734E2BC500465F9A42BC5003F3EE2A3D9401FFFD5FAF00E718E68C127247EB52508543718047BD38641E94007E14B9CD00273E94B8140075A07D68003D3A53BB5003319A7EDC50034A8273DE9C39A0068C0A71E2980DC0A70CFA50033B6314EA004FC29181038340087A7348C78A648C61C53246C0E39A570396F195DFD9F4597E7DAA177121B19AF39F8BBE288D6D9F4FB3BA22E30727B019F4EF52FDE7A113A9CAB43C0BC597ED797923AAB30195394E07BE71CD731AEDC3676A4883241237576423CA8F3A52BBB9CB6A12AF9C634CB3671FAF4A49E38DC168C0FDD8CB7D456BA92B428C972D6FBD628D4B6325B0723DB348A0CF1B42400CDEFD68BA4696BF42BAC6582190025B9CD74167A24E419B6FCA9D7233FD6B395481B469B68C0369972767CC39C63AD75B0E8AF3CCE0AC6A464707FFAF51ED2252A6EFA9836B6924DB571927DBA0AF44D13433B4A98817CF2473C5612C4C4E8861E4CC3D27409195242DC7DE7DB9E9EB5E936FA4490909E5A237A8CF3FD7A735CB2C45F63BA3847D4E7A2B1088B1CF11DAC701467BF4EDC57702C5142BBA296DA0839E78F7A8F6A53C39C04FA189271B23195E189C74C9E3F4AEDD74D2F74D21018EEE99C0E83FC297B5635864CE2EEB40F2658E4893E420391E87F0AF41FECD6206C4031EB53ED197F5589C9C5A0C125B09BCB0256C038E3B576B0696B1EE2EA48CF4CE0D4FB47DCB5874B6479FDD680AD32CB0C31950A33EE3FAD7A03E9D107DBE4E5482028EBFF00D6A15497707875D8E41B400F68AAB1020B6704038F5EDC5773058A22E323AE3149CDBEA52C347B1C6AF869E428EC8ACE463A01C1FC2BD152D864A8E463AE297B492EA57D553E8708FA028B6960962CC6EA3E5C0E7FC9AEE9EC91A30C064819C67FCF7A8736F72D6192D8F358FC32D6E4EC50BB41C01E95E8125A6EDC7675E87DF14DD5909E1D763C8B55F0C19137989632EFD481CD7A64FA61799490A140C01E95A46BB5B98BC32EC7924FE1C880651112C06D231DEBD4A6D1E351263963E9EB8C8AAFAD314B068F0EB8F0E09B688C15C920153C57AA5CE8DB2790A9DCCFC8DD8CE456D1C4B399E15AE878CFF60482ECDBCAC181190C00AF5C9FC3B13401D6204EE2D91C7355F5AEE2960EFF0009E333E8B790BED5B6CE08C37F2AF66834A59EC506C50C4739EDEB4FEB7D85F52BEC78B496FA842BE4240A5BA6E26BD465F0A8BA89B6305DA71D73F8D6AB1B1EA61F5099E56C750B75412A9C1F5AEA355F0EDDDB637A6FCE78EB9ADE389848C2783A91E86559CF0CF1149485661C64E2B3DEDE58CB6F4C7B7A56EAA2673BA0D1AF1C32C51992191B6AE381D78A8F4FBD9136C2188524E558F0462A9DFA1CEE3635744D6D05EA2A4E1483D7BE6B1EEAD16DB51595495DDC8DBD39A7095B72251BEC7DA9F0B3C4573A8E92B0DCDC198A818663938AF0BF84FE32B8D035248EE1CBDBB7CBF4E689C79B636A5579373EC8461F5AA7A7DEC5796B1CF0BABA38C820D7138B47A319A91A4A68520F7A929E83FBF4340E475340AE3C0FF0A4E7B1C500386318EE697AFD680170297140098A31CD0037F0A7500007B5385002014F140094549420E9D29149C9C8EF8A005341E450027434719A006E0FA52FCBEA6803FFD6FAF71E94EC81F5A92845273EF4E0467DE801181F6E695865A800C0ED40392450021CF614EE9400DCF34EE09F4A003AD267271400A052D0037033484E08E3A9C5048A00E696980DC0F4A53C76A650D22826801951C8E114B13D29F411CCF8BB5B8B45D1A5B890B29C103039E95E3BF1A7C46264FB0DB383B321B054E4927039FA5425CCCCA75144F22F167889B51D4E758F7B0CEE690E0B1EFF00E735C69B5791DA6924037F3B7E52718EDED5D31A4A3A9E7CA6E453BF9B74842BEEDDD47248C7A52AB5BEFF00DD956719F9885F4AE8D199DAC514B606D0E7EE9F9490BF956C5869E2E7F74199990E481B403E958D5AAA274C29393D08346D104B3452B20DA01E368E6BD0F49D37ECD147840AC14F5DA7FCE6BCEAB88EC7AF470ABED0DB6D2E248E35C0E3AAECE95D1C36C1428DC319CE700935C8E4FB9E9A8452D11CD4DA114BDF32DD170846E52A06E0179C5767F67532B372A4F00800638A5CED10E9C7B18BA7C004604785C93B82C679233DBF0ADE86D3030154AEECE3008EDFE153CD72D2B6C25BDB36CC29DA4E39087F97D2B4A18D767CC98DD8EC29A46BB91FD981C0CE78F7E79ED5A2046645555392011EC2A44A26443686395F785E40238E3AFAF4AD096D41476C724E0F1D298DC6C3446A58640C718E08A78DA30830768C1CA8EBFE3468115B5C040483F2E5B241273D45594276AFCBC7DE1F28E7EB4AC742B6A406D491B890A4FF000FEBFCAAF2C61D88619CE327F4C7E548A714558E3DA077E82AD3C3BF62F208E381EBD0D046C311725940C123A9E29CA7686570471DFA52B17743248F1819FBDED529C6E5DC7248E839A9B0CA8D1BFD9D89E5FF009F15608CC5B3B1CD0BCC2C55FB3B36D25BAAF15622049CA1E413C907F3AAB5C2C8CF9613F283B4772077F4ABAF1170ABE530CE3B7E953CA1CA62DCD93391B793B73DF3F9D6F2460A6D724E78CF38C5357427047351C27798CC476372A76F19E3FC6BA06B788C8018400BC8C2F1CE3FC2AB4EA62A9347262CD609258B60F9C65467F4FA5743359AC8436CF9F1F2FCA7FC295BB10E9B5B1CCDBDB101C05DA4FCDD3D4F35B82D990E481C927834491317630AE34E59211E644A724E46DADD30B94C1524F3DAA145F434767B9E6BABF84EDA78DDE28D632385EF5E817166CC1D48E0E474FF00EB56B19C96C61529C59F3A6A5A64FA75D1DD860790474AF4EF12E84254641100075C21E9DABBE862BA48F32BE19FD93CC2D244997CB73953FDEE718A6DE5ACB637854A958CB7F771B6BD285453478D5293832FC72CB6774A222428C7CCB9C7AD57265171BB7E630460E38EBF5AD22EC6328F323EA0F833E3069224D1EE6676206230DCD7947C3AD620B5F1159BB48239558039CFAD5BF7D0A9C9C19F6BC4D900D54D3A713DA4522B6E0CB9C8E6B8A4ACCF5549491A6B4D5E6B32C947342D0038018A518EBDA900A314A08A004C1CD3B3400DC51B8671400A338E6941C8A004381EB4B9E334006297B5218C029C78A006F6A09C74E6800A43CD00273E9494058FFFD7FB0300F6C7D6940E00FE7520371CD29E28017BF4349839CE68000297F1A0006475A0063CFAD001DBA53B1C500260669D8A006E39A53C50037BD3B6FBD00369D8A0061A5238A77018C30691B38EB45C2E739E2BD5E1D1F41BBBA95F1B50E077F6FD71F9D79E7C69D624B5D17EC6AE1159496605B3FF008EFBEDA9E677B194E76D8F9BFC5FAF4DAAEAD35C839563BB6EE5C679CF1DBAD723A85C4CCD9F303248C7E504EE007E1DEBB611495EC704DB910CF7EEF6E5A43B5D8630188C71D80E7159C5165BB239DAA075CF3C63AFD6B497A10958B9A7463CE8A531672080320E4FAE2AF69B047F698D51CC847240E7F9D673BC51A28F31D7787AC142EE4555914024961C018CF6EBCF4ADDD263F2D3EF138F989C019C63DBF5AF26B4DCB73DEC3C1246ADA5B9445181F74F2D8E993D78E9C74ABD0C64ED3F31F97EB8383D31F5AE649B3BD3B8E8E318552530B90718E0D4FB31855E5727032BC9A5AF440DBB263CC68EC4E074391C7A714E1CEFE9F863A67F9D3BB5D0D5AD51222903865DBEA31FE7A52020055C100B7F7877E7D3D6A6E0A25D55DA188704023D3A5360DD923701FC3C103FA5234512DA8CB2374E3693C76E6951594282C4F4EB8FF0AA2921CAA8230770233CFCA38FF3E9530C6D38049DD9C64741F850368A73400CC583019FD7DFEB574AF072CA40CE0E4520B15A28C6D3CFCA4E79E319AB41395F9723EA39A7705A0E409B31821B00FD6A6284AE72327D871526B721D80BEDC003E8BDA9727A3107E98A099111064770CBC01C6718353820EE0AC0E5BBE38A6044D6ECA72AE00DB8C7BE79A9C91E68CE377D7AE454814551B83D8751567701CE5411F7B9A1AB8C8B6468C4600CE7AA8A57660C182FE0719A16802189083D386396E314EDCE464819FA0C9A91DC85930800518E38007F2A98A964720851D0F3C8391FE341572A18D43050BC72064018CD4CE84804A82DEF8A092A9560189C6738E31C54AC325941DA7919E3A55A643572B15272578001F4A7938DDBCF0072001410E162BB460038CF5C8FF39A9864900F0C3B9C53B993440D1FC98751C01D875F5FCE95CB10DC28CFF9F5F5AB49932460EAB6619772C6A7A0C63D2B4E71BCB2305DBC7F0AE7352EE8C6523C4BC49A51173BD220CB8CED0B83C9EF5D6F892C5674950F961987DF6039FA37FF005EBAF0F51C59C189A7CCB53CD30E2303C960472508C63D2A59BCCB67F2A675908E339E73DABD78B523C49C794B3A3BC897B0CD12E581C903A8A8EC24F2E70CBB4A1186527393E9F5AA52B688C9C7AB3EDBF8737935E785ADDE58F69D80E0FD2B0BE0FDEFDA7C3088AE1D130AB9FF00F5D4555D4EBA12E8CF591D303AFAD22138AE43B2E4AA39A519CD003FB63140A928502979A0030697E86801314BF8D000178A5ED412276A5C5002138E2948E28010D07B50027D0507918071400D208EF41FAD003703D2939F5A0763FFD0FB0B1464548C427DA978A004F6A4CF71CD001DFA528EB400EC73C51400605140052E78E68010D079E680128ED54007818A5E28018476A53D29088A41C74A1F18A40780FC73F2E1821B96664C020019007F875356FE36D8477161E63A12BB7001C91FFD7CD4A6DB39EB6C7C917642B83280A0B1E8180E9E952FD951B510AEC11158FF0001C71CD7A51F84F3DEE2DED97F665CADB33C5249328937C2C4AAA9191CF18E7AFA53E68D24BF6784F98E140DD8230076E7A5424FA95A32FE8D1225C82803373B4329FE60558B1B70AD1A9CB74FF966C48E7A7F9C5635647553A6CEF3485DD0E04B9218FAE5790076C678F5A974B42B68A177F2DC8DAD819C67DB3939FE95E4CEECF7A9AB1D04390FFBB70588C1C7041C7AD246C4484BA67E50082A7BD6691D091286DDB333E771E0E481FA0A173F22FA0EEA401F952F746D7BA8907FAE97E7209C7B639E9D2850C5DB729FBBD369EB9A97EA6AFE2411E51573202ACDCE47BFD2A40C9848DDD4B39241C9E71D69D849EE3D1BE5259803C6EEB9E9F4A90FCA8CA4E73DF047B8A2C6DDCB91312E097CFA0E73FCA9B0C8DE6E49DB81D831C7E34F4EE522EC7970DF3F231CAFE9DB8A8565629F331009CE486FC33EB4F41964AED663BF1D88DC4F6F5A8BED059DB20F41F3738A41744A3E4018951CE33E9FA54293931AFEF5BE8C3193F9504DD16031CBAE79E9D0FDDE3FC6904A4A39E700FA9E9C7B7B51A142302DB083C1E323FF00D54FF332DF7C631C9E47FF00AE9010840A186E27D4EE14F63849339CE48EA7A53B14C4D8001C1F6A4595FCD059D82F7C0FFEB71516042313B402385E474E7F4A4DA4EC4328CF538355601C154C986E83AF23FCF5A71201216627BE031E3FC9A2C026C43FC3820727771FCA9A64C904BE178C0E7FC2A2C5E84807EECFCD8CF1938E981EDED503396420C8012DC8CD160B88CE012AEA718CAF1C7F9CD34EEDE01231D8738FE5EB55A12990E015258E09E01E3F1A528DE4E0B64703A8E4FE548190B49F3364A83EB91CE4539E4DA49DD9C1FEF7A0FA5322C44DB760E84120738A0B8CA9DD807B1EDFA51E845885F6ABB6E6183C7518A598B0DE55BF0DC48AB5731915A6C6E2570723190467143B0C921802067391E9F4A767D8C8E575B4DF1112A86DC72318E39E9FA8A9B575013686CA9EC1867EB9C7FBD574D6A72D46AC7956B36E8AEC4BAEEDC31B4AE73536A4A1AE194C8578C039FEB5EB5276478751DD999142EBE55CC7B132DF303D49FA76AB30C67291B36E61CF2DD7DEB65B9CEDE87D1DF022F37D9CD6E0B71862091C567FC0E900BE95146D523A039C8C71DB9AD669343A4F53E8E8BA74A58BEED7033D32704628CF15203C74A51D2994396818F5A005FA52F18A00314B9140094B5220FC29B40099E69D40086909E3140099A3273D6A8084B00E783C5389C630724F5A90D08FCD1E87F2A931EF40EE8FFFD1FB0A8A90039ED4873D8D002E08ED40193400829403400B4B8F4E682839ED4801F5C50018C8E9463D38A000E00F6A42091400EF5A68CFAF4AA014E7B629A719A000F7E2908F7A09239381D29586071401E0FF001CD636B28C1DFE6AA13F2A8DAAB9E87247BFE55ABF192343A3665CAE17E43C7CBC8E7907D4D669599856575A1F215D42AAECD960F92725571F7781D7AD5ABD8D637DD305081BA065E83BF1C57A29E879FCAEE578238A147DDB9C8391B941FAF7E28FB4160321319C020A9CF19A5265463A9AF62ABE743850A4727E4520003EBC9AB7A0D8F9973E6ED5665E0FDC6C7B63FBD8AE1A8CF4A8D3BEE76BA5A11002EB9CB0FBA01E8067BF1C8C7F5AB16B18FB3F0CBCE7FBA7A1C8E87835E7C8F6A292B9A63966C2B1C0C86E703183EBDB351EEDF71D4138241DABD3232393EDD6A55CD39B627888F948047A6171C7B8FA53A209FBB255368C7CD81D3DCFD2B3192A06766500019E70B9A54E24F9813B47A29E99FF000AA1DFA92C51A1D871BBAE0ED2319C9F5F41F9F153C3183E5ABE133EC3DBFC3F5A35088E8D00EA9901F9C8278E83393FCF156BCBC45C64F3DD40EDF4A0D6E1187DC00889E3A64FE59CD5948D4310D9C0E98E7F91A0D63AD8224CA0E09527382BC9FF000A9631F2F4DA0E3918391EC7FA7347332F9771BE4B61CA0627D706AC850C1C22E0E7E53B7A8F6FF229EA05478DD644508301BA9E7156B6974188B00E7822959F717295248DC6E7D84E41E32477FAD592B90E1D496278E3AF353CCC7CA54C3F98B863B194673D8D5A688AC8B943D47543DC7145C7CA41E5C8602CD20CA9C63073D6A744F9096EA71D8FF8D50ACC8373EEC17F988CF53D79EDF8559F246785C019C67391D7BFD0D2B31AD0AA567C46431C8E09F9BFCFA7E7560C5B95548E3BF4C9FF00F5F1F953B02443B6456F958E78E84F20D5E119914F18CF61DBAE7BFB54DCB5128F964320DC090C3A822AE08CA900AE0F38049EA7F1A9D42C5028E14E4963DCEE22AE3A3053918EDD0F3FAFF8D3B858A646D240CE07D6A670F9CEDC7F7873C54B6BB8944A79DBCEEC1078FBDCD3DD7E51900E0E723AD0ADDC968AECCC149279E48192291D590BE783CE700F4AA4D3EA4495889D4742DB47FB44D38AC8CC06CEBF2F4239A7A2EA6572139563B5CF5C0E4F14E62C55BE4C0CE40C1E28D4CE4CAB27CD919249E719619E78A9194B924281DC72DE9CD5A4C8D0E475899826448D9627F88F6FC31EBF9D2EBD11C6ED982186EE0FF435AD3D19C35568701A8C72C8D23A02F87CF05B8A9EE897E597938DD95600F5EF9F6AF4A0FB1E3D48EA64DB06921542CE245E8D93D3F2AB96B02C93ED2ADD381F37CDEC2BA16E723D8F7AF827A69C497DF687C703CB20E00ED8C8ADAF8316335B68CF2C9828E1760CB1EDCD6939591A504AE7B2C7C0E942038AE33D126068514AC04A00CFB0EF401C1E6900A297140076E94734142F34D0723228015738E79A31C54801A5228012908A00423349C8A000F5E39A46071D698869C718A4EF81C520138F7A4FCE98CFFFD2FB0A94FD0D48098A39A0051907228E6818734B9E28010528C839A041CFE7460E298098A5E6994276A5C1CF4A004C75A697C92003C54DC05392693E6CE715570226900B91177C67F53FE14E68C197CC03E6C633F9FF008D046A23B71C50E4E39E2828F1FF008D33450786B7BB90D93B554E39E0E4820FA7A0AC9F8E8C8DA5AAB16C77C29206303FAF7A85B9854763E5ED4E4699BCD003005802B8C1047B7F85666AB214209CB6013CF3E9C7E66BBEDA1C4DB6C9ECDA59EFD02370ADC051BBD3D07BFE95A3E0CB192EEFB784DC0B0C13F7BBFF008563534475518DDEA7A1689672416E11954B151B87A8F4E47A57411C1E52E0A30006463FCE3FFAF5E74B53D8A71515A112A32C6A772F04B6FF0094E4FAE71CFE19A9943796233C92C7A0E4FB7E7597A9AF32D458F2CDD55BE5FE223D7E951DC6A096C8C5D893820633C7E7472B7B039DADA97A3DC1918F390013C64E78F4E2B8ABCF13991435BA923B10D8DADEFE98E39F7EB55EC1B39E789495D1DAA5E28DC04B1823AA96527927B6303AF7C579C41AB5EC6FF7188E320BF4AD561DF51AC5EB63D5EDAE41F2C07047249241E95C25A6BCD0C7193336F4E301F3FAFF009CD4BA56E85C31299E911B831A8668CE0FB71FA715C9D86B5E6200E7631192A793CFE15938DBA1D1EDD33B8891376E05727D3F879AC5B7D6219AE4857C04FEEB642F3DB8F5F5A9E5F236559686FF00CC230C8F9CF05588200F6E3AD662EA5B5460EE1BB8E71CF3C8CF5E953CA6CAAA77361A30AAC01C163CF0BF866AAC5781D5C8208391C91D49F5A66D75D0BA1395C3AFE207AD44B21014E097FEEE464F3F4ACE45264AC88412AC37024F41EB50433B206C83CF4391C8CFD2868D097CA1B816C74EE3DAA5520C8CC48C93B704F15285621440A3E6503A1E8B4A7E4752482493C9E3354876638C4006573C15247CA3AFF8E2A5F9F962A1B0002076F9473FAD5AB10C55445DA7180475C0C678C7F3A8D9C9511A3346587057190707DBDE807E858181CE46E3900E3A9E7A7E75179FB11C63927AE783F5E28B8D5C7B1DEC00E41E49C74E9FE34D7B8C81B4A28C64E3F1FF001A9B09DC8644200E837F1C85C5559AEF080EE192C78C838238C7D297291CC24883CC652C06704F03A62B3E4D53E6605F0D83D71CF1D7A51C8D99BAB15D49D8AE31DF38EB8AC3975948D543952704E73D7F4AA503096217434DC06046E01B3C9DA3B751CD73936BC8F1178E68C33301B77027F955AA52EC43C5266F15036E36904E4038CD73F67E218E4282565053B9C7F3A4E9CBB11EDA26C938427B9200181EB504572932AB46CAC0904907FF00AD4D46DD05ED131CC32CFC004F390063AFA5286650DF2F6C76EB5767D05CC646A96EB2DB07645386F415A322068B81DC9E54734946DA90D291E4FA9C2CAD2AB46A5C7057F039207E3E95B9E24B5103C921FB8C3B0E84FAD74D1A9D19E657A5D8E46D153CE552016C8248CFCC3BE79A934E8FCED4E04008DCC372E3F3C57A90B3DCF264DAD19F59FC2D8A36F085B4D182BBD7B81EB5B1E04B1FB07852CE0C7F0022B3AC7561E2F7675A99A72F15CC75920CD385003C522F22818FE6933F95003B9A4E695C41FA0A0FBD1700A3B6691421269A413D280149A3248A006906839A006B671466801BDF341F7A006E47A1FCA9726A8573FFD3FB15BF1A0D480D0052F5C7A9A005FEB4505077A280100CD28E33CF5A0913DF9FCE978CD001F851DA99427438E73F5A0F5CF7A00694071D7229D4AC031548FE2269D83400DC003BFE74873BB9AA13D08A63842483C7E95CA78E7C429A0E8724C0E247076E3AD652A8A3B97184A5B1E21F19B55379AB35B46E515010490D93D339E7DBBD79E78A752B8D5A592F02E7731DDE6280D9C820F5F7C55536BA9CF89C3CD6C8F3CD4A22B3A465B2980C4E3A64038CFD455FBBB726528514C8FF00781DB91FFEAAEE8F9B381C6DB1D7F8034D090BDCF240C118EABEBF903F9D741E10B510688AEC8031EBB42FCDD31E9DAB9AB4AE7A1878BEA7406305DCE0E4719CFE9FE7BD39FEFB0DBD13AA8CE7E95C1A9E9C4A7330B7B532331DC06E182464FB0EFCFAD53D5240B165C00199B72E17DB03F5A1308C6E99CC6AA5EF2560676CB6463693D88FC391F9FE742EE96423CB5E79242803A0F7FC39F5AD14AC64E9735AE614F062451116766E0E55BB76EB9F4F6E3A57576FA6A4A42B42A4818C6013D327834D57B12B071764CE31126488E5989EBB8292460807F9FF009EDE930E8F1F9250DAC792C7A460718C7F4FD7F34F12FA1A7D4E2AE79886911D191C952436421E3DFF002EFF00FD7AF5B4D0EC723758A14041F99723BFF41FE79C9F596FA14B0715D59E4571A85F24A0472B825705B9E79AF62FF845B4B2AB8B0842F405A25F5A7EDAFD07F565E6796D96A5AAAB2ED761B8704AB1278CFF003AF46BEF08DBB336C85508520008076EDC71FE3512AE57D55AEA71165E20BEB6951679247E99241C1C673D07BFF2F5AE827F0AF9512B4719600E71B78391CFBFF91597B4B9A4695BA9BDA5EB13CCA7CC27385C60B640C0FEA6B9A582F2C7CC5546D89C63D47FDF5ED51CE7528B47A07F68A88919599B2D81F875AE2E1BDB9668A329C1C6DEDCF38CF3C74A96EE5EA7776B786472402CB9E07E7FE1D2B174B998AF3C073DD48EC71C763CD0D9AF33D4EDEDD95D3716EA02F53923D3A557B57639009C11CAFA1A5A9B45EC49392AA0F19C90467A11DBEB50DC79839DCC79E38FD3E9EB493B94CB06E8794DB011C6783F4EFF008564CD78D1AB96E081D403F9669323992DC94DFA891393D7EF64E339FA5727797CC48600AAF27B804E6AEE62DB3AC7D500561BFDCF3818FCABCFAE351B87592163C83C763557239D9BBA878A1208F7B4A48E490ADEFF00D2B869A0F3D937A310DF2EE1B81E9C536E264F98DE93C5B1CA8DE54F821BA6E078EDDAB994F0B5DCE42C4768241528C7D3D33571943A9CD28D47B1727D7849280D3320618C9CE074FE8735622F05DD0954C92B1894007683ED9FE59AAE6898FB3A8F7335B57B8DC1198301C6F0D907DBD6BA6FF8432DF3BA384907B86E41E47F4A7ED20BA07D5E6CC08A7679094666752323278201CF6F6AE9C7864AA890B0DDBB27E63CE09FF1AAF6A88FAB496C73092994AC888501E4739E3F2AE8DF426888DAC4F1B4638152EA262F632EA64D95DDD5B61918E40F986EE09F4C55C9ADA6B7452225725867820FD7F9FE54DCEE0E935B1D058DF2DDC59DBB59B3B949EFEDEB5434668D94C88369079073F9FD291514D6E6E0DA5573D0F4A92100EC42833C0EBDEA56A6B1392F1746E2CD9C70092B9F7F5A93C6A07F67AC9B703D8139F96B4A495CE2AED1C6F8660327892DD9B20AC80862D9039C7F8FE54EF0EBCB16B10B471ED0080D82C003FE73F9D7AB07D8F164B4E667D99A22AFF65DBE071B07150786999F42B566DDBBCA5CFB7159D4B9DF45FB86FA73D684E38AC0D89474E949938A00783CD005031FF871480D002E79E68C9C50214D26680131C734060C320E4548C0719A6B32A0CB1C0A003B519A0069DBEB41CD003690F02801AC79C75A42726800C547BCD311FFD4FB0649561DBBF2334F60187CCB9FAD48C4EC3DE97B5002E38CD275AA18B48091400B83464E46064773E94084039CD3BBE2801BEF4BDA81852D00379CD3873CD00339C52F7A008DC753439F978A36427B9E41F14A5696F2C6D646511824633C6E238E307F2A83E20796FE2450C788D4138E31C703EBD7F2FAD79555F333DCC1C1B47906B766B1DBF9A19171BBE6038E9C6723DFF4AE8B58B6408141CF24F07DF8CE3E9DBAD0A56773BEB42338B4D1E3F73086BBF2D1FE5918AAAEEC93F4AD5D62D8C5AB020953BF920925BEBFE7F2AF569CF9D591F1D8AC3B84933B8D06148F444036900E7820E7A7D7FCE6AD6988A34A8829183B3BEE03200F4F51FA9ACEA3B1D9452B0F9C8413302BF77F8B1F37B1E2964CAACAC0004F031CF39F4C571CA6775385CC0BF579E54446554079E3D8827381EB5A6E996854395299032320673CF3F4AC9491B468A39D8D4A8951A35180A7DBD3FA9F535AB35AC6C2578D828248553B791D7A639FE7494CA74521219EDEDCB194A20C641381D3EBD3804724573BACCD6D6A1669A6171E5818DC33C8E876FE029A7CC16715766DA6BA9344F1DB47BCAB70D800100E3AFD41AF367F12EA37528B2B2D89197C8CA8233FA7602B68D09BD918FD6237773D49751B90C43DC45005424955E57048FC7938FAD71173E1BD567F0EBDECB773C92B2B3EC53B46739E477EDF89AD3EAEFAB31FACF647A12EA36E5591F5A4500FCDBB68DBC8EDF8D7CFF62F656DA8096FADE4BB8D49DF1894A64ED3839EBD79AD63864FA98CB153E87D086FFCF6916D2FA37270421C1C64F3C66BC4748D3FCD860FB2990BDC4A00C4C7013386040EA7BD39617CC4B16FA9ECBF6AB98C4724D14528EA59300F5238CFD2B36FF47D734682D5E09BED504A3E759B0483CF73D3AD62E9729BC7117E868BDC5BDC412390BB87272B8CFF009CFE958B65A916758AFE136B233756F9500E7033DBAD612A763B2126CBD3A08E7841503236820762081FCF27D6B46F6DA358A1F2F661573D011C8AC1E874264764EC96E4A2A81BC1390063AF181F4F7A5D3E3CDBA12547CF8CA8039E7F2EBDB146A6895EE761673205C0E07638CE79EE7D292C8052EDBB2D8C83C67AD5DD9AA56B1A8C0184300705BDFF003F6AACF21F253856C76519C548233F5146632606720E076269B7938C48FBB0140E4F1F9522651472D3C41E48D046A471904038CFBF35761B7F3EF54C9B49C0C023DEAEE8E771EC641B68E20E0A02CAB823693CE4F3CFD2ACEAB358D987495C23B28217039E0E08FCE9AD76276215BBB583C9F2E16938C05036F38E3A915931CB7F79346FA6D98E8486938EC7071D8715AC6939184A696E74106AADE48D967F2EE2A5588EC7B5799F8AEE7C43A25E084DEA0040C796A0633C1F5ED551C3B6F5307894B647B20D4EE373B7D948DBC1C76AF0F9350D7A2BAB3860D7A097CCB5F35F05B6C6BE878FBD8EC056FF0054918FD796973DBD75A8DD10CD672294E4A95CF24F1D0FBF3E98AF2CD27C53E2210C924D6F15CC501FE1EAC0127F515CF3A1246B1C6C1DCF558AEE0B8406220FCF8239C9FF003CF5F4AF3FB3D7B4AD4B723110DCEF2729F2F18C7F8FE7597B3923A5578EA7A3CA8A08390770E076FC7DF35CC5A6A17B0BED982CAB8F91D5B39E3393EF9A5663BA958D1BB0AE32ABB8EE0382471FD3BFE752C53ACF18257692C1586067AD09B339536AE51D34049182A1249E98EDF9F4C56A47036FFDDC69F3750380063FC8AD5485ECDDD96FE752876E1781D3A74FF1A54C21452A0F18C9FF003ED549BE5071B1CAF8CB29A74646CDC580039E9EBD699E367C6930E0659A52011CF61FE35D14FE2B1E6E263D8E43461711EB71C8A1B861907241F7357F4D58AD63FB4BA2FDA25F9530BD473CE7B74AE875E349D99CD47072AD13EAFF000A3EFF000FDA118DA50633D7A553F02C923F862CCC8BFC0A493FAD54A5CEAE52A5ECDF29D8AE49FA5393DAB32C900A51400E1450020CD28FAD003B14B400947BD0037B7000FA519ED400D3C8C1008F7A334009F8D2E0E7DAA406B1C507A50035BA5230ED9A008CE697F0A006F3ED4BCFA0A00FFFD5FB1BB52FB5480D3D38A3E9F95300E28C53003F4C50466800C77F5A3B0E680178CE6909C5002F514CE7775A450FCD18A003BD14C04341A008A4E10F5A571F29009E693D50BA9E23E38564F133904820FF0012F0413F5FAFE757BC7E153C42AC39263CA8DBB81E303BD79D52367A1EEE12A348F38BA792E2DD50C84B2391F36E07F3278E477E471D3B69DAD809EC124F2FE70DC8DA3F2FCFF3E6B169D8F454935A9E75E208A41AD5BA8DE487E465864E3AE2B575EB193FB504C547C98018264E31EE6BB30B2E53C3C6C7999D0DBC3B6DE11B7001EBDF201F6F519FE74E8958408B8C638276827391D3D7A56B3BB3920AC34C609930FF00303D09239E9FE7DE958315940C9C30E761E79FAD724AE7A5076B8C6891FCB3963919CEE6E4609FEB4BCB188286F98EE5C02771CF5F7E38AC6CCAE6D8AB2C0255946F6183938279EB8FE5DEAC046DA4007716209DA47AE31E83069A88F9AF7398D4F428AEB746C59B1D72589FC8E7D3F5AEB5ACE494491BC7D7BF3EFEFEA6AAE68EED1E751785D210935BB104124024F51DABBF5D28841952406E382467AFE3F8D1CF25D48F66BB18D6B3EA36F6CD6F75119E2D84B1C124FCBF4FA1F622B63FB2C09B2198FCBC95CE48C7E469FB56F7625416973CE24F03D9DFEA7E6A39B7B62E0B47B0F7F4CFF9C57A6C36E2328AC6461DC0C81FE7FA56B1AD6EA632C3DEC71DA47878E89A879F1C46564236EEDCA17FA1F5C8AED4C1BD5FF74467E5F9831FC3F2AB9567D04B0EB5D0CFD48EB9A8F96B3DDAC512938445C02727A8C7B7E35A0D6F333AFCCCC09CE413EDCFE952E4DAB9BC68A56D0C98F4857B356BBBC924619DC1720F5E3EA6B50DB3AC2CA3AE4F40C7F5FF003D6B2949773A141EA665DB965483E62A011C1E3923FC6AC3DB86B8DCB9F95786E491C0FD6B1D19A726C47A74236ABCAE7796C1231F98E391C8AB7676F88D78DB839E54F18EDEDD4D396FA16B4B9BB0A8ECAC54F017711DBA54B0FF00AB62C3002F3F7B20E7D7B50DD91B0B22E4229249EFB8E79A748095084600E49E78E9FE3FA1A5EA458C5BC52DE6000EEE0120F4FA7E352CAA5BCC6C9CE33C3371FE4D04BB946D55E2B812138DEB905BA74ABB15BFEF1108F980E33902872B6E66A3B1952E88F24D717D1BC52CF23027763381D335ACB6F2A2131A923A7079E320E78F418AD232BEC44A12D4C94BFBB8754DD26991985B8D918002F5E3A74E6B59AD5B7073180540008C9C8DA7AFE55B46AB89CF2A17DD1C7F8CECE0F115B413D87EEAE906C6491B1B867F2AE9E4B2F3B6296655EE18938E7D71C557B739DE19F43C2AD342D42CB553125B3485BE5C28DCB8C9E471C743CD7B4DE692AE1D6481998E3E646C9EFD3DB935A2C43EA2784E6B6867E91A5E9DA668C86E2684CAE77958D81C1C138391D326AE0D2A055506DE563838C92718047F4ACE55AFD050C16973CD7C41A5B5E4F2369D6C02E7008F6C6391E99E6BD4069C817E484E189233C8E31DB1DF150AB35D0DBEAAAC713E151A9C01229C2B2B0FE223D3A5778BA7A472170AB954E39006727DBDAB39C9CCBA749A1B686231AC822601B1D71EB9FE556A3450AA9D37303827D47D3F0ACCDD6BB965610AAAE31CF39E0639FE5491B9F2C9FE642E3DBE94EE69CA847562C38182460F1EFF00E3488DB64E57298EA483D456E9FB88E5A96D0E43C65116D195DB1C4A3B0F4ED9FA558F1800DA2AAEDEB228C6739201E99FAD7443E3479D5637BDCC6B1B2F3ACECA50A982BC70392091F856BE95118741B76D81821FB848C739FEA6B3AE9399DD806A34D9EFFE092A7C316601036A01CF638E952F83976E830E013919E7E95DCFE0D4F32BB52A8CEA528518152624A3814D5CE2801E08A071400E000A4E6801D9F634D638230188A0077F0D276A004A4FCE900102823DE8013BD34F07DA9001C63DA908A00676A0F268018683400DA2803FFFD6FB1FBD2548098A5EF4C0293A8E05318A3AF514DC60F41400BDBA5267D05200C139F7A4046718A0076297A5310514009B86EC519ED8E28280D0450031F9143F4343B5B427A9E47F1071FDB8993C795FDE1CFD734CF887B9BC411A9603309032540EDD783EA3F5F4AE2A87AF85D8E2AD481689CF00919F9473BB8E99AAB6B3B7D8840CEA8C18B7CCEA31CF07A0F7FCAB1B5D3D0EF4ED72B788819371DAA58024E51324671FFD7FA7D4E69EB1349B24936A8072BB8E081CF2338FC7F4ABA51775A1C789E9635572605E061B90463039F5CF5A642C4DBC189109FC393F967B1FCBDEBAE48F32327A8D937664DA146CE0E00EBD33CD213B9490EA7E6ECC0639C9EBD38AE6923BA2F71F1C2C1D23F2FE56ED8073F5CE6A35957ED31E02838FBD81827BD657469185EC6A40892C640C10793951CF207F234DB7993606D8325891C0E991F4F4FF00F553B9B286E5D4841121455DA318257D3D7D79A9D4068662A012DD3EE8F5FF000ACCDF97DEB10FD990468028073C8ED9C9F7F6FC2A7C37EEC6392724F183C9FE79A2C55BDDB95E7B70A70D165C671F28E9D3F0A99977C8E4AE0E08FBA2916A3AA29FD9D57662319ED94FCAA6006F5C28CA2F5201C63D68B8947444423DA8D91E664E410B9FC68973E500C39249C1C7069DFB9565A88D13308CC982474054FA1F4FA545725C0088A477E02E475E39FAD0DDC2CB42BDCB6D8C918019B81B48FD7F3FD692685CC2576800960028E0FCDF98A5622F64EE45B64594819DA173CA91D87F875A9D14895F0A010300EDEBFF00D6FCE8B10A77B05B23B3C6593E52E30319CF23F2EB56A08E5555658CF5C70BCF6EFF00851CB72A2DB4CD2B64631B2ECE9EE7273D7BF1496AD28DDBD543679FC0E7D4D4C95CE88B6DB259D1982007A9C9F94F207FFABF53532C7E63C6DB49007F74F4E3FC2A2E5E861BA3472B29E31CF4ED8FAD5ABA81DEDDDF60DEE55490319EA39FCAA933392EC5652639464EE5C67A367EEFFF00ABF314D0B2798E7660AAE31B4F1FE4607E02AD5C85234A120AE0F52707E5233D79FD6AA445BCA1F2E4739E31D7A5371B9A2916DED8994489C855C606EE073FE3532480B336CC64761D79CD416ECCA8B1EF11939C76073D73CFF4FCEA6923424328008C9FBB9E7F2FA7E545C8B154C243B609C8EBB7AF6FEA6A623EF92AB9DC411CE33C7F515570B323304A595F71C38E833EFF00E356BE552584790B9E3E9522E5206876C40B606738EBCF4FF1AB4ECD8452806323AF19C9FF001A5A858A4C8A6465C0236E7F43EDF5FCAAC6D7FDEED55C30EA39EFFF00EBFCEA958563326525948E81B803AF5381D3DAAD3261630A5783C9EFD0D558E76BB144332A7C8D9079F9B3EBFF00EAFCE9D728A236E7A1E00CF4E0FF0087E54EC8CDF98093E750187DD1D093EBFE14A80ACC390BB0739CFA1FF1AD209ECCC67B2394F19296B085B76184D8C6F6C00791F8D4DE2F493FB3A2541B46F182B9F5E0D6B771D51C8ACDBB967468D934E8C1605B1D589F9BBE7FAD4DA616368080843900E73E9FE4528CB9A5EF1D508F24343DE7C3685344B45240CC6A7F3A9340DADA2DA1504298D7F957A1269C6C8F1E5F19B6BD050B9CFB7AD40870CFE14E1400E1CD1D3B5002E451C50029E98CD19A0009E318A4C6450037F3A3B50021EB49DE801093CFA521A9010E71484F7C500349C1A43D7A500231E69B83B893DE8013145007FFD7FB172681CD480738A5C738CF1EB4008060601A771EB4CA1B8F7A53C500263D2970298077A3B75A00067BD1C9A090FC68C71D6800CF141ED40098A5C8A0063138341E45219E55F118347A95ADC025576904AE4E791DBF01FAFAD6C78FED99B4C8A60D80AC72D83E87D3E95CB5DDB647A3859F43C80C2FB63064DBB5064E4F1F8E3B73F9D5C982C566836B0200C039240CF3D3F0FCFF003E792B6C7A13492B9CF6B2EE10A0D84139CE7A9FBABDBD3FC9AB92402E1E65F9F683919DDDBF1F5FCEB6A5A1C9895AA485B7327D8210643B01CE7711FD2954016E8421C1C803048E9D6B7933CE8C7718EC5226DA790DCF3DBBF6F406919B20E139DC091CF19E0FE80573499DF18EE3907CF80E186CC85231C638EDF4FCE852C2E232A180037720E3823031D71D381E86B9F5674456C5CB7675CAAC993BB04F1C73C76FE7FAD3A09018C0008018104FFBD4F6DCDD35AE85C8A47D84A95183C9EDD4F14F877796415DB9C9C638CF23B7D6AD36DD8D927CCF427DFB8C59755E0023AF6F4A0852D1ED0CDDD78C16E9D3D7938FF26B34EEAE24A5CAC6EDDC8E186370C670338071D71FE7E952F771B7763BE718C81FD4E7FF00AD55736488A252D227CDF5C8E809E7157846049E6263A637F539CFE7E9F9D473326C55F2095C1DB8CE4F7C7AD36E2E7C9894EE2BE61C0EBCFE94D89DD5CAF3AA894AAB1240E7EEE47AD50B992469C0521411C73C373927A73D452498AFB1149310BB15BBF1D39C9FA54862CC03AF5C1C8E7BE7B7B71569686339B71D0742158BB039E08DA1463183C74EBC5490E431225DA7B0CFF9CFDE14931422F991A900FB8BBB0477C0EBF971CD3ED5A3DF1A065C67041CE4FE9EB537378C4B91267A8C9C95F980EB8F4FF3C5588914C5B830C9E3EF74E70699BAD051180EAA838C67A0EB5608713A8247299E783F5ACEC3D0CA9631E49DCA39604E00F53838C549721963C8201E0679C7419FE5544B32274DB311E5B67DB1C8ED4E90132EE079EA303BD524632BA6AC88EDCAB4281B19208C0C71C647E950881EDC2480F1BBA13CF0307B7A55A40E5257D0D711967DB804E38ED9A65B4AA438DF9F97EE9E839C66A1C4D232B8F6B7C300AA38033D319E7AFE557A34490957201E391D0F5FF1F5ACDB3433244721B2BB4E71CE3D7357A589443B998119208CFB7D3D2865D8CEF2D4B1F90291C9C81838AB92464993072C077031FE734D30B11C51A864E8480782A0E7DB14EE55F1B7E5EDC807924FF4FD6999EDB8C94868DC1CB01D09E2A360D891B7E1370F9F3903D7D28B0DD88A55C85040195CF047B7F85376BAE140E7A9247D7F21C559CA579106CDC8A3EF1C8201E30477F7A18204259CF7F4E7AFF4146A672191EC1313B540DB95C01DC74A894857DC1B81C8191D31EB9ADA2F639EA2D118DE2185A6B0C70C5F680C14607F9FEB53EA67FD1D406DC786C939207AE4E78E4F3F5ADB96F73CF6F95B1963098AD22565E78380A3AE69B60BB86E2DB973B7E9C9FCFA565AA91DF4E7CE9A3DEBC2EECDA2DB9DF921173C7B533C2C33A25BB018CA2E47E15E87D93C892D5B3A653C5363395C66A6E412AFD68030393400E19279A07414008376E273C672297A1A005ED476C6698087A75A08F7A0086E253140D26D2D8EC3A9A948F534808C312A0D2E3071400869A73DB9A40210734D118567604E5CE4E4FB638F4A00424E79A0F5A004EA3D691576A851D05001CFA51C5007FFD0FB180C1EB49DBAD4800A5A601FAD1CFAD30023A7AD1F8D002638A5EDC9A004C52F6A004E49F4A5EF48607AF028CF3C1A005CD27E34C414668018E09A5FAD0F463E872DE35B5375E1ABA5420B2AEE1BB18EE3AFE35278C4C83C3573E58EA00AE7ACFC8E8C3A77DCF0F9E3675872E47B05C81EBD7AF1B4D4AACB2ADBA9407B8C6060F1C9E3FDA3C572C8F5BD94A515A99F2078DD80E76E37707B9C81F97E7DB3566EE2D91C9205452C42FDDCE0F273F4E2B4818E221252452707CA1B4FD015EA08F71DE8071B1FCB43F2F40B9FA63AE3DBD6B6DD1E7C15D90BB3342E0C81431C90C09079E9C838E07EB51499485432A6EE47C8BC7539FA9C1FCB9AE66F53B113AE44C72E4A8CB360920600C647F4FAFAD40CD235C36554B46A42F0781CE0E39C13FE7B6317D0E9874352CCE501DDF79F1C7B5416EEE12328A31BCE3A8CF6F7AA52B5CE95D4D7898ED70371DAC599893C8C7F2A687758A4753F32FCD8C74EF9FAE08A22EF266ABB97910E50648C8C646EF9B91CFF002FF39CB5493246A4803395014E724FFF005CD4FD9634BA1601564392DF3018E4FB1F5E0E05406475859576EE5E79192723EBE9EF512D36359590F9EE63472A38E32700E4707B7E031C5645C4F2ACB2630CE40F97B0EA3F99AAD6C6641773BBC5B833333329C024FA673F4CD44BBCC6187049DE0608CA923FC2849F5224D3BD81038BBDAECCDB97701D40E3A7EA7FCE3316A33B4731B93F2809F328041078E3F5FF003CE6F98CADB1A2097B78CE58924E7938EF8FE7581FDB50DBC31095D5532DBF686C0C63F3EB49F5B1A26AD666D099848C858ED5EDB891FF00D6EF5C95CF8CB474924DF741540E582B617DCFA77E9E950A2F4253D6E8F42B178F6C4E322490F0791C76AE674BD6E2B88E031CE9202739524E48FF003CF5A39645C6A2D4F42865C2E51885DDC724F4F9BF9573D6B7C1C01D4F39018F5FFF005FE14F53A1DB5D4E9BCE46701D7200C7538C7E558ED77B98C8CCAB195DB9CF19C7EB45EE24D685BBD94B23380739C724FCA73D6B96D63C410E9F6CD24F2B283C805589EDFE22972B6633A9157369114C8E44870413DCF7FA57176FE3BD28DC3C6B34B92360668DC28CE7AF1C7FF5CD69CAC88C933B79606FB3AE371249049EA79ACA83578AF2D50C44618FCAC73EB4B94D1BB96E1758E468B03181B80207155ADA4F3AF6E26DDF2A2E3A9E4D55884EDB1B3693E0AFCBF26DEFC93D3FC6A07056E498A4C10BD7B67207E3D2A25646F177DCD277CA8E571F78004138E9FCAAB41740A00C4AA9C951B8E0E071D6938964D2AEE0CC8065890BCFD33FCE9B2382CF92BB79F9C7040E33FCAA6C0401F7042EB951D540E0F1C76ED9A7970B222962A319E093B4FBFE04D24824D0D947CB20540086F6EE714D77D90B310583107E87D2AC949103331656C618F51EC73FA734C988528D81920F6FCA9231B15660EC9BC6DCE486181DF38C5453370100CF24EC04FBE0FEB5BA673D4761595BCDCEF2171CF3D3A7FF005FF2A8BCCDB2138CE46093DB81FF00D7FCE9A4CC27B152E6291911C152431272474E7DBBE6A693989303AB6319C9C56E91C135A8CD3D195E4DE4FCAF923FE05FFD6FD6A385D84D28D8CCAD201D707181FCB150DEBA9D74EF6D0F75F0F63FB22DC2E31B003DBB556F0B5CC5369317960FCAA011D71C5765EE8F3AAC6CF53A251863DA973819A4624833918A06739F5A0093A01EBE9477CD5008734B9A004A4E7B9C52003934BF8D003493484F148069273487AD002723B52920D0014DE7D68011A86E68012909A004A5C7B5501FFFD1FB1B9E31DE82C0548077A280178F5A4C9A77003FAD2601208278A2E03BF5A053010B0042F734BC62800FC28A403583646D2073CF1D47A52939A002973E94C04E3D28A006914134015AEEDA3B9B768655DD1B8C115393918ED49A4F71C5B8EC79B6A5F0F3CDBB1259DC858867E561D3F4AF466033CD652A71378D794744CF15D63C137F63A44D793DC46C14E5829E7183FE35EABAEC5E6E8976BE9137247A0A52828EC54B11296E7CE99E9F29000EACDC74C1FCFA9A9AE8326F56700E78057AFCDD874C741F434A5B22612D5190DB0A2944214123EBFE1CFE9492B36E806F396270D827D0F53F4FC8F5EF5CAD6E7741DEE09261A62A4964CE324F23FA1C7F234C53B9260E5B20804AAF18F5CE7D3159F589D705AA352D954A28753919E8073DF803FF00D7ED51DBB31309380DBBB718F9BA75E9923F03DB9CE66CB635E15C26591B7648FBBEBD7F99AAB1B88E1E46497395C671C7E67F2AD3CD169EE6B827CC8CAC7B8918C63E61C8E7F5FE75516631E1403C2F23A64FFF00A87F2F4E62C5F36C5A99CBC720D8A464671C000F7150ACB84657E5776DFC286B72AF718D1EE9482012546703AF6FFEB73563230599B28AB92AA385E87F0EB9AB5D04DDBA949A25112633919EA73DFA525C4A0220C8DA0F38E7F8B1FE7FFD5939598F3A4AE539511E57565223EA41380DF303FD29B2C8A6762C7270410780793FE14D2B1339A4D2306E7C3B6F34E09F38A373E53B12A7D47E9FAD6B965F90800F5E4AF4E47F87EBF5A2CED730F68CE6FF00E119B1CCA05A02C7A6E43C0200FEA7F9D747246ACE7119F9980FBB9C9C8E3F4FCAADA7748576605AF870D86D3A7CBB18E7E5E707F0CFD3F3AEC2DA0F31901C8C02719F6E31FA7E5551BB33D55B439EB5BCD4ECA28E29EC48D9C02A0B2E38EC0F1D7F4AEB07971C7B0AAB64E3693D393F9F00534AE57B47A989FDA5772CC12184962BC9653C738E7FFAF5BEAC8E4B2A80DB38201E3152A290DD47A58E757461342A6E5774AED9E4707A74F5E00ADD214C5855C297E78DD9192067D78155E862F99DEE6249A45BC81B64288402AAC46720FF00FAFDB35AC7CB1233ED20E00662795E9C8FCA9B6D9AA9D8A56FA4345E48F3C904E368380493FF00D7EB5A31B8D888EBF33741DC608FF0ACEDDCD6357B966D10C4ADE5A0607AF1EBF8D3239766E254021B8C7AD4BF23A1548BB9724DC5724055FA74F4AABF69CC8473F74601F61EB51C97DCB55168391E446C31524330E791F97E7F95441D5C82BC0DC4E7AFF9E73F9D1A7734526CBE2E4BAB0C1DED27CDFA64807EB549B6EF25976B8CAEE1D7391CD1A15CCD742EABBF9C7CC2A4B0CAF6C75231F89AAA2759244C210BC1C77EB51A93D89DE4D90310AA79071F5E01FCFAD55323ED646C6E2480318EC38FD6A82FB921909DACC78C633FCAA169361C08CE3195CFD4F5FCA95C84FB95A4FF00548991B7A901B8CFA535DC811C6C70064F539CF1FE35B44E4A8D3B0C65DAE406E54E3A741EA7F0A4690932315E42FE783FE1C569A98CF6154B011C648C761823B7A7E5F9D35F25E24600AE783C8E8C7B7BE056D157386698FB740F316390430C8F6C715E91E1FF000B5949636F7B2479924525C1E54E4FA5395069EA5C314A08D2F065ACB6F62DE682BB88C67E95D4DBC31C51AA28C05E2B74B951C93A9ED5938C62941F939E29198F1D314D140120F5A4CF14C046601829EA78A46552E188F7FD2801C6933489173CE293BE68010918C014D2463DE82842C376290918CF7A003349F5A004A3BD002640A4CD000DC76A1A801B9349F8D3B927FFD2FB1B39A3A76E2A405C8F4A4A60266971CE334C007B51F8D002F5EB450003A75A00C77A000D1D280129714009D3DA8EBD695C029A79A2E0213D283D7DA8181191CD20C1E32680109E05291C520B156E916481D1867208E7E952483E5E450D5C67CEDADDB9B7D4AE95D72AB96DA7A3649E3AFA01FA7E1ABE3B805B7896F47CCA245073C7CD9C1E7DC1C73C678F4AC52B7C46B648E15C3068064175623EADD3AFAF53EBC7E21D232892255C919247039238F5F7FE5ED5CB3BA676539218B1F132ED58D9700865390B8F6CF626A3223225618219D7A001718E879E6B3D6E8EA5A3D0D0899818989C647CB90321BB8FCB1FE73886DB0244DC5D542E0ED1C0F97F9F5FD6A629595CDF646AC40790A4371DCFF083DFEB4C88EF856529CB9393E871EBD7F4E69BD2F62D752DA0632719184E4631C1073FA1A8C1027254038048F9BA9A49ED729B4AC4AAC5727AB02A18E78EFC9F4E86AA6E2AF924E4B03BBAFAE31EFF00E34D90E7BD8B170ED87C3807D7AE073FD0D676A170C90BB6CE0A853B46430C8EFF008FE5548CE72DC64B38558DF20066E9DFB7F55CFE3ED58B25E08FCB250315C6011D724FF8D3B99455E2AE6892CF24C77F2A07FB5D41E7F5AC9FED111B928836328CE072793C75F6F5A7EF1B72272573711CCA62DAC0700B00338247AFF9FE758A9A93FDA95990B023761C678EFCF53FD697907B15A1B6B2158D99B1C30E47739EDF8D436F3AECF2E41C17C7CA33C64FF5FEBED935DC3937376DDD4B29070B8CE3A95EBF9F4AA51DDB47300AC995423A671D47F8D526D15ECE2EDA97E591A38549E08255BD7191FE18FC7EB4F49EDEEE38E2650818E303907D4E7B71FE7AD52958895186A470DC666215D81382723BF38E3F0A9CAA40ED22C058C6701B9386EB9C7D4D1CDB5C2346C461C88A3C9C8CE3D7A01FE1F8D4064CA88D548639C01F8E3FA7D734AEF5B14E90ACFB8B2E78DC30704F7FE550C936D470A98C9F5F7FF3F855264B8A77D4958C9940A783CFB639FF000AAA6F02CA8C9D06320F183B8F6FC454C9993A6B426F30812EC7E060FD4E7154C4AA22DDFC59E31F4FFEB1FF00EB536AC27168B6652CC487CE13839C64F7AA466911B2A8A06C183D41E06011F8FE94AE09B3462958B08C9200241C8033FE4D50B79640C984C61812467804F07353645466D1B05D98C9B9B83CFE87FC4555129CBA9232DD3F3C63F97E549F91D2AAF72CE59A5505B000247638E7FC0D307CCF1CC40DACB8009E7A13FC89ACF5354F6240EC11FE638192013DB247F5A841721E32A739DD8DA79FF279A351B7B8B24FC29DDF305E0F6C0031FCA98E4AB160D8023C6578201F5F4E39A563394B62265DCB12EEC61B807A1E48FF003F4A473E614F4519C639E847F5FD2B68BB1C6F5B0C25BCB76C73C838EDFE1402EA5D8E01C9C7E39C9FD2B6B18547BD8B112B34D1281D7819E727DBF3AB5A540F36A96A9D72791DC839FF00E27F5AE88459CB51D923D97408847A45A0008C460FCD57ED2311DBA2F650315D12699C712D28F6E69CA3DF9A8DC761700FD2940C8EB458603823DA8C71400EC52E2800C7BD07AD003696A44212450DD680236F5A711C63140C6375A5C738A0069E0E0D07DF9A004ED411C6680136F39A5FC2801941E2801307D69D9AA11FFFD3FB18F3F4A2A403B75A31CF340000734629DC0314B45C03BD1DE9809DE94E00A004A5C500341254120827B51DF39340067146290099E694800520233BB7637714B85DFB7B8A0A0F6CD04647A50021FAD2D0046FEE734A45311E61F122C028875005707E539038C0E3F99AEABC5DA68D43C3B751050481E6723D39ACA6BDEBDF42E1ADAE7CF52828EADE68DC4038DDD0E0FF009CFD7F175EC45660AE08C6E0DC30278E79FF003F76B9EAC53D7A1D5464B429722263E72E739041E4F24FE7C81F4349B82C79DC492D82307D31CFE63AFA56124D3DCF42C9DCB28E2395999B785E080DC8C6739FFBEB1CFF0074D425CC72600601176820723AE31F8FF5F5A2D645A5B5CD68646D8997E40E78E0F63C74E9CE6ABC040D89D542F3942327827F9838EB511D6E6B7469F055F33018192381D3F9F43FE4D1165B78C8CF419C90463FFAC3F5A16AD03498D75744F2DA5DBB71F7B823A7A7B739A7C8A9E4647CA41DDC724FE3FA537A98DD6C739AC079E378EDA40AE4905805E3A9FA1FBB8E7D6B5A5B74524956382578CF43C7E3D2A968270E6DCE167D31D823B5E4A5801F75873CF1F4AEA2EED86EC0018AA7009383CFA8C67A8E99A6A65C29D91CD43A6948F9BC9031603048EA0F183FF0215B51AA7D9DC123709366E393CFF5C0FC307DB354E6813D4A90E9C659550DFCA0BF54201C707038E78C77AD34B64264226080F20053951C8F7CF5F7F6CD1CEB4374D683134705377DB98BB31182A0F278CFEA3F33F86A5AC64A22332F5F99BA739E9FA9AA52DCD3DDD4CE7D37508B0609D5D801818C13EBF5C0C63F1AE9A18E3859C8DAC3685F7031918F7C13FD68B8FDD396B67D46D5A33728428CE5539039C0E7FCE6BACFB3DA8923CC7D86001D78E98F4A0869184D793B2B15B777128039038C9EB835D03416CD190B19D8BC11D09181DF9F5C7D7F3A9690DC6FB1CC14D42510B3A818661F39E4743CFA703B7A1AE99EDE3CA14C601C0518C03C9FE60F7C73EF45EC0A1DCE5A58F516B7E5C70477EE471F81F5EC2BA136AB242241C138C00339ED8FD0FE9EB4F9AC2715AD91CABA5E970C6352A0004938EFCF7EDC7E55D0BDA032E1BF87903B7AFF002F4A9E6B993BAB1C8345A8F96557CB6CB7F08E831E9D3B0EDF85752B6C23208880C723DF8FFF0059F5E29B664DDCE71EDF54B695E6959644521CC6A31939CF273C8C63F0AECA3B346126E44E4E3763200C81DFB6074F434262E46CC6D3A48A5489502EDE57054718EBC76EDEBD7AD5BFB29B7740B1ED5EE40F7F4FC4FE9E829377172B56B9655321982F4E48E3823391D7D453D102248C140CF1ED8E71CF7E0D24812BDC9B6056CA8DCC547CC064FA7F2FAD23E76BB1058907927F1E7D7FFAD53667526432C38E550839E7E53FE7FF00AF4922F3C0E73C64638CF7FF003D69599321AEADB8120EE0A0FDDEFD3FFAD50152CEC0850785EA38E3F9E79FAD5A8D8872B8FCF3197E319F638A423E7048C0EC3A5558CF6D87618FCA17BE4E4F5EBEF4E0A4804A80D9C1CE3DFF00A1ADACEFA1C956495CE93C2166D3F88E3C22911A02CC7F4AE8FC056052DA4B99065D8000E3A8C7F8F15DD1565A9E74E5CD63D0221840339A55C0C0A864EC49E9CF3483A8345C2E48060F06907268B80EFAD03D85002D27703D6980B9CE0D1DE8013BD21E4E7A5480BC62908CD0021F634878C11400DF7CD388EF400DE334639A0043D714B8E6801BDBAD04726801A714A450037228DA2981FFD4FB181CD2E3DA801282280139A77140C323D68C5001DE928101A2800E7D6909C0A061474A003231D693249E94001F4A68653218F70DC0648A0916973ED416251839E4628013A1CF6A0D048D6E68228B015AEA312DBBC6C786054E7D315338E3149EAAC52763E72F13E9EF63AE5C5A97550AC4A965CE3A91F5C83FA8AEE3E23E8EA6E63D411783CC800CF23033F91FD05632DACCB8BB6C79036F4291B9C9DE381C12481D79F50453E60772A3273B48202E320B004F07D40C62B9A515A9DF4A7715490EE0B80CC79F9325FD463A631EE3DEA0456092315CE4004E060F20F3F8211EB8C0F7AC9F4D4EA8C93DCBB0CB28688B93801B71C1C807AFB1C003B5528A40B288DA2E62F6CF002E7F97EBF5A51B585CC6F2CB210EE5D548C75E01F6CFFC08FAD508A5251B0A776E0B9E339C8193CE060E338E9CE3A5116AE5A35D1CC8570428393CFA8238FCCD5612AAED217A8F5E9C9F7E7AD3BA26E4EDB821F98E776075E3A8E7FF001E3F87BD42CD8660EA586E07953CE303D7F1A2E1719346D2E7E62038C640EBD78FC8FEBED53821D5F744AD8FF6338381F9F19FCC7E25F6358BB186617283636EF9C8E33DCE33D38E76FF003AD796DC6D1BA35C07C9DCBBB81D3EA09C73EC454357B8ED7D8CB01F7B92C5C49CAF56DDF311F8F38FCC673CD6A25B615B7820375C0249018F18EE7A1FC07BE5AB29585CB2452404281E63A951805871C8F5FF001AD55B661B4F96838EA01C64E3EBEBDFD3D78AAE7F75B4434CCE5B8962B8979DBD7208E9C0E3F223FC0D5FFB282C5C4688C48276E47A0EDDB81EB54987BC574924053329DA14E319E30719FE67B54C2030372B9E3249EC768CFEA055D9073322F32E178795810C171F43FF00D63FFEAE2AC2DB3AA329419DDD76904E7B75F614EE815EC279C54C61980057907A9C1E7F2E3353ADBB79AA7C855F941F957380463AF7FEB4AE82376F523F36501003C9E188F503FF00D5D7DAAE8B770AA8C1033167E3E98FF22A1BBDEE5F2BEE52CCECC416EBC8CFE5CFA74357BC84224E118ED231CFD077F4DBF99A574AC270932B245BD32586E1863EDFE1DFF3AB851739DAB850727381C671F4E01A9B8D52B1147180AA4F500641C804639FA5292C2356E09C86E727BF7F6E47AD1A9B2762399031642C71B704FBE07F8523E41CA9521867AF5C7FFA8D4DB6B1332B79A11C160792063FBB81CFF8D291808C48C1C1624638E307F2354B4B98A761EC722442A4A900633F87F9FA0A85970AC36F00E0E7D8FA531B624AC03E438E5891939C67F9D3481B83678C7E5D3FC7F434F94CF999217E4C81718C70C4FEBF8E7F23503B8F224046E2DC1CE7AFF9C7E42ACA7343C61995403D8F39FF003DC520C2B380C47006EE7FCF735718B673CAA13DA23CD3411C69F7DC29008E78E7B7D47E15D4F81F496BABD17330F9223BB927939FFF005FE75D708DB73CFA95398F45D16CFEC7A7456E32BB5467F3CD6946B8181C0ADA52B9824283CD3874F7AC864A31C5340E38C5003F3E9480EEEB5403F3EF471400BF8E6939CE3BD048ECF19A6E7B50007A75A1BAD0509CE7AD140077A38A900278EB4D238A003B8A31CD50099E695850034F5A5238A90194BDFA500252ED1E94EE07FFD5FB186280295C05EB471D8D17013228028B8073EB47D28B805145C0404E3918A418E28B806E04D29E9C51700E0D2018EF45C0630638DBD8F34FE33D3AD17013819C1A38E38A2E02E452647A5328319349C50004507141234D0680187141A7A0EC62788F4D4D4745B880AFCC549041E7B7F856BB8050F4FC6B26AE33E5AD46D5AD247865D8AD1E4018071D8F18CF18F6EC6BB2F885A58B3D6649122256E0999776703046EED8FEEFE0A2B3DCD69CEC79CFFCF425C1DC48C8C647F918FC8D3A7120CABA0215F6F24F38EA3EBCB7F5C6398940EBA73E61AAC4480E4907D0F4E39C1FC3EBC8F6C42AC8A587C98E318E87A1FC39E9F874033583496C6D165F8080A1830238CB7AFCA08F6FE21C8FEF63B1C565970818ED0F9CE4641C82791C7FB27F23E950958D2E6925D22C7182C64EA0803D4AFF00439FC2AA452B165FDE2FFC0BD7A761D4E4FE62A2C245F122AB024A9391D7B8C93FAF7AAF1B304084AEC56E415FBA3183915A26366A4720E72771EFC753D3D3DEABC2C4C3B986D1B491D0E080074C63F4A4BCC66A44D1F962442555241D0F5F4EFF00E79AAAAFCE408C7CC4A8F5C8E98A52B1A2958B63CB0D82DC823070063D7F5A4C8F2DDB7A12010BF4E38FCCD2B753452B928455C6EC20FE2E99F623AF1C9FFBE4F71C304E582ECDA029CF03BFAFB13C7E429F4B09C490222FCAC9D4F2481D0939E9F4F6A119806542154E70476E7039FA1AA06481226C2E40CF5C9C9E69002E58230C0E83B7D3A504DBB8EFB3A1DEE4032640391DFA7E5D4E7DBF1A031C05240FE139EA0EDEFEDD7F3A65E88708D37E0A851C8200C71FE1C8C7D6824F240DFD4E07D0823D78E7F3A06A37DB424403319D8467A93DFD87A734C5326EDBBB39C7FC0B923FA76F5ACDD812638463076FDF3939F5EC314A640ADBFE5241E0E7B13CFEB8FCE9A572AF610E328CB8DB8C139C9C7391F91351332F2ABC8C72071D987F3DD9FC7D3956EC29495C889211030E147A918C8E71F993485B7ABB0E4639CFD08FE46A969B9327D86C8488CB8007F10C0C8040E78FC0544F2803EF600FBC71C1E31FCE97A10D8D719524739F947240C7007E1814C3B769F9B2071D70383FFD7146DB99318C18B3038DFCA8049E9919FE7C5319B21C745276E73C2F0307E9C735427214B3100851CE029391D73D7D7B7EB51B940EE768CF240000DB9C75FC8D5A899B90B9F90153C74C9F4181FD69ABB595D554E588C67D306B5E5688948B30C4F7332C01725987193DDB9FCBA5741E12B482E757F31D4EC423E6382324FF008D74D3471D4AB63D23C3BA6A69FA7470900BE3E739E339AD780008A001C569739916801E94800A9B143B19071D4D2F4A40283C0E940C5003875A426A807520FA5003C1C9CE79A68A420E41E2909C8A00767D79A6D031D49C500039A0714800F43CD1914007BE6931C5300278A5E3D2801A718A776A403391C52900F20500373EF4BF2FB53B81FFD6FB1891EA29A3BE71EDED5201F852D3001F9D183DA98064679A43823A5002679EA28233C8029001228E3140075F6A38CFA53002D8A69233400B9E281CF6FD6800EFC1CD1D050021238A08EB400647B1A42307A014006E05B6E4668C8F4028010919A43F7A801BF88A38F6A0A23760012700554D46E85B5B3C9D48E9D3AD4F301E73F1224B69A38232E7CD590E08C74E33C639EDF9D2F886CDAF6392578C031C588D8F20127AFD7A7E54ACD13CC7905DC3901C649C719CF1803AFB8E7D8E07AD4D2E5D4EE0A14E519B0339C30C93D7F1CE39FCE1B3485D6C66A90B36E5C1476C2903AE79F41C723F3EDD2A4756F33E6F2D896248D8083F74FE44E7A7A8AE794763AE1364210AA021CE54B70738CFCA327D075E3E9DC53FEEE5818F0F961900AFDEC7F41F9FE3492DCDB998A5C9CEE56F986D1D413CE481D39EFF009714CDAC2421187C8063391C60AE33DB9E3E8738A9B2254DA2DC432A3CC05C824920FDE03AF4F7C541013B9632B104E8B81DC60F3EB9C824FF00B34B94D55446A272CDC8E48C0CFD076F5EBED55D59F6090B23285E98CF0148FAF56F5EDF4ACECCBB9A30B15E5F38E319F5E9C71EAA7F3AAF1BBB12A483823393D4F03D7EBEFD3D392F619A4AD208CE06D38EFD07AF6F71F8FE755E07255647248380C7924F5CF4EF8E7F13EB8A572D685F88A98C2E580EC318CFCA3AFEB8FA54519F2BCBDCA09C8EC304E0607D3AFE74EC55CD062763B3104E76B211C0200C8F61906AA99F6F21D48D9920E7E51D3FC4F3EB47316EC8B99CB90A14E3E6DA01C9040EDF8FE951AB1276301B93076E7183B7A7F9F434C5CC897943BB1C06C1C9CE7E51D78F7A87E5C326FEA36B1CE7D07AFB1FF27977127D8924919CB068F710C4B0071C1CF7EDD6A06316E0A5012300E063A93FAE307F1A77453F32CFCC1B1801B0071F87EBD78FF2226753C7CDCE1B20743BB19F7E58FF00FAF9ACEDDC2FD87B3131F20F4CE71D33D0FE78FCAA091C10CD80060E403F7463273CD3D7A0BD452589C8501C1E083C7527F9A9FCBDF155CB80DF367A8CFA75CF1F4E47EBC5249F42135D452C502719073C67A8C0FF001FFF005D43E605450C392412338E475F6E8DDFD074AD12EE294C748DC1570A4A83900F03201CFE66A9C8E8C393FC3CE3DF1FE7F1AA6AE4B99212C3006DF9CF42391CF7FCFF00CE2ABEF7120C8EA776475EBD3F0C9A6A36317324918EF326E51B8FB676E781509C9591828CF0467B9C0033F977F5A6912D8E425F660803A64FBF4C7EBF9D4440C6501DAB920E3190463FC3F3AD519364F13398C720EF503EBFE1C914803280028EBB1474393BBFA83F98AD2E9B3393D343ACD023B8B7B29EE9416574DC87A85231823D3AD6D786ECEF21D2A1DC0485949CF7C10703F5AE8B72A3956AF53B2D1EFD6EEC619D4902450C33D791DEB9DD15A4B3B9B9D3E48722DD83A739C82791468CBA94EDAA3BB5607F1AAD6B2F9B0A920E4819349AB109A7B1701A062900F045373ED400F273D29323B52B00E069063B51601D9F414DCF34805CD266801777B52715403B23349C62800C8A4CE48152019C9A4392471D2801FBA9280173EF4D3DA801C4E293F0FD6800CF1498E7AD0027CB494EC07FFFD7FB14714983EB5202D2641E94C0538A0E29809DA939C7228017A1A427340084F14114009D4734B4009DE949E680100E714023AD001F4E94607BE28014F233DE9B4001C1E0D264678A000E29A4E6800279269A4F1400D73B579359DAA5EADADA48D900E3383C7419FE54C2E606A97ED73A9C36EA8C76BE719073803D7EB59F69E6BCE9E73894F941BE6FF68E78EDD01ED56A226C99D22951E3EA7A64B7BFFF00AC1F5C53E48D371126E397E830C4E3AF07F1FCE8DC87E478D788AC869DABDC5B6DC26E0CA3E5E07B71D39EBCF7AE8BC77A630B8FB4C2F2465546E5651CF4E323A0E17F2AE2AB74F43B2935D4E14C61C2A796086F98AB85E4E7A7D79C75ED5113E4CB229B9080901320051C93BBBF75FC73C54A9DF737E47D040A7C943E5E241C751D40F5EB93923A7A7A53C3A347FEB1C63271CF4C039F5CF041A971BEC34EDB91C89CB32C219957EF1C6D2467D339EE7E95210370DD260B138C0E7EEE1B83C7500FBF7E0D459A3456EA576DEA4BF97B82007038F94F4CFE181835216879024F9B0E01CF4E8791DF9E79FEE9A39EDD0971EC48B249F3131812EEE42918C96E47E9D3DFE950BA062F82E8588E7F01FAF07F303BF06853BAD8BEBBB6AB45F38202A100738E9FA11EFCE7B551FB42A2EF646C9566C91F78B020FE647D78FA65385F61C67DCD783E58942A2AAB1C01B872A4E0F4E830C3FF00D554E3B98198A292C0B928460E0066E38EDC01FF00D6ACECCD398D6471BA31B2362085C6077233C7E06B321BB40524C36CF978C7079E47E649CFBD3E52B9D1B3B9C29000DAA485F973CFF8722B345D44A06E43B9883C15F7DD9E38C0CFE47D395640E48DA5DF8C641073905B83C9038EDD3F5359A971852CE8C4A92FC7E9FCFF003A770F69634FCC6E85958E5338C0E9CF3E9D4FE75416EA3230A0E3A13B873F2939E47B53D7A829DBA9A2B21054A901B395E4104647F41FA5660BA8B2CCAA467E6041E3A8EF8E3803F1E2A5FA03A9DCD1F33695CB8F2C0EA42FB1FE62B33ED8870C579EA09F6FC3FCFE79AB5D0948D0DD218F2ECA1C280400085C9FF0E3BFB5664D7C0472622C1E7018F41DFF004FD7D2882265365F92462C43BA92415E3D7FC7079FAFB563BDC34D71811F058055CE0F5EBE993C03CF6CFAD3891CEFA971A672B856538F986719FC8FB67F2F7AAB9E1731F400920827007F4E3F2AA6C4E5D89159BA798ADB4642F1938CE07E869C88DBCEE5E49CB7232793919C7B9A094B54280CB207DDD4E41C0E3E6F4FCA9532C8A7CA50981927A8C8CE3FF1E3F88F6A57634B4421563F7DC6432E4AF6F5E3FCFA52176D80E1949C0C7BED073F91CFD456A9596A43620EADF74FCBC7A01D7FC3F2A5019A5DEEA2507271D8E49E07AE31C1F4A7195C9E56549CDC4D2DB2C12851E72348C7FBBBBF4E9DF1FAD75FA46893C9E1EBCBCB8401FCB270467701D3B71D056D18B4EE4B927B1E8B61088AD42AC4718DCB81D78A6E9576AFA6DA39E0B2051DFB5745EEAC73493457BE22CF56B2BB23025CC6E00E0F353EAC44F632C31A1276FCB91CE73CD427D8D28BBAB3346D6E560BDF249C0700824723AF7FC0D6543766F3438EEA32048980C5B83919E3F535AAB3DCC6AC1C1DCED10865C8354AC2ED2EAD63951832951D3E952E36127734314D0723D2A06C7739C1A4EA79340C781CD20C83D680141C8A41F4A007014631DE801296800E6819A003F0A0F152026401D28A005CD20E680107BD041F5A0009E69A73D31400E18C67348AA7BF140099A7647A53D00FFFD0FB10734BD2800E49E68CF1D2800391DE8340099E70697BD002138A69CFAD0029A0F4A004FC68271DE80038C74A4DC49EA28000D81D290B719A602E4D42F32A6727A75A56604AC7DAB366D402103CC507D0D3B017CB85F6FC2B06E75175C14CE4F4C9E9EF4589B9AEF3AA8CE78AE726BB9A4F2E433EC5EE49C0AAE56272B6E6ADC5F6C8D897EE08183CE4E076F5EBE95CDB3F9D744C6DC0E7907B1FF1E69D869DF60D5271736CCB2124C8FE59E7241C01C7AF06ABDFF981AD6105B2D20F940E1C71C74E9D3F0A692D2E43BBBEA2D85C2CCF73B46EC4BB0618718000FD3149A62011DC48996335C317639C67D40EC3008C9EF4AD748B56572F209C4259C2EF9392A3A038CFD33EF4904421B40249017241073D7FC7FC29FA0B96C64EB3035CC7711632B24255723D71F87F11ED5667962965D88C5C15EC475C71594A1CC3E668F129619E3B992DE70A244628D83D3DFF001FAF5CFD2B57C6512596B497B6E0059800C7D4E7AE3B602FFE3A2BCF9D39AE87AB46AA99CDB8B8B5DC6554405C8059860E0E3F007273F51EB5723F2E50B1483E7504B26ECE371C6467A8C67FCE318F3491D32822989A462C77229FBA707952307B7B371F4CF6CD54B8B678E6388D7182AC4BE72790074E39F5FAF5AB53BEE8C1D37D0D10ECCDF3BA0E373F2B8C972319FC48FCFE959493156D92460B6D2A41E4705B8E9E847E46AAD7E836BB1A5F398FAE5DCE154724F1E9EB939FC7DAA04B943197D81983316CBF7181CF6EE3231DA869859A2550588DB322BE76A943D3827F420FBF029A5C79C1D42B018DB97E79C0C7EA01F73536923456EA06195A33B5E30CC490060639271FA6DCFBFE35209635882F9681831738200C614F4C0C0C807EA3D4D17627144312C8AE1D64CAA6323B838DDFCB3C7F8D5F001910EC8DBAB72E0FCDCF5C7E193FE3473B27D9DF6331629418C2C87CE553B40C0048EDF539C1FA7BD687948CB1AF971BE33F3230C9C639C7AF534F998946E67A2CEE326E9BE65CFCA7248EC7EA7E53ED9CF6AD1C2856FBA33C83B8606064E33D860E3D38A39983A654DB760A3B5D390ADC633CF7C7FE3DFA1F4ABE10A4ABB5631B5B04E41C15191CF5FE2CE7D0FE51CCFA89532A44920DA44EF22E37E3191D4FE9C01F539F6ABE17649BB6A750C3EEF0430DC39ED9E94F9DF42F9115C42E59479AEDF2F1D32738CFF3FD3E956C48548C155C1049E33819C7F3FF003C60E664A858AB1C476FEF24919B703C9FBC09DD9EBE9FD6A5123795B76A7008C023E5078FE6719FA7A72AF25B1561DB0AC78F3188181923EF70324FA9DA6A369C96214A67AE582804900EE1FCA9252172F72461831E646FBC40033C0C8F7FF685546B9938FDE0200183C724723BFF009C7B569662B762632619499383F8E4F4AAD994BE414C9F994FA9C7FF00B5F97BD3D88B5F62C79E0F95B0B63A107B0E01CFE031F426A4D3ECDE5D82460536601C807200CE3EA4E6AB9CA5063D124908660E1B76CCE3BFD3EBCFD7F0C5B998450150CA0018C8E33F30C0EB8E73FA5473DF4348D2D2EC2CEDD750D5E0D390BA995B24E3380003DFB707F95749E05D3DAE5A6D6997E76731C64F4DA31923D8E0907D0D74D1835AC8E59BB688ED3ECC0E9B3C4B0109B1C11D3DC7EB56E4B8886E4E318E76F3DEBA4E4A7B9434195FFB0A088233B229507D7938FE553787DD21B7B88548091DC38009E71B891FCE9FA1B548E9A16E2370CCAE5002403CF3DEA781A52586338F94678E0F4A2CBA1CB2BA9187A7A4D637977612A2812932A05EE0EEEDF8D5AD4C18B55B2D4721503143C718E78FD6AAFDCECF7671B225D0AE1E1125A9529E5F20118E327FC2A29498BC48A780B2478FA9047F515AFC48F3A49C59D44378ACA3240FAF6E9D7F3AE62EECEFA3BF8EE6CEE9E384E0320E84F38A98C15C6E763B40C2B9FB6D4AEA29025CA161D7701F954B8762F991D1039AAD05DC72EDC303DAA2D61DCB6339A686E3008CD20B8EEBD281C1C5002FF2A4049FAD050A3EB49400B9C9FA5009CD4809CFA52E7A500007340381CD001DB9A5C9CF268013146680168E7D4500266931405CFFD1FB168ED400981DE8E8703AD001CFAD276EA2801C6A369147523F31400E3D7354A6BD863EAD8E33D7FCFA8F7A622D13822B1A5D4D88023070C31D39E4E075A7CA2E635DA451DC74CD729F6F91E50269769230141EBD7FC29F2F7173763A192EB61F90678CF0327B7F8D71573AAC0B72F0DB87BC91095789002149F539F97A67FC8A568F729733E875171A8B63F743711918078E3DFDEB91BAB8B8BAB62F73A94564194E52300B28C0FE23C13CF06A94189F2AEA6E5D5E3247FBF758E3E8323AFCBD2B9417961E6B4B696AF7D7AE4804A64E7033F31381C9AD153643A9146CCD3CF346C2DEDD14601F3A6DC31C71C77F7AC67B1919126D5B5103CC27721C20C63B73DBBF3F95572456ECCD54D3E1242D7FBE4371AC246A80E024007041CFCC7A80060D173A469B6E7ED5241F696271F37CE49E73D4FA669A8444A736FB0FB76D91992379664ED2CBB82923B0F4FF00F57AD4F7C4AC76912C002B48B80AA06383E871D70393D48A4CA5272F8892D94A5C5C20036228E7664924027B77C7AFA7E2E81373DCCCBC2BB9078E72A54003E801F5A8657C3B15EFFF0079A8E9EA028258BFDD208F93AE3B7271F88A6C81A4D5A1564C8B743260A81D7B67A13C838F4268E97262B52E699BFEC00AB0C0240186E39C11EBD40FE751698EDFD989BE031A92E44457819EF8F4ED49E9B1BCEC91A06309691BB289081FC3CF4C7E7D69E91C8D07CEE076E3A6063939FA1EBE86A4CA252981327FA966C2EEE03741CD68162278F10B30209276E0761FD6A8BBA3CF3C61A71BAD367781BC99321803BB865072D81F53D3835D5EBB6715C6993FEE86F4E7057049FC3159CE1CC9EA5465C8D9E11617123DB463CEF2E42B900EE3B707033C73CF6F7A6BDADD69FAB496C130CE331611FA823D091DBA679CFD73E5D5872BDCF628545248D263F6885D659490146412DF2F20800907827031E8D8E69B6DE70862062D858F657E854ED273F4CFF9C8E7715DCEC48A373A7C81DA5593CC524A93BDD8647CBC0C7AB0FAFE1CEF6D7F9D4C63E604AFCAE477C0F6E318CE3F1C50B4EA66E9D9DD1C928789D58DDB8DEC083BDCEEC2A853D31D074C74E6BA4BCB0F31F745904329006FC13C29C7D40C0ABE7F31389CDADC047DE26660BC1C31C9E3191C75240C5684D6324782CA024A3CC180E00C8CFAF1C93F953E644721556728AA824CB141966908FE223B8F523F0A55864D8CA0A804EC04F9830770C7F261F53ED426989A2C7DA57CC2CB236D3807249DC30471C7520FE3F8D554497239C16E581F307380149FCC9FF80FB0A86917144FBD36C4770DE01CE1C90C003FECE0727F1155C43302D22BED45036AFEF320E0039FA03CD2D0B48B9F695DD9320E49D8C1D58AEE2A0678E9C63FFD7C55F2A5398C5D6F28FC619F6B2E4938E3A7CD9C7B9A3413896D6E15CC7FBDDA5768DBE62E3EBF77D88E3B544D6D2F9832EC3962AC1DC9EA4FA73C83CFA1F6A6DC7B10D6A3E5B888C2C41CF0181240CE71CF43D315125ABB615EE43738203BF238C7F0FBD4F32EC5B8E837ED28649D8EC65DDB1712F5040181C75EB93ED52AC383CCCC7730FF00968E7610719FBBFE73ED4D7A05AE30DD09234CE18B1E373A8E70727A7D7156A28C8DA9BF6E0E30B2392C30548CEDF5E47B555FC85CA40BE6C8ED1344B955DA46E1CF53C71E9CF5ED5B90D83BBB6F95D5465831661C72338C7BFE1C6719A2E371B9910DBB79806C53D79240031CE7A7F9E6BAB8E18214110724B1DB92E77642F7E3EBF951A0E30EE65DB698A8A1A68C00A776CCAE46480074E7A77F5AD279C31123484061D9CE41C1CF6F519A7743E448AF2B24790A89B0052BB587A37F5DBF9D54BA946033CBB785CED63D7A01D3D76FEBE9468269197AA4F2BCB0DADB42B24923AC6554A640391F4E0818A8F426379E31B79B3E7A4672B8271BB1BFA6DE33C11E9815D5428F33BB47355A8A0AC7B5E87A50B5D2A0B37548D234014274E9EB5A76D1BF92865738DA02EDF7E95E834AD64795ED1B776471BDB448BB76B96E00FC78A798A1883246DF3139008E9CD212650D346DD4350F34041E68703DF1FE4D5956896E4C8E872C3EBCF4CFE5492B1AF3A7B96FCED9398BE672179C7B77A606906F08AA36F19CF6A71224E233545926B3910203E5FCC99C601193D3F0ABBB2765C3ED21C10DC0EBC9FEB43571C2A28F43175194CF6567791AF3C6F0700A8EF8FC69969149268F7363700F990B32FBE0838FD056B061563D51BEAC278108382CB9CFA120FF008D50D1EE1A6D35324875F90827A607D2892EC73295F72D5BAEC9251281B98ED39E78A5BB90243BC2648EA00E78350F4298F134627542C23909E31F4EB583A9CA23D574D6DE30D204620FBFEB42D4D214D3DCEAC5F792079B8C0FE2FF001AE7F59BF7B3484A2AE646085588C107FF00AFC55285CCA53B1D7C172928465392DE9CD73511BA85BED36FC2E3E65CD0E16129DCEB477E95976FAA472AAFCCA0E7041ED59B8F635E646A739E94C491245054F07BD4D9A0B92520381EB458A14E003C526720F6A005C1C9C8E94D0D9C8F4A901C460669A0927DBD280140F539A07D6801727FBB4668013F0A3F1A047FFFD2FB08B1031D7E958971A8FC8EEAE230ABFC471F5FD29D89E646A49731A1197504FF00B55C9DE6A908DB2B4B2CFB1B20C4AC40C104E7F3A2C17BEC6FDC6A2537041927A01C9E95C98BDBABAD3FED519DB014C8232C7AFA11EEFDAB4E51B7CBB9B126A4AEA2692786156C302CFD8E3DBFCF1EB5CA695A7C377A88BADBFB9453B4CB2B33393D0819C0EBD00CF038A39495283EA6DB6B7A789957ED2B2B12136C6BBC92067D38E0F7E6AD91E4F091AC5BB3CE3181C81D7D3807E94DAEC0A51281B9D4A668FC8B68E188E0BC933E18A8009E07D7A93F855B366A59525C80013B73C1C11C9E3AFD31425DC894FB239EBF4B479E3FB65FCB35CAE310ABE1091BBA803DBD79A9E048C788AF5C05091A05209C7518F41C81F87BD384AEF634719455CCC93562CE9636D09B3529F7805F9460720738190DD8F27A74A496EA193C4CD1A1CB4207DC246DCF00FDDF4008DBDFB574A4BB1C1294A4F5668DBE856B03196691667CE30C01F98E339E3DBDAB65258D94B06E8DEA3A83DFD3F1AC5C8DA316FA1025ADB2473843B5080A11481D703818C0A7BCBE5438F333217CE323231DBA7B1FD3D6A1DCD397C8C2D549BBD6AC2CC18CEC2656000EA395EBCF7C7E74D8249EE3C437171E4ABC50A88F0ECB8E84E71FA679EBED52B999D2E0A112CDE5C2CDA9B5B83B8464646E1C1C161D07078191F5AC588093C4D3334CCACAAAA02CA32A372E001807A678F61EF5D308B5D0F324EF2376E5A392FE1591C1454672EC4647DD3DB1FE1F2FB1AAE1E596FEF4C6D9748446AA4A90495CF4C71DBF5F7AC9B37B5B634ACF698172BB32CC492140C127E9EA2A29DBCAB090460EC8D4AEEC824F007A71D476ED525C9D96A64DBCD3C91EA978AE23902E03FCB80C0631CE73D57939AAF1293E0DBC9776C1206E51D7196E809C1E73C77AB92B2D8AA31BCB53A1D2F11E9D6F1C6B9FDD0F324C7078CFAF1CE3F3AB510115A2A285CECC05181CF3C63F01596ACD272F7AC5848D3CA56910485463737D33F8719FCCD0BF28393BB2BC1006D3D08EDEF8A48CF99A1D23A0B94C951C12091CE3233FCAAADB3C4ED1FEE7738255861703A679FC6A8A5A8DD40A496970A30E48CE546074F7A92F5631672092CC0210860003CE3B7AD38BD5226713C33C53A648AFA7DFA46CE91CCF1C87CA19E5814079C9CEE239CD76171616F71A4EA7693302B1DC078B023E083C63D3AE71EBCD726229732BF73A30D51C74385B38D1ED95BCB2130AC408C608C118E1B19EDEBCFA51671FD981B37882343FBB058C64FDD03920F5FBC7EA3DC03E73525B23DDA734D1A88806E12A907FBBB48552430E39F427B7A75A6C7B104BFBB51925B69F2F820120647B006A3565A5764AB1B6E1884977209F90F5EA33CF66EF4E0A4230F2CEE041DC153A8FE9CB1FC7DA89790EC22DBC0C0AB40AEA07219091C0DDEBECDCFD4F5A9194151B1B6A965F98ECC0CAA9EDDF83F99F5A776F60E529DC69F0321290A0CAE4330380DB571939CE3E43F91F415656DD1599C2C4CEEB83B8293C212075CF7A906AE62CBA6BAC9F2C1BC0619254E48FA6EEB8C0FCB9ADD30AEE797680F8186C29217078F7E828D7B93ECCE74C1327FAEB4F2DB61C968DF05B0327AF3D3A74AE94C113CC24DB905803F22E39279F5FE2F5ED46BD509267351AB2C4AC2D9D108185C3646EED9CFA679FA56FBE9F6E22553046A76907280F4008FE828D1F52B94C78C1370BFE8C71C13B95F81B8F4E7FDE047A1F6ADF16D0960A90EC0C38C27424823BFA64FE3459F72BD99870DB5D4F1C6EB6DBDCA1192AFC1C81CF3ED5D04712A95F976824E30BD3B8239A9B0F959461D1DD8B2C8A1131F742C9EB819E7B000E7DAAFAA950C4A7DC395CA8C1E3A139E7B7E74D32B92C25B41E4C0A63832DC12433900F41CF7EB8F614DC6028DA5CA854FB87F84B0F5F43CD0DB17293B4A76B9F2F08AA17203FA138C7B60FE42A9A0595E4411B061CFDD201F94FBF724834ADE63B12FEF03E327071CB33E47CDFFEBFCE9B2460CA4BA8E5813942327383DFD69DFCC762BB2CCD146193AF53F38C64007F9668914233655B196C90B8C707FDAF6A39925B93CA9991AADC4F1091829DE48653F386209CFF00423FE0559F750BEA3A84436BB2019C88C8F97827BF7ED554D39BDC86D44EC348D1E7D2F48D3AE82179646CC8599C8DA7E51F31E8471F9D761AA5AEFD1158C44B2C7852AB9E838E8783D3F2AFA2A568AB23E7ABCB9A6EE7456D289EC52507AAE78CE3A647358FA65D03A3C402E7E5002A86F5FAE3F5A89C75BA328EB1B9A124A4DEC9B80236E7033EFF00E155325AEE3919770741B5515B3C7E3EF59DAC55D1A11CE56E9318233F31DCC7B03FD6B36E6E248AEECC18F6292C5783CE1475FCAB451B99C99D1C473BF24638038E82A08EE10B82CC012395FC31EB50D334577B22EA1C221503820375EB8AAD1DC22B3A1C900E491D0D4DD95CB2294788F5FB9809016540CA39E70A01E71ED4CBE90477D67745F6B2650F079C903FA8A69D8E86F990DB4464D62E6D998AABE5979FFEB525F30B6D56D2700E19B639E48C1AD93B9E7CB47A9AB824812FDD39E6A562ADB594FC9D79E2B1772B56723AF5CA450412B487315C2838CF5E7DBD055AD774F6B857822401A43B8360E372E73E9D8D347441A5B8BADC8D359432228CACA00C93EB9038FC3F3A7DEB4971A232CB010F8E70318C0C0E87D71F956B0BA392A34CD9B5FDE4111CE49182403CD53D1A779B4D865888EDC1278A27A8A0896E2C84B70D26F2ACA030DA71CF7CF156834FE633ED4238C0F5AC6F6D8DF934D4822BE92C982CAE17076F39C1EB8FE55475E322E94EEEAA48390A01CE411FE155CDDC50A4E4F43A88B5089F2338F97393F4AE3EE77DAE97F6BB690A385DD8C9C138A6A17094B94EF11C38C8E6B91B1BF9E4B28E509962A72549EF49C04AA267620AE3F9D73D16A37300CCDF3A671C0391FE351CA689DCE8B23D2B3ADF528277D81F07DC54D819A5DEA312820E0E7E940C7934DCF1EB5202F1E94645303FFFD3FA2A6B6C5CBCBF2A950402436464E791D2AF25AC4AB2398D7EFEE1B141F7249233D7BFEB5ADBB9CD2F23275D68E3D39C34BB4BF01B6E303DBF2A8B5A8621AA6991471001E61C6D077001BBF5F41FA74A2DD8DE9B6865D96B3F0F180CE481179678623773F8F73D0D3FC49113A73218B9F9BE6D991EDD3F9FB0ADE1AEE6156A4986811C3FD9CAC1FCB6750CC55DB19E7073DCF3D78356F49B3892C222611C2E3851C74E9F9D39248CE1CDD8B538FDD6C49DA366CFCBC8C02BF4FC69FF006480B232C1DC9F9549EDFE158B34B91B0427CC4B92C54361F7739C1EBDBBFA53EE628921673106655239070C707E9E9DF150D969B39ED38853A8BAB3CAE666C64BB63E5C8C9C71DB9F7AB1A3C69068A5963DA8247232A77608E9EDC0C7E5EBC5423AEA55672462E9734CFADDE3F9606E755380D92480D939EB9F6E9ED53E820BCD76767DE6381B78E074049E7A1208EDEBDBAE51496871D9F446EA491310F2643019F9891CE31CFE356648F7233795BF9DDC83DFA01F8D72DECF53A17322AA9884491DB0C00323AE307B938E48C0EE79AB45488D1D41E73875C9E79C67F4FCA89B56D04D49B39CD1D6573A8DCDCCE48F3981DA4F18F518F6E82A7D1F2DA2CC76F26E19C9C107EF751DFA01C7FF00AE882D4DEADD232F49B355D6EED9988656080966CB103A608C74DA73CFF3AB5A2EF1F6AB87DC92172ABB830C9DDDC1CE307683FE1C56D3972A38A314D97AD1048D35CB3B10D216241620E38EE3D09152E9D6FB2D659C83F500FCB8047181ED58F31D3C960BB0B1D94D29006D52700923824F1C77CD4D771BB58BC5D4BC78033C1E08C0FCBF5FAD35A91277DCE7C6E1E0B709D59C8CEE3F78377239E0F7C7E54D9A496DFC28C23F319CB80705B70F9C124E39EF5A56B5D2469464D4B53A0B9B84B7B6591D9958100FB8C8F6FF003FAD79878BBE2347A35FC9A6DBA19AF8A28190595780718C75C1039C7E5CD65083B2629C97B43B1D7BC77A4E8D0CB24B7680EEF2C22B8DE5B3D3DBAE3DB06BE7C7BABCD4E633B5CCA5D99BE6DD2743C9EBC6D24638CE0B7BD528A5B9129B7D0F76F0478CED35DD427B646206E0D1B48E0B36739CF419CE4719E9F979A78484F6177657D03C9248A554AAB48DF2F036F2BD325BF118FA69CA9EC4C6525D0FA3252B2C521DF9C8CFE954ECAF16FACE2937846917E600F3D3A7F3FCAB9DC5F35D1D0DDD1C4CC0AE9DAEC5F683BC36039704AB1031FC3D2AFBA452EA97D6CCC70E8A59771049E7A6719E08E47BFA55CE3171B051959981E2AD1A28ED96FE3CA4AAA85F6B280E3B9E57AF039F6AEC6DFCBB9D3E03CCB0988A60B648CF5E4F5AE3A9453D8EC8CDC0F278DD0236F98ABEECE55D412707BEDE7BF1ED5ADADE9EDA6DF6D52C609007550CFF00200C300F1D7E6AF3E74ADD0EFA55D772085559B66000CDB7829C6703D3DF9FC2A684386C92E43B7DE4624677727EEF7C1FD7F0C546C762D7A9029CDAABBF97CA9CFCC300F43CEDCE79FC81AB8024ADB9E76E9C80C7241208EDDB19F7048A6925B156B159B3967F90804B9F9D738C7D3D38FF0022A5995E3463E66E232DC3704019F4F4A9E5F22B423DEC8A72177061839523807FC47F9EAACC11B28C767F17CD9009247A76049FD3B0A3E41F31A36A3AB88C02386C0523B13F871D6972A54912804900AEE1EA33C63D4FF2AA6BC8561305B030A431D833B495079E98F414C7DA01DCE4B306C9DC38200E791EE07E15366BA1562656272D84DC08C80579F9874FA5337A8B89327055B711F28E33DB8FEEEEFCFDAAADE44EA4A0811C7B554EDF987DCE993D3DB8FD6A0F32328630F9DDF20C30E31C8EDD29EA5A64ED81B4EE4200299F933D7FFD5FE7A426621812D9DA779F9873D73DBDBFCF351AF61731296C966DAB838627E504E01CF1F538A858011B179092AA3E5DCA0F183E9DF34EFE4049B3098C866C3025769C9E7DFDAABBCCC85951C060D9E187519E47D76FFE3D4ECFB0B98B2E32CA4ED0846010146474FE583F5ACC92E159DB2FB76904E4AFCB8054E7F36FC4517F217311EA132B214842B6412C57671903DFFCE6A8491CB72CF1C40891BF7642B236090C49E57A6307F0A6A9CA6F43194D416ACDDF0B6862E34CB9BB9E35566CAA174500283EB9EF8E39AEDAD34B9BFB352289422326D4DA571B8E7DBDEBD4A749416879B3AD77A890DD497FE1E8D1632148037155F9BB64506C2E34CD2A38432232600DAABEA38E9EF5DAA36EA7155927AA2D5969E34FD3E38AD155F18001039C0F4AE7DB58D4ADACA4790A6E452E1C30DBB720E3DB8CFE5454D0C20FA1BA3558B4E20DEDBC91BA9E5DC290A063273DB9AF17F19F8A3569FC392E2E5903062C03AE4818C8E9CF152E0DBD0E873825B1DD7883C6D697A7FE25D0A4EF6E4FEF300838C6475F439AE0BC21A6F99A346648B0CD1E19B6AF24A8C76FF67F5ADB91C51C2EAB93D0DF7F1B6BA4A42840F30B05708095E33EBE95A50F86639761555CE71F754738C1C71E951D4212985AF8FB5A8A73713439448B263D8072173EBF4FCEAC4FE175305D21452AC8FF00C2B9E547538FA7E544B4D8D632949D992A7C45B2D76D547D8DA08B76EDECA3B12723BF4C7E55CF691E1AF2B4332451B285776C04418E718E383C03438C59A735484AC7AB5D6A76173A7A496374091B5B0A41EE3B67D0E6BC5742B4D46C75EBB457754F3554F0A000383D3E83F3AD39125A1CF2AD79D99F45DACC66B54936EE565049C7506B88D1FC58628D62BA4DA000140C73C818EBEF58CE06B19A676B7018471CA17732B74F5A82DF51B2BE8F742EA54119C9F5ACD2B1574539A51B648989C9C32F1D41C7F534D788472046FBA980791F7771C62B44F52654EDB0DF0F12D6002F5DC4639A6683149034F6F2280CAE4A9017D6B492D34334DADCDB2F2798C154EE3FE1C54EA48273C15C63A7615CE6DBA30B5D92E3FB12E1800ADB8100F4C6E3FE153788C91A2C857FBCBE9D39FF001A966D46EB623BC12C9A08578F76630323D707FC2A6BA6D9A510CB801071C71F29F7AD63B98552AF87983E98301E3E08C1193D48EFEE2A6D114369484E0FCA4E71CF3920F1EE6B4A9A23081A2E0829B8EE18E4E714D9A325500271C73F4273FCAB14AE6C9A11C6C567DBF7012B8FC3FC69AF6F2676331C30E31F87F854B469095DD99058EA1732DBF9E8ED1C7B8E411920679AA9A534EBA74CAD96DB23AF6E99AAB6A6B5AD0D8DCB7D7E27631484B3038E0639E31FCEB94D16E637D42E484CFEF085C8F4518EF5A4A9D91CAEA9DFFDB63F5AC2DCBFDC6FCAB1E5469CE7FFD4FA7C10BBD635C3E5875FC4F3FA5543728F8002B6CE3B8F523271C1E2B6E5BAD486BDDB99973B27F10D8A39DC023BA801410738DD8C75193CF4E48EB8C408669FC48232F1AA470FCC830704E07E0783FF00D6ACD5EFB1B72250B8CF1207758123F2D98C8A0850AD9FEEF1E99FCAAB6B5117D56D47DA1BCC3206DCCEBC7A718FC7D78FCFB211D353CD92573A9B58923B142A0797C1CED38C6077FC28B7836DB80B33046F9CE4023AFD38E38AC671BBDCDE31B2D09B01654464E71C90010B8C7F8FE3834D29389576C8032478E7A1231F9F23B7A9A94FB97CAAC57D54A8D3AE9C9FF96649D8A3918233CF07AF7ACDD72490E912234CB16FCAA852324673804F19C63AFF004ACE4BB1A53826F5645671A45A147B08CAAF400007278EA78FFF005D56BE9EDADF445DF216558DBE6C0F972000718E38CFD315BA8BB99621A6F71BE1F2AD0492AC6996738C607B8E01F4007AE3EA6A2D06246D1E31048B13B29C18F6F04640F41C63D3BF6AD6774B630495B73A2DAAAA5010ADE8140C73C77F5A8B6CA1C64C6CD9001236F24E4F5F7AC25B9A72DCB4D828A49046DC16DA38EDFCFF001F6AAF74F37D9A46611A6D8F2718EBD3F97E351335515D0C9D30C716873920A9DF2300140EAD9E9FE7D28D160B77F0EC24B23971BD9C81F302DFE27F235AC63668AADAA21B3D90D9BC710FDE15924384038EBD73EDFA76CD47B8BCB7C42071E42A0042FCBC1CE7D3827F3FCD556614D1ABA74262D3230501257FBB807238FE751DBABCF631C4A8638BCB520000718E327B720F5A5366AEE3F5195E5B192385BE73132EE0A786F51F97EBF5CF1DE25F1C47A745369FA380F3E3CB691245210E01E46EEA39C9CF1EF4D69B10DA32F58F130F0F68B716A80A5FCDB963C47BB67CCB9F97381C1E0739C8EC78F32F0B68D77ADEADAA4FA830B89DA6C6F9821C9EA3A1EB839C119ED5B7C466EF1D4E73C3BA7DEEA7E2AD4AEEE2D1F78B82CC5617EA73C8C76DCA7A9EE2BD8FC29A2DAD8F88EED23B68D14B7DCD8B80BD0743C608E3F3ADE3A2B1CED36EE86E97E178E3284D8BFC836E36B008A14F032DEBB7A7BD7A5470C65C8D8A58280DB8023B73D7F97BFAD727A1BC60FA9CC59DB2D9C0505ABEE4C7003654E3EB8EB935D22DA2C76BE5AAA2AEE27EEE7FBDD4E79EDF953E6EE5B8BE8724FAEDF699E2BB2852DDDAD2542AEA03E7238DC304F3923B0EBED526BD6917F6FE960EC6901CA91EDEBCE7AE7D7A54C65EF34CE88C6E99AD6733DC6BB25CC05B8897202B7CE7D3D3A63A019E7D6B9CD6753BBF0BBAEA36A88C23C808CA02E7AF1F30CF0571C8EBED551578B673DF95A3B7D25D2DEE2E6C5832969CBC68776429EE33D40AE7B4DD79352D6ADEF65B5108113A3EEC83BBE50075CF576158F2389DB1946A23A99ACED6ED0457312ED2A5782571E8073D324FE54D9489C6D57006729B5813D0FBF3C50E2A5B99BBC5EE72FA9787AE74D6DF049E6C4CDB300B16518EBEFC9FD3F2E99AD3519622A6FD460139F2F9C723AE79E0F4AE5961E2F667447152A7BA389815C9036B2B11C26D73C617AFE02BA3B8F0CCED234B6D748CD8650DE5819DCDC7423B0AE67869F43B218E84B739B6819C32E3AA95CAB3E3EEEDFC7DAAE5CDADE5B92B3C055BEF02132A48E06324F4C11FFEBACA5068EE8D48BEA67ED2255902B80C72B90D93D08FA1C1E956A487F7A57C972072AC633C360A8E01ACB4EA5E8661858AAED997214ED62CC0E71C1E9CF233F43579E3FDDAEE888385C820FA63D7D01147CC933258E42EDC8D84938DCD9E49E3A7FBDF90FA54D340A3A2B85EBCA38233DCF39078FF002792ECBAB1DC8499D65DD8248E79DD971CE71C75C8CE69B2C07EE085BEEED2369E46D1CFE381F9D4A57EA5A771BBA7770865540553E605B1DC67EEFB53CC72038319C1192A37F4E471CFAF6F63425AEE0C4489CF9859BCB5F95480C7A739EDEF534685A4184661EBB5B91804FEA33F8E29E81AF51AA76B36E604A9C6496C700F3D3A118FCA9EE9F248C8AFFF007CB1E41C9FF0A2C896CA264F911CB9C85525431CE79047DDEDB45433B30322C6AD8519FBAC0B646783C75EBF8D16644AC8A7248EDB000B26E61B30E7EF673FDDF5231FEF7B56FE8BA1DDDD4A6EE68DA18D5B7213BC7A363F02587D4D7453C2F3EECC2A578C117344D23CAB691AE0C87B8DCDEE401D3A00735A3E20BC3A668D2DBDBA9126C64CE0B630A47F4AF4A14E31D11E454AEE72772C0D7EDE0F2ED60951D10EC2AA78CE0E3B7B7EB5CF785347925B18DE48CE1955B3F375EB8C91F8735D328452B9C9CD29459A3777B73A8C0F13B95072B957393904F5C7BD742BA52C312210CA03649C9EA31FE359B4946E2B4BA9C9EA6AF69A538DC7638099527B9C7A1E809ADCD7A0016CDB928F70A3827FBD96E9EC28D1CACCE9A74EE713E24D2ACBFB20B2A955C8217773F73E87FBA73C5777AFDB2A69E58020464138CF200FEB935A53E5EC613BB6D19BE1DB1B38B498768DA23551F301C719F4F7AE8B46854E971C6CA3EEAA1E3AF03DBDAAAA5999D3564D917D9EDFE401573B895ED8F9463B7BD68B59C6F2C5C3293F37E071EDED5868CDE1752B195756C05A4DFBE246D39E473C0FF0AB77B6B225B4A04AC0946F989381C75A4CD69DF9EC737A45B22F8794B4E5A30787C81DBAFA76156B47B4DBE1B8B73396DB9C9639E9EFCD6BA5C789934F439ED26C213A85CB2BE59A5CB0383D863B7A55BD26D264B9BB75326FF37713B8FDEDB83FA8ADDA563CFBEB761269E6395410012467A73F4E3FD935BE6411C852E23E03E07B727A71EE6B1DCD22BB98115E4BA686F29BEF05046E07938C76F715D0C821190B1163B78E09E41E3F90A97A0DA29F86B5F7D69E49EE36A215F276923A827FC6B1746B4B848AE9A1DC9189C83F31E99EB468CE8ABEEEC77D665A595264F9415DADC0CE01A668AA3FB391B392EC5B0C79EA3FC69331DCDB88912E0824E3073503EF8D7249200EA2A18F5453F11A893409A36EA4A8C03D79E7150F881E61A51F2C2939C8CF1DFF004A93B30EAFB8FD48674724282DB3AFAF07FC68D42598698C5A218553D3F2FE75707A9CD5A21E1C70748881C63181CF6C71543C36F37F6745B50636E02F39E73EDEFF00A56B57630823A52BC29DDC7F8E49FE755926B8091B34608DC1719FCAB9CDD434DCB448085B195C11F8D425C98CA3A9E99E39A4C705666769440B4BB19CE247CFB77151E8F2A496B7640E5666C8E84FCD54B45736C446E8A5A4E17529428E3764600F71FE1F95374BF9353B95563FEB372FD703FC2BA65F05CF375BEA748AA85412E738F5A80AE4E77019F7AE5D4DCFFFD5FA4E5B843831336727A038EE3D39C73C8A491195B6E30FE6750180FF00EBE78FF26B57A6C609BD9187A64523EBF7CEF3BEE00A79618800646060F5231D7BFE3573461235E5FDC48A4B19B2A406C30C03C1C91D874A5147449BE5B3326F6DC1D76CD52596350D2670F21C63AF518EB9E7B63A8E3132EE97C4EC0212B07CBC86076E71B7D31D33C57627A1E7386A750B6E56DD712383F28249C9C77A9D0A9F2D4E428E33B4918E723F215C8F73A1688AC56556988661B8641EA338C91F991D69D2B1791936BF3F286008239EDEFC9A6B540DEB639FD7E3FB425BA493C8EB25CAE573821413C00074C81EBC54D72259FC4F608D1EE555693E5563B58E3FCF6F5F53445ABEA6D18595D107880241A4F97B801B4A9DA42823903B11DF1914DF1433B470C7FBD0EF27505871C7A0C1E08FF3CD6F03967AEE5BD3ED223A65BC6C7F802060FD7A11FCCD5EB48D96C020F333B4E0056E0907A7E751564C708DCA845CA5C26251F3A676E0EE07B738E7A2FE5EF565895BE8109C82086C838FFEBF7FC066A17BC68959157564B91A7DE389369DA70B1B819EDF96DFE46A7BE766D16E9464332374566C73E9C1C003FF00D759D4958D295AE52D3A3821D1ED953E440072A467A0CFB74C7E58A6584FBF43B69149086352464921700F271CF23EA6ABA9555DE5633CAB4965A9E25E58B2028CA0B10467B7B74F7ACCB9BBBBB1FB45A99119AE1D9A15676C01C019E38CE735724DA4630B2959997E27F165DA4434EB1991278E22653E600C3A648C0F423F2F7AB9A7F86DE7479EF59C0CE599DD8F42073C0E0853472AEA39CDBD8E6341F0BDDDD34725C331C02DF31527B0C671C741FE15EAF6F6AB6E91C6A87E5420FCE4E3E5E8411C9F7EB56E4AC64A17D4E3BC3DA55AD8F88F53893696DA1B700A7600172A4819C71EFD7AF06B634F1FF157DE297C39404012678CAE0E08C8E0F6E38AC20DEACEE9D3BD34D987B0C1E2F3865412AF382B9C0E00E3BE7B0F6E39A9350942F8A61F326077AB63126002793DBBE3A8E7F335DF4E57BDCF3AF64CEB9B699599C845DBF38F94606189FAF6FA543F6C4590AA3B3B2AB2FCA7820E33C63BE13F026B98E98C64C9FE636FE5BB6ED84E373A9C8E6A24B9C79CD1C723A960172C3DBFC0D4BB17CAD183E2001754D3AEBE57559BAB9419CF27AE3AE4631CF155FC5CD7DFD9B6B308A38DBCF4FBD267DC8FBA7A007F4A87A4969B9D746178BD48FC5F6914FA4363CB1B80E484C677679DDF51E9E953788E2793460F25D101632FC380A00E7B039E31C7B67D2B785D476382AAF7918F65A5A2582958A3F2954600551C636E0FD0000735B9A1D9C674B4413B8C6D5C6F186E149ED81C1FF26AEA994125D4E7278EEACAE2DE4372D13A9640E4C7C9F948FCC83D2BA9974CB5F2E067EECB9DCC3B72BC11E8A3AD616B1B412EE36C7C5BF34506A0A8C4303B8B2F2338E83DFF000F7A59B4BB20D9408ADF360923863DBBE3DFB77C52BEAD31A6AF63A3B796D6F039B69194AE3E65E4AFCA79C1CF7CFE55C56932BB6B17B6F958E581F62B171F3024904E47B1078E33529DD3674F2F2ABB3AFBEB7B8FB2B3490ADD045CFCC141C819EBC01C815047AD2CA91A4E3CA7662A46FE579E393F87E752E0DF40854699CB5FC02D480A300AE195906E1927E84FD6BAED66C6D6EB4C9A2C84703820804E01E3DFAFF9E6B96743C8EE86292DCE037A9F248010F1D147A93EBE84D67C8EFA7DE9B6BB1189930AC03A12D907039009E98C572CA36D91D7ED1174AFCFBF231D4655474C9EB9F71FE455449236054056523FE999C751FAE4FE951AF54573A2D344884C59C000804A8E3201C7DEF6C7E7E9517988D8E5738182594E4E4FF863F1F7C51AAEC5DE2B664FC2A1DE32D9E48419E473C67D3F90AAE644CA8C025810701307BF1F850AEBA21A9A7D49F6E49C297DBC81B7FBAA3DFD081F5FC33359DB3DEC85610114B3E5F09850C3040FC003F87D73A284A5D0CE75E30D1EA5599232588F978FEEE0138E7BD6C7D8D6DEE92C6D504B7B31C67E4FDD0230180F63D47FF5ABA2343B9CD2C4F548A165A3309965BFD914415432B7525700E39F407F315B5AC431CDB2CE363E73B6FDEA4703008E3D0E0FFDF46BB29D28A5B1E757C4CA72B26564D7234961B5B2B731A48488768E1806393D7B83C555874558FC616B1EE216DADCE3017182381EA3A1E29DF5D0AF6778DDB20F115B19ED8BC98F2E471860B9CE4AF4C9C9EB5A9ADDADBB5DDA42F86DEE3921782377E7C9F7AE8A6AEAE70CA314EF73A3D0ECE3B4D3E38D17700393D4FEBEFCD5A82DA2FB212815491D401C7158D4937A174F95684D32E510673C1C1C1F51FE15088E48663B079889855E9D71FF00EBFCAA2FEE2356AF2B191E2087FD1AC49CB20B853F37A63D8FBD1AFDC46CFA74122952D2AFDE00FA7BFB52D79D1D3417BB26C97C440AE98DB1B70C13800FCD8238AADE2AB944D359F2183E370DAB93CF3DC7A7EB5BD3471CD352668E8F1C89A6C4A73B885607079A8F4B915F4F55030C0803200EFF00A5150C29C5B8B34C92F347BC630B9CE7A1CE0D2B63CEF30F707838E33595CD947A906A484585D1CF05181241205375404E9779B3E70636E807F76A1EE6B4A3ADCC9D2867C3716EC9054838078E6A4D2829F0E465811F2163F28E41ADDBD48C4AD4CFF0FAB1BCBA2EACADE71CE73EE7BFD697C38A7FB42E82AEDFDE9276A807A0F4F635B545EEAB1C4D1AF7D664AB3C6B923900FD315A8E15D4AEC009E4FD41FAD7326D1D3CA672AA92645182DCFE64FF0089AB4F12ED2BCA83C74FF6686EE2B3B9CF690812EB50888010C8D90C08EA703F91A934C493FE124D4D0A963F23818C73B8FBFB2FE74459D35237468692CD089D36911C6DC31EF9C703F2AA8D7A60BE86C3C9625A5DEC76F6C8FF001AD63791C8DB475B8F957FBB818FF3F4A644E0803F8B19C1C71838FE558C8BF89183E23F3974992151925940EBEBD2AEEB5134B66F1C409943AB8FC0D246F46D123D41E6FECC650B92570A4F7E33FCEA5D5888F497F900F97038CF38C56B0DCC27A999E1EB80DA544550AE573B483EDFE3FA55BF0F6D3A522152703E56F506B5AAF439E0F52C0B97122808DC1EFEA09C7F2AB7220207382483C77EA3FAD73266E95B60599F76C29D173C52EE62873C92A48C607149B434B530B4B6DF2DFA04D8C1F39FC3353E8C7CE6BD3C7323A13F438A6BE13AAABD0CED354AEAF77961F7815F6E0923F2C7E74EB680278966F95B1FD4641EFE856BA9ABD33CD72773A4F213B31A4FB2B0E371E3EB5C56373FFFD6FA36E76E5B7AED0D920ED1F2919E4F3C0FF1FCAADFA9934DBC9D24F2F866253079C939C67DF03DCD5B8B493B99D3B363BC3A8134A7942323CB2B7240009CF7E48231C7F3C555D1ACE283C2F100C514A1C60203B49E7181839F4AD52F79246D5A518AD482C06EF10DD3A0C852141098E368F43DB1DBA66A0F0FDAC6DABDF304405A4C155540C31B881918E830307A63DCE7A24AC8E25CA767F2C4C011B4641C363DFA738E9EF59F74238CB2CADFB9219704820E70BD7A74CF5CFAD72BF235B45F50DFFBA65037B0E9853D873F8FF88A6A4A8A5BC84F9509208DA54F24738C8E9FCCD166B626EB56662F9B378CA5C5A1CAC39CB2819CE0670383CFD4FE1C54564676F176A2ED6E811615058ED38EB9E7393C91ED52A27749A54D3454D785DC9AB5992621B641FF002CDB8001079073DF19F7F6A87565B89BC5F6D19957E50CF8201E40C83D78C0C83DF0715D50565A9E6CA77E8761691CCB6CAA55718181CF4F423B7F9F6A6C704EB6AD1412AEF64236955383C7271DB9FF003DB296ACB85FB11484F9DBDE37F95082A411EBCF5FAF5F5C74A9B0CD2E3CA12213DF038E7FFAFF0095432B9D34666AF22A68B74F1ED61E5B6D1B4F19CFF31FE474AA7E21480E812F9F6A99276B0D83BB020718E9DF1DFF005835A2B5238E4FB1E82B72FBE45860323AE5B2D919EEDE954E5963FF00845C416F2461A68C2C808552808EB8E98C71CFAFBD69CB294B4615A4A176647842797579EEB54BC8A56712954454910AA8271C1E3BE38C8E95A3E0F305A69C6DD1235F9C05F2D78383DB939E7D703AD75D5B463A1CF1719C9BB1D2B19DAD1C2448A0E02139607B608CF4E00EBDE927BB916DA5D96A58ECFE2E8304E4F3F81FC2B937374E23FCBB82C36B2C60A863805B3D40E4FD39A4D970A91208E38C2A118233CE071D79C004D3B0DC91CF5B5B4C3C697A1A771BA11851B8719CF19E3AFA7A7E4C86D65FF84EEE5CC8C233015601700F2A7D7D8FE67F188BD19D2E6DD2D0A37B1C29E2981F7B65F7200DE6609277673D0F2BC671D0F3D69DAA5AC51F8A6CE7FB32BB48CE8CFC9C7DD201C1E301457653DD9E5CB9B94EB566891B3E6EC55C16C123183C83ED807F018A9218A3585764587D848E38C91F5FAFE7593B1B41B68ACB724CD200924A3E52786C0E01EC3D00AD08A38CDD3A6CE081918E0F38FF001FCAB2762936D9C8F8C25B96D1188428EB20C36E61D78278191FE1CF03246978A431F0EB30899F6105B009E3A6783D3E6F5EE4F3DE24F547550777632B5586F6EB48425D61F9430F99FF00BB8C7F5E95AF3FCDA0AAA4782D18C264865EFEB9EB5B27A1CD5A369D8C4F0C46D3E91066EE4550807DF6F4C1EDD79C63DB3D454BE0F4F2AC8C3B376DDCA010C30031E993DF19E3B135D155C5AD0C21A6E69C9A79319334D29F9D4FDF2464B0C607D7EB5A72AC7E4C80F3F383B8138000041FCC1AE4E666D1D3529358A178F2F2018272AEDCFAFF009F635724525D0EC2CDB5BA6460FF0091D7D852BBB36C514B738FB0B34B7F165F46CC55A5432212ED91C1E071C75C75AD07013C6E19002AF6A30413CB0E7B71D17A7BFB5117AB48EF95DD32636E86E9D18E5C90465CE738CF1C73DBF2ACEF11CF3E9F7D6F3C03F8943E43631DFF001E08CE0F4AD146EB43822FDE68B72EA92DA6A90D8DCBB1B6760525C9E082783C7B75F7A875289B50D31D2266F3D17CC8CA161F375C75159D95F53A21CB28B6CE3BE2727D8AEF4FD4A391D049FBB6C4A50647239C119209FCBBD2F8B5AE353F09492107ED162DE66D1BF38DBC7039C608E99EA2B3505D48AB19415D3385FED6913694B9755640BC4DDB767FB9D71DC7B5677DAE4C6F12B8DDF79D8480E41C64FAF1CFD2AFD9C1F438BDB4FB9A906AB74EE8FF006E71B587595723DF1B7A648CFE3EB4BA058EA1ADDF2430BF2143B333CAABCEEC82481D8AFE22874E3D8A8559ED7B9B5E1EB4BBD6EED228A790C0AAAECE26FB995EDC73E95EADA16891E9965E4405F6918CBB963EDD7D2972C16E8EC839C756C9E0B6B6D374F670544308CB1247382467F234A592FEF2484126CADB07E56237B05CE3DC54DEFB9AA83BDE4C6451ADB34BA9DC8DB2DD702376C855ED8E3B9FF3D2B45A3171728EC404DD954FA1F4AA4D226A5493D119F65A439905DCE5B71231C8F9463183C56EB6D194C104820FD4D5396C918A85D9CAD9DA79DE2AD427F39C794153EF0C6707DBFD91F855BD0A54B8BBD52E8171BEE48249359C7A1D536947531752851BC456D0AB2B18D59C2EE1E98F4F41F5E6A7958CDE2F116E61B237E013B40C9238FA9AEF82B44F2A77D2C74915B4A8B8494F7C73F7B8FA568AB02A1BD17701FA571B7791D6959A29179137ABC4DC375561FE7D7F3AB6A14E51B254B1C74E454E96B1566A7B9CDEB97301BCD292520B79FF002EEDA31C01CE71D8E6A7D5E21FDADA6C6412BE674CF1F74E7F9D38EAEE74D3D20CA1E2C9A08AC47DC07E5E0951FC6B9EBF534EF16AAA592B1C11B86793F8F41F4ADA9AB9C3525ADCD1D3444F608484391DBB9E40FE55634FB541A7A2A821D415FBDEE3FC28A9644D3BEC492C0AF3615DBA13D73DC0FEB48D6ECB3A9595C61485E78EDFD79AC9B37D6C56D42DD974BBC559882626DB823AEDE9497D69BB4BBC02573F2310723DF1DBDAA265D293B99DA55B27FC23F0BE77662C80C00E738A9749B703C3D09F35F90460B03D5AB5EBA86224CA3E1A814DF5EED1F309988C81DF00FE94EF0FC23EDF7004AE544AFC83FEC8C76F6AEAA9F0A38B999D4B2CA03ED20F05B040CE296412A29C0DE146D3CF24631D6B8AE747311CAD2050FE531209C1C820F1814D1249E421688AE46319181C0CFF003A63E6473F69BD7C6370AA8104B02B7DD071838FE99A7AF9CFE2D607081ADC11EE06723A7E34E2AE8EA7CAE9A68ADA840C9E20B1919F393E5B0503A9C1FE94BAC472C3A95B48D29C79A41E9CE79F4F635BD2D0F3A52D1B3AE8114C5130625800B938C938EF51DBC0AD6EA7CC38F97BF6C565245539685811BB26F079EE320F1491C044DC390B8C6D359A2D4B528EB0CC74799402182B00091D00E2A3D6418F4E694AEE4E41206783C66B583EE277921743431E988154138C0E3D29BA3CFE5E969B399163E558E32DFF00EBAA9B4CCA3169EA69CA328991CAC9918F5CD35E54F2012C321B70AC6D636B31A6396303649D01C64023A75CD299E2121CBA9E0F19E0F3436349DCC9F0FE6337B8ED3B0C7A0C71C547A1DC4266BD5570DB650320F5201FF0A13F7753AEAC5D8863627C4EF8C12704E0FB1C7F21553ED11C7E260BB803B010463AEEE47EBFA1AEB86B4CF2A574F63B4DC3D0FE42A3F3A3ECC31FEF0AE6B1B6BE67FFD7F7FD591D7C3974662635DA576E491C15C8E473C77C71CF5C53FC4076691222CA2377655186619E738E01C7000FF811AD6567A0A8A6844B0787C3704324CDBFC9C330618627EF67039AB77C447A31C36EDCA321571FC2738F4E95A4198E22EF76739E19B56492E6596662CF3B13B981299C7078EB91EF5A5E198A47D3925323B172194EE3C063EA474AD2A799CE9366B7D9A25E0E1F206E2C72391B78E3FAFF0017B54DB5844B26E018FD738E4F6E9CE3F2AE75E46E9589CFDD0D8CB15C6001EFFE34162980B9FB99071F7B818C71DF3ED4AE5257763034D42FE22D5A4F9405200638CF38EE71DD5BB814ED1576DDEA436B3279C47CC5BE5C004823038ED8E4F39EF445E913AEADD4124674A15FC5D0A864F9158F5193939C8E39CE1B9191C0F5E1622A7C5CCADCA8419C311D18ED3EBDC0EBCFE15D5D0F2D46ECEB048A514ED1B402A47041E9C669D10C040796C962430F6C76F6FE7E95CFD4E98AB0DCB79AE4B2B1E08238E719F4FAF1EF526D4677F986071C1FD6A42C617880A8D225CED6DC7009038E4FFF005BD3AFB0A3C483668DBDDF1BA4041638E9938C639EDEBD29367450A6D95EE2347D1543451AA88C0C6D53C63F2E840C7F8559993CBD1C28CAB27042B724F4EBD8F535B537AE88E5C427CFCA8C7F09C512DA3820297790924275DDD7E5EBEB923D6A4F0C46FF006258F77CAD9E43861CB100600C0E067BD6D553B18C5B523A055FF4691F60C1FC0281C60FE34ACE56DDA63265776092C075C0FC7A8AE44740E0ADE5AE46E3CB64AF53D4E3DFAF1EF48982916170E154751800F03F2EF543B5CE75540F1E5CEE552FF67562DB074E071DCF0339E995EBD6A44609E37D8BB9B75BE595986E501863B73C67BF7F6A88B6EE76592858CAD7467C4FA6E7EEEF6E4004824FCB8FA9007E3DA97C48FE4EA96726546D908E582ED27BF3EB81D3FBAB5D749BD0F224DB763AB80836D1B8504F04630402300F7F7144322C960AE1970C9DF1D33C76FA7E558CF7D0DA0D8F51FBE90E00C8E41E0AF0471F9D24B3AC64B4ACA01518638E7AE39FC2A5AD0DD232F5E84CBA44C2465DC4A92AE06CC0208FE40F5C8E7046734FD56583FB22F143282229004C8E7000C7D79AC5A37A116AA6857B71E6F86E2C3E77C5BB19E49C7A74FEEF43DFDAA1D16EE33E1A8FE524E0F040C60311F4E87F1C56F062C545A9DCA3E1741019E009B0AB10760E3A1E73939FE754BC3D3B1F105E46B6FB954E770DB93D49E9CFB7AE73CD6F27EE5CE151BB573B578DCA12A393C83B7FD9FF00EC45460CC46D68506E1B81C8E40241FE75CD24B96E74AD5344CA54DBC442820267383DFF001F61EF5562376D0AE5900CEE249183F313FCB1F9D3BDE21C915A5CC6D4D193C63A748E8CC648994AB8073C72720E41038E41E4D57D745C47AC69372B3246AB26C180BD4F3819F7E3AF46359C5EA775349C6D72BF8B5316E1D62F9B21B85C8237647719E99FC29DE2586E1B4D322B47B97047CAA7043018E7AF04D7552563CD9C6D2D0BD6F0FF00A1A49B0A7CBB9805208E3DCF3F89A8F477B86D2423346C0A633C74C0FAF7E3FC9C2A8BB0D4525A189AE589B4125FC31171B584F1842432E093919E7818E2B767B23324915CE1A393E565E39EA003CFBE2B0E5674F32B6A78E5DF81A6BBD5ADA1D2E11F67B824EF2871110A0E7A8C2F51CFA57B4699A05B59DBA796816305B6FCB8E0E403F5ADA2D2DCE2AB152D8CDD07C376BA2D86CB68C26541766DD9279E99E99C9ADD99C2DB2C3237EF5CAAA0F97E6232303F3ACE73EC6B460A3A11DDDF12A6D2DC159E41C9C7089C67E879FC6B2758B3FB22C30A380F712069A5C0F9F90BCFAF03FAD38439B737BC63B1D069F6B1470A048F6443951CF249E73534514D1DA2049776E23008F7A52F22399BDCB3264C91AF4233923E83FA9AAED1CF24C489828500038C73C7F4150BCCA497427B82555DF6E0804F43CFA567EA06EFEC97320913722310028A5B1A5385E7629F8691974EBA775C23DC33A9C1CE31EF55F428A7B7F0CC61195810ED8C63EF1C8E86AA25578A4EC45A6C6F3789EE19D77611231F29EB824F3F87E7C537457906AF78C612EA1F6F03A6D000F6F5FC6BB252D2C8F3B91DF53AC8F9DA718E08CE7A0383FD6AB0B9915DCBC2DF2AE3007B93FC8D71DACCEA717B93C61FCB6CF563BFFDD3ED51433C66DF1F3A93C61BAF23AD4B56279756CCDD4096F11E9EAA3FBCDCF4F4FE751DF323F8AAC3E6271192001D81C9FD688E875C17EE5B29F8AE426D50B213B6540C0E4646E5F6F634EF1503FBBC8CFEF147DDC8C8CD75419E74D1BB65F2DB28033C7EB81FE34FB3005AAE0F3B811F981FD2B2AB7610B923303286C7054F1D81DA7FC69C38906D3F2E320E074C566DEA6BD0A97C57EC178140C189F18C8C7071FCE92F866C2E06D077211C77F6FCA94D1A51BDCA3A4B7FC53701393F26EE33C53F461BBC3F0B0E06CE3AF4233FCAB47BA0C44599FA07CFAA5F1C900CA738FC39E9EF4FD1001AB5D85C80643C7D403EBE845754BE13859D47255B18E801FCB9148DB59FAE32B9E3B715C8745D90E7FD1D433649E8463A9031FCA9833E442A76E32578EDE94018F73B53C5D645436E7564FC3A7A7A0350EA6766BFA7B36E52A4F273DCF4FAF02944E98FC361DE20407C87C02165420E3D47FF005853BC47F259F9C080090464FA30FF000FD6B6A6CE09C1DDA372CCAB5AA7CA3E61C7E74CD3583D8C600FE1193EF9A55050D3434401F681260E327AD359FE643B88C9E3F2AC51B5BB15B520A74B9CEDCE236381D381D3153DCA2BD9CC32012BFF00EBFD699B537D0A96290FD995F6282E0119F5C7F8D56B1F9B4589B270A0A8C8EC1B145D8EAAB6A8B72451791B828C138E39E9D6945BC6625DAC72C7770C79CF5A7B1CFCEFA0C682212305E72386E38EB9FE549F621B5373B0C0C707D73C7EB46E526CC9D0E2892E3528D620A165E0E3A0207F2E69BA3D89FED0D47F78C7F7A091B8F7047F51425EEEA7555BD8A1771443C5113F9601C738EC707DBFCF3EB4CBCB3FF8A9613B9B715E393D7A7F9FC6BB69B4A99E4CE4EE74E225DA362FCB8E3E94D8E17589144F260003EF9AC2F137E69773FFD0FA075FDC52D5170A19F9C2B741924F1EDFCC553D652F25D6F4D89044002F2608DF93C0078391C67F33F834FB1A41685AF134A574878BF8986DF9573C9247DDC8CFE049E3BD6778A6359204479FEF3E36654839E3D7D391EE38C57446ECE1ACD37A9B9A49F2F4A84CD205DA98DA7381F9D1636D6A966331C6A4767392B9C7DEFCE9CDB7B8E118A341AEE2F3172C4F1BB0AA4E4723B7D691CC31EC1B502E718551C71B80E3D467F2AC9237D047BA00B1D8D8046FC83CF5FF001E9497175146AE19D7785C9E7FD9F51DFBD268A85B9B5307C3B71712457BFBA287CE663E66E0C32463AFA01D3E9E951F86AF216B4BADB131DB29C11191EADFCC11C7F8E08F43A310E3CBA146CDEFCF8BAE43C9183E5A91F7C9407B1EDEA739EA45575BF68BC5F24696D8FDD860194852A0EEFEF600C851D0F43EF5DD6D0F339D27A1DC06BC9154FDA15581E4A939FBDC0CE3D38E9EB5009E7DCA22B7015067E7C8C771C67A9F6AE3E5D4D9CF42C88642B2033B8E3A8DDC707A7E58E3BD570270B3323464039FBB9EA0F5E78C13CE3B71EF4A4AD14CA72D0CBF14AAB6991279926D7983128EC0F43DC7E58C555F15C1706DEC904BB9FED0A3E4E09EA31C1C8EB8CD4497BC91D5876CD4D4A041A63ABCAC4018CB337704673F4CF2477AA9E2285A2D09D5DF7B2C65B0AC7E638FAF1C1CF5ED5BD38EA70E224F9AE57F0B4329D361904EECC4A96258B64807D8F623F3EF8A93C2B688BA2DBFCC5A46542721812719C609E982063B1DDCF3C6B595911177D4D463751C4C45C2647037123E84FE63F2A7CB68444C649C9E8B83C9C71F9D73AB1776465EED635E509C641C300001CF7A905BCEBD2E010A70A0F73938C8FC29E85239D57BA6F1C10D2C7E52DB67F88020838ED81FE7DB291C372FE3270B2E025B0000C8078FC88C8E9EFEC2A2115A9DBCDEEAD0A5E2986E3ED36D9BA28C641CABB0C6133E9C75279F5349E288647BEB3633613CF0C1F0C7807E6E7AFA9EFD2BAE94568795ED3DE674F6D162DE0F9DC83C8193B5471C63A77E292DED24102AEE71F2F00654F048EDFA56355599A539B648D0C0B788CD87561B46EF6190471EE3F3A4FB1C62F2320E42A955C96FF3E9FE4D4F435576477924234DB88C324798DB2C0F03B641F5C9CFE1525DD9C7F649E331A86DA47209E3E6FF0001594E4AC8D69BB4AE62787AFA23E1A0779DC37ABA06CB03D08E80750474A9FC371AB7878124912395DC58E73FC5C9EA723DF807F0D7DD4D58AAFAEACC2D275056F115E966CB0DA413D98EE271919EA3381EBEF5734D523C4D751842A0150D92DC723079E3B91F403DB3D1A72D8E0B1D17DAA4078418551D5FBF3FE1FAD5AC286201C6DC7524E4D733B6C6F0B9496E2E1AD98228E2465E5F9C723D3E953C40889CBFCBF798E7F87A1E78E284D582499C8F8965BD8A1B067311512A83B9941F98E01190475C7E557BC50A16D6C8A2602DC290A0903D71C7BD671DCEBA2F422D656E3EC2D978C281D4B023007D31FF00EAAB7AD329D2F76FC6E0A371627F5C73C64FE35D3096A70D556664F875EE869902BBA6550AE091F31DC7AE07BE7F0AB3E15C3E9EA016076A9552E49E727B81D862B5A96B1926D9AD11965BA48A40BB23279E33824F278F5156F4F456592E376779C0E47DDEDDBDC5632D0D15E4CB331222118C6DC633F427FC689300C8FB898F07804FA75E95CF2D4E95B68543034922C9B03B03C12064E463A62AC3C8DB8AE02B03C1079E003E9EF4AF71356398D611E4D6ECA00FC872C570A46300771F5FCAA49253378AD252FB82444FDF231D3DBEBF9D75C1AB1CF2D4DFDD72891A90A71D08FAD590A3E4CE063B03C9CF7AC1B35512979D74B3488D082A14107839CF1F9E3B5580774D21C80015EE3AE7D314958AB3465EB125C2E917856304EC23008F503FAD4BAEE3FB1E766C007049040E011E83DAA5BEE6F477B946CAE258FC376ACD171E506639518CF51565557FE11D8143EE2635006E15B41A32C4B6DDCC4F0CDCA9927919304C87E6C019F98E3FA559F0C04920B865E5959B0770278C7B7B7E75BCDA38EDA9D02DD202777071D0FF00BA3FC6A69523314830B9033BB8C11CF1FAD723B33A75486A490CB6B19010EE41807FDDFAD392DA2DA9C638E39E9D3FC687E635276B987750DB3F8AECF0AA0AC47A05E873FD4525C5BC47C630B23804403032391F4C7A9A9D0EC4DAA56454F12DB23CD6E0386065404704632727AFA1A8BC4F0EC9ED7FD208C4B18241038C9CF6F415D3496879D39B4745671B9B48889060D16904E9649B25EC1B1C7183513DC21365868E7F3226F3D4155C90140C9CE71F9545FBE330572A0B0CE7238EDFCAA6D734E77622BB8645D3AE0193A6E6246381923D7D0532E639469F704C836846CFB7CA7359491AD19BB9534585C7866242DC8423A0F53FE15168626FEC050D3062A1BB838E4E3A56B6D4AC44DF52AE88B326A7789B87CB2646473FCFEB51E933CBFDAF720A00C5C9FBC0F5071FCB35D4D7BA70B95CEB55A619E99E01E9D38FE86A267958EE51861F4E9C7F415C86BCC30B4EF081F2A1DC49C62986295D5F9C6338FCC7F8D1A0F98C4D66129AAE9AD30523CCE9B7AF23F5E78A76B56C82FF004C95DB72F9E7A6DE060F1F8673F5A773A69BBC49BC476D11D25D0E0E57AFA7403BFA9A975EB50DA4B323312179FC58FF008E6B4858E3949F317B4A8A26B152BC023239F6E0F5A8B4484C9A74597214A000D5565622323525886114B152182F5EFB7FFD7F95364B7668946E23E7041CF43FE73F9D73D8D948B0D1C86290291F30E491C72011FA9A4F2E456CF99804631DBA9FF1A866B19D9985A3B4BFD9AF0C8A1845315247604FD7D41A4D1BCE5B9BF89D146655F957A7030DF9800D5C59AD7574686973892D0AEC6DD0B6C6CAF4393FE1542C678ADF55B9474D81B6B956039040FF00EB9AD9AE64705ECCE873F36D0578E47BF34D2B9F97238C9E9ED5CEB466DE86269015357D41B6AF0FD4E4771EFEDFAD33465517DA97211B78C8C74CAFD6AD2F70E8AADD8A778A7FE12383764923E5F94E73B87FF5FF005F5A5BC588F8920DD83C06E00C8C81C7F3FCEBAA1FC33CB96ACE9518796B9F2C1C0E3142A00A011938FEF8AC2E3B33FFD1F75BD9AE64F1669ABB1115626E5CF4F980E46307207AFF005CCD7FBA2F13E9B8DC119194A8538CF7E831FE7EA6A9491AD3578995E2DB6711297B878D164418246D3F788FC4123A8F7EBCD5CF16890D93343E5A100316E4719381F28EDB8D75C248F3E717CDA9A7A75B62CA31E7498C90D923DB1D00EF8FCEA5D3199ECD81DE1B18C367B0C1EDF4FCAA2A32208B26D2DCCB1BB2A72738383CF5FCFA9A9CB282A19F951C92DDFDBD781FA1AC1B3A5223F22105B11468C4062A1060718FC7D31D2A69360DE4E0871F3B0C9C7B0FC69B7A17F68E7FC35B50EA31B22131DC31385008C9CF23E87AFBFD72ED1415BDD532186C9B680720019E3195EE49E7F0E2A69B6D9D156DCA6337CBE308C16440E4A92A509049241391CE09FCF071C53DD99BC683F77F3ECFBACCCBB8E78CFE3C64F3F8715DA9E8795256676A802ECF2DF0C33851852A70391D3B1F534E4DECA814839C7739CF19FD075AE57B9D0B618B808F86F95481F291C0073C67F5A54E602C7247DDDDC81D07A739E7A516BA068C0F139C7F67C60A822E5492AC304E41391DFB9E7D29FE22506E74A8589E262410D8E769EF8C77C75150ACDEA76D056D85F14911F87A678C2FCA8428C8E3E5C01CF5FBCBDE9BE2B93FE24D731A637B23A8539E7236FF009FFF005676A4EE70623717C34E9FD951B46C08DB93B4AF23A8231F5352E8055B488D83755F9B693D4AF18C8EDD3EB9ABABA930D8D5908F209DC4B01B54F727038FFEB53A470AC8328B87073D790391F5E2B9EF736B0D631E5B6927F8472327AE3F4A76E0A7208EC49CF5201FF1C734D0D239BB321BC637D20561E5C5E5E700ED00820FA8E41E3DB3EF4694E1FC47A94BF2EF8CAC6A060E7049181D4743D33D68A76676547CB4D3451F108B77D76D60C2B1F34B818538EFDFDB3D293536697C4F671E549C33E5B181C1C9E4770C3D3A574434D8F29ECDA4751029F2151636C8C10081D875A9620A600082AA0E76820E38E3F4EF58CDDCDA29A201B7ED118F270A141DCE01E483C727DAA6608F7476E0284048E0024E0FF2A946893E854BBC456370D22E176F24803185E4FEB9A8F5621F44BA2BB7F79195F9BB863B7033DF935954D91AD38DD99DA1875F0D4646D32323330C71923A75E9D7FC8A9349743E1A836904B26EEAB8231D4119E3AF3ED5BB4EE8788F756A65E8BF3F88EF9B39C9072171F519CF5FC3FA541E1C9EDDF56BB759D0B798A782849C0CFE7D8FBD757438D29754763B58AB124818DA063D3FA7BD5586E6265DCE0A74055B1919EDFCFF002AE3BEA6F18B2C2AA088FCDD642D951D72474E79E9510BA1E580226208E303A77A1DD6C3716E3739FF001336F9B4E8F70F9E7427F0F7CFD07FFAAABF882E33ACE990AC2C487E305782AA4A9C7A649FF38CCA9347552A6F92ECB7E207F2AC1C942E141270B8C71DB9159BE2A9E53A69C5A062549DA76E48240EFE99ADE17D59E7D68EA59D111A3F0EA6D52A7CA507E5E01C119C64FAD41A4C927F645B0311559B192761C64E7F84EDF4FCEB79EB7223EEA3ABB4DF05A46244180BBB780319C0C77E9C0AB113C654038DC70A338E0E6B9A52B9B535A0E27299E49073C7B521821754D8A0601DA00159EC6B1B2665691E6BDCDEA4EC5955C940C0F425BDF1D4E3E955B4E8A54F106A0A9206000ECA382411D393C83D734A3D4EAAB15C972AB061E2B54D9C6C1C01CE01C7AFB1A80B32F8B5898493E56728148EBC9F6E36FE75D6ADA9E64A3EEDCEBD02F98BEB80338FD3F5155CDCB0CBF94F9F4001EDD7AFB9AE466B1F3244C34B280A43103D7E99EB50C57244930309211B3803BF4E39E9414D5CA1E220D0E84ECBBB79603182475F406ABF896E233A1BA30CE5954E71C02DCF0695CE9A31D5A2D5DEF5D1942BE42AA919CF200C5326B883FB1A2625B69419C83C8C76F4AD6273575A3652F0A876B72ECBD5BD0F386F727EB517851A37B0DCAC39E48DA38F5FD6BA27B1CFD763A7727C9750A0865200C77FFF005535E54F2599641EBDBFCF4AE56743D8957211015E7B7A9E9FE14AAF9DAC8461B1DC75009FE94A7D056F74C0CAC9E2F4F94802DC0C61BA658FF5A46057C5A981CB41D31D87A7FDF42947A9D767ECCAFE23205CDA06279907DDCF23E6F4F4C533C4ADFE936C0A919986D233C02315D1493B1E74F768E86D8036A9C7DEC0C60F5EB4EB46DD6A8CC3B0C75F5A896E149BE5B8F38F3C63A00704E7D6959C071B07F0903241FF003DFF002ACD3374B42BDD30365700E78439E3AF5FF1A7DCAAFD9E6CA7182381F8544874DD9991A2C87FB2497273B9877E473EBF5A8B4271FD887209E48C9CF1EDCD6BD4789B9574C63FDB57071C920F7E3A7F87E468D39C2F882E739CAE0F00F3FE78AEAB68715DA3A70109040C0C0C7B0EF9A54283664F1B703DF9EF5CAD9BA52EC22604637236411F8723FC29434656439070C41E7D38A92B95F63135EC22D9C814931CDBBA9C607DEA7F889E21A724A5800B20C75FBD8C5173A28AD0B5AA465B4D7183D31D4F3E94EBD2A747F95F6EF4C64E78AD62CE5AA9A7B09E1D2AFA7A0D9C6DEC4FF8545E193BEC51D720F2C3AE0E4107F955562217371B0632EA091D40F7A048C22E7F87935CECD87E5438E383C0FAF3FE140C0D807B01F883FE34325FC46358055D6F504E8D918E3A9C1C76F7C510306F125CAED208887CDCE3AF34D1D352FCA57BA8D62F1246C808DD1B648E80803DBD9851A9129AC5BCD9F91B2B8C1C649E9FE7D7DABA21B1C123662F331F27CC3EF00DE98A40C530E33D391CFAF4AC25B9A424BA985A3B4DFDA7A8C8200B9971C1EB83CF6A7693745754D4328C7F7A73F29E7A1E3F034D3F70EEA8D34665F4938F13C38832704E43819000C76F534B7D7D18F135B7CACA549519DDEE3FF4235D70FE19E6CDA4CE9D67728098B048CF4FFEB5097B16C5C06E9E86B0B8BDA23FFFD2F71D72731DF595CAA2F98AED83CF71FE7FFD5517883FD659FF00BC7FA5423AE8FC259F120DDA43C849C94DD8CF19CD1E22FF00901BFF00D733FCEBB2079757E226D0A6696C6453F28F308E09FEF7B9ED517877FE3CE4FF00AE9FFB3D150CE06E447700BB42800631DB81FE7F014907DE1F41FC856323A0B671F39DA38CFB74A43D24FF008152E835F1183A52E2FF0059C31FF5AA3F3E7F4FF3934BA57FC84359FF00AEB1D3A5B9D953E12940C0F88E5B864059570064E002D823AD360FF90D4FFEEAFF00E875D11D8F1DEE75218F4C631C03939C7A5347DE3F5358F53AD6C3D3FD4A9C9CB4DB0F3DB73509FEA23FFAF9FF00D9DA9F407B983AD487FB6747CA82598BE791C909E9FF005D1AA3D6FF00E433A2FE1FFB46B9E3B9D94761BE2C9596CDD4018550475EE47F87EA699E2EFF008F59BFDC5FE75D144E1C46E68E953BC7A5281C80ED18CB13F2A92077F6E7EA6A2D37FE4163FEBB4BFF00A1B56950986C5DB8BC9D6E14823A7A7639E3E955EEBFD7AFFBA3FAD739D258379334E3247CC64F5E39AAFF00F2DE3FAC9FFA1531A317C3934B737BAD4D24AC3CBBB78C28385C0618FF00D08D33C29FEB35EFFAFE93FF00434ACA91E8623F8688A5899BC6A2337136D892465CBE7FBE31F4C28A964FF91E9FFEB9C9FCE4AEC81E52F859D5242593719A5C93D9FDF1FCAA58BFD4AFD7FF0066AE666BD088DB042C44D37CB195197ECBB80FD2A793FE5A7FBAFF00CDAAD1AC4C7D6AD94787A77F324DC38273D7041C9FC6A6D6BFE45AB8FA9FE9594F646987DCAB6B69141E1E86242C563452A09FA71FA9AB09FF002045FF00AE6BFF00B2D75BDD118D317C376D125CCEE4176F364196E4FCA700E7AD4FE1EFF5D3FF00D779BFF43AA7B1C92D8DD847CEEA38C33723A9CE3FC4D2C3FEB9FF00DE3FFB2D717508B17EE46BB4FDD5E3F03437FABFF809FF00D0AB4617F70E6F520CFE2DD3D4BB05485E40BC632377F9FF00229D7FFF00237D97FD7AC9FCDA9C4E88B7ECCABE261FB855048C90A4E73C6E14BE25FF00549FEF8FFD08554366704F72D69918D96AA09003B63F0071FC853F4CE96DFEFC9FC9AB797507B1BD34082DC11C128DE9DBA54B3FFC7B2FFB8F5CD13A21B150191193F7ACD807EF6287FBCBF434E40B73174F95FF00E125D48E47CB1EE0303A8C7F89A669FF00F2326A9FF5C0FF00ECB58AD99DB53F863587FC55E3382551467032410BFE14ADFF0023837D17FF004115D8BA9E7BF84E941F9907039C74F6A41F7E3FF7BFA5731B11C6A1259C8EC37F4F6FE54E5FF5971FEE7FECB4148C7F13408FA490401F394CE0671F954BE23FF9059FFAEC7F95247451F8991DCC109D2A363121C6D1D3B66A4B8FF90427D57FF42AD627256D9989E1AB5856CA2511AE1941380076CF6F7A9BC39FF1EB07FB83FF0041ADA7B099BC2D62FB2B0C1FBB538FF8F66FF72B9196F619F674119009181B47D39A94FF00AB6FAFF534E7D0D3EC9CE4CBB7C5D080DF7E123A0E3EEF4E3DCD3AE3FE46FB6FFAE47FF64A21D4EBFF009766678A5DD65B721BFE5AA0E83FC29BE2AFF596FF00F5D92BB296C7992F8A47436D7128B4419FE1CD456FFF001EA9FEE5613DC9A5F0934D2C8F70885B0189CE07D3FC4D31FF00E3EE2FA9FF00D96B24742D89594BBCC85CE0267A0EB9FA53BFE5B4FF00F5CC7F3A990E9EE657876089B4760C8085775E9D463BD49E1CFF0090449FF5D5FF009552DCD71253B38A38FC4175B635E4AF6FF3EA69F6DFF2305CFD57FA5753D8F364748238CF969B060AE7F4A70FF591FF00B9FF00B2D731B21FE5468D246ABF2827F46A7B7FAF97EADFFA15055CC0F114317F65FF00AB5F99C03C0FEEEEFE752788BFE416BFF5D07FE8BA83A30FB13DDDB443497651B4951D2A6BBFF90337FBA2B589357731FC370A43A445B074DB8CFB939A9340FF0090427FC03F99AD6B18C4D269E510F0DD8544FF00EA7F2AE766C59827919C216E037F5A8EDFFD70FF007BFF0066A6CCFED1936B7B30F14C8B853985092739E4B67BFF00B0B55ADBFE46D7FF00AE11FF003928476CFE12EEB77127DA60518016638C7FBA7FC4D43ADFFC7DC7FF005D8FFE802BA29EC79B33A80C7EC719EE7AD20FF8F18BEB58CB726254B38D23BDBE651825877E9CB0FE429D6DFF001F779F51FF00A1350BE03AA5B187AAC510F14409B3232CB924E7F9D3F56FF91B60FF0079ABAE1FC33CDABB9D2A469E5AFCA3A0A7A7FAB5FA0AE63A1247FFD9','JOSEDIAZ','2017-06-25 18:00:47.209000',NULL,NULL,0);

/*!40000 ALTER TABLE `documentoempleado` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table educacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `educacion`;

CREATE TABLE `educacion` (
  `IdEducacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `NivelEducacion` varchar(2) NOT NULL,
  `Institucion` varchar(100) NOT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `Titulo` varchar(200) DEFAULT NULL,
  `Descripcion` varchar(250) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdEducacion`),
  KEY `fkEmpleado_Educacion` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_Educacion` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table empleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `empleado`;

CREATE TABLE `empleado` (
  `IdEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `ApellidoPaterno` varchar(50) NOT NULL,
  `ApellidoMaterno` varchar(50) NOT NULL,
  `TipoDocumento` varchar(2) NOT NULL,
  `NumeroDocumento` varchar(100) NOT NULL,
  `Genero` varchar(2) NOT NULL,
  `EstadoCivil` varchar(2) NOT NULL,
  `GrupoSangre` varchar(2) NOT NULL,
  `Discapacitado` tinyint(1) NOT NULL,
  `FechaNacimiento` datetime(6) NOT NULL,
  `PaisNacimiento` varchar(2) NOT NULL,
  `DepartamentoNacimiento` varchar(2) DEFAULT NULL,
  `ProvinciaNacimiento` varchar(2) DEFAULT NULL,
  `DistritoNacimiento` varchar(50) DEFAULT NULL,
  `Codigo` varchar(20) NOT NULL,
  `EmailInterno` varchar(150) DEFAULT NULL,
  `TelefonoInterno` varchar(50) DEFAULT NULL,
  `AnexoInterno` varchar(50) DEFAULT NULL,
  `IdCentroCosto` int(11) NOT NULL,
  `ContratoTrabajo` varchar(2) DEFAULT NULL,
  `RegimenHorario` varchar(2) DEFAULT NULL,
  `RegimenLaboral` varchar(2) DEFAULT NULL,
  `EsPersonalDeConfianza` tinyint(1) NOT NULL,
  `TipoTrabajador` varchar(2) NOT NULL,
  `EntidadBancariaSueldo` varchar(2) DEFAULT NULL,
  `CTS` varchar(2) DEFAULT NULL,
  `AFP` varchar(2) DEFAULT NULL,
  `TieneEPS` tinyint(1) DEFAULT NULL,
  `EPS` varchar(2) DEFAULT NULL,
  `TelefonoCasa` varchar(50) DEFAULT NULL,
  `TelefonoCelular` varchar(50) NOT NULL,
  `TelefonoAdicional` varchar(50) DEFAULT NULL,
  `EmailPersonal` varchar(150) DEFAULT NULL,
  `TipoDomicilio` varchar(2) NOT NULL,
  `DireccionDomicilio` varchar(200) NOT NULL,
  `PaisDomicilio` varchar(2) NOT NULL,
  `DepartamentoDomicilio` varchar(2) DEFAULT NULL,
  `ProvinciaDomicilio` varchar(2) DEFAULT NULL,
  `DistritoDomicilio` varchar(50) DEFAULT NULL,
  `NombreContactoEmergencia` varchar(100) DEFAULT NULL,
  `EmailContactoEmergencia` varchar(150) DEFAULT NULL,
  `TelefonoContactoEmergencia` varchar(50) DEFAULT NULL,
  `RelacionContactoEmergencia` varchar(2) DEFAULT NULL,
  `FechaIngreso` datetime(6) NOT NULL,
  `FechaRenuncia` datetime(6) DEFAULT NULL,
  `MotivoRenuncia` varchar(2) DEFAULT NULL,
  `FechaCese` datetime(6) DEFAULT NULL,
  `IdEmpresa` int(11) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdEmpleado`),
  KEY `fkEmpresa_Empleado` (`IdEmpresa`),
  KEY `fkCentroCosto_Empleado` (`IdCentroCosto`),
  CONSTRAINT `fkCentroCosto_Empleado` FOREIGN KEY (`IdCentroCosto`) REFERENCES `centrocosto` (`IdCentroCosto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpresa_Empleado` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;

INSERT INTO `empleado` (`IdEmpleado`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `TipoDocumento`, `NumeroDocumento`, `Genero`, `EstadoCivil`, `GrupoSangre`, `Discapacitado`, `FechaNacimiento`, `PaisNacimiento`, `DepartamentoNacimiento`, `ProvinciaNacimiento`, `DistritoNacimiento`, `Codigo`, `EmailInterno`, `TelefonoInterno`, `AnexoInterno`, `IdCentroCosto`, `ContratoTrabajo`, `RegimenHorario`, `RegimenLaboral`, `EsPersonalDeConfianza`, `TipoTrabajador`, `EntidadBancariaSueldo`, `CTS`, `AFP`, `TieneEPS`, `EPS`, `TelefonoCasa`, `TelefonoCelular`, `TelefonoAdicional`, `EmailPersonal`, `TipoDomicilio`, `DireccionDomicilio`, `PaisDomicilio`, `DepartamentoDomicilio`, `ProvinciaDomicilio`, `DistritoDomicilio`, `NombreContactoEmergencia`, `EmailContactoEmergencia`, `TelefonoContactoEmergencia`, `RelacionContactoEmergencia`, `FechaIngreso`, `FechaRenuncia`, `MotivoRenuncia`, `FechaCese`, `IdEmpresa`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(2,'WALTER','AGUILAR','ZEGARRA','DI','25342525','M','S','O',0,'2000-03-14 00:00:00.000000','PE',NULL,NULL,NULL,'936','jamdiazdiaz@gmail.com','','',45,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','CL','LA PERLA',NULL,NULL,NULL,NULL,'2016-06-15 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,'NELSSON JOSE','AGUILAR','SALVADOR','DI','25342525','M','S','O',0,'2000-03-14 00:00:00.000000','PE',NULL,NULL,NULL,'889','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-09-10 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000','cron-usr','2017-02-28 11:27:35.079000',5),
	(4,'DIEGO ELADIO','AGUIRRE','RABANAL','DI','25342525','M','S','O',0,'1988-09-15 00:00:00.000000','PE',NULL,NULL,NULL,'943','jamdiazdiaz@gmail.com','','',45,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2016-08-08 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,'YESENIA','AGUIRRE','DIAZ','DI','25342525','F','S','O',0,'1988-03-17 00:00:00.000000','PE',NULL,NULL,NULL,'858','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2014-07-08 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,'JHONATTAN','ALARCON','MALDONADO','DI','25342525','M','S','O',0,'2000-08-06 00:00:00.000000','PE',NULL,NULL,NULL,'851','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CALLAO',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,'DIEGO','ALFARO','VIVES','DI','25342525','M','S','O',0,'2000-07-03 00:00:00.000000','PE',NULL,NULL,NULL,'937','jamdiazdiaz@gmail.com','','',37,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,'ERNESTO','ANGELES','VALVERDE','DI','25342525','M','S','O',0,'1978-01-02 00:00:00.000000','PE',NULL,NULL,NULL,'358','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2005-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,'CESAR JESUS','ANGULO','GASCO','DI','25342525','M','S','O',0,'1992-11-14 00:00:00.000000','PE',NULL,NULL,NULL,'910','jamdiazdiaz@gmail.com','','',45,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN BORJA',NULL,NULL,NULL,NULL,'2016-03-07 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,'DANIEL','ARELLANO','MONTOYA','DI','25342525','M','S','O',0,'1973-07-22 00:00:00.000000','PE',NULL,NULL,NULL,'347','jamdiazdiaz@gmail.com','','',45,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2007-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000','cron-usr','2017-02-27 12:09:26.683000',2),
	(11,'ENRIQUE YOBAR','ARENAS','COHA','DI','25342525','M','S','O',0,'1971-07-11 00:00:00.000000','PE',NULL,NULL,NULL,'28','jamdiazdiaz@gmail.com','','',29,'IN','PG','TC',1,'EM',NULL,NULL,'HA',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'1999-07-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,'DANIEL JUAN','ARIAS','ASTOLA','DI','25342525','M','S','O',0,'1975-12-12 00:00:00.000000','PE',NULL,NULL,NULL,'739','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2010-01-04 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,'JUAN CARLOS','ARRUE','CHAVEZ','DI','25342525','M','S','O',0,'1976-10-21 00:00:00.000000','PE',NULL,NULL,NULL,'861','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CALLAO',NULL,NULL,NULL,NULL,'2014-10-16 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(14,'EDGAR AUGUSTO','ASPIROS','MIRANDA','DI','25342525','M','S','O',0,'1992-01-10 00:00:00.000000','PE',NULL,NULL,NULL,'930','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MARTIN DE PORRES',NULL,NULL,NULL,NULL,'2016-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(15,'KEVIN','ASTETE','CARPIO','DI','25342525','M','S','O',0,'2000-01-10 00:00:00.000000','PE',NULL,NULL,NULL,'915','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','VILLA EL SALVADOR',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(16,'JAQUELINE','BACA','RIVERA','DI','25342525','F','S','O',0,'1973-03-15 00:00:00.000000','PE',NULL,NULL,NULL,'70','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',1,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2004-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(17,'SILVIA ELENA','BARBA','QUEZADA','DI','25342525','F','S','O',0,'1985-04-26 00:00:00.000000','PE',NULL,NULL,NULL,'897','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MIRAFLORES',NULL,NULL,NULL,NULL,'2015-11-02 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(18,'NILTON CESAR','BENITES','PORRAS','DI','25342525','M','S','O',0,'2016-08-15 00:00:00.000000','PE',NULL,NULL,NULL,'946','jamdiazdiaz@gmail.com','','',31,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LOS OLIVOS',NULL,NULL,NULL,NULL,'2016-08-16 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(19,'ROGER','BOBBIO','CAM','DI','25342525','M','S','O',0,'1980-02-01 00:00:00.000000','PE',NULL,NULL,NULL,'73','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2000-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(20,'GIANCARLO','BORJAS','GIRALDO','DI','25342525','M','S','O',0,'1988-09-16 00:00:00.000000','PE',NULL,NULL,NULL,'929','jamdiazdiaz@gmail.com','','',25,'IN','PG','TC',1,'EM','CR','TF','I2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI',NULL,NULL,NULL,NULL,NULL,NULL,'2016-05-25 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 13:56:23.496000',49),
	(21,'ALONSO','BUSTOS','HERRERA','DI','25342525','M','S','O',0,'1986-11-22 00:00:00.000000','PE',NULL,NULL,NULL,'442','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2009-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(22,'LUPE','CALERO','VALDIVIA','DI','25342525','F','S','O',0,'2000-06-23 00:00:00.000000','PE',NULL,NULL,NULL,'905','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','INDEPENDENCIA',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(23,'HUGO','CAM','TERRY','DI','25342525','M','S','O',0,'1965-08-28 00:00:00.000000','PE',NULL,NULL,NULL,'2','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN ISIDRO',NULL,NULL,NULL,NULL,'1997-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(24,'MARGARITA','CARBAJAL','CAVERO','DI','25342525','F','S','O',0,'1973-06-21 00:00:00.000000','PE',NULL,NULL,NULL,'56','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA MOLINA',NULL,NULL,NULL,NULL,'2000-03-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(25,'BETSY LITA','CARDAMA','BARCO','DI','25342525','M','S','O',0,'1988-11-12 00:00:00.000000','PE',NULL,NULL,NULL,'931','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'NO',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA VICTORIA',NULL,NULL,NULL,NULL,'2016-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(26,'JOSE','CARDENAS','GUTIERREZ','DI','25342525','M','S','O',0,'2000-06-10 00:00:00.000000','PE',NULL,NULL,NULL,'925','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SURQUILLO',NULL,NULL,NULL,NULL,'2016-05-16 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(27,'OSCAR','CASTILLO','PAMPAS','DI','25342525','M','S','O',0,'1983-01-15 00:00:00.000000','PE','LI','LI','Jesús María','928','jamdiazdiaz@gmail.com','4177100','533',39,'IN','PG','TC',1,'EM','CR',NULL,'PR',1,'RI','7024512','987789782',NULL,'oscar.alexander@gmail.com','PR','Direccion de domicilio','PE','LI','LI','LOS OLIVOS','Diana Ocaña Cruz','dianadoc_35@gmail.com','969969050','CV','2016-05-23 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000','cron-usr','2017-03-06 12:58:32.824000',24),
	(28,'JOSE','CASTILLO','CARDENAS','DI','25342525','M','S','O',0,'1991-02-24 00:00:00.000000','PE',NULL,NULL,NULL,'870','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SURQUILLO',NULL,NULL,NULL,NULL,'2015-03-09 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(29,'CRISTIAN GERALD','CCORI','UGARTE','DI','25342525','M','S','O',0,'2000-04-23 00:00:00.000000','PE',NULL,NULL,NULL,'940','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LINCE',NULL,NULL,NULL,NULL,'2016-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(30,'LUIS MIGUEL','CHAVEZ','ZUMARAN','DI','25342525','M','S','O',0,'1984-01-21 00:00:00.000000','PE',NULL,NULL,NULL,'566','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LOS OLIVOS',NULL,NULL,NULL,NULL,'2009-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(31,'ALAN RICARDO','CHAYAN','SILVESTRE','DI','25342525','M','S','O',0,'1985-07-14 00:00:00.000000','PE',NULL,NULL,NULL,'721','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN JUAN DE MIRAFLORES',NULL,NULL,NULL,NULL,'2009-10-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(32,'ELARD FAVIO','CHILO','BERNUY','DI','25342525','M','S','O',0,'2000-05-22 00:00:00.000000','PE',NULL,NULL,NULL,'953','jamdiazdiaz@gmail.com','','',37,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','EL AGUSTINO',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(33,'FREDDY','CHOMBO','HIDALGO','DI','25342525','M','S','O',0,'1976-06-26 00:00:00.000000','PE',NULL,NULL,NULL,'282','jamdiazdiaz@gmail.com','','',29,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2009-06-22 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(34,'ESTHER','CONTRERAS','GALVAN','DI','25342525','F','S','O',0,'1989-03-05 00:00:00.000000','PE',NULL,NULL,NULL,'932','jamdiazdiaz@gmail.com','','',35,'IN','PG','TC',0,'EM',NULL,NULL,'RI',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','CL','CALLAO',NULL,NULL,NULL,NULL,'2016-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(35,'ESTANISLAO','CONTRERAS','CHAVEZ','DI','25342525','M','S','O',0,'1974-07-31 00:00:00.000000','PE',NULL,NULL,NULL,'43','jamdiazdiaz@gmail.com','','',41,'IN','PG','TC',1,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000','cron-usr','2017-03-14 16:35:49.417000',7),
	(36,'DIEGO','CORRADA','','DI','25342525','M','S','O',0,'2000-04-02 00:00:00.000000','PE',NULL,NULL,NULL,'947','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CHORRILLOS',NULL,NULL,NULL,NULL,'2016-09-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(37,'ROGER','CRUZ','ROJAS','DI','25342525','M','S','O',0,'1984-11-25 00:00:00.000000','PE',NULL,NULL,NULL,'389','jamdiazdiaz@gmail.com','','',27,'IM','PG','TC',1,'EM',NULL,NULL,'I2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2007-12-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(38,'JAVIER RICHARD','CUICAPUZA','ANTONIO','DI','25342525','M','S','O',0,'2000-11-18 00:00:00.000000','PE',NULL,NULL,NULL,'948','jamdiazdiaz@gmail.com','','',25,'IN','PG','TC',0,'EM',NULL,NULL,'P2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN BORJA',NULL,NULL,NULL,NULL,'2016-09-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(39,'ENRIQUE','DE LA PIEDRA','GUTIERREZ','DI','25342525','M','S','O',0,'2000-04-02 00:00:00.000000','PE',NULL,NULL,NULL,'951','jamdiazdiaz@gmail.com','','',30,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(40,'OSCAR ALESSANDRO','DE PIEROLA','ARDELA','DI','25342525','M','S','O',0,'1988-10-03 00:00:00.000000','PE',NULL,NULL,NULL,'865','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2015-02-02 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(41,'JOSE AMADEO MARTIN','DIAZ','DIAZ','DI','25342525','M','S','O',0,'1974-11-06 00:00:00.000000','PE',NULL,NULL,NULL,'916','jamdiazdiaz@gmail.com','','',25,'IN','PG','TC',0,'EM',NULL,NULL,'P2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','CL','VENTANILLA',NULL,NULL,NULL,NULL,'2016-04-04 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(42,'JULISSA','DIAZ','AMPUERO','DI','25342525','F','S','O',0,'1973-04-27 00:00:00.000000','PE',NULL,NULL,NULL,'421','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',1,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA MOLINA',NULL,NULL,NULL,NULL,'2008-09-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(43,'JOSE LUIS','DIAZ','GUTIERREZ','DI','25342525','M','S','O',0,'1985-08-15 00:00:00.000000','PE',NULL,NULL,NULL,'903','jamdiazdiaz@gmail.com','','',38,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','BREÑA',NULL,NULL,NULL,NULL,'2016-01-25 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(44,'JESUS','DIAZ','AVILA','DI','25342525','M','S','O',0,'1970-04-26 00:00:00.000000','PE',NULL,NULL,NULL,'10','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN BORJA',NULL,NULL,NULL,NULL,'1998-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(45,'XIOMI','EGUSQUIZA','ESCRIBA','DI','25342525','M','S','O',0,'1994-10-04 00:00:00.000000','PE',NULL,NULL,NULL,'924','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','CL','CALLAO',NULL,NULL,NULL,NULL,'2016-05-10 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(46,'DAVID','ESCALANTE','DURAND','DI','25342525','M','S','O',0,'1975-05-08 00:00:00.000000','PE',NULL,NULL,NULL,'772','jamdiazdiaz@gmail.com','','',29,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','JESUS MARIA',NULL,NULL,NULL,NULL,'2010-11-08 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(47,'CARLOS','ESPINO','MARAVI','DI','25342525','M','S','O',0,'1989-04-25 00:00:00.000000','PE',NULL,NULL,NULL,'933','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','PUEBLO LIBRE',NULL,NULL,NULL,NULL,'2016-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(48,'JOSE','ESPINOZA','DELGADO','DI','25342525','M','S','O',0,'2000-03-19 00:00:00.000000','PE',NULL,NULL,NULL,'911','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','JESUS MARIA',NULL,NULL,NULL,NULL,'2016-03-07 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(49,'YOHANA','ESPINOZA','SOLORZANO','DI','25342525','F','S','O',0,'1988-08-10 00:00:00.000000','PE',NULL,NULL,NULL,'881','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MIRAFLORES',NULL,NULL,NULL,NULL,'2015-07-21 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(50,'MARCO','ESTRELLA','AMARO','DI','25342525','M','S','O',0,'1969-01-31 00:00:00.000000','PE',NULL,NULL,NULL,'18','jamdiazdiaz@gmail.com','','',26,'IN','PG','TC',1,'EM',NULL,NULL,'HA',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','JESUS MARIA',NULL,NULL,NULL,NULL,'1999-03-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(51,'KENYA','FERNANDEZ','CASTILLO','DI','25342525','M','S','O',0,'1986-02-02 00:00:00.000000','PE',NULL,NULL,NULL,'895','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2015-10-15 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(52,'KIEFER','FERNANDEZ','VALLADOLID','DI','25342525','M','S','O',0,'1992-01-17 00:00:00.000000','PE',NULL,NULL,NULL,'867','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MARTIN DE PORRES',NULL,NULL,NULL,NULL,'2015-02-09 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(53,'LUIS FERNANDO','FUENTES','PAREDES','DI','25342525','M','S','O',0,'1984-02-23 00:00:00.000000','PE',NULL,NULL,NULL,'871','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'P2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','COMAS',NULL,NULL,NULL,NULL,'2015-03-24 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(54,'JORGE','GALINDO','ARAUCO','DI','25342525','M','S','O',0,'1974-04-12 00:00:00.000000','PE',NULL,NULL,NULL,'8','jamdiazdiaz@gmail.com','','',33,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'1997-10-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(55,'ANTONIO','GALVEZ','GALVEZ','DI','25342525','M','S','O',0,'1970-08-15 00:00:00.000000','PE',NULL,NULL,NULL,'97','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2001-04-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(56,'ANDRES','GARCIA','FALCON','DI','25342525','M','S','O',0,'2000-01-10 00:00:00.000000','PE',NULL,NULL,NULL,'892','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MAGDALENA DEL MAR',NULL,NULL,NULL,NULL,'2015-10-12 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(57,'JORGE JAVIER','GARCIA','JUNES','DI','25342525','M','S','O',0,'1985-10-28 00:00:00.000000','PE',NULL,NULL,NULL,'882','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','JESUS MARIA',NULL,NULL,NULL,NULL,'2015-08-03 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(58,'VIACHESLAV','GUEVARA','TARAMONA','DI','25342525','M','S','O',0,'2000-06-13 00:00:00.000000','PE',NULL,NULL,NULL,'944','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA VICTORIA',NULL,NULL,NULL,NULL,'2016-08-15 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(59,'SISSI','HIDALGO','ALEMAN','DI','25342525','F','S','O',0,'1973-12-21 00:00:00.000000','PE',NULL,NULL,NULL,'353','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2005-01-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(60,'MIGUEL ANGEL','ISA','MATAYOSHI','DI','25342525','M','S','O',0,'1978-01-14 00:00:00.000000','PE',NULL,NULL,NULL,'908','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MAGDALENA DEL MAR',NULL,NULL,NULL,NULL,'2016-02-15 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(61,'ELTON HAMILTON','LARA','FERREL','DI','25342525','M','S','O',0,'1985-05-09 00:00:00.000000','PE',NULL,NULL,NULL,'898','jamdiazdiaz@gmail.com','','',43,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','BARRANCO',NULL,NULL,NULL,NULL,'2015-11-02 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(62,'MARJORIE','LEON','GASTAÑADUY','DI','25342525','F','S','O',0,'1987-11-10 00:00:00.000000','PE',NULL,NULL,NULL,'848','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MIRAFLORES',NULL,NULL,NULL,NULL,'2015-02-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(63,'HECTOR','LOPEZ','BURGOS','DI','25342525','M','S','O',0,'2000-10-05 00:00:00.000000','PE',NULL,NULL,NULL,'927','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CHORRILLOS',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(64,'DORINA CONSUELO','LUCAS','FALCON','DI','25342525','F','S','O',0,'1972-01-08 00:00:00.000000','PE',NULL,NULL,NULL,'280','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2009-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(65,'HECTOR','LUJAN','TERRY','DI','25342525','M','S','O',0,'2000-11-15 00:00:00.000000','PE',NULL,NULL,NULL,'920','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN ISIDRO',NULL,NULL,NULL,NULL,'2016-05-02 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(66,'RAUL EDUARDO','MAGUIÑA','CASTILLO','DI','25342525','M','S','O',0,'1979-04-15 00:00:00.000000','PE',NULL,NULL,NULL,'780','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2011-04-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(67,'FRANK','MAMANI','VÉLIZ','DI','25342525','M','S','O',0,'1987-05-06 00:00:00.000000','PE',NULL,NULL,NULL,'843','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'','Direccion de domicilio','PE',NULL,NULL,'',NULL,NULL,NULL,NULL,'2013-10-14 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(68,'CESAR','MARCHENA','MIRANDA','DI','25342525','M','S','O',0,'1975-12-07 00:00:00.000000','PE',NULL,NULL,NULL,'57','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN BORJA',NULL,NULL,NULL,NULL,'2000-03-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(69,'ALDO','MARTINEZ','ALARCON','DI','25342525','M','S','O',0,'1982-04-04 00:00:00.000000','PE',NULL,NULL,NULL,'436','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'NO',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2009-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(70,'RODRIGO','MATTEO','CASTILLO','DI','25342525','M','S','O',0,'1976-06-02 00:00:00.000000','PE',NULL,NULL,NULL,'357','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2006-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(71,'IGOR','MEDINA','GARCIA','DI','25342525','M','S','O',0,'2000-03-05 00:00:00.000000','PE',NULL,NULL,NULL,'883','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MARTIN DE PORRES',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(72,'PEDRO','MENDEZ CHACÓN','RODRÍGUEZ','DI','25342525','M','S','O',0,'1980-04-05 00:00:00.000000','PE',NULL,NULL,NULL,'913','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MIRAFLORES',NULL,NULL,NULL,NULL,'2016-04-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(73,'ALFREDO','MENDIOLA','LOYOLA','DI','25342525','M','S','O',0,'2000-12-21 00:00:00.000000','PE',NULL,NULL,NULL,'803','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(74,'LUIS EDUARDO','MIRANDA','ALVA','DI','25342525','M','S','O',0,'2000-09-18 00:00:00.000000','PE',NULL,NULL,NULL,'909','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LOS OLIVOS',NULL,NULL,NULL,NULL,'2016-03-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(75,'YNES ADRIAN','MOLOCHE','CHAMBERGO','DI','25342525','F','S','O',0,'1989-05-01 00:00:00.000000','PE',NULL,NULL,NULL,'874','jamdiazdiaz@gmail.com','','',30,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CHORRILLOS',NULL,NULL,NULL,NULL,'2015-04-06 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(76,'MARCO','MONTENEGRO','CUEVA','DI','25342525','M','S','O',0,'2000-01-05 00:00:00.000000','PE',NULL,NULL,NULL,'922','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SURQUILLO',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(77,'FERNANDO','MORON','MOYANO','DI','25342525','M','S','O',0,'1971-06-14 00:00:00.000000','PE',NULL,NULL,NULL,'170','jamdiazdiaz@gmail.com','','',37,'IN','PG','TC',1,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2005-01-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(78,'NICOLAS','NAKASONE','ROQUE','DI','25342525','M','S','O',0,'2000-07-23 00:00:00.000000','PE',NULL,NULL,NULL,'952','jamdiazdiaz@gmail.com','','',24,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','PUEBLO LIBRE',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(79,'RICHARD','NEGRON','GONZALES','DI','25342525','M','S','O',0,'1983-07-25 00:00:00.000000','PE',NULL,NULL,NULL,'891','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LINCE',NULL,NULL,NULL,NULL,'2015-10-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(80,'JESSICA','NINAYPATA','GONZALES','DI','25342525','F','S','O',0,'1991-12-25 00:00:00.000000','PE',NULL,NULL,NULL,'901','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','BREÑA',NULL,NULL,NULL,NULL,'2016-01-18 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(81,'ISRAEL','NIZAMA','PILLACA','DI','25342525','M','S','O',0,'1973-05-02 00:00:00.000000','PE',NULL,NULL,NULL,'19','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'P2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'1999-03-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(82,'JIM','OCAÑA','CORNEJO','DI','25342525','M','S','O',0,'1982-02-18 00:00:00.000000','PE',NULL,NULL,NULL,'783','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN JUAN DE LURIGANCHO',NULL,NULL,NULL,NULL,'2011-04-25 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(83,'WALTER DANTE','ORTIZ','LLIMPE','DI','25342525','M','S','O',0,'2000-12-22 00:00:00.000000','PE',NULL,NULL,NULL,'954','jamdiazdiaz@gmail.com','','',41,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CHORRILLOS',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(84,'PEDRO','PALACIOS','AGUILAR','DI','25342525','M','S','O',0,'1986-08-20 00:00:00.000000','PE',NULL,NULL,NULL,'873','jamdiazdiaz@gmail.com','','',41,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LOS OLIVOS',NULL,NULL,NULL,NULL,'2015-03-25 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(85,'MARCO','PALOMINO','VASQUEZ','DI','25342525','M','S','O',0,'2000-08-01 00:00:00.000000','PE',NULL,NULL,NULL,'775','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2011-02-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(86,'URBANO','PALOMINO','PALMA','DI','25342525','M','S','O',0,'1988-08-01 00:00:00.000000','PE',NULL,NULL,NULL,'941','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','VILLA MARÍA DEL TRIUNFO',NULL,NULL,NULL,NULL,'2016-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(87,'JOSE','PAUCAR','VILLALOBOS','DI','25342525','M','S','O',0,'1988-03-24 00:00:00.000000','PE',NULL,NULL,NULL,'887','jamdiazdiaz@gmail.com','','',37,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CHORRILLOS',NULL,NULL,NULL,NULL,'2015-09-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(88,'MARCELA','PAZ','ADAUTO','DI','25342525','F','S','O',0,'1987-11-17 00:00:00.000000','PE',NULL,NULL,NULL,'862','jamdiazdiaz@gmail.com','','',30,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MIRAFLORES',NULL,NULL,NULL,NULL,'2014-10-23 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(89,'JORGE','PAZ','DIAZ','DI','25342525','M','S','O',0,'1988-08-30 00:00:00.000000','PE',NULL,NULL,NULL,'830','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','VILLA EL SALVADOR',NULL,NULL,NULL,NULL,'2013-01-07 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(90,'GONZALO','PAZOS','CARCELEN','DI','25342525','M','S','O',0,'1992-11-15 00:00:00.000000','PE',NULL,NULL,NULL,'876','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MIRAFLORES',NULL,NULL,NULL,NULL,'2015-04-20 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(91,'ANATOLY','PEDEMONTE','KU','DI','25342525','M','S','O',0,'2000-05-27 00:00:00.000000','PE',NULL,NULL,NULL,'955','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA MOLINA',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(92,'MANUEL VICENTE','PEÑA','REYES','DI','25342525','M','S','O',0,'2000-01-28 00:00:00.000000','PE',NULL,NULL,NULL,'32','jamdiazdiaz@gmail.com','','',24,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','INDEPENDENCIA',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(93,'JUAN CARLOS','PEÑA','CASTILLO','DI','25342525','M','S','O',0,'1988-01-27 00:00:00.000000','PE',NULL,NULL,NULL,'795','jamdiazdiaz@gmail.com','','',27,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2011-10-24 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(94,'VICTOR','POLO','TEJEDA','DI','25342525','M','S','O',0,'1981-06-24 00:00:00.000000','PE',NULL,NULL,NULL,'828','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'RI',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MARTIN DE PORRES',NULL,NULL,NULL,NULL,'2012-10-29 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(95,'PEDRO','PONCE','SUYO','DI','25342525','M','S','O',0,'2000-09-12 00:00:00.000000','PE',NULL,NULL,NULL,'192','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(96,'AUGUSTO','PRADO','MORALES','DI','25342525','M','S','O',0,'1970-06-07 00:00:00.000000','PE',NULL,NULL,NULL,'69','jamdiazdiaz@gmail.com','','',35,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2000-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(97,'ADOLFO','QUESQUEN','OSPINA','DI','25342525','M','S','O',0,'1967-11-29 00:00:00.000000','PE',NULL,NULL,NULL,'616','jamdiazdiaz@gmail.com','','',24,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2008-03-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(98,'EMILIO','QUISPE','LICLA','DI','25342525','M','S','O',0,'1950-04-05 00:00:00.000000','PE',NULL,NULL,NULL,'4','jamdiazdiaz@gmail.com','','',23,'IN','PG','TC',0,'EM',NULL,NULL,'EX',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'1997-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(99,'CARLOS','RAMIREZ','CABALLERO','DI','25342525','M','S','O',0,'1980-10-20 00:00:00.000000','PE',NULL,NULL,NULL,'716','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2009-09-14 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(100,'ELMER','RAMIREZ','CHAVEZ','DI','25342525','M','S','O',0,'1980-07-18 00:00:00.000000','PE',NULL,NULL,NULL,'785','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','INDEPENDENCIA',NULL,NULL,NULL,NULL,'2011-05-02 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(101,'DANTE','REBAGLIATI','SALAVERRY','DI','25342525','M','S','O',0,'2000-11-26 00:00:00.000000','PE',NULL,NULL,NULL,'1','jamdiazdiaz@gmail.com','','',23,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'','Direccion de domicilio','PE',NULL,NULL,'',NULL,NULL,NULL,NULL,'1997-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(102,'ALESSANDRA','REBAGLIATI','ORDOÑEZ','DI','25342525','F','S','O',0,'1988-03-02 00:00:00.000000','PE',NULL,NULL,NULL,'812','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',1,'EM',NULL,NULL,'HA',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2012-05-07 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(103,'GONZALO','RECABARREN','RIVERA','DI','25342525','M','S','O',0,'1979-07-30 00:00:00.000000','PE',NULL,NULL,NULL,'782','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2011-04-25 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(104,'JUAN CARLOS','REYES','SOLANO','DI','25342525','M','S','O',0,'2000-09-11 00:00:00.000000','PE',NULL,NULL,NULL,'942','jamdiazdiaz@gmail.com','','',25,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-08-03 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000','NEVHA.URDANEGUI','2017-03-17 14:07:02.403000',8),
	(105,'BERENICE ELVIRA','REYNA','HUAMAN','DI','25342525','F','S','O',0,'2000-12-14 00:00:00.000000','PE',NULL,NULL,NULL,'939','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN LUIS',NULL,NULL,NULL,NULL,'2016-07-18 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(106,'JUAN','RIVERA','REYNA','DI','25342525','M','S','O',0,'1970-07-28 00:00:00.000000','PE',NULL,NULL,NULL,'303','jamdiazdiaz@gmail.com','','',29,'IN','PG','TC',0,'EM',NULL,NULL,'HA',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','VILLA MARÍA DEL TRIUNFO',NULL,NULL,NULL,NULL,'2007-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(107,'JHONY','RIVERO','','DI','25342525','M','S','O',0,'1974-02-25 00:00:00.000000','PE',NULL,NULL,NULL,'907','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2016-02-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(108,'DIANA BELISSA','ROBLES','GARCIA','DI','25342525','F','S','O',0,'1985-09-02 00:00:00.000000','PE',NULL,NULL,NULL,'886','jamdiazdiaz@gmail.com','','',30,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','BREÑA',NULL,NULL,NULL,NULL,'2015-08-10 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(109,'FELIPE','ROJAS','RODRIGUEZ','DI','25342525','M','S','O',0,'1970-05-11 00:00:00.000000','PE',NULL,NULL,NULL,'16','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'1999-01-11 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(110,'ABEL JARED','ROMAN','CESPEDES','DI','25342525','M','S','O',0,'1993-04-16 00:00:00.000000','PE',NULL,NULL,NULL,'934','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'P2',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CARABAYLLO',NULL,NULL,NULL,NULL,'2016-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(111,'PAUL','ROMERO','GUZMAN','DI','25342525','M','S','O',0,'1977-08-04 00:00:00.000000','PE',NULL,NULL,NULL,'415','jamdiazdiaz@gmail.com','','',43,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2008-09-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(112,'MASSIEL','SAMMAME','MORENO','DI','25342525','M','S','O',0,'1985-04-02 00:00:00.000000','PE',NULL,NULL,NULL,'935','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'RI',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2016-06-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(113,'DAVID','SANDOVAL','FELANDRO','DI','25342525','M','S','O',0,'1978-07-07 00:00:00.000000','PE',NULL,NULL,NULL,'25','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA MOLINA',NULL,NULL,NULL,NULL,'1999-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(114,'EDUARDO','SARMIENTO','ERAUSQUIN','DI','25342525','M','S','O',0,'1982-02-25 00:00:00.000000','PE',NULL,NULL,NULL,'378','jamdiazdiaz@gmail.com','','',45,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2007-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(115,'VLADIMIR','SILVERIO','RIVERA','DI','25342525','M','S','O',0,'1994-01-06 00:00:00.000000','PE',NULL,NULL,NULL,'921','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'','Direccion de domicilio','PE',NULL,NULL,'',NULL,NULL,NULL,NULL,'2016-05-02 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(116,'YHAIR','TAMAYO','VILLAVICENCIO','DI','25342525','M','S','O',0,'1971-05-14 00:00:00.000000','PE',NULL,NULL,NULL,'835','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2013-04-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(117,'JHOFFRETT','TAPIA','TELLO','DI','25342525','M','S','O',0,'1982-07-22 00:00:00.000000','PE',NULL,NULL,NULL,'855','jamdiazdiaz@gmail.com','','',30,'IN','PG','TC',1,'EM',NULL,NULL,'RI',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2014-05-05 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(118,'JUAN CARLOS','TENORIO','HUAYTALLA','DI','25342525','M','S','O',0,'1989-03-16 00:00:00.000000','PE',NULL,NULL,NULL,'885','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN JUAN DE MIRAFLORES',NULL,NULL,NULL,NULL,'2015-08-03 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(119,'JHONATAN','TIRADO','TIRADO','DI','25342525','M','S','O',0,'1984-07-21 00:00:00.000000','PE',NULL,NULL,NULL,'832','jamdiazdiaz@gmail.com','','',43,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN BORJA',NULL,NULL,NULL,NULL,'2013-01-14 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(120,'RAJIV KUMAR','TOORA','HURTADO','DI','25342525','M','S','O',0,'2000-09-25 00:00:00.000000','PE',NULL,NULL,NULL,'950','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','MAGDALENA DEL MAR',NULL,NULL,NULL,NULL,'2016-09-12 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(121,'JUAN CARLOS','TORRES','PARODI','DI','25342525','M','S','O',0,'1972-01-14 00:00:00.000000','PE',NULL,NULL,NULL,'72','jamdiazdiaz@gmail.com','','',30,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2000-08-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(122,'CARLOS','TREVEJO','JIMENEZ','DI','25342525','M','S','O',0,'1987-06-10 00:00:00.000000','PE',NULL,NULL,NULL,'822','jamdiazdiaz@gmail.com','','',32,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SANTIAGO DE SURCO',NULL,NULL,NULL,NULL,'2012-09-24 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(123,'NEVHA','URDANEGUI','ISMODES','DI','25342525','F','S','O',0,'1967-07-19 00:00:00.000000','PE',NULL,NULL,NULL,'430','jamdiazdiaz@gmail.com','','',35,'IN','PG','TC',1,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CHORRILLOS',NULL,NULL,NULL,NULL,'2008-12-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(124,'DOLLY PATRICIA','VALDIVIA','PONCE','DI','25342525','F','S','O',0,'1984-04-17 00:00:00.000000','PE',NULL,NULL,NULL,'914','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2016-04-04 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(125,'RONALD','VALDIVIA','MONTERO','DI','25342525','M','S','O',0,'1972-12-06 00:00:00.000000','PE',NULL,NULL,NULL,'50','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',1,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA MOLINA',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(126,'JOSE','VALENCIA','CASTRO','DI','25342525','M','S','O',0,'1984-05-30 00:00:00.000000','PE',NULL,NULL,NULL,'366','jamdiazdiaz@gmail.com','','',41,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','LA MOLINA',NULL,NULL,NULL,NULL,'2007-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(127,'ALAN','VALLADOLID','VALLEJOS','DI','25342525','M','S','O',0,'1987-07-21 00:00:00.000000','PE',NULL,NULL,NULL,'849','jamdiazdiaz@gmail.com','','',34,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN BORJA',NULL,NULL,NULL,NULL,'2014-02-10 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(128,'JORGE JEFFREY','VARGAS','IPINCE','DI','25342525','M','S','O',0,'2000-07-25 00:00:00.000000','PE',NULL,NULL,NULL,'949','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'PR',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','PUEBLO LIBRE',NULL,NULL,NULL,NULL,'2016-09-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(129,'JENNER','VELA','INOÑAN','DI','25342525','F','S','O',0,'2000-06-08 00:00:00.000000','PE',NULL,NULL,NULL,'879','jamdiazdiaz@gmail.com','','',33,'IN','PG','TC',0,'EM',NULL,NULL,NULL,1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CARABAYLLO',NULL,NULL,NULL,NULL,'2000-01-01 00:00:00.000000',NULL,NULL,NULL,1,'I','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(130,'MIRTHA','VELARDE','AREVALO DE SHERRILL','DI','25342525','F','S','O',0,'1972-10-15 00:00:00.000000','PE',NULL,NULL,NULL,'163','jamdiazdiaz@gmail.com','','',23,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'2002-04-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(131,'FREDY','VELASQUEZ','CASTILLO','DI','25342525','M','S','O',0,'1978-10-03 00:00:00.000000','PE',NULL,NULL,NULL,'893','jamdiazdiaz@gmail.com','','',28,'IN','PG','TC',0,'EM',NULL,NULL,'PF',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','CL','CALLAO',NULL,NULL,NULL,NULL,'2015-10-12 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(132,'PERCY MICHAEL','VILLEGAS','CUELLAR','DI','25342525','M','S','O',0,'1984-08-03 00:00:00.000000','PE',NULL,NULL,NULL,'938','jamdiazdiaz@gmail.com','','',24,'IN','PG','TC',0,'EM',NULL,NULL,'IN',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2016-07-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(133,'CRISTIAN','VILLEGAS','CHAVEZ','DI','25342525','M','S','O',0,'1989-11-27 00:00:00.000000','PE',NULL,NULL,NULL,'923','jamdiazdiaz@gmail.com','','',42,'IN','PG','TC',0,'EM',NULL,NULL,'H1',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','SAN MIGUEL',NULL,NULL,NULL,NULL,'2016-05-09 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(134,'CHRISTIAN','ZAMUDIO','NAKAMURA','DI','25342525','M','S','O',0,'1981-12-13 00:00:00.000000','PE',NULL,NULL,NULL,'827','jamdiazdiaz@gmail.com','','',39,'IN','PG','TC',0,'EM',NULL,NULL,'RI',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','ATE',NULL,NULL,NULL,NULL,'2012-10-22 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(135,'NELLY','ZEVALLOS','REGAL','DI','25342525','F','S','O',0,'2000-11-29 00:00:00.000000','PE',NULL,NULL,NULL,'12','jamdiazdiaz@gmail.com','','',23,'IN','PG','TC',1,'EM',NULL,NULL,'RI',1,'RI','7024512','987789782',NULL,NULL,'PR','Direccion de domicilio','PE','LI','LI','CERCADO DE LIMA',NULL,NULL,NULL,NULL,'1998-05-01 00:00:00.000000',NULL,NULL,NULL,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table empleadocompensacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `empleadocompensacion`;

CREATE TABLE `empleadocompensacion` (
  `IdEmpleadoCompensacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `Tardanzas` char(18) DEFAULT NULL,
  `HorasTardanzaIngreso` decimal(10,2) DEFAULT NULL,
  `HorasTardanzaSalida` decimal(10,2) DEFAULT NULL,
  `HorasDemoraAlmuerzo` decimal(10,2) DEFAULT NULL,
  `HorasTrabajadas` decimal(10,2) DEFAULT NULL,
  `HorarioHorasTrabajo` decimal(10,2) DEFAULT NULL,
  `Vacaciones` int(11) DEFAULT NULL,
  `Licencias` int(11) DEFAULT NULL,
  `Inasistencias` int(11) DEFAULT NULL,
  `HorasPendientesTotal` decimal(10,2) DEFAULT NULL,
  `HorasPendientesMesActual` decimal(10,2) DEFAULT NULL,
  `HorasPendientesHastaMesAnterior` decimal(10,2) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `Mes` varchar(20) NOT NULL,
  PRIMARY KEY (`IdEmpleadoCompensacion`),
  KEY `fkEmpleado_EmpleadoCompensacion` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_EmpleadoCompensacion` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table empresa
# ------------------------------------------------------------

DROP TABLE IF EXISTS `empresa`;

CREATE TABLE `empresa` (
  `IdEmpresa` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(20) NOT NULL,
  `RazonSocial` varchar(100) NOT NULL,
  `RUC` varchar(50) NOT NULL,
  `Logo` varchar(150) DEFAULT NULL,
  `IdJefe` int(11) DEFAULT NULL,
  `JefeNoDisponible` tinyint(1) DEFAULT NULL,
  `IdJefeReemplazo` int(11) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdEmpresa`),
  KEY `fkEmpleado_Empresa` (`IdJefe`),
  KEY `fkJefeReem_Empresa` (`IdJefeReemplazo`),
  CONSTRAINT `fkEmpleado_Empresa` FOREIGN KEY (`IdJefe`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkJefeReem_Empresa` FOREIGN KEY (`IdJefeReemplazo`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;

INSERT INTO `empresa` (`IdEmpresa`, `Codigo`, `RazonSocial`, `RUC`, `Logo`, `IdJefe`, `JefeNoDisponible`, `IdJefeReemplazo`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,'JOE','JOEDAYZ','111111111111111',NULL,NULL,NULL,NULL,'A','JOE','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table equipoentregado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `equipoentregado`;

CREATE TABLE `equipoentregado` (
  `IdEquipoEntregado` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `TipoEquipo` varchar(2) NOT NULL,
  `Descripcion` varchar(100) NOT NULL,
  `FechaEntrega` datetime(6) NOT NULL,
  `FechaDevolucion` datetime(6) DEFAULT NULL,
  `Estado` varchar(2) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdEquipoEntregado`),
  KEY `fkEmpleado_EquipoEntregado` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_EquipoEntregado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table experiencialaboral
# ------------------------------------------------------------

DROP TABLE IF EXISTS `experiencialaboral`;

CREATE TABLE `experiencialaboral` (
  `IdExperienciaLaboral` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `RazonSocial` varchar(100) NOT NULL,
  `Departamento` varchar(50) NOT NULL,
  `Cargo` varchar(50) NOT NULL,
  `Descripcion` varchar(450) DEFAULT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdExperienciaLaboral`),
  KEY `fkEmpleado_ExperienciaLaboral` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_ExperienciaLaboral` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table historiallaboral
# ------------------------------------------------------------

DROP TABLE IF EXISTS `historiallaboral`;

CREATE TABLE `historiallaboral` (
  `IdHistorialLaboral` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `IdUnidadDeNegocio` int(11) NOT NULL,
  `IdDepartamentoArea` int(11) DEFAULT NULL,
  `IdProyecto` int(11) DEFAULT NULL,
  `IdCargo` int(11) NOT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `Descripcion` varchar(450) DEFAULT NULL,
  `Salario` decimal(10,2) NOT NULL,
  `IdMoneda` int(11) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdHistorialLaboral`),
  KEY `fkCargo_HistorialLaboral` (`IdCargo`),
  KEY `fkEmpleado_HistorialLaboral` (`IdEmpleado`),
  KEY `fkProyecto_HistorialLaboral` (`IdProyecto`),
  KEY `fkMoneda_HistorialLaboral` (`IdMoneda`),
  KEY `fkUnidadDeNegocio_HistorialLaboral` (`IdUnidadDeNegocio`),
  KEY `fkDepartamentoArea_HistorialLaboral` (`IdDepartamentoArea`),
  CONSTRAINT `fkCargo_HistorialLaboral` FOREIGN KEY (`IdCargo`) REFERENCES `cargo` (`IdCargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkDepartamentoArea_HistorialLaboral` FOREIGN KEY (`IdDepartamentoArea`) REFERENCES `departamentoarea` (`IdDepartamentoArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpleado_HistorialLaboral` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkMoneda_HistorialLaboral` FOREIGN KEY (`IdMoneda`) REFERENCES `moneda` (`IdMoneda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkProyecto_HistorialLaboral` FOREIGN KEY (`IdProyecto`) REFERENCES `proyecto` (`IdProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUnidadDeNegocio_HistorialLaboral` FOREIGN KEY (`IdUnidadDeNegocio`) REFERENCES `unidaddenegocio` (`IdUnidadDeNegocio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table horario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `horario`;

CREATE TABLE `horario` (
  `IdHorario` int(11) NOT NULL AUTO_INCREMENT,
  `TipoHorario` varchar(2) NOT NULL,
  `IdEmpresa` int(11) NOT NULL,
  `IdProyecto` int(11) DEFAULT NULL,
  `Nombre` varchar(100) NOT NULL,
  `HorasSemanal` tinyint(3) unsigned DEFAULT NULL,
  `PorDefecto` tinyint(1) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdHorario`),
  KEY `fkEmpresa_Horario` (`IdEmpresa`),
  KEY `fkProyecto_Horario` (`IdProyecto`),
  CONSTRAINT `fkEmpresa_Horario` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkProyecto_Horario` FOREIGN KEY (`IdProyecto`) REFERENCES `proyecto` (`IdProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table horariodia
# ------------------------------------------------------------

DROP TABLE IF EXISTS `horariodia`;

CREATE TABLE `horariodia` (
  `IdHorarioDia` int(11) NOT NULL AUTO_INCREMENT,
  `IdHorario` int(11) NOT NULL,
  `DiaSemana` varchar(2) NOT NULL,
  `Laboral` tinyint(1) NOT NULL,
  `Entrada` varchar(5) DEFAULT NULL,
  `Salida` varchar(5) DEFAULT NULL,
  `TiempoAlmuerzo` tinyint(3) unsigned DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdHorarioDia`),
  KEY `fkHorario_HorarioDia` (`IdHorario`),
  CONSTRAINT `fkHorario_HorarioDia` FOREIGN KEY (`IdHorario`) REFERENCES `horario` (`IdHorario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table horarioempleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `horarioempleado`;

CREATE TABLE `horarioempleado` (
  `IdHorarioEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `InicioVigencia` datetime(6) NOT NULL,
  `FinVigencia` datetime(6) DEFAULT NULL,
  `TipoHorario` varchar(2) NOT NULL,
  `IdHorario` int(11) DEFAULT NULL,
  `HorasSemanal` tinyint(3) unsigned DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdHorarioEmpleado`),
  KEY `fkEmpleado_HorarioEmpleado` (`IdEmpleado`),
  KEY `fkHorario_HorarioEmpleado` (`IdHorario`),
  CONSTRAINT `fkEmpleado_HorarioEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkHorario_HorarioEmpleado` FOREIGN KEY (`IdHorario`) REFERENCES `horario` (`IdHorario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table horarioempleadodia
# ------------------------------------------------------------

DROP TABLE IF EXISTS `horarioempleadodia`;

CREATE TABLE `horarioempleadodia` (
  `IdHorarioEmpleadoDia` int(11) NOT NULL AUTO_INCREMENT,
  `IdHorarioEmpleado` int(11) NOT NULL,
  `DiaSemana` varchar(2) NOT NULL,
  `Laboral` tinyint(1) NOT NULL,
  `Entrada` varchar(5) DEFAULT NULL,
  `Salida` varchar(5) DEFAULT NULL,
  `TiempoAlmuerzo` tinyint(3) unsigned DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `NombreDiaSemana` varchar(20) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdHorarioEmpleadoDia`),
  KEY `fkHorarioEmpleado_HorarioEmpleadoDia` (`IdHorarioEmpleado`),
  CONSTRAINT `fkHorarioEmpleado_HorarioEmpleadoDia` FOREIGN KEY (`IdHorarioEmpleado`) REFERENCES `horarioempleado` (`IdHorarioEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table horasextra
# ------------------------------------------------------------

DROP TABLE IF EXISTS `horasextra`;

CREATE TABLE `horasextra` (
  `IdHorasExtra` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `Fecha` datetime(6) NOT NULL,
  `HoraSalidaHorario` varchar(5) DEFAULT NULL,
  `IdAtendidoPor` int(11) NOT NULL,
  `HoraSalidaSolicitado` varchar(5) NOT NULL,
  `Horas` decimal(5,2) DEFAULT NULL,
  `HorasCompensadas` decimal(5,2) DEFAULT NULL,
  `Motivo` varchar(200) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `ComentarioResolucion` varchar(250) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `Tipo` varchar(2) NOT NULL,
  `HorasNoCompensables` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`IdHorasExtra`),
  KEY `fkEmpleado_HorasExtra` (`IdEmpleado`),
  KEY `fkEmpleado_HorasExtra_AtendidoPor` (`IdAtendidoPor`),
  CONSTRAINT `fkEmpleado_HorasExtra` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpleado_HorasExtra_AtendidoPor` FOREIGN KEY (`IdAtendidoPor`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table licencia
# ------------------------------------------------------------

DROP TABLE IF EXISTS `licencia`;

CREATE TABLE `licencia` (
  `IdLicencia` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `IdTipoLicencia` int(11) DEFAULT NULL,
  `Comentario` varchar(250) DEFAULT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) NOT NULL,
  `Dias` tinyint(3) unsigned DEFAULT NULL,
  `DiaEntero` tinyint(1) NOT NULL,
  `HoraInicio` varchar(5) DEFAULT NULL,
  `HoraFin` varchar(5) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `IdAtendidoPor` int(11) NOT NULL,
  `ComentarioResolucion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`IdLicencia`),
  KEY `fkEmpleado_Licencia` (`IdEmpleado`),
  KEY `fkTipoLicencia_Licencia` (`IdTipoLicencia`),
  KEY `fk_LicenciaAtendidoPor_Empleado` (`IdAtendidoPor`),
  CONSTRAINT `fkEmpleado_Licencia` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkTipoLicencia_Licencia` FOREIGN KEY (`IdTipoLicencia`) REFERENCES `tipolicencia` (`IdTipoLicencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LicenciaAtendidoPor_Empleado` FOREIGN KEY (`IdAtendidoPor`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table marcacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `marcacion`;

CREATE TABLE `marcacion` (
  `IdMarcacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `Fecha` datetime(6) NOT NULL,
  `HoraIngreso` varchar(5) DEFAULT NULL,
  `HoraInicioAlmuerzo` varchar(5) DEFAULT NULL,
  `HoraFinAlmuerzo` varchar(5) DEFAULT NULL,
  `HoraSalida` varchar(5) DEFAULT NULL,
  `HoraIngresoHorario` varchar(5) DEFAULT NULL,
  `HoraSalidaHorario` varchar(5) DEFAULT NULL,
  `Inasistencia` tinyint(1) NOT NULL,
  `Vacaciones` tinyint(1) DEFAULT NULL,
  `Licencia` tinyint(1) DEFAULT NULL,
  `Tardanza` tinyint(1) NOT NULL,
  `EsPersonaDeConfianza` tinyint(1) NOT NULL,
  `HorasTrabajoHorario` decimal(5,2) DEFAULT NULL,
  `DemoraEntrada` decimal(5,2) DEFAULT NULL,
  `DemoraAlmuerzo` decimal(5,2) DEFAULT NULL,
  `DemoraSalida` decimal(5,2) DEFAULT NULL,
  `HorasTrabajoReal` decimal(5,2) DEFAULT NULL,
  `HorasPermiso` decimal(5,2) DEFAULT NULL,
  `HorasExtra` decimal(5,2) DEFAULT NULL,
  `HorasTrabajoPendiente` decimal(5,2) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdMarcacion`),
  KEY `fkEmpleado_Marcacion` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_Marcacion` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table marcador
# ------------------------------------------------------------

DROP TABLE IF EXISTS `marcador`;

CREATE TABLE `marcador` (
  `IdMarcador` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(20) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Descripcion` varchar(150) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `IdEmpresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdMarcador`),
  KEY `fk_Empresa_Marcador` (`IdEmpresa`),
  CONSTRAINT `fk_Empresa_Marcador` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table modulo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `modulo`;

CREATE TABLE `modulo` (
  `IdModulo` int(11) NOT NULL AUTO_INCREMENT,
  `IdParent` int(11) DEFAULT NULL,
  `Codigo` varchar(20) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `EtiquetaMenu` varchar(50) NOT NULL,
  `Url` varchar(150) DEFAULT NULL,
  `HelpUrl` varchar(150) DEFAULT NULL,
  `Orden` tinyint(3) unsigned DEFAULT NULL,
  `Visible` tinyint(1) NOT NULL,
  `TieneSubModulo` tinyint(1) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `ImageClass` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`IdModulo`),
  KEY `fkModulo_Modulo` (`IdParent`),
  CONSTRAINT `fkModulo_Modulo` FOREIGN KEY (`IdParent`) REFERENCES `modulo` (`IdModulo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `modulo` WRITE;
/*!40000 ALTER TABLE `modulo` DISABLE KEYS */;

INSERT INTO `modulo` (`IdModulo`, `IdParent`, `Codigo`, `Nombre`, `EtiquetaMenu`, `Url`, `HelpUrl`, `Orden`, `Visible`, `TieneSubModulo`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`, `ImageClass`)
VALUES
	(39,NULL,'DA000','Dashboard','Dashboard','#','#',1,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-dashboard'),
	(40,NULL,'EM000','Personal','Personal','#','#',2,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-person'),
	(41,NULL,'AU000','Autogestión','Autogestión','#','#',3,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-auto-manage-person'),
	(42,NULL,'GT000','Gestión de Tiempo','Gestión de Tiempo','#','#',4,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-calendar'),
	(43,NULL,'OR000','Organización','Organización','#','#',5,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-organization'),
	(44,NULL,'MA000','Mantenimiento','Mantenimiento','#','#',6,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-settings'),
	(45,NULL,'SE000','Seguridad','Seguridad','#','#',7,1,1,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,'fa fa-lg fa-fw ic-security'),
	(46,39,'DA001','Mi Dashboard','Mi Dashboard','/dashboard/principal','/dashboard/principal',1,1,NULL,'TSS','2017-02-10 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 17:49:58.122000',3,NULL),
	(47,39,'DA002','Mi Dashboard','Mi Dashboard','/dashboard/principalJefe','/dashboard/principalJefe',2,1,NULL,'TSS','2017-02-10 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 17:37:15.063000',2,NULL),
	(48,39,'DA003','Mi Dashboard','Mi Dashboard','/dashboard/principalAnalistaRRHH','/dashboard/principalAnalistaRRHH',3,1,NULL,'TSS','2017-02-10 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 17:39:07.315000',2,NULL),
	(49,40,'EM001','Empleados','Empleados','/personal/busquedaEmpleado','/personal/busquedaEmpleado',1,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(50,41,'AU001','Datos Personales','Datos Personales','/autogestion/actualizarDatosPersonales','/autogestion/actualizarDatosPersonales',1,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(51,41,'AU002','Solicitar Permiso','Solicitar Permiso','/autogestion/solicitarPermiso','/autogestion/solicitarPermiso',2,1,NULL,'TSS','2017-02-10 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 17:41:46.376000',2,NULL),
	(52,41,'AU003','Programar Vacaciones','Programar Vacaciones','/autogestion/agendarVacaciones','/autogestion/agendarVacaciones',3,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(53,41,'AU004','Solicitar Horas Extras','Solicitar Horas Extras','/autogestion/solicitarHorasExtra','/autogestion/solicitarHorasExtra',4,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(54,41,'AU005','Solicitar Licencia','Solicitar Licencia','/autogestion/solicitarLicencia','/autogestion/solicitarLicencia',5,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(55,42,'GT001','Permisos','Permisos','/gestionTiempo/busquedaPermisos','/gestionTiempo/busquedaPermisos',1,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(56,42,'GT002','Vacaciones','Vacaciones','/gestionTiempo/busquedaVacaciones','/gestionTiempo/busquedaVacaciones',2,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(57,42,'GT003','Licencias','Licencias','/gestionTiempo/busquedaLicencias','/gestionTiempo/busquedaLicencias',4,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(58,42,'GT004','Horarios','Horarios','/gestionTiempo/busquedaHorarios','/gestionTiempo/busquedaHorarios',5,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(59,42,'GT005','Marcaciones','Marcaciones','/gestionTiempo/busquedaMarcaciones','/gestionTiempo/busquedaMarcaciones',6,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(60,42,'GT006','Horas Extras','Horas Extras','/gestionTiempo/busquedaHorasExtras','/gestionTiempo/busquedaHorasExtras',3,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(61,42,'GT007','Compensaciones','Compensaciones','/gestionTiempo/busquedaCompensacion','/gestionTiempo/busquedaCompensacion',7,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(62,43,'OR001','Empresa','Empresa','/organizacion/busquedaEmpresa','/organizacion/busquedaEmpresa',1,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(63,43,'OR002','Estructura','Estructura','#','#',2,0,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(64,43,'OR003','Proyectos','Proyectos','/organizacion/busquedaProyecto','/organizacion/busquedaProyecto',3,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(65,43,'OR004','Calendario','Calendario','/organizacion/busquedaCalendario','/organizacion/busquedaCalendario',4,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(66,43,'OR005','Cargos','Cargos','/organizacion/busquedaCargo','/organizacion/busquedaCargo',5,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(67,43,'OR006','Centro de Costos','Centro de Costos','/organizacion/busquedaCentroCosto','/organizacion/busquedaCentroCosto',6,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(68,44,'MA001','Códigos','Códigos','/mantenimientos/busquedaCodigos','/mantenimientos/busquedaCodigos',1,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(69,44,'MA002','Configuraciones','Configuraciones','/mantenimientos/busquedaConfiguracionSistema','/mantenimientos/busquedaConfiguracionSistema',2,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(70,44,'MA003','Alertas','Alertas','/mantenimientos/busquedaAlertas','/mantenimientos/busquedaAlertas',3,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(71,44,'MA004','Reporte Marcaciones','Reporte Marcaciones','/mantenimientos/busquedaReporteMarcaciones','/mantenimientos/busquedaReporteMarcaciones',4,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(72,44,'MA005','Tipos de Licencia','Tipos de Licencia','/mantenimientos/busquedaTiposLicencias','/mantenimientos/busquedaTiposLicencias',5,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(73,45,'SE001','Usuarios','Usuarios','/seguridad/busquedaUsuarios','/seguridad/busquedaUsuarios',1,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(74,45,'SE002','Roles','Roles','/seguridad/busquedaRoles','/seguridad/busquedaRoles',2,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(75,45,'SE003','Modulos','Modulos','/seguridad/busquedaModulos','/seguridad/busquedaModulos',3,1,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(76,45,'SE004','Editar Mi Cuenta','Editar Mi Cuenta','#','#',4,0,0,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,NULL),
	(77,42,'GT008','Periodos','Periodos','/gestionTiempo/busquedaPeriodo','/gestionTiempo/busquedaPeriodo',8,1,0,'TSS','2017-02-20 16:09:11.340000',NULL,NULL,1,NULL),
	(78,44,'MA006','Marcadores','Marcadores','/mantenimientos/busquedaMarcador','/mantenimiento/busquedaMarcador',6,1,0,'TSS','2017-03-28 13:21:48.163000',NULL,NULL,1,NULL);

/*!40000 ALTER TABLE `modulo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table moneda
# ------------------------------------------------------------

DROP TABLE IF EXISTS `moneda`;

CREATE TABLE `moneda` (
  `IdMoneda` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Sigla` varchar(50) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdMoneda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;

INSERT INTO `moneda` (`IdMoneda`, `Codigo`, `Nombre`, `Sigla`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,'PE','Soles','S/.','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,'US','Dolares','US$','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pais
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pais`;

CREATE TABLE `pais` (
  `IdPais` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdPais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;

INSERT INTO `pais` (`IdPais`, `Codigo`, `Nombre`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,'PE','Perú','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,'US','Estados Unidos','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table periodoempleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `periodoempleado`;

CREATE TABLE `periodoempleado` (
  `IdPeriodoEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `Periodo` varchar(50) NOT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) NOT NULL,
  `MaxDiasVacaciones` tinyint(3) unsigned DEFAULT NULL,
  `DiasVacacionesDisponibles` tinyint(3) unsigned DEFAULT NULL,
  `MaxPermisos` tinyint(3) unsigned DEFAULT NULL,
  `PermisosDisponibles` tinyint(3) unsigned DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `DiasVacacionesAcumulado` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPeriodoEmpleado`),
  KEY `fkEmpleado_PeriodoEmpleado` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_PeriodoEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table periodoempleadotipolicencia
# ------------------------------------------------------------

DROP TABLE IF EXISTS `periodoempleadotipolicencia`;

CREATE TABLE `periodoempleadotipolicencia` (
  `IdPeriodoEmpleadoTipoLicencia` int(11) NOT NULL AUTO_INCREMENT,
  `IdPeriodoEmpleado` int(11) NOT NULL,
  `IdTipoLicencia` int(11) NOT NULL,
  `DiasLicencia` tinyint(3) unsigned DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdPeriodoEmpleadoTipoLicencia`),
  KEY `fkPeriodoEmpleado_PeriodoEmpleadoTipoLicencia` (`IdPeriodoEmpleado`),
  KEY `fkTipoLicencia_PeriodoEmpleadoTipoLicencia` (`IdTipoLicencia`),
  CONSTRAINT `fkPeriodoEmpleado_PeriodoEmpleadoTipoLicencia` FOREIGN KEY (`IdPeriodoEmpleado`) REFERENCES `periodoempleado` (`IdPeriodoEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkTipoLicencia_PeriodoEmpleadoTipoLicencia` FOREIGN KEY (`IdTipoLicencia`) REFERENCES `tipolicencia` (`IdTipoLicencia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table permisoempleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `permisoempleado`;

CREATE TABLE `permisoempleado` (
  `IdPermisoEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdPeriodoEmpleado` int(11) NOT NULL,
  `IdAtendidoPor` int(11) DEFAULT NULL,
  `Motivo` varchar(2) NOT NULL,
  `Razon` varchar(250) DEFAULT NULL,
  `Fecha` datetime(6) NOT NULL,
  `HoraInicio` varchar(5) NOT NULL,
  `HoraFin` varchar(5) NOT NULL,
  `Horas` decimal(5,2) DEFAULT NULL,
  `FechaRecuperacion` datetime(6) DEFAULT NULL,
  `HoraInicioRecuperacion` varchar(5) DEFAULT NULL,
  `HoraFinRecuperacion` varchar(5) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `ComentarioResolucion` varchar(250) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdPermisoEmpleado`),
  KEY `fkEmpleado_PermisoEmpleado` (`IdAtendidoPor`),
  KEY `fkPeriodoEmpleado_PermisoEmpleado` (`IdPeriodoEmpleado`),
  CONSTRAINT `fkEmpleado_PermisoEmpleado` FOREIGN KEY (`IdAtendidoPor`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkPeriodoEmpleado_PermisoEmpleado` FOREIGN KEY (`IdPeriodoEmpleado`) REFERENCES `periodoempleado` (`IdPeriodoEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `permisoempleado` WRITE;
/*!40000 ALTER TABLE `permisoempleado` DISABLE KEYS */;

INSERT INTO `permisoempleado` (`IdPermisoEmpleado`, `IdPeriodoEmpleado`, `IdAtendidoPor`, `Motivo`, `Razon`, `Fecha`, `HoraInicio`, `HoraFin`, `Horas`, `FechaRecuperacion`, `HoraInicioRecuperacion`, `HoraFinRecuperacion`, `Estado`, `ComentarioResolucion`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(27,5821,35,'P','Prueba 14/03/2017','2017-03-14 00:00:00.000000','08:00','10:00',2.00,'2017-03-15 00:00:00.000000','17:30','19:30','R','No se puede','cron-usr','2017-03-13 12:11:57.562000','cron-usr','2017-03-13 12:18:44.081000',1),
	(28,5821,35,'P','prueba','2017-03-16 00:00:00.000000','09:00','13:00',4.00,'2017-03-17 00:00:00.000000','19:00','23:00','A',NULL,'cron-usr','2017-03-13 13:58:53.890000','cron-usr','2017-03-16 10:58:17.484000',1),
	(29,5821,35,'P','Permiso','2017-03-28 00:00:00.000000','09:00','10:00',1.00,'2017-03-29 00:00:00.000000','18:00','19:00','E',NULL,'cron-usr','2017-03-13 16:17:38.755000',NULL,NULL,0),
	(31,5893,35,'C','cita medica','2017-03-17 00:00:00.000000','09:00','12:00',3.00,NULL,NULL,NULL,'R','pase produccion','cron-usr','2017-03-14 10:34:41.739000','cron-usr','2017-03-14 11:30:55.359000',1),
	(33,5821,35,'P','Prueba','2017-03-30 00:00:00.000000','08:00','10:00',2.00,'2017-03-31 00:00:00.000000','18:00','20:00','E',NULL,'cron-usr','2017-03-14 11:39:24.598000',NULL,NULL,0),
	(36,5893,35,'C','comida','2017-04-07 00:00:00.000000','09:00','15:00',6.00,NULL,NULL,NULL,'A',NULL,'JUAN.REYES','2017-03-20 12:17:53.707000','ESTANISLAO.CONTRERAS','2017-03-20 12:18:49.446000',1),
	(37,5837,35,'P','ADASDASD','2017-03-27 00:00:00.000000','09:00','17:30',8.50,'2017-03-30 00:00:00.000000','08:30','17:30','E',NULL,'JAVIER.CUICAPUZA','2017-03-24 12:00:19.787000',NULL,NULL,0),
	(38,5837,101,'N','asdads','2017-05-10 00:00:00.000000','09:00','17:30',8.50,NULL,NULL,NULL,'E',NULL,'JAVIER.CUICAPUZA','2017-03-28 15:16:35.997000',NULL,NULL,0),
	(39,5837,101,'N','asdasd','2017-03-29 00:00:00.000000','09:30','11:11',1.68,NULL,NULL,NULL,'E',NULL,'JAVIER.CUICAPUZA','2017-03-28 15:19:26.224000',NULL,NULL,0);

/*!40000 ALTER TABLE `permisoempleado` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table provincia
# ------------------------------------------------------------

DROP TABLE IF EXISTS `provincia`;

CREATE TABLE `provincia` (
  `IdProvincia` int(11) NOT NULL AUTO_INCREMENT,
  `IdDepartamento` int(11) NOT NULL,
  `Codigo` varchar(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdProvincia`),
  KEY `fkDepartamento_Provincia` (`IdDepartamento`),
  CONSTRAINT `fkDepartamento_Provincia` FOREIGN KEY (`IdDepartamento`) REFERENCES `departamento` (`IdDepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table proyecto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proyecto`;

CREATE TABLE `proyecto` (
  `IdProyecto` int(11) NOT NULL AUTO_INCREMENT,
  `IdDepartamentoArea` int(11) NOT NULL,
  `Codigo` varchar(20) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(250) DEFAULT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `Cliente` varchar(250) NOT NULL,
  `IdJefe` int(11) DEFAULT NULL,
  `JefeNoDisponible` tinyint(1) DEFAULT NULL,
  `IdJefeReemplazo` int(11) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdProyecto`),
  KEY `fkDepartamentoArea_Proyecto` (`IdDepartamentoArea`),
  KEY `fkEmpleado_Proyecto` (`IdJefe`),
  KEY `fkJefeReem_Proyecto` (`IdJefeReemplazo`),
  CONSTRAINT `fkDepartamentoArea_Proyecto` FOREIGN KEY (`IdDepartamentoArea`) REFERENCES `departamentoarea` (`IdDepartamentoArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpleado_Proyecto` FOREIGN KEY (`IdJefe`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkJefeReem_Proyecto` FOREIGN KEY (`IdJefeReemplazo`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;

INSERT INTO `proyecto` (`IdProyecto`, `IdDepartamentoArea`, `Codigo`, `Nombre`, `Descripcion`, `FechaInicio`, `FechaFin`, `Cliente`, `IdJefe`, `JefeNoDisponible`, `IdJefeReemplazo`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(2,3,'CELTIC','CELTIC','CELTIC PRICING','2016-01-01 00:00:00.000000',NULL,'CELTIC',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000','cron-usr','2017-03-14 12:56:05.022000',24),
	(3,3,'DOLE','DOLE','DOLE','2016-01-01 00:00:00.000000',NULL,'DOLE',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,3,'CFR','CFR','CFR','2016-01-01 00:00:00.000000',NULL,'CFR',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,3,'XPO','XPO','XPO','2016-01-01 00:00:00.000000',NULL,'XPO',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,3,'CLARK_STEEL','CLARK STEEL','CLARK STEEL','2016-01-01 00:00:00.000000',NULL,'CLARK',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,3,'CONTOPSA','CONTOPSA','CONTOPSA','2016-01-01 00:00:00.000000',NULL,'CONTOPSA',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,3,'SITRAN','SITRAN','SITRAN','2016-01-01 00:00:00.000000',NULL,'SITRAN',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,3,'SMOOTH_COM','SMOOTH COM','SMOOTH COM','2016-01-01 00:00:00.000000',NULL,'XPO',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,3,'TRANS_MOBILE','TRANS MOBILE','TRANS MOBILE','2016-01-01 00:00:00.000000',NULL,'TSS',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,3,'ULOG','ULOG','ULOG','2016-01-01 00:00:00.000000',NULL,'ULTRAMAR',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,3,'ULTRATUG','ULTRATUG','ULTRATUG','2016-01-01 00:00:00.000000',NULL,'ULTRATUG',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,3,'UMAR','UMAR','UMAR','2016-01-01 00:00:00.000000',NULL,'ULTRAMAR',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(14,3,'XPO_INTERNATIONAL','XPO INTERNATIONAL','XPO INTERNATIONAL','2016-01-01 00:00:00.000000',NULL,'XPO',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(15,3,'XPO_MOBILE','XPO MOBILE','XPO MOBILE','2016-01-01 00:00:00.000000',NULL,'XPO',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000','NEVHA.URDANEGUI','2017-03-27 08:43:44.154000',2),
	(26,3,'SHUTTLE TRACKING','SHUTTLE TRACKING','','2017-01-01 00:00:00.000000',NULL,'',NULL,0,NULL,'A','TSS','2017-03-22 12:58:51.070000',NULL,NULL,0);

/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table registromarcacionempleado
# ------------------------------------------------------------

DROP TABLE IF EXISTS `registromarcacionempleado`;

CREATE TABLE `registromarcacionempleado` (
  `IdRegistroMarcacionEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) NOT NULL,
  `CodigoEmpleado` varchar(20) NOT NULL,
  `DNI` varchar(50) NOT NULL,
  `Fecha` datetime(6) NOT NULL,
  `Hora` varchar(5) NOT NULL,
  `Tipo` varchar(2) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Procesado` varchar(2) NOT NULL DEFAULT 'N',
  `Sensor` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`IdRegistroMarcacionEmpleado`),
  KEY `fkEmpleado_RegistroMarcacionEmpleado` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_RegistroMarcacionEmpleado` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table reportemarcacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reportemarcacion`;

CREATE TABLE `reportemarcacion` (
  `IdReporteMarcacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpresa` int(11) NOT NULL,
  `IdUnidadDeNegocio` int(11) DEFAULT NULL,
  `IdDepartamentoArea` int(11) DEFAULT NULL,
  `IdProyecto` int(11) DEFAULT NULL,
  `IdJefe` int(11) DEFAULT NULL,
  `ReporteDiario` tinyint(1) NOT NULL,
  `ReporteAcumulado` tinyint(1) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdReporteMarcacion`),
  KEY `fkUnidadDeNegocio_ReporteMarcacion` (`IdUnidadDeNegocio`),
  KEY `fkDepartamentoArea_ReporteMarcacion` (`IdDepartamentoArea`),
  KEY `fkProyecto_ReporteMarcacion` (`IdProyecto`),
  KEY `fkEmpresa_ReporteMarcacion` (`IdEmpresa`),
  KEY `fkEmpleado_ReporteMarcacion` (`IdJefe`),
  CONSTRAINT `fkDepartamentoArea_ReporteMarcacion` FOREIGN KEY (`IdDepartamentoArea`) REFERENCES `departamentoarea` (`IdDepartamentoArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpleado_ReporteMarcacion` FOREIGN KEY (`IdJefe`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpresa_ReporteMarcacion` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkProyecto_ReporteMarcacion` FOREIGN KEY (`IdProyecto`) REFERENCES `proyecto` (`IdProyecto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUnidadDeNegocio_ReporteMarcacion` FOREIGN KEY (`IdUnidadDeNegocio`) REFERENCES `unidaddenegocio` (`IdUnidadDeNegocio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table reportemarcacionsubscriptor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reportemarcacionsubscriptor`;

CREATE TABLE `reportemarcacionsubscriptor` (
  `IdReporteMarcacionSubscriptor` int(11) NOT NULL AUTO_INCREMENT,
  `IdReporteMarcacion` int(11) NOT NULL,
  `IdEmpleado` int(11) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdReporteMarcacionSubscriptor`),
  KEY `fkReporteMarcacion_ReporteMarcacionSubscriptor` (`IdReporteMarcacion`),
  KEY `fkEmpleado_ReporteMarcacionSubscriptor` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_ReporteMarcacionSubscriptor` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkReporteMarcacion_ReporteMarcacionSubscriptor` FOREIGN KEY (`IdReporteMarcacion`) REFERENCES `reportemarcacion` (`IdReporteMarcacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table rol
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `IdRol` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(150) DEFAULT NULL,
  `RolSistema` tinyint(1) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;

INSERT INTO `rol` (`IdRol`, `Nombre`, `Descripcion`, `RolSistema`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,'ADMIN','Administrador de la Empresa',1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,'RHANA','Analista de RRHH',1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,'EMPLE','Empleado',1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,'GEREN','Jefe',1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,'SALAR','Rol para definir las bandas salariales',0,'A','TSS','2017-03-20 13:05:50.933000',NULL,NULL,0);

/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table solicitudcambiomarcacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `solicitudcambiomarcacion`;

CREATE TABLE `solicitudcambiomarcacion` (
  `IdSolicitudCambioMarcacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdMarcacion` int(11) NOT NULL,
  `IdAtendidoPor` int(11) DEFAULT NULL,
  `CambiarIngreso` tinyint(1) NOT NULL,
  `CambiarInicioAlmuerzo` tinyint(1) NOT NULL,
  `CambiarFinAlmuerzo` tinyint(1) NOT NULL,
  `CambiarSalida` tinyint(1) NOT NULL,
  `HoraIngreso` varchar(5) DEFAULT NULL,
  `HoraInicioAlmuerzo` varchar(5) DEFAULT NULL,
  `HoraFinAlmuerzo` varchar(5) DEFAULT NULL,
  `HoraSalida` varchar(5) DEFAULT NULL,
  `RazonCambioHoraIngreso` varchar(150) DEFAULT NULL,
  `RazonCambioHoraInicioAlmuerzo` varchar(150) DEFAULT NULL,
  `RazonCambioHoraFinAlmuerzo` varchar(150) DEFAULT NULL,
  `RazonHoraSalida` varchar(150) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdSolicitudCambioMarcacion`),
  KEY `fkMarcacion_SolicitudCambioMarcacion` (`IdMarcacion`),
  KEY `fkEmpleado_SolicitudCambioMarcacion` (`IdAtendidoPor`),
  CONSTRAINT `fkEmpleado_SolicitudCambioMarcacion` FOREIGN KEY (`IdAtendidoPor`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkMarcacion_SolicitudCambioMarcacion` FOREIGN KEY (`IdMarcacion`) REFERENCES `marcacion` (`IdMarcacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sysdiagrams
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysdiagrams`;

CREATE TABLE `sysdiagrams` (
  `name` varchar(160) NOT NULL,
  `principal_id` int(11) NOT NULL,
  `diagram_id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `definition` longblob,
  PRIMARY KEY (`diagram_id`),
  UNIQUE KEY `UK_principal_name` (`principal_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table tablageneral
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tablageneral`;

CREATE TABLE `tablageneral` (
  `IdTablaGeneral` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpresa` int(11) DEFAULT NULL,
  `Codigo` varchar(2) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Grupo` varchar(100) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdTablaGeneral`),
  KEY `fkEmpresa_TablaGeneral` (`IdEmpresa`),
  CONSTRAINT `fkEmpresa_TablaGeneral` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tablageneral` WRITE;
/*!40000 ALTER TABLE `tablageneral` DISABLE KEYS */;

INSERT INTO `tablageneral` (`IdTablaGeneral`, `IdEmpresa`, `Codigo`, `Nombre`, `Grupo`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,NULL,'DI','DNI','Empleado.TipoDocumento','A','TSS','2017-02-07 00:00:00.000000','GIANCARLO.BORJAS','2017-03-24 17:50:09.279000',5),
	(2,1,'PA','Pasaporte','Empleado.TipoDocumento','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,1,'CE','Carné de Extranjería','Empleado.TipoDocumento','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,1,'M','Masculino','Empleado.Generico','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,1,'F','Femenino','Empleado.Generico','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,1,'S','Soltero','Empleado.EstadoCivil','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,1,'C','Casado','Empleado.EstadoCivil','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,1,'V','Viudo','Empleado.EstadoCivil','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,1,'D','Divorciado','Empleado.EstadoCivil','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,1,'A','Grupo A','Empleado.GrupoSanguineo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,1,'B','Grupo B','Empleado.GrupoSanguineo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,1,'AB','Grupo AB','Empleado.GrupoSanguineo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,1,'O','Grupo O','Empleado.GrupoSanguineo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(14,1,'IN','A plazo indeterminado D LEG 728','Empleado.ContratoTrabajo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(15,1,'DE','Decreto Legislativo 728, Ley de Productividad y Competitividad Laboral','Empleado.ContratoTrabajo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(16,1,'EM','Empleado','Empleado.TipoTrabajo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(17,1,'PG','Privado General - Decreto Legislativo N.728','Empleado.RegimenHorario','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(18,1,'TC','Tiempo Completo','Empleado.RegimenLaboral','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(19,1,'TP','Tiempo Parcial','Empleado.RegimenLaboral','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(20,1,'CY','Conyuge','Empleado.RelacionContacto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(21,1,'CV','Conviviente','Empleado.RelacionContacto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(22,1,'HI','Hijo(a)','Empleado.RelacionContacto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(23,1,'PA','Padre','Empleado.RelacionContacto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(24,1,'MA','Madre','Empleado.RelacionContacto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(25,1,'OT','Otro','Empleado.RelacionContacto','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(26,1,'CY','Conyuge','Empleado.RelacionDependiente','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(27,1,'CV','Conviviente','Empleado.RelacionDependiente','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(28,1,'HI','Hijo(a)','Empleado.RelacionDependiente','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(29,1,'PA','Padre','Empleado.RelacionDependiente','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(30,1,'MA','Madre','Empleado.RelacionDependiente','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(31,1,'OT','Otro','Empleado.RelacionDependiente','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(32,1,'PR','Propia','Empleado.TipoDomicilio','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(33,1,'AL','Alquilado','Empleado.TipoDomicilio','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(34,1,'TE','Terceros','Empleado.TipoDomicilio','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(35,1,'PR','Pregrado','Empleado.NivelEducacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(36,1,'PO','Postgrado','Empleado.NivelEducacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(37,1,'DI','Diplomado','Empleado.NivelEducacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(38,1,'MA','Maestría','Empleado.NivelEducacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(39,1,'DO','Doctorado','Empleado.NivelEducacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(40,1,'OT','Otro','Empleado.NivelEducacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(41,1,'CE','Celular','EquiposEntregados.TipoEquipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(42,1,'LA','Laptop','EquiposEntregados.TipoEquipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(43,1,'FO','Fotocheck','EquiposEntregados.TipoEquipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(44,1,'LE','Llaves de escritorio','EquiposEntregados.TipoEquipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(45,1,'LO','Llaves de oficina','EquiposEntregados.TipoEquipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(46,1,'OT','Otro','EquiposEntregados.TipoEquipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(47,1,'C','Compensacion de Horas','Permiso.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(48,1,'P','Permisos Personales','Permiso.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(49,1,'PP','Periodo de prueba','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(50,1,'FA','Fallecimiento','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(51,1,'IN','Invalidez absoluta y permanente','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(52,1,'MD','Mutuo disenso','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(53,1,'DD','Despido o destitución','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(54,1,'RE','Renuncia con Incentivos','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(55,1,'OT','Otro','BajaDeEmpleado.Motivo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(56,1,'CR','CREDITO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(57,1,'IN','INTERBANK','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(58,1,'CI','CITIBANK','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(59,1,'CO','CONTINENTAL','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(60,1,'NA','NACION','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(61,1,'CO','COMERCIO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(62,1,'FI','FINANCIERO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(63,1,'BI','B.I.F.','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(64,1,'CS','CREDISCOTIA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(65,1,'MB','MI BANCO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(66,1,'AG','AGROBANCO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(67,1,'GN','BANCO GNB PERU S.A.','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(68,1,'FA','BANCO FALABELLA PERU S.A.','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(69,1,'RI','BANCO RIPLEY S.A.','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(70,1,'SA','BANCO SANTANDER','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(71,1,'AZ','BANCO AZTECA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(72,1,'IC','ICBC PERU BANK','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(73,1,'ME','CMAC METROPOLITANA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(74,1,'PI','CMAC PIURA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(75,1,'TR','CMAC TRUJILLO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(76,1,'AR','CMAC AREQUIPA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(77,1,'SU','CMAC SULLANA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(78,1,'CU','CMAC CUZCO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(79,1,'HU','CMAQ HUANCAYO','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(80,1,'TA','CMAQ TACNA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(81,1,'TF','TFC FINANCIERA','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(82,1,'PR','PRIMA','AFP','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(83,1,'HA','HABITAT','AFP','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(84,1,'IN','INTEGRA','AFP','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(85,1,'PF','PROFUTURO','AFP','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(86,1,'RI','RIMAC','EPS','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(87,1,'PA','PACIFICO','EPS','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(88,NULL,'EN','Entregado','EquiposEntregados.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(89,NULL,'DE','Devuelto','EquiposEntregados.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(90,NULL,'A','Alta','Empleado.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(91,NULL,'I','Baja','Empleado.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(92,NULL,'A','Activo','Usuario.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(93,NULL,'I','Inactivo','Usuario.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(94,NULL,'B','Bloqueado','Usuario.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(95,NULL,'EM','Empresa','Horario.TipoHorario','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(96,NULL,'PR','Proyecto','Horario.TipoHorario','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(97,NULL,'PE','Personalizado','Horario.TipoHorario','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(98,NULL,'P','Pendiente','Permiso.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(99,NULL,'E','Enviado','Permiso.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(100,NULL,'A','Aprobado','Permiso.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(101,NULL,'R','Denegado','Permiso.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(102,NULL,'P','Pendiente','Vacaciones.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(103,NULL,'E','Enviado','Vacaciones.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(104,NULL,'A','Aprobado','Vacaciones.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(105,NULL,'R','Denegado','Vacaciones.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(106,NULL,'P','Pendiente','HorasExtra.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(107,NULL,'A','Aprobado','HorasExtra.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(108,NULL,'R','Denegado','HorasExtra.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(109,NULL,'P','Pendiente','Licencia.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(110,NULL,'E','Enviado','Licencia.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(111,NULL,'A','Aprobado','Licencia.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(112,NULL,'V','Validado','Licencia.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(113,NULL,'R','Denegado','Licencia.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(114,NULL,'P','Pendiente','SolicitudCambioMarcacion.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(115,NULL,'A','Aprobado','SolicitudCambioMarcacion.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(116,NULL,'R','Denegado','SolicitudCambioMarcacion.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(117,NULL,'P','Propuesto','Proyecto.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(118,NULL,'A','Activo','Proyecto.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(119,NULL,'C','Cerrado','Proyecto.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(120,NULL,'D','Reporte Diario','ReporteMarcaciones.TipoReporte','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(121,NULL,'M','Reporte Acumulado Mensual','ReporteMarcaciones.TipoReporte','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(122,NULL,'TA','Tardanzas','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(123,NULL,'IN','Inasistencias','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(124,NULL,'VC','Vencimiento del Contrato','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(125,NULL,'EP','Envío de Solicitud de Permisos','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(126,NULL,'EV','Envío de Solicitud de Vacaciones','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(127,NULL,'EH','Envío de Solicitud de Horas Extras','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(128,NULL,'RP','Resolución de Solicitud de Permisos','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(129,NULL,'RV','Resolución de Solicitud de Vacaciones','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(130,NULL,'RH','Resolución de Solicitud de Horas Extras','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(131,NULL,'LI','Máximo del Tipo de Licencia','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(132,NULL,'CP','Creación de Periodo','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(133,NULL,'EL','Envío de Licencia','Alerta.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(134,NULL,'A','Acceder','Accion.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(135,NULL,'I','Insertar','Accion.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(136,NULL,'M','Modificar','Accion.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(137,NULL,'E','Eliminar','Accion.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(138,NULL,'P','Personalizado','Accion.Tipo','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(139,NULL,'I','Individual','Alerta.TipoNotificacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(140,NULL,'G','Grupal','Alerta.TipoNotificacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(141,NULL,'P','Pendiente','Alerta.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(142,NULL,'M','Mostrado','Alerta.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(143,NULL,'P','Pendiente','Contrato.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(144,NULL,'T','Firmado','Contrato.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(145,NULL,'C','Cancelado','Contrato.Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(146,NULL,'FO','Fotografia','Documento.TipoDocumento','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(147,NULL,'PE','Documentos Personales','Documento.TipoDocumento','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(148,NULL,'LI','Licencia','Documento.TipoDocumento','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(149,NULL,'E','Entrada','Dashboard.TipoMarcacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(150,NULL,'A','Almuerzo','Dashboard.TipoMarcacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(151,NULL,'S','Salida','Dashboard.TipoMarcacion','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(152,NULL,'LU','Lunes','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(153,NULL,'MA','Martes','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(154,NULL,'MI','Miércoles','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(155,NULL,'JU','Jueves','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(156,NULL,'VI','Viernes','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(157,NULL,'SA','Sábado','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(158,NULL,'DO','Domingo','Dia','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(159,NULL,'EN','Enero','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(160,NULL,'FE','Febrero','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(161,NULL,'MR','Marzo','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(162,NULL,'AB','Abril','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(163,NULL,'MY','Mayo','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(164,NULL,'JN','Junio','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(165,NULL,'JL','Julio','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(166,NULL,'AG','Agosto','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(167,NULL,'SE','Setiembre','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(168,NULL,'OC','Octubre','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(169,NULL,'NO','Noviembre','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(170,NULL,'DI','Diciembre','Mes','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(171,NULL,'A','Activo','Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(172,NULL,'I','Inactivo','Estado','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(173,1,'SC','SCOTIABANK','Entidad Financiera','A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(174,1,'I2','INTEGRA COMISION MIXTA','AFP','A','TSS','2017-02-22 11:14:18.040000',NULL,NULL,1),
	(175,1,'H1','HABITAT COMISION MIXTA','AFP','A','TSS','2017-02-22 12:20:12.267000',NULL,NULL,1),
	(176,1,'RI','PRIMA COMISION MIXTA','AFP','A','TSS','2017-02-22 12:20:23.543000',NULL,NULL,1),
	(178,1,'N','No Recuperable','Permiso.Tipo','A','TSS','2017-03-16 17:01:30.493000',NULL,NULL,0),
	(179,NULL,'P','Por Temas Personales','HorasExtra.Tipo','A','TSS','2017-03-28 11:19:27.493000',NULL,NULL,1),
	(180,NULL,'C','Por Compensacion','HorasExtra.Tipo','A','TSS','2017-03-28 11:19:51.700000',NULL,NULL,1);

/*!40000 ALTER TABLE `tablageneral` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tipolicencia
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tipolicencia`;

CREATE TABLE `tipolicencia` (
  `IdTipoLicencia` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(2) NOT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `LimiteMensual` smallint(6) DEFAULT NULL,
  `LimiteAnual` smallint(6) DEFAULT NULL,
  `ActivaParaESS` tinyint(1) NOT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdTipoLicencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tipolicencia` WRITE;
/*!40000 ALTER TABLE `tipolicencia` DISABLE KEYS */;

INSERT INTO `tipolicencia` (`IdTipoLicencia`, `Codigo`, `Nombre`, `LimiteMensual`, `LimiteAnual`, `ActivaParaESS`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,'MA','Maternidad Pre y Post Natal',0,98,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(2,'FA','Falta No Justificada',3,3,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(3,'SA','Sanción Disciplinaria',30,30,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(4,'LS','Licencia sin Goce de Haberes',0,365,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(5,'LC','Licencia con Goce de Haberes',0,365,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(6,'PA','Paternidad',4,4,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(7,'CA','Capacitacion',0,160,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(8,'EN','Enfermedad',0,360,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(9,'AC','Accidente',0,360,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(10,'DA','Descanso Medico (Accidente)',20,20,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(11,'DE','Descanso Medico (Enfermedad)',20,20,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(12,'CU','Cumpleaños',1,1,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(13,'FL','Fallecimiento de un familiar',2,10,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(14,'EM','Examen Medico TSS',1,1,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(15,'AD','Adopción',30,30,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(16,'EF','Enfermedad familiar',7,7,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(17,'LA','Lactancia',0,365,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(18,'FE','Función edil',2,10,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(19,'MM','Miembro de mesa',2,10,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(20,'NM','Nacimiento múltiple',0,128,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(21,'CJ','Citación judicial',10,10,1,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1),
	(22,'NN','Nacimiento de niño discapacitado',0,120,0,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `tipolicencia` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table unidaddenegocio
# ------------------------------------------------------------

DROP TABLE IF EXISTS `unidaddenegocio`;

CREATE TABLE `unidaddenegocio` (
  `IdUnidadDeNegocio` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpresa` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `IdJefe` int(11) DEFAULT NULL,
  `JefeNoDisponible` tinyint(1) DEFAULT NULL,
  `IdJefeReemplazo` int(11) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdUnidadDeNegocio`),
  KEY `fkEmpresa_UnidadDeNegocio` (`IdEmpresa`),
  KEY `fkEmpleado_UnidadDeNegocio` (`IdJefe`),
  KEY `fkJefeReem_UnidadDeNegocio` (`IdJefeReemplazo`),
  CONSTRAINT `fkEmpleado_UnidadDeNegocio` FOREIGN KEY (`IdJefe`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkEmpresa_UnidadDeNegocio` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkJefeReem_UnidadDeNegocio` FOREIGN KEY (`IdJefeReemplazo`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `unidaddenegocio` WRITE;
/*!40000 ALTER TABLE `unidaddenegocio` DISABLE KEYS */;

INSERT INTO `unidaddenegocio` (`IdUnidadDeNegocio`, `IdEmpresa`, `Nombre`, `IdJefe`, `JefeNoDisponible`, `IdJefeReemplazo`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(1,1,'Trans Solution Systems',NULL,NULL,NULL,'A','TSS','2017-02-07 00:00:00.000000',NULL,NULL,1);

/*!40000 ALTER TABLE `unidaddenegocio` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usuario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `IdEmpleado` int(11) DEFAULT NULL,
  `CuentaUsuario` varchar(50) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `ApellidoPaterno` varchar(50) NOT NULL,
  `ApellidoMaterno` varchar(50) NOT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdUsuario`),
  KEY `fkEmpleado_Usuario` (`IdEmpleado`),
  CONSTRAINT `fkEmpleado_Usuario` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;

INSERT INTO `usuario` (`IdUsuario`, `IdEmpleado`, `CuentaUsuario`, `Password`, `Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Email`, `Estado`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`)
VALUES
	(491,3,'NELSSON.AGUILAR','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','NELSSON JOSE','AGUILAR ','SALVADOR','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(492,2,'WALTER.AGUILAR','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','WALTER CHARLE','AGUILAR ','ZEGARRA ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(493,5,'YESENIA.AGUIRRE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','YESENIA MELISA','AGUIRRE ','DIAZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(494,4,'DIEGO.AGUIRRE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DIEGO ELADIO','AGUIRRE ','RABANAL','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(495,8,'ERNESTO.ANGELES','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ERNESTO','ANGELES ','VALVERDE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(496,9,'CESAR.ANGULO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CESAR JESUS','ANGULO ','GASCO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(497,10,'DANIEL.ARELLANO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DANIEL HUGO','ARELLANO ','MONTOYA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(498,11,'ENRIQUE.ARENAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' YOBAR ENRIQUE','ARENAS ','COHA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(499,12,'DANIEL.ARIAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' DANIEL JUAN','ARIAS ','ASTOLA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(500,13,'JUAN.ARRUE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JUAN CARLOS','ARRUE ','CHAVEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(501,14,'EDGAR.ASPIROS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','EDGAR AUGUSTO','ASPIROS ','MIRANDA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(502,16,'JAQUELINE.BACA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JAQUELINE TERESA','BACA ','RIVERA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(503,17,'SILVIA.BARBA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','SILVIA ELENA','BARBA ','QUEZADA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(504,18,'','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','NILTON CESAR','BENITES ','PORRAS','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(505,19,'ROGER.BOBBIO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ROGER GUSTAVO','BOBBIO',' CAM','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(506,20,'GIANCARLO.BORJAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','GIANCARLO','BORJAS ','GIRALDO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(507,21,'ALONSO.BUSTOS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ALONSO','BUSTOS ','HERRERA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(508,23,'HUGO.CAM','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' HUGO','CAM ','TERRY','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(509,24,'MARGARITA.CARBAJAL','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MARGARITA CEC','CARBAJAL ','CAVERO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(510,25,'BETSY.CARDAMA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','BETSY LITA','CARDAMA ','BARCO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(511,26,'JOSE.CARDENAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JOSE LUIS','CARDENAS ','GUTIERREZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(512,28,'JOSE.CASTILLO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JOSE HILDEB','CASTILLO ','CARDENAS','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(513,27,'OSCAR.CASTILLO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','OSCAR ALEXAND','CASTILLO ','PAMPAS','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000','cron-usr','2017-03-24 10:01:23.286000',3),
	(514,29,'CRISTIAN.CCORI','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CRISTIAN GERALD','CCORI ','UGARTE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(515,30,'LUIS.CHAVEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','LUIS MIGUEL','CHAVEZ ','ZUMARAN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(516,31,'ALAN.CHAYAN','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ALAN RICARDO','CHAYAN ','SILVESTRE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(517,32,'FAVIO.CHILO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ELARD FAVIO','CHILO ','BERNUY','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(518,33,'FREDDY.CHOMBO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' FREDDY ORLANDO','CHOMBO ','HIDALGO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(519,35,'ESTANISLAO.CONTRERAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ESTANISLAO','CONTRERAS ','CHAVEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(520,34,'ESTHER.CONTRERAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ESTHER','CONTRERAS ','GALVAN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(521,36,'DIEGO.CORRADA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DIEGO FRANCESCO','CORRADA ','','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(522,37,'ROGER.CRUZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ROGER STEVE','CRUZ ','ROJAS','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(523,38,'JAVIER.CUICAPUZA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' JAVIER RICHARD','CUICAPUZA ','ANTONIO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000','cron-usr','2017-03-27 16:16:01.859000',2),
	(524,40,'OSCAR.DEPIEROLA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' OSCAR ALESS','DE PIEROLA ','ARDELA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(525,42,'JULISSA.DIAZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JULISSA KARIN','DIAZ ','AMPUERO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(526,44,'JESUS.DIAZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JESUS','DIAZ ','AVILA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(527,41,'JOSEDIAZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JOSE AMADEO MARTIN','DIAZ ','DIAZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(528,43,'JOSE.DIAZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JOSE LUIS','DIAZ ','GUTIERREZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(529,45,'XIOMI.EGUSQUIZA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' XIOMI GERAL','EGUSQUIZA ','ESCRIBA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(530,47,'CARLOS.ESPINO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CARLOS','ESPINO ','MARAVI','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(531,48,'JOSE.ESPINOZA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' JOSE ADALBER','ESPINOZA ','DELGADO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(532,49,'YOHANA.ESPINOZA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','YOHANA MAR','ESPINOZA ','SOLORZANO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(533,50,'MARCO.ESTRELLA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MARCO PATRICIO','ESTRELLA ','AMARO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(534,51,'KENYA.FERNANDEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','KENYA EYMI','FERNANDEZ ','CASTILLO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(535,52,'KIEFER.FERNANDEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','KIEFER','FERNANDEZ ','VALLADOLID','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(536,53,'LUIS.FUENTES','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' LUIS FERNANDO','FUENTES',' PAREDES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(537,54,'JORGE.GALINDO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JORGE','GALINDO ','ARAUCO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(538,55,'ANTONIO.GALVEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' ANTONIO','GALVEZ ','GALVEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(539,57,'JORGE.GARCIA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JORGE JAVIER','GARCIA ','JUNES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(540,58,'VIACHESLAV.GUEVARA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' VIACHESLAV','GUEVARA',' TARAMONA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(541,59,'SISSI.HIDALGO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','SISSI MARGARIT','HIDALGO ','ALEMAN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(542,60,'MIGUEL.ISA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MIGUEL ANGEL','ISA',' MATAYOSHI','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(543,61,'ELTON.LARA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ELTON HAMILTON','LARA ','FERREL','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(544,62,'MARJORIE.LEON','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MARJORIE HELE','LEON ','GASTAÑADUY','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(545,64,'CONSUELO.LUCAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DORINA CONSUELO','LUCAS ','FALCON','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(546,66,'RAUL.MAGUINA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','RAUL EDUARDO','MAGUIÑA ','CASTILLO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(547,67,'FRANK.MAMANI','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','FRANK GERARDO','MAMANI ','VELIZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(548,68,'CESAR.MARCHENA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CESAR ERNEST','MARCHENA',' MIRANDA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(549,69,'ALDO.MARTINEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ALDO DAVID','MARTINEZ ','ALARCON','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(550,70,'RODRIGO.MATTEO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','RODRIGO NICOL','MATTEO ','CASTILLO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(551,71,'IGOR.MEDINA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','IGOR ALEJANDRO','MEDINA ','GARCIA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(552,NULL,'PEDRO.MENDEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','PEDRO','MENDEZ CHACON',' RODRIGUEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(553,74,'LUIS.MIRANDA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','LUIS EDUARDO','MIRANDA ','ALVA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(554,75,'YNES.MOLOCHE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','YNES ADRIAN','MOLOCHE ','CHAMBERGO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(555,77,'FERNANDO.MORON','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','FERNANDO','MORON ','MOYANO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(556,78,'NICOLAS.NAKASONE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','NICOLAS FERNAN','NAKASONE ','ROQUE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(557,79,'RICHARD.NEGRON','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','RICHARD OSWAL','NEGRON ','GONZALES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(558,NULL,'JESSICA.NINAPAYTA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JESSICA KA','NINAPAYTA ','GONZALES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(559,81,'MANUEL.NIZAMA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MANUEL ISRAEL','NIZAMA ','PILLACA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(560,82,'JIM.OCANA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JIM FREDY','OCAÑA ','CORNEJO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(561,83,'WALTER.ORTIZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','WALTER DANTE','ORTIZ ','LLIMPE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(562,84,'PEDRO.PALACIOS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','PEDRO ANTONI','PALACIOS ','AGUILAR','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(563,86,'URBANO.PALOMINO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','URBANO','PALOMINO ','PALMA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(564,85,'MARCO.PALOMINO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' MARCO ANTONI','PALOMINO',' VASQUEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(565,87,'JOSE.PAUCAR','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JOSE JEANCA','PAUCAR ','VILLALOBOS','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(566,88,'MARCELA.PAZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MARCELA','PAZ ','ADAUTO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(567,90,'GONZALO.PASOS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','GONZALO ALFONS','PAZOS ','CARCELEN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(568,91,'ANATOLY.PEDEMONTE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ANATOLY ALEXEI','PEDEMONTE',' KU','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(569,93,'JUAN.PENA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JUAN CARLOS','PEÑA ','CASTILLO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(570,94,'VICTOR.POLO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','VICTOR OMAR','POLO ','TEJEDA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(571,96,'AUGUSTO.PRADO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','AUGUSTO','PRADO',' MORALES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(572,97,'ADOLFO.QUESQUEN','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ADOLFO SEGUND','QUESQUEN ','OSPINA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(573,98,'','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','EMILIO','QUISPE',' LICLA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(574,99,'CARLOS.RAMIREZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CARLOS ENRI','RAMIREZ ','CABALLERO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(575,100,'ELMER.RAMIREZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ELMER DAVID','RAMIREZ ','CHAVEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(576,102,'ALESSANDRA.REBAGLIATI','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ALESSANDRA','REBAGLIATI ','ORDOÑEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(577,101,'DANTE.SALAVERRY','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DANTE','REBAGLIATI',' SALAVERRY','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(578,103,'GONZALO.RECABARREN','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','GONZALO OMA','RECABARREN',' RIVERA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(579,104,'JUAN.REYES','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JUAN CARLOS','REYES ','SOLANO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(580,105,'BERENICE.REYNA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','BERENICE ELVIRA','REYNA ','HUAMAN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(581,106,'JUAN.RIVERA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JUAN JOSE','RIVERA ','REYNA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(582,107,'JHONY.RIVERO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JHONY ALEXANDER','RIVERO ','','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(583,108,'BELISSA.ROBLES','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DIANA BELISSA M','ROBLES ','GARCIA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(584,109,'FELIPE.ROJAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','FELIPE SANTIA','ROJAS',' RODRIGUEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(585,110,'ABEL.ROMAN','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ABEL JARED','ROMAN',' CESPEDES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(586,111,'JAMES.ROMERO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','PAUL','ROMERO ','GUZMAN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(587,NULL,'ARMANDO.RUIZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ARMANDO EULOGIO','RUIZ ','REBOLLAR','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(588,NULL,'MASSIEL.SAMMAME','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','KLYMBERI MASSI','SAMMAME ','MORENO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(589,113,'DAVID.SANDOVAL','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DAVID ALBER','SANDOVAL ','FELANDRO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(590,114,'EDUARDO.SARMIENTO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','EDUARDO C','SARMIENTO ','ERAUSQUIN','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(591,115,'VLADIMIR.SILVERIO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','VLADIMIR MARC','SILVERIO ','RIVERA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(592,116,'YHAIR.TAMAYO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','YHAIR','TAMAYO ','VILLAVICENCIO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(593,117,'JHOFFRETT.TAPIA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm',' JHOFFRETT','TAPIA ','TELLO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(594,118,'JUAN.TENORIO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JUAN CARLOS','TENORIO',' HUAYTALLA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(595,119,'JHONATAN.TIRADO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JHONATAN HAMNER','TIRADO ','TIRADO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(596,120,'RAJIV.TOORA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','RAJIV KUMAR','TOORA ','HURTADO ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(597,121,'JUAN.TORRES','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JUAN CARLOS','TORRES ','PARODI','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(598,122,'CARLOS.TREVEJO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CARLOS ALBERT','TREVEJO ','JIMENEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(599,123,'NEVHA.URDANEGUI','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','NEVHA','URDANEGUI ','ISMODES','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(600,125,'RONALD.VALDIVIA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','RONALD ARMAN','VALDIVIA ','MONTERO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(601,124,'PATRICIA.VALDIVIA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','DOLLY PATRICIA','VALDIVIA ','PONCE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(602,126,'JOSE.VALENCIA','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JOSE LUIS','VALENCIA ','CASTRO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(603,127,'ALAN.VALLADOLID','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ALAN ALEX','VALLADOLID ','VALLEJOS','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(604,128,'JORGE.VARGAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JORGE JEFFREY','VARGAS ','IPINCE','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(605,130,'MIRTHA.VELARDE','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','MIRTHA','VELARDE AREVALO',' DE SHERRILL','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(606,131,'FREDY.VELASQUEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','FREDY RUBE','VELASQUEZ',' CASTILLO','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(607,133,'CRISTIAN.VILLEGAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CRISTIAN','VILLEGAS ','CHAVEZ','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(608,132,'MICHAEL.VILLEGAS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','PERCY MICHAE','VILLEGAS',' CUELLAR','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(609,NULL,'JORGE.YANEZ','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','JORGE ALEXANDER','YAÑEZ ','RANGEL','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(610,134,'CHRISTIAN.ZAMUDIO','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','CHRISTIAN AD','ZAMUDIO ','NAKAMURA','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(611,135,'NELLY.ZEVALLOS','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','NELLY','ZEVALLOS ','REGAL','jamdiazdiaz@gmail.com','A','TSS','2017-02-10 00:00:00.000000',NULL,NULL,1),
	(612,NULL,'admin','$2a$04$BrZ9aISZIOKswlfQgFKzDOvx12mZBMxUxM/i6wBnLUUKzxWrWlOKm','ADMIN','ADMIN','ADMIN','jamdiazdiaz@gmail.com','A','cron-usr','2017-02-17 10:34:30.976000',NULL,NULL,0);

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usuarioreset
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuarioreset`;

CREATE TABLE `usuarioreset` (
  `IdUsuarioReset` int(11) NOT NULL AUTO_INCREMENT,
  `IdUsuario` int(11) NOT NULL,
  `Enlace` varchar(150) DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `FechaInicio` datetime(6) DEFAULT NULL,
  `FechaFin` datetime(6) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`IdUsuarioReset`),
  KEY `fkUsuario_UsuarioReset` (`IdUsuario`),
  CONSTRAINT `fkUsuario_UsuarioReset` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table usuariorol
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuariorol`;

CREATE TABLE `usuariorol` (
  `IdUsuarioRol` int(11) NOT NULL AUTO_INCREMENT,
  `IdUsuario` int(11) NOT NULL,
  `IdRol` int(11) NOT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `PorDefecto` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdUsuarioRol`),
  KEY `fkUsuario_UsuarioRol` (`IdUsuario`),
  KEY `fkRol_UsuarioRol` (`IdRol`),
  CONSTRAINT `fkRol_UsuarioRol` FOREIGN KEY (`IdRol`) REFERENCES `rol` (`IdRol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUsuario_UsuarioRol` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `usuariorol` WRITE;
/*!40000 ALTER TABLE `usuariorol` DISABLE KEYS */;

INSERT INTO `usuariorol` (`IdUsuarioRol`, `IdUsuario`, `IdRol`, `Creador`, `FechaCreacion`, `Actualizador`, `FechaActualizacion`, `RowVersion`, `PorDefecto`)
VALUES
	(121,491,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(122,492,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(123,493,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(124,494,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(125,495,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(126,496,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(127,497,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(128,498,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(129,499,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(130,500,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(131,501,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(132,502,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(133,503,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(134,505,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(135,506,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(136,507,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(137,508,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(138,509,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(139,510,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(140,511,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(141,512,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(142,513,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(143,514,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(144,515,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(145,516,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(146,517,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(147,518,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(148,519,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(149,520,2,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(150,521,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(151,522,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(152,523,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(153,524,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(154,525,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(155,526,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(156,527,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(157,528,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(158,529,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(159,530,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(160,531,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(161,532,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(162,533,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(163,534,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(164,535,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(165,536,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(166,537,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(167,538,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(168,539,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(169,540,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(170,541,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(171,542,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(172,543,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(173,544,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(174,545,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(175,546,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(176,547,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(177,548,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(178,549,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(179,550,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(180,551,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(181,552,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(182,553,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(183,554,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(184,555,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(185,556,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(186,557,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(187,558,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(188,559,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(189,560,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(190,561,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(191,562,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(192,563,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(193,564,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(194,565,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(195,566,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(196,567,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(197,568,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(198,569,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(199,570,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(200,571,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(201,572,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(202,574,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(203,575,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(204,576,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(205,577,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(206,578,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(207,579,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(208,580,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(209,581,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(210,582,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(211,583,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(212,584,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(213,585,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(214,586,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(215,587,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(216,588,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(217,589,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(218,590,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(219,591,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(220,592,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(221,593,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(222,594,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(223,595,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(224,596,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(225,597,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(226,598,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(227,599,4,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(228,599,2,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(229,600,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(230,601,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(231,602,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(232,603,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(233,604,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(234,605,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(235,606,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(236,607,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(237,608,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(238,609,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(239,610,3,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,1),
	(240,611,2,'TSS','2017-02-10 00:00:00.000000',NULL,NULL,1,0),
	(242,599,3,'TSS','2017-02-15 15:37:01.293000',NULL,NULL,1,0),
	(243,612,1,'cron-usr','2017-02-17 10:34:30.984000',NULL,NULL,0,1),
	(244,520,3,'TSS','2017-02-27 14:16:34.179000',NULL,NULL,1,0),
	(245,577,3,'TSS','2017-02-27 16:06:53.217000',NULL,NULL,0,1),
	(246,498,3,'TSS','2017-02-27 16:06:53.223000',NULL,NULL,0,1),
	(247,519,3,'TSS','2017-02-27 16:06:53.230000',NULL,NULL,0,0),
	(248,584,3,'TSS','2017-02-27 16:06:53.233000',NULL,NULL,0,1),
	(249,555,3,'TSS','2017-02-27 16:06:53.240000',NULL,NULL,0,1),
	(250,508,3,'TSS','2017-02-27 16:06:53.247000',NULL,NULL,0,1),
	(251,502,4,'TSS','2017-02-27 16:06:53.253000',NULL,NULL,0,0),
	(252,526,3,'TSS','2017-02-27 16:06:53.260000',NULL,NULL,0,1),
	(253,611,3,'TSS','2017-02-27 16:06:53.263000',NULL,NULL,0,1),
	(254,590,4,'TSS','2017-03-14 17:51:06.627000',NULL,NULL,0,0),
	(256,506,1,'TSS','2017-03-20 12:53:56.890000',NULL,NULL,0,0);

/*!40000 ALTER TABLE `usuariorol` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table vacacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `vacacion`;

CREATE TABLE `vacacion` (
  `IdVacacion` int(11) NOT NULL AUTO_INCREMENT,
  `IdPeriodoEmpleado` int(11) NOT NULL,
  `IdAtendidoPor` int(11) DEFAULT NULL,
  `FechaInicio` datetime(6) NOT NULL,
  `FechaFin` datetime(6) NOT NULL,
  `DiasCalendarios` tinyint(3) unsigned DEFAULT NULL,
  `DiasHabiles` tinyint(3) unsigned DEFAULT NULL,
  `Estado` varchar(1) NOT NULL,
  `ComentarioResolucion` varchar(250) DEFAULT NULL,
  `Creador` varchar(50) NOT NULL,
  `FechaCreacion` datetime(6) NOT NULL,
  `Actualizador` varchar(50) DEFAULT NULL,
  `FechaActualizacion` datetime(6) DEFAULT NULL,
  `RowVersion` int(11) NOT NULL,
  `Regularizacion` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdVacacion`),
  KEY `fkEmpleado_Vacacion` (`IdAtendidoPor`),
  KEY `fkPeriodoEmpleado_Vacacion` (`IdPeriodoEmpleado`),
  CONSTRAINT `fkEmpleado_Vacacion` FOREIGN KEY (`IdAtendidoPor`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkPeriodoEmpleado_Vacacion` FOREIGN KEY (`IdPeriodoEmpleado`) REFERENCES `periodoempleado` (`IdPeriodoEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
