package com.example.zone.Vo;

public class ReportVO {

    String id;
    String date;
    String ReportText;


    public ReportVO(String id, String date, String reportText) {
        this.id = id;
        this.date = date;
        ReportText = reportText;
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
}
