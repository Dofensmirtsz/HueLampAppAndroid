package com.avans.huelampapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LightState implements Parcelable {

    @SerializedName("on")
    private boolean status;

    @Nullable
    @SerializedName("sat")
    private Integer saturation;

    @Nullable
    @SerializedName("bri")
    private Integer brightness;

    @Nullable
    @SerializedName("hue")
    private Integer hue;

    @Nullable
    private List<Float> xy;

    @SerializedName("ct")
    @Nullable
    private Integer colorTemperature;

    private String alert;

    @Nullable
    private String effect;

    @Nullable
    private String colormode;

    private boolean reachable;

    public LightState(boolean status, Integer saturation, Integer brightness, Integer hue,
                      List<Float> xy, Integer colorTemperature, String alert, String effect,
                      String colormode, boolean reachable) {
        this.status = status;
        this.saturation = saturation;
        this.brightness = brightness;
        this.hue = hue;
        this.xy = xy;
        this.colorTemperature = colorTemperature;
        this.alert = alert;
        this.effect = effect;
        this.colormode = colormode;
        this.reachable = reachable;
    }

    public boolean getStatus() {
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

    @Nullable
    public List<Float> getXy() {
        return xy;
    }

    @Nullable
    public Integer getColorTemperature() {
        return colorTemperature;
    }

    public String getAlert() {
        return alert;
    }

    @Nullable
    public String getEffect() {
        return effect;
    }

    @Nullable
    public String getColormode() {
        return colormode;
    }

    public boolean isReachable() {
        return reachable;
    }

    protected LightState(Parcel in) {
        status = in.readByte() != 0x00;
        saturation = in.readByte() == 0x00 ? null : in.readInt();
        brightness = in.readByte() == 0x00 ? null : in.readInt();
        hue = in.readByte() == 0x00 ? null : in.readInt();
        if (in.readByte() == 0x01) {
            xy = new ArrayList<Float>();
            in.readList(xy, Float.class.getClassLoader());
        } else {
            xy = null;
        }
        colorTemperature = in.readByte() == 0x00 ? null : in.readInt();
        alert = in.readString();
        effect = in.readString();
        colormode = in.readString();
        reachable = in.readByte() != 0x00;
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
        if (xy == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(xy);
        }
        if (colorTemperature == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(colorTemperature);
        }
        dest.writeString(alert);
        dest.writeString(effect);
        dest.writeString(colormode);
        dest.writeByte((byte) (reachable ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LightState> CREATOR = new Parcelable.Creator<LightState>() {
        @Override
        public LightState createFromParcel(Parcel in) {
            return new LightState(in);
        }

        @Override
        public LightState[] newArray(int size) {
            return new LightState[size];
        }
    };
}