package clientRun;

import generated.RestaurantsResponse.Envelope;
import generated.RestaurantsResponse.ObjectFactory;
import generated.RestaurantsResponse.Restaurant;
import generated.RestaurantsResponse.RestaurantResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/1/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class clientStartup {
    private Scanner scan = new Scanner(System.in);

    public clientStartup() {
        String restaurantName = "";
        String menuItems = "";
        restaurantName = sendMessage("getRestaurants", null);
        menuItems = sendMessage("getMenu", restaurantName);
    }

    public String sendMessage(String request, String arg) {
        String response = "";
        if (request.equals("getRestaurants")) {
            response = getRestaurants();
        } else if (request.equals("getMenu")) {
            getMenu(arg);
        } else if (request.equals("sendOrder")) {

        }
        return response;
    }

    public void getMenu(String restaurant) {
        try {
            generated.MenuRequest.Envelope envelope = new generated.MenuRequest.Envelope();
            generated.MenuRequest.Body body = new generated.MenuRequest.Body();
            generated.MenuRequest.GetRestaurantMenu getMenu = new generated.MenuRequest.GetRestaurantMenu();
            getMenu.setRestaurantName(restaurant);
            body.setGetRestaurantMenu(getMenu);
            envelope.setBody(body);
            JAXBContext marshalContext = JAXBContext.newInstance(generated.MenuRequest.Envelope.class);
            Marshaller marshaller = marshalContext.createMarshaller();

            StringWriter stringWriter = new StringWriter();

            JAXBContext unmarshallerContext = JAXBContext.newInstance(generated.MenuResponse.ObjectFactory.class);
            Unmarshaller unmarshaller = unmarshallerContext.createUnmarshaller();
            marshaller.marshal(envelope, stringWriter);

            System.out.println(stringWriter);

            Object o = unmarshaller.unmarshal(sendRequest(stringWriter.toString()));
            System.out.println(o.getClass());


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String getRestaurants() {
        String restaurantName = "";
        try {
            StringReader responseStream = sendGetRequest("restaurant-restaurants-soap-request.xml");
            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Envelope envelope = (Envelope) unmarshaller.unmarshal(responseStream);
            generated.RestaurantsResponse.Body body = envelope.getBody();

            List<Restaurant> restaurantList = body.getRestaurantResponse().getRestaurant();
            System.out.println("please choose a restaurant from the list below");
            int i = 1;
            for (Restaurant restaurant : restaurantList) {
                System.out.println(i + ". " + restaurant.getName());
                i++;
            }
            int choice = Integer.parseInt(scan.nextLine());
            Restaurant chosenRestaurant = restaurantList.get(choice - 1);
            restaurantName = chosenRestaurant.getName();

            responseStream.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return restaurantName;
    }

    public StringReader sendGetRequest(String fullFileName) {
        StringReader stringReader = null;
        try {
            HttpURLConnection connection = null;
            URL serverURL = new URL("http://localhost:8080/Restaurant");
            connection = (HttpURLConnection) serverURL.openConnection();
            connection.setRequestProperty("Content-Type", "text/xml");

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fullFileName)));
            String readLine = fileReader.readLine();
            String message = "";
            while (readLine != null) {
                message += readLine;
                readLine = fileReader.readLine();
            }

            writer.write(message);
            writer.flush();

            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = "";
            String responseLine = "";
            while((responseLine = responseReader.readLine()) != null){
               response += responseLine;
            }

            stringReader = new StringReader(response);

            connection.getInputStream().close();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringReader;
    }

    public StringReader sendRequest(String stringToSend) {
        StringReader stringReader = null;
        try {
            HttpURLConnection connection = null;
            URL serverURL = new URL("http://localhost:8080/Restaurant");
            connection = (HttpURLConnection) serverURL.openConnection();
            connection.setRequestProperty("Content-Type", "text/xml");

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            writer.write(stringToSend);
            writer.flush();

            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = "";
            String responseLine = "";
            while((responseLine = responseReader.readLine()) != null){
                response += responseLine;
            }

            stringReader = new StringReader(response);
            connection.getInputStream().close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringReader;
    }

    public static void main(String[] args) {
        new clientStartup();
    }
}
