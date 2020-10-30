package com.example.zone;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_Test extends AppCompatActivity {

    private ArrayList<MainData> arrayList = new ArrayList<MainData>();
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12,
                   seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22,
                   seat23, seat24, seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32,
                   seat33, seat34, seat35, seat36, seat37, seat38, seat39, seat40, seat41, seat42,
                   seat43, seat44, seat45, seat46, seat47, seat48, seat49, seat50, seat51, seat52,
                   seat53, seat54, seat55, seat56, seat57, seat58, seat59, seat60, seat61, seat62,
                   seat63, seat64, seat65, seat66, seat67, seat68, seat69, seat70, seat71, seat72,
                   seat73, seat74, seat75, seat76, seat77, seat78, seat79, seat80, seat81, seat82, seat83, seat84;

    private Button[] btnarray = {seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12,
            seat13, seat14, seat15, seat16, seat17, seat18, seat19, seat20, seat21, seat22,
            seat23, seat24, seat25, seat26, seat27, seat28, seat29, seat30, seat31, seat32,
            seat33, seat34, seat35, seat36, seat37, seat38, seat39, seat40, seat41, seat42,
            seat43, seat44, seat45, seat46, seat47, seat48, seat49, seat50, seat51, seat52,
            seat53, seat54, seat55, seat56, seat57, seat58, seat59, seat60, seat61, seat62,
            seat63, seat64, seat65, seat66, seat67, seat68, seat69, seat70, seat71, seat72,
    seat73, seat74, seat75, seat76, seat77, seat78, seat79, seat80, seat81, seat82, seat83, seat84};
    
    private String[] btnName = {"seat1", "seat2", "seat3", "seat4", "seat5", "seat6", "seat7", "seat8", "seat9", "seat10", "seat11", "seat12",
            "seat13", "seat14", "seat15", "seat16", "seat17", "seat18", "seat19", "seat20", "seat21", "seat22",
            "seat23", "seat24", "seat25", "seat26", "seat27", "seat28", "seat29", "seat30", "seat31", "seat32",
            "seat33", "seat34", "seat35", "seat36", "seat37", "seat38", "seat39", "seat40", "seat41", "seat42",
            "seat43", "seat44", "seat45", "seat46", "seat47", "seat48", "seat49", "seat50", "seat51", "seat52",
            "seat53", "seat54", "seat55", "seat56", "seat57", "seat58", "seat59", "seat60", "seat61", "seat62",
            "seat63", "seat64", "seat65", "seat66", "seat67", "seat68", "seat69", "seat70", "seat71", "seat72",
            "seat73", "seat74", "seat75", "seat76", "seat77", "seat78", "seat79", "seat80", "seat81", "seat82", "seat83", "seat84"};    //버튼 이벤트는 이걸로 활용하면 될듯

    private int check = 0, i;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        context = this;

        seat1 = new Button(context);    seat2 = new Button(context);    seat3 = new Button(context);    seat4 = new Button(context);
        seat5 = new Button(context);    seat6 = new Button(context);    seat7 = new Button(context);    seat8 = new Button(context);
        seat9 = new Button(context);    seat10 = new Button(context);   seat11 = new Button(context);   seat12 = new Button(context);
        seat13 = new Button(context);   seat14 = new Button(context);   seat15 = new Button(context);   seat16 = new Button(context);
        seat17 = new Button(context);   seat18 = new Button(context);   seat19 = new Button(context);   seat20 = new Button(context);
        seat21 = new Button(context);   seat22 = new Button(context);   seat23 = new Button(context);   seat24 = new Button(context);
        seat25 = new Button(context);   seat26 = new Button(context);   seat27 = new Button(context);   seat28 = new Button(context);
        seat29 = new Button(context);   seat30 = new Button(context);   seat31 = new Button(context);   seat32 = new Button(context);
        seat33 = new Button(context);   seat34 = new Button(context);   seat35 = new Button(context);   seat36 = new Button(context);
        seat37 = new Button(context);   seat38 = new Button(context);   seat39 = new Button(context);   seat40 = new Button(context);
        seat41 = new Button(context);   seat42 = new Button(context);   seat43 = new Button(context);   seat44 = new Button(context);
        seat45 = new Button(context);   seat46 = new Button(context);   seat47 = new Button(context);   seat48 = new Button(context);
        seat49 = new Button(context);   seat50 = new Button(context);   seat51 = new Button(context);   seat52 = new Button(context);
        seat53 = new Button(context);   seat54 = new Button(context);   seat55 = new Button(context);   seat56 = new Button(context);
        seat57 = new Button(context);   seat58 = new Button(context);   seat59 = new Button(context);   seat60 = new Button(context);
        seat61 = new Button(context);   seat62 = new Button(context);   seat63 = new Button(context);   seat64 = new Button(context);
        seat65 = new Button(context);   seat66 = new Button(context);   seat67 = new Button(context);   seat68 = new Button(context);
        seat69 = new Button(context);   seat70 = new Button(context);   seat71 = new Button(context);   seat72 = new Button(context);
        seat73 = new Button(context);   seat74 = new Button(context);   seat75 = new Button(context);   seat76 = new Button(context);
        seat77 = new Button(context);   seat78 = new Button(context);   seat79 = new Button(context);   seat80 = new Button(context);
        seat81 = new Button(context);   seat82 = new Button(context);   seat83 = new Button(context);   seat84 = new Button(context);

        seat1.setText("1");     seat2.setText("2");     seat3.setText("3");     seat4.setText("4");     seat5.setText("5");     seat6.setText("6");
        seat7.setText("7");     seat8.setText("8");     seat9.setText("9");     seat10.setText("10");   seat11.setText("11");   seat12.setText("12");
        seat13.setText("13");   seat14.setText("14");   seat15.setText("15");   seat16.setText("16");   seat17.setText("17");   seat18.setText("18");
        seat19.setText("19");   seat20.setText("20");   seat21.setText("21");   seat22.setText("22");   seat23.setText("23");   seat24.setText("24");
        seat25.setText("25");   seat26.setText("26");   seat27.setText("27");   seat28.setText("28");   seat29.setText("29");   seat30.setText("30");
        seat31.setText("31");   seat32.setText("32");   seat33.setText("33");   seat34.setText("34");   seat35.setText("35");   seat36.setText("36");
        seat37.setText("37");   seat38.setText("38");   seat39.setText("39");   seat40.setText("40");   seat41.setText("41");   seat42.setText("42");
        seat43.setText("43");   seat44.setText("44");   seat45.setText("45");   seat46.setText("46");   seat47.setText("47");   seat48.setText("48");
        seat49.setText("49");   seat50.setText("50");   seat51.setText("51");   seat52.setText("52");   seat53.setText("53");   seat54.setText("54");
        seat55.setText("55");   seat56.setText("56");   seat57.setText("57");   seat58.setText("58");   seat59.setText("59");   seat60.setText("60");
        seat61.setText("61");   seat62.setText("62");   seat63.setText("63");   seat64.setText("64");   seat65.setText("65");   seat66.setText("66");
        seat67.setText("67");   seat68.setText("68");   seat69.setText("69");   seat70.setText("70");   seat71.setText("71");   seat72.setText("72");
        seat73.setText("73");   seat74.setText("74");   seat75.setText("75");   seat76.setText("76");   seat77.setText("77");   seat78.setText("78");
        seat79.setText("79");   seat80.setText("80");   seat81.setText("81");   seat82.setText("82");   seat83.setText("83");   seat84.setText("84");

        arrayList.add(new MainData(seat1, seat2, seat3, seat4, seat5, seat6));          arrayList.add(new MainData(seat7, seat8, seat9, seat10, seat11, seat12));
        arrayList.add(new MainData(seat13, seat14, seat15, seat16, seat17, seat18));    arrayList.add(new MainData(seat19, seat20, seat21, seat22, seat23, seat24));
        arrayList.add(new MainData(seat25, seat26, seat27, seat28, seat29, seat30));    arrayList.add(new MainData(seat31, seat32, seat33, seat34, seat35, seat36));
        arrayList.add(new MainData(seat37, seat38, seat39, seat40, seat41, seat42));    arrayList.add(new MainData(seat43, seat44, seat45, seat46, seat47, seat48));
        arrayList.add(new MainData(seat49, seat50, seat51, seat52, seat53, seat54));    arrayList.add(new MainData(seat55, seat56, seat57, seat58, seat59, seat60));
        arrayList.add(new MainData(seat61, seat62, seat63, seat64, seat65, seat66));    arrayList.add(new MainData(seat67, seat68, seat69, seat70, seat71, seat72));
        arrayList.add(new MainData(seat73, seat74, seat75, seat76, seat77, seat78));    arrayList.add(new MainData(seat79, seat80, seat81, seat82, seat83, seat84));

        myAdapter = new MyAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();


        /*for ( i = 0; i < btnarray.length; i++){
            btnarray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check == 0){
                        check = i + 1;

                    }
                    else{
                       showToast("신고는 하나의 좌석씩 가능합니다.");
                    }
                }
            });
        }*/

    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
