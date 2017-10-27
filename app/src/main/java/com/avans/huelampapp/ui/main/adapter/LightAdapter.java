package com.avans.huelampapp.ui.main.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avans.huelampapp.R;
import com.avans.huelampapp.data.model.Light;
import com.avans.huelampapp.util.HueUtil;

import java.util.Map;

import io.fabianterhorst.isometric.IsometricView;

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder> {

    private Map<String, Light> data;
    private String[] keys;

    public LightAdapter(Map<String, Light> data) {
        this.data = data;
        this.keys = data.keySet().toArray(new String[data.size()]);
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
        holder.name.setText(item.getName());
        HueUtil.createIsometricLight(holder.isometricView, color, item.getState().getStatus());
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

        public ViewHolder(View itemView) {
            super(itemView);
            isometricView = (IsometricView) itemView.findViewById(R.id.light_isometric_view);
            name = (TextView) itemView.findViewById(R.id.light_name);
        }
    }
}

