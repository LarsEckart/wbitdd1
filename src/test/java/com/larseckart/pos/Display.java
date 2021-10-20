package com.larseckart.pos;

class Display {

  private String text;

  public static String formatMonetaryAmount(int priceInCents) {
    return String.format("$%,.2f", priceInCents / 100.0d);
  }

  public String getText() {
    return text;
  }

  public void displayPrice(Integer priceInCents) {
    this.text = formatMonetaryAmount(priceInCents);
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
    this.text = "Total: " + formatMonetaryAmount(priceInCents);
  }
}
