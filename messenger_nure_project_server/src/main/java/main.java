import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import utilities.db;
import utilities.http_server;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        User user = new User("Витя");
        api api =  new API(new Service(new Repository(db.getSessionFactory())));



    }
}
