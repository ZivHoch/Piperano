package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ExtraOnPizzaAdapter extends ArrayAdapter {

    public ExtraOnPizzaAdapter(@NonNull Context context, ArrayList<ExtraOnPizzaItem> resource) {
        super(context,0, resource);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.extra_spinner_design,parent,false);
        }
        ImageView ballImage = convertView.findViewById(R.id.extra_type_for_pizza_image);
        TextView ballName = convertView.findViewById(R.id.extra_type_for_pizza_name);

        ExtraOnPizzaItem currentBall = (ExtraOnPizzaItem) getItem(position);
        if(currentBall != null){
            ballImage.setImageResource(currentBall.getExtraImage());
            ballName.setText(currentBall.getExtraName());
        }
        return convertView;
    }
}
