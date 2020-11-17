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
package ch.hslu.SW10.hanoi;

import java.util.concurrent.RecursiveAction;

/**
 * Codevorlage «Türme von Hanoi» mit Fork-Join-Framework.
 */
@SuppressWarnings("serial")
public final class MoveDiskTask extends RecursiveAction {

    private final String from;
    private final String via;
    private final String to;
    private final int n;

    /**
     * Erstellt eine Aufgabe um eine Scheibe zu versetzen.
     *
     * @param from Ausgangspunkt.
     * @param via Zwischenlager.
     * @param to Zielpunkt.
     * @param n Anzahl Scheiben.
     */
    public MoveDiskTask(String from, String via, String to, int n) {
        this.from = from;
        this.via = via;
        this.to = to;
        this.n = n;
    }

    @Override
    protected void compute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
