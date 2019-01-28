package com.example.myapplication;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    EditText editText;
    ArrayList<ScoreItem> scoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        scoreData = new ArrayList<ScoreItem>();

        //Males
        scoreData.add(new ScoreItem("MALES",0,0, "male"));

        scoreData.add(new ScoreItem("Ryan",63,12345678993223L, "male"));
        scoreData.add(new ScoreItem("Sam",86,1536442851000L,"male"));
        scoreData.add(new ScoreItem("Joey",78,1546442992000L,"male"));

        //Females
        scoreData.add(new ScoreItem("FEMALES",0,0, "female"));

        scoreData.add(new ScoreItem("Melissa",91,1540341851000L,"female"));
        scoreData.add(new ScoreItem("Jess",93,1540341751000L, "female"));
        scoreData.add(new ScoreItem("Carly",89,1540341651000L, "female"));

        System.out.println("before sort down");

        for(ScoreItem item : scoreData){

            System.out.println(new String("beforesort : %s %s" + item.name + " " + item.timestamp));
        }
        System.out.println("before sort up");


        Collections.sort(scoreData, new Comparator<ScoreItem>() {
            @Override
            public int compare(ScoreItem o1, ScoreItem o2) {
                return Long.compare(Long.parseLong(String.valueOf(o1.timestamp)),
                        Long.parseLong(String.valueOf(o2.timestamp)));
            }
        });

        Collections.sort(scoreData, new Comparator<ScoreItem>() {
            @Override
            public int compare(ScoreItem o1, ScoreItem o2) {
                return o1.gender.compareTo(o2.gender);
            }
        });



        System.out.println("after sort down");
        for(ScoreItem item : scoreData){

            System.out.println(new String("beforesort : %s %s" + item.name + " " + item.timestamp + " " + item.gender));
        }
        System.out.println("after sort up");

        listView = (ListView) findViewById(R.id.ScoreListView);
        System.out.println("this is scoredata item name: " + scoreData.get(0).name);

        String[]scores = {};
        arrayList = new ArrayList<>();
        for(ScoreItem item : scoreData){
            if (item.name != "MALES" &&
                    item.name != "FEMALES") {
                Date dt = new Date(item.timestamp);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);

                String strDate = dateFormat.format(dt);

                arrayList.add(String.format("%s  %s%%\n%s", item.name, item.score, strDate));
            } else {
                arrayList.add(item.name);
            }


            System.out.println("iteration : " + item.name);
        }
        System.out.println("state of array list : " + arrayList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(position);
                System.out.println("Show scores");
            }
        });
    }

    public void showDetail(int position){
        if (scoreData.get(position).name != "MALES" &&
                scoreData.get(position).name != "FEMALES") {

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("ScoreItem", scoreData.get(position));
            startActivity(intent);
            System.out.println("Show detail");
        }
    }

    public void showScoresButtonPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        System.out.println("Show scores");
    }
}
