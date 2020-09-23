/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW01;

import static ch.hslu.SW01.Ackermann.ackermann;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Philipp
 */
public class AckermannTest {

    @Test
    void FibonacciIterTest() {
        assertEquals(1, ackermann(0, 0));
        assertEquals(2, ackermann(1, 0));
        assertEquals(2, ackermann(0, 1));
        assertEquals(3, ackermann(1, 1));
    }
}
