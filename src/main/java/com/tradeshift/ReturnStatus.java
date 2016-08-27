package com.tradeshift;

/**
 * Return codes
 */
public enum ReturnStatus {
    OK("OK", 0),
    ERROR("ERROR", -100);

    ReturnStatus(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public String value;
    public int code;
}

