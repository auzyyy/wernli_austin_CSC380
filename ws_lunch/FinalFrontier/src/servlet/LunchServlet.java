package servlet;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import service.LunchServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/8/13
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/services/*")
public class LunchServlet extends CXFNonSpringServlet {

    protected void loadBus(ServletConfig sc) {
        super.loadBus(sc);

        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);
        Endpoint.publish("/LunchService", new LunchServiceImpl());
    }
}
