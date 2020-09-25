package com.example.zone.Vo;

public class UserVO {

    String ID;
    String PWD;
    int report;//신고횟수

    public UserVO(String ID, String PWD, int report) {
        this.ID = ID;
        this.PWD = PWD;
        this.report = report;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }
}