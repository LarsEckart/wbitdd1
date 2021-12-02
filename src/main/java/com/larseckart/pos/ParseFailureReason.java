package com.larseckart.pos;

class ParseFailureReason {

  private final String message;

  public ParseFailureReason(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
