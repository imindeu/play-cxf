
package services.sunsetrise;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="L" type="{http://www.webserviceX.NET/}LatLonDate"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "l"
})
@XmlRootElement(name = "GetSunSetRiseTime")
public class GetSunSetRiseTime {

    @XmlElement(name = "L", required = true)
    protected LatLonDate l;

    /**
     * Gets the value of the l property.
     * 
     * @return
     *     possible object is
     *     {@link LatLonDate }
     *     
     */
    public LatLonDate getL() {
        return l;
    }

    /**
     * Sets the value of the l property.
     * 
     * @param value
     *     allowed object is
     *     {@link LatLonDate }
     *     
     */
    public void setL(LatLonDate value) {
        this.l = value;
    }

}
