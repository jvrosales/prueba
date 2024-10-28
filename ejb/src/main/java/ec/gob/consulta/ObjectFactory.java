
package ec.gob.consulta;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.gob.consulta package. 
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

    private final static QName _Intersects_QNAME = new QName("http://consulta.gob.ec/", "intersects");
    private final static QName _IntersectsResponse_QNAME = new QName("http://consulta.gob.ec/", "intersectsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.gob.consulta
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IntersectsResponse }
     * 
     */
    public IntersectsResponse createIntersectsResponse() {
        return new IntersectsResponse();
    }

    /**
     * Create an instance of {@link Intersects }
     * 
     */
    public Intersects createIntersects() {
        return new Intersects();
    }

    /**
     * Create an instance of {@link CapasResponse }
     * 
     */
    public CapasResponse createCapasResponse() {
        return new CapasResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Intersects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://consulta.gob.ec/", name = "intersects")
    public JAXBElement<Intersects> createIntersects(Intersects value) {
        return new JAXBElement<Intersects>(_Intersects_QNAME, Intersects.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IntersectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://consulta.gob.ec/", name = "intersectsResponse")
    public JAXBElement<IntersectsResponse> createIntersectsResponse(IntersectsResponse value) {
        return new JAXBElement<IntersectsResponse>(_IntersectsResponse_QNAME, IntersectsResponse.class, null, value);
    }

}
