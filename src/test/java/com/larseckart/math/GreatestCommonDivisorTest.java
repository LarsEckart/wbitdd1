package com.larseckart.math;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class GreatestCommonDivisorTest {

  @Test
  void gcd() {
    assertThat(gcd(48,180)).isEqualTo(12);
  }

  private int gcd(int first, int second) {
    return 0;
  }
}
