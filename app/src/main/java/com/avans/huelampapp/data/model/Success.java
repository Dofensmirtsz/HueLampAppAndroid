package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Success implements Parcelable {

    private String username;

    public Success(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    protected Success(Parcel in) {
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
    public static final Parcelable.Creator<Success> CREATOR = new Parcelable.Creator<Success>() {
        @Override
        public Success createFromParcel(Parcel in) {
            return new Success(in);
        }

        @Override
        public Success[] newArray(int size) {
            return new Success[size];
        }
    };
}