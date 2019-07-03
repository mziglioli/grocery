package utils;

import static org.apache.commons.lang3.StringUtils.isNumeric;

import domain.Grocery;
import java.math.BigDecimal;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    private String getTitle(Document document) {
       return document.selectFirst(".productTitleDescriptionContainer h1").text();
    }

    private String getDescription(Document document) {
        return document.selectFirst(".mainProductInfo .productText p").text();
    }

    private BigDecimal getPrice(Document document) {
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

    private int getCalories(Document document) {
        Element kcalElement = document.selectFirst("table.nutritionTable .tableRow0 td");
        if(kcalElement != null){
            String kcal = kcalElement.text()
                .replaceAll("kcal", "");
            if(isNumeric(kcal)){
                return Integer.valueOf(kcal);
            }
        }
        return 0;
    }
}
