/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW02;

/**
 *
 * @author Philipp
 */
public interface IQueue<E> {

    public boolean add(E element);

    public Object getHeadElement();

    public Object removeHeadElement();

    public int getNoOfElements();

    public boolean isEmpty();

    public boolean isFull();
}
