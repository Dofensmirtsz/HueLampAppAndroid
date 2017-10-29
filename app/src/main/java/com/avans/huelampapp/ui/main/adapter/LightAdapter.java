package com.avans.huelampapp.ui.main.adapter;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avans.huelampapp.R;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.ui.detail.DetailActivity;
import com.avans.huelampapp.util.HueUtil;

import java.util.Map;

import io.fabianterhorst.isometric.IsometricView;

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder> {

    private Map<String, Light> data;
    private String[] keys;

    private OnCheckedListener onCheckedListener;

    private int[][] states = new int[][] {
            new int[] {-android.R.attr.state_checked},
            new int[] {android.R.attr.state_checked},
    };

    public LightAdapter(Map<String, Light> data, OnCheckedListener onCheckedListener) {
        this.data = data;
        this.keys = data.keySet().toArray(new String[data.size()]);
        this.onCheckedListener = onCheckedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_light, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Light item = getItem(position);

        int color = HueUtil.getColor(item);

        int[] thumbColors = new int[] {
                Color.WHITE,
                color,
        };

        holder.name.setText(item.getName());

        holder.lightSwitch.setChecked(item.getState().getStatus());
        holder.lightSwitch.setThumbTintList(new ColorStateList(states, thumbColors));
        holder.lightSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            onCheckedListener.onCheckedChange(keys[position], getItem(position));
        });

        HueUtil.createIsometricLight(holder.isometricView,
                item.getState().getStatus() ? color : android.graphics.Color.HSVToColor(new float[]{1.0f ,0.0f ,0.8f}),
                item.getState().getStatus());

        holder.itemView.setOnClickListener(view -> {
            holder.itemView.getContext().startActivity(DetailActivity.getStartIntent(holder.itemView.getContext(), keys[position], item));
        });

    }

    private Light getItem(int position) {
        return data.get(keys[position]);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        IsometricView isometricView;
        TextView name;
        SwitchCompat lightSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            isometricView = (IsometricView) itemView.findViewById(R.id.light_isometric_view);
            name = (TextView) itemView.findViewById(R.id.light_name);
            lightSwitch = (SwitchCompat) itemView.findViewById(R.id.light_switch_toggle);

        }
    }

    public interface OnCheckedListener {
        void onCheckedChange(String id, Light light);
    }
}

