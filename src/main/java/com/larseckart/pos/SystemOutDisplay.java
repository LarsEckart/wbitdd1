package com.larseckart.pos;

import java.io.PrintStream;

public class SystemOutDisplay extends Display {

  private PrintStream printStream;

  public SystemOutDisplay(PrintStream printStream) {
    this.printStream = printStream;
  }

  @Override
  public void displayPrice(Integer priceInCents) {
    super.displayPrice(priceInCents);
    printStream.println(getText());
  }

  @Override
  public void displayProductNotFoundMessage(String barcode) {
    super.displayProductNotFoundMessage(barcode);
    System.out.println(getText());
  }

  @Override
  public void displayEmptyBarcodeMessage() {
    super.displayEmptyBarcodeMessage();
    System.out.println(getText());
  }

  @Override
  public void displayNoSaleInProgressMessage() {
    super.displayNoSaleInProgressMessage();
    System.out.println(getText());
  }

  @Override
  public void displayPurchaseTotal(Integer priceInCents) {
    super.displayPurchaseTotal(priceInCents);
    System.out.println(getText());
  }
}
