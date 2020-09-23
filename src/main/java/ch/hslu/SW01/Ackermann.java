/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Philipp
 */
public class Ackermann {

    public Ackermann() {
    }

    private static final Logger m_LOGGER = LogManager.getLogger(Ackermann.class);
    private static long m_lFunctionCalls = 0;
    private static long m_lTime = 0;

    public static void main(final String[] args) {
        final int n = 3;
        final int m = 11;
        ackermann(n, m);
    }

    public static int ackermann(int n, int m) {
        m_LOGGER.info("ackermann() START n=({}), m=({})", n, m);
        StartTimer();
        final int result = ack(n, m);
        StopTimer();
        m_LOGGER.info("ackermann() DONE  n=({}), m=({}), function calls=({}) ,execution time=({})ms", n, m, m_lFunctionCalls, m_lTime);
        return result;
    }

    private static int ack(int n, int m) {
        m_lFunctionCalls++;

        if (n == 0) {
            return m + 1;
        }

        if (m == 0) {
            return ack(n - 1, 1);
        }

        return ack(n - 1, ack(n, m - 1));
    }

    private static void StartTimer() {
        m_lTime = System.currentTimeMillis();
    }

    private static void StopTimer() {
        m_lTime = System.currentTimeMillis() - m_lTime;
    }
}
