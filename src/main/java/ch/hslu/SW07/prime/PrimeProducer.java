/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW07.prime;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Philipp
 */
public class PrimeProducer implements Runnable {

    private final PrimeList m_primeList;

    PrimeProducer(PrimeList primeList) {
        m_primeList = primeList;
    }

    @Override
    public void run() {
        BigInteger bi;
        boolean fFoundPrime;
        while (m_primeList.assignJob()) {
            fFoundPrime = false;
            while (!fFoundPrime) {
                bi = new BigInteger(1024, new Random());
                if (bi.isProbablePrime(Integer.MAX_VALUE)) {
                    fFoundPrime = true;
                    m_primeList.addPrime(bi);
                }
            }
        }
    }

}
