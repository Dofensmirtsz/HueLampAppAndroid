package com.avans.huelampapp.ui.detail;

import android.util.Log;

import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.data.model.SimpleState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DetailPresenter {


    private final DataManager dataManager;
    private final DetailView view;

    public DetailPresenter(DataManager dataManager, DetailView view) {
        this.dataManager = dataManager;
        this.view = view;
    }

    public void toggleLight(Light light, String key, float[] hsv) {
        dataManager.updateLightState(key,
                new SimpleState(true,
                        Math.round(hsv[1] * 254),
                        Math.round(hsv[2] * 254),
                        Math.round(hsv[0] * (65535.0f / 360.0f))))
                .enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Timber.d(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

    public void toggleLight(Light light, String key){
        dataManager.updateLightState(key,
                new SimpleState(!light.getState().getStatus()))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Timber.d(response.body());
                        view.setStatus(!light.getState().getStatus());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Timber.e(t);
                    }
                });
    }
}
