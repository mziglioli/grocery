package com.sainsburys.dto;

import com.sainsburys.domain.Grocery;
import java.util.List;

public class GroceryDto {

    private List<Grocery> groceries;
    private TotalDto total;

    public GroceryDto(){

    }

    public List<Grocery> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }

    public TotalDto getTotal() {
        return total;
    }

    public void setTotal(TotalDto total) {
        this.total = total;
    }
}
