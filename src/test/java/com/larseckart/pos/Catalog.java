package com.larseckart.pos;

import java.util.Map;

public record Catalog(Map<String, String> pricesByBarcode) {

}
