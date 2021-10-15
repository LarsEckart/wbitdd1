package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
public class SellOneItemTest {

  @Test
  void applesauce() {
    Display display = new Display();
    Sale sale = new Sale();

    sale.onBarcode("12345");

    assertThat("$7.95").isEqualTo(display.getText());
  }

  static class Display {

    public String getText() {
      return "$7.95";
    }
  }

  static class Sale {

    public void onBarcode(String barcode) {}
  }
}
