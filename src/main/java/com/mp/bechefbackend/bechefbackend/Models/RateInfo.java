package com.mp.bechefbackend.bechefbackend.Models;

import java.io.Serializable;

public class RateInfo implements Serializable {

    private long recipeId;

    private String userToken;

    private double rate;

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RateInfo{" +
                "recipeId=" + recipeId +
                ", userToken=" + userToken +
                ", rate=" + rate +
                '}';
    }
}
