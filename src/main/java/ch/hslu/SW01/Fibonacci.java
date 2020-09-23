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
public final class Fibonacci {

    public Fibonacci() {
    }

    private static final Logger m_LOGGER = LogManager.getLogger(Fibonacci.class);
    private static long m_lTime = 0;

    public static void main(final String[] args) {
        if (args.length > 0) {
            System.out.println("hello");
        }
        final int iNthFibonacciNumber = 17; //94
        long lResFibNo = 0;

        StartTimer();
        lResFibNo = FibonacciRek01(iNthFibonacciNumber);
        StopTimer();
        //m_LOGGER.info("{}-th Fibonacci number is: ({}) -- FibonacciRek01() exection time: {} ms", iNthFibonacciNumber, lResFibNo, m_lTime);

        StartTimer();
        lResFibNo = FibonacciRek02(iNthFibonacciNumber);
        StopTimer();
        //m_LOGGER.info("{}-th Fibonacci number is: ({}) -- FibonacciRek02() exection time: {} ms", iNthFibonacciNumber, lResFibNo, m_lTime);

        StartTimer();
        lResFibNo = FibonacciIter(iNthFibonacciNumber);
        StopTimer();
        //m_LOGGER.info("{}-th Fibonacci number is: ({}) -- FibonacciIter() exection time: {} ms", iNthFibonacciNumber, lResFibNo, m_lTime);
    }

    public static long FibonacciRek01(final int iNthFibonacciNumber) {
        assert iNthFibonacciNumber >= 0 : "iNthNumber must be positiv";

        //Rekursionsbasis
        if (iNthFibonacciNumber == 0) {
            return 0;
        }
        if (iNthFibonacciNumber == 1) {
            return 1;
        }

        //Rekursionsvorschrift
        return FibonacciRek01(iNthFibonacciNumber - 2) + FibonacciRek01(iNthFibonacciNumber - 1);
    }

    public static long FibonacciRek02(final int iNthFibonacciNumber) {
        assert iNthFibonacciNumber >= 0 : "iNthFibonacciNumber must be positiv";

        if (iNthFibonacciNumber == 0) {
            return 0;
        }
        if (iNthFibonacciNumber == 1) {
            return 1;
        }

        long[] fibArr = new long[iNthFibonacciNumber + 1];
        fibArr[0] = 0;
        fibArr[1] = 1;

        return FibonacciRek02Impl(iNthFibonacciNumber, fibArr);
    }

    private static long FibonacciRek02Impl(final int iNthFibonacciNumber, long[] fibArr) {
        if (fibArr[iNthFibonacciNumber] != 0 || iNthFibonacciNumber <= 1) {
            return fibArr[iNthFibonacciNumber];
        }

        fibArr[iNthFibonacciNumber] = FibonacciRek02Impl(iNthFibonacciNumber - 2, fibArr) + FibonacciRek02Impl(iNthFibonacciNumber - 1, fibArr);

        return fibArr[iNthFibonacciNumber];
    }

    public static long FibonacciIter(final int iNthFibonacciNumber) {
        assert iNthFibonacciNumber >= 0 : "iNthNumber must be positiv";

        if (iNthFibonacciNumber == 0) {
            return 0;
        }
        if (iNthFibonacciNumber == 1) {
            return 1;
        }

        long lPrevFibNo = 0;
        long lCurrFibNo = 1;
        long lTmpFibNo = 0;

        for (int i = 0; i < iNthFibonacciNumber - 1; i++) {
            lTmpFibNo = lPrevFibNo + lCurrFibNo;
            lPrevFibNo = lCurrFibNo;
            lCurrFibNo = lTmpFibNo;
        }

        return lCurrFibNo;
    }

    private static void StartTimer() {
        m_lTime = System.currentTimeMillis();
    }

    private static void StopTimer() {
        m_lTime = System.currentTimeMillis() - m_lTime;
    }

}
