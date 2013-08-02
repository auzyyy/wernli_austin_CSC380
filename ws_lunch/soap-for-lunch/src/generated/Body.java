
package generated;

import generated.GetRestaurants.GetRestaurants;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://localhost:8080/restaurants}getRestaurants"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getRestaurants"
})
@XmlRootElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Body {

    @XmlElement(namespace = "http://localhost:8080/restaurants", required = true)
    protected GetRestaurants getRestaurants;

    /**
     * Gets the value of the getRestaurants property.
     *
     * @return possible object is
     *         {@link generated.GetRestaurants.GetRestaurants }
     */
    public GetRestaurants getGetRestaurants() {
        return getRestaurants;
    }

    /**
     * Sets the value of the getRestaurants property.
     *
     * @param value allowed object is
     *              {@link generated.GetRestaurants.GetRestaurants }
     */
    public void setGetRestaurants(GetRestaurants value) {
        this.getRestaurants = value;
    }

}
