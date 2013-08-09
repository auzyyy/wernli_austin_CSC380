package service;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/8/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "service.LunchService", serviceName = "LunchService")
public class LunchServiceImpl implements LunchService {

    public List<Restaurant> restaurants = new ArrayList<>();
    public List<String> orders = new ArrayList<>();

    public LunchServiceImpl() {
        restaurants.add(new Restaurant("Carls Jr", "bacon burger", "cheese burger", "no burder :("));
        restaurants.add(new Restaurant("chow mein", "chinese food 1", "chinese food 2", "chinese food 3"));
        restaurants.add(new Restaurant("artic cirlce", "ranch burger", "cheese burger", "lettuce wrap"));
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public Menu getMenu(@WebParam(name = "restaurant") Restaurant restaurant) {
        return restaurant.getMenu();
    }

    @Override
    public void addOrder(@WebParam(name = "restaurant") Restaurant restaurant, @WebParam(name = "menuItems") MenuItem... menuItems) {
        String menuString = "";
        for (MenuItem menuItem : menuItems) {
            menuString += menuItem.getMenuString() + ",";
        }
        orders.add(restaurant.getName() + " " + menuString);
        System.out.println("order added: " + restaurant.getName() + " (" + menuString + ")");    }
}
