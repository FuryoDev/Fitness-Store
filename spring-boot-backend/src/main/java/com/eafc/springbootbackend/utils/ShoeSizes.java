package com.eafc.springbootbackend.utils;

public enum ShoeSizes {
    THIRTY_SIX("36"),
    THIRTY_SEVEN("37"),
    THIRTY_EIGHT("38"),
    THIRTY_NINE("39"),
    FORTY("40"),
    FORTY_ONE("41"),
    FORTY_TWO("42"),
    FORTY_TREE("43"),
    FORTY_FOUR("44"),
    FORTY_FIVE("45"),
    FORTY_SIX("46"),
    FORTY_SEVEN("47");

    private String value;

    ShoeSizes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
