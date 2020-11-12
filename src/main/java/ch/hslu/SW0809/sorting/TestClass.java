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
public class TestClass {

    public static void main(String[] args) {
        final int iNoOfElements = 1_000_000 * 2;
        //final int iNoOfElements = 100;
        final boolean fSortedArray = false;
        final int iTestCase = 2;
        //1: InsertionSort, SelectionSort, BubbleSort
        //2: Quicksort

        Sort mySort = new Sort();
        MyTimer myTimer = new MyTimer();
        long lTimeDurationMs;
        String strIsSorted;

        final int[] origArray;
        if (fSortedArray) {
            origArray = ArrayBuilder.generateSortedIntArray(iNoOfElements);

        } else {
            origArray = ArrayBuilder.generateRandomIntArray(iNoOfElements, 1);
        }

        switch (iTestCase) {
            case 1:
                int[] arrSelectionSort = origArray.clone();
                int[] arrInsertionSort = origArray.clone();
                int[] arrBubbleSort = origArray.clone();
                int[] arrShellSort = origArray.clone();

                //******************************************
                myTimer.startTimer();
                mySort.selectionSort(arrSelectionSort);
                myTimer.stopTimer();
                lTimeDurationMs = myTimer.getTimePassed();
                strIsSorted = mySort.isIntArraySortet(arrSelectionSort) ? "sorted" : "not sorted";
                System.out.println("SelectionSort: " + lTimeDurationMs + "ms - " + strIsSorted);

                //******************************************
                myTimer.startTimer();
                mySort.insertionSort(arrInsertionSort);
                myTimer.stopTimer();
                lTimeDurationMs = myTimer.getTimePassed();
                strIsSorted = mySort.isIntArraySortet(arrInsertionSort) ? "sorted" : "not sorted";
                System.out.println("InsertionSort: " + lTimeDurationMs + "ms - " + strIsSorted);

                //******************************************
                myTimer.startTimer();
                mySort.bubbleSort(arrBubbleSort);
                myTimer.stopTimer();
                lTimeDurationMs = myTimer.getTimePassed();
                strIsSorted = mySort.isIntArraySortet(arrBubbleSort) ? "sorted" : "not sorted";
                System.out.println("BubbleStort: " + lTimeDurationMs + "ms - " + strIsSorted);

                //******************************************
                myTimer.startTimer();
                mySort.shellsort(arrShellSort);
                myTimer.stopTimer();
                lTimeDurationMs = myTimer.getTimePassed();
                strIsSorted = mySort.isIntArraySortet(arrShellSort) ? "sorted" : "not sorted";
                System.out.println("ShellSort: " + lTimeDurationMs + "ms - " + strIsSorted);
                break;

            case 2:
                int[] tmpArray;
                long lTimeTot = 0;
                int iNoOfRuns = 99;
                for (int i = 0; i < iNoOfRuns; i++) {
                    tmpArray = origArray.clone();
                    myTimer.startTimer();
                    mySort.quickSort(tmpArray);
                    myTimer.stopTimer();
                    lTimeDurationMs = myTimer.getTimePassed();
                    lTimeTot += lTimeDurationMs;
                    strIsSorted = mySort.isIntArraySortet(tmpArray) ? "sorted" : "not sorted";
                    //System.out.println("[" + (i + 1) + "/" + iNoOfRuns + "] Quicksort: " + lTimeDurationMs + "ms - " + strIsSorted);
                }
                lTimeTot /= iNoOfRuns;
                System.out.println("Average sortingtime with QuickSort: " + lTimeTot + " ms");

                lTimeTot = 0;
                for (int i = 0; i < iNoOfRuns; i++) {
                    tmpArray = origArray.clone();
                    myTimer.startTimer();
                    mySort.quickInsertionSort(tmpArray);
                    myTimer.stopTimer();
                    lTimeDurationMs = myTimer.getTimePassed();
                    lTimeTot += lTimeDurationMs;
                    strIsSorted = mySort.isIntArraySortet(tmpArray) ? "sorted" : "not sorted";
                    //System.out.println("[" + (i + 1) + "/" + iNoOfRuns + "] Quicksort: " + lTimeDurationMs + "ms - " + strIsSorted);
                }
                lTimeTot /= iNoOfRuns;
                System.out.println("Average sortingtime with QuickInsertionSort: " + lTimeTot + " ms");

                break;

            case 3:
                break;
        }

    }

}
