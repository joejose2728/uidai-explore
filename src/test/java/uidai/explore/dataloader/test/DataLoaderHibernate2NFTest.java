package uidai.explore.dataloader.test;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import uidai.explore.dataloader.DataLoaderHibernate2NF;
import uidai.explore.util.CSVParser;

public class DataLoaderHibernate2NFTest {

	private static final String csvDirectory = "resources/test-data"; /*"C:\\MS SE\\CMPE 226\\Project I Data - UIDAI\\csv"*/;
	private static CSVParser csvParser;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		csvParser = new CSVParser(new DataLoaderHibernate2NF());
	}
	

	@Test
	public void testDataLoader1NF() throws IOException
	{
		csvParser.loadCSVFilesToDatabase(new File(csvDirectory));

	}
}
