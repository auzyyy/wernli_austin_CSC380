
package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="restaurant" type="{http://localhost/LunchService}restaurant" minOccurs="0"/>
 *         &lt;element name="menuItems" type="{http://localhost/LunchService}menuItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addOrder", propOrder = {
    "restaurant",
    "menuItems"
})
public class AddOrder {

    protected Restaurant restaurant;
    protected List<MenuItem> menuItems;

    /**
     * Gets the value of the restaurant property.
     * 
     * @return
     *     possible object is
     *     {@link Restaurant }
     *     
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets the value of the restaurant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Restaurant }
     *     
     */
    public void setRestaurant(Restaurant value) {
        this.restaurant = value;
    }

    /**
     * Gets the value of the menuItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the menuItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMenuItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MenuItem }
     * 
     * 
     */
    public List<MenuItem> getMenuItems() {
        if (menuItems == null) {
            menuItems = new ArrayList<MenuItem>();
        }
        return this.menuItems;
    }

}
