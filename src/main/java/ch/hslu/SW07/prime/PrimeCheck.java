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
package ch.hslu.SW07.prime;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 100 grosse Primzahlen produzieren.
 */
public final class PrimeCheck {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.SW07.prime.PrimeCheck.class);

    /**
     * Privater Konstruktor.
     */
    public PrimeCheck() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        final int iNoOfPrimesRequired = 100;
        PrimeList primeList = new PrimeList(iNoOfPrimesRequired);

        final int iCores = Runtime.getRuntime().availableProcessors() + 1;

        final ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < iCores; i++) {
            executor.submit(new PrimeProducer(primeList));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.err.println("timeout reached");
        }
        LOG.info("end");

        Iterator<BigInteger> itr = primeList.getIterator();
        for (int i = 1; itr.hasNext(); i++) {
            LOG.info(i + ": " + itr.next().toString().substring(0, 20) + "...");
        }

    }
}
