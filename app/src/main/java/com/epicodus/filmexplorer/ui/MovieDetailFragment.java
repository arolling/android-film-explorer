package com.epicodus.filmexplorer.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.filmexplorer.R;
import com.epicodus.filmexplorer.adapters.PersonListAdapter;
import com.epicodus.filmexplorer.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    @Bind(R.id.overviewTextView) TextView mOverViewTextView;
    @Bind(R.id.detailRatingTextView) TextView mDetailRatingTextView;
    @Bind(R.id.releaseDateTextView) TextView mReleaseDateTextView;
    @Bind(R.id.detailGenresTextView) TextView mDetailGenresTextView;
    @Bind(R.id.directorRecyclerView) RecyclerView mDirectorRecyclerView;
    @Bind(R.id.castRecyclerView) RecyclerView mCastRecyclerView;
    @Bind(R.id.backdropImageView) ImageView mBackdropImageView;
    private PersonListAdapter mCastAdapter;
    private PersonListAdapter mCrewAdapter;
    private Movie mMovie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMovie.getBackdropUrl()).into(mBackdropImageView);
        mOverViewTextView.setText(mMovie.getOverview());
        mDetailRatingTextView.setText(Double.toString(mMovie.getVoteAverage()) + "/10");
        mReleaseDateTextView.setText(mMovie.getReleaseDate());
        mDetailGenresTextView.setText(android.text.TextUtils.join(", ", mMovie.getGenres()));


        mCastAdapter = new PersonListAdapter(this.getContext(), mMovie.getCast(), mMovie.getMovieID());
        mCastRecyclerView.setAdapter(mCastAdapter);
        RecyclerView.LayoutManager castLayoutManager = new LinearLayoutManager(this.getContext());
        mCastRecyclerView.setLayoutManager(castLayoutManager);
        mCastRecyclerView.setHasFixedSize(true);

        mCrewAdapter = new PersonListAdapter(this.getContext(), mMovie.getDirectors(), mMovie.getMovieID());
        mDirectorRecyclerView.setAdapter(mCrewAdapter);
        RecyclerView.LayoutManager crewLayoutManager = new LinearLayoutManager(this.getContext());
        mDirectorRecyclerView.setLayoutManager(crewLayoutManager);
        mDirectorRecyclerView.setHasFixedSize(true);

        return view;
    }

}
