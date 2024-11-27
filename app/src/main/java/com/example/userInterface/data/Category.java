package com.example.userInterface.data;

public enum Category {
    EXERCISE("운동 5-GO"),
    READING("독서 5-GO"),
    HEALTH("건강 5-GO"),
    WORDS("건강 5-GO");

    final String name;
    Category(String name) {
        this.name = name;
    }
}
