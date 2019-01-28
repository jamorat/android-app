package com.example.myapplication;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ArrayList<ScoreItem> scoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        scoreData = new ArrayList<ScoreItem>();
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
                return o2.gender.compareTo(o1.gender);
            }
        });


        listView = (ListView) findViewById(R.id.ScoreListView);
        String[] scores = {};
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(position);
                System.out.println("Show scores");
            }
        });

        arrayList = new ArrayList<String>();
        arrayList.add("empty");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        loadTableContent();
    }

    ArrayList<ScoreItem> addHeaders(ArrayList<ScoreItem> unprocessedArray) {
        arrayList.removeAll(arrayList);
        ArrayList<ScoreItem> processedArray = new ArrayList<ScoreItem>();

        processedArray.add(new ScoreItem("MALES",0,0,"male"));
        String femaleSet = "false";
        for (ScoreItem item : unprocessedArray) {
            String g = new String(item.gender);
            if (femaleSet == "false" && item.gender.equals("female")) {
//                if (item.gender.equals("female")) {
                    System.out.println("ADDING FEMALES");
                    processedArray.add(new ScoreItem("FEMALES", 0, 0, "female"));
                    femaleSet = "true";
//                }
            }
            processedArray.add(item);
        }
        return processedArray;
    }

    void updateListItems(ArrayList<ScoreItem> data){
        arrayList.removeAll(arrayList);
        ArrayList<ScoreItem> preConditionedData = data;

        Collections.sort(preConditionedData, new Comparator<ScoreItem>() {
            @Override
            public int compare(ScoreItem o1, ScoreItem o2) {
                return Long.compare(Long.parseLong(String.valueOf(o1.timestamp)),
                        Long.parseLong(String.valueOf(o2.timestamp)));
            }
        });

        Collections.sort(scoreData, new Comparator<ScoreItem>() {
            @Override
            public int compare(ScoreItem o1, ScoreItem o2) {
                return o2.gender.compareTo(o1.gender);
            }
        });

        ArrayList<ScoreItem> conditionedData = addHeaders(preConditionedData);
        scoreData = conditionedData;
        System.out.println("Size to loop through:" + data.size());
        for (ScoreItem item : conditionedData) {
            if (item.name != "MALES" && item.name != "FEMALES") {
                Date dt = new Date(item.timestamp);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);

                String strDate = dateFormat.format(dt);
                arrayList.add(String.format("%s  %s%%\n%s", item.name, item.score, strDate));
            } else {
                arrayList.add(item.name);
            }
        }
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

    public void loadTableContent() {
        Thread thread = new Thread(new Runnable(){
            public void run() {
                try {
                    System.out.println("inside thread");

                    String ss = JavaUrlConnectionReader.getUrlContents("https://raw.githubusercontent.com/jamorat/android-app/master/app/src/main/res/endpointResource.json");
                    try {
                        JSONArray jsonArray = new JSONArray(ss);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            final String jsonName = jsonArray.getJSONObject(i).getString("name");
                            final int jsonScore = jsonArray.getJSONObject(i).getInt("score");
                            final long jsonTimestamp = jsonArray.getJSONObject(i).getLong("date_created");
                            final String jsonGender = jsonArray.getJSONObject(i).getString("gender");
                            System.out.println(jsonName);
                            System.out.println(jsonScore);
                            System.out.println(jsonTimestamp);


                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    scoreData.add(new ScoreItem(jsonName, jsonScore, jsonTimestamp, jsonGender));
                                }
                            });
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               // updateListItems(scoreData);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            updateListItems(scoreData);
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}

