package uidai.explore.dataloader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uidai.explore.hibernate.nf1.data.AadharRecPerDayKey;
import uidai.explore.hibernate.nf1.data.AadharRecordPerDay;
import uidai.explore.hibernate.nf1.data.AgencyDetails;
import uidai.explore.hibernate.nf1.data.LocationDetails;
import uidai.explore.intf.IDataLoader;
import uidai.explore.util.HibernateUtil;

public class DataLoaderHibernate2NF implements IDataLoader{

	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	public void loadUidaiData(List<String[]> csvValues, String enrollmentDate) {
		final int batchSize = 1000;
		int cnt =0;
		Date date = null;

		try {
			date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(enrollmentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Transaction tx  = session.beginTransaction();
			for (String[] row: csvValues)
			{

				int pin_code = 000000;
				try{
					pin_code = Integer.parseInt(row[5].trim());
				}catch(NumberFormatException e){
					System.err.println("Error parsing the pin code");
				}

				String state = row[2];
				String district = row[3];
				String subDistrict = row[4];
				String registrar = row[0];
				String enrollmentAgency = row[1];

				LocationDetails locationDetails = (LocationDetails) session.get(LocationDetails.class, pin_code);
				if(locationDetails == null)	{
					//create location details
					locationDetails = new LocationDetails();
					locationDetails.setPincode(pin_code);
					locationDetails.setState(state);
					locationDetails.setDistrict(district);
					locationDetails.setSubDistrict(subDistrict);
					session.save(locationDetails);
				}
				List<AgencyDetails> agencyDetailsSet = locationDetails.getAgencyDetails();
				
				AgencyDetails agencyDetail = new AgencyDetails();
				agencyDetail.setEnrollmentAgency(enrollmentAgency);
				agencyDetail.setPincode(pin_code);
				agencyDetail.setRegistrar(registrar);

				if (agencyDetailsSet.contains(agencyDetail)){
					agencyDetail = agencyDetailsSet.get(agencyDetailsSet.indexOf(agencyDetail));
					agencyDetailsSet.remove(agencyDetail);
				}else {
					session.save(agencyDetail);
				}
				AadharRecPerDayKey key = new AadharRecPerDayKey();
				key.setAge(Integer.parseInt(row[7]));
				key.setAgId(agencyDetail.getAgId());
				key.setDate(date);
				key.setGender(row[6].toCharArray()[0]);

				AadharRecordPerDay recordPerDay = new AadharRecordPerDay();
				recordPerDay.setAadharGenerated(parseColumn(row[8]));
				recordPerDay.setEnrollmentRejected(parseColumn(row[9]));
				recordPerDay.setResidentsWithEmail(parseColumn(row[10]));
				recordPerDay.setResidentsWithMobileNumber(parseColumn(row[11]));
				recordPerDay.setKey(key);
				//agencyDetail.addAadharRecord(recordPerDay);
				locationDetails.addAgencyDetail(agencyDetail);
				
				try {
					if (session.get(AadharRecordPerDay.class, key) == null)
						session.save(recordPerDay);
					if (++cnt % batchSize == 0)
					{			 
						session.flush();
						session.clear();
					}
				}
				catch (HibernateException e){
					e.printStackTrace();
				}
			}
			try {
				tx.commit();
			}
			catch (HibernateException e){
				e.printStackTrace();
			}

		} finally {
			// session.close();
		}
	}

	/*private long createAgencyDetails(String registrar, String enrollmentAgency,
			int pin_code) {

		Query query = session.createQuery(Constants.find_agencyDetails_by_pincode_registrar_agencyName);
		query.setString("registrar", registrar);
		query.setString("agencyName", enrollmentAgency);
		query.setInteger("pincode", pin_code);

		long agid = 0;
		try
		{
			List<AgencyDetails> agencyDetailsList = new ArrayList<AgencyDetails>(query.list());
			if(agencyDetailsList.size() == 0)
			{
				AgencyDetails agencyDetails = new AgencyDetails();
				agencyDetails.setRegistrar(registrar);
				agencyDetails.setPincode(pin_code);
				agencyDetails.setEnrollmentAgency(enrollmentAgency);
				session.save(agencyDetails);
				agid = agencyDetails.getAgId();
			} else {
				agid = agencyDetailsList.get(0).getAgId();
			}
		}catch(HibernateException e)
		{
			e.printStackTrace();
		}
		return agid;
	}

	private void createLocationDetails(int pin_code, String state, String district, String subDistrict) {
	
		LocationDetails locationDetails = (LocationDetails) session.get(LocationDetails.class, pin_code);
	
		if(locationDetails == null)
		{
			//create location details
			locationDetails = new LocationDetails();
			locationDetails.setPincode(pin_code);
			locationDetails.setState(state);
			locationDetails.setDistrict(district);
			locationDetails.setSubDistrict(subDistrict);
			session.save(locationDetails);
			session.flush();
			session.clear();
		}
	}
*/
	public void close() {
		// TODO Auto-generated method stub

	}

	private int parseColumn(String col){
		int val = 0;
		try{
			val = Integer.parseInt(col.trim());
		}catch (NumberFormatException e){
			System.err.println("Error while parsing column:"+col);
		}
		return val;
	}

}
