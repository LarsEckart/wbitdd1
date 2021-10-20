package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FormatMonetaryAmountTest {

  @ParameterizedTest
  @CsvSource({"$7.89,789"})
  void name(Integer priceInCents, String formattedPrice) {
    assertThat(formattedPrice).isEqualTo(format(priceInCents));
  }

  private String format(int priceInCents) {
    return "$7.89";
  }
}
