#/bin/bash

mysql --user=root --password=root -h localhost < sql/CAAO_DB_MYSQL_MOD_CORE_MOD_PG_unix_for_new_db.sql
mysql --user=root --password=root -h localhost < sql/data_unix.sql
