package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
public class SellOneItemTest {

  private Display display;
  private Sale sale;

  @BeforeEach
  void setUp() {
    display = new Display();
    sale = new Sale(display, Map.of("12345", "$7.95", "23456", "$12.50"));
  }

  @Test
  void productFound() {
    sale.onBarcode("12345");

    assertThat("$7.95").isEqualTo(display.getText());
  }

  @Test
  void anotherProductFound() {
    sale.onBarcode("23456");

    assertThat("$12.50").isEqualTo(display.getText());
  }

  @Test
  void emptyBarcode() {
    Sale sale = new Sale(display, Collections.emptyMap());

    sale.onBarcode("");

    assertThat("Scanning error: empty barcode").isEqualTo(display.getText());
  }

  @Test
  void productNotFound() {
    Display display = new Display();
    Sale sale = new Sale(display, Map.of("12345", "$7.95", "23456", "$12.50"));

    sale.onBarcode("99999");

    assertThat("Product not found for 99999").isEqualTo(display.getText());
  }

  static class Display {

    private String text;

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }
  }

  static class Sale {

    private Display display;
    private Map<String, String> pricesByBarcode;

    Sale(Display display, Map<String, String> pricesByBarcode) {
      this.display = display;
      this.pricesByBarcode = pricesByBarcode;
    }

    public void onBarcode(String barcode) {
      // SMELL: refused bequest; move up the call stack?
      if ("".equals(barcode)) {
        displayEmptyBarcodeMessage();
        return;
      }

      if (pricesByBarcode.containsKey(barcode)) {
        findPriceThenDisplayPrice(barcode);
      } else {
        displayProductNotFoundMessage(barcode);
      }
    }

    private void findPriceThenDisplayPrice(String barcode) {
      String priceAsText = pricesByBarcode.get(barcode);
      display.setText(priceAsText);
    }

    private void displayProductNotFoundMessage(String barcode) {
      display.setText("Product not found for " + barcode);
    }

    private void displayEmptyBarcodeMessage() {
      display.setText("Scanning error: empty barcode");
    }
  }
}
