package at.hochbichler.camel.launch;

import at.hochbichler.camel.route.jms2jdbc.Jms2DBRoute;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class AppLauncher {
    public static void main(String[] args) {
        Main main = new Main();
        String url = "jdbc:postgresql://localhost:5432/localdb";
        main.bind("myDataSource", setUpDataSource(url));
        main.addRouteBuilder(new Jms2DBRoute());
        try {
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static DataSource setUpDataSource(String url) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setPassword("postgres");
        ds.setUrl(url);
        return ds;
    }
}
