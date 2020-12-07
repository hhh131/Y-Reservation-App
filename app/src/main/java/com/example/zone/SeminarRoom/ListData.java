package com.example.zone.SeminarRoom;

import android.widget.TextView;

public class ListData {
    private TextView subject;

    public ListData(TextView subject){
        this.subject = subject;
    }

    public TextView getSubject() {
        return subject;
    }

    public void setSubject(TextView subject) {
        this.subject = subject;
    }
}
