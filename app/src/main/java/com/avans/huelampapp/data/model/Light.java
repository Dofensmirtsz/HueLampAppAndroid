package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Light implements Parcelable {

    private  LightState state;

    private  String type;

    private  String name;

    @SerializedName("modelid")
    private  String modelId;

    @SerializedName("swversion")
    private  String swVersion;

    @SerializedName("pointsymbol")
    private HashMap<String, String> pointSymbol;

    public Light(LightState state, String type, String name, String modelId, String swVersion, HashMap<String, String> pointSymbol) {
        this.state = state;
        this.type = type;
        this.name = name;
        this.modelId = modelId;
        this.swVersion = swVersion;
        this.pointSymbol = pointSymbol;
    }

    public LightState getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getModelId() {
        return modelId;
    }

    public String getSwVersion() {
        return swVersion;
    }

    public Map<String, String> getPointSymbol() {
        return pointSymbol;
    }

    protected Light(Parcel in) {
        state = (LightState) in.readValue(LightState.class.getClassLoader());
        type = in.readString();
        name = in.readString();
        modelId = in.readString();
        swVersion = in.readString();
        pointSymbol = (HashMap) in.readValue(HashMap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(state);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(modelId);
        dest.writeString(swVersion);
        dest.writeValue(pointSymbol);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Light> CREATOR = new Parcelable.Creator<Light>() {
        @Override
        public Light createFromParcel(Parcel in) {
            return new Light(in);
        }

        @Override
        public Light[] newArray(int size) {
            return new Light[size];
        }
    };
}