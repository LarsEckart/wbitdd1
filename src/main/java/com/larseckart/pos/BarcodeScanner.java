package com.larseckart.pos;

import java.io.InputStream;
import java.util.Scanner;

public class BarcodeScanner {

  private final Scanner scanner;

  public BarcodeScanner(InputStream inputStream) {
    scanner = new Scanner(inputStream);
  }

  public String scan() {
    return scanner.nextLine();
  }

  public Barcode scanBarcode() {
    String rawBarCode = scanner.nextLine();
    return Barcode.from(rawBarCode);
  }
}

