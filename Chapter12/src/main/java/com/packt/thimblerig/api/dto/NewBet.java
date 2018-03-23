package com.packt.thimblerig.api.dto;

import java.math.BigDecimal;

public class NewBet {
  private int pick;

  private BigDecimal amount;

  public NewBet() {
  }

  public NewBet(int pick, BigDecimal amount) {
    this.pick = pick;
    this.amount = amount;
  }

  public int getPick() {
    return pick;
  }

  public void setPick(int pick) {
    this.pick = pick;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
