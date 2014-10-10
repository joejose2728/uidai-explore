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
	
	public void loadUidaiData(List<String[]> csvValues) {
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
				ps.setLong(6, pin_code);//pin code
				ps.setString(7, row[6]);//gender
				ps.setInt(8, Integer.parseInt(row[7].trim()));//age
				ps.setBoolean(9, row[8].trim() == "1");//aadhaar generated?
				ps.setBoolean(10, row[9].trim() == "1");//enrollment rejected?
				ps.setBoolean(11, row[10].trim() == "1");//has email
				ps.setBoolean(12, row[11].trim() == "1");//has phone
				
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
