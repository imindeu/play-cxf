
package services.sunsetrise;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LatLonDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LatLonDate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="SunSetTime" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="SunRiseTime" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Day" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Month" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LatLonDate", propOrder = {
    "latitude",
    "longitude",
    "sunSetTime",
    "sunRiseTime",
    "timeZone",
    "day",
    "month",
    "year"
})
public class LatLonDate {

    @XmlElement(name = "Latitude")
    protected float latitude;
    @XmlElement(name = "Longitude")
    protected float longitude;
    @XmlElement(name = "SunSetTime")
    protected float sunSetTime;
    @XmlElement(name = "SunRiseTime")
    protected float sunRiseTime;
    @XmlElement(name = "TimeZone")
    protected int timeZone;
    @XmlElement(name = "Day")
    protected int day;
    @XmlElement(name = "Month")
    protected int month;
    @XmlElement(name = "Year")
    protected int year;

    /**
     * Gets the value of the latitude property.
     * 
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     */
    public void setLatitude(float value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     */
    public void setLongitude(float value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the sunSetTime property.
     * 
     */
    public float getSunSetTime() {
        return sunSetTime;
    }

    /**
     * Sets the value of the sunSetTime property.
     * 
     */
    public void setSunSetTime(float value) {
        this.sunSetTime = value;
    }

    /**
     * Gets the value of the sunRiseTime property.
     * 
     */
    public float getSunRiseTime() {
        return sunRiseTime;
    }

    /**
     * Sets the value of the sunRiseTime property.
     * 
     */
    public void setSunRiseTime(float value) {
        this.sunRiseTime = value;
    }

    /**
     * Gets the value of the timeZone property.
     * 
     */
    public int getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     */
    public void setTimeZone(int value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the day property.
     * 
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     * 
     */
    public void setDay(int value) {
        this.day = value;
    }

    /**
     * Gets the value of the month property.
     * 
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     */
    public void setMonth(int value) {
        this.month = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

}
