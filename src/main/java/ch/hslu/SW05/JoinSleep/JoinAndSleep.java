/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW05.JoinSleep;

import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Philipp
 */
public class JoinAndSleep implements Runnable {

    private static final Logger m_LOGGER = LogManager.getLogger(JoinAndSleep.class);
    private Thread m_dependentThread;
    private int m_iSleepMs;

    JoinAndSleep(Thread dependentThread, int iSleepMs) {
        m_dependentThread = dependentThread;
        m_iSleepMs = iSleepMs;
    }

    @Override
    public void run() {
        Thread localThread = Thread.currentThread();
        String localThreadName = localThread.getName();
        String dependentThreadName = m_dependentThread != null ? m_dependentThread.getName() : "NONE";
        m_LOGGER.info("Thread: \"{}\" run(), waiting for thread \"{}\" termination", localThreadName, dependentThreadName);

        if (m_dependentThread != null) {
            try {
                m_dependentThread.join();
            } catch (InterruptedException ex) {
                m_LOGGER.error("ERROR thraed: localThreadName exception at join(): {}", ex.getMessage());
            }
            m_LOGGER.info("Thread: \"{}\" run(), thread \"{}\" has terminated", localThreadName, dependentThreadName);
        }

        m_LOGGER.info("Thread: \"{}\" run(), goint to sleep for {} ms", localThreadName, String.valueOf(m_iSleepMs));
        try {
            Thread.sleep(m_iSleepMs);
        } catch (InterruptedException ex) {
            m_LOGGER.error("ERROR thraed: localThreadName exception at sleep: {}", ex.getMessage());
        }
        m_LOGGER.info("Thread: \"{}\" run(), woke up, reached end of run() --> Termination", localThreadName);
    }

    public static void main(String[] args) {
        Thread t3 = new Thread(new JoinAndSleep(null, 4000), "Thread3");
        Thread t2 = new Thread(new JoinAndSleep(t3, 3000), "Thread2");
        Thread t1 = new Thread(new JoinAndSleep(t2, 2000), "Thread1");

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(JoinAndSleep.class.getName()).log(Level.SEVERE, null, ex);
        }

        t1.interrupt();

    }

}
