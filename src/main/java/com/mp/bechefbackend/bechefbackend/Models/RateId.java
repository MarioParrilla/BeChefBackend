package com.mp.bechefbackend.bechefbackend.Models;

import java.io.Serializable;

public class RateId implements Serializable {

    private long recipeId;

    private long userId;

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getUserID() {
        return userId;
    }

    public void setUserID(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RateId{" +
                "recipeId=" + recipeId +
                ", userID=" + userId +
                '}';
    }
}
