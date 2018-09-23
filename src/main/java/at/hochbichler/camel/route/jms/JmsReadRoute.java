package at.hochbichler.camel.route.jms;

import org.apache.camel.builder.RouteBuilder;

public class JmsReadRoute extends RouteBuilder {
    public void configure() throws Exception {
        from("activemq:queue:testQueue")
                .to("log:?level=INFO&showBody=true")
                .to("direct:readQueue");
    }
}
