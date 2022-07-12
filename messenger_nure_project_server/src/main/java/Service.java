interface service {
    void addUser();

    void getUser();

    void updateUser();
}


class Service implements service {
    repository repository;

    Service(repository repository) {
        this.repository = repository;
    }

    public void addUser() {

    }

    public void getUser() {

    }

    public void updateUser() {

    }


}
