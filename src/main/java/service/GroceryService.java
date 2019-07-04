package service;

import client.GroceriesClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Grocery;
import dto.GroceryDto;
import dto.TotalDto;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import utils.GroceriesWrapper;

public class GroceryService {

    private ObjectMapper objectMapper;
    private GroceriesClient groceriesClient;
    private GroceriesWrapper groceriesWrapper;

    public GroceryService(GroceriesClient groceriesClient, ObjectMapper objectMapper, GroceriesWrapper groceriesWrapper){
        this.groceriesClient = groceriesClient;
        this.objectMapper = objectMapper;
        this.groceriesWrapper = groceriesWrapper;
    }

    public String getGroceriesAsJson(){
        GroceryDto groceryDto = buildGroceryDto();
        try {
            return objectMapper.writeValueAsString(groceryDto);
        } catch (IOException e) {
            return "Error trying to convert: " + e.getMessage();
        }
    }

    GroceryDto buildGroceryDto(){
        GroceryDto dto = new GroceryDto();
        final BigDecimal[] total = {BigDecimal.ZERO};
        List<Grocery> groceries = getGroceriesUrls().stream()
            .map(url -> {
                Grocery g = buildGrocery(url);
                total[0] = total[0].add(g.getPrice());
                return g;
            }).collect(Collectors.toList());
        dto.setGroceries(groceries);
        dto.setTotal(buildTotal(total));
        return dto;
    }

    public List<String> getGroceriesUrls(){
        Document document = groceriesClient.getDocument();
        if(document != null){
            return groceriesWrapper.getGroceriesUrls(document);
        }
        return new ArrayList<>();
    }

    private Grocery buildGrocery(String url) {
        Document document = groceriesClient.getDocument(url);
        return groceriesWrapper.buildGrocery(document);
    }

    private TotalDto buildTotal(final BigDecimal[] total){
        TotalDto totalDto = new TotalDto();
        totalDto.setGross(total[0]);
        totalDto.setVat(total[0].multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
        return totalDto;
    }

}
