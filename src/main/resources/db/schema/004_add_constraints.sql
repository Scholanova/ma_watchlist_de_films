<<<<<<< mwdf-Issue-2A
<<<<<<< mwdf-Issue-2A
<<<<<<< mwdf-Issue-2A
<<<<<<< mwdf-Issue-2A
--liquibase formatted sql

=======
>>>>>>> ajout des contraintes migrations
--changeset scholanova:5
=======
--liquibase formatted sql
>>>>>>> Ajout des migrations tables lists movies et contraintes fk

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
<<<<<<< mwdf-Issue-2A
<<<<<<< mwdf-Issue-2A
RENAME COLUMN LIST_ID TO MOVIELIST_ID;
=======
--changeset scholanova:5
=======
--liquibase formatted sql
>>>>>>> Ajout des migrations tables lists movies et contraintes fk

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
>>>>>>> ajout des contraintes migrations
=======
RENAME COLUMN LIST_ID TO MOVIELIST_ID;
>>>>>>> Modification migrations
=======
ADD CONSTRAINT constraint_movie FOREIGN KEY (LIST_ID) REFERENCES LISTS (LIST_ID);
>>>>>>> ajout des contraintes migrations
