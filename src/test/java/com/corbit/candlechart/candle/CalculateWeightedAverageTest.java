package com.corbit.candlechart.candle;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateWeightedAverageTest {

	private static final int IDX_PRICE = 1;
	private static final int IDX_SIZE = 2;

	@Test
	void calculate_weighted_average() {
		String[] aTrade = {"1383038122", "250000", "2.00000000"};
		List<String[]> targets = new ArrayList<>();
		targets.add(aTrade);
		double volume = targets.stream().mapToDouble(x -> Double.parseDouble(x[IDX_SIZE])).sum();
		double weightedAverage = targets.stream().mapToDouble(x -> Integer.parseInt(x[IDX_PRICE]) * Double.parseDouble(x[IDX_SIZE])).sum() / volume;

		assertThat(weightedAverage).isEqualTo(250000);
	}


	@Test
	void calculate_weighted_average2() {
		List<String[]> targets = List.of(
				new String[]{"1383038169","254000","0.09700000"},
				new String[]{"1383038169","259000","1.90300000"});
		double volume = targets.stream().mapToDouble(x -> Double.parseDouble(x[IDX_SIZE])).sum();
		double weightedAverage = targets.stream().mapToDouble(x -> Integer.parseInt(x[IDX_PRICE]) * Double.parseDouble(x[IDX_SIZE])).sum() / volume;

		assertThat(weightedAverage).isCloseTo(258758, Offset.offset(Double.valueOf(0.5)));
	}

	@Test
	void calculate_weighted_average3() {
		String[] aTrade = {"1383038233", "251000", "1.39100000"};
		List<String[]> targets = new ArrayList<>();
		targets.add(aTrade);
		double volume = targets.stream().mapToDouble(x -> Double.parseDouble(x[IDX_SIZE])).sum();
		double weightedAverage = targets.stream().mapToDouble(x -> Integer.parseInt(x[IDX_PRICE]) * Double.parseDouble(x[IDX_SIZE])).sum() / volume;

		assertThat(weightedAverage).isEqualTo(251000);
	}
}
