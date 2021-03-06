package com.larseckart.pos;

import java.util.ArrayList;
import java.util.Collection;

class Sale {

  private final Display display;
  private final Catalog catalog;
  private Collection<Integer> pendingPurchaseItemPrices = new ArrayList<>();

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

    Integer priceInCents = catalog.findPrice(barcode);
    if (priceInCents == null) {
      display.displayProductNotFoundMessage(barcode);
    } else {
      // REFACTOR: is this a shopping cart?
      pendingPurchaseItemPrices.add(priceInCents);
      display.displayPrice(priceInCents);
    }
  }

  public void onTotal() {
    // SMELL: looks like model behaviour
    boolean saleInProgress = !pendingPurchaseItemPrices.isEmpty();
    if (saleInProgress) {
      display.displayPurchaseTotal(pendingPurchaseTotal());
    } else {
      display.displayNoSaleInProgressMessage();
    }
  }

  // SMELL: looks like model behaviour
  private Integer pendingPurchaseTotal() {
    return computePurchaseTotal(pendingPurchaseItemPrices);
  }

  public static Integer computePurchaseTotal(Collection<Integer> pendingPurchaseItemPrices) {
    if (pendingPurchaseItemPrices.isEmpty()) {
      return 0;
    } else if (pendingPurchaseItemPrices.size() == 1) {
      return pendingPurchaseItemPrices.iterator().next();
    } else return pendingPurchaseItemPrices.stream().mapToInt(i -> i).sum();
  }
}
