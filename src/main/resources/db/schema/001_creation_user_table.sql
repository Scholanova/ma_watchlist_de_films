--liquibase formatted sql

--changeset scholanova:1
CREATE TABLE IF NOT EXISTS USERS (
  USER_ID                 SERIAL PRIMARY KEY,
  USERNAME				  VARCHAR(100) UNIQUE NOT NULL,
  PASSWORD				  VARCHAR(100) NOT NULL,
  FIRSTNAME				  VARCHAR(100) NOT NULL,
  LASTNAME				  VARCHAR(100) NOT NULL,
  ACCOUNT_NON_EXPIRED BOOLEAN NOT NULL,
  ACCOUNT_NON_LOCKED BOOLEAN NOT NULL,
  CREDENTIALS_NON_EXPIRED BOOLEAN NOT NULL,
  ENABLED BOOLEAN NOT NULL
);
