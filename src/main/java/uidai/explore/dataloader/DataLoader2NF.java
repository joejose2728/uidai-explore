package uidai.explore.dataloader;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import uidai.explore.intf.Constants;
import uidai.explore.intf.IDataLoader;
import uidai.explore.util.ConnectionFactory;

public class DataLoader2NF implements IDataLoader {
	
	private Connection connection;
	
	@SuppressWarnings("unused")
	private PreparedStatement ps1, ps2, ps3;
	
	public DataLoader2NF(){
		init();
	}
	
	private void init(){
		connection = ConnectionFactory.getInstance().getConnection();
		if (connection != null){
			try {
				ps1 = connection.prepareStatement(Constants.INSERT_QUERY_2NF_LOCATION_DETAILS);
				ps2 = connection.prepareStatement(Constants.INSERT_QUERY_2NF_AGENCY_DETAILS);
				ps3 = connection.prepareStatement(Constants.INSERT_QUERY_2NF_AADHAR_RECORD);
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadUidaiData(List<String[]> csvValues, String enrollmentData) {
		if (ps1 == null)
			throw new RuntimeException("Prepared statement is null.");
		
		final int batchSize = 10000;
		int count = 0;
		try {
			 for (String[] row: csvValues){
		      //==========location details =============
			    long pin_code = 000000;
					try{
						pin_code = Long.parseLong(row[5].trim());
					}catch(NumberFormatException e){
					}
				ps1.setLong(6, pin_code);//pin code
				ps1.setString(5, row[4]);//sub-district
      			ps1.setString(4, row[3]);//district
				ps1.setString(3, row[2]);//state
               
               //=============Agency details===========
                ps2.setString(1, row[0]);//registrar
				ps2.setString(2, row[1]);//ea
      			ps2.setLong(6, pin_code);//pin code
				
				//============Aadhaar_Record_Per_Day========
				ps3.setString(13, enrollmentData);
				ps3.setString(7, row[6]);//gender
				ps3.setInt(8, parseColumn(row[7]));//age
				ps3.setInt(9, parseColumn(row[8]));//aadhaar generated?
				ps3.setInt(10, parseColumn(row[9]));//enrollment rejected?
				ps3.setInt(11, parseColumn(row[10]));//has email
				ps3.setInt(12, parseColumn(row[11]));//has phone
				
				ps1.addBatch();
				ps2.addBatch();
				ps3.addBatch();
				
				if (++count % batchSize == 0)
					ps1.executeBatch();
				    ps2.executeBatch();
				    ps3.executeBatch(); 
			 }
			 
			 ps1.executeBatch();
			 ps2.executeBatch();
			 ps3.executeBatch();
		
		} catch (BatchUpdateException e) {
			e.getNextException().printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}

	private int parseColumn(String col){
		int val = 0;
		try{
			val = Integer.parseInt(col.trim());
		}catch (NumberFormatException e){
		}
		return val;
	}
	
	
	public void close() {
		// TODO Auto-generated method stub
		
	}

}










