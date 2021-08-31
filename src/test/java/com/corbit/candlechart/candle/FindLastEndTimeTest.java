package com.corbit.candlechart.candle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FindLastEndTimeTest {
	@ParameterizedTest
	@CsvSource(value = {
			"30, 10000, 10050, 10060",
			"30, 10000, 10080, 10090",
			"30, 10000, 10070, 10090",
			"30, 10000, 10088, 10090",
			"30, 10000, 10140, 10150",
			"60, 10000, 10540, 10600",
			"100, 10000, 10510, 10600",})
	@DisplayName("실제 사용할 때, xx < lastEnd 의 lastEnd를 구한다. " +
			"비교시 자동으로 마지막 숫자는 걸러지므로, 테스트에서는 lastEnd + 1 한 값을 구한다.")
	void find_last_end_plus_1(int period, int start, int last, int expected) {
		int limitTime = ((last - start) / period + 1) * period + start;
		assertThat(limitTime).isEqualTo(expected);
		assertThat(limitTime).isEqualTo(last + period);
	}



}