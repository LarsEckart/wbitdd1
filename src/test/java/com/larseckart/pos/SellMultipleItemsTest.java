package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
public class SellMultipleItemsTest {

  @Test
  void zeroItems() {
    Display display = new Display();
    Sale sale = new Sale(display, emptyCatalog());

    sale.onTotal();

    assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
  }

  @Test
  void oneItemFound() {
    Display display = new Display();
    Catalog catalog = new Catalog(Map.of("12345", 650));
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("12345");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("Total: $6.50");
  }

  @Test
  void noItemFound() {
    Display display = new Display();
    Catalog catalog = new Catalog(Map.of("12345", 650));
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("99999");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
  }

  @Test
  void severalItemsNotFound() {
    Display display = new Display();
    Catalog catalog =
        catalogWithoutBarcodes(
            "product you won't find",
            "another product you won't find",
            "a third product you won't find");
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("product you won't find");
    sale.onBarcode("another product you won't find");
    sale.onBarcode("a third product you won't find");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
  }

  private Catalog catalogWithoutBarcodes(String... barcodes) {
    return emptyCatalog();
  }

  private Catalog emptyCatalog() {
    return new Catalog(Collections.emptyMap());
  }

  @Test
  void several_items_found() {
    Display display = new Display();
    Catalog catalog =
        new Catalog(
            Map.of(
                "1", 850,
                "2", 1275,
                "3", 330));
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("1");
    sale.onBarcode("2");
    sale.onBarcode("3");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("Total: $24.55");
  }

  @Test
  void several_items_found_some_not() {
    Display display = new Display();
    Catalog catalog =
        new Catalog(
            Map.of(
                "1", 1200,
                "2", 500));
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("1");
    sale.onBarcode("you don't know this product");
    sale.onBarcode("2");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("Total: $17.00");
  }
}
