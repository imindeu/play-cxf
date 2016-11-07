
package services.coffee;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.coffee package. 
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

    private final static QName _MakeCoffee_QNAME = new QName("http://coffee.services/", "makeCoffee");
    private final static QName _MakeCoffeeResponse_QNAME = new QName("http://coffee.services/", "makeCoffeeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.coffee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coffee.services/", name = "makeCoffee")
    public JAXBElement<String> createMakeCoffee(String value) {
        return new JAXBElement<String>(_MakeCoffee_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://coffee.services/", name = "makeCoffeeResponse")
    public JAXBElement<String> createMakeCoffeeResponse(String value) {
        return new JAXBElement<String>(_MakeCoffeeResponse_QNAME, String.class, null, value);
    }

}
