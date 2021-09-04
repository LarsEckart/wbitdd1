package com.larseckart.math;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
class FractionTest {

  @Test
  @Disabled("just an exercise about the api of fraction")
  void my_first_test() {
    assertThat(Fraction.of(7, 3).plus(Fraction.of(4, 5))).isEqualTo(Fraction.of(47, 5));
  }

  @ParameterizedTest
  @CsvSource({"0,0,0", "1,0,1"})
  void addition_for_whole_numbers(int first, int second, int result) {
    assertThat(Fraction.from(first).plus(Fraction.from(second))).isEqualTo(Fraction.from(result));
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
    assertThat(Fraction.of(1, 1).plus(Fraction.from(1))).isEqualTo(Fraction.from(2));
  }

  @Test
  void fraction_with_numerator_and_denominator_of_1_is_equal_to_fraction_from_whole_number() {
    Assertions.assertAll(
        () -> assertThat(Fraction.of(1, 1)).isEqualTo(Fraction.from(1)),
        () -> assertThat(Fraction.of(3, 1)).isEqualTo(Fraction.from(3)));
  }

  @Test
  void denominator_of_zero_causes_exception() {
    assertThrows(IllegalArgumentException.class, () -> Fraction.of(1, 0));
  }

  @Test
  void lowest_term() {
    Assertions.assertAll(
        () -> assertThat(Fraction.of(4, 2)).isEqualTo(Fraction.from(2)),
        () -> assertThat(Fraction.of(2, 4)).isEqualTo(Fraction.of(1, 2)));
  }

  @Test
  void adding_two_fractions_with_same_denominator() {
    assertThat(Fraction.of(1, 4).plus(Fraction.of(1, 4)))
        .isEqualTo(Fraction.of(1, 2));
    assertThat(Fraction.of(1, 3).plus(Fraction.of(2, 3)))
        .isEqualTo(Fraction.from(1));
  }

  @Test
  void adding_two_fractions_with_different_denominator() {
    assertThat(Fraction.of(1, 4).plus(Fraction.of(1, 2)))
        .isEqualTo(Fraction.of(3, 4));
    assertThat(Fraction.of(4, 3).plus(Fraction.of(1, 2)))
        .isEqualTo(Fraction.of(11, 6));
  }

  @Nested
  class GreatestCommonDivisor {

    @Test
    void divisors_of_a_number() {
      assertThat(GCD.divisors(6)).isEqualTo(Set.of(1, 2, 3, 6));
    }

    @Test
    void gcd_as_class() {
      assertThat(GCD.from(54, 24)).isEqualTo(6);
    }

    @Test
    void common_divisors() {
      assertThat(GCD.divisors(6)).isEqualTo(Set.of(1, 2, 3, 6));
      assertThat(GCD.commonDivisors(GCD.divisors(6), GCD.divisors(9))).isEqualTo(Set.of(3, 1));
      assertThat(GCD.max(GCD.commonDivisors(GCD.divisors(6), GCD.divisors(9)))).isEqualTo(3);
    }
  }
}
