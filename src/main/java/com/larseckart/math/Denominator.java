package com.larseckart.math;

record Denominator(int value) {

  Denominator {
    if (value == 0) {
      throw new IllegalArgumentException();
    }
  }

  Denominator divideBy(int gcd) {
    return new Denominator(value() / gcd);
  }

  boolean isNegative() {
    return this.value < 0;
  }

  Denominator revert() {
    return new Denominator(value * -1);
  }

  Denominator multiply(Denominator other) {
    return new Denominator(value * other.value);
  }

}
