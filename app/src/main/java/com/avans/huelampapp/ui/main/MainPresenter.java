package com.avans.huelampapp.ui.main;

import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.HueError;
import com.avans.huelampapp.data.model.Light;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private DataManager dataManager;
    private MainView view;

    public MainPresenter(DataManager dataManager, MainView view) {
        this.dataManager = dataManager;
        this.view = view;
    }


    public void loadLights() {

        dataManager.getLights().enqueue(new Callback<Map<String, Light>>() {
            @Override
            public void onResponse(Call<Map<String, Light>> call, Response<Map<String, Light>> response) {
                view.showLights(response.body());
            }

            @Override
            public void onFailure(Call<Map<String, Light>> call, Throwable t) {
                dataManager.getPreferencesHelper().clear();
                view.showError(new HueError("", "", "unauthorized"));
            }
        });

    }
}
