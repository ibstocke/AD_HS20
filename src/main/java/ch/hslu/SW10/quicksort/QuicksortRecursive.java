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

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public class QuicksortRecursive {

    /**
     * public method exposed to client, sorts given array using QuickSort Algorithm in Java.
     *
     * @param array input array.
     */
    public static void quicksort(int[] array) {
        QuicksortRecursive.quicksort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort logic.
     *
     * @param array input array.
     * @param startIdx start index of the array.
     * @param endIdx end index of the array.
     */
    public static void quicksort(int[] array, int startIdx, int endIdx) {
        int up = startIdx;          // linke Grenze
        int down = endIdx - 1;   // rechte Grenze (ohne Trennelement)
        int iLength = endIdx - startIdx + 1;

        if (iLength < 25) {
            //selectionsort
            int iTmpMinIndex;
            int iMinValue;
            for (int i = startIdx; i < endIdx; i++) {
                iMinValue = array[i];
                iTmpMinIndex = i;
                for (int j = i + 1; j <= endIdx; j++) {
                    if (iMinValue > array[j]) {
                        iMinValue = array[j];
                        iTmpMinIndex = j;
                    }
                }
                array[iTmpMinIndex] = array[i];
                array[i] = iMinValue;
            }

        } else {
            //quicksort
            int t = array[endIdx];      // rechtes Element als Trennelement

            boolean allChecked = false;
            do {
                // suche grösseres (>=) Element von links an
                while (array[up] < t) {
                    up++;
                }
                // suche echt kleineres (<) Element von rechts an
                while ((array[down] >= t) && (down > up)) {
                    down--;
                }
                // solange keine Überschneidung
                if (down > up) {
                    exchange(array, up, down);
                    up++;           // linke und rechte Grenze verschieben
                    down--;
                } else {
                    allChecked = true;  // Austauschen beendet
                }
            } while (!allChecked);
            exchange(array, up, endIdx);       // Trennelement an endgültige Position (a[up])
            if (startIdx < (up - 1)) {
                quicksort(array, startIdx, (up - 1));   // linke Hälfte
            }
            if ((up + 1) < endIdx) {
                quicksort(array, (up + 1), endIdx);   // rechte Hälfte, ohne T’Elt.
            }
        }
    }

    /**
     * Divides array from pivot, left side contains elements less than Pivot while right side contains elements greater
     * than pivot.
     *
     * @param array array to partitioned.
     * @param left lower bound of the array.
     * @param right upper bound of the array.
     * @return the partition index.
     */
    public static int partition(int[] array, int left, int right) {
        int up = left;          // linke Grenze
        int down = right - 1;   // rechte Grenze (ohne Trennelement)
        int lenght = right - left;

        int t;
        //median of three
        if (lenght >= 3) {
            if (array[left] > array[(right + left) / 2]) {
                if (array[(right + left) / 2] > array[right]) {
                    exchange(array, (right + left) / 2, right);
                } else if (array[left] <= array[right]) {
                    exchange(array, left, right);
                }
            } else {
                if (array[left] > array[right]) {
                    exchange(array, left, right);
                } else if (array[(right + left) / 2] <= array[right]) {
                    exchange(array, (right + left) / 2, right);
                }
            }
        }

        t = array[right];

        boolean allChecked = false;

        do {
            // suche grösseres (>=) Element von links an
            while (array[up] < t) {
                up++;
            }
            // suche echt kleineres (<) Element von rechts an
            while ((array[down] >= t) && (down > up)) {
                down--;
            }
            // solange keine Überschneidung
            if (down > up) {
                exchange(array, up, down);
                up++;           // linke und rechte Grenze verschieben
                down--;
            } else {
                allChecked = true;  // Austauschen beendet
            }
        } while (!allChecked);
        exchange(array, up, right);

        return up;
    }

    private static void exchange(final int[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
