package com.example.zone.Vo;

public class SeminarVO {

    String id;
    Boolean status;
    Boolean enter;

    public SeminarVO(String id, Boolean status, Boolean enter) {
        this.id = id;
        this.status = status;
        this.enter = enter;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getEnter() {
        return enter;
    }

    public void setEnter(Boolean enter) {
        this.enter = enter;
    }
}
