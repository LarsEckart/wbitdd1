package com.larseckart.pos;

class Sale {

  private final Display display;
  private final Catalog catalog;
  private String scannedPrice;

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

    scannedPrice = catalog.findPriceThenFormatPrice(barcode);
    if (scannedPrice == null) {
      display.displayProductNotFoundMessage(barcode);
    } else {
      display.displayPrice(formatPrice(scannedPrice));
    }
  }

  private String formatPrice(String scannedPrice) {
    return scannedPrice;
  }

  public void onTotal() {
    boolean saleInProgress = scannedPrice != null;
    if (saleInProgress) {
      display.displayPurchaseTotal(scannedPrice);
    } else {
      display.displayNoSaleInProgressMessage();
    }
  }
}
