package com.example.zone.Vo;

public class UserVO {

    String ID;
    String PWD;
    int report;//신고횟수
    String token;

    public UserVO(String ID, String PWD, int report, String token) {
        this.ID = ID;
        this.PWD = PWD;
        this.report = report;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}