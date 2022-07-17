package layers;

import models.User;

import java.util.List;

public interface Layer {
    void addUser(String login, String ip, int port);
    User getUser(String login);
    void updateUser();
    List<User> getUsers();

}