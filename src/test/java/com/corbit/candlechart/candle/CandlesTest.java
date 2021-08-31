package com.corbit.candlechart.candle;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CandlesTest {

	@Test
	void candle1() {
		List<String[]> trades = new ArrayList<>(List.of(
				new String[]{"1383038122", "250000", "2.00000000"},
				new String[]{"1383038169", "254000", "0.09700000"},
				new String[]{"1383038169", "259000", "1.90300000"},
				new String[]{"1383038233", "251000", "1.39100000"}));
		Candles candles = new Candles(30, trades);
		String expected = "[\n" +
				"  {\n" +
				"    \"start\": 1383038122,\n" +
				"    \"end\": 1383038151,\n" +
				"    \"open\": \"250000\",\n" +
				"    \"close\": \"250000\",\n" +
				"    \"high\": \"250000\",\n" +
				"    \"low\": \"250000\",\n" +
				"    \"average\": \"250000\",\n" +
				"    \"weighted_average\": \"250,000\",\n" +
				"    \"volume\": \"2.00000000\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"start\": 1383038152,\n" +
				"    \"end\": 1383038181,\n" +
				"    \"open\": \"254000\",\n" +
				"    \"close\": \"259000\",\n" +
				"    \"high\": \"259000\",\n" +
				"    \"low\": \"254000\",\n" +
				"    \"average\": \"256500\",\n" +
				"    \"weighted_average\": \"258,758\",\n" +
				"    \"volume\": \"2.00000000\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"start\": 1383038182,\n" +
				"    \"end\": 1383038211,\n" +
				"    \"open\": null,\n" +
				"    \"close\": null,\n" +
				"    \"high\": null,\n" +
				"    \"low\": null,\n" +
				"    \"average\": null,\n" +
				"    \"weighted_average\": null,\n" +
				"    \"volume\": \"0.00000000\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"start\": 1383038212,\n" +
				"    \"end\": 1383038241,\n" +
				"    \"open\": \"251000\",\n" +
				"    \"close\": \"251000\",\n" +
				"    \"high\": \"251000\",\n" +
				"    \"low\": \"251000\",\n" +
				"    \"average\": \"251000\",\n" +
				"    \"weighted_average\": \"251,000\",\n" +
				"    \"volume\": \"1.39100000\"\n" +
				"  }\n" +
				"]";
		assertThat(candles.toJson()).isEqualTo(expected);
	}

}