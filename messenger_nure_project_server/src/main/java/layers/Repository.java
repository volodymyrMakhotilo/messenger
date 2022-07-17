package layers;

import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class Repository implements Layer {
    SessionFactory db;

    public Repository(SessionFactory db) {
        this.db = db;
    }

    public void addUser(String login, String ip, int port) {
        User user = new User(login, ip, port);
        Session session = db.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public User getUser(String login) {
        User user = null;
        Session session = db.openSession();
        Transaction tx = session.beginTransaction();
        try {
            // Hard coded
            user = (User) session.byNaturalId(User.class).using("login",login).load();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public List<User> getUsers(){
        List<User> users = null;
        Session session = db.openSession();
        Transaction tx = session.beginTransaction();
        try {
            users = session.createQuery("from User", User.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public void updateUser() {

    }


}
