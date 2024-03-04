package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class PizzaItem implements Serializable {

    private String mdata;
    private Integer mImage;
    private String mPrice;
    private int mPizzaAmount;
    private String mDescription;

    public PizzaItem(String data, Integer image, String Price, int PizzaAmount, String Description) {
        mdata=data;
        mImage=image;
        mPizzaAmount=PizzaAmount;
        mDescription=Description;
        mPrice=Price;
    }
    //GETS:
    public void setmImage(Integer mImage) {
        this.mImage = mImage;
    }
    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
    public void setMdata(String mdata) {
        this.mdata = mdata;
    }
    public void setmPizzaAmount(int mPizzaAmount) {
        this.mPizzaAmount = mPizzaAmount;
    }
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    //SETS:
    public Integer getmImage() { return mImage; }
    public String getPrices() {
        return mPrice;
    }
    public String getMdata() {
        return mdata;
    }
    public int getmPizzaAmount() { return mPizzaAmount; }
    public String getmDescription() {
        return mDescription;
    }

}