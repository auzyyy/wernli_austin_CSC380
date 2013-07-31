package neumont;

import generated.GetRestaurants.Envelope;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/30/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "RestaurantServlet", urlPatterns = "/Restaurant")
public class SoapSocket extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            InputStream inputStream = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));


            JAXBContext context = JAXBContext.newInstance(generated.ObjectFactory.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object clientRequest = unmarshaller.unmarshal(br);
            String[] splitString = clientRequest.getClass().toString().split("\\.");
            System.out.println(clientRequest);
            String className = splitString[1];

            System.out.println(className);

            if (className.equals("GetRestaurants")) {
                sendRestaurants(response);
            } else if (className.equals("GetRestaurantsMenu")) {
                sendRestaurantsMenu(response, (generated.GetRestaurants.Envelope) clientRequest);
            } else if (className.equals("MenuRequest")) {
                sendRestaurantsMenu(response, (generated.GetRestaurants.Envelope) clientRequest);
            } else if (className.equals("RestaurantsRequest")) {
                sendRestaurants(response);
            }

        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void sendRestaurantsMenu(javax.servlet.http.HttpServletResponse response, Envelope envelope) throws IOException {
        Scanner scan = new Scanner(this.getClass().getClassLoader().getResourceAsStream("restaurant-menu-soapp-response.xsd"));
        String message = "";
        while (scan.hasNextLine()) {
            message += scan.nextLine();
        }
        response.getWriter().write(message);
    }

    private void sendRestaurants(javax.servlet.http.HttpServletResponse response) throws IOException {
        Scanner scan = new Scanner(this.getClass().getClassLoader().getResourceAsStream("restaurant-restaurants-soap-response.xml"));
        String message = "";
        while (scan.hasNextLine()) {
            message += scan.nextLine();
        }
        response.getWriter().write(message);
    }


}
