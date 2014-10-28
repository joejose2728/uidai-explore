package uidai.explore.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uidai.explore.intf.Constants;
import uidai.explore.intf.IQuery;
import uidai.explore.util.ConnectionFactory;
import uidai.explore.util.Result;

public class DataReader2NF implements IQuery {
	
	private Connection connection;
	
	public DataReader2NF(){
		init();
	}
	
	private void init(){
		connection = ConnectionFactory.getInstance().getConnection();
	}

	public List<Result> getNoOfEnrollmentsForAgencies() {
		return execute(Constants.GET_NO_OF_ENROLLMENTS_FOR_AGENCIES_2NF, "GetNoOfEnrollmentsForAgencies-2NF");
	}

	public List<Result> getNoOfRejectionsForAgencies() {
		return execute(Constants.GET_NO_OF_REJECTIONS_FOR_AGENCIES_2NF, "GetNoOfRejectionsForAgencies-2NF");
	}

	public List<Result> getNoOfSeniorCitizensEnrolledGroupedByGender() {
		return execute(Constants.GET_NO_OF_SENIOR_CITIZENS_ENROLLED_GROUP_BY_GENDER_2NF, "GetNoOfSeniorCitizensEnrolledGroupedByGender-2NF");
	}

	public List<Result> getNoOfAadhaarUsersProvidingPhoneGroupedByState() {
		return execute(Constants.GET_NO_OF_USERS_PROVIDING_PHONE_NUMBER_GROUP_BY_STATE_2NF, "GetNoOfAadhaarUsersProvidingPhoneGroupedByState-2NF");
	}

	public List<Result> getNoOfAadhaarGeneratedForDateRangeGroupedByState(
			String startDate, String endDate) {
		return execute(Constants.GET_NO_OF_ENROLLMENTS_FOR_DATE_RANGE_GROUP_BY_STATE_2NF, "GetNoOfAadhaarGeneratedForDateRangeGroupedByState-2NF"
				, startDate, endDate);
	}

	private List<Result> execute(String sql, String method, Object... args){
		long start = System.currentTimeMillis();
		List<Result> results = new ArrayList<Result>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			if (args != null){
				int i = 0;
				for (Object obj : args)
					ps.setString(++i,obj.toString());
			}
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Result result = new Result();
				result.setGroupedAttribute(rs.getString(1));
				result.setCount(rs.getInt(2));
				results.add(result);
			}
			rs.close();ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(method + " time: " + (System.currentTimeMillis() - start) + " ms");
		return results;
	}
	
}
