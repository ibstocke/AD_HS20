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

/**
 * Eine Synchronisationshilfe, die es ermöglicht, einen oder mehrere Threads warten zu lassen, bis diese durch andere
 * Threads aufgeweckt werden. Latches sperren so lange, bis sie einmal ausgelöst werden. Danach sind sie frei
 * passierbar.
 */
public class Latch implements Synch {

    private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(Latch.class);

    private int m_iNoOfReadyHorses;
    final private int m_iNoOfParticipatingHorses;

    public Latch(int iNoOfParticipatingHorses) {
        m_iNoOfParticipatingHorses = iNoOfParticipatingHorses;
    }

    @Override
    public void acquire() throws InterruptedException {
        synchronized (this) {
            m_iNoOfReadyHorses++;
            this.wait();
        }
    }

    @Override
    public void release() {
        waitForAllHorsesReadyToRun();

        synchronized (this) {
            LOG.info("****** Start ******");
            m_iNoOfReadyHorses = 0;
            this.notifyAll();
        }
    }

    private void waitForAllHorsesReadyToRun() {
        while (!areAllHorsesReadyToRun()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                LOG.debug(ex);
            }
        }
    }

    private boolean areAllHorsesReadyToRun() {
        synchronized (this) {
            return m_iNoOfReadyHorses == m_iNoOfParticipatingHorses;
        }
    }
}
