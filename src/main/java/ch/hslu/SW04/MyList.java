/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW04;

/**
 *
 * @author Philipp
 * @param <V> Generic type
 */
public class MyList<V> implements Iterable<V>, IMyList<V> {

    private ListNode<V> m_head;

    public MyList() {
        m_head = null;
    }

    @Override
    public void add(V element) {
        ListNode<V> newNode = new ListNode<>(element);
        newNode.setNextNode(m_head);
        m_head = newNode;
    }

    @Override
    public boolean remove(V element) {
        if (isEmpty()) {
            return false;
        }

        ListNode<V> currentNode = m_head;
        ListNode<V> prevNode = m_head;
        while (currentNode != null) {
            if (currentNode.getValue().equals(element)) {
                if (currentNode == m_head) {
                    m_head = currentNode.getNextNode();
                } else {
                    prevNode.setNextNode(currentNode.getNextNode());
                }
                currentNode.destroyNode();
                return true;
            }
            prevNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return m_head == null;
    }

    @Override
    public boolean contains(V element) {
        if (isEmpty()) {
            return false;
        }
        ListNode<V> currentNode = m_head;

        while (currentNode != null) {
            if (currentNode.getValue().equals(element)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    @Override
    public int getLength() {
        ListNode<V> currentNode = m_head;
        int iNoOfElements = 0;
        while (currentNode != null) {
            iNoOfElements++;
            currentNode = currentNode.getNextNode();
        }
        return iNoOfElements;
    }

    @Override
    public String toString() {
        int iLength = getLength();
        if (iLength == 0) {
            return "List is empty";
        }

        ListNode<V> currentNode = m_head;
        String strList = "";

        for (int i = 1; i <= iLength; i++) {
            if (currentNode == null) {
                break;
            }
            strList = strList + "(" + Integer.toString(i) + ")\"" + currentNode.getValue().toString() + "\", ";
            currentNode = currentNode.getNextNode();
        }
        return strList;
    }

    @Override
    public MyListIterator<V> iterator() {
        return new MyListIterator<>(this);
    }

    public ListNode<V> getHead() {
        return m_head;
    }

    public void setHead(ListNode<V> newNode) {
        m_head = newNode;
    }

}
