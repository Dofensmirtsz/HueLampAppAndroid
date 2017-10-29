package com.avans.huelampapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.avans.huelampapp.R;
import com.avans.huelampapp.data.DataManager;
import com.avans.huelampapp.data.model.HueError;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.ui.base.BaseActivity;
import com.avans.huelampapp.util.HueUtil;
import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

public class DetailActivity extends BaseActivity implements DetailView {

    private static final String LIGHT = "light";
    private static final String KEY = "key";
    private String key;
    private Light light;

    private DetailPresenter presenter;
    private Toolbar toolbar;
    private View root;

    private HSLColorPicker colorPicker;
    private ImageView indicator;
    private Switch statusSwitch;
    private EditText editName;

    public static Intent getStartIntent(Context context, String key, Light item) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY, key);
        intent.putExtra(LIGHT, item);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        presenter = new DetailPresenter(DataManager.instance(this), this);

        root = findViewById(R.id.rootview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        light = getIntent().getParcelableExtra(LIGHT);
        key = getIntent().getStringExtra(KEY);

        colorPicker = (HSLColorPicker) findViewById(R.id.color_picker);
        indicator = (ImageView) findViewById(R.id.image_color);
        statusSwitch = (Switch) findViewById(R.id.detail_switch_light_status);
        editName = (EditText) findViewById(R.id.edit_light_name);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(light.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        colorPicker.setColor(HueUtil.getColor(light));
        DrawableCompat.setTint(indicator.getDrawable(), HueUtil.getColor(light));
        colorPicker.setColorSelectionListener(new SimpleColorSelectionListener(){
            @Override
            public void onColorSelected(int color) {
                float[] hsv = new float[3];
                Color.colorToHSV(color, hsv);
                DrawableCompat.setTint(indicator.getDrawable(), color);
                presenter.changeLightColor(key, hsv);
            }
        });

        statusSwitch.setChecked(light.getState().getStatus());
        statusSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.toggleLight(light, key));
    }

    @Override
    public void showError(HueError error) {
        if (error == null) {
            return;
        }
        Snackbar.make(root, error.getDescription(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showStatus(boolean status) {
        statusSwitch.setChecked(status);
    }

    @Override
    public void showSuccess(String desc) {
        Snackbar.make(root, desc, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void updateName(String name) {
        getSupportActionBar().setTitle(name);
    }

    public void changeName(View view) {
        if (!editName.getText().toString().isEmpty()) {
            presenter.changeLightName(key, editName.getText().toString());
        }
    }
}
