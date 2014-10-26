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
}
