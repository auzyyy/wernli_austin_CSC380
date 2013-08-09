
package generated;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-08-08T21:21:57.358-06:00
 * Generated source version: 2.7.6
 */
public final class LunchService_LunchServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://service/", "LunchService");

    private LunchService_LunchServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        Scanner scan = new Scanner(System.in);
        URL wsdlURL = LunchService_Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        LunchService_Service ss = new LunchService_Service(wsdlURL, SERVICE_NAME);
        LunchService port = ss.getLunchServiceImplPort();


        //Restaurant Getter
        System.out.println("Choose from the following restaurants");
        java.util.List<generated.Restaurant> _getRestaurants__return = port.getRestaurants();
        System.out.println("getRestaurants.result=" + _getRestaurants__return);

        //Restaurant Chooser
        int i = 0;
        for (Restaurant restaurant : _getRestaurants__return) {
            System.out.println(i++ + ". " + restaurant.getName());
        }
        int restaurantChoice = Integer.parseInt(scan.nextLine());
        Restaurant chosenRestaurant = _getRestaurants__return.get(restaurantChoice);

        //Menu Getter
        System.out.println("Choose one or more of the following menu items seperated by a comma");
        generated.Restaurant _getMenu_restaurant = chosenRestaurant;
        generated.Menu _getMenu__return = port.getMenu(_getMenu_restaurant);
        System.out.println("getMenu.result=" + _getMenu__return);

        //Menu item Chooser
        int a = 0;
        for (MenuItem menuItem : _getMenu__return.getMenuItems()) {
            System.out.println(a++ + ". " + menuItem.getMenuString());
        }
        String chosenMenuString = scan.nextLine();
        String[] chosenMenuArray = chosenMenuString.split(",");
        List<MenuItem> chosenmenuItems = new ArrayList<>();
        for (String s : chosenMenuArray) {
            chosenmenuItems.add(_getMenu__return.getMenuItems().get(Integer.parseInt(s)));
        }

        //Order Placer
        System.out.println("Invoking addOrder...");
        generated.Restaurant _addOrder_restaurant = chosenRestaurant;
        java.util.List<generated.MenuItem> _addOrder_menuItems = chosenmenuItems;
        port.addOrder(_addOrder_restaurant, _addOrder_menuItems);

        System.exit(0);
    }

}
