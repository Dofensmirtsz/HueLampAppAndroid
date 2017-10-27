package com.avans.huelampapp.ui.connect;

import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.local.PreferencesHelper;
import com.avans.huelampapp.data.model.HueError;
import com.avans.huelampapp.data.model.SuccessResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ConnectPresenter {

    private final DataManager dataManager;
    private final ConnectView view;

    public ConnectPresenter(DataManager dataManager, ConnectView view) {
        this.dataManager = dataManager;
        this.view = view;
    }

    public void connect() {

        if (dataManager.getPreferencesHelper().contains(PreferencesHelper.USERNAME)) {
            view.showSuccess("already connected: " + dataManager.getPreferencesHelper().getUsername());
            return;
        }

        dataManager.getUsername().enqueue(new Callback<SuccessResponse[]>() {
            @Override
            public void onResponse(Call<SuccessResponse[]> call, Response<SuccessResponse[]> response) {
                if(response.body()[0].getSuccess() != null) {
                    dataManager.getPreferencesHelper().setUsername(response.body()[0].getSuccess().getUsername());
                    view.showSuccess(response.body()[0].getSuccess().getUsername());
                } else {
                    view.showError(new HueError("101", "", "Press the link button first"));
                }
            }

            @Override
            public void onFailure(Call<SuccessResponse[]> call, Throwable t) {
                Timber.e(t);
                view.showError(new HueError("101", "", "link button not pressed"));
            }
        });

    }

    public void check() {
        if (dataManager.getPreferencesHelper().contains(PreferencesHelper.USERNAME)) {
            view.showSuccess(dataManager.getPreferencesHelper().getUsername());
            return;
        }
    }
}
