package com.packtpublishing.tddjava.ch09.features.fibonacci;

public class FibonacciNumber {
    private final int number, value;

    public FibonacciNumber(int number, int value) {
        this.number = number;
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }
}
