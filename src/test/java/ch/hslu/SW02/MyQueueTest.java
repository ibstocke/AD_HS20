/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Philipp
 */
public class MyQueueTest {

    /**
     * Test
     */
    @Test
    void testQueueCounting() {
        IQueue<String> myQueue = new ArrayQueue<>(3);
        final String str1 = "String1";

        assertEquals(myQueue.getNoOfElements(), 0);
        assertEquals(myQueue.isEmpty(), true);
        assertEquals(myQueue.isFull(), false);
        myQueue.add(str1);
        assertEquals(myQueue.getNoOfElements(), 1);
        assertEquals(myQueue.isEmpty(), false);
        assertEquals(myQueue.isFull(), false);
        myQueue.add(str1);
        assertEquals(myQueue.getNoOfElements(), 2);
        assertEquals(myQueue.isEmpty(), false);
        assertEquals(myQueue.isFull(), false);
        myQueue.add(str1);
        assertEquals(myQueue.getNoOfElements(), 3);
        assertEquals(myQueue.isEmpty(), false);
        assertEquals(myQueue.isFull(), true);
        myQueue.removeHeadElement();
        assertEquals(myQueue.getNoOfElements(), 2);
        assertEquals(myQueue.isEmpty(), false);
        assertEquals(myQueue.isFull(), false);
        myQueue.removeHeadElement();
        myQueue.removeHeadElement();
        assertEquals(myQueue.getNoOfElements(), 0);
        assertEquals(myQueue.isEmpty(), true);
        assertEquals(myQueue.isFull(), false);
    }

    /**
     * Test
     */
    @Test
    void testQueue() {
        IQueue<String> myQueue = new ArrayQueue<>(3);
        assertEquals(myQueue.isEmpty(), true);
        final String str1 = "String1";
        final String str2 = "String2";
        final String str3 = "String3";
        final String str4 = "String4";
        final String str5 = "String5";

        myQueue.add(str1);
        myQueue.add(str2);
        myQueue.add(str3);
        myQueue.add(str4);

        assertEquals(myQueue.getHeadElement(), str1);
        assertEquals(myQueue.removeHeadElement(), str1);
        myQueue.add(str4);
        assertEquals(myQueue.removeHeadElement(), str2);
        assertEquals(myQueue.removeHeadElement(), str3);
        myQueue.add(str1);
        myQueue.add(str2);
        assertEquals(myQueue.removeHeadElement(), str4);
        assertEquals(myQueue.removeHeadElement(), str1);
        myQueue.add(str4);
        assertEquals(myQueue.removeHeadElement(), str2);
        assertEquals(myQueue.removeHeadElement(), str4);
        assertEquals(myQueue.isEmpty(), true);
    }

}
