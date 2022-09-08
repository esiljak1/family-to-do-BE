package com.esiljak1.familytodo.authentication;

import javax.persistence.*;

@Entity
@Table
public class Authentication {
    @Id
    @SequenceGenerator(
            name = "auth_sequence",
            sequenceName = "auth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "auth_sequence"
    )
    private Long id;
    private String hashedPassword;
    private String passwordSalt;

    public Authentication() {
    }

    public Authentication(Long id, String hashedPassword, String passwordSalt) {
        this.id = id;
        this.hashedPassword = hashedPassword;
        this.passwordSalt = passwordSalt;
    }

    public Authentication(String hashedPassword, String passwordSalt) {
        this.hashedPassword = hashedPassword;
        this.passwordSalt = passwordSalt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
