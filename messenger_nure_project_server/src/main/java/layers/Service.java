package layers;

import models.User;

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

    public boolean checkUser(String login) {
        return getUser(login) != null;
    }
}
