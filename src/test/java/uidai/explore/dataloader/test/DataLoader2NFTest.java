package uidai.explore.dataloader.test;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import uidai.explore.dataloader.DataLoader2NF;
import uidai.explore.util.CSVParser;
import uidai.explore.util.SchemaGenerator;

public class DataLoader2NFTest {
	
	private static final String sqlScriptFileName = "resources/raw-data-schema-2NF.sql";
	private static final String csvDirectory = "resources/test-data"; /*"C:\\MS SE\\CMPE 226\\Project I Data - UIDAI\\csv"*/;
	private static CSVParser csvParser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SchemaGenerator.generateSchema(sqlScriptFileName);
		csvParser = new CSVParser(new DataLoader2NF());
	}

	@Test
	public void testDataLoader2NF() throws IOException {
		csvParser.loadCSVFilesToDatabase(new File(csvDirectory));
	}

}
