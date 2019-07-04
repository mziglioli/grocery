package com.sainsburys;

import com.sainsburys.client.GroceriesClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.service.GroceryService;
import com.sainsburys.utils.GroceriesWrapper;

public class Application {

    public static void main(String[] args) {
        GroceryService groceryService = new GroceryService(new GroceriesClient(), new ObjectMapper(), new GroceriesWrapper());
        String json = groceryService.getGroceriesAsJson();

        System.out.println(json);
    }
}
