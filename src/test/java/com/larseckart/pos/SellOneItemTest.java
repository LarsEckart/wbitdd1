package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SellOneItemTest {

  private Display display;
  private Sale sale;

  @BeforeEach
  void setUp() {
    display = new Display();
    sale = new Sale(display, new Catalog(Map.of("12345", 795, "23456", 1250)));
  }

  @Test
  void productFound() {
    sale.onBarcode(new Barcode("12345"));

    assertThat("$7.95").isEqualTo(display.getText());
  }

  @Test
  void anotherProductFound() {
    sale.onBarcode(new Barcode("23456"));

    assertThat("$12.50").isEqualTo(display.getText());
  }

  @Test
  void emptyBarcode() {
    Sale sale = new Sale(display, new Catalog(Collections.emptyMap()));

    sale.onBarcode(new Barcode(""));

    assertThat("Scanning error: empty barcode").isEqualTo(display.getText());
  }

  @Test
  void productNotFound() {
    Display display = new Display();
    Sale sale = new Sale(display, new Catalog(Map.of("12345", 795, "23456", 1250)));

    sale.onBarcode(new Barcode("99999"));

    assertThat("Product not found for 99999").isEqualTo(display.getText());
  }
}
