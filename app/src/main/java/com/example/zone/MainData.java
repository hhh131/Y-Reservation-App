package com.example.zone;

import android.widget.Button;

public class MainData {
    private Button L_seat1;
    private Button L_seat2;
    private Button L_seat3;
    private Button R_seat1;
    private Button R_seat2;
    private Button R_seat3;

    public MainData(Button l_seat1, Button l_seat2, Button l_seat3, Button r_seat1, Button r_seat2, Button r_seat3) {
        L_seat1 = l_seat1;
        L_seat2 = l_seat2;
        L_seat3 = l_seat3;
        R_seat1 = r_seat1;
        R_seat2 = r_seat2;
        R_seat3 = r_seat3;
    }

    public Button getL_seat1() {
        return L_seat1;
    }

    public void setL_seat1(Button l_seat1) {
        L_seat1 = l_seat1;
    }

    public Button getL_seat2() {
        return L_seat2;
    }

    public void setL_seat2(Button l_seat2) {
        L_seat2 = l_seat2;
    }

    public Button getL_seat3() {
        return L_seat3;
    }

    public void setL_seat3(Button l_seat3) {
        L_seat3 = l_seat3;
    }

    public Button getR_seat1() {
        return R_seat1;
    }

    public void setR_seat1(Button r_seat1) {
        R_seat1 = r_seat1;
    }

    public Button getR_seat2() {
        return R_seat2;
    }

    public void setR_seat2(Button r_seat2) {
        R_seat2 = r_seat2;
    }

    public Button getR_seat3() {
        return R_seat3;
    }

    public void setR_seat3(Button r_seat3) {
        R_seat3 = r_seat3;
    }
}
