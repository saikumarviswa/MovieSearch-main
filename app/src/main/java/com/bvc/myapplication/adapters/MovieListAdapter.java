package com.bvc.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bvc.myapplication.MovieDetailsActivity;
import com.bvc.myapplication.R;
import com.bvc.myapplication.model.Movie;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieViewHolder> implements Filterable {

    Context context;
    ArrayList<Movie> movies = new ArrayList<>();

    public MovieListAdapter(Context context,ArrayList<Movie> movies){

        this.context = context;
        this.movies = movies;

    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);

        return new MovieViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie =movies.get(position);

        Glide.with(context)
                .load(movie.getPoster())
                .into(holder.movieImage);

        holder.imdbId.setText(movie.getImdbID());
        holder.movieName.setText(movie.getTitle());
        holder.year.setText(movie.getYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MovieDetailsActivity.class);
                intent.putExtra("Poster",movie.getPoster());
                intent.putExtra("imdbId",movie.getImdbID());
                intent.putExtra("movieName",movie.getTitle());
                intent.putExtra("year",movie.getYear());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
class MovieViewHolder extends RecyclerView.ViewHolder{

    ImageView movieImage;
    TextView movieName,year,imdbId;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImage = itemView.findViewById(R.id.movie_image);
        movieName = itemView.findViewById(R.id.movie_name);
        year = itemView.findViewById(R.id.year);
        imdbId = itemView.findViewById(R.id.imdbId);


    }
}