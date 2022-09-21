package com.esiljak1.familytodo.family;

public class FamilyDTO {
    private String name;
    private boolean isPrivate;
    private long ownerId;

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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
