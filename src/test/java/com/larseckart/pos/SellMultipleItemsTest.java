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
    Sale sale = new Sale(display, new Catalog(Collections.emptyMap(),Collections.emptyMap()));

    sale.onTotal();

    assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
  }

  @Test
  void oneItemFound() {
    Display display = new Display();
    Catalog catalog = new Catalog(Map.of("12345", "$6.50"), Map.of("12345", 650));
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("12345");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("Total: $6.50");
  }

  @Test
  void noItemFound() {
    Display display = new Display();
    Catalog catalog = new Catalog(Map.of("12345", "$6.50"), Map.of("12345", 650));
    Sale sale = new Sale(display, catalog);

    sale.onBarcode("99999");
    sale.onTotal();

    assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
  }

  @Disabled("make the change easy, then make the easy change")
  @Test
  void several_items_found() {
    Display display = new Display();
    Catalog catalog =
        new Catalog(
            Map.of(
                "1", "$8.50",
                "2", "$12.75",
                "3", "$3.30"),
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
}
