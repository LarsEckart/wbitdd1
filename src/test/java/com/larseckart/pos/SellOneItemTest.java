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
    sale.onBarcode(Barcode.from("12345").get());

    assertThat(display.getText()).isEqualTo("$7.95");
  }

  @Test
  void anotherProductFound() {
    sale.onBarcode(Barcode.from("23456").get());

    assertThat(display.getText()).isEqualTo("$12.50");
  }

  @Test
  void productNotFound() {
    Display display = new Display();
    Sale sale = new Sale(display, new Catalog(Map.of("12345", 795, "23456", 1250)));

    sale.onBarcode(Barcode.from("99999").get());

    assertThat(display.getText()).isEqualTo("Product not found for 99999");
  }
}
