package at.hochbichler.camel.route.jms2jdbc;

import at.hochbichler.camel.route.jdbc.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

public class Jms2DBRoute extends RouteBuilder {
    public void configure() throws Exception {
        onException(PSQLException.class)
                .handled(true)
                .log("A database exception occured!")
                .process(new ExceptionProcessor());

        from("activemq:queue:testQueue")
                .to("log:?level=INFO&showBody=true")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?level=INFO&showBody=true");
               // .to("direct:output");
    }
}
