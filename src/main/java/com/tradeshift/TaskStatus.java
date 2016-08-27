package com.tradeshift;

/**
 * Enumerated task status values
 */
public enum TaskStatus {
    NEW("NEW", 0),
    ASSIGNED("ASSIGNED", 1),
    COMPLETE("COMPLETE", 2),
    UNKNOWN("UNKNOWN", -1);

    public String value;
    public int code;

    TaskStatus(String value, int code) {
        this.value = value;
        this.code = code;
    }

    /**
     *
     * @param arg
     * @return
     */
    public static TaskStatus discoverMatchingEnum(String arg) {
        TaskStatus result = UNKNOWN;

        if (arg == null) {
            return result;
        }

        for (TaskStatus token : TaskStatus.values()) {
            if (token.value.equalsIgnoreCase(arg)) {
                result = token;
            }
        }

        return result;
    }
}
