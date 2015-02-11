/*

     This will create database in cassandra and populate with test data.

     Usage:
           To be executed from maven plug-in or standalone from cassandra cql

     Version and compatibility:
           Written for Cassandra >= 2.1.2
           Tested on 2.0.9

     Notes
          On data types see http://www.datastax.com/documentation/cql/3.1/cql/cql_reference/cql_data_types_c.html
*/

CREATE KEYSPACE IF NOT EXISTS cao WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE cao;

CREATE TABLE IF NOT EXISTS cao.countries (
  country_id    UUID,
  fk_lang_id    INT ,
  country_title TEXT,
  PRIMARY KEY (country_id,fk_lang_id)
)
WITH comment='list of countries (pre entered) http://www.iso.org/iso/home/standards/country_codes.htm field ALPHA_3';


/*==============================================================*/
/* Table: core_languages                                        */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.core_languages
(
  lang_id    UUID,
  lang       TEXT, // COMMENT 'two length language code'
  local_name TEXT, // COMMENT 'the name of the language in local writing'
  PRIMARY KEY (lang_id)
)
WITH comment='Language codes ISO_639-1 http://en.wikipedia.org';

/*==============================================================*/
/* Table: core_locations_list                                   */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.core_locations_list
(
  location_id    UUID,
  location_title TEXT,
  fk_lang_id     INT,
  fk_country_id  INT,
  PRIMARY KEY (location_id)
) WITH comment='list of location - pre entered. Could be city or village.';

/*==============================================================*/
/* Table: core_users                                            */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.core_users
(
  user_id       UUID,
  fk_lang_id     INT, //'the language user prefer',
  f_name        TEXT, //'first name'
  l_name        TEXT,
  base_location TEXT, // 'the base location of the user'
  pwd           TEXT,
  e_mail        TEXT,  //'will used as login (as it''s unique per user)',
  street_name   TEXT,
  def_phone_num TEXT,
  zip_code      INT,
  longitude     INT,
  latitude      INT,
  PRIMARY KEY (user_id)
) WITH COMMENT='list of users of the system';

/*==============================================================*/
/* Index: users_IDX                                             */
/*==============================================================*/
CREATE INDEX IF NOT EXISTS users_IDX ON core_users
(
  base_location
);

/*==============================================================*/
/* Table: pg_events_list                                        */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.pg_events_list
(
  event_id   UUID, //'uniquie event id',
  fk_user_id INT, // 'unique id of user',
  event_date TIMESTAMP, //,
  event_text TEXT ,
  PRIMARY KEY (event_id)
) WITH COMMENT='the list of events. Note that absence of fk_user_id ind';

// todo get rid of this blob and xsd transformations
/*==============================================================*/
/* Table: pg_growing_plan_xml_schemas                           */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.pg_growing_plan_xml_schemas
(
  xsd_id     uuid, //'row identifier',
  fk_pgp_id  INT,  //'foreign key from the plant growing plan',
  xsd_schema BLOB,  //  COMMENT 'the xsd that describes the XML file'
  PRIMARY KEY (xsd_id)
) WITH COMMENT='table contains xml schemas of the plant growing plans.';

/*==============================================================*/
/* Table: pg_plant_groups                                       */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.pg_plant_groups
(
  group_id    INT, // 'identifier of the record',
  group_title TEXT ,
  fk_lang_id  INT, // 'the language it belongs to',
  PRIMARY KEY (group_id)
) WITH COMMENT='The plant groups';

/*==============================================================*/
/* Index: pg_plant_groups_IDX                                   */
/*==============================================================*/
CREATE INDEX IF NOT EXISTS pg_plant_groups_IDX ON pg_plant_groups
(
  group_title
);

/*==============================================================*/
/* Table: pg_plant_growing_plan                                 */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.pg_plant_growing_plan
(
  pgp_id      uuid, // 'the plan or record id'
  pgp_title   TEXT, // 'title of the plant'
  plan_xml    BLOB, // 'the xml file of growing plan'
  fk_lang_id  INT,  // 'the language code'
  fk_user_id  INT,  // 'user id which to this record belongs'
  fk_plant_id INT,  // 'the plant id which the plan does belong'
  user_id     INT,  // 'unique id of user'
  PRIMARY KEY (pgp_id)
) WITH COMMENT='Plant growing plans';

/*==============================================================*/
/* Table: pg_plants                                             */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.pg_plants
(
  plant_id    uuid,
  plant_tilte TEXT,
  fk_lang_id  INT,
  fk_group_id INT, // 'identifier of the record',
  comments    TEXT,
  PRIMARY KEY (plant_id)
) WITH COMMENT='the plants';
/*==============================================================*/
/* Index: pg_plants_IDX                                         */
/*==============================================================*/
CREATE INDEX IF NOT EXISTS pg_plants_IDX ON cao.pg_plants
(
  plant_tilte
);

/*==============================================================*/
/* Table: pg_wiki_refs                                          */
/*==============================================================*/
CREATE TABLE IF NOT EXISTS cao.pg_wiki_refs
(
  ref_id      uuid,
  fk_plant_id INT,
  URL         TEXT ,
  PRIMARY KEY (ref_id)
) WITH COMMENT='references to wiki pages';

/*
// ===================  Demo data goes below  ===================
BEGIN BATCH
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
use cao;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO core_languages (lang_id,lang,local_name) VALUES (uuid(),'en','English');
INSERT INTO core_languages (lang_id,lang,local_name) VALUES (uuid(),'fi','Suomi');
INSERT INTO core_languages (lang_id,lang,local_name) VALUES (uuid(),'ru','Русский');
INSERT INTO core_languages (lang_id,lang,local_name) VALUES (uuid(),'tj','Тоҷики');
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO core_users('fk_lang_id','f_name','l_name','base_location','pwd','e_mail','street_name','def_phone_num','zip_code','longitude','latitude')
       VALUES (1,'Test','User','','test','zafar@linux.com','Yuliopistokatu','+13470000000',80201,0,0);
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (1,'Finland');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (1,'Tajikistan');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (1,'United States of America');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (2,'Amerikan yhdysvaltojen');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (2,'Tadzikistan');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (2,'Suomi');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (3,'Россия');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (3,'Таджикистан');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (4,'Тоҷикистон');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (4,'Руссия');
INSERT INTO core_countries('fk_lang_id','country_title') VALUES (4,'Финлонд');
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO core_locations_list('location_id','location_title','fk_lang_id','fk_country_id') VALUES (1,'Denver',1,1);
INSERT INTO core_locations_list('location_id','location_title','fk_lang_id','fk_country_id') VALUES (2,'Dushanbe',1,3);
INSERT INTO core_locations_list('location_id','location_title','fk_lang_id','fk_country_id') VALUES (3,'Dushanbe',2,1);
INSERT INTO core_locations_list('location_id','location_title','fk_lang_id','fk_country_id') VALUES (4,'Joensuu',2,2);
INSERT INTO core_locations_list('location_id','location_title','fk_lang_id','fk_country_id') VALUES (5,'Helsingissa',2,1);
INSERT INTO core_locations_list('location_id','location_title','fk_lang_id','fk_country_id') VALUES (6,'Душанбе',3,3);
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO pg_plant_groups('group_id','group_title','fk_lang_id') VALUES (4,'Пахта',4);
INSERT INTO pg_plant_groups('group_id','group_title','fk_lang_id') VALUES (3,'Хлопок',3);
INSERT INTO pg_plant_groups('group_id','group_title','fk_lang_id') VALUES (0,'Cotton',1);
INSERT INTO pg_plant_groups('group_id','group_title','fk_lang_id') VALUES (1,'Puuvilla',2);
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO pg_plants('plant_id','plant_tilte','fk_lang_id','fk_group_id','comments') VALUES (1,'Египедский хлопок Сорт1',3,3,'Египедский хлопок 1-й селекции');
INSERT INTO pg_plants('plant_id','plant_tilte','fk_lang_id','fk_group_id','comments') VALUES (2,'Egypt cotton',1,0,'Egypt cotton (first selection)');
INSERT INTO pg_plants('plant_id','plant_tilte','fk_lang_id','fk_group_id','comments') VALUES (3,'Egypti puuvilla',2,1,'Egypti puuvilla');
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO pg_wiki_refs('ref_id','fk_plant_id','URL') VALUES (4,1,'http://ru.wikipedia.org/wiki/%D0%A5%D0%BB%D0%BE%D0%BF%D1%87%D0%B0%D1%82%D0%BD%D0%B8%D0%BA');
INSERT INTO pg_wiki_refs('ref_id','fk_plant_id','URL') VALUES (5,2,'http://fi.wikipedia.org/wiki/Puuvillat');
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO pg_events_list('fk_user_id','event_TEXT') VALUES (1,'test event from database  ');
INSERT INTO pg_events_list('fk_user_id','event_TEXT') VALUES (1,'test event from database 2');
INSERT INTO pg_events_list('fk_user_id','event_TEXT') VALUES (1,'test event from database 3');
INSERT INTO pg_events_list('fk_user_id','event_TEXT') VALUES (1,'test event from database 4');
INSERT INTO pg_events_list('fk_user_id','event_TEXT') VALUES (1,'test event from database 5');
INSERT INTO pg_events_list('fk_user_id','event_TEXT') VALUES (1,'test event from database 6');
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO pg_plant_growing_plan('pgp_id','pgp_title','plan_xml','fk_lang_id','fk_user_id','fk_plant_id','user_id') VALUES (0,'test plant growing plan',null,1,1,2,1);
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
APPLY BATCH;
*/