package com.avans.huelampapp.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.avans.huelampapp.R;
import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.HueError;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.ui.base.BaseActivity;
import com.avans.huelampapp.ui.connect.ConnectActivity;
import com.avans.huelampapp.ui.main.adapter.LightAdapter;

import java.util.Map;

public class MainActivity extends BaseActivity implements MainView {

    MainPresenter presenter;

    RecyclerView recyclerView;
    Toolbar toolbar;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(DataManager.instance(this), this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lampkes");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadLights();
    }

    @Override
    public void showLights(Map<String, Light> lights) {
        recyclerView.setAdapter(new LightAdapter(lights));
    }

    @Override
    public void showError(HueError error) {
        if (error == null) {
            return;
        }
        startActivity(ConnectActivity.getStartIntent(this));
        finish();

    }
}
