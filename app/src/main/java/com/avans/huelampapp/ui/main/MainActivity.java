package com.avans.huelampapp.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Switch;

import com.avans.huelampapp.R;
import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.HueError;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.ui.base.BaseActivity;
import com.avans.huelampapp.ui.connect.ConnectActivity;
import com.avans.huelampapp.ui.main.adapter.LightAdapter;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;

import java.util.Map;

public class MainActivity extends BaseActivity implements MainView {

    MainPresenter presenter;

    RecyclerView recyclerView;
    Toolbar toolbar;

    Switch toggleAllSwitch;
    ImageView background;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
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

        toggleAllSwitch = (Switch) findViewById(R.id.switch_all);
        toggleAllSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked)
                        -> presenter.toggleAllLights(isChecked)
        );

        background = (ImageView) findViewById(R.id.main_image_background);
        FlowingGradientClass flowingGradientBg = new FlowingGradientClass();
        flowingGradientBg.setBackgroundResource(R.drawable.translate_regular)
                .onImageView(background)
                .setTransitionDuration(4000)
                .start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (presenter != null) {
            presenter.loadLights();
        }

    }

    @Override
    public void showLights(Map<String, Light> lights) {
        recyclerView.setAdapter(new LightAdapter(lights, onCheckedListener));
    }

    @Override
    public void showError(HueError error) {
        if (error == null) {
            return;
        }
        startActivity(ConnectActivity.getStartIntent(this));
        finish();

    }

    private LightAdapter.OnCheckedListener onCheckedListener = ((key, light) -> presenter.toggleLight(key, light));
}
