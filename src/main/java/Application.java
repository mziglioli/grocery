import client.GroceriesClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.GroceryService;
import utils.GroceriesWrapper;

public class Application {

    public static void main(String[] args) {
        GroceryService groceryService = new GroceryService(new GroceriesClient(), new ObjectMapper(), new GroceriesWrapper());
        String json = groceryService.getGroceriesAsJson();

        System.out.println(json);
    }
}
