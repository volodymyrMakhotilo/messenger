interface api {
    void addUser();

    void getUser();

    void updateUser();
}


class API implements api {
    service service;

    API(service service) {
        this.service = service;
    }

    public void addUser() {

    }

    public void getUser() {

    }

    public void updateUser() {

    }


}

