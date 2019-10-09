drop database if exists dbhackathon;
create database dbhackathon;
use dbhackathon;

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
                         `id` int auto_increment primary key ,
                         `email` text not null ,
                         `name` text not null ,
                         `password` text not null,
                         `timestamp` TIMESTAMP default current_timestamp
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
                        `password` text not null,
                        `timestamp` TIMESTAMP default current_timestamp
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


DROP TABLE IF EXISTS `travel_activity`;
/* table activities*/
CREATE TABLE `travel_activity` (
                        `id` int auto_increment primary key ,
                        `user_email` text not null ,
                        `medium` text not null ,  # bike, cycling ,other etc.
                        `distance` int not null ,
                        `contributors` int not null, # 1 for 100 % etc - ghg_footprint/contribution
                        `ghg_footprint` int  null,
                        `timestamp` TIMESTAMP default current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
UNLOCK TABLES;


LOCK TABLES `travel_activity` WRITE;
/*!40000 ALTER TABLE `travel_activity` DISABLE KEYS */;

INSERT INTO `travel_activity` ( `user_email`, `medium`,`distance`, `contributors`)
VALUES
('ashish@db.com','bike','10','1'),
('ashish@db.com','bicycle','2','1');

/*!40000 ALTER TABLE `travel_activity` ENABLE KEYS */;
UNLOCK TABLES;


 private int id;

    private String userEmail;
    private String suggestedPlantScore;
    private Timestamp timestamp;

DROP TABLE IF EXISTS `green_activity`;
/* table activities */
CREATE TABLE `green_activity` (
                        `id` int auto_increment primary key ,
                        `user_email` text not null ,
                        `type` text not null ,
                        `suggestedPlantScore` text null,
                        `timestamp` TIMESTAMP default current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `green_activity` WRITE;
/*!40000 ALTER TABLE `green_activity` DISABLE KEYS */;


INSERT INTO `green_activity` ( `user_email`, `type`)
VALUES
('ashish@db.com','used_public_transport'),
('ashish@db.com','used_bicycle'),
('ashish@db.com','planted_tree');

/*!40000 ALTER TABLE `green_activity` ENABLE KEYS */;
UNLOCK TABLES;




DROP TABLE IF EXISTS `electricity_consumption`;
/* table activities */
CREATE TABLE `electricity_consumption` (
                                  `id` int auto_increment primary key ,
                                  `user_email` text not null ,
                                  `appliance_type` text not null ,
                                  `duration_minutes` text not null ,
                                  `contributors` text not null ,
                                  `ghg_footprint` int null,
                                  `timestamp` TIMESTAMP default current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `electricity_consumption` WRITE;
/*!40000 ALTER TABLE `electricity_consumption` DISABLE KEYS */;


INSERT INTO `electricity_consumption` ( `user_email`, `appliance_type`,`duration_minutes`,`contributors`)
VALUES
('ashish@db.com','fridge','90','3'),
('ashish@db.com','washing_machine','45','1');
/*!40000 ALTER TABLE `electricity_consumption` ENABLE KEYS */;
UNLOCK TABLES;