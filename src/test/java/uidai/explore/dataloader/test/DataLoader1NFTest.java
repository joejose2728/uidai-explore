package uidai.explore.dataloader.test;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import uidai.explore.dataloader.DataLoader1NF;
import uidai.explore.util.CSVParser;
import uidai.explore.util.SchemaGenerator;

public class DataLoader1NFTest {

	private static final String sqlScriptFileName = "resources/raw-data-schema.sql";
	private static final String csvDirectory = "resources/test-data"; /*"C:\\MS SE\\CMPE 226\\Project I Data - UIDAI\\csv"*/;
	private static CSVParser csvParser;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		SchemaGenerator.generateSchema(sqlScriptFileName);
		csvParser = new CSVParser(new DataLoader1NF());
	}
	
	@Test
	public void testDataLoader1NF() throws IOException {
		csvParser.loadCSVFilesToDatabase(new File(csvDirectory));
	}

}
