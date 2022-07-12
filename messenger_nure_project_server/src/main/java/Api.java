interface Api {
    void addUser();

    void getUser(int UserId);

    void updateUser();
}


class ApiImpl implements Api {
    Service service;

    ApiImpl(Service service) {
        this.service = service;
    }

    public void addUser() {

    }

    public void getUser(int UserId) {
        service.getUser(UserId);
    }

    public void updateUser() {

    }


}

