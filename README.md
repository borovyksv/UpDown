# UpDown
mySQL setup:

create database relzetdb;
use relzetdb;

create table APP_USER ( 
id BIGINT NOT NULL AUTO_INCREMENT, 
sso_id VARCHAR(30) NOT NULL, 
login VARCHAR(30) NOT NULL,
pass VARCHAR(244) NOT NULL,
first_name VARCHAR(30) NOT NULL, 
last_name VARCHAR(30) NOT NULL, 
email VARCHAR(30) NOT NULL, 
PRIMARY KEY (id), 
UNIQUE (sso_id),
UNIQUE (login)
); 


CREATE TABLE `user_document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `folder` tinyint(1) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(100) NOT NULL,
  `glyphicon` varchar(100) DEFAULT NULL,
  `content` longblob NOT NULL,
  `files_counter` int(11) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `document_user` (`user_id`),
  CONSTRAINT `document_user` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8
