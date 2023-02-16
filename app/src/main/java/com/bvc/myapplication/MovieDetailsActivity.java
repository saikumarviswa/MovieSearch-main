package com.bvc.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView movie_picture;
    TextView movie_title,movie_year,movie_imdbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        String poster = intent.getStringExtra("Poster");
        String imdbId = intent.getStringExtra("imdbId");
        String movieName = intent.getStringExtra("movieName");
        String year = intent.getStringExtra("year");

        movie_picture = (ImageView)findViewById(R.id.movie_picture);
        movie_title = (TextView)findViewById(R.id.movie_title);
        movie_year = (TextView)findViewById(R.id.movie_year);
        movie_imdbId = (TextView)findViewById(R.id.movie_imdbId);

        Glide.with(getApplicationContext())
                .load(poster)
                .into(movie_picture);

        movie_title.setText(movieName);
        movie_year.setText(year);
        movie_imdbId.setText(imdbId);


    }


}