-- Run once in MySQL (e.g. mysql -u root -p < setup_mysql.sql)
CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

CREATE TABLE IF NOT EXISTS userdata (
  id INT PRIMARY KEY AUTO_INCREMENT,
  uname VARCHAR(64) NOT NULL
);

-- Seed a single high-score row (column uname stores the score as in the original app)
INSERT INTO userdata (uname)
SELECT '0' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM userdata LIMIT 1);
