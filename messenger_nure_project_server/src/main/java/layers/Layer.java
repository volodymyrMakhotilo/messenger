package layers;

import models.User;

public interface Layer {
    void addUser(String login, String ip, int port);
    User getUser(String login);
    void updateUser();

}