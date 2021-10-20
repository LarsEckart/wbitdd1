package com.larseckart.pos;

class Display {

  private String text;

  public static String format(int priceInCents) {
    return String.format("$%,.2f", priceInCents / 100.0d);
  }

  public void displayPrice(Integer priceInCents) {
    this.text = format(priceInCents);
  }

  public String getText() {
    return text;
  }

  public void displayProductNotFoundMessage(String barcode) {
    this.text = "Product not found for " + barcode;
  }

  public void displayEmptyBarcodeMessage() {
    this.text = "Scanning error: empty barcode";
  }

  public void displayNoSaleInProgressMessage() {
    this.text = "No sale in progress. Try scanning a product.";
  }

  public void displayPurchaseTotal(Integer priceInCents) {
    this.text = "Total: " + format(priceInCents);
  }
}
