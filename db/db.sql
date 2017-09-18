/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.29 : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) NOT NULL,
  `departmentid` varchar(32) DEFAULT NULL,
  `roleid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ru577oyl9l740lyua64qtpghc` (`departmentid`),
  KEY `FK_2i9d7ynp99wortu2hbuwb0yy` (`roleid`),
  CONSTRAINT `FK_2i9d7ynp99wortu2hbuwb0yy` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_ru577oyl9l740lyua64qtpghc` FOREIGN KEY (`departmentid`) REFERENCES `t_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`create_date`,`modify_date`,`address`,`mobile`,`name`,`password`,`userName`,`departmentid`,`roleid`) values ('1443b4290h554j94','2015-09-28 16:30:55','2015-09-28 16:30:55','重庆','100000000000','系统管理员','123456','admin','14e427529311aq69','14427m5745q57w20');

/*Table structure for table `t_content` */

DROP TABLE IF EXISTS `t_content`;

CREATE TABLE `t_content` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `contentType` varchar(255) DEFAULT NULL,
  `fieldsType` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_content` */

insert  into `t_content`(`id`,`create_date`,`modify_date`,`contentType`,`fieldsType`,`name`) values ('144058q12v8n0450','2015-08-26 17:28:00','2015-08-26 17:28:00','String','varchar','文本框'),('14a40z5814y01416','2015-08-26 17:30:01','2015-08-26 17:30:01','String','text','HTML框'),('1lrq440581467896','2015-08-26 17:31:07','2015-08-26 17:31:07','Float','decimal','价格框'),('f144058g132b0662','2015-08-26 17:28:40','2015-08-26 17:28:40','int','int','数字框'),('n1440581lc362209','2015-08-26 17:29:22','2015-08-26 17:29:22','Date','datetime','时间框');

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`create_date`,`modify_date`,`name`) values ('14e427529311aq69','2015-09-20 20:42:11','2015-09-20 20:42:11','系统管理');

/*Table structure for table `t_field` */

DROP TABLE IF EXISTS `t_field`;

CREATE TABLE `t_field` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `fieldLength` varchar(255) DEFAULT NULL,
  `fieldName` varchar(255) DEFAULT NULL,
  `fieldcontent` varchar(255) DEFAULT NULL,
  `fieldtype` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `spreadSheets_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iehr9cowh2rh7rt08d9o4renu` (`spreadSheets_id`),
  CONSTRAINT `FK_iehr9cowh2rh7rt08d9o4renu` FOREIGN KEY (`spreadSheets_id`) REFERENCES `t_spread_sheets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_field` */

insert  into `t_field`(`id`,`create_date`,`modify_date`,`fieldLength`,`fieldName`,`fieldcontent`,`fieldtype`,`name`,`spreadSheets_id`) values ('1443fg5q11402157','2015-09-29 15:23:22','2015-09-29 15:23:22','36','name','String','varchar','姓名','14c4351a1401f967'),('1443x51g15b19855','2015-09-29 15:25:19','2015-09-29 15:25:19','36','name','String','varchar','部门名称','144351151o97bc70'),('144a35114e026w25','2015-09-29 15:23:22','2015-09-29 15:23:22',' ','modifyDate','Date','datetime','修改时间','14c4351a1401f967'),('144n3511520f00r1','2015-09-29 15:25:20','2015-09-29 15:25:20',' ','createDate','Date','datetime','创建时间','144351151o97bc70'),('144tf3v511402256','2015-09-29 15:23:22','2015-09-29 15:23:22','36','username','String','varchar','用户名','14c4351a1401f967'),('14d4rl3511519996','2015-09-29 15:25:19','2015-09-29 15:25:19','32','id','String','varchar','主键','144351151o97bc70'),('1u4435sk11559790','2015-09-29 15:25:59','2015-09-29 15:25:59','32','id','String','varchar','主键','144szc3511559539'),('1zk44351u1402613','2015-09-29 15:23:22','2015-09-29 15:23:22','32','id','String','varchar','主键','14c4351a1401f967'),('e1443511559ue630','2015-09-29 15:25:59','2015-09-29 15:25:59','36','name','String','varchar','角色名称','144szc3511559539'),('g1443b5115597w95','2015-09-29 15:25:59','2015-09-29 15:25:59',' ','createDate','Date','datetime','创建时间','144szc3511559539'),('i14435114lf02330','2015-09-29 15:23:22','2015-09-29 15:23:22','32','password','String','varchar','密码','14c4351a1401f967'),('i144qj3511402431','2015-09-29 15:23:22','2015-09-29 15:23:22','80','address','String','varchar','地址','14c4351a1401f967'),('jbj1443511402381','2015-09-29 15:23:22','2015-09-29 15:23:22','15','mobile','String','varchar','联系电话','14c4351a1401f967'),('o1443d5115n20006','2015-09-29 15:25:20','2015-09-29 15:25:20',' ','modifyDate','Date','datetime','修改时间','144351151o97bc70'),('u1443511dg402620','2015-09-29 15:23:22','2015-09-29 15:23:22',' ','createDate','Date','datetime','创建时间','14c4351a1401f967'),('x144351u15s59800','2015-09-29 15:25:59','2015-09-29 15:25:59',' ','modifyDate','Date','datetime','修改时间','144szc3511559539');

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `add` int(11) DEFAULT '0',
  `del` int(11) DEFAULT '0',
  `upd` int(11) DEFAULT '0',
  `sel` int(11) DEFAULT '0',
  `roleid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `spreadsheetsid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_permission` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`create_date`,`modify_date`,`name`) values ('14427m5745q57w20','2015-09-20 21:57:35','2015-09-20 21:57:35','系统管理员');

/*Table structure for table `t_spread_sheets` */

DROP TABLE IF EXISTS `t_spread_sheets`;

CREATE TABLE `t_spread_sheets` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  `tableName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_spread_sheets` */

insert  into `t_spread_sheets`(`id`,`create_date`,`modify_date`,`className`,`tableName`) values ('144351151o97bc70','2015-09-29 15:25:19','2015-09-29 15:25:19','Department','t_department'),('144szc3511559539','2015-09-29 15:25:59','2015-09-29 15:25:59','Role','t_role'),('14c4351a1401f967','2015-09-29 15:23:21','2015-09-29 15:23:21','Admin','t_admin');

/*Table structure for table `t_tach` */

DROP TABLE IF EXISTS `t_tach`;

CREATE TABLE `t_tach` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `try_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `try_id` (`try_id`),
  CONSTRAINT `try_id` FOREIGN KEY (`try_id`) REFERENCES `t_try` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_tach` */

/*Table structure for table `t_try` */

DROP TABLE IF EXISTS `t_try`;

CREATE TABLE `t_try` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `admin_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `t_try` */

/* Procedure structure for procedure `region_pro` */

/*!50003 DROP PROCEDURE IF EXISTS  `region_pro` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `region_pro`(in _lft int,in fid int,out m int)
BEGIN
	declare _id int default 0;
	declare done int default 0;
	declare region_cursor cursor for select id from region where pid=fid;
	declare continue handler for sqlstate '02000' set done=1;
	SET m=_lft;
	set @@max_sp_recursion_depth =2000;
	open region_cursor;
	REPEAT
		FETCH region_cursor INTO _id;
		IF NOT done THEN
		  call region_pro(m+1,_id,m);
		  set m=m+1;
		END IF;
	UNTIL done END REPEAT;
	update region set lft=_lft,rgt=m+1 where id=fid;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
