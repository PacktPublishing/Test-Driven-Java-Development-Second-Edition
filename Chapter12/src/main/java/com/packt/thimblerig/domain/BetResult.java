package com.packt.thimblerig.domain;

import java.math.BigDecimal;

public class BetResult {
  public final int pickedPosition;
  public final int prizePosition;
  public final BigDecimal prizeWon;

  BetResult(int pickedPosition, int prizePosition, BigDecimal prizeWon) {
    this.pickedPosition = pickedPosition;
    this.prizePosition = prizePosition;
    this.prizeWon = prizeWon;
  }
}
