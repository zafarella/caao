drop database caorganizer;

create database caorganizer;

use caorganizer;


/*==============================================================*/
/* Table: core_countries                                        */
/*==============================================================*/
create table core_countries
(
   country_id           int unsigned not null auto_increment,
   fk_lang_id           int not null,
   country_title        varchar(40) not null,
   primary key (country_id)
);

alter table core_countries comment 'list of countries (pre entered)
http://www.iso.org/iso';

/*==============================================================*/
/* Index: countries_idx                                         */
/*==============================================================*/
create unique index countries_idx on core_countries
(
   fk_lang_id,
   country_title,
   country_id
);

/*==============================================================*/
/* Table: core_languages                                        */
/*==============================================================*/
create table core_languages
(
   lang_id              int not null auto_increment comment 'unique identifier of the language',
   lang                 char(3) not null comment 'two length language code',
   local_name           varchar(30) character set utf8 comment 'the name of the language in local writing',
   primary key (lang_id)
);

alter table core_languages comment 'Language codes
ISO_639-1
http://en.wikipedia.org';

/*==============================================================*/
/* Table: core_locations_list                                   */
/*==============================================================*/
create table core_locations_list
(
   location_id          int unsigned not null auto_increment,
   location_title       varchar(20) not null,
   fk_lang_id           int,
   fk_country_id        int unsigned not null,
   primary key (location_id)
);

alter table core_locations_list comment 'list of location (pre entered). Could be city or village.';

/*==============================================================*/
/* Index: Index_2                                               */
/*==============================================================*/
create index Index_2 on core_locations_list
(
   fk_country_id
);

/*==============================================================*/
/* Table: core_users                                            */
/*==============================================================*/
create table core_users
(
   user_id              smallint not null auto_increment comment 'unique id of user',
   fk_lang_id           int comment 'the language user prefer',
   f_name               varchar(10) not null comment 'first name',
   l_name               varchar(10) comment 'last name',
   base_location        varchar(15) comment 'the base location of the user',
   pwd                  varchar(20),
   e_mail               varchar(20) not null comment 'will used as login (as it''s unique per user)',
   street_name          varchar(25),
   def_phone_num        varchar(10),
   zip_code             numeric,
   longitude            numeric,
   latitude             numeric,
   primary key (user_id)
);

alter table core_users comment 'list of users of the system';

/*==============================================================*/
/* Index: users_IDX                                             */
/*==============================================================*/
create index users_IDX on core_users
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
create table pg_events_list
(
   event_id             int not null auto_increment comment 'uniquie event id',
   fk_user_id           smallint comment 'unique id of user',
   event_date           timestamp default CURRENT_TIMESTAMP,
   event_text           varchar(100) not null,
   primary key (event_id)
);

alter table pg_events_list comment 'the list of events
Note that absence of fk_user_id ind';

/*==============================================================*/
/* Index: events_idx                                            */
/*==============================================================*/
create unique index events_idx on pg_events_list
(
   event_id,
   fk_user_id,
   event_date
);

/*==============================================================*/
/* Table: pg_growing_plan_xml_schemas                           */
/*==============================================================*/
create table pg_growing_plan_xml_schemas
(
   xsd_id               int not null auto_increment comment 'row identifier',
   fk_pgp_id            int comment 'foreign key from the plant growing plan',
   xsd_schema           blob not null comment 'the xsd that describes the XML file',
   primary key (xsd_id)
);

alter table pg_growing_plan_xml_schemas comment 'table contains xml schemas of the plant growing plans.';

/*==============================================================*/
/* Index: pg_growing_plan_xml_schemas_IDX                       */
/*==============================================================*/
create index pg_growing_plan_xml_schemas_IDX on pg_growing_plan_xml_schemas
(
   xsd_id,
   fk_pgp_id
);

/*==============================================================*/
/* Table: pg_plant_groups                                       */
/*==============================================================*/
create table pg_plant_groups
(
   group_id             int not null comment 'identifier of the record',
   group_title          varchar(40) not null,
   fk_lang_id           int comment 'the language it belongs to',
   primary key (group_id)
);

alter table pg_plant_groups comment 'The plant groups';

/*==============================================================*/
/* Index: pg_plant_groups_IDX                                   */
/*==============================================================*/
create index pg_plant_groups_IDX on pg_plant_groups
(
   group_title,
   fk_lang_id
);

/*==============================================================*/
/* Table: pg_plant_growing_plan                                 */
/*==============================================================*/
create table pg_plant_growing_plan
(
   pgp_id               int not null comment 'the plan or record id',
   pgp_title            varchar(30) comment 'title of the plant',
   plan_xml             BLOB comment 'the xml file of growing plan',
   fk_lang_id           int comment 'the language code',
   fk_user_id           smallint comment 'user id which to this record belongs',
   fk_plant_id          int comment 'the plant id which the plan does belong',
   user_id              smallint comment 'unique id of user',
   primary key (pgp_id)
);

alter table pg_plant_growing_plan comment 'Plant growing plans';

/*==============================================================*/
/* Table: pg_plants                                             */
/*==============================================================*/
create table pg_plants
(
   plant_id             int not null auto_increment,
   plant_tilte          varchar(40) not null,
   fk_lang_id           int,
   fk_group_id          int comment 'identifier of the record',
   comments             varchar(500),
   primary key (plant_id)
);

alter table pg_plants comment 'the plants';

/*==============================================================*/
/* Index: pg_plants_IDX                                         */
/*==============================================================*/
create index pg_plants_IDX on pg_plants
(
   plant_tilte,
   fk_lang_id,
   fk_group_id
);

/*==============================================================*/
/* Table: pg_wiki_refs                                          */
/*==============================================================*/
create table pg_wiki_refs
(
   ref_id               int not null auto_increment,
   fk_plant_id          int,
   URL                  varchar(300) not null,
   primary key (ref_id)
);

alter table pg_wiki_refs comment 'references to wiki pages';

/*==============================================================*/
/* Index: pg_wiki_refs_IDX                                      */
/*==============================================================*/
create index pg_wiki_refs_IDX on pg_wiki_refs
(
   fk_plant_id,
   URL
);

alter table core_countries add constraint FK_Relationship_1 foreign key (fk_lang_id)
      references core_languages (lang_id) on delete restrict on update restrict;

alter table core_locations_list add constraint FK_Reference_15 foreign key (fk_country_id)
      references core_countries (country_id);

alter table core_locations_list add constraint FK_Relationship_14 foreign key (fk_lang_id)
      references core_languages (lang_id) on delete restrict on update restrict;

alter table core_users add constraint FK_Relationship_10 foreign key (fk_lang_id)
      references core_languages (lang_id) on delete restrict on update restrict;

alter table pg_events_list add constraint FK_Reference_16 foreign key (fk_user_id)
      references core_users (user_id) on delete restrict on update restrict;

alter table pg_growing_plan_xml_schemas add constraint FK_Reference_12 foreign key (fk_pgp_id)
      references pg_plant_growing_plan (pgp_id) on delete restrict on update restrict;

alter table pg_plant_groups add constraint FK_Relationship_12 foreign key (fk_lang_id)
      references core_languages (lang_id) on delete restrict on update restrict;

alter table pg_plant_growing_plan add constraint FK_Reference_14 foreign key (fk_plant_id)
      references pg_plants (plant_id) on delete restrict on update restrict;

alter table pg_plant_growing_plan add constraint FK_Reference_17 foreign key (user_id)
      references core_users (user_id) on delete restrict on update restrict;

alter table pg_plant_growing_plan add constraint FK_Reference_9 foreign key (fk_user_id)
      references core_users (user_id) on delete restrict on update restrict;

alter table pg_plant_growing_plan add constraint FK_Relationship_13 foreign key (fk_lang_id)
      references core_languages (lang_id) on delete restrict on update restrict;

alter table pg_plants add constraint FK_Reference_13 foreign key (fk_group_id)
      references pg_plant_groups (group_id) on delete restrict on update restrict;

alter table pg_plants add constraint FK_Relationship_11 foreign key (fk_lang_id)
      references core_languages (lang_id) on delete restrict on update restrict;

alter table pg_wiki_refs add constraint FK_Reference_11 foreign key (fk_plant_id)
      references pg_plants (plant_id) on delete restrict on update restrict;


use caorganizer;
delimiter |
create trigger tbu_users before update
on core_users for each row
begin
/*
Powered by z1
*/
   if new.pwd <> old.pwd then
   set new.pwd = new.pwd;
   end if;
end;

|

create trigger tib_users before insert
on core_users for each row
begin
 set new.pwd =  PASSWORD(new.pwd);
end;
|
