package com.github.iTzhsnu.WynnSearcher.general;

public enum ErrorType {
    LATEST("Latest"),
    SUCCEED("Archive Loaded"),
    FAILED("Load Failed")
    ;

    private final String value;

    ErrorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
