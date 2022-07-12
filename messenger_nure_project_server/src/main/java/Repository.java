import org.hibernate.SessionFactory;

interface repository {
    void addUser();

    void getUser();

    void updateUser();
}


class Repository  implements repository{
    SessionFactory db;

    Repository(SessionFactory db) {
        this.db = db;
    }

    public void addUser() {

    }

    public void getUser() {

    }

    public void updateUser() {

    }


}
