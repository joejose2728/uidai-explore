package uidai.explore.query.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import uidai.explore.hibernate.nf1.util.HibernateUtil;
import uidai.explore.intf.Constants;
import uidai.explore.intf.IQuery;
import uidai.explore.util.Result;

public class DataReaderHibernate1NF implements IQuery{

	public List<Result> getNoOfEnrollmentsForAgencies() {
		return execute(Constants.HBN_GET_NO_OF_ENROLLMENTS_FOR_AGENCIES,"getNoOfEnrollmentsForAgencies" );
	}

	public List<Result> getNoOfRejectionsForAgencies() {
		return execute(Constants.HBN_GET_NO_OF_REJECTIONS_FOR_AGENCIES,"getNoOfRejectionsForAgencies");
	}

	public List<Result> getNoOfSeniorCitizensEnrolledGroupedByGender() {
		return execute(Constants.HBN_GET_NO_OF_SENIOR_CITIZENS_ENROLLED_GROUP_BY_GENDER, "getNoOfSeniorCitizensEnrolledGroupedByGender");
	}

	public List<Result> getNoOfAadhaarUsersProvidingPhoneGroupedByState() {
		return execute(Constants.HBN_GET_NO_OF_USERS_PROVIDING_PHONE_NUMBER_GROUP_BY_STATE,"getNoOfAadhaarUsersProvidingPhoneGroupedByState");
	}

	public List<Result> getNoOfAadhaarGeneratedForDateRangeGroupedByState(
			String startDate, String endDate) {
		
		Date startDt = null;
		Date endDt = null;
		
		try {
			startDt = new SimpleDateFormat("yyyyMMdd").parse(startDate);
			endDt = new SimpleDateFormat("yyyyMMdd").parse(endDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return execute(Constants.HBN_GET_NO_OF_ENROLLMENTS_FOR_DATE_RANGE_GROUP_BY_STATE, "GetNoOfAadhaarGeneratedForDateRangeGroupedByState"
				, startDt, endDt);
	}

	private List<Result> execute(String sql, String method){
		long start = System.currentTimeMillis();
		List<Result> results = new ArrayList<Result>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(sql);

			// the join creates duplicate records - this will remove them
			Set<Result> set = new LinkedHashSet<Result>(query.list());
			results = new ArrayList<Result>(set);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

		System.out.println(method + " time: " + (System.currentTimeMillis() - start) + " ms");
		return results;
	}

	private List<Result> execute(String sql, String method, Date startDate, Date endDate){
		long start = System.currentTimeMillis();
		List<Result> results = new ArrayList<Result>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);

			// the join creates duplicate records - this will remove them
			Set<Result> set = new LinkedHashSet<Result>(query.list());
			results = new ArrayList<Result>(set);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

		System.out.println(method + " time: " + (System.currentTimeMillis() - start) + " ms");
		return results;
	}
}
