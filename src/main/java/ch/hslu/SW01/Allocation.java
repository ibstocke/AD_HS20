/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW01;

import ch.hslu.SW02.*;
import java.util.Objects;

/**
 *
 * @author Philipp
 */
public class Allocation implements Comparable<Allocation> {

    private final int m_address;
    private final int m_size;

    public Allocation(int iAddress, int iSize) {
        m_address = iAddress;
        m_size = iSize;
    }

    public int getAddress() {
        return m_address;
    }

    public int getSize() {
        return m_size;
    }

    @Override
    public boolean equals(final Object objOther) {
        if (this == objOther) {
            return true;
        }
        if (!(objOther instanceof Allocation)) {
            return false;
        }
        final Allocation allocOther = (Allocation) objOther;
        return allocOther.m_address == m_address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_address, m_size);
    }

    @Override
    public String toString() {
        return "Allocation[Address:" + m_address + "; Size:" + m_size + "]";
    }

    @Override
    public int compareTo(Allocation allocOther) {
        return m_address - allocOther.m_address;
    }

}
