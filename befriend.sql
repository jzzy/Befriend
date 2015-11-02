/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : befriend

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-11-02 18:20:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `time` varchar(255) DEFAULT '2014-04-22 15:14:50',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for adv
-- ----------------------------
DROP TABLE IF EXISTS `adv`;
CREATE TABLE `adv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `summary` text,
  `time` varchar(255) DEFAULT NULL,
  `pathimg` text,
  `sequence` int(11) NOT NULL DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `finaltime` varchar(255) DEFAULT NULL,
  `calculatingTime` int(255) DEFAULT '0',
  `online` int(11) DEFAULT '0',
  `bbt` int(11) DEFAULT '0',
  `syn` int(11) DEFAULT '0',
  `wechat` int(11) DEFAULT '0',
  `ios` int(11) DEFAULT '0',
  `android` int(11) DEFAULT '0',
  `web` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `summary` text,
  `time` varchar(255) DEFAULT NULL,
  `pathapk` varchar(255) DEFAULT NULL,
  `pathimg` varchar(255) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `downloads` int(11) DEFAULT NULL,
  `pathimg1` varchar(255) DEFAULT NULL,
  `pathimg2` varchar(255) DEFAULT NULL,
  `pathimg3` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `vnum` varchar(255) DEFAULT NULL,
  `dpt` text,
  `realds` int(11) DEFAULT NULL,
  `appsize` float(11,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for appup
-- ----------------------------
DROP TABLE IF EXISTS `appup`;
CREATE TABLE `appup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apptv` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `updates` text,
  `upnum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `summary` text,
  `review` text,
  `time` varchar(255) DEFAULT NULL,
  `admin` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `house` varchar(255) DEFAULT NULL,
  `hits` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for cis
-- ----------------------------
DROP TABLE IF EXISTS `cis`;
CREATE TABLE `cis` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `senduserid` int(11) DEFAULT '0',
  `groupid` int(11) DEFAULT '0',
  `information` text,
  `time` datetime DEFAULT NULL,
  `userid` int(255) DEFAULT '0',
  `online` int(11) DEFAULT '0',
  `ip` varchar(255) DEFAULT '0.0.0.0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `newsid` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `newstitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=568 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for edu_services
-- ----------------------------
DROP TABLE IF EXISTS `edu_services`;
CREATE TABLE `edu_services` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `merchant_id` varchar(32) NOT NULL,
  `is_vip` varchar(16) DEFAULT NULL,
  `is_stop` varchar(32) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `alias` varchar(128) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `city_id` varchar(64) DEFAULT NULL,
  `pro_pinyin` varchar(128) DEFAULT NULL,
  `county` varchar(128) DEFAULT NULL,
  `class_first` varchar(64) DEFAULT NULL,
  `class_first_id` varchar(32) DEFAULT NULL,
  `class_second` varchar(64) DEFAULT NULL,
  `class_second_id` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `business_hours` varchar(255) DEFAULT NULL,
  `ave_price` varchar(16) DEFAULT NULL,
  `star` varchar(16) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `describe` text,
  `label` varchar(255) DEFAULT NULL,
  `map_type` varchar(16) DEFAULT NULL,
  `latitude` varchar(16) DEFAULT NULL,
  `longitude` varchar(16) DEFAULT NULL,
  `env_score` varchar(16) DEFAULT NULL,
  `ser_score` varchar(16) DEFAULT NULL,
  `five_star` varchar(16) DEFAULT NULL,
  `four_star` varchar(16) DEFAULT NULL,
  `three_star` varchar(16) DEFAULT NULL,
  `two_star` varchar(16) DEFAULT NULL,
  `one_star` varchar(16) DEFAULT NULL,
  `is_store` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`,`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=622291 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `information` text,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for follect
-- ----------------------------
DROP TABLE IF EXISTS `follect`;
CREATE TABLE `follect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `forumid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=620 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forumone
-- ----------------------------
DROP TABLE IF EXISTS `forumone`;
CREATE TABLE `forumone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `total` int(11) DEFAULT '0',
  `userid` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT NULL,
  `content` longtext,
  `area` varchar(255) DEFAULT NULL,
  `areas` varchar(255) DEFAULT NULL,
  `follectnum` int(11) DEFAULT '0',
  `frs` int(11) DEFAULT '0',
  `fHits` int(11) DEFAULT '0',
  `img` varchar(255) DEFAULT NULL,
  `admin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forumthree
-- ----------------------------
DROP TABLE IF EXISTS `forumthree`;
CREATE TABLE `forumthree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `touserid` int(11) DEFAULT NULL,
  `reply` longtext,
  `time` varchar(255) DEFAULT NULL,
  `forumid` int(11) DEFAULT NULL,
  `forumtwoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forumtwo
-- ----------------------------
DROP TABLE IF EXISTS `forumtwo`;
CREATE TABLE `forumtwo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `reply` longtext,
  `time` varchar(255) DEFAULT NULL,
  `forumid` int(11) DEFAULT NULL,
  `touserid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1350 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for groupchat
-- ----------------------------
DROP TABLE IF EXISTS `groupchat`;
CREATE TABLE `groupchat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '我的班级',
  `userid` int(255) DEFAULT '1234',
  `img` varchar(255) DEFAULT NULL,
  `schoolname` varchar(255) DEFAULT NULL,
  `schooladdress` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `gclass` varchar(255) DEFAULT NULL,
  `headteachername` varchar(255) DEFAULT NULL,
  `htphone` varchar(255) DEFAULT NULL,
  `groupno` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT '2014-02-11 16:52:30',
  `groupid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for groupmembers
-- ----------------------------
DROP TABLE IF EXISTS `groupmembers`;
CREATE TABLE `groupmembers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT '0',
  `groupid` int(11) DEFAULT '0',
  `urp` int(1) DEFAULT '0',
  `time` varchar(255) DEFAULT '2015-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolname` text,
  `address` text,
  `scope` text,
  `property` text,
  `time` text,
  `admin` text,
  `count` int(11) DEFAULT '0',
  `keywords` text,
  `hits` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2067 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for letter
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `img` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `hits` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `content` longtext,
  `imgmax` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT '2015-01-01 00:00:00',
  `collectnum` int(11) DEFAULT '0',
  `reviews` int(11) DEFAULT '0',
  `area` varchar(255) DEFAULT NULL,
  `areas` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `types` varchar(255) DEFAULT NULL,
  `hits` int(11) DEFAULT '0',
  `cah` int(11) DEFAULT '0',
  `admin` varchar(255) DEFAULT '无',
  `expert` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1681 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for password
-- ----------------------------
DROP TABLE IF EXISTS `password`;
CREATE TABLE `password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64743 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT '0',
  `sdname` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `kip` varchar(255) DEFAULT NULL,
  `ddb` int(11) DEFAULT '0',
  `judge` int(11) DEFAULT '-1',
  `rsbs` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT '未填写',
  `addcity` varchar(255) DEFAULT '未填写',
  `Note7` int(11) DEFAULT '0',
  `Note30` int(11) DEFAULT '0',
  `Note365` int(11) DEFAULT '0',
  `noteall` int(11) DEFAULT '0',
  `Note` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT '2015-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `newsid` int(255) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1791 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for setip
-- ----------------------------
DROP TABLE IF EXISTS `setip`;
CREATE TABLE `setip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stas
-- ----------------------------
DROP TABLE IF EXISTS `stas`;
CREATE TABLE `stas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vored` int(11) DEFAULT '0',
  `usersaved` int(11) DEFAULT '0',
  `userlogined` int(11) DEFAULT '0',
  `usersyned` int(11) DEFAULT '0',
  `downloaded` int(11) DEFAULT '0',
  `time` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `pv` int(11) DEFAULT '0',
  `uv` int(11) DEFAULT '0',
  `ip` int(11) DEFAULT '0',
  `home1` int(11) DEFAULT '0',
  `home2` int(11) DEFAULT '0',
  `home3` int(11) DEFAULT '0',
  `home31` int(11) DEFAULT '0',
  `home32` int(11) DEFAULT '0',
  `home33` int(11) DEFAULT '0',
  `home34` int(11) DEFAULT '0',
  `home331` int(11) DEFAULT '0',
  `home332` int(11) DEFAULT '0',
  `home333` int(11) DEFAULT '0',
  `home334` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1321 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT '未填写',
  `phone` varchar(255) DEFAULT '未填写',
  `stage` varchar(255) DEFAULT '未填写',
  `school` varchar(255) DEFAULT '未填写',
  `address` varchar(255) DEFAULT '未选取',
  `addcity` varchar(255) DEFAULT '未选取',
  `time` varchar(255) DEFAULT '2015-01-01 00:00:00',
  `img` varchar(255) DEFAULT NULL,
  `competence` int(11) DEFAULT '0',
  `gag` int(11) DEFAULT '0',
  `loginnum` int(11) DEFAULT '0',
  `finaltime` varchar(255) DEFAULT '2015-01-01 00:00:00',
  `ip` varchar(255) DEFAULT '0.0.0.0',
  `port` int(11) DEFAULT '0',
  `online` int(11) DEFAULT '0',
  `accnumno` varchar(255) DEFAULT NULL,
  `come` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `lookphone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64817 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appmac` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
