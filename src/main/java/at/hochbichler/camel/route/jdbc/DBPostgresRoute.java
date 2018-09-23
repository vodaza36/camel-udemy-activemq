package at.hochbichler.camel.route.jdbc;

import org.apache.camel.builder.RouteBuilder;

public class DBPostgresRoute extends RouteBuilder {
    public void configure() throws Exception {
        from("direct:dbInput")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?level=INFO&showBody=true");
    }
}
