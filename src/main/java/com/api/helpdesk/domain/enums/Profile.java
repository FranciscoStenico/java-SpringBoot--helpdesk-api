package com.api.helpdesk.domain.enums;

public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENT(1, "ROLE_CLIENT"),
    DEV(2, "ROLE_DEV");

    private Integer code;
    private String description;

    private Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Profile instance : Profile.values()) {
            if (code.equals(instance.getCode())) {
                return instance;
            }
        }

        throw new IllegalArgumentException("Invalid Profile");
    }

}
