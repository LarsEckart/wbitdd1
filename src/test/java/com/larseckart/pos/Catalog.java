package com.larseckart.pos;

import java.util.Map;

public final class Catalog {

  private final Map<String, Integer> pricesInCentsByBarcode;

  public Catalog(Map<String, Integer> pricesInCentsByBarcode) {
    this.pricesInCentsByBarcode = pricesInCentsByBarcode;
  }

  public static String format(int priceInCents) {
    return String.format("$%,.2f", priceInCents / 100.0d);
  }

  public String findPriceThenFormatPrice(String barcode) {
    Integer priceInCents = pricesInCentsByBarcode.get(barcode);
    if (priceInCents == null) {
      return null;
    }
    return format(priceInCents);
  }
}
