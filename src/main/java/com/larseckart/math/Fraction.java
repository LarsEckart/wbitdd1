package com.larseckart.math;

import java.util.Objects;

public class Fraction {

  private final int integer;

  public Fraction(int integer) {
    this.integer = integer;
  }

  public static Fraction of(int numerator, int denominator) {
    return null;
  }

  public static Fraction from(int integer) {
    return new Fraction(integer);
  }

  public Fraction plus(Fraction other) {
    return Fraction.from(this.integer + other.integer);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Fraction fraction = (Fraction) o;
    return integer == fraction.integer;
  }

  @Override
  public int hashCode() {
    return Objects.hash(integer);
  }
}
