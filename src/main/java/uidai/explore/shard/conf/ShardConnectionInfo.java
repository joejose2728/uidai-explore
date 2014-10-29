package uidai.explore.shard.conf;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ShardConnectionInfo {

	private int batchSize;
	private Connection connection;
	private PreparedStatement statement;
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public PreparedStatement getStatement() {
		return statement;
	}
	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}
}
