package models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NaturalId
    @Column(name = "login")
    private String login;
    @Column(name = "ip")
    private String IP;
    @Column(name = "port")
    private int port;


    //   private int age;
       /* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Auto> autos;
        */

    public User() {
    }

    public User(String login, String IP, int port) {
        this.login = login;
        this.IP = IP;
        this.port = port;
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

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
