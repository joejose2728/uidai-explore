package uidai.explore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import uidai.explore.intf.Constants;
import uidai.explore.shard.conf.Node;

public class ConnectionFactory {
	
	private static final String s_propertiesFile = "resources/db.properties";

	private static Properties s_cfg;
	private static ConnectionFactory s_instance;
	
	static {
		s_cfg = new Properties();
		try {
			s_cfg.load(new FileInputStream(new File(s_propertiesFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionFactory(){
		try{
			Class.forName(s_cfg.getProperty(Constants.JDBC_DRIVER));
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} 
	}
	
	public static ConnectionFactory getInstance(){
	    if (s_instance == null)
	    	s_instance = new ConnectionFactory();
	    return s_instance;
	}
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(s_cfg.getProperty(Constants.JDBC_URL),
					s_cfg.getProperty(Constants.JDBC_USER), s_cfg.getProperty(Constants.JDBC_PASSWORD));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection getConnection(Node node){
		try {
			return DriverManager.getConnection(node.getURL(),
					node.getUserName(), node.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
