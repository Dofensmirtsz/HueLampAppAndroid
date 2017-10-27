package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ErrorResponse implements Parcelable {

    private Error error;

    public ErrorResponse(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    protected ErrorResponse(Parcel in) {
        error = (Error) in.readValue(Error.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
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
