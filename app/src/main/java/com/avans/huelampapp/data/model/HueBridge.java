package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class HueBridge implements Parcelable {

    @SerializedName("id")
    public String id;

    @SerializedName("internalipaddress")
    public String internalipaddress;

    @SerializedName("macaddress")
    @Nullable
    public String macaddress;

    @SerializedName("name")
    @Nullable
    public String name;

    public HueBridge(String id, String internalipaddress, String macaddress, String name) {
        this.id = id;
        this.internalipaddress = internalipaddress;
        this.macaddress = macaddress;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getInternalipaddress() {
        return internalipaddress;
    }

    @Nullable
    public String getMacaddress() {
        return macaddress;
    }

    @Nullable
    public String getName() {
        return name;
    }

    protected HueBridge(Parcel in) {
        id = in.readString();
        internalipaddress = in.readString();
        macaddress = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(internalipaddress);
        dest.writeString(macaddress);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<HueBridge> CREATOR = new Parcelable.Creator<HueBridge>() {
        @Override
        public HueBridge createFromParcel(Parcel in) {
            return new HueBridge(in);
        }

        @Override
        public HueBridge[] newArray(int size) {
            return new HueBridge[size];
        }
    };
}