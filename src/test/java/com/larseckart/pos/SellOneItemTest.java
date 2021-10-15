package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(FastTestCommitRevertMainExtension.class)
public class SellOneItemTest {

  @Test
  void productFound() {
    Display display = new Display();
    Sale sale = new Sale(display);

    sale.onBarcode("12345");

    assertThat("$7.95").isEqualTo(display.getText());
  }

  @Test
  @Disabled
  void anotherProductFound() {
    Display display = new Display();
    Sale sale = new Sale(display);

    sale.onBarcode("23456");

    assertThat("$12.50").isEqualTo(display.getText());
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

    Sale(Display display) {
      this.display = display;
    }

    public void onBarcode(String barcode) {
      display.setText("$7.95");
    }
  }
}
