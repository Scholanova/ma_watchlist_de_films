--liquibase formatted sql

--changeset scholanova:5
ALTER TABLE USERS ADD COLUMN LIST_ID INTEGER;
ALTER TABLE USERS
ADD CONSTRAINT constraint_user FOREIGN KEY (LIST_ID) REFERENCES LISTS (LIST_ID);

ALTER TABLE LISTS ADD COLUMN MOVIE_ID INTEGER;
ALTER TABLE LISTS
ADD CONSTRAINT constraint_list FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES (MOVIE_ID);

ALTER TABLE LISTS ADD COLUMN USER_ID INTEGER;
ALTER TABLE LISTS
ADD CONSTRAINT constraint_list2 FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);

ALTER TABLE MOVIES ADD COLUMN LIST_ID INTEGER;
ALTER TABLE MOVIES
ADD CONSTRAINT constraint_movie FOREIGN KEY (LIST_ID) REFERENCES LISTS (LIST_ID);

--changeset scholanova:6
ALTER TABLE USERS
DROP CONSTRAINT constraint_user;

ALTER TABLE LISTS
DROP CONSTRAINT constraint_list;

ALTER TABLE LISTS
DROP CONSTRAINT constraint_list2;

ALTER TABLE MOVIES
DROP CONSTRAINT constraint_movie;


ALTER TABLE USERS
RENAME COLUMN LIST_ID TO USERLIST_ID;

ALTER TABLE LISTS
RENAME COLUMN MOVIE_ID TO MOVIELIST_ID;

ALTER TABLE LISTS
RENAME COLUMN USER_ID TO USERLIST_ID;

ALTER TABLE MOVIES
RENAME COLUMN LIST_ID TO MOVIELIST_ID;