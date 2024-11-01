
package ec.gob.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for intersects complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="intersects">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xCoor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yCoor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "intersects", propOrder = {
    "user",
    "pass",
    "xCoor",
    "yCoor"
})
public class Intersects {

    protected String user;
    protected String pass;
    protected Integer xCoor;
    protected Integer yCoor;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the pass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets the value of the pass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPass(String value) {
        this.pass = value;
    }

    /**
     * Gets the value of the xCoor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getXCoor() {
        return xCoor;
    }

    /**
     * Sets the value of the xCoor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXCoor(Integer value) {
        this.xCoor = value;
    }

    /**
     * Gets the value of the yCoor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getYCoor() {
        return yCoor;
    }

    /**
     * Sets the value of the yCoor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYCoor(Integer value) {
        this.yCoor = value;
    }

}
