package uidai.explore.dataloader.test;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import uidai.explore.dataloader.DataLoaderHibernate1NF;
import uidai.explore.util.CSVParser;

public class DataLoaderHibernate1NFTest {

	private static final String csvDirectory = "resources/test-data";
	private static CSVParser csvParser;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		csvParser = new CSVParser(new DataLoaderHibernate1NF());
	}
	

	@Test
	public void testDataLoader1NF() throws IOException
	{
		csvParser.loadCSVFilesToDatabase(new File(csvDirectory));

	}
}
