package com.esiljak1.familytodo.family;

public class FamilyDTO {
    private String name;
    private Boolean isPrivate;
    private Long ownerId;

    public FamilyDTO(String name, boolean isPrivate, long ownerId) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
