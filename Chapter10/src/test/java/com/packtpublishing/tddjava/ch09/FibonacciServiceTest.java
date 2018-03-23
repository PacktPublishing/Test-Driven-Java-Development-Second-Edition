package com.packtpublishing.tddjava.ch09;

import com.packtpublishing.tddjava.ch09.features.fibonacci.FibonacciService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class FibonacciServiceTest {
    private FibonacciService tested;
    private final String expectedExceptionMessage = "Requested number must be a positive number no bigger than " + FibonacciService.LIMIT;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void beforeTest() {
        tested = new FibonacciService();
    }

    @Test
    public void test0() {
        int actual = tested.getNthNumber(0);
        assertEquals(0, actual);
    }

    @Test
    public void test1() {
        int actual = tested.getNthNumber(1);
        assertEquals(1, actual);
    }

    @Test
    public void test7() {
        int actual = tested.getNthNumber(7);
        assertEquals(13, actual);
    }

    @Test
    public void testNegative() {
        exception.expect(RuntimeException.class);
        exception.expectMessage(is(expectedExceptionMessage));
        tested.getNthNumber(-1);
    }

    @Test
    public void testOutOfBounce() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(is(expectedExceptionMessage));
        tested.getNthNumber(FibonacciService.LIMIT + 1);
    }
}
