/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW03;

/**
 *
 * @author Philipp
 */
public interface IMyTree<K, V> {

    public boolean add(K key, V value);

    public boolean search(K key);

    public V getValue(K key);

    public boolean setValue(K key, V value);

    public V remove(K key);

    public int getNoOfNodes();

    public boolean isEmpty();

    @Override
    public String toString();

}
