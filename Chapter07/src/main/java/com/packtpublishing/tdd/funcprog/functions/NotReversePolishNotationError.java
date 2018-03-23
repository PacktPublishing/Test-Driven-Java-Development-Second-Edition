package com.packtpublishing.tdd.funcprog.functions;

public class NotReversePolishNotationError extends RuntimeException {
  public NotReversePolishNotationError() {
    super("Not a Reverse Polish Notation");
  }
}
