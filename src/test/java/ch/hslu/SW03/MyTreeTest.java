/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Philipp
 */
public class MyTreeTest {

    /**
     * Test
     */
    @Test
    void testAdd() {
        IMyTree<Integer, String> myTree = new MyTree<>();
        myTree.add(10, "root");
        myTree.add(1, "Node1");
        myTree.add(6, "Node6");
        myTree.add(11, "Node11");
        assertEquals(myTree.add(5, "Node5"), true);
        assertEquals(myTree.add(11, "Node5"), false);

        assertEquals(myTree.search(5), true);
        assertEquals(myTree.search(11), true);

        assertEquals(myTree.getNoOfNodes(), 5);
        myTree.add(15, "Node15");
        assertEquals(myTree.getNoOfNodes(), 6);
    }

    /**
     * Test
     */
    @Test
    void testGetElement() {
        IMyTree<Integer, String> myTree = new MyTree<>();
        myTree.add(10, "root");
        myTree.add(1, "Node1");
        myTree.add(6, "Node6");
        myTree.add(11, "Node11");

        assertEquals(myTree.getValue(5), null);
        assertEquals(myTree.getValue(10), "root");
        assertEquals(myTree.getValue(11), "Node11");
    }

    /**
     * Test
     */
    @Test
    void testSetElement() {
        IMyTree<Integer, String> myTree = new MyTree<>();
        myTree.add(10, "root");
        myTree.add(1, "Node1");
        myTree.add(6, "Node6");
        myTree.add(11, "Node11");
        myTree.add(5, "Node5");

        assertNotEquals(myTree.getValue(5), "abc");
        assertEquals(myTree.setValue(5, "abc"), true);
        assertEquals(myTree.setValue(7, "abc"), false);
        assertEquals(myTree.getValue(5), "abc");
    }

    /**
     * Test
     */
    @Test
    void testRemoveElement() {
        IMyTree<Integer, String> myTree = new MyTree<>();
        myTree.add(10, "root");
        myTree.add(1, "Node1");
        myTree.add(6, "Node6");
        myTree.add(11, "Node11");
        myTree.add(5, "Node5");

        assertEquals(myTree.getValue(5), "Node5");
        assertEquals(myTree.remove(14), null);
        assertEquals(myTree.remove(5), "Node5");
        assertEquals(myTree.getValue(5), null);
        assertEquals(myTree.remove(5), null);
    }

}
