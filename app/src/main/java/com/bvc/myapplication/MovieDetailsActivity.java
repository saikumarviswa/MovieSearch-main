package com.bvc.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        String poster = intent.getStringExtra("Poster");
        String imdbId = intent.getStringExtra("imdbId");
        String movieName = intent.getStringExtra("movieName");
        String year = intent.getStringExtra("year");
    }
}