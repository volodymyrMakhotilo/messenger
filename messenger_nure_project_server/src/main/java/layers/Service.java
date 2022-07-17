package layers;

import models.User;

import java.util.List;

public class Service implements Layer {
    Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void addUser(String login, String ip, int port) {
        repository.addUser(login, ip, port);
    }

    public User getUser(String login) {
        return repository.getUser(login);
    }

    public void updateUser() {

    }
    public List<User> getUsers(){
        return repository.getUsers();
    }

    public boolean checkUser(String login) {
        return getUser(login) != null;
    }
}
