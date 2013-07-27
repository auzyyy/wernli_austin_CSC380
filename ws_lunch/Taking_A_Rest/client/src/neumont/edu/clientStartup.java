package neumont.edu;

import generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/25/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class clientStartup {

    public clientStartup() {
        Scanner scan = new Scanner(System.in);
        Restaurants restaurants = getRestaurants();
        int i = 0;
        for (Restaurant restaurantType : restaurants.getRestaurant()) {
            i++;
            System.out.println(i + ". " + restaurantType.getName());
        }
        System.out.println("Enter the number corresponding to the restaurant you want (e.g. \"1\" for carl's junior");

        int choice = Integer.parseInt(scan.nextLine());
        Restaurant chosenRestaurant = restaurants.getRestaurant().get(choice - 1);
        MenuItems menu = chosenRestaurant.getMenuItems();
        System.out.println("----------Menu----------");
        int menuItems = 0;
        for (MenuItem menuItemsType : menu.getMenuItem()) {
            menuItems++;
            System.out.println(menuItems + ". " + menuItemsType.getName());
        }
        System.out.println("Enter the number corresponding to the menu item you want (e.g. \"1 2\" for burger 1 and burger 2");

        String[] menuArray = scan.nextLine().split(" ");
        MenuItem[] chosenMenuItems = new MenuItem[menuArray.length];

        int a = 0;
        for (String o : menuArray) {
            chosenMenuItems[a] = menu.getMenuItem().get(Integer.parseInt(o) - 1);
            a ++;
        }

        sendOrder(chosenRestaurant, chosenMenuItems);

        System.out.println("Your order has been placed!");
    }

    private Restaurants getRestaurants() {
        return getRestaurantsFromServer("GET", "restaurants");
    }

    public static void main(String[] args) {
        new clientStartup();
    }

    private void sendOrder(Restaurant restaurant, MenuItem...menuItem){
        try{
            String menuString = "";
            for (MenuItem item : menuItem) {
                menuString += item.getName() + " ";
            }
            HttpURLConnection connection = null;
            URL serverUrl = new URL("http://localhost:8080/Restaurant");
            connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("PUT");
            connection.setRequestProperty("order", restaurant.getName() + " " + menuString);

            InputStream in = connection.getInputStream();
            Scanner inScanner = new Scanner(in);
            String message = inScanner.nextLine();
            System.out.println(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private Restaurants getRestaurantsFromServer(String method, String requestParam) {
        Restaurants allRestaurants = null;
        try {
            HttpURLConnection connection = null;
            URL serverUrl = new URL("http://localhost:8080/Restaurant");
            connection = (HttpURLConnection) serverUrl.openConnection();

            connection.setRequestMethod(method);
            connection.setRequestProperty("request", requestParam);
            connection.setRequestProperty("Content-type", "xml/application");

            InputStream inStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));

            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            allRestaurants = (Restaurants) ((JAXBElement) unmarshaller.unmarshal(br)).getValue();


        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return allRestaurants;
    }
}
