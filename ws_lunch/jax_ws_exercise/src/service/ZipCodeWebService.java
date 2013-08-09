package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/6/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(name = "ZipCodeService", targetNamespace = "http://localhost/ZipCodeService")
public interface ZipCodeWebService {

    @WebMethod(operationName = "getState")
    public
    @WebResult(name = "State")
    State getState(@WebParam(name = "zipCode") Integer zipCode);

    @WebMethod(operationName = "addZipCode")
    public void addZipCode(@WebParam(name = "state") State state, @WebParam(name = "zipCodes") Integer... zipCodes);
}
