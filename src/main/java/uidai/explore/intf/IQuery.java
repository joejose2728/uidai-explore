package uidai.explore.intf;

import java.util.List;

import uidai.explore.nf1.model.AadhaarUser;

public interface IQuery {

	public String getStateWithMaxEnrollments();
	
	public String getStateWithMinEnrollments();
	
	public String getStateWithMaxMaleEnrollments();
	
	public String getStateWithMaxFemaleEnrollments();
	
	public String getStateWithMinMaleEnrollments();
	
	public String getStateWithMinFemaleEnrollments();
	
	public int getNoOfSeniorCitizensEnrolled();
	
	public int getNoOfYouthEnrolled();
	
	public int getNoOfWorkingClassCitizensEnrolled();
	
	public List<AadhaarUser> getAllEnrolledUsers(); 
	
}
