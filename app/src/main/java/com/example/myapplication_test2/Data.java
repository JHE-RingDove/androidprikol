package com.example.myapplication_test2;

import android.graphics.Bitmap;

public class Data {
    Bitmap image;
    String text;

    public Data(Bitmap image, String text) {
        this.image = image;
        this.text = text;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
