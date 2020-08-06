/**** Previous DB *****/
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  age INT,
  country VARCHAR(100),
  emailId VARCHAR(100),
  UNIQUE (emailId),
  PRIMARY KEY (id));

mysqld => start mysql server [always run on port:33060]
mysql -uroot => start mysql client


/******* New User DB *********/
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  age INT,
  country VARCHAR(100),
  email VARCHAR(100) NOT NULL,
  UNIQUE (email),
  PRIMARY KEY (id));


/******* New Topics DB *********/
DROP TABLE IF EXISTS topics;
CREATE TABLE topics (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(100),
    description VARCHAR(500),
    created_on DATE,
    last_modified_on DATE,
    is_active  TINYINT(1),
    PRIMARY KEY (id));


