package com.avans.huelampapp.data;

import android.content.Context;

import com.avans.huelampapp.BuildConfig;
import com.avans.huelampapp.data.local.PreferencesHelper;
import com.avans.huelampapp.data.model.DeviceBody;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.data.model.SimpleState;
import com.avans.huelampapp.data.model.SuccessResponse;
import com.avans.huelampapp.data.remote.HueService;
import com.avans.huelampapp.network.ServiceProvider;

import java.util.Map;

import retrofit2.Call;

public class DataManager {

    private static DataManager instance;
    private final PreferencesHelper preferencesHelper;
    private final HueService hueService;

    public static DataManager instance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    private DataManager(Context context) {
        preferencesHelper = PreferencesHelper.instance(context);
        hueService = ServiceProvider.instance().createService(HueService.class);
    }

    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }

    public Call<SuccessResponse[]> getUsername() {
        return hueService.registerDevice(
                new DeviceBody(
                        BuildConfig.APPLICATION_ID + "#" + android.os.Build.MANUFACTURER
                ));
    }

    public Call<Map<String, Light>> getLights() {
        return hueService.getLights(preferencesHelper.getUsername());
    }

    public Call<Light> getLight(String id) {
        return hueService.getLight(preferencesHelper.getUsername(), id);
    }

    public Call<String> updateLightState(String id, SimpleState state) {
        return hueService.manageLightState(preferencesHelper.getUsername(), id, state);
    }

}
