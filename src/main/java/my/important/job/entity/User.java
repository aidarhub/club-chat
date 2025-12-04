package my.important.job.entity;

public class User {
    private int id;
    private String login;
    private String email;
    private char[] password;

    public User() {
    }

    public User(int id, String login, String email, char[] password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
