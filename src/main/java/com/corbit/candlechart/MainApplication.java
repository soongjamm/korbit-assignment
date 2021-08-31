package com.corbit.candlechart;

import com.corbit.candlechart.utility.exporter.FilePrinter;
import com.corbit.candlechart.utility.importer.CSVLoader;

public class MainApplication {
	/**
	 * 이곳에서 파일 경로를 입력하거나 설정을 변경
	 */
	public static void main(String[] args) {
		int sec = Integer.parseInt(args[0]);
		CandleController candleController = new CandleController(new CSVLoader(), new FilePrinter("candlechart.json"));
		candleController.run(".korbitKRW.csv", sec);
	}
}
