package at.hochbichler.camel.route.jdbc;

import org.apache.camel.Exchange;

public class InsertProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        String input = (String) exchange.getIn().getBody();
        String insertStmt = "INSERT INTO messages VALUES ('1', '" + input + "')";
        exchange.getIn().setBody(insertStmt);
    }
}
