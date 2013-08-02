package clientRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/1/13
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class clientStartup {

    public clientStartup() {
        System.out.println(sendMessage("getRestaurants", null) + " fuck ");
    }

    public String sendMessage(String request, String args) {
        String response = "";
        if (request.equals("getRestaurants")) {
            response = getRestaurants();
        } else if (request.equals("getMenu")) {

        } else if (request.equals("sendOrder")) {

        }
        return response;
    }

    public String getRestaurants() {
        String response = "";
        try {
            HttpURLConnection connection = null;
            URL serverURL = new URL("http://localhost:8080/Restaurant");
            connection = (HttpURLConnection) serverURL.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("restaurant-restaurants-soap-request.xml")));
            String readLine = fileReader.readLine();
            String message = "";
            while (readLine != null) {
                message += readLine;
                readLine = fileReader.readLine();
            }

            writer.write(message);
            writer.flush();

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String serverLine = serverReader.readLine();
            while (serverLine != null) {
                response += serverLine;
                serverLine = serverReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static void main(String[] args) {
        new clientStartup();
    }
}
