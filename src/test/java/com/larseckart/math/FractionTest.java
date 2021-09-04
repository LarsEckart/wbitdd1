package com.larseckart.math;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
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
