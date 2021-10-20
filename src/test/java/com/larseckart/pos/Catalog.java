package com.larseckart.pos;

import java.util.Map;

public final class Catalog {

  private final Map<String, Integer> pricesInCentsByBarcode;

  public Catalog(Map<String, Integer> pricesInCentsByBarcode) {
    this.pricesInCentsByBarcode = pricesInCentsByBarcode;
  }

  public Integer findPrice(String barcode) {
    Integer priceInCents = pricesInCentsByBarcode.get(barcode);
    return priceInCents;
  }
}
