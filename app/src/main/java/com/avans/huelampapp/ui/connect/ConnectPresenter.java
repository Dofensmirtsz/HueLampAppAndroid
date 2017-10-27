package com.avans.huelampapp.ui.connect;

import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.local.PreferencesHelper;

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

    }

    public void check() {
        if (dataManager.getPreferencesHelper().contains(PreferencesHelper.USERNAME)) {
            view.showSuccess(dataManager.getPreferencesHelper().getUsername());
            return;
        }
    }
}
