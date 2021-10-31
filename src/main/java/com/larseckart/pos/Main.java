package com.larseckart.pos;

import java.util.Map;

public class Main {

  public static void main(String[] args) {
    BarcodeScanner bcs = new BarcodeScanner(System.in);
    Sale sale = new Sale(new SystemOutDisplay(System.out), new Catalog(Map.of(
        "1", 850,
        "2", 1275,
        "3", 330)));
    boolean saleOngoing = true;
    while (saleOngoing) {
      displayAvailableForNextCommand();
      String action = bcs.scan();
      if ("1".equals(action)) {
        displayAskForBarcode();
        try {
          Barcode barcode = bcs.scanBarcode();
          sale.onBarcode(barcode);
        } catch (IllegalArgumentException e) {
          sale.onBarcodeError();
        }
      } else if ("2".equals(action)) {
        sale.onTotal();
        saleOngoing = false;
      }
    }
    System.out.println("Ende");
  }

  // additional behaviour for Display?
  private static void displayAvailableForNextCommand() {
    System.out.println("Please enter 1 for scanning a barcode or 2 for onTotal");
  }

  // additional behaviour for Display?
  private static void displayAskForBarcode() {
    System.out.print("Barcode: ");
  }
}
