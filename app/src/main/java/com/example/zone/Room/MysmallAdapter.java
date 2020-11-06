package com.example.zone.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zone.R;

import java.util.List;

public class MysmallAdapter extends BaseAdapter {
        private List<ListData> semiData;

    public MysmallAdapter(List<ListData> data){
            this.semiData = data;
        }

        @Override
        public int getCount() {
            return this.semiData.size();
        }

        @Override
        public Object getItem(int position) {
            return this.semiData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_middlelist, viewGroup, false);

            TextView bynView = view.findViewById(R.id.btnday);

            ListData listData = semiData.get(position);
            bynView.setText(listData.getSubject());

            return view;
        }
}
