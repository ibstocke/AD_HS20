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
package ch.hslu.SW10.findfile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Codevorlage zu CountedCompleter für eine Dateisuche.
 */
@SuppressWarnings("serial")
public final class FindFileTask extends CountedCompleter<ArrayList<String>> {

    private final String regex;
    private final File dir;
    private final BlockingDeque<String> strList;

    /**
     * Erzeugt eine File-Such-Aufgabe.
     *
     * @param regex Ausdruck der den Filenamen enthält.
     * @param dir Verzeichnis in dem das File gesucht wird.
     */
    public FindFileTask(String regex, File dir) {
        this(null, regex, dir, new LinkedBlockingDeque<String>());
    }

    private FindFileTask(final CountedCompleter<?> parent, final String regex, final File dir, final BlockingDeque<String> strList) {
        super(parent);
        this.dir = dir;
        this.regex = regex;
        this.strList = strList;
    }

    @Override
    public void compute() {
        final File[] list = dir.listFiles();

        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    //System.out.println("Search in directory: " + file.getName());
                    final FindFileTask root = new FindFileTask(this, regex, file, this.strList);
                    this.addToPendingCount(1);
                    root.fork();
                } else if (regex.equalsIgnoreCase(file.getName())) {
                    strList.add(dir.getAbsolutePath());
                }
            }
        }
        this.tryComplete();
    }

    @Override
    public ArrayList<String> getRawResult() {
        ArrayList<String> strResults = new ArrayList<>();
        Iterator<String> itr = this.strList.iterator();
        while (itr.hasNext()) {
            strResults.add(itr.next());
        }
        return strResults;
    }
}
