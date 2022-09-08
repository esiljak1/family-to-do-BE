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

    public Authentication() {
    }

    public Authentication(Long id, String hashedPassword) {
        this.id = id;
        this.hashedPassword = hashedPassword;
    }

    public Authentication(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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

}
