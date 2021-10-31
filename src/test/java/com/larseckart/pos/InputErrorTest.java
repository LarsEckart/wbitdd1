package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class InputErrorTest {

  @Test
  void display_error_on_empty_barcode() {
    Display display = new Display();
    Sale sale = new Sale(display, null);

    sale.onBarcodeError();

    assertThat(display.getText()).isEqualTo("Scanning error: empty barcode");
  }
}
