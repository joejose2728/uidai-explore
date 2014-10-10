package uidai.explore.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;

public final class SchemaGenerator {

	private static Connection connection = ConnectionFactory.getInstance().getConnection();

	public static void generateSchema(String sqlScriptFileName){
		File sqlScript = new File(sqlScriptFileName);
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(sqlScript));
			byte[] raw = new byte[(int) sqlScript.length()];
			bis.read(raw);
			String schema = new String(raw);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(schema);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
