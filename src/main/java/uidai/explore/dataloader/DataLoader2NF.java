package uidai.explore.dataloader;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.postgresql.util.PSQLException;

import uidai.explore.intf.Constants;
import uidai.explore.intf.IDataLoader;
import uidai.explore.util.ConnectionFactory;

public class DataLoader2NF implements IDataLoader {
	
	private Connection connection;
	
	@SuppressWarnings("unused")
	private PreparedStatement ps1, ps2, ps3,ps4;
	
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
				ps4 = connection.prepareStatement(Constants.GET_AGID);
								
			}
			
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadUidaiData(List<String[]> csvValues, String enrollmentData) {
		if (ps1 == null)
			throw new RuntimeException("Prepared statement is null.");
				
		try { 
			connection.setAutoCommit(false);  //start transaction  
			
			 for (String[] row: csvValues){
		      //==========location details =============
			    long pin_code = 000000;
					try{
						pin_code = Long.parseLong(row[5].trim());
					}catch(NumberFormatException e){
					}
				ps1.setLong(1, pin_code);//pin code
				ps1.setString(2, row[2]);//state 
				ps1.setString(3, row[3]);//district
				ps1.setString(4, row[4]);//sub-district      			
				ps1.setLong(5, pin_code);//pin code in where clause 
				
				ps1.executeUpdate();
				
               //=============Agency details===========
                ps2.setString(1, row[0]);//registrar
				ps2.setString(2, row[1]);//ea
      			ps2.setLong(3, pin_code);//pin code 
      			
      			ps2.setString(4, row[0]);//registrar in where clause 
				ps2.setString(5, row[1]);//ea in where clause 
      			ps2.setLong(6, pin_code);//pin code in where clause 
      			      			
      			try {
      				ps2.executeUpdate();
      			}catch (PSQLException pe){
      				
      			}			
      			
      			
				ResultSet rs = ps2.getGeneratedKeys();
				long returned_agid;
				
				if(rs.first()){
					returned_agid = rs.getLong(1);
				}else{
					ps4.setString(1, row[0]);//registrar
					ps4.setString(2, row[1]);//ea
		      		ps4.setLong(3, pin_code);//pin code 
		      		
		      		ResultSet rs2 = ps4.executeQuery(); 
		      		returned_agid=rs2.getLong(1);
				}
				
				//============Aadhaar_Record_Per_Day======== 
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				try {
					Date d = df.parse(enrollmentData);
					ps3.setDate(1, new java.sql.Date(d.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				ps3.setString(2, row[6]);//gender
				ps3.setInt(3, parseColumn(row[7]));//age
				ps3.setLong(4,returned_agid);
				ps3.setInt(5, parseColumn(row[8]));//aadhaar generated?
				ps3.setInt(6, parseColumn(row[9]));//enrollment rejected?
				ps3.setInt(7, parseColumn(row[10]));//has email
				ps3.setInt(8, parseColumn(row[11]));//has phone 
				
				ps3.executeUpdate();
				
				connection.commit(); // end transaction				   
			 }
			 
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










