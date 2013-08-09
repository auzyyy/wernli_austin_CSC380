
package generated.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-08-06T18:47:06.716-06:00
 * Generated source version: 2.7.6
 */
public final class ZipCodeService_ZipCodeWebServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://service/", "ZipCodeWebService");

    private ZipCodeService_ZipCodeWebServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ZipCodeWebService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        ZipCodeWebService ss = new ZipCodeWebService(wsdlURL, SERVICE_NAME);
        ZipCodeService port = ss.getZipCodeWebServiceImplPort();

        {
            System.out.println("Invoking addZipCode...");
            generated.client.State _addZipCode_state = new State();
            _addZipCode_state.setFullName("Texas");
            _addZipCode_state.setTwoDigitCode("TX");
            java.util.List<java.lang.Integer> _addZipCode_zipCodes = new ArrayList<>();
            _addZipCode_zipCodes.add(75028);
            port.addZipCode(_addZipCode_state, _addZipCode_zipCodes);


        }
        {
            System.out.println("Invoking getState...");
            java.lang.Integer _getState_zipCode = 75028;
            generated.client.State _getState__return = port.getState(_getState_zipCode);
            System.out.println("getState.result=" + _getState__return.getTwoDigitCode());


        }

        System.exit(0);
    }

}
