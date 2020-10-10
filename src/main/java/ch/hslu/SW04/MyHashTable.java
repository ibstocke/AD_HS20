/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

/**
 *
 * @author Philipp
 */
public class MyHashTable implements IMyHashTable {

    private Object[] m_hashTable;

    MyHashTable(int size) {
        m_hashTable = new Object[size];
    }

    @Override
    public boolean add(Object object) {
        if (isFull()) {
            return false;
        }

        int index = getIndex(object);

        while (m_hashTable[index] != null) {
            index++;
            index = index % m_hashTable.length;
        }
        m_hashTable[index] = object;
        return true;
    }

    @Override
    public Object remove(Object object) {
        if (isEmpty()) {
            return null;
        }

        int index = search(object);
        if (index < 0) {
            return null;
        }

        Object tmpObject = m_hashTable[index];
        m_hashTable[index] = null;
        return tmpObject;
    }

    @Override
    public int search(Object object) {
        if (isEmpty()) {
            return -1;
        }
        int iIndex = getIndex(object);
        final int iStartIndex = iIndex;
        while (!m_hashTable[iIndex].equals(object)) {
            iIndex++;
            iIndex = iIndex % m_hashTable.length;

            if (m_hashTable[iIndex] == null || iStartIndex == iIndex) {
                iIndex = -1;
                break;
            }
        }
        return iIndex;
    }

    @Override
    public boolean isEmpty() {
        return getNoOfObjects() == 0;
    }

    @Override
    public boolean isFull() {
        return getNoOfObjects() == m_hashTable.length;
    }

    @Override
    public String toString() {
        String strMyHashTable = "Contents of 'MyHashTable' (size: " + m_hashTable.length + ")\n";
        for (int i = 0; i < m_hashTable.length; i++) {
            strMyHashTable += "[" + i + "] -- ";
            strMyHashTable += m_hashTable[i] == null ? "EMPTY" : m_hashTable[i].toString();
            strMyHashTable += '\n';
        }

        return strMyHashTable;
    }

    private int getNoOfObjects() {
        int cnt = 0;
        for (Object tmp : m_hashTable) {
            if (tmp != null) {
                cnt++;
            }
        }
        return cnt;
    }

    private int getIndex(Object object) {
        return object.hashCode() % m_hashTable.length;
    }

    public static void main(String[] args) {
        IMyHashTable myHashTable = new MyHashTable(5);
        myHashTable.add(2);
        myHashTable.add(3);
        myHashTable.add(3);
        myHashTable.add(4);
        myHashTable.remove(4);
        myHashTable.remove(0);

        System.out.println(myHashTable);
    }

}
