
package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMenuResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMenuResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MenuList" type="{http://localhost/LunchService}menu" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMenuResponse", propOrder = {
    "menuList"
})
public class GetMenuResponse {

    @XmlElement(name = "MenuList")
    protected Menu menuList;

    /**
     * Gets the value of the menuList property.
     * 
     * @return
     *     possible object is
     *     {@link Menu }
     *     
     */
    public Menu getMenuList() {
        return menuList;
    }

    /**
     * Sets the value of the menuList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Menu }
     *     
     */
    public void setMenuList(Menu value) {
        this.menuList = value;
    }

}
