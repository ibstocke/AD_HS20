/*
 * Copyright 2020 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.SW10.fibo;

import ch.hslu.SW0809.sorting.MyTimer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Codevorlage für die Verwendung von RecursiveTask mit einem Fork-Join-Pool.
 */
public final class DemoFibonacciCalc {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.SW10.fibo.DemoFibonacciCalc.class);

    /**
     * Privater Konstruktor.
     */
    private DemoFibonacciCalc() {
    }

    /**
     * Berechnet den Fibonacci Wert für n.
     *
     * @param n für die Fibonacci Berechnung.
     * @return Resultat der Fibonacci Berechnung.
     */
    public static long fiboIterative(final int n) {
        long f = 0;
        long g = 1;
        for (int i = 1; i <= n; i++) {
            f = f + g;
            g = f - g;
        }
        return f;
    }

    /**
     * Berechnet den Fibonacci Wert für n.
     *
     * @param n für die Fibonacci Berechnung.
     * @return Resultat der Fibonacci Berechnung.
     */
    public static long fiboRecursive(final int n) {
        return n > 1 ? fiboRecursive(n - 1) + fiboRecursive(n - 2) : n;
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int n = 42;
        MyTimer myTimer = new MyTimer();

        LOG.info("fibo(" + n + ") start...");
        myTimer.startTimer();
        final FibonacciTask task = new FibonacciTask(n);
        long result = task.invoke();
        myTimer.stopTimer();
        LOG.info("Conc. recursive = " + result);
        LOG.info("Conc. recursive : " + myTimer.getTimePassed() + " ms");

        myTimer.startTimer();
        result = fiboIterative(n);
        myTimer.stopTimer();
        LOG.info("Func. iterative = " + result);
        LOG.info("Func. iterative : " + myTimer.getTimePassed() + " ms");

        myTimer.startTimer();
        result = fiboRecursive(n);
        myTimer.stopTimer();
        LOG.info("Func. recursive = " + result);
        LOG.info("Func. recursive : " + myTimer.getTimePassed() + " ms");
    }
}
