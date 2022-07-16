package layers;

public class Service implements Layer {
    Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void addUser() {

    }

    public void getUser(int UserId) {
        repository.getUser(UserId);
    }

    public void updateUser() {

    }


}
