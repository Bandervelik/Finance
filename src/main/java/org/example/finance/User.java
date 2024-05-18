package org.example.finance;

public class User {
    private int idusers;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(int idusers, String firstName, String lastName, String username, String password) {
        this.idusers = idusers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(User other) {
        this.idusers = other.getidusers();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.username = other.getUsername();
        this.password = other.getPassword();
    }

    public int getidusers() {
        return idusers;
    }

    public void setidusers(int idusers) {
        this.idusers = idusers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "idusers=" + idusers +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
