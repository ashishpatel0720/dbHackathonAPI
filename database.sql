drop database if exists dbhackathon;
create database dbhackathon;
use dbhackathon;

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
                         `id` int auto_increment primary key ,
                         `email` text not null ,
                         `name` text not null ,
                         `password` text not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;

INSERT INTO `admin` (`email`, `name`, `password`)
VALUES
('ashish.patel@db.com','Ashish Patel','123');

/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `user` (
                        `id` int auto_increment primary key ,
                        `email` text not null ,
                        `name` text not null ,
                        `password` text not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` ( `email`, `name`, `password`)
VALUES
('saina.n@db.com','Saina N','123'),
('vikas-a.katiyar@db.com','Vikas Katiyar','123'),
('ashish.patel@db.com','Ashish Patel','123');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `travelactivity`;
/* table activities*/
CREATE TABLE `travel_activity` (
                        `id` int auto_increment primary key ,
                        `user_email` text not null ,
                        `medium` text not null ,  # bike, cycling ,other etc.
                        `contributors` int not null, # 1 for 100 % etc - ghg_footprint/contribution
                        `ghg_footprint` int not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `travel_activity` WRITE;
/*!40000 ALTER TABLE `travel_activity` DISABLE KEYS */;

INSERT INTO `travel_activity` ( `user_email`, `medium`, `contributors`, `ghg_footprint`)
VALUES
('ashish@db.com','bike','1','999'),
('ashish@db.com','bicycle','1','0');
