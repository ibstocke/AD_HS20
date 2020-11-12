/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW0809.sorting;

import java.util.Random;

/**
 *
 * @author Philipp
 */
public class ArrayBuilder {

    public ArrayBuilder() {
    }

    public static char[] generateRandomCharArray(int iNoOfElements, int iSeed) {
        Random random = new Random(iSeed);
        char[] array = new char[iNoOfElements];
        for (int i = 0; i < iNoOfElements; i++) {
            array[i] = (char) (random.nextInt(95) + 32);
        }
        return array;
    }

    public static int[] generateRandomIntArray(int iNoOfElements, int iSeed) {
        Random random = new Random(iSeed);
        int[] array = new int[iNoOfElements];
        for (int i = 0; i < iNoOfElements; i++) {
            array[i] = random.nextInt(iNoOfElements * 10);
        }
        return array;
    }

    public static int[] generateSortedIntArray(int iNoOfElements) {
        int[] array = new int[iNoOfElements];
        for (int i = 0; i < iNoOfElements; i++) {
            array[i] = i;
        }
        return array;
    }

    public static void flipIntArray(int[] array) {
        int iLenght = array.length;
        int iTmp;
        for (int i = 0; i < iLenght / 2; i++) {
            iTmp = array[iLenght - 1 - i];
            array[iLenght - 1 - i] = array[i];
            array[i] = iTmp;
        }
    }

    public static void shuffleIntArray(int[] array) {
        Random random = new Random();
        int iLenght = array.length;
        int iTmp;
        int iFlipIndex;
        for (int i = 0; i < iLenght - 1; i++) {
            iFlipIndex = random.nextInt(iLenght - 1);
            iTmp = array[iFlipIndex];
            array[iFlipIndex] = array[i];
            array[i] = iTmp;
        }
    }

    public static void printIntArray(int[] array) {
        int iLength = array.length;
        for (int i = 0; i < iLength - 1; i++) {
            System.out.println("[" + i + "] " + array[i]);
        }
        System.out.println();
    }

}
