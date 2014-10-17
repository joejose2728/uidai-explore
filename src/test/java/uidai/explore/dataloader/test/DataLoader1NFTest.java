package uidai.explore.dataloader.test;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import uidai.explore.util.CSVParser;
import uidai.explore.util.SchemaGenerator;

public class DataLoader1NFTest {

	private static final String sqlScriptFileName = "resources/raw-data-schema.sql";
	private static final String csvDirectory = "resources/test-data"; /*"C:\\MS SE\\CMPE 226\\Project I Data - UIDAI\\csv"*/;
	@BeforeClass
	public static void setUpBeforeClass(){
		SchemaGenerator.generateSchema(sqlScriptFileName);
	}
	
	@Test
	public void testDataLoader1NF() throws IOException {
		CSVParser.loadCSVFilesToDatabase(new File(csvDirectory));
	}

}
