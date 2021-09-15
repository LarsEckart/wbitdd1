package com.larseckart.math;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
class FractionTest {

  @Test
  void my_first_test() {
    assertThat(Fraction.of(7, new Denominator(3)).plus(Fraction.of(4, new Denominator(5))))
        .isEqualTo(Fraction.of(47, new Denominator(15)));
  }

  @ParameterizedTest
  @CsvSource({"0,0,0", "1,0,1", "1,1,2"})
  void addition_for_whole_numbers(int first, int second, int result) {
    assertThat(Fraction.from(first).plus(Fraction.from(second))).isEqualTo(Fraction.from(result));
  }

  @Test
  void equality() {
    assertAll(
        () -> assertThat(Fraction.from(1)).isEqualTo(Fraction.from(1)),
        () -> assertThat(Fraction.of(1, new Denominator(1))).isEqualTo(Fraction.from(1)),
        () -> assertThat(Fraction.of(3, new Denominator(1))).isEqualTo(Fraction.from(3)));
    //    EqualsVerifier.forClass(Fraction.class).verify();
  }

  @Test
  void fractions_are_always_expressed_at_lowest_term() {
    assertAll(
        () -> assertThat(Fraction.of(4, new Denominator(2))).isEqualTo(Fraction.from(2)),
        () ->
            assertThat(Fraction.of(2, new Denominator(4)))
                .isEqualTo(Fraction.of(1, new Denominator(2))));
  }

  @Test
  void adding_two_fractions_with_same_denominator() {
    assertAll(
        () ->
            assertThat(Fraction.of(1, new Denominator(4)).plus(Fraction.of(1, new Denominator(4))))
                .isEqualTo(Fraction.of(1, new Denominator(2))),
        () ->
            assertThat(Fraction.of(1, new Denominator(3)).plus(Fraction.of(2, new Denominator(3))))
                .isEqualTo(Fraction.from(1)));
  }

  @Test
  void subtracting_two_fractions_with_same_denominator() {
    assertAll(
        () ->
            assertThat(Fraction.of(2, new Denominator(4)).minus(Fraction.of(1, new Denominator(4))))
                .isEqualTo(Fraction.of(1, new Denominator(4))),
        () ->
            assertThat(Fraction.of(2, new Denominator(3)).minus(Fraction.of(1, new Denominator(3))))
                .isEqualTo(Fraction.of(1, new Denominator(3))));
  }

  @Test
  void adding_two_fractions_with_different_denominator() {
    assertAll(
        () ->
            assertThat(Fraction.of(1, new Denominator(4)).plus(Fraction.of(1, new Denominator(2))))
                .isEqualTo(Fraction.of(3, new Denominator(4))),
        () ->
            assertThat(Fraction.of(4, new Denominator(3)).plus(Fraction.of(1, new Denominator(2))))
                .isEqualTo(Fraction.of(11, new Denominator(6))));
  }

  @Test
  void subtracting_two_fractions_with_different_denominator() {
    assertThat(Fraction.of(2, new Denominator(3)).minus(Fraction.of(1, new Denominator(2))))
        .isEqualTo(Fraction.of(1, new Denominator(6)));
  }

  @Test
  void multiply_two_fractions_with_different_denominator() {
    assertThat(Fraction.of(2, new Denominator(3)).times(Fraction.of(3, new Denominator(4))))
        .isEqualTo(Fraction.of(1, new Denominator(2)));
  }

  @Test
  void divide_two_fractions_with_different_denominator() {
    assertThat(Fraction.of(1, new Denominator(2)).divide(Fraction.of(3, new Denominator(4))))
        .isEqualTo(Fraction.of(2, new Denominator(3)));
  }

  @Test
  void negative_fractions_equality() {
    assertAll(
        () -> assertThat(Fraction.of(-1, new Denominator(1))).isEqualTo(Fraction.from(-1)),
        () -> assertThat(Fraction.of(1, new Denominator(-1))).isEqualTo(Fraction.from(-1)),
        () -> assertThat(Fraction.of(-1, new Denominator(-1))).isEqualTo(Fraction.from(1)));
  }

  @Nested
  class DenominatorTest {

    @Test
    void denominator_of_zero_causes_exception() {
      assertThrows(IllegalArgumentException.class, () -> new Denominator(0));
    }
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
