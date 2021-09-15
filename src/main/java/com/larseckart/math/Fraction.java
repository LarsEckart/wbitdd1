package com.larseckart.math;

import java.util.Objects;

public final class Fraction {

  private final int numerator;
  private final int denominator;

  private Fraction(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public static Fraction from(int integer) {
    return of(integer, 1);
  }

  public static Fraction of(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException();
    }
    if (denominator < 0) {
      denominator = denominator * -1;
      numerator = numerator * -1;
    }
    final int gcd = GCD.from(numerator, denominator);
    return new Fraction(numerator / gcd, denominator / gcd);
  }

  public Fraction plus(Fraction other) {
    return Fraction.of(
        this.numerator * other.getDenominator() + this.getDenominator() * other.numerator,
        this.getDenominator() * other.getDenominator());
  }

  public Fraction minus(Fraction other) {
    return Fraction.of(
        this.numerator * other.getDenominator() - this.getDenominator() * other.numerator,
        this.getDenominator() * other.getDenominator());
  }

  public Fraction times(Fraction other) {
    return Fraction.of(this.numerator * other.numerator, this.getDenominator()
        * other.getDenominator());
  }

  public Fraction divide(Fraction other) {
    return this.times(other.reciprocal());
  }

  private Fraction reciprocal() {
    return Fraction.of(this.getDenominator(), this.numerator);
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
    return numerator == fraction.numerator && getDenominator() == fraction.getDenominator();
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, getDenominator());
  }

  @Override
  public String toString() {
    return "Fraction " + numerator + "/" + getDenominator();
  }

  public int getDenominator() {
    return denominator;
  }
}
