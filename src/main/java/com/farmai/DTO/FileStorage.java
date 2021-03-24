package com.farmai.DTO;


public class FileStorage {
    private String tablesName;
    private String filesName;

    public FileStorage(String tablesName, String filesName) {
        this.tablesName = tablesName;
        this.filesName = filesName;
    }

    public String getTablesName() {
        return tablesName;
    }

    public void setTablesName(String tablesName) {
        this.tablesName = tablesName;
    }

    public String getFilesName() {
        return filesName;
    }

    public void setFilesName(String filesName) {
        this.filesName = filesName;
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "tablesName='" + tablesName + '\'' +
                ", filesName='" + filesName + '\'' +
                '}';
    }
}
