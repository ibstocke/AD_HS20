/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Philipp
 */
public class MyHashTableTest {

    @Test
    void testAdd() {
        IMyHashTable myHashTable = new MyHashTable(5);
        assertEquals(myHashTable.isEmpty(), true);

        myHashTable.add(1);
        assertNotEquals(myHashTable.isEmpty(), true);
        myHashTable.add(2);
        myHashTable.add(3);
        myHashTable.add(4);
        assertNotEquals(myHashTable.isFull(), true);
        myHashTable.add(5);
        assertEquals(myHashTable.isFull(), true);
        assertNotEquals(myHashTable.add(7), true);
        assertEquals(myHashTable.remove(7), null);
        assertEquals(myHashTable.remove(3), 3);
        assertNotEquals(myHashTable.isFull(), true);
        assertEquals(myHashTable.add(7), true);
        assertEquals(myHashTable.remove(7), 7);

        assertEquals(true, true);
        assertNotEquals(true, false);
    }
}
