
package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRestaurantsResponse_QNAME = new QName("http://localhost/LunchService", "getRestaurantsResponse");
    private final static QName _AddOrderResponse_QNAME = new QName("http://localhost/LunchService", "addOrderResponse");
    private final static QName _GetMenu_QNAME = new QName("http://localhost/LunchService", "getMenu");
    private final static QName _GetRestaurants_QNAME = new QName("http://localhost/LunchService", "getRestaurants");
    private final static QName _AddOrder_QNAME = new QName("http://localhost/LunchService", "addOrder");
    private final static QName _GetMenuResponse_QNAME = new QName("http://localhost/LunchService", "getMenuResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetMenuResponse }
     * 
     */
    public GetMenuResponse createGetMenuResponse() {
        return new GetMenuResponse();
    }

    /**
     * Create an instance of {@link AddOrder }
     * 
     */
    public AddOrder createAddOrder() {
        return new AddOrder();
    }

    /**
     * Create an instance of {@link GetRestaurants }
     * 
     */
    public GetRestaurants createGetRestaurants() {
        return new GetRestaurants();
    }

    /**
     * Create an instance of {@link GetRestaurantsResponse }
     * 
     */
    public GetRestaurantsResponse createGetRestaurantsResponse() {
        return new GetRestaurantsResponse();
    }

    /**
     * Create an instance of {@link GetMenu }
     * 
     */
    public GetMenu createGetMenu() {
        return new GetMenu();
    }

    /**
     * Create an instance of {@link AddOrderResponse }
     * 
     */
    public AddOrderResponse createAddOrderResponse() {
        return new AddOrderResponse();
    }

    /**
     * Create an instance of {@link MenuItem }
     * 
     */
    public MenuItem createMenuItem() {
        return new MenuItem();
    }

    /**
     * Create an instance of {@link Menu }
     * 
     */
    public Menu createMenu() {
        return new Menu();
    }

    /**
     * Create an instance of {@link Restaurant }
     * 
     */
    public Restaurant createRestaurant() {
        return new Restaurant();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRestaurantsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/LunchService", name = "getRestaurantsResponse")
    public JAXBElement<GetRestaurantsResponse> createGetRestaurantsResponse(GetRestaurantsResponse value) {
        return new JAXBElement<GetRestaurantsResponse>(_GetRestaurantsResponse_QNAME, GetRestaurantsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/LunchService", name = "addOrderResponse")
    public JAXBElement<AddOrderResponse> createAddOrderResponse(AddOrderResponse value) {
        return new JAXBElement<AddOrderResponse>(_AddOrderResponse_QNAME, AddOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMenu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/LunchService", name = "getMenu")
    public JAXBElement<GetMenu> createGetMenu(GetMenu value) {
        return new JAXBElement<GetMenu>(_GetMenu_QNAME, GetMenu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRestaurants }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/LunchService", name = "getRestaurants")
    public JAXBElement<GetRestaurants> createGetRestaurants(GetRestaurants value) {
        return new JAXBElement<GetRestaurants>(_GetRestaurants_QNAME, GetRestaurants.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/LunchService", name = "addOrder")
    public JAXBElement<AddOrder> createAddOrder(AddOrder value) {
        return new JAXBElement<AddOrder>(_AddOrder_QNAME, AddOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMenuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost/LunchService", name = "getMenuResponse")
    public JAXBElement<GetMenuResponse> createGetMenuResponse(GetMenuResponse value) {
        return new JAXBElement<GetMenuResponse>(_GetMenuResponse_QNAME, GetMenuResponse.class, null, value);
    }

}
