package com.avans.huelampapp.data.model;

import com.google.gson.annotations.SerializedName;

public class DeviceBody {

    @SerializedName("devicetype")
    private String type;

    public DeviceBody(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
