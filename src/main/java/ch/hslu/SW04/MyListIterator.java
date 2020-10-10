/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

import java.util.Iterator;

/**
 *
 * @author Philipp
 */
public class MyListIterator<V> implements Iterator<V> {

    private ListNode<V> m_currentNode;
    private ListNode<V> m_prevNode;
    private final MyList<V> m_list;

    public MyListIterator(MyList<V> list) {
        m_list = list;
        m_currentNode = list.getHead();
        m_prevNode = list.getHead();
    }

    @Override
    public boolean hasNext() {
        return m_currentNode.getNextNode() != null;
    }

    @Override
    public V next() {
        if (m_currentNode == null) {
            return null;
        }
        V tmpValue = m_currentNode.getValue();
        m_prevNode = m_currentNode;
        m_currentNode = m_currentNode.getNextNode();
        return tmpValue;
    }

    @Override
    public void remove() {
        if (m_currentNode == m_prevNode) {
            m_list.setHead(m_currentNode.getNextNode());
        } else {
            m_prevNode.setNextNode(m_currentNode.getNextNode());
        }
        m_currentNode.destroyNode();
        m_currentNode = m_prevNode.getNextNode();
    }

}
