/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW02;

/**
 *
 * @author Philipp
 * @param <T> Generic type of data
 */
public class Node<T> {

    private Node<T> m_nextNode;
    private T m_Data;

    public Node(T data) {
        m_Data = data;
        m_nextNode = null;
    }

    public Node<T> getNextNode() {
        return m_nextNode;
    }

    public T getData() {
        return m_Data;
    }

    public void setNextNode(Node<T> nextNode) {
        m_nextNode = nextNode;
    }

    public void setData(T data) {
        m_Data = data;
    }

    public void deleteNode() {
        m_Data = null;
        m_nextNode = null;
    }
}
