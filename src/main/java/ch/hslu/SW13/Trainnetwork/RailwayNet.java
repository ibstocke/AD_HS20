/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW13.Trainnetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Philipp
 */
public class RailwayNet {

    private ArrayList<String> m_stationList;
    private LinkedList<LinkedList<Integer>> m_AdjMatrix;

    public RailwayNet() {
        m_stationList = new ArrayList<>();
        m_AdjMatrix = new LinkedList<>();
    }

    public boolean addStation(String newStation, HashMap<String, Integer> listOfConnections) {
        // there can only be one station with exact that name
        if (m_stationList.contains(newStation)) {
            return false;
        }
        m_stationList.add(newStation);
        final int iNoOfStations = m_stationList.size();

        // add on each column of the ADJ matrix one element
        for (int i = 0; i < m_AdjMatrix.size(); i++) {
            m_AdjMatrix.get(i).add(iNoOfStations - 1, Integer.MAX_VALUE);
        }

        // add new row to ADJ matrix
        LinkedList<Integer> tmpList = new LinkedList<>();
        for (int i = 0; i < iNoOfStations - 1; i++) {
            tmpList.add(Integer.MAX_VALUE);
        }
        tmpList.add(0);
        m_AdjMatrix.add(tmpList);

        // fill in the new connections between stations
        if (listOfConnections != null) {
            Set<String> keySet = listOfConnections.keySet();
            for (String connectsTo : keySet) {
                int iIdxConnection = getIndexOfStation(connectsTo);
                int iDistance = listOfConnections.get(connectsTo);
                if (iIdxConnection != -1) {
                    m_AdjMatrix.get(iNoOfStations - 1).set(iIdxConnection, iDistance);
                    m_AdjMatrix.get(iIdxConnection).set(iNoOfStations - 1, iDistance);
                }
            }
        }
        return true;
    }

    public boolean removeStation(String stationToRemove) {
        if (!m_stationList.contains(stationToRemove)) {
            return false;
        }

        final int iIdxOfStation = getIndexOfStation(stationToRemove);
        m_AdjMatrix.remove(iIdxOfStation);
        for (int i = 0; i < m_AdjMatrix.size(); i++) {
            m_AdjMatrix.get(i).remove(iIdxOfStation);
        }

        m_stationList.remove(stationToRemove);

        return true;
    }

    public int getNumberOfStations() {
        return m_stationList.size();
    }

    public HashMap<String, Integer> getDestinationMap(String startStation) {
        HashMap<String, Integer> distMap = new HashMap<>();
        if (!m_stationList.contains(startStation)) {
            return distMap;
        }

        final int iNoOfStations = getNumberOfStations();
        int[][] shortDist = new int[iNoOfStations][iNoOfStations];
        for (int i = 0; i < iNoOfStations; i++) {
            LinkedList<Integer> tmpList = m_AdjMatrix.get(i);
            for (int j = 0; j < tmpList.size(); j++) {
                shortDist[i][j] = tmpList.get(j);
            }
        }

        for (int y = 0; y < iNoOfStations; y++) {
            for (int i = 0; i < iNoOfStations; i++) {
                if (shortDist[i][y] < Integer.MAX_VALUE) {
                    for (int j = 0; j < iNoOfStations; j++) {
                        if (shortDist[y][j] < Integer.MAX_VALUE) {
                            if (shortDist[i][y] + shortDist[y][j] < shortDist[i][j]) {
                                shortDist[i][j] = shortDist[i][y] + shortDist[y][j];
                            }
                        }
                    }
                }
            }
        }

        final int iIdxStartStation = getIndexOfStation(startStation);
        for (int j = 0; j < shortDist.length; j++) {
            if (iIdxStartStation != j) {
                int iDistance = shortDist[j][iIdxStartStation];
                distMap.put(getStationName(j), iDistance);
            }
        }

        return distMap;
    }

    public boolean isDirectConnected(String stationOne, String stationTwo) {
        final int iIdxOne = getIndexOfStation(stationOne);
        final int iIdxTwo = getIndexOfStation(stationTwo);
        if (iIdxOne == -1 || iIdxTwo == -1) {
            return false;
        }
        return m_AdjMatrix.get(iIdxOne).get(iIdxTwo) != Integer.MAX_VALUE;
    }

    public void printADJMatrix() {
        for (LinkedList<Integer> tmpList : m_AdjMatrix) {
            String strLine = "";
            for (int i : tmpList) {
                strLine += "[" + i + "] ";
            }
            System.out.println(strLine);
        }
        System.out.println();
    }

    public void printStations() {
        for (String station : m_stationList) {
            int idx = getIndexOfStation(station);
            System.out.println(idx + ": " + station);
        }
        System.out.println();
    }

    private String getStationName(int index) {
        return m_stationList.get(index);
    }

    private int getIndexOfStation(String station) {
        return m_stationList.indexOf(station);
    }

    public static void main(String[] args) {
        RailwayNet graph = new RailwayNet();
        HashMap<String, Integer> connectionList = new HashMap<>();

        graph.addStation("Brugg", null);

        connectionList.put("Brugg", 13);
        graph.addStation("Aarau", connectionList);

        connectionList.clear();
        connectionList.put("Aarau", 8);
        connectionList.put("Brugg", 16);
        graph.addStation("Lenzburg", connectionList);

        connectionList.clear();
        connectionList.put("Lenzburg", 19);
        connectionList.put("Brugg", 16);
        graph.addStation("Dietikon", connectionList);

        connectionList.clear();
        connectionList.put("Lenzburg", 19);
        connectionList.put("Dietikon", 12);
        graph.addStation("Zürich", connectionList);

        connectionList.clear();
        connectionList.put("Aarau", 13);
        connectionList.put("Zürich", 36);
        graph.addStation("Olten", connectionList);

        connectionList.clear();
        connectionList.put("Olten", 7);
        connectionList.put("Lenzurg", 34);
        graph.addStation("Zofingen", connectionList);

        connectionList.clear();
        connectionList.put("Zofingen", 35);
        connectionList.put("Lenzburg", 80);
        graph.addStation("Luzern", connectionList);

        connectionList.clear();
        connectionList.put("Lenzburg", 9);
        connectionList.put("Dietikon", 30);
        graph.addStation("Wohlen", connectionList);

        connectionList.clear();
        connectionList.put("Wohlen", 23);
        connectionList.put("Luzern", 16);
        graph.addStation("Rotkreuz", connectionList);

        connectionList.clear();
        connectionList.put("Rotkreuz", 15);
        connectionList.put("Luzern", 30);
        graph.addStation("Arth-Golday", connectionList);

        connectionList.clear();
        connectionList.put("Rotkreuz", 12);
        connectionList.put("Zürich", 25);
        connectionList.put("Arth-Goldau", 20);
        graph.addStation("Zug", connectionList);

        connectionList.clear();
        connectionList.put("Zürich", 30);
        connectionList.put("Arth-Goldau", 39);
        graph.addStation("Pfäffikon", connectionList);

        graph.printADJMatrix();
        graph.printStations();

        HashMap<String, Integer> distMap = graph.getDestinationMap("Luzern");
        System.out.println("Luzern to xxx (min. time needed)");
        System.out.println(distMap);
    }
}
