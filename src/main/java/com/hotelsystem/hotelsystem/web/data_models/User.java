package com.hotelsystem.hotelsystem.web.data_models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class User {
    @Id
    private String pesel;
    private String password;
    private String name;
    private String surname;
    private String emailAddress;
    private UserType userRole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Opinion> opinions = new HashSet<>();

    public User(String pesel, String password, String name, String surname, String emailAddress, UserType userRole) {
        this.pesel = pesel;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.userRole = userRole;
    }

    public User(){

    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserType getUserRole() {
        return userRole;
    }

    public void setUserRole(UserType userRole) {
        this.userRole = userRole;
    }
}
