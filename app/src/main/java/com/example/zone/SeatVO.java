package com.example.zone;

public class SeatVO {

  int Num;
  String ID;
  String Name;
  String Zone;


  public SeatVO()
  {

  }
  public  SeatVO(int Num, String ID,String Name,String Zone)
  {
    this.Num=Num;
    this.ID=ID;
    this.Name=Name;
    this.Zone=Zone;
  }



  public int getNum() {
    return Num;
  }

  public void setNum(int num) {
    Num = num;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
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
}