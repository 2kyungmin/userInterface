package com.example.userInterface.dto;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    EXERCISE("운동 5-GO", Exercise.getChallenges()),
    READING("독서 5-GO", Reading.getChallenges()),
    HEALTH("건강 5-GO", Health.getChallenges()),
    ENGLISH("영어 5-GO", English.getChallenges());

    private String categoryName;
    private List<String> challengeList;

    public static List<String> getList(String categoryName) {

        switch (categoryName) {
            case ("운동 5-GO"):
                return EXERCISE.challengeList;
            case ("독서 5-GO"):
                return READING.challengeList;
            case ("건강 5-GO"):
                return HEALTH.challengeList;
            case ("영어 5-GO"):
                return ENGLISH.challengeList;
            default:
                return null;
        }
    }

    Category(String categoryName, List<String> challengeList) {
        this.categoryName = categoryName;
        this.challengeList = challengeList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<String> getChallengeList() {
        return challengeList;
    }
}

enum Exercise{
    PLANK("플랭크 5-GO"),
    SIT_UP("윗몸일으키기 5-GO"),
    SQUAT("스쿼트 5-GO"),
    WORK("제자리 걷기 5-GO");

    private String challenge;

    Exercise(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public static List<String> getChallenges() {
        List<String> challenges = new ArrayList<>();
        for(Exercise exercise: Exercise.values()){
            challenges.add(exercise.getChallenge());
        }
        return challenges;
    }
}

enum Reading{
    SPEAK("소리내서 읽기 5-GO"),
    WRITE("필사 5-GO");

    private String challenge;

    Reading(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public static List<String> getChallenges() {
        List<String> challenges = new ArrayList<>();
        for(Reading reading: Reading.values()){
            challenges.add(reading.getChallenge());
        }
        return challenges;
    }
}

enum Health {
    MEDITATION("명상 5-GO"),
    STRETCH("스트레칭 5-GO");

    private String challenge;

    Health(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public static List<String> getChallenges() {
        List<String> challenges = new ArrayList<>();
        for(Health health: Health.values()){
            challenges.add(health.getChallenge());
        }
        return challenges;
    }
}

enum English{
    SONG("팝송 듣기 5-GO"),
    WORDS("영단어 공부 5-GO");

    private String challenge;

    English(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public static List<String> getChallenges() {
        List<String> challenges = new ArrayList<>();
        for(English english: English.values()){
            challenges.add(english.getChallenge());
        }
        return challenges;
    }
}
