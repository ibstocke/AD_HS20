package ch.hslu.SW01;

import static ch.hslu.SW01.Fibonacci.FibonacciIter;
import static ch.hslu.SW01.Fibonacci.FibonacciRek01;
import static ch.hslu.SW01.Fibonacci.FibonacciRek02;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

final class FibonacciTest {

    @Test
    void FibonacciRek01Test() {
        assertEquals(0, FibonacciRek01(0));
        assertEquals(1, FibonacciRek01(1));
        assertEquals(5, FibonacciRek01(5));
        assertEquals(987, FibonacciRek01(16));
    }

    @Test
    void FibonacciRek02Test() {
        assertEquals(0, FibonacciRek02(0));
        assertEquals(1, FibonacciRek02(1));
        assertEquals(987, FibonacciRek02(16));
        assertEquals(832040, FibonacciIter(30));
    }

    @Test
    void FibonacciIterTest() {
        assertEquals(0, FibonacciIter(0));
        assertEquals(1, FibonacciIter(1));
        assertEquals(987, FibonacciIter(16));
        assertEquals(832040, FibonacciIter(30));
    }
}
