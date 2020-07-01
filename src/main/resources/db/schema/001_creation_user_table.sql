--liquibase formatted sql

--changeset scholanova:1
CREATE TABLE IF NOT EXISTS USERS (
  ID                  NUMERIC         NOT NULL,
  NAME				  VARCHAR(100),
  EMAIL				  VARCHAR(100),
  PRIMARY KEY (ID)
);