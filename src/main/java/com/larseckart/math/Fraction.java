package com.larseckart.math;

import java.util.Objects;

public class Fraction {

  private final int numerator;
  private final int denominator;

  public Fraction(int integer) {
    this(integer, 1);
  }

  public Fraction(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public static Fraction of(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException();
    }
    final int gcd = GCD.from(numerator, denominator);
    return new Fraction(numerator/gcd, denominator/gcd);
  }

  public static Fraction from(int integer) {
    return new Fraction(integer);
  }

  public Fraction plus(Fraction other) {
    return Fraction.of(this.numerator + other.numerator, denominator);
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
    return numerator == fraction.numerator && denominator == fraction.denominator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  @Override
  public String toString() {
    return "Fraction " + numerator + "/" + denominator;
  }
}
