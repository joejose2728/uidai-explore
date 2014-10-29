package uidai.explore.dataloader;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uidai.explore.intf.Constants;
import uidai.explore.intf.IDataLoader;
import uidai.explore.shard.conf.Node;
import uidai.explore.shard.conf.ShardConnectionInfo;
import uidai.explore.util.ConnectionFactory;

public class ShardDataLoader implements IDataLoader {

	private List<Node> nodes;
	private Map<String, ShardConnectionInfo> connectionInfo = new HashMap<String, ShardConnectionInfo>();
	
	public ShardDataLoader(List<Node> nodes){
		this.nodes = nodes;
		initializeMap();
	}
	
	private void initializeMap() {
		for (Node node: nodes){
			List<String> states = node.getStates();
			Connection connection = ConnectionFactory.getInstance().getConnection(node);
			try {
				PreparedStatement ps = connection.prepareStatement(Constants.INSERT_QUERY_1NF);
				ShardConnectionInfo ci = new ShardConnectionInfo();
				ci.setConnection(connection);
				ci.setStatement(ps);
				for (String state: states){
					connectionInfo.put(state, ci);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadUidaiData(List<String[]> csvValues, String enrollmentDate) {
		final int batchSize = 10000;
		try {
			 for (String[] row: csvValues){
				String state = row[2];
				ShardConnectionInfo shardInfo = connectionInfo.get(state); 
				PreparedStatement ps = shardInfo.getStatement();
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
				ps.setInt(8, parseColumn(row[7]));//age
				ps.setInt(9, parseColumn(row[8]));//aadhaar generated?
				ps.setInt(10, parseColumn(row[9]));//enrollment rejected?
				ps.setInt(11, parseColumn(row[10]));//has email
				ps.setInt(12, parseColumn(row[11]));//has phone
				ps.setString(13, enrollmentDate);
				ps.addBatch();
				
				int count = shardInfo.getBatchSize();
				
				if (++count % batchSize == 0){
					ps.executeBatch();
				}
				shardInfo.setBatchSize(count);
			 }
			 for (Node node: nodes){ // execute the remaining batch
					List<String> states = node.getStates();
					PreparedStatement ps = connectionInfo.get(states.get(0)).getStatement();
					ps.executeBatch();
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

	}

}
