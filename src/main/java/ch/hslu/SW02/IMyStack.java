/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW02;

/**
 *
 * @author Philipp
 * @param <T> Generic type
 */
public interface IMyStack<T> {

    public void push(T data);

    public T pop();

}
