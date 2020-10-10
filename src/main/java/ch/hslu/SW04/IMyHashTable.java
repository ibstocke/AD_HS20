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
public interface IMyHashTable {

    public boolean add(Object object);

    public Object remove(Object object);

    public int search(Object object);

    public boolean isEmpty();

    public boolean isFull();
}
