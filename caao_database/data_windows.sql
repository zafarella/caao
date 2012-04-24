-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
use caorganizer;
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`core_languages`(`lang_id`,`lang`,`local_name`) VALUES (1,'en','English');
INSERT INTO `caorganizer`.`core_languages`(`lang_id`,`lang`,`local_name`) VALUES (2,'fi','Suomi');
INSERT INTO `caorganizer`.`core_languages`(`lang_id`,`lang`,`local_name`) VALUES (3,'ru','Русский');
INSERT INTO `caorganizer`.`core_languages`(`lang_id`,`lang`,`local_name`) VALUES (4,'tj','Тоҷики');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`core_users`(`fk_lang_id`,`f_name`,`l_name`,`base_location`,`pwd`,`e_mail`,`street_name`,`def_phone_num`,`zip_code`,`longitude`,`latitude`) VALUES (1,'Test','User','','test','zkhayda@uef.fi','Yuliopistokatu','+420777200689',80201,0,0);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (1,'Finland');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (1,'Tajikistan');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (1,'United States of America');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (2,'Amerikan yhdysvaltojen');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (2,'Tadzikistan');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (2,'Suomi');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (3,'Россия');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (3,'Таджикистан');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (4,'Тоҷикистон');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (4,'Руссия');
INSERT INTO `caorganizer`.`core_countries`(`fk_lang_id`,`country_title`) VALUES (4,'Финлонд');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`core_locations_list`(`location_id`,`location_title`,`fk_lang_id`,`fk_country_id`) VALUES (1,'Denver',1,1);
INSERT INTO `caorganizer`.`core_locations_list`(`location_id`,`location_title`,`fk_lang_id`,`fk_country_id`) VALUES (2,'Dushanbe',1,3);
INSERT INTO `caorganizer`.`core_locations_list`(`location_id`,`location_title`,`fk_lang_id`,`fk_country_id`) VALUES (3,'Dushanbe',2,1);
INSERT INTO `caorganizer`.`core_locations_list`(`location_id`,`location_title`,`fk_lang_id`,`fk_country_id`) VALUES (4,'Joensuu',2,2);
INSERT INTO `caorganizer`.`core_locations_list`(`location_id`,`location_title`,`fk_lang_id`,`fk_country_id`) VALUES (5,'Helsingissa',2,1);
INSERT INTO `caorganizer`.`core_locations_list`(`location_id`,`location_title`,`fk_lang_id`,`fk_country_id`) VALUES (6,'Душанбе',3,3);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`pg_plant_groups`(`group_id`,`group_title`,`fk_lang_id`) VALUES (4,'Пахта',4);
INSERT INTO `caorganizer`.`pg_plant_groups`(`group_id`,`group_title`,`fk_lang_id`) VALUES (3,'Хлопок',3);
INSERT INTO `caorganizer`.`pg_plant_groups`(`group_id`,`group_title`,`fk_lang_id`) VALUES (0,'Cotton',1);
INSERT INTO `caorganizer`.`pg_plant_groups`(`group_id`,`group_title`,`fk_lang_id`) VALUES (1,'Puuvilla',2);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`pg_plants`(`plant_id`,`plant_tilte`,`fk_lang_id`,`fk_group_id`,`comments`) VALUES (1,'Египедский хлопок Сорт1',3,3,'Египедский хлопок 1-й селекции');
INSERT INTO `caorganizer`.`pg_plants`(`plant_id`,`plant_tilte`,`fk_lang_id`,`fk_group_id`,`comments`) VALUES (2,'Egypt cotton',1,0,'Egypt cotton (first selection)');
INSERT INTO `caorganizer`.`pg_plants`(`plant_id`,`plant_tilte`,`fk_lang_id`,`fk_group_id`,`comments`) VALUES (3,'Egypti puuvilla',2,1,'Egypti puuvilla');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`pg_wiki_refs`(`ref_id`,`fk_plant_id`,`URL`) VALUES (4,1,'http://ru.wikipedia.org/wiki/%D0%A5%D0%BB%D0%BE%D0%BF%D1%87%D0%B0%D1%82%D0%BD%D0%B8%D0%BA');
INSERT INTO `caorganizer`.`pg_wiki_refs`(`ref_id`,`fk_plant_id`,`URL`) VALUES (5,2,'http://fi.wikipedia.org/wiki/Puuvillat');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`PG_events_list`(`fk_user_id`,`event_text`) VALUES (1,'test event from database  ');
INSERT INTO `caorganizer`.`PG_events_list`(`fk_user_id`,`event_text`) VALUES (1,'test event from database 2');
INSERT INTO `caorganizer`.`PG_events_list`(`fk_user_id`,`event_text`) VALUES (1,'test event from database 3');
INSERT INTO `caorganizer`.`PG_events_list`(`fk_user_id`,`event_text`) VALUES (1,'test event from database 4');
INSERT INTO `caorganizer`.`PG_events_list`(`fk_user_id`,`event_text`) VALUES (1,'test event from database 5');
INSERT INTO `caorganizer`.`PG_events_list`(`fk_user_id`,`event_text`) VALUES (1,'test event from database 6');
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
INSERT INTO `caorganizer`.`pg_plant_growing_plan`(`pgp_id`,`pgp_title`,`plan_xml`,`fk_lang_id`,`fk_user_id`,`fk_plant_id`,`user_id`) VALUES (0,'test plant growing plan',null,1,1,2,1);
-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

commit;