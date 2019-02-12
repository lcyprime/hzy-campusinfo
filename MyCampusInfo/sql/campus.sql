DROP DATABASE IF EXISTS campus;
CREATE DATABASE IF NOT EXISTS campus;
USE campus;



DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_date` varchar(50) NOT NULL,
  `log_level` varchar(8) NOT NULL,
  `location` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL,
  PRIMARY KEY  (`log_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `username` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;



INSERT INTO `user` (`id`,`username`,`password`,`role`) VALUES 
 (1,'admin','E10ADC3949BA59ABBE56E057F20F883E','admin'),
 (2,'user','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (3,'test','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (4,'李明','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (5,'刘锋','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (6,'周大鹏','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (7,'张科明','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (8,'王兵','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (9,'廖平','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (10,'黄小丽','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (11,'李华','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (12,'周冬琴','E10ADC3949BA59ABBE56E057F20F883E','user'),
 (13,'简利平','E10ADC3949BA59ABBE56E057F20F883E','user');



DROP TABLE IF EXISTS `user_certificate`;
CREATE TABLE `user_certificate` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) NOT NULL,
  `certificate` varchar(45) NOT NULL,
  `level` varchar(45) NOT NULL,
  `state` smallint(5) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) NOT NULL,
  `course` varchar(45) NOT NULL,
  `credit` smallint(5) unsigned NOT NULL,
  `state` smallint(5) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `order_id` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) NOT NULL,
  `order_business` varchar(45) NOT NULL,
  `order_time` datetime NOT NULL,
  `order_state` smallint(5) unsigned NOT NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `my_id` int(10) unsigned NOT NULL,
  `other_id` int(10) unsigned NOT NULL,
  `relation` varchar(45) NOT NULL,
  `state` smallint(5) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




