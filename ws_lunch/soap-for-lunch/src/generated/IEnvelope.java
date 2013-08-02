package generated;

import generated.GetRestaurantMenu.Body;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/1/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IEnvelope {
    Body getBody();

    void setBody(Body value);

    String getEncodingStyle();

    void setEncodingStyle(String value);
}
