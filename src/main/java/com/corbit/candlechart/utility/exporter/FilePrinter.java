package com.corbit.candlechart.utility.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Exportable {

	private BufferedWriter bufferedWriter;
	private String destination;
	private int count = 1;
	private String dir = "json/";

	public FilePrinter(String destination) {
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdir();
		}
		this.destination = destination;
	}

	@Override
	public void export(String object) {
		try {
			this.bufferedWriter = new BufferedWriter(new FileWriter(dir + count++ + destination));
			bufferedWriter.write(object);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
