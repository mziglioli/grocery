package com.sainsburys.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Grocery {

    private String title;
    private String description;
    private int calories;
    private BigDecimal price;

    public Grocery(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public BigDecimal getPrice() {
        if(price == null){
            price = BigDecimal.ZERO;
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
