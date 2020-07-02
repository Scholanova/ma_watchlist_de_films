--liquibase formatted sql

--changeset scholanova:3
CREATE TABLE IF NOT EXISTS MOVIES (
  MOVIE_ID                 SERIAL PRIMARY KEY,
  ADD_DATE				  DATE NOT NULL,
  API_FILM_ID			  NUMERIC,
  LIST_ID				  NUMERIC NOT NULL
);
