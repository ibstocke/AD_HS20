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
package ch.hslu.SW10.quicksort;

import ch.hslu.SW0809.sorting.MyTimer;
import ch.hslu.SW10.array.init.RandomInitTask;
import ch.hslu.SW10.array.sum.SumTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Vergleich von verschiedenen Quicksort-Implementationen.
 */
public final class DemoQuicksort {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.SW10.quicksort.DemoQuicksort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoQuicksort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 10_000_013;
        final int[] arrayOrig = new int[size];
        MyTimer myTimer = new MyTimer();
        long lTimePassed;
        final ForkJoinPool pool = new ForkJoinPool();

        RandomInitTask initTask = new RandomInitTask(arrayOrig, size * 5);
        pool.invoke(initTask);
        SumTask sumTask = new SumTask(arrayOrig);
        long result = pool.invoke(sumTask);
        LOG.info("Init. Checksum : " + result);

        int[] array = Arrays.copyOf(arrayOrig, arrayOrig.length);
        myTimer.startTimer();
        final QuicksortTask sortTask = new QuicksortTask(array);
        pool.invoke(sortTask);
        myTimer.stopTimer();
        lTimePassed = myTimer.getTimePassed();
        LOG.info("QuicksortTask  : " + lTimePassed + " ms");
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Conc. Checksum : " + result);

        array = Arrays.copyOf(arrayOrig, arrayOrig.length);
        myTimer.startTimer();
        QuicksortRecursive.quicksort(array);
        myTimer.stopTimer();
        lTimePassed = myTimer.getTimePassed();
        LOG.info("QuicksortRec.  : " + lTimePassed + " ms");
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Recu. Checksum : " + result);

        array = Arrays.copyOf(arrayOrig, arrayOrig.length);
        myTimer.startTimer();
        Arrays.sort(array);
        myTimer.stopTimer();
        lTimePassed = myTimer.getTimePassed();
        LOG.info("Arrays.sort    : " + lTimePassed + " ms");
        sumTask = new SumTask(array);
        result = pool.invoke(sumTask);
        LOG.info("Sort checksum  : " + result);
    }
}
