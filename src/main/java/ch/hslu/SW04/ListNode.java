/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

/**
 *
 * @author Philipp
 * @param <V> generic value
 */
public class ListNode<V> {

    private final V m_value;
    private ListNode<V> m_nextNode;

    ListNode(V value) {
        m_value = value;
        m_nextNode = null;
    }

    public V getValue() {
        return m_value;
    }

    public void setNextNode(ListNode<V> nextNode) {
        m_nextNode = nextNode;
    }

    public ListNode<V> getNextNode() {
        return m_nextNode;
    }

    public void destroyNode() {
        m_nextNode = null;
    }
}
