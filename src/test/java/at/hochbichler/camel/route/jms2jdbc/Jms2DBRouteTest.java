package at.hochbichler.camel.route.jms2jdbc;

import at.hochbichler.camel.route.jdbc.DBPostgresRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Jms2DBRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new Jms2DBRoute();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/localdb";
        DataSource dataSource = setUpDataSource(url);
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        simpleRegistry.put("myDataSource", dataSource);
        CamelContext camelContext = new DefaultCamelContext(simpleRegistry);
        return camelContext;
    }

    private DataSource setUpDataSource(String url) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setPassword("postgres");
        ds.setUrl(url);
        return ds;
    }

    @Test
    public void insertData() {
        String input = "first db input";
        ArrayList responseList = (ArrayList) consumer.receiveBody("direct:output");
        System.out.println("Size: " + responseList.size());
        assertNotEquals(0, responseList.size());
    }
}