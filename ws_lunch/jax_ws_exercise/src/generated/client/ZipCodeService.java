package generated.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-08-06T18:47:06.776-06:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://localhost/ZipCodeService", name = "ZipCodeService")
@XmlSeeAlso({ObjectFactory.class})
public interface ZipCodeService {

    @RequestWrapper(localName = "addZipCode", targetNamespace = "http://localhost/ZipCodeService", className = "generated.client.AddZipCode")
    @WebMethod
    @ResponseWrapper(localName = "addZipCodeResponse", targetNamespace = "http://localhost/ZipCodeService", className = "generated.client.AddZipCodeResponse")
    public void addZipCode(
        @WebParam(name = "state", targetNamespace = "")
        generated.client.State state,
        @WebParam(name = "zipCodes", targetNamespace = "")
        java.util.List<java.lang.Integer> zipCodes
    );

    @WebResult(name = "State", targetNamespace = "")
    @RequestWrapper(localName = "getState", targetNamespace = "http://localhost/ZipCodeService", className = "generated.client.GetState")
    @WebMethod
    @ResponseWrapper(localName = "getStateResponse", targetNamespace = "http://localhost/ZipCodeService", className = "generated.client.GetStateResponse")
    public generated.client.State getState(
        @WebParam(name = "zipCode", targetNamespace = "")
        java.lang.Integer zipCode
    );
}
