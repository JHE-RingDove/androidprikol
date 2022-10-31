package com.example.myapplication_test2;

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

public class Adapter extends ArrayAdapter<Data> {
    private Context mContext;
    private int mResourse;
    public Adapter(@NonNull Context context,int resourse,@NonNull ArrayList<Data> objects){
        super(context,resourse,objects);
        this.mContext = context;
        this.mResourse = resourse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResourse, parent, false);
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView text = convertView.findViewById(R.id.text);
        imageView.setImageBitmap(getItem(position).getImage());
        text.setText(getItem(position).getText());
        return convertView;
    }
}
