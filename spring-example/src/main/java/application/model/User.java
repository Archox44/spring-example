package application.model;

public class User {

    private String id;
    private String login;
    private String password;
    private String points;

    public User(){

    }

    public User(String id, String login, String password, String points){
        this.id = id;
        this.login = login;
        this.password = password;
        this.points = points;
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPoints() {
        return points;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}
