package com.epicodus.filmexplorer.models;

import android.util.Log;

import com.epicodus.filmexplorer.Constants;

import org.parceler.Parcel;

/**
 * Created by Guest on 4/27/16.
 */
@Parcel
public class Movie {


    public String mTitle;
    public int mMovieID;
    public String mOverview;
    public String mPosterUrl;
    public String mBackdropUrl;
    public int[] mGenreIDs;
    public String mReleaseDate;
    public double mVoteAverage;

    public Movie(){

    }


    public Movie(String title, int id, String overview, String poster, String backdrop, int[] genres, String releaseDate, double voteAverage){
        this.mTitle = title;
        this.mMovieID = id;
        this.mOverview = overview;
        this.mPosterUrl = makeImageUrl(poster);
        Log.v("poster: ", this.mPosterUrl);
        this.mBackdropUrl = makeImageUrl(backdrop);
        this.mGenreIDs = genres;
        this.mReleaseDate = releaseDate;
        this.mVoteAverage = voteAverage;
    }

    private String makeImageUrl(String imageFragment){
        if(imageFragment.equals("null")){
            return "http://paulabrown.net/coen-brothers-movies-with-steve-buscemi-210.png";
        } else {
            return Constants.IMAGE_BASE_URL + imageFragment.substring(1);
        }

    }

    public String getTitle() {
        return mTitle;
    }

    public int getMovieID() {
        return mMovieID;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getBackdropUrl() {
        return mBackdropUrl;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public int[] getGenreIDs() {
        return mGenreIDs;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }



}
