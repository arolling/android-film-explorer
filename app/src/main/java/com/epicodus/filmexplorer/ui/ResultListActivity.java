package com.epicodus.filmexplorer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.filmexplorer.R;
import com.epicodus.filmexplorer.models.Movie;
import com.epicodus.filmexplorer.services.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultListActivity extends AppCompatActivity {
    public static final String TAG = ResultListActivity.class.getSimpleName();
    public ArrayList<Movie> mMovies = new ArrayList<>();
    @Bind(R.id.resultListView) ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        String type = intent.getStringExtra("type");
        searchMovies(search, type);
    }

    private void searchMovies(String search, String type){
        final MovieService movieService = new MovieService();

        movieService.searchMovies(search, type, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = movieService.processMovies(response);

                ResultListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] movieTitles = new String[mMovies.size()];
                        for(int i = 0; i < movieTitles.length; i++) {
                            movieTitles[i] = mMovies.get(i).getTitle();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ResultListActivity.this, android.R.layout.simple_list_item_1, movieTitles);
                        mListView.setAdapter(adapter);
                        Log.d(TAG, "number: " + mMovies.size());
                        for (Movie movie : mMovies) {

                        }
                    }
                });

            }
        });
    }
}
