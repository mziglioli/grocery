package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import base.GroceryTestBase;
import client.GroceriesClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.GroceryDto;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import utils.GroceriesWrapper;


public class GroceryServiceTest extends GroceryTestBase {

    GroceriesClient groceriesClient;
    GroceriesWrapper groceriesWrapper = new GroceriesWrapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJson() throws IOException {
        validMock();
        GroceryService groceryService = new GroceryService(groceriesClient, objectMapper, groceriesWrapper);

        String json = groceryService.getGroceriesAsJson();
        assertNotNull(json);
    }

    @Test
    public void testGrocery() throws IOException {
        validMock();
        GroceryService groceryService = new GroceryService(groceriesClient, objectMapper, groceriesWrapper);

        GroceryDto dto = groceryService.buildGroceryDto();
        assertNotNull(dto);
        assertEquals(2, dto.getGroceries().size());
        assertEquals("3.50", dto.getTotal().getGross().toString());
        assertEquals("0.70", dto.getTotal().getVat().toString());
    }

    private void validMock() throws IOException {
        String urls = getFileAsString("/urls.json");
        groceriesClient = mock(GroceriesClient.class);
        groceriesWrapper = mock(GroceriesWrapper.class);
        when(groceriesClient.getDocument()).thenReturn(buildDocument("/valid.html"));
        when(groceriesClient.getDocument("sainsburys-british-strawberries-400g.html")).thenReturn(buildDocument("/strawberries.html"));
        when(groceriesClient.getDocument("sainsburys-blueberries-200g.html")).thenReturn(buildDocument("/blueberries.html"));
        when(groceriesWrapper.getGroceriesUrls(any())).thenReturn(objectMapper.readValue(urls, List.class));
        when(groceriesWrapper.buildGrocery(any())).thenCallRealMethod();
        when(groceriesWrapper.getCalories(any())).thenCallRealMethod();
        when(groceriesWrapper.getDescription(any())).thenCallRealMethod();
        when(groceriesWrapper.getPrice(any())).thenCallRealMethod();
        when(groceriesWrapper.getTitle(any())).thenCallRealMethod();
        when(groceriesWrapper.getTextByQuery(any(), anyString())).thenCallRealMethod();
    }
}
