package com.larseckart.pos;

import java.util.Map;

public final class Catalog {

  private Map<String, String> pricesAsTextByBarcode;
  private Map<String, Integer> pricesInCentsByBarcode;

  public Catalog(Map<String, String> pricesAsTextByBarcode) {
    this.pricesAsTextByBarcode = pricesAsTextByBarcode;
  }

  public Catalog(Map<String, String> pricesAsTextByBarcode, Map<String, Integer> pricesInCentsByBarcode) {
    this.pricesAsTextByBarcode = pricesAsTextByBarcode;
    this.pricesInCentsByBarcode = pricesInCentsByBarcode;
  }

  public String findPriceThenFormatPrice(String barcode) {
    return pricesAsTextByBarcode.get(barcode);
  }
}
