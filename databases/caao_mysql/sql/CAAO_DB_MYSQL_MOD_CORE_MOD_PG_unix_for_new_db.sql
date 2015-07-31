DROP DATABASE IF EXISTS caao;

CREATE DATABASE caao;

USE caao;


/*==============================================================*/
/* Table: core_countries                                        */
/*==============================================================*/
DROP TABLE IF EXISTS core_countries;
CREATE TABLE core_countries
(
  country_id    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  fk_lang_id    INT          NOT NULL,
  country_title VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (country_id)
);

ALTER TABLE core_countries COMMENT 'list of countries (pre entered)
http://www.iso.org/iso';

/*==============================================================*/
/* Index: countries_idx                                         */
/*==============================================================*/
CREATE UNIQUE INDEX countries_idx ON core_countries
(
  fk_lang_id,
  country_title,
  country_id
);

/*==============================================================*/
/* Table: core_languages                                        */
/*==============================================================*/
DROP TABLE IF EXISTS core_languages;
CREATE TABLE core_languages
(
  lang_id    INT     NOT NULL AUTO_INCREMENT COMMENT 'unique identifier of the language',
  lang       CHAR(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
  COMMENT 'two length language code',
  local_name VARCHAR(30)
             CHARACTER SET utf8 COMMENT 'the name of the language in local writing',
  PRIMARY KEY (lang_id)
);

ALTER TABLE core_languages COMMENT 'Language codes ISO_639-1 http://en.wikipedia.org';

/*==============================================================*/
/* Table: core_locations_list                                   */
/*==============================================================*/
DROP TABLE IF EXISTS core_locations_list;
CREATE TABLE core_locations_list
(
  location_id    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  location_title VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
DROP TABLE IF EXISTS core_users;
CREATE TABLE core_users
(
  user_id       SMALLINT    NOT NULL AUTO_INCREMENT COMMENT 'unique id of user',
  fk_lang_id    INT COMMENT 'the language user prefer',
  f_name        VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
  COMMENT 'first name',
  l_name        VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'last name',
  base_location VARCHAR(15)CHARACTER SET utf8 COLLATE utf8_unicode_ci  COMMENT 'the base location of the user',
  pwd           VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  e_mail        VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
  COMMENT 'will used as login (as it''s unique per user)',
  street_name   VARCHAR(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  def_phone_num VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
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
DROP TABLE IF EXISTS pg_events_list;
CREATE TABLE pg_events_list
(
  event_id   INT          NOT NULL AUTO_INCREMENT COMMENT 'uniquie event id',
  fk_user_id SMALLINT COMMENT 'unique id of user',
  event_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  event_text VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
DROP TABLE IF EXISTS pg_growing_plan_xml_schemas;
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
DROP TABLE IF EXISTS pg_plant_groups;
CREATE TABLE pg_plant_groups
(
  group_id    INT         NOT NULL COMMENT 'identifier of the record',
  group_title VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
DROP TABLE IF EXISTS pg_plant_growing_plan;
CREATE TABLE pg_plant_growing_plan
(
  pgp_id      INT NOT NULL
  COMMENT 'the plan or record id',
  pgp_title   VARCHAR(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'title of the plant',
  plan_xml    BLOB COMMENT 'the xml file of growing plan',
  fk_lang_id  INT COMMENT 'the language code',
  fk_user_id  SMALLINT COMMENT 'user id which to this record belongs',
  fk_plant_id INT COMMENT 'the plant id which the plan does belong',
  user_id     SMALLINT COMMENT 'unique id of user',
  PRIMARY KEY (pgp_id)
);

ALTER TABLE pg_plant_growing_plan COMMENT 'Plant growing plans';

/*==============================================================*/
/* Table: pg_plants                                             */
/*==============================================================*/
DROP TABLE IF EXISTS pg_plants;
CREATE TABLE pg_plants
(
  plant_id    INT         NOT NULL AUTO_INCREMENT,
  plant_tilte VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  fk_lang_id  INT,
  fk_group_id INT COMMENT 'identifier of the record',
  comments    VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  PRIMARY KEY (plant_id)
);

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
DROP TABLE IF EXISTS pg_wiki_refs;
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


USE caao;
DELIMITER |
CREATE TRIGGER tbu_users BEFORE UPDATE
ON core_users FOR EACH ROW
  BEGIN
/*
Powered by z1
*/
    IF new.pwd <> old.pwd
    THEN
      SET new.pwd = new.pwd;
    END IF;
  END;

|

CREATE TRIGGER tib_users BEFORE INSERT
ON core_users FOR EACH ROW
  BEGIN
    SET new.pwd = PASSWORD(new.pwd);
  END;
|
