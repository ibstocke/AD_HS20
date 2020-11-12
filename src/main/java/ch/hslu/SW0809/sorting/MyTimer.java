/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW0809.sorting;

/**
 *
 * @author Philipp
 */
public class MyTimer {

    private long m_lStartTime;
    private long m_lStopTime;

    public void startTimer() {
        m_lStopTime = 0;
        m_lStartTime = System.currentTimeMillis();
    }

    public void stopTimer() {
        m_lStopTime = System.currentTimeMillis();
    }

    public long getTimePassed() {
        return m_lStopTime - m_lStartTime;
    }

}
