package com.avans.huelampapp.ui.main;

import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.ui.base.MvpView;

import java.util.Map;

public interface MainView extends MvpView {
    void showLights(Map<String, Light> body);
}
