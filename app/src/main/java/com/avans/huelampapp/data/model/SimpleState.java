package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class SimpleState implements Parcelable {

    @SerializedName("on")
    private boolean status;

    @Nullable
    @SerializedName("sat")
    private  Integer saturation;

    @Nullable
    @SerializedName("bri")
    private  Integer brightness;

    @Nullable
    @SerializedName("hue")
    private  Integer hue;

    public SimpleState(boolean status, Integer saturation, Integer brightness, Integer hue) {
        this.status = status;
        this.saturation = saturation;
        this.brightness = brightness;
        this.hue = hue;
    }

    public SimpleState(boolean status){
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    @Nullable
    public Integer getSaturation() {
        return saturation;
    }

    @Nullable
    public Integer getBrightness() {
        return brightness;
    }

    @Nullable
    public Integer getHue() {
        return hue;
    }

    protected SimpleState(Parcel in) {
        status = in.readByte() != 0x00;
        saturation = in.readByte() == 0x00 ? null : in.readInt();
        brightness = in.readByte() == 0x00 ? null : in.readInt();
        hue = in.readByte() == 0x00 ? null : in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 0x01 : 0x00));
        if (saturation == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(saturation);
        }
        if (brightness == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(brightness);
        }
        if (hue == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(hue);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SimpleState> CREATOR = new Parcelable.Creator<SimpleState>() {
        @Override
        public SimpleState createFromParcel(Parcel in) {
            return new SimpleState(in);
        }

        @Override
        public SimpleState[] newArray(int size) {
            return new SimpleState[size];
        }
    };
}