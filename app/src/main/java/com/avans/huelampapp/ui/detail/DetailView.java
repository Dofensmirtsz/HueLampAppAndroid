package com.avans.huelampapp.ui.detail;

import com.avans.huelampapp.ui.base.MvpView;

public interface DetailView extends MvpView {

    void showStatus(boolean status);

    void showSuccess(String desc);

    void updateName(String name);
}