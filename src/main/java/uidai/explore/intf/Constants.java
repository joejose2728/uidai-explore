package uidai.explore.intf;

public interface Constants {

	String JDBC_DRIVER = "jdbc.driver";
	String JDBC_USER = "jdbc.user";
	String JDBC_PASSWORD = "jdbc.password";
	String JDBC_URL = "jdbc.url";

	//1NF Queries
	String INSERT_QUERY_1NF =
			"insert into uidai.aadhaar_user values(nextval('uidai.uniqueid_sequence'),?,?,?,?,?,?,?,?,?,?,?,?,?);";
	
	String GET_NO_OF_ENROLLMENTS_FOR_AGENCIES = "select enrollement_agency, sum(aadhaar_generated) from uidai.aadhaar_user"
			+ " group by enrollement_agency;";
	
	String GET_NO_OF_REJECTIONS_FOR_AGENCIES = "select enrollement_agency, sum(enrollment_rejected) from uidai.aadhaar_user"
			+ " group by enrollement_agency;";
	
	String GET_NO_OF_SENIOR_CITIZENS_ENROLLED_GROUP_BY_GENDER = "select gender, sum(aadhaar_generated) from uidai.aadhaar_user"
			+ " where age >= 65 group by gender;";
	
	String GET_NO_OF_USERS_PROVIDING_PHONE_NUMBER_GROUP_BY_STATE = "select state, sum(phone_number_provided) from uidai.aadhaar_user"
			+ " group by state;";
	
	String GET_NO_OF_ENROLLMENTS_FOR_DATE_RANGE_GROUP_BY_STATE = "select state, sum(aadhaar_generated) from uidai.aadhaar_user"
			+ " where enrollment_date between ? and ? group by state;";
	


	// Hibernate 1NF queries
	String HBN_GET_NO_OF_ENROLLMENTS_FOR_AGENCIES = "select new uidai.explore.util.Result(record.enrollmentAgency , sum(record.aadharGenerated)) from AadharRecord record"
			+ " group by record.enrollmentAgency";
	
	String HBN_GET_NO_OF_REJECTIONS_FOR_AGENCIES = "select new uidai.explore.util.Result(record.enrollmentAgency, sum(record.enrollmentRejected)) from AadharRecord record"
			+ " group by record.enrollmentAgency";
	
	String HBN_GET_NO_OF_SENIOR_CITIZENS_ENROLLED_GROUP_BY_GENDER = "select new uidai.explore.util.Result(record.gender, sum(record.aadharGenerated)) from AadharRecord record"
			+ " where record.age >= 65 group by record.gender";
	
	String HBN_GET_NO_OF_USERS_PROVIDING_PHONE_NUMBER_GROUP_BY_STATE = "select new uidai.explore.util.Result(record.state, sum(record.phoneNumberProvided)) from AadharRecord record"
			+ " group by record.state";
	
	String HBN_GET_NO_OF_ENROLLMENTS_FOR_DATE_RANGE_GROUP_BY_STATE = "select new uidai.explore.util.Result(record.state, sum(record.aadharGenerated)) from AadharRecord record"
			+ " where record.enrollmentDate between :startDate and :endDate group by record.state";

	//Hibernate 2NF
	
	String find_agencyDetails_by_pincode_registrar_agencyName = "from AgencyDetails agencyDetails where agencyDetails.registrar =:registrar "
			+ "and agencyDetails.enrollmentAgency=:agencyName and agencyDetails.pincode=:pincode";
	
	String HBN_2NF_GET_NO_OF_ENROLLMENTS_FOR_AGENCIES = "select new uidai.explore.util.Result(agencyDetails.enrollmentAgency , "
			+ "sum(recordPerDay.aadharGenerated)) from AgencyDetails agencyDetails inner join agencyDetails.aadharrecords recordPerDay"
			+ " group by agencyDetails.enrollmentAgency";
	

	String HBN_2NF_GET_NO_OF_REJECTIONS_FOR_AGENCIES = "select new uidai.explore.util.Result(agencyDetails.enrollmentAgency , "
			+ "sum(recordPerDay.enrollmentRejected)) from AgencyDetails agencyDetails inner join agencyDetails.aadharrecords recordPerDay"
			+ " group by agencyDetails.enrollmentAgency";
	
	String HBN_2NF_GET_NO_OF_SENIOR_CITIZENS_ENROLLED_GROUP_BY_GENDER = "select new uidai.explore.util.Result(record.key.gender, sum(record.aadharGenerated))"
			+ "from AadharRecordPerDay record"
			+ " where record.key.age >= 65 group by record.key.gender";
	

	String HBN_2NF_GET_NO_OF_USERS_PROVIDING_PHONE_NUMBER_GROUP_BY_STATE = "select "
			+ "new uidai.explore.util.Result(locationDetails.state, sum(record.residentsWithMobileNumber)) from LocationDetails locationDetails"
			+ " inner join locationDetails.agencyDetails agencyDetails inner join agencyDetails.aadharrecords record"
			+ " group by locationDetails.state";
	
	String HBN_2NF_GET_NO_OF_ENROLLMENTS_FOR_DATE_RANGE_GROUP_BY_STATE = "select new uidai.explore.util.Result(locationDetails.state, sum(record.aadharGenerated)) "
			+ "from LocationDetails locationDetails inner join locationDetails.agencyDetails agencyDetails inner join agencyDetails.aadharrecords record"
			+ " where record.key.date between :startDate and :endDate group by locationDetails.state";

	//2NF Insert Queries
	String END_TRANSACTION = "COMMIT;";
		
	String INSERT_QUERY_2NF_LOCATION_DETAILS =
			"insert into uidai.location_details (pin_code,state,district,sub_district) select ?,?,?,? where not exists (select 1 from uidai.location_details where pin_code=?);"; 
	String INSERT_QUERY_2NF_AGENCY_DETAILS = 
			"insert into uidai.agency_details (agid,registrar,enrollement_agency,pin_code) select nextval('uidai.agid_sequence'),?,?,? where not exists (select 1 from uidai.agency_details where registrar=? and enrollement_agency=? and pin_code=?);";
	String INSERT_QUERY_2NF_AADHAR_RECORD = 
			"insert into uidai.aadhaar_record_per_day values(?,?,?,?,?,?,?,?);";
    String GET_AGID = "select agid from uidai.agency_details where registrar=? and enrollement_agency=? and pin_code=?;";


    
    //2NF Read Queries 
    String GET_NO_OF_ENROLLMENTS_FOR_AGENCIES_2NF = "select enrollement_agency,sum(aadhaar_generated) as temp from uidai.aadhaar_record_per_day,uidai.agency_details where uidai.aadhaar_record_per_day.agid=uidai.agency_details.agid group by enrollement_agency order by temp desc;";
	
	String GET_NO_OF_REJECTIONS_FOR_AGENCIES_2NF = "select enrollement_agency,sum(enrollment_rejected) as temp from uidai.aadhaar_record_per_day,uidai.agency_details where uidai.aadhaar_record_per_day.agid=uidai.agency_details.agid group by enrollement_agency order by temp desc;";
	
	String GET_NO_OF_SENIOR_CITIZENS_ENROLLED_GROUP_BY_GENDER_2NF = "select gender, sum(aadhaar_generated) from uidai.aadhaar_record_per_day"
			+ " where age >= 65 group by gender;";
	
	String GET_NO_OF_USERS_PROVIDING_PHONE_NUMBER_GROUP_BY_STATE_2NF = "select state,sum(phone_number_provided) from uidai.aadhaar_record_per_day,(select agid,state from uidai.agency_details,uidai.location_details where uidai.agency_details.pin_code=uidai.location_details.pin_code) as t where uidai.aadhaar_record_per_day.agid=t.agid group by state order by sum desc;";
	
	String GET_NO_OF_ENROLLMENTS_FOR_DATE_RANGE_GROUP_BY_STATE_2NF = "select state,sum(aadhaar_generated) from uidai.aadhaar_record_per_day,(select agid,state from uidai.agency_details,uidai.location_details where uidai.agency_details.pin_code=uidai.location_details.pin_code) as t where uidai.aadhaar_record_per_day.agid=t.agid and enrollment_date between to_date(?,'YYYYMMDD') and to_date(?,'YYYYMMDD') group by state order by sum desc;";
    
    
}

