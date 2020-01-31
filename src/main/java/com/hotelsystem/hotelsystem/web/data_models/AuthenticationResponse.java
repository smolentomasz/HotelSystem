package com.hotelsystem.hotelsystem.web.data_models;

public class AuthenticationResponse {
    private final String jwt;
    private String name;
    private String surname;
    private String emailAddress;

    public AuthenticationResponse(String jwt, String name, String surname, String emailAddress) {
        this.jwt = jwt;
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
    }

    public String getJwt() {
        return jwt;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
