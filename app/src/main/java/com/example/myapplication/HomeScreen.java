package com.example.myapplication;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeScreen extends AppCompatActivity {

    List<ScoreItem> scoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        scoreData = new List<ScoreItem>() {

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<ScoreItem> iterator() {
                return null;
            }

            @Nullable
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(@Nullable T[] a) {
                return null;
            }

            @Override
            public boolean add(ScoreItem scoreItem) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends ScoreItem> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends ScoreItem> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public ScoreItem get(int index) {
                return null;
            }

            @Override
            public ScoreItem set(int index, ScoreItem element) {
                return null;
            }

            @Override
            public void add(int index, ScoreItem element) {

            }

            @Override
            public ScoreItem remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<ScoreItem> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<ScoreItem> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<ScoreItem> subList(int fromIndex, int toIndex) {
                return null;
            }
        };


    }

    public void showScoresButtonPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        System.out.println("Show scores");
    }
}
