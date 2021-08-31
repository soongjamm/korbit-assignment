package com.corbit.candlechart.utility.importer;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVLoader implements Importable {

	@Override
	public List<String[]> load(String path) {
		FileReader filereader;
		List<String[]> trades = null;
		try {
			filereader = new FileReader(path);
			CSVReader csvReader = new CSVReader(filereader);
			trades = csvReader.readAll();
			System.out.println("loading finished");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trades;
	}
}
