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


create table USER_DOCUMENT( 
id BIGINT NOT NULL AUTO_INCREMENT, 
user_id BIGINT NOT NULL, 
folder boolean,
name VARCHAR(100) NOT NULL, 
description VARCHAR(255) , 
type VARCHAR(100) NOT NULL, 
glyphicon VARCHAR(100), 
content longblob NOT NULL, 
PRIMARY KEY (id), 
CONSTRAINT document_user FOREIGN KEY (user_id) REFERENCES APP_USER (id) ON UPDATE CASCADE ON DELETE CASCADE 
);
