package com.esiljak1.familytodo.family_invite;

public class InviteDTO {
    private Long id;
    private Long userId;
    private Long familyId;

    public InviteDTO(Long id, Long userId, Long familyId) {
        this.id = id;
        this.userId = userId;
        this.familyId = familyId;
    }

    public InviteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }
}
