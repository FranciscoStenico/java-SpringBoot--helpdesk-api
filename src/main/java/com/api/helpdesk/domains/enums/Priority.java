package com.api.helpdesk.domains.enums;

public enum Priority {

    LOW_PRIORITY(0, "LOW_PRIORITY"),
    MEDIUM_PRIORITY(1, "MEDIUM_PRIORITY"),
    HIGH_PRIORIT(2, "HIGH_PRIORITY");

    private Integer code;
    private String description;

    private Priority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priority toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Priority instance : Priority.values()) {
            if (code.equals(instance.getCode())) {
                return instance;
            }
        }

        throw new IllegalArgumentException("Invalid Priority");
    }

}
