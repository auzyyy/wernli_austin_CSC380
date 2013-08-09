package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/8/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */

@WebService(name = "LunchService", targetNamespace = "http://localhost/LunchService")
public interface LunchService {

    @WebMethod(operationName = "getRestaurants")
    public
    @WebResult(name = "RestaurantList")
    List<Restaurant> getRestaurants();

    @WebMethod(operationName = "getMenu")
    public
    @WebResult(name = "MenuList")
    Menu getMenu(@WebParam(name = "restaurant") Restaurant restaurant);

    @WebMethod(operationName = "addOrder")
    public void addOrder(@WebParam(name = "restaurant") Restaurant restaurant, @WebParam(name = "menuItems") MenuItem... menuItems);
}
