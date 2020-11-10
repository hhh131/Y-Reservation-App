package com.example.zone.Vo;

public class SeatVO {


  String ID;
  String SeatNum;//좌석유형
  String Time;//예약 시간
  Boolean status;//좌석현황

  public SeatVO(String ID, String seatNum, String time, Boolean status) {
    this.ID = ID;
    SeatNum = seatNum;
    Time = time;
    this.status = status;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getSeatNum() {
    return SeatNum;
  }

  public void setSeatNum(String seatNum) {
    SeatNum = seatNum;
  }

  public String getTime() {
    return Time;
  }

  public void setTime(String time) {
    Time = time;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}