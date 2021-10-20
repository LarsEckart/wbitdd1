package com.larseckart.pos;

import java.util.Map;

public final class Catalog {

  private Map<String, String> pricesByBarcode;
  private Map<String, Integer> pricesInCentsByBarcode;

  public Catalog(Map<String, String> pricesByBarcode) {
    this.pricesByBarcode = pricesByBarcode;
  }

  public Catalog(Map<String, String> pricesByBarcode, Map<String, Integer> pricesInCentsByBarcode) {
    this.pricesByBarcode = pricesByBarcode;
    this.pricesInCentsByBarcode = pricesInCentsByBarcode;
  }

  public String findPriceThenFormatPrice(String barcode) {
    return pricesByBarcode.get(barcode);
  }
}
