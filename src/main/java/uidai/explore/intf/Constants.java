package uidai.explore.intf;

public interface Constants {

	String JDBC_DRIVER = "jdbc.driver";
	String JDBC_USER = "jdbc.user";
	String JDBC_PASSWORD = "jdbc.password";
	String JDBC_URL = "jdbc.url";

	String INSERT_QUERY_1NF =
			"insert into uidai.aadhaar_user values(nextval('uidai.uniqueid_sequence'),?,?,?,?,?,?,?,?,?,?,?,?);";
}
