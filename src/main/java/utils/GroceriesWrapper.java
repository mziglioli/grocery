package utils;

import static org.apache.commons.lang3.StringUtils.isNumeric;

import domain.Grocery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GroceriesWrapper {

    public GroceriesWrapper() {
    }

    public Grocery buildGrocery(Document document) {
        Grocery grocery = new Grocery();
        grocery.setTitle(getTitle(document));
        grocery.setDescription(getDescription(document));
        grocery.setCalories(getCalories(document));
        grocery.setPrice(getPrice(document));

        return grocery;
    }

    public String getTitle(Document document) {
       return getTextByQuery(document, ".productTitleDescriptionContainer h1");
    }

    public String getDescription(Document document) {
        return getTextByQuery(document, ".mainProductInfo .productText p");
    }

    public String getTextByQuery(Document document, String query){
        Element element = document.selectFirst(query);
        if(element != null){
            return element.text();
        }
        return "";
    }

    public BigDecimal getPrice(Document document) {
        Element element = document.selectFirst(".pricingAndTrolleyOptions .pricePerUnit");
        if(element != null){
            String pricePerUnit = element.childNode(0)
                .outerHtml()
                .replaceAll(" ", "")
                .substring(1);
            return new BigDecimal(pricePerUnit);
        }
        return BigDecimal.ZERO;
    }

    public int getCalories(Document document) {
        String kcal = getTextByQuery(document, "table.nutritionTable .tableRow0 td")
            .replaceAll("kcal", "");
        if(isNumeric(kcal)){
            return Integer.valueOf(kcal);
        }
        return 0;
    }

    public List<String> getGroceriesUrls(Document document){
        Elements groceries = document.select("div.productNameAndPromotions a");
        if(groceries != null){
            return groceries.eachAttr("abs:href");
        }
        return new ArrayList<>();
    }
}
