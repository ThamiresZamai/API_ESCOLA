package br.com.csvreader.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

	public boolean fileExists(String file) {
		File fileObj = new File(file);
		return fileObj.exists();
	}
	
	public List<List<String>> readFile(String file) {
		List<List<String>> records = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		    }
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return records;
	}
	
}
