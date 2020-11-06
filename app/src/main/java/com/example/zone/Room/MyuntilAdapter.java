package com.example.zone.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zone.R;

import java.util.List;

public class MyuntilAdapter  extends BaseAdapter {
    private List<ListData> untilData;

    public MyuntilAdapter(List<ListData> data){
        this.untilData = data;
    }

    @Override
    public int getCount() {
        return this.untilData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.untilData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_middlelist, viewGroup, false);

        TextView bynView = view.findViewById(R.id.btnday);

        ListData listData = untilData.get(position);
        bynView.setText(listData.getSubject());

        return view;
    }
}
