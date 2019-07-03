package client;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

    public List<String> getGroceriesUrls(){
        Document document = getDocument();
        if(document != null){
            try{
                return document.select("div.productNameAndPromotions a")
                    .eachAttr("abs:href");
            }catch (Exception e){
                System.out.println("Error getting urls: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }
}
