--liquibase formatted sql

--changeset scholanova:2
CREATE TABLE IF NOT EXISTS LISTS (
  LIST_ID                 SERIAL PRIMARY KEY,
  TITLE				      VARCHAR(100) NOT NULL
);