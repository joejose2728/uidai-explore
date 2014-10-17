drop schema if exists uidai cascade;
create schema uidai;

--grant all privileges on database uidai to postgres;

--connect to uidai;

--uidai sequence
create sequence uidai.uniqueid_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 10;
    
create table uidai.aadhaar_user
(
   aid bigint primary key default nextval('uidai.uniqueid_sequence'),
   registrar varchar(100),
   enrollement_agency varchar(100),
   state varchar(50),
   district varchar(50),
   sub_district varchar(50),
   pin_code bigint,
   gender char,
   age int,
   aadhaar_generated int,
   enrollment_rejected int,
   email_provided int,
   phone_number_provided int,
   enrollment_date varchar(10)
);