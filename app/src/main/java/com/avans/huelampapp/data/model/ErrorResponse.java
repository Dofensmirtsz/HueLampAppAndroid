package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ErrorResponse implements Parcelable {

    private HueError hueError;

    public ErrorResponse(HueError hueError) {
        this.hueError = hueError;
    }

    public HueError getHueError() {
        return hueError;
    }

    protected ErrorResponse(Parcel in) {
        hueError = (HueError) in.readValue(HueError.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(hueError);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ErrorResponse> CREATOR = new Parcelable.Creator<ErrorResponse>() {
        @Override
        public ErrorResponse createFromParcel(Parcel in) {
            return new ErrorResponse(in);
        }

        @Override
        public ErrorResponse[] newArray(int size) {
            return new ErrorResponse[size];
        }
    };
}
