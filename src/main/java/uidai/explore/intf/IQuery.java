package uidai.explore.intf;

import java.util.List;

import uidai.explore.util.Result;

public interface IQuery {

	/*public String getStateWithMaxEnrollments();
	
	public String getStateWithMinEnrollments();
	
	public String getStateWithMaxMaleEnrollments();
	
	public String getStateWithMaxFemaleEnrollments();
	
	public String getStateWithMinMaleEnrollments();
	
	public String getStateWithMinFemaleEnrollments();
	
	public int getNoOfSeniorCitizensEnrolled();
	
	public int getNoOfYouthEnrolled();
	
	public int getNoOfWorkingClassCitizensEnrolled();
	
	public List<AadhaarUser> getAllEnrolledUsers(); */
	
	
	public List<Result> getNoOfEnrollmentsForAgencies();
	
	public List<Result> getNoOfRejectionsForAgencies();
	
	public List<Result> getNoOfSeniorCitizensEnrolledGroupedByGender();
	
	public List<Result> getNoOfAadhaarUsersProvidingPhoneGroupedByState();
	
	public List<Result> getNoOfAadhaarGeneratedForDateRangeGroupedByState(String startDate, String endDate);
}
