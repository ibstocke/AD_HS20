/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW12.quicksearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Philipp
 */
public class Quicksearch {

    private final String m_strPathOfFile;
    private final String m_strPattern;
    private final int[] m_shift;
    private final SortedMap<Integer, ArrayList<Integer>> m_occurenceList;

    public Quicksearch(String strFileName, String strPattern) {
        FindFile myFindFile = new FindFile(strFileName, Paths.get(".").toAbsolutePath().normalize().toFile());
        m_strPathOfFile = myFindFile.searchFile();
        m_strPattern = strPattern;
        m_occurenceList = new TreeMap<>();
        m_shift = new int[65535];
        initShift();
    }

    private void initShift() {
        final int m = m_strPattern.length();
        for (int i = 0; i < m_shift.length; i++) {
            m_shift[i] = m + 1;
        }
        for (int i = 0; i < m; i++) {
            m_shift[m_strPattern.charAt(i)] = m - i;
        }
    }

    public boolean search() {
        int lLineCnt = 0;
        ArrayList<Integer> foundOnIndexList;

        try {
            File myFile = new File(m_strPathOfFile);
            if (myFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(myFile));

                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    lLineCnt++;

                    foundOnIndexList = searchInString(strCurrentLine);
                    if (!foundOnIndexList.isEmpty()) {
                        ArrayList<Integer> tmpList = m_occurenceList.get(lLineCnt);

                        if (tmpList == null) {
                            tmpList = new ArrayList<>();
                        }
                        for (Integer iTmpIndex : foundOnIndexList) {
                            tmpList.add(iTmpIndex);
                        }
                        m_occurenceList.put(lLineCnt, tmpList);
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Quicksearch.class.getName()).log(Level.SEVERE, null, ex);
        }

        return !m_occurenceList.isEmpty();
    }

    public ArrayList<Integer> searchInString(String str) {
        final int m = m_strPattern.length();
        final int n = str.length();
        boolean fStopSearch = false;
        ArrayList<Integer> foundItems = new ArrayList<>();
        Object[][] patternMatrix = new Object[2][m];
        char[] pattern = new char[m];
        int[] compareOrder = new int[m];

        for (int i = 0; i < m; i++) {
            pattern[i] = m_strPattern.charAt(i);
            compareOrder[i] = i;
        }

        int i = 0;
        int j = 0;
        if (m <= n) {
            do {
                do {
                    if (str.charAt(i + compareOrder[j]) == pattern[j]) {      //   match
                        j++;
                    } else {                  //   mismatch
                        if ((i + m) < n) {    //   a.charAt(i1+m) is  not outside a
                            i += m_shift[str.charAt(i + m)];   // jump forward
                            if (j > 0) {
                                char tmp = pattern[j];
                                pattern[j] = pattern[0];
                                pattern[0] = tmp;
                                int iTmp = compareOrder[j];
                                compareOrder[j] = compareOrder[0];
                                compareOrder[0] = iTmp;
                            }
                            j = 0;
                        } else {
                            fStopSearch = true;
                            break;            // (mismatch) && (noshiftis possible)
                        }
                    }
                } while ((j < m) && ((i + m) <= n));// (patternp not found) && (end of a not reached)
                if (j == m) {
                    foundItems.add(i);                 //  patternfound
                    i = i + j;
                    j = 0;
                }
            } while ((i + m) <= n && !fStopSearch);
        }
        return foundItems;
    }

    public int getNumberOfHits() {
        int iNoOfHits = 0;
        for (Integer key : m_occurenceList.keySet()) {
            ArrayList<Integer> list = m_occurenceList.get(key);
            iNoOfHits += list.size();
        }
        return iNoOfHits;
    }

    public static void main(String[] args) {
        final String strFile = "book.txt";
        final String strPattern = "Blume";

        Quicksearch myQuicksearch = new Quicksearch(strFile, strPattern);
        boolean fFound = myQuicksearch.search();

        System.out.println("found '" + strPattern + "'? in '" + strFile + "' " + (fFound ? "yes" : "no") + " number of hits: " + myQuicksearch.getNumberOfHits());
        System.out.println();

    }

}
