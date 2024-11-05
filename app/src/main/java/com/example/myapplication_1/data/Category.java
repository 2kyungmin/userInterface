package com.example.myapplication_1.data;

public enum Category {
    EXCERCISE, HEALTH, READING, ENGLISH_WORDS;
    public enum Excercise {
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
            case EXCERCISE:
                return Excercise.values().length;
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


