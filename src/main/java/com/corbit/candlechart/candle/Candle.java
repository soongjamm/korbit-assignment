package com.corbit.candlechart.candle;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.List;

public class Candle {
	private static final DecimalFormat VOLUME_FORMAT = new DecimalFormat("0.00000000");
	private static final DecimalFormat WEIGHTED_AVERAGE_FORMAT = new DecimalFormat("0,000");
	private static final int PRICE = 1;
	private static final int SIZE = 2;

	private int start;
	private int end;
	private String open;
	private String close;
	private String high;
	private String low;
	private String average;
	@SerializedName(value = "weighted_average")
	private String weightedAverage;
	private String volume;

	private Candle(int start, int end) {
		this.start = start;
		this.end = end;
		this.volume = VOLUME_FORMAT.format(0);
	}

	public static Candle from(int base, int period, List<String[]> targets) {
		Candle candle = new Candle(base, base + period - 1);
		int numberOfTrades = targets.size();
		if (numberOfTrades == 0) {
			return candle;
		}
		CandleCalculator candleCalculator = new CandleCalculator(targets);
		candle.open = targets.get(0)[PRICE];
		candle.close = targets.get(numberOfTrades - 1)[PRICE];
		candle.high = String.valueOf(candleCalculator.highestPrice);
		candle.low = String.valueOf(candleCalculator.lowestPrice);
		candle.average = String.valueOf(candleCalculator.getAverage());
		candle.volume = VOLUME_FORMAT.format(candleCalculator.volume);
		candle.weightedAverage = WEIGHTED_AVERAGE_FORMAT.format(candleCalculator.getWeightedAverage());
		return candle;
	}

	private static class CandleCalculator {
		private int tradeSize;
		private int highestPrice;
		private int lowestPrice;
		private int sum;
		private double volume;
		private double sumOfPriceMultiplySize;

		CandleCalculator(List<String[]> targets) {
			load(targets);
		}

		private void load(List<String[]> targets) {
			lowestPrice = Integer.MAX_VALUE;
			tradeSize = targets.size();
			for (String[] target : targets) {
				int price = Integer.parseInt(target[PRICE]);
				double size = Double.parseDouble(target[SIZE]);
				volume += size;
				sum += price;
				sumOfPriceMultiplySize += (price * size);
				highestPrice = Math.max(highestPrice, price);
				lowestPrice = Math.min(lowestPrice, price);
			}
		}

		public int getAverage() {
			return sum / tradeSize;
		}

		public int getWeightedAverage() {
			return (int)Math.round(sumOfPriceMultiplySize / volume);
		}
	}
}
