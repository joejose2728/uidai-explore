drop schema if exists uidai cascade;
create schema uidai;


---sequence of agid(agency id)
create sequence uidai.agid_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 10;

create table uidai.location_details
  (
    pin_code bigint primary key,
    state varchar(50),
    district varchar(50),
    sub_district varchar(50)
  );
  
create table uidai.agency_details
(
  agid bigint primary key default nextval('uidai.agid_sequence'),
  registrar varchar(100),
  enrollement_agency varchar(100),
  pin_code bigint references uidai.location_details(pin_code)
);

create table uidai.aadhaar_record_per_day
  (
    enrollment_date date,
    gender char,
    age int,
    agid bigint references uidai.agency_details(agid),
    aadhaar_generated int,
    enrollment_rejected int,
    email_provided int,
    phone_number_provided int,
    PRIMARY KEY (enrollment_date, gender,age,agid)
  );




