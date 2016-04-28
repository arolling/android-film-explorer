package com.epicodus.filmexplorer.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.filmexplorer.R;
import com.epicodus.filmexplorer.adapters.MovieListAdapter;
import com.epicodus.filmexplorer.adapters.MoviePagerAdapter;
import com.epicodus.filmexplorer.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private MoviePagerAdapter adapterViewPager;
    ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new MoviePagerAdapter(getSupportFragmentManager(), mMovies);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
