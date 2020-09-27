package com.example.zone.Vo;

public class ReservationVO {

    String ReservationType;
    String SeatNum;
    String id;
    String date;


    public ReservationVO(String reservationType, String seatNum, String id, String date) {
        ReservationType = reservationType;
        SeatNum = seatNum;
        this.id = id;
        this.date = date;
    }

    public String getReservationType() {
        return ReservationType;
    }

    public void setReservationType(String reservationType) {
        ReservationType = reservationType;
    }

    public String getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(String seatNum) {
        SeatNum = seatNum;
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
}
