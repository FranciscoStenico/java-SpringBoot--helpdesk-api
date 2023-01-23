package com.api.helpdesk.domain.enums;

public enum Status {

    OPENED(0, "OPENED"), 
    IN_PROGRESS(1, "IN_PROGRESS"), 
    CLOSED(2, "CLOSED");

    private Integer code;
    private String description;

    private Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Status instance : Status.values()) {
            if (code.equals(instance.getCode())) {
                return instance;
            }
        }

        throw new IllegalArgumentException("Invalid Status");
    }

}
