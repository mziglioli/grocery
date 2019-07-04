package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import base.GroceryTestBase;
import domain.Grocery;
import java.io.IOException;
import java.math.BigDecimal;
import org.jsoup.nodes.Document;
import org.junit.Test;


public class GroceryWrapperTest extends GroceryTestBase {

    GroceriesWrapper groceriesWrapper = new GroceriesWrapper();

    @Test
    public void testValidGrocery() throws IOException {
        Document document = buildDocument();
        Grocery grocery = groceriesWrapper.buildGrocery(document);
        assertNotNull(grocery);
        assertEquals("Sainsbury's Strawberries 400g", grocery.getTitle());
        assertEquals("by Sainsbury's strawberries", grocery.getDescription());
        assertEquals("1.75", grocery.getPrice().toString());
        assertEquals(33, grocery.getCalories());
    }

    @Test
    public void testValidTitle() throws IOException {
        Document document = buildDocument();
        String text = groceriesWrapper.getTitle(document);
        assertEquals("Sainsbury's Strawberries 400g", text);
    }

    @Test
    public void testValidDescription() throws IOException {
        Document document = buildDocument();
        String text = groceriesWrapper.getDescription(document);
        assertEquals("by Sainsbury's strawberries", text);
    }

    @Test
    public void testValidPrice() throws IOException {
        Document document = buildDocument();
        BigDecimal price = groceriesWrapper.getPrice(document);
        assertEquals("1.75", price.toString());
    }

    @Test
    public void testValidCalories() throws IOException {
        Document document = buildDocument();
        int kcal = groceriesWrapper.getCalories(document);
        assertEquals(33, kcal);
    }

    @Test
    public void testInValidTitle() throws IOException {
        Document document = buildDocument404();
        String text = groceriesWrapper.getTitle(document);
        assertEquals("", text);
    }

    @Test
    public void testInValidDescription() throws IOException {
        Document document = buildDocument404();
        String text = groceriesWrapper.getDescription(document);
        assertEquals("", text);
    }

    @Test
    public void testInValidPrice() throws IOException {
        Document document = buildDocument404();
        BigDecimal price = groceriesWrapper.getPrice(document);
        assertEquals("0", price.toString());
    }

    @Test
    public void testInValidCalories() throws IOException {
        Document document = buildDocument404();
        int kcal = groceriesWrapper.getCalories(document);
        assertEquals(0, kcal);
    }
}
