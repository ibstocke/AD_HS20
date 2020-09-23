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
 * @author phil
 */
public class Exercise01 {

    public Exercise01() {
    }

    private static final Logger m_LOGGER = LogManager.getLogger(Exercise01.class);
    private static final long L_THREAD_SLEEPTIME = 1; // simulation delay time [ms]
    private static long m_lNoOfFunctionCalls = 0;

    public static void main(final String[] args) {
        long n = 1;
        for (long i = 0; i < 8; i++) {
            task(n);
            n *= 2;
        }
    }

    public static void task(final long n) {
        final long lStartTime = System.currentTimeMillis();
        m_lNoOfFunctionCalls = 0;

        //f(n) = 4 + 3*n + 2*n^2
        task1();
        task1();
        task1();
        task1();     // T ~ 4
        for (int i = 0; i < n; i++) {           // aeussere Schleife: n-mal
            task2();
            task2();
            task2();                            // T ~ n · 3
            for (int j = 0; j < n; j++) {       // innerer Schleife: n-mal
                task3();
                task3();                        // T ~ n · n· 2
            }
        }

        final long lExecutionTime = System.currentTimeMillis() - lStartTime;
        m_LOGGER.info("Result: n={}, no. of functin calls: {}, exection time={}ms", n, m_lNoOfFunctionCalls, lExecutionTime);
    }

    public static void task1() {
        m_lNoOfFunctionCalls++;
        //LOGGER.info("task1()");
        try {
            Thread.sleep(L_THREAD_SLEEPTIME);
        } catch (InterruptedException e) {
        }
    }

    public static void task2() {
        m_lNoOfFunctionCalls++;
        //LOGGER.info("task2()");
        try {
            Thread.sleep(L_THREAD_SLEEPTIME);
        } catch (InterruptedException e) {
        }
    }

    public static void task3() {
        m_lNoOfFunctionCalls++;
        //LOGGER.info("task3()");
        try {
            Thread.sleep(L_THREAD_SLEEPTIME);
        } catch (InterruptedException e) {
        }
    }

}
