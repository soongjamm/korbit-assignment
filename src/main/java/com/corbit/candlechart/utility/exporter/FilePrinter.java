package com.corbit.candlechart.utility.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Exportable {

	private BufferedWriter bufferedWriter;

	public FilePrinter(String destination) {
		try {
			this.bufferedWriter = new BufferedWriter(new FileWriter(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void export(String object) {
		try {
			bufferedWriter.write(object);
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
