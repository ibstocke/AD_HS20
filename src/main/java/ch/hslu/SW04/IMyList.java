/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

/**
 *
 * @author Philipp
 * @param <V> Generic value
 */
public interface IMyList<V> {

    public void add(V value);

    public boolean remove(V value);

    public boolean isEmpty();

    public boolean contains(V value);

    public int getLength();

    @Override
    public String toString();

    public MyListIterator<V> iterator();
}
