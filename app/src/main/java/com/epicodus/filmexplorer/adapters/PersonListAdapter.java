package com.epicodus.filmexplorer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.filmexplorer.R;
import com.epicodus.filmexplorer.models.Person;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/28/16.
 */
public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {
    private static final int MAX_WIDTH = 600;
    private static final int MAX_HEIGHT = 600;
    private ArrayList<Person> mPeople = new ArrayList<>();
    private Context mContext;
    private int mMovieid;

    public PersonListAdapter(Context context, ArrayList<Person> people, int movieId){
        mContext = context;
        mPeople = people;
        mMovieid = movieId;
    }

    @Override
    public PersonListAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonListAdapter.PersonViewHolder holder, int position) {
        holder.bindPerson(mPeople.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    public class PersonViewHolder extends  RecyclerView.ViewHolder {
        private Context mContext;
        @Bind(R.id.profileImageView) ImageView mProfileImage;
        @Bind(R.id.nameTextView) TextView mNameTextView;
        @Bind(R.id.characterNameTextView) TextView mCharacterTextView;
        @Bind(R.id.typeTextView) TextView mTypeTextView;

        public PersonViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPerson(Person person){
            if(person.getType().equals("cast")){
                mCharacterTextView.setText(person.getCharacters().get(mMovieid));
            } else {
                mTypeTextView.setText("Director");
            }

            Picasso.with(mContext).load(person.getPicture()).into(mProfileImage);
            mNameTextView.setText(person.getName());

        }
    }


}
