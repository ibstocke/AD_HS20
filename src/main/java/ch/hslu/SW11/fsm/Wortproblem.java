/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package ch.hslu.SW11.fsm;

import java.util.ArrayList;

enum FSMStates {
    z0,
    z1,
    z2,
    z3,
    z4,
    z5
}

/**
 *
 * @author Philipp
 */
public class Wortproblem {

    public static boolean isWordLanguage(final String string) {
        final int strLen = string.length();
        if (strLen == 0) {
            return false;
        }

        FSMStates actState = FSMStates.z0;
        for (int i = 0; i < strLen && actState != FSMStates.z5; i++) {

            final char nextChar = string.charAt(i);
            if (nextChar != '0' && nextChar != '1') {
                actState = FSMStates.z5;
                break;
            }

            switch (actState) {
                case z0:
                    if (nextChar == '0') {
                        actState = FSMStates.z1;
                    } else {
                        actState = FSMStates.z5;
                    }
                    break;
                case z1:
                    if (nextChar == '1') {
                        actState = FSMStates.z2;
                    } else {
                        actState = FSMStates.z5;
                    }
                    break;
                case z2:
                    if (nextChar == '1') {
                        actState = FSMStates.z3;
                    } else {
                        actState = FSMStates.z4;
                    }
                    break;
                case z3:
                    if (nextChar == '1') {
                        actState = FSMStates.z2;
                    } else {
                        actState = FSMStates.z5;
                    }
                    break;
                case z4:
                    if (nextChar == '1') {
                        actState = FSMStates.z2;
                    } else {
                        actState = FSMStates.z5;
                    }
                    break;
                case z5:
                    break;
            }
        }

        return (actState == FSMStates.z1 || actState == FSMStates.z4);
    }

    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("0");
        strList.add("010");
        strList.add("01110");
        strList.add("010111010");
        strList.add("1010");
        strList.add("0110");
        strList.add("010010");

        for (int i = 0; i < strList.size(); i++) {
            final String strCurrent = strList.get(i);
            System.out.println(strCurrent + ":\t\t " + (isWordLanguage(strCurrent) ? "OK" : "not OK"));
        }
    }

}
