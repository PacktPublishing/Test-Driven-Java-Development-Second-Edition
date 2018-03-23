package com.packt.thimblerig.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThimblerigServiceTest {
  @Rule
  public ExpectedException exception = ExpectedException.none();

  // Mocks
  private RandomNumberGenerator mockedRandom = mock(RandomNumberGenerator.class);

  private ThimblerigService thimblerigService;

  @Before
  public void setUp() {
    thimblerigService = new ThimblerigService(mockedRandom);
  }

  @Test
  public void placingBetDoesNotAcceptPositionsLessThanOne() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Invalid position <0>. Please pick one of [1, 2, 3]");
    thimblerigService.placeBet(0, BigDecimal.ONE);
  }

  @Test
  public void placingBetDoesNotAcceptPositionsGreaterThan3() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Invalid position <4>. Please pick one of [1, 2, 3]");
    thimblerigService.placeBet(4, BigDecimal.ONE);
  }

  @Test
  public void placingBetOnlyAcceptsAmountsGreaterThanZero() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Invalid bet amount <0.00>. Please place a bet of more than 0");
    thimblerigService.placeBet(1, BigDecimal.ZERO);
  }

  @Test
  public void onFailedBetThePrizeIsZero() {
    when(mockedRandom.getRandomInt()).thenReturn(2);
    BetResult result = thimblerigService.placeBet(1, BigDecimal.TEN);
    assertThat(result.pickedPosition, equalTo(1));
    assertThat(result.prizePosition, equalTo(2));
    assertThat(result.prizeWon, equalTo(BigDecimal.ZERO));
  }

  @Test
  public void whenThePositionIsGuessedCorrectlyThePrizeIsDoubleTheBet() {
    when(mockedRandom.getRandomInt()).thenReturn(1);
    BetResult result = thimblerigService.placeBet(1, BigDecimal.TEN);
    assertThat(result.pickedPosition, equalTo(1));
    assertThat(result.prizePosition, equalTo(1));
    assertThat(result.prizeWon, equalTo(BigDecimal.valueOf(20)));
  }
}
