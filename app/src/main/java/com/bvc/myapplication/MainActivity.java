package com.bvc.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bvc.myapplication.Utils.Utils;
import com.bvc.myapplication.adapters.MovieListAdapter;
import com.bvc.myapplication.model.Movie;
import com.bvc.myapplication.model.Search;
import com.bvc.myapplication.network.APIService;
import com.bvc.myapplication.network.ApiUtils;
import com.bvc.myapplication.network.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Animation mSlideUp;
    private Animation mSlideDown;
    CardView searchLayout;
    ImageView closeSearch;
    ImageView movieSearch;
    EditText searchMovieName;
    Search searchMovies;
    MovieListAdapter movieListAdapter;
    RecyclerView recyclerView;
    LinearLayout search_layot;

    APIService apiService = ApiUtils.getAPIService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        searchLayout = (CardView)findViewById(R.id.searchLayout);
        closeSearch = (ImageView)findViewById(R.id.closeSearchLayoutImage);
        movieSearch = (ImageView)findViewById(R.id.movieSearch);
        searchMovieName = (EditText) findViewById(R.id.searchMovieName);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        search_layot = (LinearLayout)findViewById(R.id.search_layot);

        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layout_slid_up);
                mSlideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layut_slide_down);

                searchLayout.setAnimation(mSlideUp);
                searchLayout.setVisibility(View.GONE);

            }
        });

        movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchMovieName.getText().toString().isEmpty()){

                    mSlideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layout_slid_up);
                    mSlideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layut_slide_down);

                    searchLayout.setAnimation(mSlideUp);
                    searchLayout.setVisibility(View.GONE);

                    //implement retrofit
                    getMoviesFromServer(searchMovieName.getText().toString().trim());
                    searchMovieName.setText("");


                }else {
                    Toast.makeText(getApplicationContext(),"Please entae the Movie Name",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void getMoviesFromServer(String movieName) {

            final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "", "Loading");
            apiService.getMovies(Utils.APIKEY,movieName,Utils.TYPE).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if (response != null) {
                        Log.w("**********", "Http" + response.toString());
                        dialog.dismiss();
                        if (response.code() == Utils.OK) {

                            searchMovies = new Gson().fromJson(response.body().getAsJsonObject(), Search.class);
                            Log.i("**********", "onResponse: "+searchMovies);

                            if(!searchMovies.equals(null)){
                                setDataToAdapter(searchMovies);
                            }else {
                                Toast.makeText(getApplicationContext(),"Sorry There is no movies with the name",Toast.LENGTH_LONG).show();
                            }


                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    dialog.dismiss();

                }
            });
        }


    private void setDataToAdapter(Search searchMovies) {
        Log.i(TAG, "setDataToAdapter: ---------------"+searchMovies.getSearch());

        if(searchMovies.getSearch() != null){
            movieListAdapter = new MovieListAdapter(MainActivity.this,searchMovies.getSearch());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView.setAdapter(movieListAdapter);
            search_layot.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_right_to_left);

            recyclerView.setLayoutAnimation(controller);

            recyclerView.scheduleLayoutAnimation();
        }else {
            Toast.makeText(getApplicationContext(),"Sorry There is no movies with the name",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {


            case R.id.action_search:
                /*Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent1);*/
                mSlideUp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layout_slid_up);
                mSlideDown = AnimationUtils.loadAnimation(MainActivity.this, R.anim.search_layut_slide_down);

                searchLayout.setAnimation(mSlideDown);
                searchLayout.setVisibility(View.VISIBLE);

                return true;

            /*case R.id.action_search:

                TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.toolbar));
                MenuItemCompat.expandActionView(item);
                return true;*/




            default:
                return super.onOptionsItemSelected(item);
        }
    }

}