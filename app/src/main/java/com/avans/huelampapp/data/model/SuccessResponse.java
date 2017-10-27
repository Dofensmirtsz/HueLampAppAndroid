package com.avans.huelampapp.data.model;

import com.google.gson.annotations.SerializedName;

public class SuccessResponse {

    private Succes succes;

    public SuccessResponse(Succes succes) {
        this.succes = succes;
    }

    public Succes getSucces() {
        return succes;
    }
}
