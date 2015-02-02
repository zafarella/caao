//
//
//     This will create database in cassandra and populate with test data.
//
//     Usage:
//           To be executed from maven plug-in or standalone from cassandra cql
//
//     Version and compatibility:
//           Written for Cassandra >= 2.1.2
//
//     Notes
//          On data types see http://www.datastax.com/documentation/cql/3.1/cql/cql_reference/cql_data_types_c.html
//

CREATE KEYSPACE IF NOT EXISTS cao WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE cao;

CREATE TABLE IF NOT EXISTS cao.countries (
  country_id    uuid,
  fk_lang_id    INT          NOT NULL,
  country_title VARCHAR  NOT NULL,
  PRIMARY KEY (country_id,fk_lang_id)
)
WITH comment='list of countries (pre entered) http://www.iso.org/iso';

// todo continue from here

/*==============================================================*/
/* Table: core_languages                                        */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.languages
(
  lang_id    uuid,
  lang       CHAR(3) NOT NULL
  COMMENT 'two length language code',
  local_name VARCHAR(30)
             CHARACTER SET utf8 COMMENT 'the name of the language in local writing',
  PRIMARY KEY (lang_id)
)
WITH comment='Language codes ISO_639-1 http://en.wikipedia.org';

/*==============================================================*/
/* Table: core_locations_list                                   */
/*==============================================================*/
CREATE TABLE core_locations_list
(
  location_id    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  location_title VARCHAR(20)  NOT NULL,
  fk_lang_id     INT,
  fk_country_id  INT UNSIGNED NOT NULL,
  PRIMARY KEY (location_id)
);

ALTER TABLE core_locations_list COMMENT 'list of location (pre entered). Could be city or village.';

/*==============================================================*/
/* Index: Index_2                                               */
/*==============================================================*/
CREATE INDEX Index_2 ON core_locations_list
(
  fk_country_id
);

/*==============================================================*/
/* Table: core_users                                            */
/*==============================================================*/
CREATE TABLE core_users
(
  user_id       SMALLINT    NOT NULL AUTO_INCREMENT COMMENT 'unique id of user',
  fk_lang_id    INT COMMENT 'the language user prefer',
  f_name        VARCHAR(10) NOT NULL
  COMMENT 'first name',
  l_name        VARCHAR(10) COMMENT 'last name',
  base_location VARCHAR(15) COMMENT 'the base location of the user',
  pwd           VARCHAR(20),
  e_mail        VARCHAR(20) NOT NULL
  COMMENT 'will used as login (as it''s unique per user)',
  street_name   VARCHAR(25),
  def_phone_num VARCHAR(10),
  zip_code      NUMERIC,
  longitude     NUMERIC,
  latitude      NUMERIC,
  PRIMARY KEY (user_id)
);

ALTER TABLE core_users COMMENT 'list of users of the system';

/*==============================================================*/
/* Index: users_IDX                                             */
/*==============================================================*/
CREATE INDEX users_IDX ON core_users
(
  base_location,
  e_mail,
  street_name,
  zip_code,
  longitude,
  latitude
);

/*==============================================================*/
/* Table: pg_events_list                                        */
/*==============================================================*/
CREATE TABLE pg_events_list
(
  event_id   INT          NOT NULL AUTO_INCREMENT COMMENT 'uniquie event id',
  fk_user_id SMALLINT COMMENT 'unique id of user',
  event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  event_text VARCHAR(100) NOT NULL,
  PRIMARY KEY (event_id)
);

ALTER TABLE pg_events_list COMMENT 'the list of events
Note that absence of fk_user_id ind';

/*==============================================================*/
/* Index: events_idx                                            */
/*==============================================================*/
CREATE UNIQUE INDEX events_idx ON pg_events_list
(
  event_id,
  fk_user_id,
  event_date
);

/*==============================================================*/
/* Table: pg_growing_plan_xml_schemas                           */
/*==============================================================*/
CREATE TABLE pg_growing_plan_xml_schemas
(
  xsd_id     INT  NOT NULL AUTO_INCREMENT COMMENT 'row identifier',
  fk_pgp_id  INT COMMENT 'foreign key from the plant growing plan',
  xsd_schema BLOB NOT NULL
  COMMENT 'the xsd that describes the XML file',
  PRIMARY KEY (xsd_id)
);

ALTER TABLE pg_growing_plan_xml_schemas COMMENT 'table contains xml schemas of the plant growing plans.';

/*==============================================================*/
/* Index: pg_growing_plan_xml_schemas_IDX                       */
/*==============================================================*/
CREATE INDEX pg_growing_plan_xml_schemas_IDX ON pg_growing_plan_xml_schemas
(
  xsd_id,
  fk_pgp_id
);

/*==============================================================*/
/* Table: pg_plant_groups                                       */
/*==============================================================*/
CREATE TABLE pg_plant_groups
(
  group_id    INT         NOT NULL
  COMMENT 'identifier of the record',
  group_title VARCHAR(40) NOT NULL,
  fk_lang_id  INT COMMENT 'the language it belongs to',
  PRIMARY KEY (group_id)
);

ALTER TABLE pg_plant_groups COMMENT 'The plant groups';

/*==============================================================*/
/* Index: pg_plant_groups_IDX                                   */
/*==============================================================*/
CREATE INDEX pg_plant_groups_IDX ON pg_plant_groups
(
  group_title,
  fk_lang_id
);

/*==============================================================*/
/* Table: pg_plant_growing_plan                                 */
/*==============================================================*/
CREATE TABLE pg_plant_growing_plan
(
  pgp_id      INT NOT NULL
  COMMENT 'the plan or record id',
  pgp_title   VARCHAR(30) COMMENT 'title of the plant',
  plan_xml    BLOB COMMENT 'the xml file of growing plan',
  fk_lang_id  INT COMMENT 'the language code',
  fk_user_id  SMALLINT COMMENT 'user id which to this record belongs',
  fk_plant_id INT COMMENT 'the plant id which the plan does belong',
  user_id     SMALLINT COMMENT 'unique id of user',
  PRIMARY KEY (pgp_id)
);

ALTER TABLE pg_plant_growing_plan COMMENT 'Plant
  plant_tilte VARCHAR(40) NOT NULL,
  fk_lang_id  INT,
  fk_group_id INT COMMENT 'identifier of the record',
  comments    VARCHAR(500),
  PRIMARY KEY (plant_id)
);

/*==============================================================*/
/* Table: pg_plants                                             */
/*==============================================================*/
CREATE TABLE pg_plants
(
  plant_id    INT         NOT NULL AUTO_INCREMENT,
ALTER TABLE pg_plants COMMENT 'the plants';

/*==============================================================*/
/* Index: pg_plants_IDX                                         */
/*==============================================================*/
CREATE INDEX pg_plants_IDX ON pg_plants
(
  plant_tilte,
  fk_lang_id,
  fk_group_id
);

/*==============================================================*/
/* Table: pg_wiki_refs                                          */
/*==============================================================*/
CREATE TABLE pg_wiki_refs
(
  ref_id      INT          NOT NULL AUTO_INCREMENT,
  fk_plant_id INT,
  URL         VARCHAR(300) NOT NULL,
  PRIMARY KEY (ref_id)
);

ALTER TABLE pg_wiki_refs COMMENT 'references to wiki pages';

/*==============================================================*/
/* Index: pg_wiki_refs_IDX                                      */
/*==============================================================*/
CREATE INDEX pg_wiki_refs_IDX ON pg_wiki_refs
(
  fk_plant_id,
  URL
);

ALTER TABLE core_countries ADD CONSTRAINT FK_Relationship_1 FOREIGN KEY (fk_lang_id)
REFERENCES core_languages (lang_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE core_locations_list ADD CONSTRAINT FK_Reference_15 FOREIGN KEY (fk_country_id)
REFERENCES core_countries (country_id);

ALTER TABLE core_locations_list ADD CONSTRAINT FK_Relationship_14 FOREIGN KEY (fk_lang_id)
REFERENCES core_languages (lang_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE core_users ADD CONSTRAINT FK_Relationship_10 FOREIGN KEY (fk_lang_id)
REFERENCES core_languages (lang_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_events_list ADD CONSTRAINT FK_Reference_16 FOREIGN KEY (fk_user_id)
REFERENCES core_users (user_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_growing_plan_xml_schemas ADD CONSTRAINT FK_Reference_12 FOREIGN KEY (fk_pgp_id)
REFERENCES pg_plant_growing_plan (pgp_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plant_groups ADD CONSTRAINT FK_Relationship_12 FOREIGN KEY (fk_lang_id)
REFERENCES core_languages (lang_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plant_growing_plan ADD CONSTRAINT FK_Reference_14 FOREIGN KEY (fk_plant_id)
REFERENCES pg_plants (plant_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plant_growing_plan ADD CONSTRAINT FK_Reference_17 FOREIGN KEY (user_id)
REFERENCES core_users (user_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plant_growing_plan ADD CONSTRAINT FK_Reference_9 FOREIGN KEY (fk_user_id)
REFERENCES core_users (user_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plant_growing_plan ADD CONSTRAINT FK_Relationship_13 FOREIGN KEY (fk_lang_id)
REFERENCES core_languages (lang_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plants ADD CONSTRAINT FK_Reference_13 FOREIGN KEY (fk_group_id)
REFERENCES pg_plant_groups (group_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_plants ADD CONSTRAINT FK_Relationship_11 FOREIGN KEY (fk_lang_id)
REFERENCES core_languages (lang_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE pg_wiki_refs ADD CONSTRAINT FK_Reference_11 FOREIGN KEY (fk_plant_id)
REFERENCES pg_plants (plant_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

// Demo data goes below
BEGIN BATCH
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
use cao;
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'core_languages'('lang_id','lang','local_name') VALUES (1,'en','English');
INSERT INTO 'caorganizer'.'core_languages'('lang_id','lang','local_name') VALUES (2,'fi','Suomi');
INSERT INTO 'caorganizer'.'core_languages'('lang_id','lang','local_name') VALUES (3,'ru','Русский');
INSERT INTO 'caorganizer'.'core_languages'('lang_id','lang','local_name') VALUES (4,'tj','Тоҷики');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'core_users'('fk_lang_id','f_name','l_name','base_location','pwd','e_mail','street_name','def_phone_num','zip_code','longitude','latitude')
            VALUES (1,'Test','User','','test','zkhayda@uef.fi','Yuliopistokatu','+420777200689',80201,0,0);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (1,'Finland');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (1,'Tajikistan');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (1,'United States of America');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (2,'Amerikan yhdysvaltojen');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (2,'Tadzikistan');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (2,'Suomi');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (3,'Россия');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (3,'Таджикистан');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (4,'Тоҷикистон');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (4,'Руссия');
INSERT INTO 'caorganizer'.'core_countries'('fk_lang_id','country_title') VALUES (4,'Финлонд');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'core_locations_list'('location_id','location_title','fk_lang_id','fk_country_id') VALUES (1,'Denver',1,1);
INSERT INTO 'caorganizer'.'core_locations_list'('location_id','location_title','fk_lang_id','fk_country_id') VALUES (2,'Dushanbe',1,3);
INSERT INTO 'caorganizer'.'core_locations_list'('location_id','location_title','fk_lang_id','fk_country_id') VALUES (3,'Dushanbe',2,1);
INSERT INTO 'caorganizer'.'core_locations_list'('location_id','location_title','fk_lang_id','fk_country_id') VALUES (4,'Joensuu',2,2);
INSERT INTO 'caorganizer'.'core_locations_list'('location_id','location_title','fk_lang_id','fk_country_id') VALUES (5,'Helsingissa',2,1);
INSERT INTO 'caorganizer'.'core_locations_list'('location_id','location_title','fk_lang_id','fk_country_id') VALUES (6,'Душанбе',3,3);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'pg_plant_groups'('group_id','group_title','fk_lang_id') VALUES (4,'Пахта',4);
INSERT INTO 'caorganizer'.'pg_plant_groups'('group_id','group_title','fk_lang_id') VALUES (3,'Хлопок',3);
INSERT INTO 'caorganizer'.'pg_plant_groups'('group_id','group_title','fk_lang_id') VALUES (0,'Cotton',1);
INSERT INTO 'caorganizer'.'pg_plant_groups'('group_id','group_title','fk_lang_id') VALUES (1,'Puuvilla',2);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'pg_plants'('plant_id','plant_tilte','fk_lang_id','fk_group_id','comments') VALUES (1,'Египедский хлопок Сорт1',3,3,'Египедский хлопок 1-й селекции');
INSERT INTO 'caorganizer'.'pg_plants'('plant_id','plant_tilte','fk_lang_id','fk_group_id','comments') VALUES (2,'Egypt cotton',1,0,'Egypt cotton (first selection)');
INSERT INTO 'caorganizer'.'pg_plants'('plant_id','plant_tilte','fk_lang_id','fk_group_id','comments') VALUES (3,'Egypti puuvilla',2,1,'Egypti puuvilla');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'pg_wiki_refs'('ref_id','fk_plant_id','URL') VALUES (4,1,'http://ru.wikipedia.org/wiki/%D0%A5%D0%BB%D0%BE%D0%BF%D1%87%D0%B0%D1%82%D0%BD%D0%B8%D0%BA');
INSERT INTO 'caorganizer'.'pg_wiki_refs'('ref_id','fk_plant_id','URL') VALUES (5,2,'http://fi.wikipedia.org/wiki/Puuvillat');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'pg_events_list'('fk_user_id','event_text') VALUES (1,'test event from database  ');
INSERT INTO 'caorganizer'.'pg_events_list'('fk_user_id','event_text') VALUES (1,'test event from database 2');
INSERT INTO 'caorganizer'.'pg_events_list'('fk_user_id','event_text') VALUES (1,'test event from database 3');
INSERT INTO 'caorganizer'.'pg_events_list'('fk_user_id','event_text') VALUES (1,'test event from database 4');
INSERT INTO 'caorganizer'.'pg_events_list'('fk_user_id','event_text') VALUES (1,'test event from database 5');
INSERT INTO 'caorganizer'.'pg_events_list'('fk_user_id','event_text') VALUES (1,'test event from database 6');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO 'caorganizer'.'pg_plant_growing_plan'('pgp_id','pgp_title','plan_xml','fk_lang_id','fk_user_id','fk_plant_id','user_id') VALUES (0,'test plant growing plan',null,1,1,2,1);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
APPLY BATCH;