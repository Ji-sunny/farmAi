package com.farmai.DTO;

public class Macro {
    private String macroName;
    private String macroFile1;
    private String macroFile2;
    private String macroFile3;
    private String macroFile4;
    private String macroFile5;

    public Macro(String macroName, String macroFile1, String macroFile2, String macroFile3, String macroFile4, String macroFile5) {
        this.macroName = macroName;
        this.macroFile1 = macroFile1;
        this.macroFile2 = macroFile2;
        this.macroFile3 = macroFile3;
        this.macroFile4 = macroFile4;
        this.macroFile5 = macroFile5;
    }

    public Macro(String macroName) {
        this.macroName = macroName;
    }

    public String getMacroName() {
        return macroName;
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public String getMacroFile1() {
        return macroFile1;
    }

    public void setMacroFile1(String macroFile1) {
        this.macroFile1 = macroFile1;
    }

    public String getMacroFile2() {
        return macroFile2;
    }

    public void setMacroFile2(String macroFile2) {
        this.macroFile2 = macroFile2;
    }

    public String getMacroFile3() {
        return macroFile3;
    }

    public void setMacroFile3(String macroFile3) {
        this.macroFile3 = macroFile3;
    }

    public String getMacroFile4() {
        return macroFile4;
    }

    public void setMacroFile4(String macroFile4) {
        this.macroFile4 = macroFile4;
    }

    public String getMacroFile5() {
        return macroFile5;
    }

    public void setMacroFile5(String macroFile5) {
        this.macroFile5 = macroFile5;
    }

    @Override
    public String toString() {
        return "Macro{" +
                "macroName='" + macroName + '\'' +
                ", macroFile1='" + macroFile1 + '\'' +
                ", macroFile2='" + macroFile2 + '\'' +
                ", macroFile3='" + macroFile3 + '\'' +
                ", macroFile4='" + macroFile4 + '\'' +
                ", macroFile5='" + macroFile5 + '\'' +
                '}';
    }
}
