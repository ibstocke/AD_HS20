/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW09;

/**
 *
 * @author Philipp
 */
public interface IHeapInt {

    public boolean insert(int iValue);

    public int getMax();

    public int removeMax();

    public boolean isEmpty();

    public boolean isFull();

}
