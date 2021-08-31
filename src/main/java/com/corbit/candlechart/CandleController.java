package com.corbit.candlechart;

import com.corbit.candlechart.candle.Candles;
import com.corbit.candlechart.utility.exporter.Exportable;
import com.corbit.candlechart.utility.importer.Importable;

import java.io.File;
import java.util.List;

public class CandleController {
	private final Importable importable;
	private final Exportable exportable;

	public CandleController(Importable importable, Exportable exportable) {
		this.importable = importable;
		this.exportable = exportable;
	}

	/**
	 * 캔들차트 json array를 생성한다.
	 * .csv 데이터는 시간순서대로 오름차순 되어있다고 가정하고,
	 *
	 * @param source csv 파일 경로
	 * @param sec  각 거래의 기간
	 */
	private static int BATCH_SIZE = 10000;
	public void run(String source, int sec) {
		validation(source, sec);
		List<String[]> csv = importable.load(source); //timestamp,price,size
		batchJson(sec, csv);
	}

	private void batchJson(int sec, List<String[]> csv) {
		int idx = 0;
		while(idx < csv.size()) {
			List<String[]> trades = null;
			if (csv.size() < idx + BATCH_SIZE) {
				trades = csv.subList(idx, csv.size());
			} else {
				trades = csv.subList(idx, idx + BATCH_SIZE);
			}
			Candles candles = new Candles(sec, trades);
			exportable.export(candles.toJson());
		}
	}

	private void validation(String source, int sec) {
		if (!new File(source).canRead()) {
			throw new IllegalArgumentException("cannot read file in source path");
		}
		if (sec < 30 || sec > 86400) {
			throw new IllegalArgumentException("input period is not valid");
		}
	}
}
