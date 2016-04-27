package com.epicodus.filmexplorer.models;

import android.util.Log;

import com.epicodus.filmexplorer.Constants;

import org.parceler.Parcel;

import java.util.HashMap;

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
    public String[] mGenreNames;
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
        this.mGenreNames = getGenreNames(genres);
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

    public String[] getGenres() {
        return mGenreNames;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    private String[] getGenreNames(int[] genreIds){
        String[] genreNames = new String[genreIds.length];
        HashMap<Integer, String> genreLookup = new HashMap<>();
        genreLookup.put(28, "Action");
        genreLookup.put(12, "Adventure");
        genreLookup.put(16, "Animation");
        genreLookup.put(35, "Comedy");
        genreLookup.put(80, "Crime");
        genreLookup.put(99, "Documentary");
        genreLookup.put(10751, "Family");
        genreLookup.put(14, "Fantasy");
        genreLookup.put(10769, "Foreign");
        genreLookup.put(36, "History");
        genreLookup.put(27, "Horror");
        genreLookup.put(10402, "Music");
        genreLookup.put(9648, "Mystery");
        genreLookup.put(10749, "Romance");
        genreLookup.put(878, "Science Fiction");
        genreLookup.put(10770, "TV Movie");
        genreLookup.put(53, "Thriller");
        genreLookup.put(10752, "War");
        genreLookup.put(37, "Western");
        genreLookup.put(18, "Drama");

        for(int i = 0; i < genreIds.length; i++){
            genreNames[i] = genreLookup.get(genreIds[i]);
        }

        return genreNames;
    }



}
