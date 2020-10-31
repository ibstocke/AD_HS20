/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW07.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Philipp
 */
public class PrimeList {

    private int m_iRemainingPrimes;
    private final ArrayList<BigInteger> m_primeList;
    private final Object m_listLock;
    private final Object m_countLock;

    public PrimeList(int iNoOfPrimesRequired) {
        m_iRemainingPrimes = iNoOfPrimesRequired;
        m_primeList = new ArrayList<>();
        m_listLock = new Object();
        m_countLock = new Object();
    }

    public boolean isJobCompleted() {
        synchronized (m_countLock) {
            return m_iRemainingPrimes == 0;
        }
    }

    public boolean assignJob() {
        synchronized (m_countLock) {
            if (m_iRemainingPrimes > 0) {
                m_iRemainingPrimes--;
                return true;
            }
            return false;
        }
    }

    public void addPrime(BigInteger Bi) {
        synchronized (m_listLock) {
            m_primeList.add(Bi);
        }
    }

    public Iterator<BigInteger> getIterator() {
        return m_primeList.iterator();
    }

}
