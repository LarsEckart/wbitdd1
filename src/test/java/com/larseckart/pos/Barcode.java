package com.larseckart.pos;

import java.util.Objects;

public final class Barcode {

  private final String barcode;

  public Barcode(String barcode) {
    this.barcode = barcode;
  }

  public String barcode() {
    return barcode;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Barcode) obj;
    return Objects.equals(this.barcode, that.barcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(barcode);
  }

  @Override
  public String toString() {
    return "Barcode[" +
        "barcode=" + barcode + ']';
  }


}
