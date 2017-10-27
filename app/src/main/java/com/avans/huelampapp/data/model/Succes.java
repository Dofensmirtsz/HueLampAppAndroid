package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Succes implements Parcelable {

    private String username;

    public Succes(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    protected Succes(Parcel in) {
        username = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Succes> CREATOR = new Parcelable.Creator<Succes>() {
        @Override
        public Succes createFromParcel(Parcel in) {
            return new Succes(in);
        }

        @Override
        public Succes[] newArray(int size) {
            return new Succes[size];
        }
    };
}