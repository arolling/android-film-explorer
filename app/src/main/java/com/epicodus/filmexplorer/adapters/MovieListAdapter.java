package com.epicodus.filmexplorer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.filmexplorer.R;
import com.epicodus.filmexplorer.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Guest on 4/27/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies){
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.moviePosterImageView) ImageView mMoviePosterView;
        @Bind(R.id.movieTitleTextView) TextView mTitleTextView;
        @Bind(R.id.movieGenresTextView) TextView mGenresTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie) {
            Picasso.with(mContext).load(movie.getPosterUrl()).into(mMoviePosterView);
            mTitleTextView.setText(movie.getTitle());
            mGenresTextView.setText(android.text.TextUtils.join(", ", movie.getGenres()));
            mRatingTextView.setText("Rating: " + movie.getVoteAverage() + "/10");
        }
    }


}
