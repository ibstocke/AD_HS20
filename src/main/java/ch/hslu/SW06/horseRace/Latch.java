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

import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;

/**
 * Eine Synchronisationshilfe, die es ermöglicht, einen oder mehrere Threads warten zu lassen, bis diese durch andere
 * Threads aufgeweckt werden. Latches sperren so lange, bis sie einmal ausgelöst werden. Danach sind sie frei
 * passierbar.
 */
public class Latch implements Synch {

    private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(Latch.class);

    private final Semaphore m_startBoxSema;
    final private int m_iNoOfParticipatingHorses;

    public Latch(int iNoOfParticipatingHorses) {
        m_startBoxSema = new Semaphore(0);
        m_iNoOfParticipatingHorses = iNoOfParticipatingHorses;
    }

    @Override
    public void acquire() throws InterruptedException {
        synchronized (this) {
            m_startBoxSema.release();
            this.wait();
        }
    }

    @Override
    public void release() {
        try {
            m_startBoxSema.acquire(m_iNoOfParticipatingHorses);
        } catch (InterruptedException ex) {
            LOG.debug(ex);
        }

        synchronized (this) {
            LOG.info("****** Start ******");
            this.notifyAll();
        }
    }
}
