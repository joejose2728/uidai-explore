package uidai.explore.util;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import uidai.explore.dataloader.DataLoader1NF;
import uidai.explore.intf.IDataLoader;
import au.com.bytecode.opencsv.CSVReader;

public final class CSVParser {

	private static IDataLoader s_dataLoader;
	
	static {
		s_dataLoader = new DataLoader1NF();
	}
	
	public static void loadCSVFilesToDatabase(/*String database,*/ File csvDirectory) throws IOException{
		if (!csvDirectory.isDirectory())
			throw new RuntimeException("Specified file is not a directory");
		
		File[] files = csvDirectory.listFiles(new FilenameFilter() {
			
			public boolean accept(File file, String name) {
				return name.endsWith(".csv");
			}
		});
		long st = System.currentTimeMillis();
		CSVReader reader = null;
		for (File file: files){
			String fileName = file.getName();
			String date = fileName.substring(fileName.lastIndexOf("-") + 1).replace(".csv", "");
			System.out.println("Processing " + fileName);
			reader = new CSVReader(new FileReader(file), CSVReader.DEFAULT_SEPARATOR, CSVReader.DEFAULT_QUOTE_CHARACTER, 1);
			s_dataLoader.loadUidaiData(reader.readAll(), date);
			System.out.println("Processed " + fileName);
		}
		reader.close();
		System.out.println("Total time for loading " + files.length + " files: " + (System.currentTimeMillis() - st));
	}
}
