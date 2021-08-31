package com.corbit.candlechart;

import com.corbit.candlechart.candle.Candles;
import com.corbit.candlechart.utility.exporter.Exportable;
import com.corbit.candlechart.utility.importer.Importable;

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
	public void run(String source, int sec) {
		List<String[]> trades = importable.load(source); //timestamp,price,size
		Candles candles = new Candles(sec, trades);
		exportable.export(candles.toJson());
	}
}
