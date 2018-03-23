package com.packt.thimblerig.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Formatter;
import java.util.Locale;

@Service
public class ThimblerigService {
  private static final BigDecimal TWO = BigDecimal.valueOf(2);
  private RandomNumberGenerator randomNumberGenerator;

  @Autowired
  ThimblerigService(RandomNumberGenerator randomNumberGenerator) {
    this.randomNumberGenerator = randomNumberGenerator;
  }

  public BetResult placeBet(int position, BigDecimal betAmount) {
    checkPosition(position);
    checkBetAmount(betAmount);
    int winningPosition = randomNumberGenerator.getRandomInt();
    return new BetResult(
        position,
        winningPosition,
        position == winningPosition? betAmount.multiply(TWO): BigDecimal.ZERO);
  }

  private void checkPosition(int position) {
    if (position < 1 || position > 3) {
      throw new IllegalArgumentException("Invalid position <"
          + position
          + ">. Please pick one of [1, 2, 3]");
    }
  }

  private void checkBetAmount(BigDecimal betAmount) {
    if (BigDecimal.ZERO.compareTo(betAmount) >= 0) {
      Formatter formatter = new Formatter(Locale.US).format(
          "Invalid bet amount <%.2f>. Please place a bet of more than 0",
          betAmount
      );
      throw new IllegalArgumentException(formatter.toString());
    }
  }
}
