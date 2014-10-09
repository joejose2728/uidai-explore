drop database uidai;
create database uidai;

grant all privileges on database uidai to postgres;

\connect uidai;

--uidai sequence
create sequence uniqueid_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 10;
    
create table aadhaar_user
(
   aid bigint primary key default nextval('uniqueid_sequence'),
   registrar varchar(30),
   enrollement_agency varchar(30),
   state varchar(20),
   district varchar(20),
   pin_code bigint,
   gender char,
   age int,
   aadhaar_generated boolean,
   enrollment_rejected boolean,
   has_email boolean,
   has_phone_number boolean
);