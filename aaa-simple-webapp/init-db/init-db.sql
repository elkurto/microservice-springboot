-- note: set the env-var DB_NAME before running this script
-- create schema if not exists $DB_NAME;

-- create DATABASE $DB_NAME;
--create role $DB_NAME WITH encrypted PASSWORD '$DB_NAME';
--alter role $DB_NAME set client_encoding to 'utf8';
--alter role $DB_NAME set default_transaction_isolation TO 'read_committed';
--alter role $DB_NAME set timezone to 'UTC';
--GRANT all privileges on database $DB_NAME to $DB_NAME;
--ALTER role $DB_NAME LOGIN;

--ALTER role $DB_NAME set SEARCH_PATH TO ${DB_NAME},public;

create table book (
   id uuid PRIMARY KEY not null,
   name varchar(127),
   author varchar(127),
   published_date_millis bigint
);
