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
public class ArrayQueue<E> implements IQueue<E> {

    private final Object[] m_arrQueue;
    private final int m_size;
    private int m_headPointer;
    private int m_tailPointer;
    private int m_noOfElements;

    public ArrayQueue(int size) {
        m_headPointer = 0;
        m_tailPointer = 0;
        m_noOfElements = 0;
        m_size = size;
        m_arrQueue = new Object[m_size];
    }

    @Override
    public boolean add(E element) {
        if (isFull()) {
            return false;
        }
        m_arrQueue[m_tailPointer] = element;
        m_noOfElements++;
        if (!isFull()) {
            m_tailPointer++;
            m_tailPointer = m_tailPointer % m_size;
        }
        return true;
    }

    @Override
    public Object getHeadElement() {
        if (isEmpty()) {
            return null;
        }
        return m_arrQueue[m_headPointer];
    }

    @Override
    public Object removeHeadElement() {
        if (isFull()) {
            m_tailPointer = m_headPointer;
        }
        Object tmpObj = m_arrQueue[m_headPointer];
        m_arrQueue[m_headPointer] = null;
        m_noOfElements--;
        if (!isEmpty()) {
            m_headPointer++;
            m_headPointer = m_headPointer % m_size;
        }

        return tmpObj;
    }

    @Override
    public int getNoOfElements() {
        return m_noOfElements;
    }

    @Override
    public boolean isEmpty() {
        return m_noOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return m_noOfElements == m_size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty";
        }

        int cnt = 1;
        int tmpNextQueueElementPointer = m_headPointer;
        String queueString = "";
        for (int i = 0; i < m_noOfElements; i++) {
            Object tmpData = m_arrQueue[tmpNextQueueElementPointer];
            queueString = queueString + "(" + Integer.toString(cnt) + "): data: [" + tmpData.toString() + "]\n";
            cnt++;
            tmpNextQueueElementPointer++;
            tmpNextQueueElementPointer = tmpNextQueueElementPointer % m_size;
        }

        return queueString;
    }

}
