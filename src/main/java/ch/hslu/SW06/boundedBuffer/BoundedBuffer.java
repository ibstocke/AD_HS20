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
package ch.hslu.SW06.boundedBuffer;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Puffer nach dem First In First Out Prinzip mit einer begrenzten Kapazität. Der Puffer ist thread sicher.
 *
 * @param <T> Elememente des Buffers
 */
public final class BoundedBuffer<T> implements Buffer<T> {

    private final ArrayDeque<T> queue;
    private final Semaphore putSema;
    private final Semaphore takeSema;

    /**
     * Erzeugt einen Puffer mit bestimmter Kapazität.
     *
     * @param n Kapazität des Puffers
     */
    public BoundedBuffer(final int n) {
        this.queue = new ArrayDeque<>(n);
        this.putSema = new Semaphore(n);
        this.takeSema = new Semaphore(0);
    }

    @Override
    public void put(final T elem) throws InterruptedException {
        put(elem, Long.MAX_VALUE);
    }

    @Override
    public T get() throws InterruptedException {
        return get(Long.MAX_VALUE);
    }

    @Override
    public boolean put(T elem, long millis) throws InterruptedException {
        if (!this.putSema.tryAcquire(millis, TimeUnit.MILLISECONDS)) {
            return false;
        }
        synchronized (queue) {
            this.queue.addFirst(elem);
        }
        this.takeSema.release();
        return true;
    }

    @Override
    public T get(long millis) throws InterruptedException {
        if (!this.takeSema.tryAcquire(millis, TimeUnit.MILLISECONDS)) {
            return null;
        }
        T elem;
        synchronized (this.queue) {
            elem = this.queue.removeLast();
        }
        this.putSema.release();
        return elem;
    }

    @Override
    public T first() throws InterruptedException {
        this.takeSema.acquire();
        T elem;
        synchronized (this.queue) {
            elem = this.queue.removeFirst();
        }
        this.putSema.release();
        return elem;
    }

    @Override
    public T last() throws InterruptedException {
        return get();
    }

    @Override
    public boolean empty() {
        synchronized (this.queue) {
            return this.queue.isEmpty();
        }
    }

    @Override
    public boolean full() {
        return this.putSema.availablePermits() == 0;
    }

    @Override
    public int size() {
        synchronized (this.queue) {
            return this.queue.size();
        }
    }
}
