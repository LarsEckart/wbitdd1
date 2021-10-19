package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.larseckart.tcr.FastTestCommitRevertMainExtension;
import java.util.Collections;
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
    Sale sale = new Sale(display, new Catalog(Collections.emptyMap()));

    sale.onTotal();

    assertThat(display.getText()).isEqualTo("No sale in progress. Try scanning a product.");
  }
}
