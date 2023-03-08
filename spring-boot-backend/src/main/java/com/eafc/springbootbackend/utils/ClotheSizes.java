package com.eafc.springbootbackend.utils;

public enum ClotheSizes {
    XS("XS"),
    S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    XXL("XXL");

    private String value;

    ClotheSizes(String value) {
        this.value = value;
    }
}
