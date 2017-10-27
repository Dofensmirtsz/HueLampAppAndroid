package com.avans.huelampapp.ui.connect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.avans.huelampapp.R;
import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.HueError;
import com.avans.huelampapp.ui.base.BaseActivity;
import com.avans.huelampapp.ui.main.MainActivity;

public class ConnectActivity extends BaseActivity implements ConnectView {

    ConnectPresenter presenter;

    View root;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ConnectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        presenter = new ConnectPresenter(DataManager.instance(this), this);
        root = findViewById(R.id.rootview);

        presenter.check();
    }

    public void connect(View view) {
        presenter.connect();
    }

    //start Mvp methods
    @Override
    public void showSuccess(String response) {
        Intent i = MainActivity.getStartIntent(this);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void showError(HueError error) {
        if (error == null) {
            return;
        }
        Snackbar.make(root, error.getDescription(), Snackbar.LENGTH_LONG).show();
    }
}
