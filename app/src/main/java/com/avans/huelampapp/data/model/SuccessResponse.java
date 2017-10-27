package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SuccessResponse implements Parcelable {

    private Success success;

    public SuccessResponse(Success success) {
        this.success = success;
    }

    public Success getSuccess() {
        return success;
    }

    protected SuccessResponse(Parcel in) {
        success = (Success) in.readValue(Success.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SuccessResponse> CREATOR = new Parcelable.Creator<SuccessResponse>() {
        @Override
        public SuccessResponse createFromParcel(Parcel in) {
            return new SuccessResponse(in);
        }

        @Override
        public SuccessResponse[] newArray(int size) {
            return new SuccessResponse[size];
        }
    };
}