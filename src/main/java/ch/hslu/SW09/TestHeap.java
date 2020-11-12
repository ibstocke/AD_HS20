/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW09;

import java.util.Random;

/**
 *
 * @author Philipp
 */
public class TestHeap {

    public static void main(String[] args) throws InterruptedException {
        final int iDeep = 7;

        IHeapInt myHeap = new FixedSizeHeap(iDeep);
        Random random = new Random();

        final int iNoOfElements = (int) Math.pow(2, iDeep);
        int iValueToAdd;
        System.out.println("****** Fill Heap ******");
        while (!myHeap.isFull()) {
            iValueToAdd = random.nextInt(iNoOfElements * 10);
            myHeap.insert(iValueToAdd);
            System.out.println("Value added: " + iValueToAdd);
        }

        Thread.sleep(200);
        System.out.println();
        System.out.println("****** Empty Heap ******");

        boolean fHadError = false;
        int iMaxValue_1 = Integer.MAX_VALUE;
        int iMaxValue = 1;
        while (!myHeap.isEmpty()) {
            iMaxValue = myHeap.removeMax();
            System.err.println("Removed value: " + iMaxValue);
            if (iMaxValue_1 < iMaxValue) {
                fHadError = true;
                System.err.println("****** error in Heap ******");
                System.err.println(iMaxValue_1 + " < " + iMaxValue);
            }
            iMaxValue_1 = iMaxValue;
        }

        System.out.println();
        if (fHadError) {
            System.out.println("****** simple Heap test failed ******");
        } else {
            System.out.println("****** simple Heap test succeeded ******");
        }

    }

}
