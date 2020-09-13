package com.example.zone.Vo;

public class UserVO {

    String ID;
    String Name;
    String pwd;



    public UserVO()
    {


    }
    public UserVO(String ID,String pwd,String Name)
    {
        this.ID = ID;
        this.Name = Name;
        this.pwd = pwd;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
