#/bin/bash

mysql -u root -p root -h localhost < CAAO_DB_MYSQL_MOD_CORE_MOD_PG_unix_for_new_db.sql
mysql -u root -p root -h localhost < data_unix.sql