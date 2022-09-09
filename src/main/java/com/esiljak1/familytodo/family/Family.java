package com.esiljak1.familytodo.family;

import javax.persistence.*;

@Entity
@Table
public class Family {
    @Id
    @SequenceGenerator(
            name = "family_sequence",
            sequenceName = "family_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "family_sequence"
    )
    private Long id;
    private String name;
    private Boolean isPrivate;

    public Family() {
    }

    public Family(String name, Boolean isPrivate) {
        this.name = name;
        this.isPrivate = isPrivate;
    }

    public Family(Long id, String name, Boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
