package com.larseckart.math;

import java.util.Objects;

public final class Fraction {

  private final int numerator;
  private final Denominator denominator;

  private Fraction(int numerator, Denominator denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public static Fraction from(int integer) {
    return of(integer, new Denominator(1));
  }

  public static Fraction of(int numerator, Denominator denominator) {
    if (denominator.isNegative()) {
      denominator = denominator.revert();
      numerator = numerator * -1;
    }
    final int gcd = GCD.from(numerator, denominator.value());
    return new Fraction(numerator / gcd, new Denominator(denominator.value() / gcd));
  }

  public Fraction plus(Fraction other) {
    return Fraction.of(
        this.numerator * other.denominator.value() + denominator.value() * other.numerator,
        new Denominator(denominator.value() * other.denominator.value()));
  }

  public Fraction minus(Fraction other) {
    return Fraction.of(
        this.numerator * other.denominator.value() - denominator.value() * other.numerator,
        new Denominator(denominator.value() * other.denominator.value()));
  }

  public Fraction times(Fraction other) {
    return Fraction.of(this.numerator * other.numerator, new Denominator(denominator.value()
        * other.denominator.value()));
  }

  public Fraction divide(Fraction other) {
    return this.times(other.reciprocal());
  }

  private Fraction reciprocal() {
    return Fraction.of(denominator.value(), new Denominator(this.numerator));
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
    return numerator == fraction.numerator && denominator.value() == fraction.denominator.value();
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator.value());
  }

  @Override
  public String toString() {
    return "Fraction " + numerator + "/" + denominator.value();
  }

}
