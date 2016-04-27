package com.epicodus.filmexplorer.services;

import android.util.Log;

import com.epicodus.filmexplorer.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 4/27/16.
 */
public class MovieService {
    public static final String TAG = MovieService.class.getSimpleName();

    public void searchMovies(String search, String type, Callback callback){
        String MOVIE_API_KEY = Constants.MOVIE_API_KEY;
        String MOVIE_BASE_URL = Constants.MOVIE_BASE_URL;

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(MOVIE_BASE_URL).newBuilder();
        if(type.equals("Title")){
            urlBuilder.addPathSegment(Constants.TITLE_SEARCH);
        } else if (type.equals("Person")){
            urlBuilder.addPathSegment(Constants.PERSON_SEARCH);
        } else if (type.equals("Keyword")){
            urlBuilder.addPathSegment(Constants.MULTI_SEARCH);
        }

        urlBuilder.addQueryParameter(Constants.MOVIE_QUERY, search);
        urlBuilder.addQueryParameter(Constants.API_QUERY, MOVIE_API_KEY);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Log.v(TAG, "url: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
