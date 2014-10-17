package uidai.explore.dataloader;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import uidai.explore.intf.Constants;
import uidai.explore.intf.IDataLoader;
import uidai.explore.util.ConnectionFactory;

public class DataLoader1NF implements IDataLoader {

	private Connection connection;
	private PreparedStatement ps;
	public DataLoader1NF(){
		init();
	}
	
	private void init(){
		connection = ConnectionFactory.getInstance().getConnection();
		if (connection != null){
			try {
				ps = connection.prepareStatement(Constants.INSERT_QUERY_1NF);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadUidaiData(List<String[]> csvValues, String enrollmentDate) {
		if (ps == null)
			throw new RuntimeException("Prepared statement is null.");
		
		final int batchSize = 10000;
		int count = 0;
		try {
			 for (String[] row: csvValues){
				ps.setString(1, row[0]);//registrar
				ps.setString(2, row[1]);//ea
				ps.setString(3, row[2]);//state
				ps.setString(4, row[3]);//district
				ps.setString(5, row[4]);//sub-district
				long pin_code = 000000;
				try{
					pin_code = Long.parseLong(row[5].trim());
				}catch(NumberFormatException e){
				}
				int age = 0;

				try{
					age = Integer.parseInt(row[7].trim());
				}catch (NumberFormatException e){
				}
				ps.setLong(6, pin_code);//pin code
				ps.setString(7, row[6]);//gender
				ps.setInt(8, age);//age
				ps.setInt(9, Integer.parseInt(row[8].trim()));//aadhaar generated?
				ps.setInt(10, Integer.parseInt(row[9].trim()));//enrollment rejected?
				ps.setInt(11, Integer.parseInt(row[10].trim()));//has email
				ps.setInt(12, Integer.parseInt(row[11].trim()));//has phone
				ps.setString(13, enrollmentDate);
				ps.addBatch();
				
				if (++count % batchSize == 0)
					ps.executeBatch();
			 }
			 ps.executeBatch();
		} catch (BatchUpdateException e) {
			e.getNextException().printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void close(){
		try {
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
