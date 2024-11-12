package com.kyung.testapp.data;

public enum Category {
    EXERCISE, HEALTH, READING, ENGLISH_WORDS;
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

    public int getSize(){
        switch (this){
            case EXERCISE:
                return Exercise.values().length;
            case HEALTH:
                return Health.values().length;
            case READING:
                return Reading.values().length;
            case ENGLISH_WORDS:
                return EnglishWords.values().length;
        }
        return -1;
    }
}


