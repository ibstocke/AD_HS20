/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

import java.util.ArrayList;

/**
 *
 * @author Philipp
 */
public class MyHashTableList<V> implements IMyHashTableList<V> {

    private final ArrayList<MyList<V>> m_listArray;
    private final int m_size;

    public MyHashTableList(int size) {
        m_size = size;
        m_listArray = new ArrayList<>(size);
        initHashTable();
    }

    private void initHashTable() {
        for (int i = 0; i < m_size; i++) {
            m_listArray.add(i, new MyList<>());
        }
    }

    @Override
    public void add(V value) {
        final int iArrayIndex = calcArrayIndex(value);
        m_listArray.get(iArrayIndex).add(value);
    }

    @Override
    public boolean remove(V value) {
        final int iArrayIndex = calcArrayIndex(value);
        return m_listArray.get(iArrayIndex).remove(value);
    }

    @Override
    public boolean contains(V value) {
        final int iArrayIndex = calcArrayIndex(value);
        return m_listArray.get(iArrayIndex).contains(value);
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < m_size; i++) {
            if (!m_listArray.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        initHashTable();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < m_size; i++) {
            str = str + "[" + Integer.toString(i) + "]: " + m_listArray.get(i).toString() + "\n";
        }
        return str;
    }

    private int calcArrayIndex(V element) {
        return Math.abs(element.hashCode() % m_size);
    }

    public static void main(String[] args) {
        IMyHashTableList<String> myHashTable = new MyHashTableList<>(10);
        myHashTable.add("Anna");
        myHashTable.add("Alina");
        myHashTable.add("Alex");
        myHashTable.add("Arhur");

        myHashTable.add("Basil");
        myHashTable.add("Björn");
        myHashTable.add("Ben");
        myHashTable.add("Bruno");

        myHashTable.add("Clara");
        myHashTable.add("Chloe");
        myHashTable.add("Claire");
        myHashTable.add("Clarissa");

        myHashTable.add("David");
        myHashTable.add("Dario");
        myHashTable.add("Diana");

        myHashTable.add("Emily");
        myHashTable.add("Elia");
        myHashTable.add("Edith");
        myHashTable.add("Elena");

        myHashTable.add("Felix");
        myHashTable.add("Flavio");
        myHashTable.add("Fabio");
        myHashTable.add("Fiona");

        myHashTable.add("Gian");
        myHashTable.add("Gabriela");
        myHashTable.add("Giuliano");
        myHashTable.add("Greta");

        myHashTable.add("Hanna");
        myHashTable.add("Helene");
        myHashTable.add("Henry");
        myHashTable.add("Hailey");

        myHashTable.add("Isaac");
        myHashTable.add("Iris");
        myHashTable.add("Isabelle");

        myHashTable.add("Julian");
        myHashTable.add("Jan");
        myHashTable.add("Jonas");
        myHashTable.add("Jana");

        myHashTable.add("Kilian");
        myHashTable.add("Kevin");
        myHashTable.add("Kiara");
        myHashTable.add("Klara");

        myHashTable.add("Louis");
        myHashTable.add("Lea");
        myHashTable.add("Luca");
        myHashTable.add("Leonie");

        myHashTable.add("Maria");
        myHashTable.add("Max");
        myHashTable.add("Melissa");

        myHashTable.add("Nadja");
        myHashTable.add("Noah");
        myHashTable.add("Nico");
        myHashTable.add("Noel");

        myHashTable.add("Otto");
        myHashTable.add("Oliver");
        myHashTable.add("Olivia");
        myHashTable.add("Orlando");

        myHashTable.add("Philipp");
        myHashTable.add("Patrick");
        myHashTable.add("Pascal");
        myHashTable.add("Pia");

        myHashTable.add("Robin");
        myHashTable.add("Raphael");
        myHashTable.add("Rosalin");
        myHashTable.add("Remo");

        myHashTable.add("Sophia");
        myHashTable.add("Sara");
        myHashTable.add("Selina");
        myHashTable.add("Sam");

        myHashTable.remove("NixDa");
        myHashTable.remove("Maria");
        myHashTable.remove("Anna");

        System.out.println(myHashTable.toString());

    }
}
