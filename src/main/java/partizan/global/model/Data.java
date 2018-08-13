package partizan.global.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private Operation operation;
    private List<User> users;
    private String status;

    public Data() {
        this.users = new ArrayList<>();
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

}
