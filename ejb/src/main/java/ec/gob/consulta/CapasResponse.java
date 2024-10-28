
package ec.gob.consulta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for capasResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="capasResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intersectMesagge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intersectValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "capasResponse", propOrder = {
    "intersectMesagge",
    "intersectValue"
})
public class CapasResponse {

    protected String intersectMesagge;
    protected String intersectValue;

    /**
     * Gets the value of the intersectMesagge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntersectMesagge() {
        return intersectMesagge;
    }

    /**
     * Sets the value of the intersectMesagge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntersectMesagge(String value) {
        this.intersectMesagge = value;
    }

    /**
     * Gets the value of the intersectValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntersectValue() {
        return intersectValue;
    }

    /**
     * Sets the value of the intersectValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntersectValue(String value) {
        this.intersectValue = value;
    }

}
