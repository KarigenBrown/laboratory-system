-- MySQL dump 10.13  Distrib 5.7.40, for Linux (x86_64)
--
-- Host: localhost    Database: web
-- ------------------------------------------------------
-- Server version	5.7.40

create database web;
use web;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;



DROP TABLE IF EXISTS `web_achievement`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_achievement`
(
    -- 原论文的字段
    `id`                   int(10) unsigned NOT NULL AUTO_INCREMENT,
    `title`                varchar(100) DEFAULT '' COMMENT '新闻标题', -- article title与achievement name合并
    `journal`              varchar(100) DEFAULT '' COMMENT '期刊',
    `author`               varchar(100) DEFAULT '' COMMENT '第一作者',
    `authors`              varchar(100) DEFAULT '' COMMENT '其他作者',
    `date`                 varchar(100) DEFAULT '' COMMENT '时间',
    `link`                 varchar(100) DEFAULT '详情页链接',
    `papercode`            varchar(100) DEFAULT '代码',
    `theyear`              varchar(10)  DEFAULT '' COMMENT '论文年份',
    `abstract`             text COMMENT '摘要',

    category               varchar(100)     not null comment '类别',
    initials               varchar(255)     not null comment '论文首字母',
    internal               int(1)       default 1 comment '是否为实验室内部论文',
    article_status         varchar(50) comment '论文状态',
    hidden                 int(1)       default 0 comment '是否隐藏',
    technique_status       varchar(50) comment '技术状态',

    -- 原成就字段
    -- `id`       int(10) unsigned NOT NULL AUTO_INCREMENT,
    -- `name`     varchar(100) DEFAULT '' COMMENT '名称', -- 与title合并
    `achievement_category` varchar(100) DEFAULT '' COMMENT '类别',
    `address`              varchar(100) DEFAULT '' COMMENT '地址',

    deleted                int(1)       default 0 comment '逻辑删除标志(0代表未删除,1代表已删除)',
    create_by              int(10)      default null,
    create_time            datetime     default null,
    update_by              int(10)      default null,
    update_time            datetime     default null,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 94
  DEFAULT CHARSET = utf8 COMMENT ='论文管理';
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `web_manager`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_manager`
(
    `id`        int(10) unsigned NOT NULL AUTO_INCREMENT,
    `username`  varchar(50)  DEFAULT '' COMMENT '账号',
    `password`  varchar(50)  DEFAULT '' COMMENT '密码',

    permits     varchar(100) default '个人' comment '权限',
    deleted     int(1)       default 0 comment '逻辑删除标志(0代表未删除,1代表已删除)',
    create_by   int(10)      default null,
    create_time datetime     default null,
    update_by   int(10)      default null,
    update_time datetime     default null,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `web_member`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_member`
(
    `id`                   int(10) unsigned NOT NULL AUTO_INCREMENT,
    `identity`             varchar(100) DEFAULT '' COMMENT '身份',
    `name`                 varchar(100) DEFAULT '' COMMENT '姓名',
    `contact`              varchar(100) DEFAULT '' COMMENT '联系方式',
    `research`             varchar(100) DEFAULT '' COMMENT '研究方向',
    `achievement`          text COMMENT '科研成果',
    `introduction`         text COMMENT '个人经历',

    number         varchar(100)     not null comment '学工号',
    grade                  varchar(50) comment '在校生年级',
    hidden_fields          varchar(255) comment '隐藏字段,分割',
    photo_url              varchar(255)     not null comment '照片链接',
    graduation_destination varchar(255) comment '毕业去向',
    graduation_time        date comment '毕业时间',
    deleted                int(1)       default 0 comment '逻辑删除标志(0代表未删除,1代表已删除)',
    create_by              int(10)      default null,
    create_time            datetime     default null,
    update_by              int(10)      default null,
    update_time            datetime     default null,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 46
  DEFAULT CHARSET = utf8 COMMENT ='成员管理';
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `web_project`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_project`
(
    `id`        int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name`      varchar(100) DEFAULT '' COMMENT '项目名称',
    `link`      varchar(100) DEFAULT '' COMMENT '项目链接',
    `theyear`   varchar(10)  DEFAULT '' COMMENT '项目年份',
    content     text,

    status      varchar(50) comment '项目状态',
    deleted     int(1)       default 0 comment '逻辑删除标志(0代表未删除,1代表已删除)',
    create_by   int(10)      default null,
    create_time datetime     default null,
    update_by   int(10)      default null,
    update_time datetime     default null,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `web_visitors`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_visitors`
(
    `id`         int(10) unsigned NOT NULL AUTO_INCREMENT,
    `visitornum` int(11) DEFAULT '0' COMMENT '访问量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 COMMENT ='访问管理';
/*!40101 SET character_set_client = @saved_cs_client */;



/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04 17:02:05

create table web_demo
(
    id           int(10) unsigned primary key auto_increment,
    title        varchar(255) not null,
    time         date         not null,
    `group`      varchar(255) not null,
    introduction text         not null,
    video_urls   text         not null comment '换行分割',
    photo_urls   text         not null comment '换行分割',
    location     varchar(255) not null,
    content      text         not null,
    keywords     varchar(255) not null comment ',分割',

    deleted      int(1)   default 0 comment '逻辑删除标志(0代表未删除,1代表已删除)',
    create_by    int(10)  default null,
    create_time  datetime default null,
    update_by    int(10)  default null,
    update_time  datetime default null
);

create table web_activity
(
    id           int(10) unsigned primary key auto_increment,
    title        varchar(255) not null,
    urls         text comment '换行分割',
    introduction text         not null,
    content      text         not null,

    deleted      int(1)   default 0 comment '逻辑删除标志(0代表未删除,1代表已删除)',
    create_by    int(10)  default null,
    create_time  datetime default null,
    update_by    int(10)  default null,
    update_time  datetime default null
);

create table web_log
(
    id          int(10) unsigned primary key auto_increment,
    create_time datetime default null,
    userid      int(10) not null,
    log         text    not null
);