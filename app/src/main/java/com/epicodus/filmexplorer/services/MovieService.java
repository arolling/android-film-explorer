package com.epicodus.filmexplorer.services;

import android.util.Log;

import com.epicodus.filmexplorer.Constants;
import com.epicodus.filmexplorer.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public ArrayList<Movie> processMovies(Response response){
        ArrayList<Movie> movies = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject filmDatabaseJSON = new JSONObject(jsonData);
                JSONArray moviesJSON = filmDatabaseJSON.getJSONArray("results");
                for (int i = 0; i < moviesJSON.length(); i++){
                    JSONObject filmJSON = moviesJSON.getJSONObject(i);
                    String poster = filmJSON.getString("poster_path");
                    String overview = filmJSON.getString("overview");
                    String release = filmJSON.getString("release_date"); // CHANGE TO DATE FORMAT????
                    JSONArray genreJSON = filmJSON.getJSONArray("genre_ids");

                    int[] genres = new int[genreJSON.length()];
                    for(int y = 0; y < genreJSON.length(); y++){
                        genres[i] = Integer.parseInt(genreJSON.get(i).toString());
                    }
                    int id = filmJSON.getInt("id");
                    String title = filmJSON.getString("title");
                    String backdrop = filmJSON.getString("backdrop_path");
                    double voteAvg = filmJSON.getDouble("vote_average");

                    Movie movie = new Movie(title, id, overview, poster, backdrop, genres, release, voteAvg);
                    movies.add(movie); // add checking for adult movies here if necessary
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException j){
            j.printStackTrace();;
        }

        return movies;
    }
}
