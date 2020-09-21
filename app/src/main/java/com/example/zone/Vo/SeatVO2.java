package com.example.zone.Vo;

public class SeatVO2 {


  String ID;
  String Seat;//좌석유형
  String SeatInfo;//좌석현황


  public SeatVO2(String ID, String seat, String seatInfo) {
    this.ID = ID;
    Seat = seat;
    SeatInfo = seatInfo;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getSeat() {
    return Seat;
  }

  public void setSeat(String seat) {
    Seat = seat;
  }

  public String getSeatInfo() {
    return SeatInfo;
  }

  public void setSeatInfo(String seatInfo) {
    SeatInfo = seatInfo;
  }
}