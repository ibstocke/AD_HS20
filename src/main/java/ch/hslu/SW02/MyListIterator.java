/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW02;

import java.util.Iterator;

/**
 *
 * @author Philipp
 */
public class MyListIterator<T> implements Iterator<T> {

    private Node<T> m_currentNode;
    private final MyList<T> m_list;

    public MyListIterator(MyList<T> list) {
        m_list = list;
        m_currentNode = list.getHead();
    }

    @Override
    public boolean hasNext() {
        return m_currentNode.getNextNode() != null;
    }

    /**
     *
     * @return data
     */
    @Override
    public T next() {
        if (m_currentNode == null) {
            return null;
        }
        T data = m_currentNode.getData();
        m_currentNode = m_currentNode.getNextNode();
        return data;
    }

    @Override
    public void remove() {
        Node<T> prevNode = m_list.getHead();
        if (m_currentNode == prevNode) {
            //its the first node which has to be deleted
            m_list.setHead(m_currentNode.getNextNode());
            m_currentNode.deleteNode();
        } else {
            while (prevNode.getNextNode() != m_currentNode) {
                prevNode = prevNode.getNextNode();
            }
            prevNode.setNextNode(m_currentNode.getNextNode());
            m_currentNode.deleteNode();
        }

    }

}
