package com.esiljak1.familytodo.family;

import com.esiljak1.familytodo.family_invite.Invite;
import com.esiljak1.familytodo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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
    private boolean isPrivate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;
    @JsonIgnore
    @ManyToMany(mappedBy = "families")
    private List<User> users;
    @JsonIgnore
    @OneToMany(mappedBy = "family")
    private List<Invite> invites;

    public Family() {
    }

    public Family(String name, Boolean isPrivate, User owner) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
