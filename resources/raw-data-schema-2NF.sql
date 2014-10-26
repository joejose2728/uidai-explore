drop schema if exists uidai cascade;
create schema uidai;


---sequence of agid(agency id)
create sequence uidai.agid_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 10;

create table uidai.aadhaar_record_per_day
  (
    enrollment_date date,
    gender char,
    age int,
    agid bigint default nextval('uidai.agid_sequence'),
    aadhaar_generated int,
    enrollment_rejected int,
    email_provided int,
    phone_number_provided int,
    PRIMARY KEY (enrollment_date, gender,age,agid)
  );

create table uidai.agency_details
  (
    agid bigint references uidai.aadhaar_record_per_day(agid),
    registrar varchar(100),
    enrollement_agency varchar(100),
    pin_code bigint
  );

create table uidai.location_details
  (
    pin_code bigint references uidai.agency_details(pin_code),
    state varchar(50),
    district varchar(50),
    sub_district varchar(50)
  );
