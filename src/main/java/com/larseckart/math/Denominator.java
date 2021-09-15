package com.larseckart.math;

record Denominator(int value){

  Denominator(int value) {
    if (value == 0) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }
}
