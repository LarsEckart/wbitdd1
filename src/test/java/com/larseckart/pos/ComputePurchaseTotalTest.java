package com.larseckart.pos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ComputePurchaseTotalTest {

  @Test
  void zero_items() {
    assertThat(Sale.computePurchaseTotal(Collections.emptyList())).isEqualTo(0);
  }

  @Test
  void one_item() {
    assertThat(Sale.computePurchaseTotal(Collections.singleton(795))).isEqualTo(795);
  }

  @Test
  void several_items() {
    assertThat(Sale.computePurchaseTotal(List.of(850,1275,330))).isEqualTo(2455);
  }
}
