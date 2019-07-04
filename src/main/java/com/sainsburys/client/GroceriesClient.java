package com.sainsburys.client;

import java.io.IOException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GroceriesClient {

    private final static String DEFAULT_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public GroceriesClient() {
    }

    public Document getDocument() {
        return getDocument(DEFAULT_URL);
    }

    public Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (HttpStatusException ex) {
            System.out.println("Error connection: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error getting the result from Jsoup: " + e.getMessage());
        }
        return null;
    }
}
