package uidai.explore.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import uidai.explore.intf.IDataLoader;

public final class CSVParser {

	private static IDataLoader s_dataLoader;
	
	static {
		
	}
	
	public static void loadCSVFilesToDatabase(String database, File csvDirectory) throws IOException{
		if (!csvDirectory.isDirectory())
			throw new RuntimeException("Specified file is not a directory");
		
		File[] files = csvDirectory.listFiles(new FilenameFilter() {
			
			public boolean accept(File file, String name) {
				return name.endsWith(".csv");
			}
		});
		
		CSVReader reader = null;
		for (File file: files){
			reader = new CSVReader(new FileReader(file));
			s_dataLoader.loadUidaiData(reader.readAll());
		}
		reader.close();
	}
}
