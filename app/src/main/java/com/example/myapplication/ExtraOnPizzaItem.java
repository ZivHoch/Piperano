package com.example.myapplication;

//this class creates the extra ' it give it name and the appropriate image
public class ExtraOnPizzaItem {
    private String mExtraName;
    private int mExtraImage;
    public ExtraOnPizzaItem(String ExtraName, int ExtraImage) {
        mExtraName = ExtraName;
        mExtraImage = ExtraImage;
    }

    public String getExtraName() {
        return mExtraName;
    }

    public int getExtraImage() {
        return mExtraImage;
    }
}
