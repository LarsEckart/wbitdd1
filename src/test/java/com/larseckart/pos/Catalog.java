package com.larseckart.pos;

import java.util.Map;

public final class Catalog {

  private final Map<String, String> pricesByBarcode;

  public Catalog(Map<String, String> pricesByBarcode) {
    this.pricesByBarcode = pricesByBarcode;
  }

  public Map<String, String> pricesByBarcode() {
    return pricesByBarcode;
  }
}
