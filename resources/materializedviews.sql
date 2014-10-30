-- Materialized views : 1NF

CREATE MATERIALIZED VIEW GetNoOfEnrollmentsForAgencies_View AS select enrollement_agency, sum(aadhaar_generated) from uidai.aadhaar_user group by enrollement_agency WITH DATA;
CREATE MATERIALIZED VIEW GetNoOfRejectionsForAgencies_View AS select enrollement_agency, sum(enrollment_rejected) from uidai.aadhaar_user group by enrollement_agency WITH DATA;
CREATE MATERIALIZED VIEW GetNoOfSeniorCitizensEnrolledGroupedByGender_View AS select gender, sum(aadhaar_generated) from uidai.aadhaar_user where age >= 65 group by gender WITH DATA;
CREATE MATERIALIZED VIEW GetNoOfAadhaarUsersProvidingPhoneGroupedByState_View AS select state, sum(phone_number_provided) from uidai.aadhaar_user group by state WITH DATA;
