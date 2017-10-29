package com.avans.huelampapp.ui.detail;

import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.data.model.SimpleState;
import com.avans.huelampapp.data.model.SuccessResponse;

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

    public void changeLightColor(String key, float[] hsv) {
        dataManager.updateLightState(key,
                new SimpleState(true,
                        Math.round(hsv[1] * 254),
                        Math.round(hsv[2] * 254),
                        Math.round(hsv[0] * (65535.0f / 360.0f))))
                .enqueue(new Callback<SuccessResponse[]>() {
            @Override
            public void onResponse(Call<SuccessResponse[]> call, Response<SuccessResponse[]> response) {
                Timber.d(response.body()[0].toString());
            }

            @Override
            public void onFailure(Call<SuccessResponse[]> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

    public void toggleLight(Light light, String id){
        dataManager.updateLightState(id,
                new SimpleState(!light.getState().getStatus()))
                .enqueue(new Callback<SuccessResponse[]>() {
                    @Override
                    public void onResponse(Call<SuccessResponse[]> call, Response<SuccessResponse[]> response) {
                        view.showStatus(!light.getState().getStatus());
                    }

                    @Override
                    public void onFailure(Call<SuccessResponse[]> call, Throwable t) {
                        Timber.e(t);
                    }
                });
    }

    public void changeLightName(String id, String name) {
        dataManager.updateLightName(id, name).enqueue(new Callback<SuccessResponse[]>() {
            @Override
            public void onResponse(Call<SuccessResponse[]> call, Response<SuccessResponse[]> response) {
                view.showSuccess("Updated light name");
                view.updateName(name);
            }

            @Override
            public void onFailure(Call<SuccessResponse[]> call, Throwable t) {
                //view.showError(new HueError("", "", t.getLocalizedMessage()));
            }
        });
    }
}
