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
package ch.hslu.SW06.signal;

/**
 * Ein nach oben nicht begrenztes Semaphor, d.h. der Semaphorenzähler kann unendlich wachsen.
 */
public final class Semaphore {

    private int sema; // Semaphorenzähler
    private final int limit; // maximale Anzahl der Passiersignale.
    private int count; // Anzahl der wartenden Threads.

    /**
     * Erzeugt ein Semaphore mit 0 Passiersignalen.
     */
    public Semaphore() {
        this(0);
    }

    /**
     * Erzeugt ein Semaphore mit einem Initalwert für den Semaphorenzähler.
     *
     * @param permits Anzahl Passiersignale zur Initialisierung.
     */
    public Semaphore(final int permits) {
        this(permits, Integer.MAX_VALUE);
    }

    /**
     * Erzeugt ein nach oben begrenztes Semaphore.
     *
     * @param permits Anzahl Passiersignale zur Initialisierung.
     * @param limit maximale Anzahl der Passiersignale.
     * @throws IllegalArgumentException wenn Argumente ungültige Werte besitzen.
     */
    public Semaphore(final int permits, final int limit) throws IllegalArgumentException {
        if (permits < 0) {
            throw new IllegalArgumentException("permits(" + permits + ") < 0");
        }

        if (limit < permits) {
            throw new IllegalArgumentException("limit(" + limit + ") < permits(" + permits + ")");
        }

        this.sema = permits;
        this.limit = limit;
        this.count = 0;
    }

    public void acquire() throws InterruptedException {
        acquire(1);
    }

    /**
     * Entspricht dem P() Eintritt (Passieren) in einen synchronisierten Bereich, wobei mitgezählt wird, der wievielte
     * Eintritt es ist. Falls der Zähler null ist wird der Aufrufer blockiert.
     *
     * @throws java.lang.InterruptedException falls das Warten unterbrochen wird.
     */
    public synchronized void acquire(int permits) throws InterruptedException {
        while (sema < permits) {
            this.count++;
            this.wait();
            this.count--;
        }
        this.sema -= permits;
    }

    public void release() throws IllegalStateException {
        release(1);
    }

    /**
     * Entspricht dem V() Verlassen (Freigeben) eines synchronisierten Bereiches, wobei ebenfalls mitgezählt wird, wie
     * oft der Bereich verlassen wird.
     */
    public synchronized void release(int permits) throws IllegalStateException {
        if (this.sema == 0) {
            this.notifyAll();
        }

        if (this.sema + permits > this.limit) {
            throw new IllegalStateException("sema(" + this.sema + permits + ") >= limit(" + this.limit + ")");
        }
        this.sema += permits;
    }

    /**
     * Lesen der Anzahl wartenden Threads.
     *
     * @return Anzahl wartende Threads.
     */
    public synchronized int pending() {
        return this.count;
    }
}
