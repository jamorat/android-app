package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        populateScoreScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showScore(View v) {
        System.out.println("button has been clicked");
    }

    String[] name = {
            "Kardashian",
            "Beyonce",
            "Kanye",
            "Justin Bieber",
            "Khloe Kardashian",
            "Taylor Swift"
    };

    //Random date code, cut and pasted with American pride from
    //stackoverflow.com/questions/3985392/generate-random-date-of-birth
    public static String randomDate() {
        Random rnd;
        Date    dt;
        long    ms;

// Get a new random instance, seeded from the clock
        rnd = new Random();
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

// Construct a date
        dt = new Date(ms);
//        DateFormat dateFormat = new SimpleDateFormat(string: "dd/mm/yyyy hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss", Locale.ENGLISH);
        String strDate = dateFormat.format(dt);
        return strDate;


    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public void populateScoreScreen() {
        int score = randBetween(0,100);
        System.out.println("score: " + Integer.toString(score));

        int nameIndex = randBetween(0, name.length);
        System.out.println(nameIndex);

        System.out.println(name[nameIndex]);

        String date = randomDate();
        System.out.println("date:" + date);
    }


}
