package com.example.userInterface.data;

import java.util.List;

public enum Category {
    EXERCISE("운동 5-Go"),
    HEALTH("건강 5-Go"),
    READING("독서 5-Go"),
    ENGLISH_WORDS("영단어 5-Go");

    public enum Exercise {
        스쿼트,
        윗몸일으키기,
        팔굽혀펴기,
        매달리기,
        플랭크,
        제자리걷기
    }
    public enum Health{
        명상하기
    }
    public enum Reading{

    }
    public enum EnglishWords{

    }

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return category;
    }

    public static Category fromString(String string) {
        for (Category category : Category.values()) {
            if (category.getCategoryName().equals(string)) {
                return category;
            }
        }
        return null;
    }
}


