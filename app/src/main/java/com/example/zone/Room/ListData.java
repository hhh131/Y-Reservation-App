package com.example.zone.Room;

import android.widget.TextView;

public class ListData {
    private TextView subject;
    private int alpha;

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

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
