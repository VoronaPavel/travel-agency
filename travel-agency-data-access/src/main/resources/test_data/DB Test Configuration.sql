SET MODE MYSQL;
SET IGNORECASE TRUE;

CREATE DATABASE IF NOT EXISTS data;

USE data;

DROP TABLE IF EXISTS data.users;

CREATE TABLE data.users
(
  id       INT PRIMARY KEY                    NOT NULL,
  email    VARCHAR(32)                        NOT NULL,
  password VARCHAR(32)                        NOT NULL
);
ALTER TABLE data.users ADD CONSTRAINT unique_id UNIQUE (id);