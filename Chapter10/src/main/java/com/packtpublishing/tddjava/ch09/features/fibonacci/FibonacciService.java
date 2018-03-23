package com.packtpublishing.tddjava.ch09.features.fibonacci;

import org.springframework.stereotype.Service;

@Service("fibonacci")
public class FibonacciService {
    public static final int LIMIT = 30;

    public int getNthNumber(int n) {
        if (isOutOfLimits(n)) throw new IllegalArgumentException("Requested number must be a positive number no bigger than " + LIMIT);
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int first, second = 1, result = 1;
        do {
            first = second;
            second = result;
            result = first + second;
            --n;
        } while (n > 2);
        return result;
    }

    private boolean isOutOfLimits(int number) {
        return number > LIMIT || number < 0;
    }
}
