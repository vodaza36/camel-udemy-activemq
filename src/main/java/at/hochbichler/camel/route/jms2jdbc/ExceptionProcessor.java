package at.hochbichler.camel.route.jms2jdbc;

import org.apache.camel.Exchange;

public class ExceptionProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        System.out.println("Actual excetion message: " + e.getMessage());
        System.out.println("Actual excetion class: " + e.getClass());

        String failureEndpoint = (String) exchange.getProperty(Exchange.FAILURE_ENDPOINT);
        System.out.println("Failure endpoint: " + failureEndpoint);

        exchange.getIn().setBody("Exception happend in the route");
    }
}
