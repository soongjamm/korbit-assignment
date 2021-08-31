package com.corbit.candlechart.candle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Candles {
	private static final int TIMESTAMP = 0;
	private final List<Candle> candles = new ArrayList<>();
	private final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	/**
	 * String 배열을 Candle 타입으로 변환한다.
	 *  @param period 거래의 기간
	 * @param trades {timestamp,price,size} 으로 저장된 String[]
	 */
	public Candles(int period, List<String[]> trades) {
		setCandles(period, trades);
	}

	public String toJson() {
		return gson.toJson(this.candles);
	}

	public int size() {
		return this.candles.size();
	}

	private void setCandles(int period, List<String[]> trades) {
		int firstTradeTime = Integer.parseInt(trades.get(0)[TIMESTAMP]);
		int lastTradeTime = Integer.parseInt(trades.get(trades.size() - 1)[TIMESTAMP]);
		int endOfCandlesTime = (((lastTradeTime - firstTradeTime) / period) + 1) * period + firstTradeTime;
		for (int base = firstTradeTime; base < endOfCandlesTime; base += period) {
			candles.add(Candle.from(base, period, getTradesByPeriod(trades, base, period)));
		}
	}

	private List<String[]> getTradesByPeriod(List<String[]> trades, int base, int period) {
		List<String[]> tradesInPeriod = new ArrayList<>();
		while (!trades.isEmpty()) {
			String[] current = trades.get(0);
			current[1] = toKRW(current[1]);
			int currentTime = Integer.parseInt(current[TIMESTAMP]);
			int nextBase = base + period;
			if (currentTime >= nextBase) {
				break;
			}
			tradesInPeriod.add(current);
			trades.remove(0);
		}
		return tradesInPeriod;
	}

	private String toKRW(String price) {
		if (price.contains(".")) {
			return price.substring(0, price.indexOf("."));
		}
		return price;
	}
}
