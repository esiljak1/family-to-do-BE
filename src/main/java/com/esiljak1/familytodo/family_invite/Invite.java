package com.esiljak1.familytodo.family_invite;

import com.esiljak1.familytodo.family.Family;
import com.esiljak1.familytodo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Invite {
    @Id
    @SequenceGenerator(
            name = "invite_sequence",
            sequenceName = "invite_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invite_sequence"
    )
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User invitedUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "family_id", referencedColumnName = "id")
    private Family family;

    public Invite(User invitedUser, Family family) {
        this.invitedUser = invitedUser;
        this.family = family;
    }

    public Invite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
