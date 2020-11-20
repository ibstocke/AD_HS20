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
package ch.hslu.SW10.mergesort;

import ch.hslu.SW0809.sorting.MyTimer;
import ch.hslu.SW10.array.init.RandomInitTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Performance Vergleich der Mergesort-Implementation.
 */
public final class DemoMergesort {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.SW10.mergesort.DemoMergesort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoMergesort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 10_000_000;
        long lTimePassed;
        final int[] arrayOrig = new int[size];
        final ForkJoinPool pool = new ForkJoinPool();
        RandomInitTask initTask = new RandomInitTask(arrayOrig, 100);
        MyTimer myTimer = new MyTimer();
        pool.invoke(initTask);
        //SumTask sumTask = new SumTask(arrayOrig);
        //long result = pool.invoke(sumTask);
        //LOG.info("Init. Checksum  : " + result);

        int[] array;
        final int iCores = Runtime.getRuntime().availableProcessors();
        int iStartThreshold = size / iCores;

        for (int iThreshold = iStartThreshold - 10; iThreshold < iStartThreshold + 12; iThreshold = iThreshold + 2) {
            array = Arrays.copyOf(arrayOrig, arrayOrig.length);

            myTimer.startTimer();
            final MergesortTask sortTask = new MergesortTask(array, iThreshold);
            pool.invoke(sortTask);
            myTimer.stopTimer();
            lTimePassed = myTimer.getTimePassed();
            LOG.info("Conc. Mergesort[" + iThreshold + "] : " + lTimePassed + " ms");

            //sumTask = new SumTask(array);
            //result = pool.invoke(sumTask);
            //LOG.info("Merge Checksum  : " + result);
            //initTask = new RandomInitTask(array, 100);
            //pool.invoke(initTask);
            //sumTask = new SumTask(array);
            //result = pool.invoke(sumTask);
            //LOG.info("Init. checksum  : " + result);
        }
        array = Arrays.copyOf(arrayOrig, arrayOrig.length);

        myTimer.startTimer();
        MergesortRecursive.mergeSort(array);
        myTimer.stopTimer();
        lTimePassed = myTimer.getTimePassed();

        LOG.info("MergesortRec.   : " + lTimePassed + " ms");
        //sumTask = new SumTask(array);
        //result = pool.invoke(sumTask);
        //LOG.info("Sort checksum   : " + result);
    }
}
