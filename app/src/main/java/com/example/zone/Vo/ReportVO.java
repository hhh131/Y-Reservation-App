package com.example.zone.Vo;

public class ReportVO {
    int no;
    String id;
    String date;
    String ReportText;
    boolean process;
    String ReportingSeat;


    public ReportVO(int no, String id, String date, String reportText, boolean process, String reportingSeat) {
        this.no = no;
        this.id = id;
        this.date = date;
        ReportText = reportText;
        this.process = process;
        ReportingSeat = reportingSeat;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReportText() {
        return ReportText;
    }

    public void setReportText(String reportText) {
        ReportText = reportText;
    }

    public boolean isProcess() {
        return process;
    }

    public void setProcess(boolean process) {
        this.process = process;
    }

    public String getReportingSeat() {
        return ReportingSeat;
    }

    public void setReportingSeat(String reportingSeat) {
        ReportingSeat = reportingSeat;
    }
}