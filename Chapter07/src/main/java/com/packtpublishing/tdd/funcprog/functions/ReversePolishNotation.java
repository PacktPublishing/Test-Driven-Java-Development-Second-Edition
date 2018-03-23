package com.packtpublishing.tdd.funcprog.functions;

import java.util.Stack;
import java.util.function.BinaryOperator;

public class ReversePolishNotation {

  static BinaryOperator<Integer> ADD = (a, b) -> a + b;
  static BinaryOperator<Integer> SUBTRACT = (a, b) -> a - b;
  static BinaryOperator<Integer> MULTIPLY = (a, b) -> a * b;
  static BinaryOperator<Integer> DIVIDE = (a, b) -> a / b;

  int compute(String expression) {
    Stack<Integer> stack = new Stack<>();
    for (String elem : expression.trim().split(" ")) {
      if ("+".equals(elem))
        applyOperation(stack, ADD);
      else if ("-".equals(elem))
        applyOperation(stack, SUBTRACT);
      else if ("*".equals(elem))
        applyOperation(stack, MULTIPLY);
      else if ("/".equals(elem))
        applyOperation(stack, DIVIDE);
      else {
        stack.push(parseInt(elem));
      }
    }
    if (stack.size() == 1) return stack.pop();
    else throw new NotReversePolishNotationError();
  }

  private int parseInt(String number) {
    try {
      return Integer.parseInt(number);
    } catch (NumberFormatException e) {
      throw new NotReversePolishNotationError();
    }
  }


  private static void applyOperation(Stack<Integer> stack, BinaryOperator<Integer> operation) {
    int b = stack.pop(), a = stack.pop();
    stack.push(operation.apply(a, b));
  }
}
