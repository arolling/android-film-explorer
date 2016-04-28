package com.epicodus.filmexplorer.services;

import android.util.Log;

import com.epicodus.filmexplorer.Constants;
import com.epicodus.filmexplorer.models.Movie;
import com.epicodus.filmexplorer.models.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    public void searchMovies(String search, Callback callback){
        String MOVIE_API_KEY = Constants.MOVIE_API_KEY;
        String SEARCH_BASE_URL = Constants.SEARCH_BASE_URL;

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(SEARCH_BASE_URL).newBuilder();

        urlBuilder.addPathSegment(Constants.TITLE_SEARCH);

        urlBuilder.addQueryParameter(Constants.MOVIE_QUERY, search);
        urlBuilder.addQueryParameter(Constants.API_QUERY, MOVIE_API_KEY);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Log.v(TAG, "search url: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void searchCredits(int movieId, Callback callback){
        String MOVIE_API_KEY = Constants.MOVIE_API_KEY;
        String MOVIE_BASE_URL = Constants.MOVIE_BASE_URL;
        String CREDITS_REQUEST = Constants.CREDITS_REQUEST;

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(MOVIE_BASE_URL).newBuilder();
        urlBuilder.addPathSegment("" + movieId);
        urlBuilder.addPathSegment(CREDITS_REQUEST);
        urlBuilder.addQueryParameter(Constants.API_QUERY, MOVIE_API_KEY);

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Log.v(TAG, "credits url: " + request);

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
                        genres[y] = Integer.parseInt(genreJSON.get(y).toString());
                    }
                    int id = filmJSON.getInt("id");
                    String title = filmJSON.getString("title");
                    String backdrop = filmJSON.getString("backdrop_path");
                    double voteAvg = filmJSON.getDouble("vote_average");

                    Movie movie = new Movie(title, id, overview, poster, backdrop, genres, release, voteAvg);
                    getMovieCredits(movie);
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

    public void getMovieCredits(final Movie movie){
        int movieId = movie.getMovieID();
        searchCredits(movieId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                processCredits(response, movie);
            }
        });
    }

    public Movie processCredits(Response response, Movie movie){
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject creditsJSON = new JSONObject(jsonData);
                int filmId = movie.getMovieID();
                JSONArray castJSON = creditsJSON.getJSONArray("cast");
                JSONArray crewJSON = creditsJSON.getJSONArray("crew");
                for (int i = 0; i < castJSON.length() && i < 8; i++){
                    JSONObject actorJSON = castJSON.getJSONObject(i);
                    String name = actorJSON.getString("name");
                    int id = actorJSON.getInt("id");
                    String profile_path = actorJSON.getString("profile_path");
                    String characterName = actorJSON.getString("character");
                    Person actor = new Person(name, id, profile_path, "cast");
                    actor.addCharacter(filmId, characterName);
                    movie.addCast(actor);
                }
                for (int j = 0; j < crewJSON.length(); j++){
                    JSONObject supportJSON = crewJSON.getJSONObject(j);
                    String job = supportJSON.getString("job");
                    if (job.equals("Director")){
                        String name = supportJSON.getString("name");
                        int id = supportJSON.getInt("id");
                        String profile_path = supportJSON.getString("profile_path");
                        Person director = new Person(name, id, profile_path, "director");
                        movie.addDirector(director);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException j){
            j.printStackTrace();;
        }
        return movie;
    }
}
