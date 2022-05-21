package com.mp.bechefbackend.bechefbackend.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipes_rates")
@IdClass(RateId.class)
public class RateDTO implements Serializable {

    @Id
    private long recipeId;

    @Id
    private long userId;

    @Column
    private double rate;

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RateDTO{" +
                "recipeId=" + recipeId +
                ", userID=" + userId +
                ", rate=" + rate +
                '}';
    }
}
