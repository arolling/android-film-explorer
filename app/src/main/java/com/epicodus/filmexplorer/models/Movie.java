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
    public String mBackgroundUrl;
    public int[] mGenreIDs;
    public String mReleaseDate;
    public double mVoteAverage;

//    public Movie(){
//
//    }


    public Movie(String title, int id, String overview, String poster, String background, int[] genres, String releaseDate, double voteAverage){
        this.mTitle = title;
        this.mMovieID = id;
        this.mOverview = overview;
        this.mPosterUrl = Constants.IMAGE_BASE_URL + poster.substring(2);
        Log.v("poster: ", this.mPosterUrl);
        this.mBackgroundUrl = Constants.IMAGE_BASE_URL + background.substring(2);
        this.mGenreIDs = genres;
        this.mReleaseDate = releaseDate;
        this.mVoteAverage = voteAverage;
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

    public String getBackgroundUrl() {
        return mBackgroundUrl;
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
