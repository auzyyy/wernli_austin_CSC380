package service;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/6/13
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "service.ZipCodeWebService", serviceName = "ZipCodeWebService")
public class ZipCodeWebServiceImpl implements ZipCodeWebService {

    private static final Map<Integer, State> STATES;

    static {
        STATES = new HashMap<>();
        State initialState = new State();
        STATES.put(84111, new State("UTAH", "UT"));
    }

    @Override
    public State getState(Integer zipCode) {
        return STATES.get(zipCode);
    }

    @Override
    public void addZipCode(State state, Integer... zipCodes) {
        for (Integer zipCode : zipCodes) {
            if (!STATES.containsKey(zipCode))
                STATES.put(zipCode, state);
        }
    }
}
