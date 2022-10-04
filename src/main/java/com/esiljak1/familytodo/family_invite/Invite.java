package com.esiljak1.familytodo.family_invite;

import com.esiljak1.familytodo.family.Family;
import com.esiljak1.familytodo.user.User;

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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User invitedUser;

    @ManyToOne
    @JoinColumn(name = "family_id", referencedColumnName = "id")
    private Family family;

    public Invite(Long id, User invitedUser, Family family) {
        this.id = id;
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
