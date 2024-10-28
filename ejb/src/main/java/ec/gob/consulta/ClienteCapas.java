
package ec.gob.consulta;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ClienteCapas", targetNamespace = "http://consulta.gob.ec/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ClienteCapas {


    /**
     * 
     * @param xCoor
     * @param yCoor
     * @param user
     * @param pass
     * @return
     *     returns ec.gob.consulta.CapasResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "intersects", targetNamespace = "http://consulta.gob.ec/", className = "ec.gob.consulta.Intersects")
    @ResponseWrapper(localName = "intersectsResponse", targetNamespace = "http://consulta.gob.ec/", className = "ec.gob.consulta.IntersectsResponse")
    public CapasResponse intersects(
        @WebParam(name = "user", targetNamespace = "")
        String user,
        @WebParam(name = "pass", targetNamespace = "")
        String pass,
        @WebParam(name = "xCoor", targetNamespace = "")
        Integer xCoor,
        @WebParam(name = "yCoor", targetNamespace = "")
        Integer yCoor);

}
