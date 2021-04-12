package com.farmai.DTO;


public class MacroDone {
    private String macroName;
    private String score;
    private String report;
    private String kind;

    public MacroDone(String macroName, String score, String report, String kind) {
        this.macroName = macroName;
        this.score = score;
        this.report = report;
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMacroName() {
        return macroName;
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "MacroDone{" +
                "macroName='" + macroName + '\'' +
                ", score='" + score + '\'' +
                ", report='" + report + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
