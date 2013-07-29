package neumont.edu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/25/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "RestaurantServlet", urlPatterns = "/Restaurant")
public class RestaurantsServlet extends HttpServlet {

    private List<String> orders = new ArrayList<String>();

    public RestaurantsServlet() {

    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        orders.add(order);
        response.getWriter().write("completed");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        C:\Users\awernli\Documents\GitHub\wernli_austin_CSC380\ws_lunch\Taking_A_Rest\resources\Restaurants.xml
//        String filePath = new File(".").getPath();
//        System.out.println(filePath);
        Scanner scan = new Scanner(getClass().getResourceAsStream("Restaurants.xml"));
        String message = "";
        while (scan.hasNextLine()) {
            message += scan.nextLine();
        }

        response.getWriter().write(message);
    }
}
