package com.packtpublishing.tddjava.alexandria;

enum States {
    BOUGHT (1),
    RENTED (2),
    AVAILABLE (3),
    CENSORED (4);

    private final int value;

    private States(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static States fromValue(int value) {
        for (States states : values()) {
            if(states.getValue() == value) {
                return states;
            }
        }
        throw new IllegalArgumentException("Value '" + value + "' could not be found in States");
    }
}
