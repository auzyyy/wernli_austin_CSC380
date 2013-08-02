package neumont;

import generated.MenuResponse.Body;
import generated.MenuResponse.GetMenuItems;
import generated.MenuResponse.MenuItems;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
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
            response.setContentType("text/xml");
            StringReader reader = inputToStringReader(request.getInputStream());
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            XMLEventReader xmler = xmlif.createXMLEventReader(reader);
            EventFilter filter = new EventFilter() {
                public boolean accept(XMLEvent event) {
                    return event.isStartElement();
                }
            };
            XMLEventReader xmlfer = xmlif.createFilteredReader(xmler, filter);
            XMLEvent xEvent = null;
            StartElement envelope = (StartElement) xmlfer.nextEvent();
            StartElement body = (StartElement) xmlfer.nextEvent();
            StartElement method = (StartElement) xmlfer.nextEvent();

            if (method.getName().getLocalPart().equals("getRestaurants")) {
                sendRestaurants(response);
                System.out.println("restaurants");
            } else if (method.getName().getLocalPart().equals("getRestaurantMenu")) {
                sendRestaurantsMenu(response, request);
                System.out.println("method");
            }

            request.getInputStream().close();

        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void sendRestaurantsMenu(javax.servlet.http.HttpServletResponse response, javax.servlet.http.HttpServletRequest request) throws IOException {
        try {
            StringReader reader = inputToStringReader(request.getInputStream());
            JAXBContext unContext = JAXBContext.newInstance(generated.MenuRequest.ObjectFactory.class);
            Unmarshaller unmarshaller = unContext.createUnmarshaller();
            generated.MenuRequest.Envelope unEnvelope = (generated.MenuRequest.Envelope) unmarshaller.unmarshal(reader);

            String restaurantName = unEnvelope.getBody().getGetRestaurantMenu().getRestaurantName();

            generated.MenuResponse.Envelope envelope = new generated.MenuResponse.Envelope();
            generated.MenuResponse.Body body = new Body();
            generated.MenuResponse.GetMenuItems getMenuItems = new GetMenuItems();
            generated.MenuResponse.MenuItems item1 = new MenuItems();
            item1.setName("food1");
            generated.MenuResponse.MenuItems item2 = new MenuItems();
            item2.setName("food2");
            getMenuItems.getMenuItems().add(item1);
            getMenuItems.getMenuItems().add(item2);
            body.setGetMenuItems(getMenuItems);
            envelope.setBody(body);

            StringWriter swriter = new StringWriter();
            JAXBContext marshallerContext = JAXBContext.newInstance(generated.MenuResponse.ObjectFactory.class);
            Marshaller marshaller = marshallerContext.createMarshaller();
            marshaller.marshal(envelope, swriter);

            response.getWriter().write(swriter.toString());
            response.getWriter().flush();
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public StringReader inputToStringReader(InputStream inputStream) {
        StringReader sw = null;
        try {
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream));
            String response = "";
            String responseLine = "";
            while ((responseLine = responseReader.readLine()) != null) {
                response += responseLine;
            }

            sw = new StringReader(response);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return sw;
    }

    private void sendRestaurants(javax.servlet.http.HttpServletResponse response) throws IOException {
        Scanner scan = new Scanner(this.getClass().getClassLoader().getResourceAsStream("restaurant-restaurants-soap-response.xml"));
        String message = "";
        while (scan.hasNextLine()) {
            message += scan.nextLine();
        }
        response.getWriter().write(message);
        response.getWriter().flush();
    }


}
