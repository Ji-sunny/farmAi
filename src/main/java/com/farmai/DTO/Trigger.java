package com.farmai.DTO;


public class Trigger {
    private String tablesName;
    private String modelName;
    private String macroName;
    private String colsX;
    private String colY;

    public Trigger(String tablesName, String modelName, String macroName, String colsX, String colY) {
        this.tablesName = tablesName;
        this.modelName = modelName;
        this.macroName = macroName;
        this.colsX = colsX;
        this.colY = colY;
    }

    public String getTablesName() {
        return tablesName;
    }

    public void setTablesName(String tablesName) {
        this.tablesName = tablesName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMacroName() {
        return macroName;
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public String getColsX() {
        return colsX;
    }

    public void setColsX(String colsX) {
        this.colsX = colsX;
    }

    public String getColY() {
        return colY;
    }

    public void setColY(String colY) {
        this.colY = colY;
    }

    @Override
    public String toString() {
        return "Macro{" +
                "tablesName='" + tablesName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", macroName='" + macroName + '\'' +
                ", colsX='" + colsX + '\'' +
                ", colY='" + colY + '\'' +
                '}';
    }
}
