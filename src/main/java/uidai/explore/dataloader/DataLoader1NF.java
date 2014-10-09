package uidai.explore.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import uidai.explore.intf.Constants;
import uidai.explore.intf.IDataLoader;

public class DataLoader1NF implements IDataLoader {

	private Connection connection;
	private PreparedStatement ps;
	public DataLoader1NF(Properties cfg){
		init(cfg);
	}
	
	private void init(Properties cfg){
		try{
			Class.forName(cfg.getProperty(Constants.JDBC_DRIVER));
			connection = DriverManager.getConnection(cfg.getProperty(Constants.JDBC_URL),
					cfg.getProperty(Constants.JDBC_USER), cfg.getProperty(Constants.JDBC_PASSWORD));
			ps = connection.prepareStatement(Constants.INSERT_QUERY_1NF);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUidaiData(List<String[]> csvValues) {
		final int batchSize = 10000;
		int count = 0;
		try {
			 for (String[] row: csvValues){
				ps.setString(1, row[0]);//registrar
				ps.setString(2, row[1]);//ea
				ps.setString(3, row[2]);//state
				ps.setString(4, row[3]);//district
				ps.setString(5, row[4]);//sub-district
				ps.setLong(6, Long.parseLong(row[5].trim()));//pin code
				ps.setString(7, row[6]);//gender
				ps.setInt(8, Integer.parseInt(row[7].trim()));//age
				ps.setString(9, row[8].trim());//aadhaar generated?
				ps.setString(10, row[9].trim());//enrollment rejected?
				ps.setString(11, row[10].trim());//has email
				ps.setString(12, row[11].trim());//has phone
				
				ps.addBatch();
				
				if (++count % batchSize == 0)
					ps.executeBatch();
			 }
			 ps.executeBatch();
		} catch (SQLException e) {
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
