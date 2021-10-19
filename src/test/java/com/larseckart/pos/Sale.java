package com.larseckart.pos;

class Sale {

  private final Display display;
  private final Catalog catalog;

  Sale(Display display, Catalog catalog) {
    this.display = display;
    this.catalog = catalog;
  }

  public void onBarcode(String barcode) {
    // SMELL: refused bequest; move up the call stack?
    if ("".equals(barcode)) {
      display.displayEmptyBarcodeMessage();
      return;
    }

    String priceAsText = catalog.findPrice(barcode);
    if (priceAsText == null) {
      display.displayProductNotFoundMessage(barcode);
    } else {
      display.displayPrice(priceAsText);
    }
  }

}
