package uidai.explore.dataloader.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import uidai.explore.intf.IQuery;
import uidai.explore.query.impl.DataReader1NF;
import uidai.explore.query.impl.DataReaderHibernate2NF;
import uidai.explore.util.Result;

public class DataReaderHibernate2NFTest {

private static IQuery query;
	
	@BeforeClass
	public static void setUp(){
		query = new DataReaderHibernate2NF();
	}
	
	@Test
	public void testGetNoOfEnrollmentsForAgencies() {
		List<Result> results = query.getNoOfEnrollmentsForAgencies();
		System.out.println("State\t\t\tCount");
		System.out.println("=================================================================");
		for (Result result : results){
			System.out.println(result.getGroupedAttribute() + "\t\t\t" + result.getCount());
		}
	}
	
	@Test
	public void testGetNoOfRejectionsForAgencies() {
		List<Result> results = query.getNoOfRejectionsForAgencies();
		System.out.println("State\t\t\tCount");
		System.out.println("=================================================================");
		for (Result result : results){
			System.out.println(result.getGroupedAttribute() + "\t\t\t" + result.getCount());
		}
	}

	@Test
	public void testGetNoOfSeniorCitizensEnrolledGroupedByGender() {
		List<Result> results = query.getNoOfSeniorCitizensEnrolledGroupedByGender();
		System.out.println("Gender\t\t\tCount");
		System.out.println("=================================================================");
		for (Result result : results){
			System.out.println(result.getGroupedAttribute() + "\t\t\t" + result.getCount());
		}
	}

	@Test
	public void testGetNoOfAadhaarUsersProvidingPhoneGroupedByState() {
		List<Result> results = query.getNoOfAadhaarUsersProvidingPhoneGroupedByState();
		System.out.println("State\t\t\tCount");
		System.out.println("=================================================================");
		for (Result result : results){
			System.out.println(result.getGroupedAttribute() + "\t\t\t" + result.getCount());
		}
	}

	@Test
	public void testGetNoOfAadhaarGeneratedForDateRangeGroupedByState() {
		List<Result> results = query.getNoOfAadhaarGeneratedForDateRangeGroupedByState("20120101", "20120102");
		System.out.println("State\t\t\tCount");
		System.out.println("=================================================================");
		for (Result result : results){
			System.out.println(result.getGroupedAttribute() + "\t\t\t" + result.getCount());
		}
	}

}
