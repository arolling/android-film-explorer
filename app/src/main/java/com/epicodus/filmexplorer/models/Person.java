package com.epicodus.filmexplorer.models;

import com.epicodus.filmexplorer.Constants;

import org.parceler.Parcel;

import java.util.HashMap;

/**
 * Created by Guest on 4/27/16.
 */
@Parcel
public class Person {
    public String mName;
    public int mPersonID;
    public String mPicture;
    public HashMap<Integer, String> mCharacters;
    public String mType;

    public Person(){

    }

    public String getName() {
        return mName;
    }

    public int getPersonID() {
        return mPersonID;
    }

    public String getPicture() {
        return mPicture;
    }

    public String getType() {
        return mType;
    }

    public HashMap<Integer, String> getCharacters() {
        return mCharacters;
    }

    public Person(String name, int id, String profile_path, String type){
        this.mName = name;
        this.mPersonID = id;
        this.mPicture = Constants.IMAGE_BASE_URL + profile_path.substring(1);
        this.mCharacters = new HashMap<>();
        this.mType = type;
    }

    public void addCharacter(int filmId, String character){
        mCharacters.put(filmId, character);
    }

}
