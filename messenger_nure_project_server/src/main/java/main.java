import layers.Repository;
import layers.Service;
import utilities.db;
import utilities.http_server;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        new http_server(new Service(new Repository(db.getSessionFactory())));
    }
}
