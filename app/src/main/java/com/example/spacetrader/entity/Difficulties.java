package com.example.spacetrader.entity;

public enum Difficulties {
    EASY ("Easy"),
    NORMAL ("Normal"),
    DIFFICULT ("Difficult"),
    INSANE ("Insane"),
    IMPOSSIBLE("Impossible");

    private final String code;

    Difficulties(String pcode) {
        code = pcode;
    }

    public String getCode() { return code; }


    public String toString() { return code; }
}
