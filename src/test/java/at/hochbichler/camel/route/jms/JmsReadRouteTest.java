package at.hochbichler.camel.route.jms;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import static org.junit.Assert.*;

public class JmsReadRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new JmsReadRoute();
    }

    @Test
    public void readMessageFromActiveMQ() {
        String expected = "123";
        String response = consumer.receiveBody("direct:readQueue", String.class);

        assertEquals(expected, response);
    }
}