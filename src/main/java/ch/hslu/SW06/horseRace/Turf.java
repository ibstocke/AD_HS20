/*
 * Copyright 2020 Hochschule Luzern - Informatik.
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
package ch.hslu.SW06.horseRace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Eine Rennbahn für das Pferderennen.
 */
public final class Turf {

    private static final Logger LOG = LogManager.getLogger(RaceHorse.class);

    /**
     * Privater Konstruktor.
     */
    private Turf() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int iNoOfRunningHorses = 8;
        final Thread[] threads = new Thread[iNoOfRunningHorses];
        final Synch starterBox = new Latch(iNoOfRunningHorses);

        for (int i = 0; i < iNoOfRunningHorses; i++) {
            final String strHorseID = "Horse " + Integer.toString(i + 1);
            threads[i] = new Thread(new RaceHorse(starterBox), strHorseID);
            threads[i].start();
        }

        starterBox.release();

        final boolean fSimRaceInterruption = false;
        if (fSimRaceInterruption) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                LOG.debug(ex);
            }
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                LOG.debug(ex);
            }
        }
        LOG.info("****** Rennen beeendet ******");
    }
}
