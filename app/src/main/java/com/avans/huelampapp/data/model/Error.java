package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Error implements Parcelable {

    private String type;

    private String address;

    private String description;

    public Error(String type, String address, String description) {
        this.type = type;
        this.address = address;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    protected Error(Parcel in) {
        type = in.readString();
        address = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(address);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };
}