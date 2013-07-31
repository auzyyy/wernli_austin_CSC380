
package generated;

import generated.GetRestaurantMenu.Body;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2001/12/soap-envelope}Body"/>
 *       &lt;/sequence>
 *       &lt;attribute name="encodingStyle" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "body"
})
@XmlRootElement(name = "Envelope", namespace = "http://www.w3.org/2001/12/soap-envelope")
public class Envelope {

    @XmlElement(name = "Body", namespace = "http://www.w3.org/2001/12/soap-envelope", required = true)
    protected Body body;
    @XmlAttribute(name = "encodingStyle", namespace = "http://www.w3.org/2001/12/soap-envelope", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String encodingStyle;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link generated.GetRestaurantMenu.Body }
     *
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     *
     * @param value
     *     allowed object is
     *     {@link generated.GetRestaurantMenu.Body }
     *     
     */
    public void setBody(Body value) {
        this.body = value;
    }

    /**
     * Gets the value of the encodingStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodingStyle() {
        return encodingStyle;
    }

    /**
     * Sets the value of the encodingStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodingStyle(String value) {
        this.encodingStyle = value;
    }

}
