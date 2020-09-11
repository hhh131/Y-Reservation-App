package com.example.zone;

public class UserVO2 {

    String ID;
    String PWD;
    String Name;
    String Zone;
    String SeatNum;


    public UserVO2(String ID, String PWD, String name, String zone, String seatNum) {
        this.ID = ID;
        this.PWD = PWD;
        Name = name;
        Zone = zone;
        SeatNum = seatNum;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(String seatNum) {
        SeatNum = seatNum;
    }
}
