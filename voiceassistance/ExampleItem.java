package com.example.voiceassistance;

public class ExampleItem {
    private int mImageResourse;
    private String mText;

    public ExampleItem(int ImageResourse, String text) {
        mImageResourse = ImageResourse;
        mText = text;
    }

    public int getImageResourse() {
        return mImageResourse;
    }

    public String getmText() {
        return mText;
    }
}