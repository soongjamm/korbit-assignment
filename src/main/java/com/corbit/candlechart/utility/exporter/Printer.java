package com.corbit.candlechart.utility.exporter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Printer implements Exportable {

	private BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

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
