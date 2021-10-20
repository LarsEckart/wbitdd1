package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class FormatMonetaryAmountTest {

  static Stream<Arguments> arguments() {
    return Stream.of(
        Arguments.of(789, "$7.89"),
        Arguments.of(520, "$5.20"),
        Arguments.of(0, "$0.00"),
        Arguments.of(2, "$0.02"),
        Arguments.of(40, "$0.40"),
        Arguments.of(418976, "$4,189.76"));
  }

  @ParameterizedTest(name = "{0} -> {1}")
  @MethodSource("arguments")
  @CsvSource({"789, $7.89, " + "520, $5.20"})
  void test(Integer priceInCents, String formattedPrice) {
    assertThat(formattedPrice).isEqualTo(Display.formatMonetaryAmount(priceInCents));
  }
}
