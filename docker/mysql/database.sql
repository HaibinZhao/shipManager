# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.18)
# Database: shipManager
# Generation Time: 2017-11-06 16:25:22 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table shm_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shm_roles`;

CREATE TABLE `shm_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



# Dump of table shm_sailor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shm_sailor`;

CREATE TABLE `shm_sailor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `duty` varchar(255) NOT NULL,
  `emergency_mobile` varchar(255) NOT NULL,
  `identity_id` varchar(255) NOT NULL,
  `is_advanced` bit(1) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sailor_card_id` varchar(255) NOT NULL,
  `sailor_cert_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bs6ok7ntjky5lpme3s86uhhe9` (`identity_id`),
  UNIQUE KEY `UK_7b5kt0cw5aguq4srms7cykkr7` (`sailor_card_id`),
  UNIQUE KEY `UK_srcg1cvug350qynt1k3hinhnd` (`sailor_cert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table shm_ship
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shm_ship`;

CREATE TABLE `shm_ship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fcreyowoqn6n8vtm91twhpm0j` (`email`),
  UNIQUE KEY `UK_qps9pd8hny32bp8gp6xx3ukke` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table shm_users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `shm_users`;

CREATE TABLE `shm_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_odr8nf8dpx8h2c9jjnbsfnjln` (`email`),
  UNIQUE KEY `UK_ai4eslghvmy643n856fie3960` (`name`),
  KEY `FK154rmvy7cm0w8b5b5fskowxfc` (`role_id`),
  CONSTRAINT `FK154rmvy7cm0w8b5b5fskowxfc` FOREIGN KEY (`role_id`) REFERENCES `shm_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
