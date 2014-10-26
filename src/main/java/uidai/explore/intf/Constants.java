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
	
	//2NF Queries
	String BEGIN_TRANSACTIONS = "BEGIN;";
	
	String INSERT_QUERY_2NF_AADHAR_RECORD = 
			"insert into uidai.aadhaar_record_per_day values(?,?,?,?,?,?,?,?)";
	String INSERT_QUERY_2NF_AGENCY_DETAILS = 
			"insert into uidai.agency_details values(nextval('uidai.agid_sequence'),?,?,?)";
	String INSERT_QUERY_2NF_LOCATION_DETAILS = 
			"insert into uidai.location_details values(?,?,?,?)"; 
	String END_TRANSACTION = "COMMIT;";
}






