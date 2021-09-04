package com.larseckart.math;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
class FractionTest {

  @Test
  @Disabled("just an exercise about the api of fraction")
  void my_first_test() {
    assertThat(Fraction.of(7, 3).plus(Fraction.of(4, 5))).isEqualTo(Fraction.of(47, 5));
  }

  @Test
  void zero_plus_zero() {
    assertThat(Fraction.from(0).plus(Fraction.from(0))).isEqualTo(Fraction.from(0));
  }

  @Test
  void equals() {
    assertThat(Fraction.from(1)).isEqualTo(Fraction.from(1));
  }

  @Test
  void not_equal_to_different_fraction() {
    assertThat(Fraction.from(1)).isNotEqualTo(Fraction.from(0));
  }

  @Test
  void one_plus_zero() {
    assertThat(Fraction.from(1).plus(Fraction.from(0))).isEqualTo(Fraction.from(1));
  }

  @Test
  void one_plus_one() {
    assertThat(Fraction.from(1).plus(Fraction.from(1))).isEqualTo(Fraction.from(2));
  }
}
