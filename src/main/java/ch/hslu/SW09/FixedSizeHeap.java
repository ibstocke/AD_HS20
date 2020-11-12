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
public class FixedSizeHeap implements IHeapInt {

    private final int[] m_heap;
    private int m_iNoOfElements;

    public FixedSizeHeap(int iDeep) {
        m_heap = new int[(int) Math.pow(2, iDeep)];
        m_iNoOfElements = 0;
    }

    @Override
    public boolean insert(int iValue) {
        if (isFull()) {
            return false;
        }

        m_heap[m_iNoOfElements] = iValue;
        m_iNoOfElements++;

        reorganizeRise();

        return true;
    }

    @Override
    public int getMax() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        return m_heap[0];
    }

    @Override
    public int removeMax() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }

        int iMaxElement = m_heap[0];
        if (m_iNoOfElements > 1) {
            m_heap[0] = m_heap[m_iNoOfElements - 1];
            m_heap[m_iNoOfElements - 1] = 0;
        } else {
            m_heap[0] = 0;
        }
        m_iNoOfElements--;
        reorganizeSink();

        return iMaxElement;
    }

    @Override
    public boolean isEmpty() {
        return m_iNoOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return m_iNoOfElements == m_heap.length;
    }

    private void reorganizeRise() {
        if (m_iNoOfElements <= 1) {
            return;
        }

        int iItmp;
        int iIndexSon = m_iNoOfElements - 1;
        int iIndexFather = getFatherIndex(iIndexSon);
        while (iIndexFather >= 0) {
            if (m_heap[iIndexFather] < m_heap[iIndexSon]) {
                iItmp = m_heap[iIndexFather];
                m_heap[iIndexFather] = m_heap[iIndexSon];
                m_heap[iIndexSon] = iItmp;
                iIndexSon = iIndexFather;
                iIndexFather = getFatherIndex(iIndexSon);
            } else {
                break;
            }
        }
    }

    private void reorganizeSink() {
        if (m_iNoOfElements <= 1) {
            return;
        }

        int iItmp;
        int iIndexFather = 0;
        int iNoOfChildren = getNumberOfChildren(iIndexFather);
        int iBigestIndex;
        while (iNoOfChildren > 0) {
            iBigestIndex = iIndexFather;
            for (int i = 0; i < iNoOfChildren; i++) {
                if (m_heap[getLeftChildIndex(iIndexFather) + i] > m_heap[iBigestIndex]) {
                    iBigestIndex = getLeftChildIndex(iIndexFather) + i;
                }
            }
            if (iBigestIndex != iIndexFather) {
                iItmp = m_heap[iIndexFather];
                m_heap[iIndexFather] = m_heap[iBigestIndex];
                m_heap[iBigestIndex] = iItmp;
                iIndexFather = iBigestIndex;
                iNoOfChildren = getNumberOfChildren(iIndexFather);
            } else {
                break;
            }
        }
    }

    private int getFatherIndex(int iIndex) {
        if (iIndex == 0) {
            return -1;
        }
        return (iIndex - 1) / 2;
    }

    private int getLeftChildIndex(int iIndex) {
        return 2 * iIndex + 1;
    }

    private int getRightChildIndex(int iIndex) {
        return (iIndex + 1) * 2;
    }

    private int getNumberOfChildren(int iIndex) {
        int iNoOfChildren = 0;
        if (getLeftChildIndex(iIndex) < m_iNoOfElements) {
            iNoOfChildren++;
        }
        if (getRightChildIndex(iIndex) < m_iNoOfElements) {
            iNoOfChildren++;
        }
        return iNoOfChildren;
    }

}
