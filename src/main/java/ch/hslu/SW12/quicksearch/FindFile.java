/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.SW12.quicksearch;

import java.io.File;

/**
 *
 * @author Philipp
 */
public class FindFile {

    private final String m_fileName;
    private final File m_startDir;
    private String m_filePath;

    public FindFile(String strFileName, File strStartDir) {
        m_fileName = strFileName;
        m_startDir = strStartDir;
        m_filePath = "";
    }

    public String searchFile() {
        m_filePath = "";
        findFile(m_fileName, m_startDir);
        return m_filePath;
    }

    private void findFile(String name, File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (fil.isDirectory()) {
                    findFile(name, fil);
                } else if (name.equalsIgnoreCase(fil.getName())) {
                    m_filePath = fil.toString();
                    return;
                }
            }
        }
    }

}
