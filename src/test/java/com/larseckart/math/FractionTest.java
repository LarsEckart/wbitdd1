package com.larseckart.math;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.AbstractBigDecimalAssert;
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
  @Disabled("implement GCD first")
  void lowest_term() {
    assertThat(Fraction.of(4, 2)).isEqualTo(Fraction.from(2));
  }

  @Nested
  class GreatestCommonDivisor {

    @Test
    void divisors_of_a_number() {
      assertThat(divisors(6)).isEqualTo(Set.of(1, 2, 3));
    }

    @Test
    void common_divisors() {
      assertThat(commonDivisors(divisors(6), divisors(9))).isEqualTo(Set.of(3, 1));
      assertThat(divisors(6)).isEqualTo(Set.of(1, 2, 3));
    }

    private  Set<Integer> commonDivisors(Collection<Integer> divisors, Collection<Integer> divisors1) {
      return divisors.stream()
          .distinct()
          .filter(divisors1::contains)
          .collect(Collectors.toSet());
    }

    private Set<Integer> divisors(int number) {
      Set<Integer> result = new HashSet<>();
      for (int i = number -1; i > 0; i--) {
        if (number % i == 0) {
          result.add(i);
        }
      }
      return result;
    }
  }
}
