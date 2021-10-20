package com.larseckart.pos;

class Sale {

  private final Display display;
  private final Catalog catalog;
  private String price;

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

    price = catalog.findPrice(barcode);
    if (price == null) {
      display.displayProductNotFoundMessage(barcode);
    } else {
      display.displayPrice(price);
    }
  }

  public void onTotal() {
    boolean saleInProgress = price != null;
    if (saleInProgress) {
      display.displayPurchaseTotal(price);
    } else {
      display.displayNoSaleInProgressMessage();
    }
  }
}
