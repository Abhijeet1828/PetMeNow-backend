CREATE TABLE petmenowDB.user_details(
id BIGINT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
user_name VARCHAR(20),
user_password VARCHAR(50) NOT NULL,
email VARCHAR(50),
phone_number BIGINT,
address VARCHAR(1000),
image VARCHAR(100),
dob DATE,
created_timestamp TIMESTAMP,
updated_timestamp TIMESTAMP,
PRIMARY KEY(id));

CREATE TABLE petmenowDB.pet_information(
id BIGINT NOT NULL AUTO_INCREMENT,
pet_name VARCHAR(50) NOT NULL,
pet_type VARCHAR(20),
pet_breed VARCHAR(50),
age INT,
is_vaccinated BOOLEAN,
image VARCHAR(100),
owner_id BIGINT,
pet_description TEXT,
created_timestamp TIMESTAMP,
updated_timestamp TIMESTAMP,
PRIMARY KEY(id));

CREATE TABLE petmenowDB.adoption_foster_history(
id BIGINT NOT NULL AUTO_INCREMENT,
title TEXT,
history_type VARCHAR(20) NOT NULL,
start_date DATE,
duration_number INT,
duration_type VARCHAR(20),
history_status VARCHAR(20),
pet_id BIGINT,
owner_id BIGINT,
accepted_user_id BIGINT,
allowed_pets VARCHAR(100),
created_timestamp TIMESTAMP,
updated_timestamp TIMESTAMP,
PRIMARY KEY(id));

CREATE TABLE petmenowDB.chat_history(
id BIGINT NOT NULL AUTO_INCREMENT,
channel_name VARCHAR(50),
message_id VARCHAR(50),
message_type VARCHAR(20),
message_body TEXT,
message TEXT,
created_by BIGINT,
message_time TIMESTAMP,
created_timestamp TIMESTAMP,
PRIMARY KEY(id));

CREATE TABLE petmenowDB.pet_type_master(
id BIGINT NOT NULL AUTO_INCREMENT,
pet_type VARCHAR(100) NOT NULL,
created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY(id));

CREATE TABLE petmenowDB.pet_breed_master(
id BIGINT NOT NULL AUTO_INCREMENT,
pet_type_id BIGINT NOT NULL,
pet_breed VARCHAR(500) NOT NULL,
created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY(id));