package com.packt.thimblerig.api.dto;

import com.packt.thimblerig.domain.BetResult;

import java.math.BigDecimal;

public class BetReport {
  public final int playerPick;
  public final int prizePosition;
  public final BigDecimal prizeWon;

  public BetReport(BetResult result) {
    this.playerPick = result.pickedPosition;
    this.prizePosition = result.prizePosition;
    this.prizeWon = result.prizeWon;
  }

  public BetReport(int pick, int position, BigDecimal prize) {
    this.playerPick = pick;
    this.prizePosition = position;
    this.prizeWon = prize;
  }
}
