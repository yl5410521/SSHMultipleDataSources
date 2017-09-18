/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.37 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_content` (
	`id` varchar (96),
	`create_date` datetime ,
	`modify_date` datetime ,
	`contentType` varchar (765),
	`Fieldtype` varchar (765),
	`name` varchar (765)
); 
insert into `t_content` (`id`, `create_date`, `modify_date`, `contentType`, `Fieldtype`, `name`) values('144058q12v8n0450','2015-08-26 17:28:00','2015-08-26 17:28:00','String','varchar','文本框');
insert into `t_content` (`id`, `create_date`, `modify_date`, `contentType`, `Fieldtype`, `name`) values('14a40z5814y01416','2015-08-26 17:30:01','2015-08-26 17:30:01','String','text','HTML框');
insert into `t_content` (`id`, `create_date`, `modify_date`, `contentType`, `Fieldtype`, `name`) values('1lrq440581467896','2015-08-26 17:31:07','2015-08-26 17:31:07','Float','decimal','价格框');
insert into `t_content` (`id`, `create_date`, `modify_date`, `contentType`, `Fieldtype`, `name`) values('f144058g132b0662','2015-08-26 17:28:40','2015-08-26 17:28:40','int','int','数字框');
insert into `t_content` (`id`, `create_date`, `modify_date`, `contentType`, `Fieldtype`, `name`) values('n1440581lc362209','2015-08-26 17:29:22','2015-08-26 17:29:22','Date','datetime','时间框');
