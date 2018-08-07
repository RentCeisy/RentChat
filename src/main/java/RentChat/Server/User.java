package RentChat.Server;

import java.io.Serializable;

public class User implements Serializable {

    private long id;
    private String login;

    public User(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
