package com.larseckart.pos;

class Display {

  public String text;

  public static String format(int priceInCents) {
    return String.format("$%,.2f", priceInCents / 100.0d);
  }

  public void displayPrice(Integer priceInCents) {
    displayText(format(priceInCents));
  }

  public String getText() {
    return text;
  }

  // SMELL: we say we display Price but we display a String
  public void displayText(String priceAsText) {
    this.text = priceAsText;
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

  public void displayPurchaseTotal(String price) {
    this.text = "Total: " + price;
  }
}
