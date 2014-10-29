package uidai.explore.query.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import uidai.explore.shard.conf.Node;
import uidai.explore.shard.conf.ShardConnectionInfo;
import uidai.explore.util.ConnectionFactory;
import uidai.explore.util.Result;

public class ShardDataReader {

	private List<Node> nodes;
	private List<ShardConnectionInfo> connectionInfo = new ArrayList<ShardConnectionInfo>();
	private volatile long start;
	
	public ShardDataReader(List<Node> nodes){
		this.nodes = nodes;
		initializeMap();
	}
	
	private void initializeMap() {
		for (Node node: nodes){
			Connection connection = ConnectionFactory.getInstance().getConnection(node);
			ShardConnectionInfo ci = new ShardConnectionInfo();
			ci.setConnection(connection);
			connectionInfo.add(ci);
		}
	}
		
	public void getNoOfEnrollmentsForAgencies() {
		// TODO Auto-generated method stub
		execute(1);
	}

	public void getNoOfRejectionsForAgencies() {
		// TODO Auto-generated method stub
		execute(2);
	}

	public void getNoOfSeniorCitizensEnrolledGroupedByGender() {
		// TODO Auto-generated method stub
		execute(3);
	}

	public void getNoOfAadhaarUsersProvidingPhoneGroupedByState() {
		// TODO Auto-generated method stub
		execute(4);
	}

	public void getNoOfAadhaarGeneratedForDateRangeGroupedByState(
			String startDate, String endDate) {
		execute(5,startDate,endDate);
	}
	
	private void execute(int queryCase, Object... args){
		start = System.currentTimeMillis();
		System.out.println("Start: " + start);
		for (Node node: nodes){
			int index = nodes.indexOf(node);
			DataReader1NF reader = new DataReader1NF(connectionInfo.get(index).getConnection());
			Thread t = new Thread(new QueryExecutor(node, reader, queryCase, args));
			t.start();
		}
	}

	private class QueryExecutor implements Runnable {

		private DataReader1NF reader;
		private int queryCase;
		private Object[] args;
		private Node node;
		
		public QueryExecutor(Node node,DataReader1NF datareader, int queryCase, Object...args){
			this.reader = datareader;
			this.queryCase = queryCase;
			this.args = args;
			this.node = node;
		}
		
		public void run() {
			List<Result> results = null;
			//long start = System.currentTimeMillis();
			switch(queryCase) {
				case 1: results = reader.getNoOfEnrollmentsForAgencies();
					break;
				case 2: results = reader.getNoOfRejectionsForAgencies();
					break;
				case 3: results = reader.getNoOfSeniorCitizensEnrolledGroupedByGender();
					break;
				case 4: results = reader.getNoOfAadhaarUsersProvidingPhoneGroupedByState();
					break;
				case 5: results = reader.getNoOfAadhaarGeneratedForDateRangeGroupedByState(args[0].toString(), args[1].toString());
					break;
			}
			long end = System.currentTimeMillis();
			
			System.out.println("Node " + node.getURL() + " returned query result in " + end + " ms");
			System.out.println("Total time expired from start : " + (end - start) + " ms");
			System.out.println("=====================================================================");
			for (Result result : results)
				System.out.println(result.getGroupedAttribute() + "\t\t\t" + result.getCount());
		}
	}
	
}
