package com.example.myapplication;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class ScoreItem implements Serializable {
    public String name;
    int score;
    long timestamp;
    String gender;


    public ScoreItem(String name, int score, long timestamp, String gender)  {
        this.name = name;
        this.score = score;
        this.timestamp = timestamp;
        this.gender = gender;

    }
}
