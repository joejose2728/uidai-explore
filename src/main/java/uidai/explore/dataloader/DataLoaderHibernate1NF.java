package uidai.explore.dataloader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import uidai.explore.hibernate.nf1.data.AadharRecord;
import uidai.explore.intf.IDataLoader;
import uidai.explore.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataLoaderHibernate1NF implements IDataLoader{

	public void loadUidaiData(List<String[]> csvValues, String enrollmentDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		final int batchSize = 10000;
		int cnt =0;
		Date date = null;
		
		try {
			date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(enrollmentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 Transaction tx = session.beginTransaction();
			 for (String[] row: csvValues)
			 {
				 AadharRecord record = new AadharRecord();
				 record.setRegistrar(row[0]);//registrar
				 record.setEnrollmentAgency(row[1]);//ea
				 record.setState(row[2]);//state
				 record.setDistrict(row[3]);//district
				 record.setSubDistrict(row[4]);//sub-district
					long pin_code = 000000;
					try{
						pin_code = Long.parseLong(row[5].trim());
					}catch(NumberFormatException e){
						System.err.println("Error parsing the pin code");
					}
					
				record.setPinCode(pin_code);//pin code
				record.setGender(row[6].toCharArray()[0]);//gender
				record.setAge(Integer.parseInt(row[7]));//age
				record.setAadharGenerated(parseColumn(row[8]));//aadhaar generated?
				record.setEnrollmentRejected(parseColumn(row[9]));//enrollment rejected?
				record.setEmailProvided(parseColumn(row[10]));//has email
				record.setPhoneNumberProvided(parseColumn(row[11]));//has phone
				record.setEnrollmentDate(date);
				
				session.save(record);
					
					if (++cnt % batchSize == 0)
					{			 
						session.flush();
						session.clear();
					}
		
			}
			tx.commit();
		} finally {
			// session.close();
		}
	}

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
