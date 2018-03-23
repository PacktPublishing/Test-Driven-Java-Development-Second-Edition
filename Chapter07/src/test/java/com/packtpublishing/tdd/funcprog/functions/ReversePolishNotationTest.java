package com.packtpublishing.tdd.funcprog.functions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReversePolishNotationTest {

  private ReversePolishNotation reversePolishNotation =
      new ReversePolishNotation();

  @Test(expected = NotReversePolishNotationError.class)
  public void emptyInputThrowsError() {
    reversePolishNotation.compute("");
  }

  @Test(expected = NotReversePolishNotationError.class)
  public void notANumberThrowsError() {
    reversePolishNotation.compute("a");
  }

  @Test
  public void oneDigitReturnsNumber() {
    assertThat(reversePolishNotation.compute("0")).isEqualTo(0);
  }

  @Test
  public void moreThanOneDigitReturnsNumber() {
    assertThat(reversePolishNotation.compute("120")).isEqualTo(120);
  }

  @Test
  public void addOperationReturnsCorrectValue() {
    assertThat(reversePolishNotation.compute("1 2 +")).isEqualTo(3);
  }

  @Test
  public void subtractOperationReturnsCorrectValue() {
    assertThat(reversePolishNotation.compute("2 1 -")).isEqualTo(1);
  }

  @Test
  public void multiplyOperationReturnsCorrectValue() {
    assertThat(reversePolishNotation.compute("2 1 *")).isEqualTo(2);
  }

  @Test
  public void divideOperationReturnsCorrectValue() {
    assertThat(reversePolishNotation.compute("2 2 /")).isEqualTo(1);
  }

  @Test
  public void multipleAddOperationsReturnCorrectValue() {
    assertThat(reversePolishNotation.compute("1 2 5 + +")).isEqualTo(8);
  }

  @Test
  public void multipleDifferentOperationsReturnCorrectValue() {
    assertThat(reversePolishNotation.compute("5 12 + 3 -")).isEqualTo(14);
  }

  @Test
  public void aComplexTest() {
    assertThat(reversePolishNotation.compute("5 1 2 + 4 * + 3 -")).isEqualTo(14);
  }
}