package fSMessenger_common.module;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "mess_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "First Name")
    private String first_name;
    @Column(name = "Last Name")
    private String last_name;
    @Column(name = "Email")
    private String emailAddress;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Age")
    private int age;

    public User() {
    }

    public User(String first_name, String last_name, String emailAddress, String username, String password, int age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
