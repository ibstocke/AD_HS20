/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW0809.sorting;

/**
 *
 * @author Philipp
 */
public class Sort {

    public final void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(final int[] a, final int left, final int right) {
        int up = left;          // linke Grenze
        int down = right - 1;   // rechte Grenze (ohne Trennelement)

        int t = a[right];      // rechtes Element als Trennelement

        boolean allChecked = false;
        do {
            // suche grösseres (>=) Element von links an
            while (a[up] < t) {
                up++;
            }
            // suche echt kleineres (<) Element von rechts an
            while ((a[down] >= t) && (down > up)) {
                down--;
            }
            // solange keine Überschneidung
            if (down > up) {
                exchange(a, up, down);
                up++;           // linke und rechte Grenze verschieben
                down--;
            } else {
                allChecked = true;  // Austauschen beendet
            }
        } while (!allChecked);
        exchange(a, up, right);       // Trennelement an endgültige Position (a[up])
        if (left < (up - 1)) {
            quickSort(a, left, (up - 1));   // linke Hälfte
        }
        if ((up + 1) < right) {
            quickSort(a, (up + 1), right);   // rechte Hälfte, ohne T’Elt.
        }
    }

    public final void quickInsertionSort(int[] array) {
        quickInsertionSort(array, 0, array.length - 1);
    }

    private void quickInsertionSort(final int[] a, final int left, final int right) {
        int up = left;          // linke Grenze
        int down = right - 1;   // rechte Grenze (ohne Trennelement)
        int iLength = right - left + 1;

        if (iLength < 25) {
            //selectionsort
            int iTmpMinIndex;
            int iMinValue;
            for (int i = left; i < right; i++) {
                iMinValue = a[i];
                iTmpMinIndex = i;
                for (int j = i + 1; j <= right; j++) {
                    if (iMinValue > a[j]) {
                        iMinValue = a[j];
                        iTmpMinIndex = j;
                    }
                }
                a[iTmpMinIndex] = a[i];
                a[i] = iMinValue;
            }

        } else {
            //quicksort
            int t = a[right];      // rechtes Element als Trennelement

            boolean allChecked = false;
            do {
                // suche grösseres (>=) Element von links an
                while (a[up] < t) {
                    up++;
                }
                // suche echt kleineres (<) Element von rechts an
                while ((a[down] >= t) && (down > up)) {
                    down--;
                }
                // solange keine Überschneidung
                if (down > up) {
                    exchange(a, up, down);
                    up++;           // linke und rechte Grenze verschieben
                    down--;
                } else {
                    allChecked = true;  // Austauschen beendet
                }
            } while (!allChecked);
            exchange(a, up, right);       // Trennelement an endgültige Position (a[up])
            if (left < (up - 1)) {
                quickInsertionSort(a, left, (up - 1));   // linke Hälfte
            }
            if ((up + 1) < right) {
                quickInsertionSort(a, (up + 1), right);   // rechte Hälfte, ohne T’Elt.
            }
        }
    }

    private void exchange(int[] a, final int firstIndex, final int secondIndex) {
        int tmp;
        tmp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = tmp;
    }

    public void selectionSort(int[] array) {
        int iLength = array.length;
        int iTmpMinIndex;
        int iMinValue;
        for (int i = 0; i < iLength - 1; i++) {
            iMinValue = array[i];
            iTmpMinIndex = i;
            for (int j = i + 1; j < iLength; j++) {
                if (iMinValue > array[j]) {
                    iMinValue = array[j];
                    iTmpMinIndex = j;
                }
            }
            array[iTmpMinIndex] = array[i];
            array[i] = iMinValue;
        }
    }

    public void insertionSort(int[] array) {
        int iLength = array.length;
        int iInsertIndex;
        int iTmp;
        for (int i = 1; i < iLength; i++) {
            iInsertIndex = i;
            iTmp = array[i];

            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > iTmp) {
                    iInsertIndex = j;
                }
            }

            for (int k = i; k > iInsertIndex; k--) {
                array[k] = array[k - 1];
            }
            array[iInsertIndex] = iTmp;
        }
    }

    public void bubbleSort(int[] array) {
        int iLenght = array.length;
        int iTmp;
        for (int i = 0; i < iLenght - 1; i++) {
            for (int j = 0; j < iLenght - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    iTmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = iTmp;
                }
            }
        }

    }

    public void shellsort(int[] array) {
        int iLength = array.length;

        for (int i = (iLength + 1) / 2; i > 0; i = i / 2) {
            for (int j = i; j < iLength; j = j + i) {
                int k = j;
                int tmp;
                while ((k - i) >= 0 && array[k] < array[k - i]) {
                    tmp = array[k];
                    array[k] = array[k - i];
                    array[k - i] = tmp;
                    k = k - i;
                }
            }
        }
    }

    public boolean isIntArraySortet(int[] array) {
        int iLength = array.length;
        for (int i = 0; i < iLength - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isCharArraySortet(char[] array) {
        int iLength = array.length;
        for (int i = 0; i < iLength - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public final void quickSort(char[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(final char[] a, final int left, final int right) {
        int up = left;          // linke Grenze
        int down = right - 1;   // rechte Grenze (ohne Trennelement)

        char t = a[right];      // rechtes Element als Trennelement

        boolean allChecked = false;
        do {
            // suche grösseres (>=) Element von links an
            while (a[up] < t) {
                up++;
            }
            // suche echt kleineres (<) Element von rechts an
            while ((a[down] >= t) && (down > up)) {
                down--;
            }
            // solange keine Überschneidung
            if (down > up) {
                exchange(a, up, down);
                up++;           // linke und rechte Grenze verschieben
                down--;
            } else {
                allChecked = true;  // Austauschen beendet
            }
        } while (!allChecked);
        exchange(a, up, right);       // Trennelement an endgültige Position (a[up])
        if (left < (up - 1)) {
            quickSort(a, left, (up - 1));   // linke Hälfte
        }
        if ((up + 1) < right) {
            quickSort(a, (up + 1), right);   // rechte Hälfte, ohne T’Elt.
        }
    }

    private void exchange(final char[] a, final int firstIndex, final int secondIndex) {
        char tmp;
        tmp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = tmp;
    }

}
