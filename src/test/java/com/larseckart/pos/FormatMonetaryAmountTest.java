package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(FastTestCommitRevertMainExtension.class)
public class FormatMonetaryAmountTest {

  @ParameterizedTest(name = "{0} -> {1}")
  @CsvSource({"789, $7.89, 520, $5.20"})
  void test(Integer priceInCents, String formattedPrice) {
    assertThat(formattedPrice).isEqualTo(format(priceInCents));
  }

  private String format(int priceInCents) {
    return String.format("$%.2f", priceInCents / 100.0d);
  }
}
