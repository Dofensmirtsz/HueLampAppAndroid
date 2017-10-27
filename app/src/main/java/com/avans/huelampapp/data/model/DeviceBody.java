package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DeviceBody implements Parcelable {

    @SerializedName("devicetype")
    private String type;

    public DeviceBody(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    protected DeviceBody(Parcel in) {
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DeviceBody> CREATOR = new Parcelable.Creator<DeviceBody>() {
        @Override
        public DeviceBody createFromParcel(Parcel in) {
            return new DeviceBody(in);
        }

        @Override
        public DeviceBody[] newArray(int size) {
            return new DeviceBody[size];
        }
    };
}