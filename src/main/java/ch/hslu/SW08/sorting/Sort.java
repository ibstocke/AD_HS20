/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW08.sorting;

/**
 *
 * @author Philipp
 */
public class Sort {

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

    public boolean isArraySortet(int[] array) {
        int iLength = array.length;
        for (int i = 0; i < iLength - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final int iNoOfElements = 120013;
        Sort mySort = new Sort();

        //final int[] origArray = ArrayBuilder.generateSortedArray(iNoOfElements);
        //ArrayBuilder.flipArray(origArray);
        final int[] origArray = ArrayBuilder.generateRandomArray(iNoOfElements, 1);
        int[] arrSelectionSort = origArray.clone();
        int[] arrInsertionSort = origArray.clone();
        int[] arrBubbleSort = origArray.clone();
        int[] arrShellSort = origArray.clone();

        MyTimer myTimer = new MyTimer();
        long lTimeDurationMs;
        String strIsSorted;

        //******************************************
        myTimer.startTimer();
        mySort.selectionSort(arrSelectionSort);
        myTimer.stopTimer();
        lTimeDurationMs = myTimer.getTimePassed();
        strIsSorted = mySort.isArraySortet(arrSelectionSort) ? "sorted" : "not sorted";
        System.out.println("SelectionSort: " + lTimeDurationMs + "ms - " + strIsSorted);

        //******************************************
        myTimer.startTimer();
        mySort.insertionSort(arrInsertionSort);
        myTimer.stopTimer();
        lTimeDurationMs = myTimer.getTimePassed();
        strIsSorted = mySort.isArraySortet(arrInsertionSort) ? "sorted" : "not sorted";
        System.out.println("InsertionSort: " + lTimeDurationMs + "ms - " + strIsSorted);

        //******************************************
        myTimer.startTimer();
        mySort.bubbleSort(arrBubbleSort);
        myTimer.stopTimer();
        lTimeDurationMs = myTimer.getTimePassed();
        strIsSorted = mySort.isArraySortet(arrBubbleSort) ? "sorted" : "not sorted";
        System.out.println("BubbleStort: " + lTimeDurationMs + "ms - " + strIsSorted);

        //******************************************
        myTimer.startTimer();
        mySort.shellsort(arrShellSort);
        myTimer.stopTimer();
        lTimeDurationMs = myTimer.getTimePassed();
        strIsSorted = mySort.isArraySortet(arrShellSort) ? "sorted" : "not sorted";
        System.out.println("ShellSort: " + lTimeDurationMs + "ms - " + strIsSorted);

    }
}
