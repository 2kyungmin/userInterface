package com.example.userInterface.dto;

import androidx.annotation.NonNull;

public enum Emoji {
    ANGRY("angry"),
    SAD("sad"),
    HAPPY("happy");

    private final String str;

    Emoji(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

}
