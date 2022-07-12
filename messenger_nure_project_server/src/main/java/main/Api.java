package main;


public class Api implements Layer {
    Service service;

    public Api(Service service) {
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

